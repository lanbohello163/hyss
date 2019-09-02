package com.zxtech.platform.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxtech.platform.context.PlatformGlobalVar;
import com.zxtech.platform.context.lifecycle.BundleLife;
import com.zxtech.platform.property.service.ISysPropertyService;

/**
 * 加载数据库配置表信息的生命周期回调类。由组件管理器统一调用
 * 
 * @author
 * @version 1.0.0
 */
public class SysPropertyInitBundleLife implements BundleLife {
	private static Logger logger = LoggerFactory.getLogger(SysPropertyInitBundleLife.class);

	public void init() {
		logger.debug("开始初始化syspropertyinit读取系统配置表和操作系统配置表组件......");
		// 获取业务层对象
		ISysPropertyService sps = (ISysPropertyService) PlatformGlobalVar.APPLICATION_CONTEXT
				.getBean("platformSysPropertyService");
		// 调用业务方法，将系统配置表中的配置信息加载全局常量
		sps.readSysPropertyIntoGlobalVar();
		logger.debug("结束初始化syspropertyinit读取系统配置表和操作系统配置表组件......");
	}

	public void destroy() {
	}
}