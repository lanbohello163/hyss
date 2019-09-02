package com.zxtech.platform.auth;

import javax.servlet.ServletRequest;

import com.zxtech.platform.vo.UserBean;

public class CheckToken {

	public static UserBean getUserBean(ServletRequest request) throws Exception {
		String token = request.getParameter("_token");
		// 验证JWT的签名，返回CheckResult对象
		CheckResult checkResult = JWTUtils.validateJWT(token);
		if (checkResult.isSuccess()) {
			// 记录异步日志
			UserBean userBean = JWTUtils.getUser(token);
			if( userBean == null ) {
				throw new Exception();
			}else {
				return userBean;
			}
		} else {
			throw new Exception();
		}
	}
}
