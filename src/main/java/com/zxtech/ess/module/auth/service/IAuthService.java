package com.zxtech.ess.module.auth.service;

import java.util.List;
import java.util.Map;

import com.zxtech.platform.vo.UserBean;

public interface IAuthService {

	String checkAndGetUser(UserBean user, Boolean ssoLogin);

	List<Map<String, Object>> getUserMenu(UserBean user);

	String changeUserPasswd(UserBean user, String oldPasswd);
	
	void changeRole(UserBean user, Integer role_id);
	
	Map<String, String> autoLoginCheckSsoCode(UserBean user);
}