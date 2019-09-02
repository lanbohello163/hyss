package com.zxtech.ess.module.base.bean;

import com.zxtech.platform.utils.page.PageParameter;

/*
 * @version 3.0
 */
public class BasePropertySearchBean extends PageParameter {

	private static final long serialVersionUID = 1L;
	
	private Integer property_id;
	private String property_name;
	private String property_address;
	private Integer comp_id;
	private String comp_name;
	
	public String getProperty_name() {
		return property_name;
	}
	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}
	public String getProperty_address() {
		return property_address;
	}
	public void setProperty_address(String property_address) {
		this.property_address = property_address;
	}
	public Integer getProperty_id() {
		return property_id;
	}
	public void setProperty_id(Integer property_id) {
		this.property_id = property_id;
	}
	public Integer getComp_id() {
		return comp_id;
	}
	public void setComp_id(Integer comp_id) {
		this.comp_id = comp_id;
	}
	public String getComp_name() {
		return comp_name;
	}
	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}
	
	
}