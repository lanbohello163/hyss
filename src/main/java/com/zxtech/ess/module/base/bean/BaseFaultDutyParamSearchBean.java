package com.zxtech.ess.module.base.bean;

import com.zxtech.platform.utils.page.PageParameter;

public class BaseFaultDutyParamSearchBean extends PageParameter {

	private static final long serialVersionUID = 1L;
	
	private String bad_code;
	private String bad_category;
	
	public String getBad_code() {
		return bad_code;
	}
	public void setBad_code(String bad_code) {
		this.bad_code = bad_code;
	}
	public String getBad_category() {
		return bad_category;
	}
	public void setBad_category(String bad_category) {
		this.bad_category = bad_category;
	}
	
}