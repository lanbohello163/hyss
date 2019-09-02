package com.zxtech.ess.module.base.bean;

import java.util.List;

import com.zxtech.platform.utils.page.PageParameter;

public class BaseEmployeeSearchBean extends PageParameter {

	private static final long serialVersionUID = 1L;
	
	private String emp_name;
	private String emp_type;
	private String emp_code;
	private String comp_id;
	private String comp_name;
	private String stat_id;
	private String stat_name;
	private String pda_position;
	private String emp_position;
	private String bi_work_type;
	private String emp_tel;
	private String comp_or_region;
	private String type_hotline;
	private String type_regular_check;
	private String type_casual_check;
	private String type_mt;
	private String type_rota_engineer;
	private String enable_flag;
	private String selected_first_emp_code;
	private String selected_second_emp_code;
	private String selected_third_emp_code;
	private String area_id;
	
	private String login_user_comp_id;
	private String login_user_stat_id;
	private String login_user_area_id;
	
	private String contract_stat_id;
	private String contract_area_id;
	
	private List<Integer> compIdsList;
	
	private String select_emp_type;
	
	private String selected_contract_code;
	private String selected_asset_id;
	
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
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_type() {
		return emp_type;
	}
	public void setEmp_type(String emp_type) {
		this.emp_type = emp_type;
	}
	public String getEmp_code() {
		return emp_code;
	}
	public void setEmp_code(String emp_code) {
		this.emp_code = emp_code;
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
	public String getPda_position() {
		return pda_position;
	}
	public void setPda_position(String pda_position) {
		this.pda_position = pda_position;
	}
	public String getEmp_position() {
		return emp_position;
	}
	public void setEmp_position(String emp_position) {
		this.emp_position = emp_position;
	}
	public String getBi_work_type() {
		return bi_work_type;
	}
	public void setBi_work_type(String bi_work_type) {
		this.bi_work_type = bi_work_type;
	}
	public String getType_hotline() {
		return type_hotline;
	}
	public void setType_hotline(String type_hotline) {
		this.type_hotline = type_hotline;
	}
	public String getType_regular_check() {
		return type_regular_check;
	}
	public void setType_regular_check(String type_regular_check) {
		this.type_regular_check = type_regular_check;
	}
	public String getType_casual_check() {
		return type_casual_check;
	}
	public void setType_casual_check(String type_casual_check) {
		this.type_casual_check = type_casual_check;
	}
	public String getType_mt() {
		return type_mt;
	}
	public void setType_mt(String type_mt) {
		this.type_mt = type_mt;
	}
	public String getType_rota_engineer() {
		return type_rota_engineer;
	}
	public void setType_rota_engineer(String type_rota_engineer) {
		this.type_rota_engineer = type_rota_engineer;
	}
	public String getEmp_tel() {
		return emp_tel;
	}
	public void setEmp_tel(String emp_tel) {
		this.emp_tel = emp_tel;
	}
	public String getEnable_flag() {
		return enable_flag;
	}
	public void setEnable_flag(String enable_flag) {
		this.enable_flag = enable_flag;
	}
	public String getComp_or_region() {
		return comp_or_region;
	}
	public void setComp_or_region(String comp_or_region) {
		this.comp_or_region = comp_or_region;
	}
	public String getSelected_first_emp_code() {
		return selected_first_emp_code;
	}
	public void setSelected_first_emp_code(String selected_first_emp_code) {
		this.selected_first_emp_code = selected_first_emp_code;
	}
	public String getSelected_second_emp_code() {
		return selected_second_emp_code;
	}
	public void setSelected_second_emp_code(String selected_second_emp_code) {
		this.selected_second_emp_code = selected_second_emp_code;
	}
	public String getSelected_third_emp_code() {
		return selected_third_emp_code;
	}
	public void setSelected_third_emp_code(String selected_third_emp_code) {
		this.selected_third_emp_code = selected_third_emp_code;
	}
	public String getArea_id() {
		return area_id;
	}
	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}
	public String getLogin_user_comp_id() {
		return login_user_comp_id;
	}
	public void setLogin_user_comp_id(String login_user_comp_id) {
		this.login_user_comp_id = login_user_comp_id;
	}
	public String getLogin_user_stat_id() {
		return login_user_stat_id;
	}
	public void setLogin_user_stat_id(String login_user_stat_id) {
		this.login_user_stat_id = login_user_stat_id;
	}
	public String getLogin_user_area_id() {
		return login_user_area_id;
	}
	public void setLogin_user_area_id(String login_user_area_id) {
		this.login_user_area_id = login_user_area_id;
	}
	public String getContract_stat_id() {
		return contract_stat_id;
	}
	public void setContract_stat_id(String contract_stat_id) {
		this.contract_stat_id = contract_stat_id;
	}
	public String getContract_area_id() {
		return contract_area_id;
	}
	public void setContract_area_id(String contract_area_id) {
		this.contract_area_id = contract_area_id;
	}
	public List<Integer> getCompIdsList() {
		return compIdsList;
	}
	public void setCompIdsList(List<Integer> compIdsList) {
		this.compIdsList = compIdsList;
	}
	public String getSelect_emp_type() {
		return select_emp_type;
	}
	public void setSelect_emp_type(String select_emp_type) {
		this.select_emp_type = select_emp_type;
	}
	public String getSelected_contract_code() {
		return selected_contract_code;
	}
	public void setSelected_contract_code(String selected_contract_code) {
		this.selected_contract_code = selected_contract_code;
	}
	public String getSelected_asset_id() {
		return selected_asset_id;
	}
	public void setSelected_asset_id(String selected_asset_id) {
		this.selected_asset_id = selected_asset_id;
	}
	
}