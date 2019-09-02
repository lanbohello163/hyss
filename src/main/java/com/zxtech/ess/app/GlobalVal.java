package com.zxtech.ess.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.zxtech.ess.app.interceptor.CacheSignatureMapperUtil;
import com.zxtech.ess.app.util.SysTools;
import com.zxtech.ess.module.gen.bean.SysFunction;
import com.zxtech.ess.module.gen.bean.SysMenu;
import com.zxtech.ess.module.gen.mapper.SysFunctionMapper;
import com.zxtech.ess.module.gen.mapper.SysMenuMapper;
import com.zxtech.platform.context.PlatformGlobalVar;
import com.zxtech.platform.vo.TreeBean;

public class GlobalVal {

	private static Logger log = LoggerFactory.getLogger(GlobalVal.class);

	public static ApplicationContext ctx;
	private static List<TreeBean> menuList = new ArrayList<TreeBean>();
	private static List<SysFunction> funcList = new ArrayList<SysFunction>();
	private static Map<String, Map<String, Object>> promptContentMap = new HashMap<>();
	private static Map<Integer, List<SysFunction>> menuFuncMap = new HashMap<Integer, List<SysFunction>>();

	public static synchronized void init() {
		try {
			ctx = PlatformGlobalVar.APPLICATION_CONTEXT;
			CacheSignatureMapperUtil.init();

			SysMenuMapper sysMenuMapper = (SysMenuMapper) ctx.getBean(SysMenuMapper.class);
			SysFunctionMapper sysFunctionMapper = (SysFunctionMapper) ctx.getBean(SysFunctionMapper.class);
			
			List<SysMenu> menuTempList = sysMenuMapper.selectAll();
			SysTools.fillMenu(menuTempList, menuList);
			
			funcList = sysFunctionMapper.selectAll();
			SysTools.fillMenuFunctionMap(funcList, menuFuncMap);
			
		} catch (Exception e) {
			log.error("==Application init ERROR!!==" + e.getMessage());
			e.printStackTrace();
		}
	}

	public static List<TreeBean> getMenuList() {
		return menuList;
	}

	public static List<SysFunction> getFunctionList() {
		return funcList;
	}

	public static Map<Integer, List<SysFunction>> getMenuFuncMap() {
		return menuFuncMap;
	}

	public static Map<String, Map<String, Object>> getPromptContentMap() {
		return promptContentMap;
	}
}