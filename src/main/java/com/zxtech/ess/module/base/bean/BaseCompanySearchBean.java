package com.zxtech.ess.module.base.bean;

import com.zxtech.platform.utils.page.PageParameter;

public class BaseCompanySearchBean extends PageParameter{
	
	private static final long serialVersionUID = 1L;
	
	private Integer comp_id;
	private String comp_type;
	private Integer p_comp_id;
	
	private String sync_comp_type;
	private String sync_comp_code;
	private String sync_comp_name;
	private String sync_entity_code;
	
	private String comp_name;
	
	public Integer getComp_id() {
		return comp_id;
	}
	public void setComp_id(Integer comp_id) {
		this.comp_id = comp_id;
	}
	public String getComp_type() {
		return comp_type;
	}
	public void setComp_type(String comp_type) {
		this.comp_type = comp_type;
	}
	
	public Integer getP_comp_id() {
		return p_comp_id;
	}
	public void setP_comp_id(Integer p_comp_id) {
		this.p_comp_id = p_comp_id;
	}
	public String getSync_comp_type() {
		return sync_comp_type;
	}
	public void setSync_comp_type(String sync_comp_type) {
		this.sync_comp_type = sync_comp_type;
	}
	public String getSync_comp_code() {
		return sync_comp_code;
	}
	public void setSync_comp_code(String sync_comp_code) {
		this.sync_comp_code = sync_comp_code;
	}
	public String getSync_comp_name() {
		return sync_comp_name;
	}
	public void setSync_comp_name(String sync_comp_name) {
		this.sync_comp_name = sync_comp_name;
	}
	public String getSync_entity_code() {
		return sync_entity_code;
	}
	public void setSync_entity_code(String sync_entity_code) {
		this.sync_entity_code = sync_entity_code;
	}
	public String getComp_name() {
		return comp_name;
	}
	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}

}
