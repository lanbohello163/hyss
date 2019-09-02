package com.zxtech.ess.module.base.bean;

import com.zxtech.platform.utils.page.PageParameter;

public class BaseGroupSearchBean extends PageParameter{
	
	private static final long serialVersionUID = 1L;
	
	private String group_name;
	
	private Integer comp_id;
	
	private String comp_name;
	
	private Integer stat_id;
	
	private String stat_name;
	
	private Integer area_id;
	
	private String area_name;
	
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

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
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

	public Integer getArea_id() {
		return area_id;
	}

	public void setArea_id(Integer area_id) {
		this.area_id = area_id;
	}

}
