package com.zxtech.ess.module.sys.bean;

import com.zxtech.platform.utils.page.PageParameter;

public class SysUserSearchBean extends PageParameter {

	private static final long serialVersionUID = 1L;

	private String user_realname;
	
	private String comp_name;
	
	private String sso_code;
	
	public String getComp_name() {
		return comp_name;
	}

	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}

	public String getUser_realname() {
		return user_realname;
	}

	public void setUser_realname(String user_realname) {
		this.user_realname = user_realname;
	}

	public String getSso_code() {
		return sso_code;
	}

	public void setSso_code(String sso_code) {
		this.sso_code = sso_code;
	}
}