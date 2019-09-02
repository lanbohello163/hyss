package com.zxtech.ess.module.auth.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxtech.ess.app.util.OptBehaviorConstants;
import com.zxtech.ess.app.util.SysTools;
import com.zxtech.ess.module.auth.service.IAuthService;
import com.zxtech.platform.auth.AuthConstants;
import com.zxtech.platform.auth.CookieUtils;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.utils.StringUtil;
import com.zxtech.platform.vo.UserBean;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private IAuthService authService;
	
	/**
	 * @api {post} /auth/login.auth 登录
	 * @apiVersion 1.0.0
	 * @apiGroup auth
	 * 
	 * @apiDescription API to login the elevator service system for back-stage management.
	 * 
	 * @apiParam {String} userCode The user's code.
	 * @apiParam {String} userPasswd The user's password.
	 * @apiParam {Integer} tenantCode The user's tenant code.
	 * @apiParam {String} language The user's special language.
	 * @apiParamExample {json} Request-Example:
	 * {
	 * "userCode": "example_code"
	 * "userPasswd": "example_pwd"
	 * "tenantCode": 1
	 * "language": "zh_CN"
	 * }
	 * 
	 * @apiSuccess {String} data Authority result.
	 * @apiSuccessExample {String} Success-Response:
	 * "auth_ok" or "auth_error" or "auth_limited" or "(String) auth limited number"
	 */
	
	/**
	 * login
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/login.auth", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response, UserBean user) {
		int count = getAuthErrorCount(request, user.getUserCode());
		if (count >= AuthConstants.AUTH_LIMITED_NUM) {
			return AuthConstants.AUTH_LIMITED;
		}

		String result = authService.checkAndGetUser(user, false);
		if (AuthConstants.AUTH_SUCCESS.equals(result)) {
			CookieUtils.removeCookie(response, CookieUtils.AUTH_ERR_USER_PREFIX + user.getUserCode());
			SysTools.saveLog(user, OptBehaviorConstants.LOG_LOGIN);
			result = JWTUtils.token(user);
		} else if (AuthConstants.AUTH_NOT_USER.equals(result)) {
			return result;
		} else if (AuthConstants.AUTH_NOT_EMP.equals(result)) {
			return result;
		} else {
			count++;
			CookieUtils.setCookie(response, CookieUtils.AUTH_ERR_USER_PREFIX + user.getUserCode(), String.valueOf(count), 300);
			return Strings.EMPTY + (count);
		}
		return result;
	}
	
	@RequestMapping(value = "/changerole.do", method = RequestMethod.POST)
	@ResponseBody
	public String changeRole(HttpServletRequest request, HttpServletResponse response, Integer role_id) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		authService.changeRole(user, role_id);
		
		JWTUtils.updateUserBean(request, user);
		return result;
	}

	/**
	 * auth error count
	 * 
	 * @param request
	 * @param user_code
	 * @return
	 */
	private int getAuthErrorCount(HttpServletRequest request, String user_code) {
		String count = CookieUtils.getCookie(request, CookieUtils.AUTH_ERR_USER_PREFIX + user_code);
		return StringUtils.isNotBlank(count) ? Integer.valueOf(count) : 0;
	}
	
	/**
	 * @api {post} /auth/logout.do 注销
	 * @apiVersion 1.0.0
	 * @apiGroup auth
	 * @apiPermission login user
	 * 
	 * @apiDescription API to logout the elevator service system for back-stage management.
	 * 
	 * @apiSuccess {String} data Logout result.
	 * @apiSuccessExample {String} Success-Response:
	 * "ok"
	 */

	/**
	 * logout
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/logout.do", method = RequestMethod.POST)
	@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		JWTUtils.delUserInfo(request);
		CookieUtils.removeCookie(response, CookieUtils.AUTH_ERR_USER_PREFIX + user.getUserCode());
		SysTools.saveLog(user, OptBehaviorConstants.LOG_LOGOUT);
		return ResultConstants.SUCCESS;
	}

	/**
	 * get current user info
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getcurrentuserinfo.do", method = RequestMethod.GET)
	@ResponseBody
	public UserBean getCurrentUserInfo(HttpServletRequest request) {
		return (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
	}

	/**
	 * get user menu
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getusermenu.do", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getUserMenu(HttpServletRequest request) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return authService.getUserMenu(user);
	}

	/**
	 * authority function example: 1#0#1，1 yes，0 no
	 * 
	 * @param urlStr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gettoolbar.do", method = RequestMethod.POST)
	@ResponseBody
	public String getToolbar(String urlStr, HttpServletRequest request) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		StringBuilder sb = new StringBuilder();
		String[] urlArr = urlStr.split("#");
		for (String url : urlArr) {
			if (StringUtil.isNotBlank(url)) {
				if (user.hasFuncName(url)) {
					sb.append("1");
				} else {
					sb.append("0");
				}
				sb.append("#");
			}
		}
		String resStr = sb.toString();
		if (resStr.length() > 0) {
			resStr = resStr.substring(0, resStr.length() - 1);
		}
		return resStr;
	}

	/**
	 * change user password
	 * 
	 * @param user
	 * @param oldPasswd
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/changeuserpasswd.auth", method = RequestMethod.POST)
	@ResponseBody
	public String changeUserPasswd(UserBean user, String oldPasswd, HttpServletRequest request) {
		String result = ResultConstants.SUCCESS;
		try {
			result = authService.changeUserPasswd(user, oldPasswd);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		return result;
	}
	
	@RequestMapping(value = "/ssologin.auth", method = RequestMethod.POST)
	@ResponseBody
	public String ssoLogin(HttpServletRequest request, HttpServletResponse response, UserBean user) {
		String result = authService.checkAndGetUser(user, true);
		if (AuthConstants.AUTH_SUCCESS.equals(result)) {
			CookieUtils.removeCookie(response, CookieUtils.AUTH_ERR_USER_PREFIX + user.getUserCode());
			SysTools.saveLog(user, OptBehaviorConstants.LOG_LOGIN);
			result = JWTUtils.token(user);
		} else if (AuthConstants.AUTH_NOT_USER.equals(result)) {
			return result;
		} else if (AuthConstants.AUTH_NOT_EMP.equals(result)) {
			return result;
		} 
		return result;
	}
	
	@RequestMapping(value = "/autoLogin.auth", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> autoLogin(HttpServletRequest request, HttpServletResponse response, UserBean user) {
		return authService.autoLoginCheckSsoCode(user);
	}
}