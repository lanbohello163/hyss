package com.zxtech.ess.module.sys.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxtech.ess.module.sys.bean.SysUserPromptSearchBean;
import com.zxtech.ess.module.sys.service.ISysUserPromptService;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.vo.UserBean;

@Controller
@RequestMapping("/sys")
public class SysUserPromptController {

	private final static Logger LOGGER = LoggerFactory.getLogger(SysUserPromptController.class);

	@Autowired
	private ISysUserPromptService sysUserPromptService;

	/**
	 * Description: get records by queryInfo with paging
	 *
	 * @param queryInfo
	 * @return records
	 * @author
	 */
	@RequestMapping(value = "/sysuserpromptlist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getListWithPaging(HttpServletRequest request, SysUserPromptSearchBean queryInfo) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		queryInfo.setDataPermissionList(user);
		queryInfo.setEmp_code(user.getEmpCode());
		queryInfo.setUser_id(user.getUserId());
		queryInfo.setRole_id(user.getCurrentRoleId());
		return sysUserPromptService.getListWithPaging(user, queryInfo);
	}
	
	@RequestMapping(value = "/sysuserpromptupdate.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String doUpdate(HttpServletRequest request, SysUserPromptSearchBean queryInfo) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		queryInfo.setDataPermissionList(user);
		queryInfo.setEmp_code(user.getEmpCode());
		queryInfo.setUser_id(user.getUserId());
		return sysUserPromptService.update(user, queryInfo);
	}
}