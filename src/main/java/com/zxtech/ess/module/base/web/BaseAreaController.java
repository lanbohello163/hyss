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
import com.zxtech.ess.module.base.bean.BaseAreaSearchBean;
import com.zxtech.ess.module.base.service.IBaseAreaService;
import com.zxtech.ess.module.gen.bean.BaseArea;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

@Controller
@RequestMapping("/base")
public class BaseAreaController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(BaseAreaController.class);
	
	@Autowired
	private IBaseAreaService baseAreaService;
	/**
	 * Description: 获取维保片集合-下拉框用
	 * @author whx
	 */
	@RequestMapping(value = "/arealist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> areaList(HttpServletRequest request, BaseAreaSearchBean queryInfo, String combobox_type) {
		if (queryInfo.getStat_id() != null) {
			UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
			queryInfo.setDataPermissionList(user);
			return baseAreaService.areaList(queryInfo, combobox_type);
		}else {
			return null;
		}
	}
	/**
	 * Description: 获取维保片集合-弹出框用
	 * @author whx
	 */
	@RequestMapping(value = "/fetchWindowAreaList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> fetchWindowAreaList(HttpServletRequest request, BaseAreaSearchBean queryInfo) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		queryInfo.setDataPermissionList(user);
		return baseAreaService.fetchWindowAreaList(queryInfo, user);
	}
	/**
	 * 获取維保片列表
	 * @param request
	 * @return
	 * @author wsm
	 */	
	@RequestMapping(value = "/basearealist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> baseAreaPagingList(HttpServletRequest request, BaseAreaSearchBean bean) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		bean.setDataPermissionList(user);
		return baseAreaService.baseAreaPagingList(bean);
	}
	/**
	 * 增加維保片
	 * @param request
	 * @param bean
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/baseareaadd.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, BaseArea bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseAreaService.save(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_AREA, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ADD));
		}
		return result;
	}
	/**
	 * 修改维保片
	 * @param request
	 * @param bean
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/baseareamodify.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, BaseArea bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseAreaService.update(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_AREA, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MODIFY));
		}
		return result;
	}
	/**
	 * 禁用维保片
	 * @param request
	 * @param bean
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/baseareainactive.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doDisable(HttpServletRequest request, BaseArea bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseAreaService.disable(bean, user);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_AREA, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_INACTIVE));
		}
		return result;
	}
	/**
	 * 启用维保片
	 * @param request
	 * @param bean
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/baseareaactive.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doAble(HttpServletRequest request, BaseArea bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseAreaService.able(bean, user);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_AREA, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ACTIVE));
		}
		return result;
	}
	/**
	 * 导出维保片列表
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/baseareaexport.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResultBean export( HttpServletRequest request, HttpServletResponse response, BaseAreaSearchBean queryInfo) {
		ResultBean resultBean = new ResultBean();
		try {
			UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
			queryInfo.setDataPermissionList(user);
			resultBean = baseAreaService.export(user, queryInfo);
			if (ResultConstants.SUCCESS_CODE.equals(resultBean.getStatus())) {
				SysTools.saveLog(user,
						StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
								OptBehaviorConstants.LOG_MENU_BASE_AREA, OptBehaviorConstants.LOG_SEPARATOR,
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
	 * 当前维保片下存在活动状态的维保组或在职维保员工时,禁用维保片
	 * @author jic
	 */
	@RequestMapping(value = "/baseareaforbidden.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doForbiddenArea(HttpServletRequest request, BaseArea bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseAreaService.doForbiddenArea(bean, user);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_AREA, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_INACTIVE));
		}
		return result;
	}
}




