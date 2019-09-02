package com.zxtech.ess.module.sys.service;

import java.util.Map;

import com.zxtech.ess.module.gen.bean.ApiErrorLog;
import com.zxtech.ess.module.sys.bean.SysApiLogSearchBean;
import com.zxtech.platform.vo.UserBean;

public interface ISysApiLogService {
	
	public Map<String, Object> sysApiLogPagingList(SysApiLogSearchBean bean, UserBean user);
	
	public String sysApiLogModify(ApiErrorLog bean, UserBean user);
	
}
