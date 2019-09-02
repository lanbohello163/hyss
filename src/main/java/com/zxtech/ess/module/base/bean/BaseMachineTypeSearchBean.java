package com.zxtech.ess.module.base.bean;

import com.zxtech.platform.utils.page.PageParameter;

/*
 * @version 3.0
 */

public class BaseMachineTypeSearchBean extends PageParameter {

	private static final long serialVersionUID = 1L;
	
	private String ele_category;
	
	private String machine_type;
	
	private String type_code;
	
	private String type_desc;
	
	private String p_type_id;
	
	private String combobox_type;
	
	private String pTypeSign;
	
	private String enable_flag;
	
	
	
	public String getEnable_flag() {
		return enable_flag;
	}

	public void setEnable_flag(String enable_flag) {
		this.enable_flag = enable_flag;
	}

	public String getpTypeSign() {
		return pTypeSign;
	}

	public void setpTypeSign(String pTypeSign) {
		this.pTypeSign = pTypeSign;
	}

	public String getCombobox_type() {
		return combobox_type;
	}

	public void setCombobox_type(String combobox_type) {
		this.combobox_type = combobox_type;
	}

	public String getEle_category() {
		return ele_category;
	}

	public void setEle_category(String ele_category) {
		this.ele_category = ele_category;
	}

	public String getMachine_type() {
		return machine_type;
	}

	public void setMachine_type(String machine_type) {
		this.machine_type = machine_type;
	}

	public String getType_code() {
		return type_code;
	}

	public void setType_code(String type_code) {
		this.type_code = type_code;
	}

	public String getType_desc() {
		return type_desc;
	}

	public void setType_desc(String type_desc) {
		this.type_desc = type_desc;
	}

	public String getP_type_id() {
		return p_type_id;
	}

	public void setP_type_id(String p_type_id) {
		this.p_type_id = p_type_id;
	}
	
	
}