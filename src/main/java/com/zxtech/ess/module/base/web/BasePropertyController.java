package com.zxtech.ess.module.base.web;

import java.util.List;
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
import com.zxtech.ess.module.base.bean.BasePropertySearchBean;
import com.zxtech.ess.module.base.service.IBasePropertyService;
import com.zxtech.ess.module.gen.bean.BaseKeyProperty;
import com.zxtech.ess.module.gen.bean.BaseProperty;
import com.zxtech.ess.module.gen.bean.BasePropertyPerson;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

@Controller
@RequestMapping("/base")
public class BasePropertyController {

	private final static Logger LOGGER = LoggerFactory.getLogger(BasePropertyController.class);

	@Autowired
	private IBasePropertyService basePropertyService;

	/**
	 * Description: 获取物业分页列表
	 * @author whx
	 */
	@RequestMapping(value = "/propertylistpage.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> propertyListPage(HttpServletRequest request, BasePropertySearchBean queryInfo) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		queryInfo.setDataPermissionList(user);
		return basePropertyService.propertyListPage(queryInfo);
	}
	/**
	 * Description: 获取大客户下拉框列表
	 * @author whx
	 */
	@RequestMapping(value = "/getkeypropertylist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<BaseKeyProperty> keyPropertyList(HttpServletRequest request, String combobox_type) {
		return basePropertyService.keyPropertyList(combobox_type);
	}
	/**
	 * Description: add method
	 * @author whx
	 */
	@RequestMapping(value = "/propertyadd.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doSave(HttpServletRequest request, BaseProperty bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = basePropertyService.save(bean, user);
		} catch (Exception e) {
			if(e.getMessage().contains("UNIQUE KEY") && e.getMessage().contains("uq_property_code")){
				result =  ResultConstants.REPEAT;
		    }else {
		    	LOGGER.error(e.getMessage(), e);
		    	result = ResultConstants.ERROR;
		    }
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_PROPERTY, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_PROPERTY_CUSTOMER_INFORMATION, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ADD));
		}
		return result;
	}
	
	/**
	 * Description: modify method
	 * @author whx
	 */
	@RequestMapping(value = "/propertymodify.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doUpdate(HttpServletRequest request, BaseProperty bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = basePropertyService.update(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_PROPERTY, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_PROPERTY_CUSTOMER_INFORMATION, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MODIFY));
		}
		return result;
	}
	/**
	 * Description: delete method
	 * @author whx
	 */
	@RequestMapping(value = "/propertydelete.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doDelete(HttpServletRequest request, BaseProperty bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = basePropertyService.delete(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_PROPERTY, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_PROPERTY_CUSTOMER_INFORMATION, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_DELETE));
		}
		return result;
	}
	/**
	 * Description: 获取物业员工分页列表
	 * @author whx
	 */
	@RequestMapping(value = "/propertypersonlistpage.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> propertyPersonListPage(HttpServletRequest request, BasePropertySearchBean queryInfo) {
		return basePropertyService.propertyPersonListPage(queryInfo);
	}
	/**
	 * Description: 新增物业员工
	 * @author whx
	 */
	@RequestMapping(value = "/propertypersionadd.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doPersionSave(HttpServletRequest request, BasePropertyPerson bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = basePropertyService.doPersionSave(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_PROPERTY, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_PROPERTY_CUSTOMER_EMPLOYEE_INFORMATION, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ADD));
		}
		return result;
	}
	
	/**
	 * Description: 修改物业员工
	 * @author whx
	 */
	@RequestMapping(value = "/propertypersionmodify.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doPersionUpdate(HttpServletRequest request, BasePropertyPerson bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = basePropertyService.doPersionUpdate(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_PROPERTY, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_PROPERTY_CUSTOMER_EMPLOYEE_INFORMATION, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MODIFY));
		}
		return result;
	}
	/**
	 * Description: 删除物业员工
	 * @author whx
	 */
	@RequestMapping(value = "/propertypersiondelete.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doPersionDelete(HttpServletRequest request, BasePropertyPerson bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = basePropertyService.doPersionDelete(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_PROPERTY, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_PROPERTY_CUSTOMER_EMPLOYEE_INFORMATION, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_DELETE));
		}
		return result;
	}
	/**
	 * 物业导出
	 * @author whx
	 */
	@RequestMapping(value = "/propertyexport.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResultBean export(HttpServletRequest request, HttpServletResponse response, BasePropertySearchBean queryInfo)  {
		ResultBean resultBean = new ResultBean();
		try {
			UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
			queryInfo.setDataPermissionList(user);
			resultBean = basePropertyService.export(user, queryInfo);
			if (ResultConstants.SUCCESS_CODE.equals(resultBean.getStatus())) {
				SysTools.saveLog(user,
						StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
								OptBehaviorConstants.LOG_MENU_BASE_PROPERTY, OptBehaviorConstants.LOG_SEPARATOR,
								OptBehaviorConstants.LOG_MENU_BASE_PROPERTY_CUSTOMER_INFORMATION, OptBehaviorConstants.LOG_SEPARATOR,
								OptBehaviorConstants.LOG_EXPORT));
			}
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			resultBean.setStatus(ResultConstants.ERROR_CODE);
			resultBean.setMsg(ResultConstants.ERROR);
		}
		return resultBean;
	}
	/**
	 * 大客户下拉框
	 * @param request
	 * @param combobox_type
	 * @return
	 * @author: syp637
	 */
	@RequestMapping(value = "/getKeyPropertyCombobox.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> getKeyPropertyCombobox(HttpServletRequest request,BaseKeyProperty bean, String combobox_type) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return basePropertyService.getKeyPropertyCombobox(combobox_type, user ,bean);
	}
	
	@RequestMapping(value = "/getKeyPropertyNotCommonCombobox.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> getKeyPropertyNotCommonCombobox(HttpServletRequest request, String combobox_type) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return basePropertyService.getKeyPropertyNotCommonCombobox(combobox_type, user);
	}
	/**
	 * 自动生成客户编码
	 * @author jic
	 */
	@RequestMapping(value = "/initPropertyCode.do", method = { RequestMethod.POST })
	@ResponseBody
	public String initPropertyCode(HttpServletRequest request) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return basePropertyService.initPropertyCode(user);
	}
}