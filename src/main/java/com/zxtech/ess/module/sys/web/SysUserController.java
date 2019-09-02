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
import com.zxtech.ess.module.gen.bean.SysUser;
import com.zxtech.ess.module.gen.bean.SysUserDataPermission;
import com.zxtech.ess.module.sys.bean.SysUserSearchBean;
import com.zxtech.ess.module.sys.service.ISysUserService;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.utils.JsonUtil;
import com.zxtech.platform.vo.UserBean;

@Controller
@RequestMapping("/sys")
public class SysUserController {

	private final static Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

	@Autowired
	private ISysUserService sysUserService;

	/**
	 * Description: get records by queryInfo with paging
	 *
	 * @param queryInfo
	 * @return records
	 * @author
	 */
	@RequestMapping(value = "/sysuserlist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getListWithPaging(HttpServletRequest request, SysUserSearchBean queryInfo) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		queryInfo.setDataPermissionList(user);
		return sysUserService.getListWithPaging(queryInfo);
	}

	/**
	 * Description: add method
	 *
	 * @param bean
	 * @param role_select
	 * @param comp_select
	 * @return "success" or "error" or user defined
	 * @author
	 */
	@RequestMapping(value = "/sysuseradd.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doSave(HttpServletRequest request, SysUser bean, String role_ids, String compTreeSelect) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = sysUserService.save(bean, user, JsonUtil.jsonArrayToList(role_ids, SysRole.class), compTreeSelect);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}

		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_SYS, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_SYS_USER, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ADD));
		}
		return result;
	}

	/**
	 * Description: update method
	 *
	 * @param bean
	 * @param role_select
	 * @param comp_select
	 * @return "success" or "error" or user defined
	 * @author
	 */
	@RequestMapping(value = "/sysusermodify.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doUpdate(HttpServletRequest request, SysUser bean, String role_ids, String compTreeSelect) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = sysUserService.update(bean, user, JsonUtil.jsonArrayToList(role_ids, SysRole.class),
					compTreeSelect);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}

		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_SYS, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_SYS_USER, OptBehaviorConstants.LOG_SEPARATOR,
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
	@RequestMapping(value = "/sysuserdelete.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doDelete(HttpServletRequest request, SysUser bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = sysUserService.delete(bean, user);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.USED;
		}

		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_SYS, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_SYS_USER, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_DELETE));
		}
		return result;
	}

	/**
	 * 五级数据权限tree
	 * 
	 * @param request
	 * @return
	 * @author: syp637
	 */
	@RequestMapping(value = "/getcompmenutree.do", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getMenuTreeBean(HttpServletRequest request) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return sysUserService.getMenuTreeBean(user);
	}

	/**
	 * 根据用户角色回显用户数据权限
	 * 
	 * @param request
	 * @param bean
	 * @return
	 * @author: syp637
	 */
	@RequestMapping(value = "/getsysuserdatapermissionbycurrentuseridandroleid.do", method = RequestMethod.GET)
	@ResponseBody
	public List<SysUserDataPermission> getSysUserDataPermissionByRoleId(HttpServletRequest request) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return sysUserService.getSysUserDataPermissionByRoleId(user.getUserId(), user.getCurrentRoleId());
	}

	/**
	 * 查询该用户的所有数据权限
	 * 
	 * @param request
	 * @param bean
	 * @return
	 * @author: syp637
	 */
	@RequestMapping(value = "/getsysuserdatapermission.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getSysUserDataPermission(HttpServletRequest request, SysUserDataPermission bean) {
		return sysUserService.getSysUserDataPermission(bean);
	}
}