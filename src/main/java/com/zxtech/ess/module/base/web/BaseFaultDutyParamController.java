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
import com.zxtech.ess.module.base.bean.BaseFaultDutyParamSearchBean;
import com.zxtech.ess.module.base.service.IBaseFaultDutyParamService;
import com.zxtech.ess.module.gen.bean.CalFaultDutyParam;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.vo.UserBean;

/**
 * @author: syp639
 */
@Controller
@RequestMapping("/base")
public class BaseFaultDutyParamController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(BaseFaultDutyParamController.class);
	
	@Autowired
	private IBaseFaultDutyParamService baseFaultDutyParamService;
	
	@RequestMapping(value = "/basefaultdutyparamlist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getListWithPaging(HttpServletRequest request, BaseFaultDutyParamSearchBean queryInfo) {
		return baseFaultDutyParamService.getListWithPaging(queryInfo);
	}
	
	@RequestMapping(value = "/basefaultdutyparamadd.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doAdd(HttpServletRequest request, CalFaultDutyParam bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseFaultDutyParamService.add(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_FAULT_DUTY_PARAM, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ADD));
		}
		return result;
	}
	
	@RequestMapping(value = "/basefaultdutyparammodify.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doUpdate(HttpServletRequest request, CalFaultDutyParam bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseFaultDutyParamService.update(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_FAULT_DUTY_PARAM, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MODIFY));
		}
		return result;
	}
	
	@RequestMapping(value = "/basefaultdutyparamenable.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doActive(HttpServletRequest request, CalFaultDutyParam bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseFaultDutyParamService.enable(bean, user);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(),e);
			result = ResultConstants.ERROR;
		}
		
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_FAULT_DUTY_PARAM, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ACTIVE));
		}
		return result;
	}
	
	@RequestMapping(value = "/basefaultdutyparamdisable.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doInactive(HttpServletRequest request, CalFaultDutyParam bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseFaultDutyParamService.disable(bean, user);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(),e);
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_FAULT_DUTY_PARAM, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_INACTIVE));
		}
		return result;
	}
	
	
	
}
