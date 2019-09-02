package com.zxtech.ess.module.sys.bean;

import com.zxtech.platform.utils.page.PageParameter;

/*
 * @version 3.0
 */

public class SysRoleSearchBean extends PageParameter {

	private static final long serialVersionUID = 1L;
	
	private String user_id;
	
	private String role_name;
	
	private String sso_code;

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getSso_code() {
		return sso_code;
	}

	public void setSso_code(String sso_code) {
		this.sso_code = sso_code;
	}
}