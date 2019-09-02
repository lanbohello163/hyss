package com.zxtech.ess.app.interceptor;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.springframework.data.redis.connection.RedisConnection;

import com.google.gson.Gson;
import com.zxtech.platform.utils.cache.CacheTemplate;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public class CacheSignatureMapperUtil {

	private static final String BASE_PACKAGE = "com.zxtech";
	
	public static final String CACHED_UPDATE_TIME_KEY = "CACHED_UPDATE_TIME_KEY@HITACHI_CACHE";
	
	public static final String CACHE_SIGNATURE_MAPPER_KEY = "CACHE_SIGNATURE_MAPPER_KEY@HITACHI_CACHE";
	
	public static final String CACHE_PREFIX = "COM.ZXTECH_";
	
	public static final String INDENTITY = CACHE_PREFIX + "HYSS_";
	
	public static final long EXPIRE_MILLIS = 1000 * 60 * 60 * 2L; // 20 hours
	
	public static void init() {
		String packageName = BASE_PACKAGE;
		// 第一个class类的集合
		List<Class<?>> classes = new ArrayList<Class<?>>();
		// 是否循环迭代
		boolean recursive = true;
		// 获取包的名字，并进行替换
		String packageDirName = packageName.replace('.', '/');
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			while (dirs.hasMoreElements()) {
				URL url = dirs.nextElement();
				String protocol = url.getProtocol();
				if ("file".equals(protocol)) {
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// 以文件的方式扫描整个包下的文件 并添加到集合中
					findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
				} else if ("jar".equals(protocol)) {
					// 如果是jar包文件
					// 定义一个JarFile
					JarFile jar;
					try {
						// 获取jar
						jar = ((JarURLConnection) url.openConnection()).getJarFile();
						// 从此jar包 得到一个枚举类
						Enumeration<JarEntry> entries = jar.entries();
						// 同样的进行循环迭代
						while (entries.hasMoreElements()) {
							// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							// 如果是以/开头的
							if (name.charAt(0) == '/') {
								// 获取后面的字符串
								name = name.substring(1);
							}
							// 如果前半部分和定义的包名相同
							if (name.startsWith(packageDirName)) {
								int idx = name.lastIndexOf('/');
								// 如果以"/"结尾 是一个包
								if (idx != -1) {
									// 获取包名 把"/"替换成"."
									packageName = name.substring(0, idx).replace('/', '.');
								}
								// 如果可以迭代下去 并且是一个包
								if ((idx != -1) || recursive) {
									// 如果是一个.class文件 而且不是目录
									if (name.endsWith(".class") && !entry.isDirectory()) {
										// 去掉后面的".class" 获取真正的类名
										String className = name.substring(packageName.length() + 1, name.length() - 6);
										try {
											// 添加到classes
											classes.add(Class.forName(packageName + '.' + className));
										} catch (ClassNotFoundException e) {
											e.printStackTrace();
										}
									}
								}
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (classes != null && classes.size() > 0) {
			Long longTime = System.currentTimeMillis();
			for (Class<?> cls : classes) {
				// 获取类中的所有的方法
				Method[] methods = cls.getDeclaredMethods();
				if (methods != null && methods.length > 0) {
					CacheTemplate.REDIS.execute((RedisConnection conn) -> {
						for (Method method : methods) {
							CacheSignatureMapper redisCacheRelation = (CacheSignatureMapper) method
									.getAnnotation(CacheSignatureMapper.class);
							if (redisCacheRelation != null) {
								Class<?>[] redisCacheRelationValues = redisCacheRelation.value();
								String methodName = cls.getCanonicalName() + "." + method.getName();
								String methodNameCount = methodName + "_COUNT";
								
								Set<String> methodSet = new HashSet<>();
								Set<String> methodCountSet = new HashSet<>();
								String methodNameFinal = INDENTITY + methodName;
								String methodCountNameFinal = INDENTITY + methodNameCount;
								
								conn.del(methodNameFinal.getBytes());
								conn.del(methodCountNameFinal.getBytes());
								for (Class<?> redisCacheRelationValue : redisCacheRelationValues) {
									String redisCacheRelationValueFinal = CACHE_PREFIX + redisCacheRelationValue.getCanonicalName();
									methodSet.add(redisCacheRelationValueFinal);
									methodCountSet.add(redisCacheRelationValueFinal);
									conn.set(redisCacheRelationValueFinal.getBytes(), String.valueOf(longTime).getBytes());
								}
								conn.set(methodNameFinal.getBytes(), new Gson().toJson(methodSet).getBytes());
								conn.set(methodCountNameFinal.getBytes(), new Gson().toJson(methodCountSet).getBytes());
							}
						}
						return null;
					});
				}
			}
		}
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 */
	private static void findAndAddClassesInPackageByFile(String packageName, String packagePath,
			final boolean recursive, List<Class<?>> classes) {
		// 获取此包的目录 建立一个File
		File dir = new File(packagePath);
		// 如果不存在或者 也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive,
						classes);
			} else {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0, file.getName().length() - 6);
				try {
					// 添加到集合中去
					classes.add(Class.forName(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
