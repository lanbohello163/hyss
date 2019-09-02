package com.zxtech.ess.module.base.bean;

import com.zxtech.platform.utils.page.PageParameter;

/*
 * @version 3.0
 */

public class BaseDictionarySearchBean extends PageParameter {

	private static final long serialVersionUID = 1L;
	
	private String dict_code;
	
	private String dict_type;
	
	private String combobox_type;
	
	private String p_dict_type;
	
	private String p_dict_code;
	
	private String dict_additional_value;
	
	private String dict_name;
	
	private String dict_type_name;
	
	
	

	public String getDict_type() {
		return dict_type;
	}
	public void setDict_type(String dict_type) {
		this.dict_type = dict_type;
	}
	public String getCombobox_type() {
		return combobox_type;
	}
	public void setCombobox_type(String combobox_type) {
		this.combobox_type = combobox_type;
	}
	public String getDict_name() {
		return dict_name;
	}
	public void setDict_name(String dict_name) {
		this.dict_name = dict_name;
	}
	public String getDict_type_name() {
		return dict_type_name;
	}
	public void setDict_type_name(String dict_type_name) {
		this.dict_type_name = dict_type_name;
	}
	public String getP_dict_type() {
		return p_dict_type;
	}
	public void setP_dict_type(String p_dict_type) {
		this.p_dict_type = p_dict_type;
	}
	public String getP_dict_code() {
		return p_dict_code;
	}
	public void setP_dict_code(String p_dict_code) {
		this.p_dict_code = p_dict_code;
	}
	public String getDict_additional_value() {
		return dict_additional_value;
	}
	public void setDict_additional_value(String dict_additional_value) {
		this.dict_additional_value = dict_additional_value;
	}
	public String getDict_code() {
		return dict_code;
	}
	public void setDict_code(String dict_code) {
		this.dict_code = dict_code;
	}
}