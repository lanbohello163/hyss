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
import com.zxtech.ess.module.base.bean.BasePrjPersonSearchBean;
import com.zxtech.ess.module.base.service.IBasePrjPersonService;
import com.zxtech.ess.module.gen.bean.BaseProjectPerson;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.vo.UserBean;

@Controller
@RequestMapping("/base")
public class BasePrjPersonController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(BasePrjPersonController.class);
	
	@Autowired
	private IBasePrjPersonService basePrjPersonService;
	/**
	 * 获取地盘联系人列表
	 * @param request
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/project/getpersonlist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> fetchEmployeeWithPagingList(HttpServletRequest request,BasePrjPersonSearchBean bean) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return basePrjPersonService.basePrjPersonPagingList(bean, user);
	}
	/**
	 * 增加地盘联系人
	 * @param request
	 * @param bean
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/project/personadd.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, BaseProjectPerson bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = basePrjPersonService.save(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_PRJ_PERSON, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ADD));
		}
		return result;
	}
	/**
	 * 修改地盘联系人
	 * @param request
	 * @param bean
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/project/personmodify.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, BaseProjectPerson bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = basePrjPersonService.update(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_PRJ_PERSON, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MODIFY));
		}
		return result;
	}
	/**
	 * 删除地盘联系人
	 * @param request
	 * @param bean
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/project/persondelete.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doDel(HttpServletRequest request, BaseProjectPerson bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = basePrjPersonService.delete(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_PRJ_PERSON, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_DELETE));
		}
		return result;
	}
}
