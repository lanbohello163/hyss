package com.zxtech.ess.module.base.bean;

import com.zxtech.platform.utils.page.PageParameter;

public class BaseDeptSearchBean extends PageParameter{
	
	private static final long serialVersionUID = 1L;
	
	private Integer comp_id;
	
	private String dept_name;
	
	private Integer p_dept_id;
	
	private Integer dept_id;
	
	private String enable_flag;
	
	private String comp_name;
	
	public String getComp_name() {
		return comp_name;
	}

	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}

	public String getEnable_flag() {
		return enable_flag;
	}

	public void setEnable_flag(String enable_flag) {
		this.enable_flag = enable_flag;
	}

	public Integer getComp_id() {
		return comp_id;
	}

	public void setComp_id(Integer comp_id) {
		this.comp_id = comp_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public Integer getP_dept_id() {
		return p_dept_id;
	}

	public void setP_dept_id(Integer p_dept_id) {
		this.p_dept_id = p_dept_id;
	}

	public Integer getDept_id() {
		return dept_id;
	}

	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}

}
