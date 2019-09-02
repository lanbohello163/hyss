package com.zxtech.ess.module.api.utils;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.zxtech.ess.module.gen.bean.SysParameter;
import com.zxtech.ess.module.gen.mapper.SysParameterMapper;
import com.zxtech.platform.context.PlatformGlobalVar;

public class SysParameterInfoUtil {
	
	public static ApplicationContext ctx;

	/**
	 * 获取系统配置参数
	 * @param param_key
	 * @return
	 */
	public static String getSysParameterInfo(String param_key) {
		
		ctx = PlatformGlobalVar.APPLICATION_CONTEXT;
		SysParameterMapper sysParameterMapper = (SysParameterMapper) ctx.getBean(SysParameterMapper.class);
		
		String rtnString = "";
		SysParameter record = new SysParameter();
		record.setParam_key(param_key);
		List<SysParameter> list = sysParameterMapper.selectBySqlConditions(record);
		if (list != null && list.size() > 0) {
			rtnString = list.get(0).getParam_value();
		}
		return rtnString;
	}
}
