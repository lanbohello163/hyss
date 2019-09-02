package com.zxtech.ess.module.base.bean;

import com.zxtech.platform.utils.page.PageParameter;

public class BaseProjectSearchBean extends PageParameter {

	private static final long serialVersionUID = 1L;
	
	private String proj_name;
	
	private String proj_code;
	
	private String proj_address;
	
	private Integer comp_id;
	
	private String comp_name;
	
	private Integer stat_id;
	
	private String stat_name;
	
	public String getComp_name() {
		return comp_name;
	}

	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}

	public String getStat_name() {
		return stat_name;
	}

	public void setStat_name(String stat_name) {
		this.stat_name = stat_name;
	}

	public String getProj_name() {
		return proj_name;
	}

	public void setProj_name(String proj_name) {
		this.proj_name = proj_name;
	}

	public String getProj_code() {
		return proj_code;
	}

	public void setProj_code(String proj_code) {
		this.proj_code = proj_code;
	}

	public String getProj_address() {
		return proj_address;
	}

	public void setProj_address(String proj_address) {
		this.proj_address = proj_address;
	}

	public Integer getComp_id() {
		return comp_id;
	}

	public void setComp_id(Integer comp_id) {
		this.comp_id = comp_id;
	}

	public Integer getStat_id() {
		return stat_id;
	}

	public void setStat_id(Integer stat_id) {
		this.stat_id = stat_id;
	}

}
