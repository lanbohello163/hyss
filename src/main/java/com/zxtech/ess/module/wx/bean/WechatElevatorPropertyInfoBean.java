package com.zxtech.ess.module.wx.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class WechatElevatorPropertyInfoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String elevator_code;
	
	private String cust_code;
	
	private String cust_name;
	
	private String area_name;
	
	private String first_level_comp_name;
	
	private String secondary_comp_name;
	
	private String create_user;
	
	private Timestamp create_timestamp;
	
	private String contract_code;
	
	private Timestamp syn_update_timestamp;

	public String getElevator_code() {
		return elevator_code;
	}

	public void setElevator_code(String elevator_code) {
		this.elevator_code = elevator_code;
	}

	public String getCust_code() {
		return cust_code;
	}

	public void setCust_code(String cust_code) {
		this.cust_code = cust_code;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getFirst_level_comp_name() {
		return first_level_comp_name;
	}

	public void setFirst_level_comp_name(String first_level_comp_name) {
		this.first_level_comp_name = first_level_comp_name;
	}

	public String getSecondary_comp_name() {
		return secondary_comp_name;
	}

	public void setSecondary_comp_name(String secondary_comp_name) {
		this.secondary_comp_name = secondary_comp_name;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public Timestamp getCreate_timestamp() {
		return create_timestamp;
	}

	public void setCreate_timestamp(Timestamp create_timestamp) {
		this.create_timestamp = create_timestamp;
	}

	public String getContract_code() {
		return contract_code;
	}

	public void setContract_code(String contract_code) {
		this.contract_code = contract_code;
	}

	public Timestamp getSyn_update_timestamp() {
		return syn_update_timestamp;
	}

	public void setSyn_update_timestamp(Timestamp syn_update_timestamp) {
		this.syn_update_timestamp = syn_update_timestamp;
	}

}
