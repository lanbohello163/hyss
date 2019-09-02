package com.zxtech.ess.module.base.bean;

import java.util.List;

import com.zxtech.platform.utils.page.PageParameter;

/*
 * @version 3.0
 */
public class BaseElevatorSearchBean extends PageParameter {

	private static final long serialVersionUID = 1L;
	
	private String service_order;
	private String asset_num;
	private String comp_id;
	private String comp_name;
	private String stat_id;
	private String stat_name;
	private String key_words;
	private String ele_category;
	private String ele_category_name;
	private String ele_no;
	private String enable_flag;
	private Integer asset_id;
	private String contract_code;
	private String service_num;
	private String project_name;
	private String key_property_name;
	
	private String ele_model;
	private String ele_floor;
	private String ele_stop;
	
	private String selected_ele_category_id;
	
	private List<Integer> compIdsList;
	
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
	public String getKey_property_name() {
		return key_property_name;
	}
	public void setKey_property_name(String key_property_name) {
		this.key_property_name = key_property_name;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
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
	public String getStat_id() {
		return stat_id;
	}
	public void setStat_id(String stat_id) {
		this.stat_id = stat_id;
	}
	public String getService_order() {
		return service_order;
	}
	public void setService_order(String service_order) {
		this.service_order = service_order;
	}
	public String getKey_words() {
		return key_words;
	}
	public void setKey_words(String key_words) {
		this.key_words = key_words;
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
	public String getContract_code() {
		return contract_code;
	}
	public void setContract_code(String contract_code) {
		this.contract_code = contract_code;
	}
	public String getService_num() {
		return service_num;
	}
	public void setService_num(String service_num) {
		this.service_num = service_num;
	}
	public String getEle_model() {
		return ele_model;
	}
	public void setEle_model(String ele_model) {
		this.ele_model = ele_model;
	}
	public String getEle_floor() {
		return ele_floor;
	}
	public void setEle_floor(String ele_floor) {
		this.ele_floor = ele_floor;
	}
	public String getEle_stop() {
		return ele_stop;
	}
	public void setEle_stop(String ele_stop) {
		this.ele_stop = ele_stop;
	}
	public String getSelected_ele_category_id() {
		return selected_ele_category_id;
	}
	public void setSelected_ele_category_id(String selected_ele_category_id) {
		this.selected_ele_category_id = selected_ele_category_id;
	}
	public String getEle_category() {
		return ele_category;
	}
	public void setEle_category(String ele_category) {
		this.ele_category = ele_category;
	}
	public List<Integer> getCompIdsList() {
		return compIdsList;
	}
	public void setCompIdsList(List<Integer> compIdsList) {
		this.compIdsList = compIdsList;
	}
	
}