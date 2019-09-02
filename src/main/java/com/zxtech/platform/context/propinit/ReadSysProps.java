package com.zxtech.platform.context.propinit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxtech.platform.context.PlatformGlobalVar;
import com.zxtech.platform.utils.KeyValue;

/**
 * Title: 读取系统properties配置文件<br>
 * Description: 读取系统classpath根路径下的properties配置文件，并加载到全局常量PlatformGlobalVar中 <br>
 * Date: 2012-08-01<br>
 * Modify-Date:2012-08-01<br>
 * Copyright (c) 2012 zxtech <br>
 * 
 * @version 1.0
 * @author
 */
public class ReadSysProps {
	private static Logger log = LoggerFactory.getLogger(ReadSysProps.class);

	static {
		readFileProperties();
	}

	public static void init() {
	}

	/**
	 * 读取项目中的properties文件。需要项目将相关的配置properties文件放到classpath的根路径下，
	 * 那么该组件就会自动加载和读取这些properties文件。<br />
	 * 读取properties文件后，会将所有的properties文件中的key和value的值注册到全局常量类
	 * <code>PlatformGlobalVar</code>中。这样各个组件都可以通过<br />
	 * 依赖syscontext组件并通过PlatformGlobalVar获取相关上下文对象
	 * 
	 */
	private static void readFileProperties() {
		// 获取配置文件的路径信息
		File dir = new File(Thread.currentThread().getContextClassLoader().getResource("").getFile());
		if (!dir.isDirectory()) {
			return;
		}

		for (File file : dir.listFiles()) {
			if (!file.isFile()) {
				continue;
			}
			String name = file.getName();
			String[] temp = name.split("\\.");
			if (temp != null && temp.length >= 2 && "properties".equals(temp[1].toLowerCase())) {
				List<String> lines = new ArrayList<>();
				try {
					lines = FileUtils.readLines(file, "UTF-8");
				} catch (IOException e) {
					log.error("prperties文件读取失败！", e);
					continue;
				}

				for (String s : lines) {
					if (s.contains("=")) {
						int splitIndex = s.indexOf("=");
						String ss1 = s.substring(0, splitIndex).trim();
						String ss2 = s.substring(splitIndex + 1).trim();
						KeyValue<String, String> pair = new KeyValue<String, String>(ss1, ss2);
						// 将配置文件中内容加载到Properties全局变量
						PlatformGlobalVar.SYS_PROPERTIES_WITH_ORDER.add(pair);
						PlatformGlobalVar.SYS_PROPERTIES.put(ss1, ss2);
					}
				}
			}
		}

	}
}
