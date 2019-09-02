package com.zxtech.ess.module.sys.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.zxtech.ess.module.gen.bean.ApiErrorLog;
import com.zxtech.ess.module.sys.bean.SysApiLogSearchBean;
import com.zxtech.ess.module.sys.service.ISysApiLogService;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.vo.UserBean;

@Controller
@RequestMapping("/sys")
public class SysApiLogController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(SysApiLogController.class);
	
	@Autowired
	private ISysApiLogService sysApiLogService;
	/**
	 * 获取接口错误信息列表
	 * @param request
	 * @return
	 * @author syp661
	 * 2019/2/11
	 */
	@RequestMapping(value = "/sysapiloglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> sysStationPagingList(HttpServletRequest request,SysApiLogSearchBean bean) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		bean.setDataPermissionList(user);
		return sysApiLogService.sysApiLogPagingList(bean, user);
	}
	
	/**
	 * 接口错误信息处理
	 * @param request
	 * @return
	 * @author syp661
	 * 2019/2/11
	 */
	@RequestMapping(value = "/sysapilogmodify.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String sysapilogmodify(HttpServletRequest request,ApiErrorLog bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = sysApiLogService.sysApiLogModify(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_STATION, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MODIFY));
		}
		return result;
	}
	
}
