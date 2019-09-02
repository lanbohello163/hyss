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
import com.zxtech.ess.module.base.bean.BaseAttAndMagBean;
import com.zxtech.ess.module.base.service.IBaseCompAttachService;
import com.zxtech.ess.module.base.service.IBaseCompanyService;
import com.zxtech.ess.module.gen.bean.BaseCompAttach;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.vo.UserBean;

@Controller
@RequestMapping("/base")
public class BaseCompAttachController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(BaseCompAttachController.class);
	
	@Autowired
	private IBaseCompAttachService baseCompAttachService;
	/**
	 * 获取附件列表
	 * @param request
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/company/getattachlist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getattachlist(HttpServletRequest request,BaseAttAndMagBean bean) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return baseCompAttachService.compAttachPagingList(bean, user);
	}
	/**
	 * 增加附件
	 * @param request
	 * @param bean
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/company/attachadd.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, BaseCompAttach bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseCompAttachService.save(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_COMPANY, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ADD_ATTACH
                            ));
		}
		return result;
	}
	/**
	 * 删除附件
	 * @param request
	 * @param bean
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/company/attachdelete.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doDel(HttpServletRequest request, BaseCompAttach bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseCompAttachService.delete(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_COMPANY, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_DELETE_ATTACH));
		}
		return result;
	}
}
