package com.zxtech.ess.module.base.bean;

import com.zxtech.platform.utils.page.PageParameter;

public class BaseStationSearchBean extends PageParameter{
	
	private static final long serialVersionUID = 1L;
	
	private String stat_name;
	
	private String comp_id;
	
	private String comp_name;
	
	private String stat_type;
	
	private String emp_type;
	
	private String erp_stat_name;
	
	

	public String getErp_stat_name() {
		return erp_stat_name;
	}

	public void setErp_stat_name(String erp_stat_name) {
		this.erp_stat_name = erp_stat_name;
	}

	public String getStat_name() {
		return stat_name;
	}

	public void setStat_name(String stat_name) {
		this.stat_name = stat_name;
	}

	public String getComp_id() {
		return comp_id;
	}

	public void setComp_id(String comp_id) {
		this.comp_id = comp_id;
	}
	
	public String getComp_name() {
		return comp_name;
	}

	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}

	public String getStat_type() {
		return stat_type;
	}

	public void setStat_type(String stat_type) {
		this.stat_type = stat_type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEmp_type() {
		return emp_type;
	}

	public void setEmp_type(String emp_type) {
		this.emp_type = emp_type;
	}
	
	

	
}
