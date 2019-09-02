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
import com.zxtech.ess.module.base.bean.BaseDeptSearchBean;
import com.zxtech.ess.module.base.service.IBaseDeptService;
import com.zxtech.ess.module.gen.bean.BaseDept;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

@Controller
@RequestMapping("/base")
public class BaseDeptController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(BaseDeptController.class);
	
	@Autowired
	private IBaseDeptService baseDeptService;

	/**
	 * 获取部门管理列表-Combobox
	 * @param request
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/deptlist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<Map<String, Object>> deptList(HttpServletRequest request,BaseDeptSearchBean bean, String combobox_type) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		bean.setDataPermissionList(user);
		return baseDeptService.deptList(bean,combobox_type,user);
	}
	/**
	 * 获取部门管理列表
	 * @param request
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/basedeptlist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> baseDeptPagingList(HttpServletRequest request,BaseDeptSearchBean bean) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		bean.setDataPermissionList(user);
		return baseDeptService.getListWithPaging(bean);
	}
	/**
	 * 增加部门管理
	 * @param request
	 * @param bean
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/basedeptadd.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, BaseDept bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseDeptService.doAdd(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_DEPT, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ADD));
		}
		return result;
	}
	/**
	 * 修改部门管理
	 * @param request
	 * @param bean
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/basedeptmodify.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, BaseDept bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseDeptService.doUpdate(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_DEPT, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MODIFY));
		}
		return result;
	}
	/**
	 * 禁用部门管理
	 * @param request
	 * @param bean
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/basedeptinactive.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doDisable(HttpServletRequest request, BaseDept bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseDeptService.disable(bean, user);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_DEPT, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_INACTIVE));
		}
		return result;
	}
	/**
	 * 启用部门管理
	 * @param request
	 * @param bean
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/basedeptactive.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doEnable(HttpServletRequest request, BaseDept bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseDeptService.enable(bean, user);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_DEPT, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ACTIVE));
		}
		return result;
	}
	/**
	 * 导出部门管理列表
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/basedeptexport.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResultBean export(HttpServletRequest request, HttpServletResponse response, BaseDeptSearchBean queryInfo)  {
		ResultBean resultBean = new ResultBean();
		try {
			UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
			queryInfo.setDataPermissionList(user);
			resultBean = baseDeptService.export(request, response, user, queryInfo);
			if (ResultConstants.SUCCESS_CODE.equals(resultBean.getStatus())) {
				SysTools.saveLog(user,
						StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
								OptBehaviorConstants.LOG_MENU_BASE_DEPT, OptBehaviorConstants.LOG_SEPARATOR,
								OptBehaviorConstants.LOG_EXPORT));
			}
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			resultBean.setStatus(ResultConstants.ERROR_CODE);
			resultBean.setMsg(ResultConstants.ERROR);
		}
		return resultBean;
	}
}
