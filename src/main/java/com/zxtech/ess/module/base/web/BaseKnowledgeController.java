package com.zxtech.ess.module.base.web;

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
import com.zxtech.ess.module.base.bean.BaseKnowledgeSearchBean;
import com.zxtech.ess.module.base.service.IBaseKnowledgeService;
import com.zxtech.ess.module.gen.bean.BaseKnowledge;
import com.zxtech.ess.module.sys.web.SysRoleController;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.vo.UserBean;

@Controller
@RequestMapping("/base")
public class BaseKnowledgeController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(SysRoleController.class);
	
	@Autowired
	private IBaseKnowledgeService baseKnowledgeService;

	@RequestMapping(value = "/getListWithPaging.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getListWithPaging(HttpServletRequest request, BaseKnowledgeSearchBean queryInfo) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return baseKnowledgeService.getListWithPaging(queryInfo,user);
	}
	
	@RequestMapping(value = "/baseknowledgeadd.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doAdd(HttpServletRequest request, BaseKnowledge bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseKnowledgeService.doAdd(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_KNOWLEDGE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ADD));
		}
		return result;
	}
	
	@RequestMapping(value = "/baseknowledgemodify.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doUpdate(HttpServletRequest request, BaseKnowledge bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseKnowledgeService.doUpdate(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_KNOWLEDGE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MODIFY));
		}
		return result;
	}
	
	@RequestMapping(value = "/baseknowledgedelete.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doDelete(HttpServletRequest request, BaseKnowledge bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseKnowledgeService.doDelete(bean, user);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_KNOWLEDGE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_DELETE));
		}
		return result;
	}
}