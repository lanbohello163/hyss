package com.zxtech.platform.context;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.zxtech.platform.context.lifecycle.BundleLife;
import com.zxtech.platform.utils.KeyValue;
import com.zxtech.platform.utils.ReflectUtil;

/**
 * Title: 平台全局常量类<br>
 * Description: 通过该类可以获取平台共享的对象和数据，是所有组件和应用获取平台上下文对象和共享数据的桥梁 <br>
 * Date: 2012-08-2<br>
 * Modify-Date:2012-08-2<br>
 * Copyright (c) 2012 zxtech <br>
 * 
 * @version 1.0
 * @author
 */
public class PlatformGlobalVar {
	private static Logger logger = LoggerFactory.getLogger(PlatformGlobalVar.class);
	/**
	 * Servlet的上下文对象
	 * 
	 **/
	public static ServletContext SERVLET_CONTEXT;
	/**
	 * Spring应用的上下文对象
	 * 
	 **/
	public static ApplicationContext APPLICATION_CONTEXT;
	/**
	 * 加载类路径根目录下所有properties名值对文件后，将名值对数据装入该全局Map，装入Map会和配置文件中前后顺序不同
	 * 
	 **/
	public static Map<String, String> SYS_PROPERTIES = new Hashtable<String, String>();

	/**
	 * 加载类路径根目录下所有properties名值对文件后，将名值对数据装入该全局Properties中，properties会保持顺序
	 * 
	 **/
	public static List<KeyValue<String, String>> SYS_PROPERTIES_WITH_ORDER = new Vector<KeyValue<String, String>>();
	/**
	 * 用来保存各个组件入口生命周期对象的有序集合
	 **/
	public static List<BundleLife> BUNDLE_CONTEXT_LIST = new Vector<BundleLife>();

	/**
	 * 数据库中property属性的数据库表加载到该成员变量
	 * 
	 **/
	public static Map<String, String> SYS_DB_PROPERTIES = new Hashtable<String, String>();

	private static Map<String, Object> CONTEXT_CACHE = new Hashtable<String, Object>();

	/**
	 * 获取文件夹在Web项目中的绝对路径
	 * 
	 * @param path
	 *            文件在Web中的相对路径
	 * @return String 返回文件在Web中的绝对路径
	 * 
	 **/
	public static String getRealPath(String path) {
		return SERVLET_CONTEXT.getRealPath(path);
	}

	/**
	 * 允许各个组件将相关启动的上下文对象注册到全局上下文常量中
	 * 
	 * @param name
	 *            ，注册上下文的名字
	 * @param context
	 *            ，注册的上下文对象
	 **/
	public static void registContext(String name, Object context) {
		if (CONTEXT_CACHE == null)
			CONTEXT_CACHE = new Hashtable<String, Object>();
		CONTEXT_CACHE.put(name, context);
	}

	/**
	 * 根据给定的key值获取上下文对象
	 * 
	 * @param name
	 *            ，注册上下文的名字
	 * @return 从缓存容器中根据key获取缓存的上下文对象
	 **/
	public static Object getContextByName(String name) {
		if (!CONTEXT_CACHE.containsKey(name))
			return null;
		else
			return CONTEXT_CACHE.get(name);
	}

	/**
	 * 刷新各个的组件
	 **/
	public static void refreshComponent() {
		// 调用各个组件销毁方法
		destroyComponent();
		// 初始化所有组件
		initComponent();
	}

	/**
	 * 根据Property配置文件中对于组件库中组件的配置情况，调用各个组件库中生命周期方法，完成各个组件的销毁动作
	 **/
	public static void destroyComponent() {
		int size = BUNDLE_CONTEXT_LIST.size();
		if (size > 0) {
			for (int i = size - 1; i >= 0; i--) {
				BundleLife obj = BUNDLE_CONTEXT_LIST.get(i);
				// 调用生命周期销毁函数
				obj.destroy();
			}
			// 清空集合
			BUNDLE_CONTEXT_LIST.clear();
		}
	}

	/**
	 * 根据Property配置文件中对于组件库中组件的配置情况，调用各个组件库中生命周期方法，完成各个组件的初始化
	 **/
	public static void initComponent() {
		// 判断组件生命周期对象集合是否为空，如果不为空，清空，以便装载对象
		if (BUNDLE_CONTEXT_LIST.size() > 0)
			BUNDLE_CONTEXT_LIST.clear();
		// 根据properties配置文件决定加载的组件
		List<KeyValue<String, String>> list = SYS_PROPERTIES_WITH_ORDER;
		for (int i = 0; i < list.size(); i++) {
			KeyValue<String, String> keyValue = list.get(i);
			String key = keyValue.getKey();
			String value = keyValue.getValue();
			if (key != null && value != null & key.startsWith("com.zxtech.platform.")
					&& "true".equals(value.toLowerCase())) {
				logger.debug("开始调用组件【" + key + "】中的BundleLife方法！");
				Class<?> clazz = null;
				try {
					clazz = Class.forName(key);
				} catch (ClassNotFoundException e) {
					logger.error("系统启动失败！Properties中配置的启动组件类未找到，请确定该组件已经包含在项目中。【组件名字：" + key + "】");
					throw new RuntimeException(e);
				}

				if (ReflectUtil.isInterfaceImpl(clazz, BundleLife.class)) {
					try {
						BundleLife obj = (BundleLife) ReflectUtil.newInstance(clazz);
						obj.init();
						// 将生命周期对象保存到有序集合中
						BUNDLE_CONTEXT_LIST.add(obj);
					} catch (IllegalAccessException e) {
						logger.error("系统启动失败！无法创建组件中启动类对象。【组件名字：" + key + "】", e);
						throw new RuntimeException(e);
					} catch (InstantiationException e) {
						logger.error("系统启动失败！无法创建组件中启动类对象。【组件名字：" + key + "】", e);
						throw new RuntimeException(e);
					}
				} else {
					logger.error(
							"系统启动失败！组件启动类没有实现组件平台规定的com.zxtech.platform.context.BundleLife接口。【组件名字：" + key + "】");
					throw new RuntimeException(
							"系统启动失败！组件启动类没有实现组件平台规定的com.zxtech.platform.context.BundleLife接口。【组件名字：" + key + "】");
				}
			}
		}
	}
}
