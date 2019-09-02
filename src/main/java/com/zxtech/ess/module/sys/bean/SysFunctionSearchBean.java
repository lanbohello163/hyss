package com.zxtech.ess.module.sys.bean;

import com.zxtech.platform.utils.page.PageParameter;

/*
 * @version 3.0
 */

public class SysFunctionSearchBean extends PageParameter {

	private static final long serialVersionUID = 1L;
	private String role_id;
	private String user_id;
	private boolean isSa;
	private String menu_type;
	
	public String getMenu_type() {
		return menu_type;
	}
	public void setMenu_type(String menu_type) {
		this.menu_type = menu_type;
	}
	public boolean isSa() {
		return isSa;
	}
	public void setSa(boolean isSa) {
		this.isSa = isSa;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

}