package com.zxtech.ess.app.util.wrap;

import com.zxtech.platform.vo.UserBean;

public class ComboboxDefined {
	
	private String id;
	private String text;
	private String combobox_type;
	private UserBean user;
	
	public ComboboxDefined(UserBean user) {
		this.user = user;
	}
	
	public ComboboxDefined(String id, String text, String combobox_type, UserBean user) {
		this.id = id;
		this.text = text;
		this.combobox_type = combobox_type;
		this.user = user;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCombobox_type() {
		return combobox_type;
	}
	public void setCombobox_type(String combobox_type) {
		this.combobox_type = combobox_type;
	}
	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}
	
}
