package com.zxtech.ess.module.sys.bean;

import com.zxtech.platform.utils.page.PageParameter;

/*
 * @version 3.0
 */

public class SysDictionarySearchBean extends PageParameter {

	private static final long serialVersionUID = 1L;
	
	private String dict_value;
	
	private String dict_type;
	
	private String p_dict_type;
	
	private String p_dict_value;
	
	private String combobox_type;

	public String getDict_value() {
		return dict_value;
	}

	public void setDict_value(String dict_value) {
		this.dict_value = dict_value;
	}

	public String getDict_type() {
		return dict_type;
	}

	public void setDict_type(String dict_type) {
		this.dict_type = dict_type;
	}

	public String getP_dict_type() {
		return p_dict_type;
	}

	public void setP_dict_type(String p_dict_type) {
		this.p_dict_type = p_dict_type;
	}

	public String getP_dict_value() {
		return p_dict_value;
	}

	public void setP_dict_value(String p_dict_value) {
		this.p_dict_value = p_dict_value;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCombobox_type() {
		return combobox_type;
	}

	public void setCombobox_type(String combobox_type) {
		this.combobox_type = combobox_type;
	}
}