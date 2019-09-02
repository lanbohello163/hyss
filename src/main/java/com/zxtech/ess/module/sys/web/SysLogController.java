package com.zxtech.ess.module.sys.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxtech.ess.app.util.OptBehaviorConstants;
import com.zxtech.ess.app.util.SysTools;
import com.zxtech.ess.module.sys.bean.SysLogSearchBean;
import com.zxtech.ess.module.sys.service.ISysLogService;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

@Controller
@RequestMapping("/sys")
public class SysLogController {

	private final static Logger LOGGER = LoggerFactory.getLogger(SysLogController.class);
	
	@Autowired
	private ISysLogService sysLogService;
	
	@RequestMapping(value = "/sysloglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> sysloglistPage(HttpServletRequest request, SysLogSearchBean queryInfo) {
		return sysLogService.sysLogListPage(queryInfo);
	}
	/**
	 * 导出
	 */
	@RequestMapping(value = "/syslogExport.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResultBean  export(SysLogSearchBean queryInfo, HttpServletRequest request, HttpServletResponse response)  {
		ResultBean resultBean = new ResultBean();
		try {
			UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
			 resultBean = sysLogService.export(queryInfo,request, response,user);
			if (resultBean.getStatus() == ResultConstants.SUCCESS_CODE) {
				SysTools.saveLog(user,
						StringUtils.join(OptBehaviorConstants.LOG_MODULE_SYS, OptBehaviorConstants.LOG_SEPARATOR,
								OptBehaviorConstants.LOG_MENU_SYS_LOG, OptBehaviorConstants.LOG_SEPARATOR,
								OptBehaviorConstants.LOG_EXPORT));
			}
			
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		return resultBean;
	}
	
	
	
	
}