package com.zxtech.ess.module.sys.web;

import java.util.List;
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
import com.zxtech.ess.module.gen.bean.SysRole;
import com.zxtech.ess.module.gen.bean.SysRoleMenuFuncRel;
import com.zxtech.ess.module.sys.bean.MenuTreeBean;
import com.zxtech.ess.module.sys.bean.SysRoleSearchBean;
import com.zxtech.ess.module.sys.service.ISysRoleService;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.utils.JsonUtil;
import com.zxtech.platform.vo.UserBean;

@Controller
@RequestMapping("/sys")
public class SysRoleController {

	private final static Logger LOGGER = LoggerFactory.getLogger(SysRoleController.class);

	@Autowired
	private ISysRoleService sysRoleService;

	/**
	 * Description: get records by queryInfo with paging
	 *
	 * @param queryInfo
	 * @return records
	 * @author
	 */
	@RequestMapping(value = "/sysrolelist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getListWithPaging(HttpServletRequest request, SysRoleSearchBean queryInfo) {
		return sysRoleService.getListWithPaging(queryInfo);
	}

	/**
	 * Description: add method
	 *
	 * @param bean
	 * @param menu_select
	 * @return "success" or "error" or user defined
	 * @author
	 */
	@RequestMapping(value = "/sysroleadd.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doAdd(HttpServletRequest request, SysRole bean, String menu_select) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = sysRoleService.doAdd(bean, JsonUtil.jsonArrayToList(menu_select, MenuTreeBean.class), user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_SYS, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_SYS_ROLE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ADD));
		}
		return result;
	}

	/**
	 * Description: update method
	 *
	 * @param bean
	 * @param menu_select
	 * @return "success" or "error" or user defined
	 * @author
	 */
	@RequestMapping(value = "/sysrolemodify.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doUpdate(HttpServletRequest request, SysRole bean, String menu_select) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = sysRoleService.doUpdate(bean, JsonUtil.jsonArrayToList(menu_select, MenuTreeBean.class), user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_SYS, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_SYS_ROLE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MODIFY));
		}
		return result;
	}

	/**
	 * Description: delete method
	 *
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author
	 */
	@RequestMapping(value = "/sysroledelete.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doDelete(HttpServletRequest request, SysRole bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = sysRoleService.doDelete(bean, user);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_SYS, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_SYS_ROLE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_DELETE));
		}
		return result;
	}

	/**
	 * Description: get menu tree
	 *
	 * @param role_id
	 * @return menu tree list
	 * @author
	 */
	@RequestMapping(value = "/sysrolegetmenutree.do", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getMenuTreeBean(HttpServletRequest request) {
		return sysRoleService.getMenuTreeBean();
	}

	/**
	 * Description: get records by queryInfo without paging
	 * 
	 * @param queryInfo
	 * @return records
	 * @author
	 */
	@RequestMapping(value = "/sysrolemenufuncrellist.do", method = { RequestMethod.GET })
	@ResponseBody
	public List<SysRoleMenuFuncRel> getRoleMenuFuncRelList(SysRoleMenuFuncRel queryInfo) {
		return sysRoleService.getRoleMenuFuncRelList(queryInfo);
	}
	
	@RequestMapping(value = "/getsysrolelist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> getSysRolelist(HttpServletRequest request, SysRoleSearchBean queryInfo) {
		return sysRoleService.getSysRolelist(queryInfo);
	}
}