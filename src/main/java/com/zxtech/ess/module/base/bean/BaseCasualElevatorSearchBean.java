package com.zxtech.ess.module.base.bean;

import com.zxtech.platform.utils.page.PageParameter;

public class BaseCasualElevatorSearchBean extends PageParameter  {
	
	private static final long serialVersionUID = 1L;
	private String asset_num;
	private String comp_id;
	private String comp_name;
	private String stat_id;
	private String stat_name;
	private String ele_category;
	private String ele_category_name;
	private String ele_no;
	private String enable_flag;
	private Integer asset_id;
	private String casual_check_category;
	public String getAsset_num() {
		return asset_num;
	}
	public void setAsset_num(String asset_num) {
		this.asset_num = asset_num;
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
	public String getStat_id() {
		return stat_id;
	}
	public void setStat_id(String stat_id) {
		this.stat_id = stat_id;
	}
	public String getStat_name() {
		return stat_name;
	}
	public void setStat_name(String stat_name) {
		this.stat_name = stat_name;
	}
	public String getEle_category() {
		return ele_category;
	}
	public void setEle_category(String ele_category) {
		this.ele_category = ele_category;
	}
	public String getEle_category_name() {
		return ele_category_name;
	}
	public void setEle_category_name(String ele_category_name) {
		this.ele_category_name = ele_category_name;
	}
	public String getEle_no() {
		return ele_no;
	}
	public void setEle_no(String ele_no) {
		this.ele_no = ele_no;
	}
	public String getEnable_flag() {
		return enable_flag;
	}
	public void setEnable_flag(String enable_flag) {
		this.enable_flag = enable_flag;
	}
	public Integer getAsset_id() {
		return asset_id;
	}
	public void setAsset_id(Integer asset_id) {
		this.asset_id = asset_id;
	}
	public String getCasual_check_category() {
		return casual_check_category;
	}
	public void setCasual_check_category(String casual_check_category) {
		this.casual_check_category = casual_check_category;
	}
	
	
}
