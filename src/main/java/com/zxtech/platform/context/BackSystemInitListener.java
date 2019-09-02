package com.zxtech.platform.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zxtech.ess.module.api.utils.QueueUtil;
import com.zxtech.platform.context.propinit.ReadSysProps;

/**
 * 用来启动和管理相关各个组件上下文对象的Listener。该Listener需要配置到Web.xml中。
 * 
 * @author
 */
public class BackSystemInitListener implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		PlatformGlobalVar.destroyComponent();
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		PlatformGlobalVar.SERVLET_CONTEXT = sc;

		// 启动Spring框架，并保持上下文对象
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		PlatformGlobalVar.APPLICATION_CONTEXT = ctx;

		// 读取系统Properties配置文件
		ReadSysProps.init();

		// 初始化组件库中各个组件
		PlatformGlobalVar.initComponent();

		// 初始化接口队列
		QueueUtil.init();
	}

}
