package com.zxtech.ess.module.sys.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxtech.ess.module.sys.bean.SysLogSearchBean;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

public interface ISysLogService {

	public Map<String, Object> sysLogListPage(SysLogSearchBean queryInfo);

	public ResultBean export(SysLogSearchBean queryInfo, HttpServletRequest request, HttpServletResponse response, UserBean user);

}