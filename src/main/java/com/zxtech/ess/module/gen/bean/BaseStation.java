package com.zxtech.ess.module.gen.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table base_station
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class BaseStation implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.id
	 * @mbg.generated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.comp_id
	 * @mbg.generated
	 */
	private Integer comp_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.dept_id
	 * @mbg.generated
	 */
	private Integer dept_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.stat_name
	 * @mbg.generated
	 */
	private String stat_name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.stat_manager
	 * @mbg.generated
	 */
	private String stat_manager;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.stat_type
	 * @mbg.generated
	 */
	private String stat_type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.mgr_area
	 * @mbg.generated
	 */
	private String mgr_area;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.stat_address
	 * @mbg.generated
	 */
	private String stat_address;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.stat_state
	 * @mbg.generated
	 */
	private String stat_state;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.stat_province
	 * @mbg.generated
	 */
	private String stat_province;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.stat_city
	 * @mbg.generated
	 */
	private String stat_city;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.stat_county
	 * @mbg.generated
	 */
	private String stat_county;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.licensed_contractor_name
	 * @mbg.generated
	 */
	private String licensed_contractor_name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.office_tel
	 * @mbg.generated
	 */
	private String office_tel;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.office_fax
	 * @mbg.generated
	 */
	private String office_fax;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.office_zip_code
	 * @mbg.generated
	 */
	private String office_zip_code;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.practices
	 * @mbg.generated
	 */
	private String practices;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.visit_director
	 * @mbg.generated
	 */
	private String visit_director;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.is_use_pda
	 * @mbg.generated
	 */
	private String is_use_pda;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.enable_flag
	 * @mbg.generated
	 */
	private String enable_flag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.create_user
	 * @mbg.generated
	 */
	private String create_user;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.create_timestamp
	 * @mbg.generated
	 */
	private Timestamp create_timestamp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.last_update_user
	 * @mbg.generated
	 */
	private String last_update_user;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.last_update_timestamp
	 * @mbg.generated
	 */
	private Timestamp last_update_timestamp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.last_update_remark
	 * @mbg.generated
	 */
	private String last_update_remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.sync_datetime
	 * @mbg.generated
	 */
	private Timestamp sync_datetime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.source_id
	 * @mbg.generated
	 */
	private String source_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.erp_stat_id
	 * @mbg.generated
	 */
	private Integer erp_stat_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.region_state_name
	 * @mbg.generated
	 */
	private String region_state_name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.region_province_name
	 * @mbg.generated
	 */
	private String region_province_name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.region_city_name
	 * @mbg.generated
	 */
	private String region_city_name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_station.region_district_name
	 * @mbg.generated
	 */
	private String region_district_name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table base_station
	 * @mbg.generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.id
	 * @return  the value of base_station.id
	 * @mbg.generated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.id
	 * @param id  the value for base_station.id
	 * @mbg.generated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.comp_id
	 * @return  the value of base_station.comp_id
	 * @mbg.generated
	 */
	public Integer getComp_id() {
		return comp_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.comp_id
	 * @param comp_id  the value for base_station.comp_id
	 * @mbg.generated
	 */
	public void setComp_id(Integer comp_id) {
		this.comp_id = comp_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.dept_id
	 * @return  the value of base_station.dept_id
	 * @mbg.generated
	 */
	public Integer getDept_id() {
		return dept_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.dept_id
	 * @param dept_id  the value for base_station.dept_id
	 * @mbg.generated
	 */
	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.stat_name
	 * @return  the value of base_station.stat_name
	 * @mbg.generated
	 */
	public String getStat_name() {
		return stat_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.stat_name
	 * @param stat_name  the value for base_station.stat_name
	 * @mbg.generated
	 */
	public void setStat_name(String stat_name) {
		this.stat_name = stat_name == null ? null : stat_name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.stat_manager
	 * @return  the value of base_station.stat_manager
	 * @mbg.generated
	 */
	public String getStat_manager() {
		return stat_manager;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.stat_manager
	 * @param stat_manager  the value for base_station.stat_manager
	 * @mbg.generated
	 */
	public void setStat_manager(String stat_manager) {
		this.stat_manager = stat_manager == null ? null : stat_manager.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.stat_type
	 * @return  the value of base_station.stat_type
	 * @mbg.generated
	 */
	public String getStat_type() {
		return stat_type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.stat_type
	 * @param stat_type  the value for base_station.stat_type
	 * @mbg.generated
	 */
	public void setStat_type(String stat_type) {
		this.stat_type = stat_type == null ? null : stat_type.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.mgr_area
	 * @return  the value of base_station.mgr_area
	 * @mbg.generated
	 */
	public String getMgr_area() {
		return mgr_area;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.mgr_area
	 * @param mgr_area  the value for base_station.mgr_area
	 * @mbg.generated
	 */
	public void setMgr_area(String mgr_area) {
		this.mgr_area = mgr_area == null ? null : mgr_area.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.stat_address
	 * @return  the value of base_station.stat_address
	 * @mbg.generated
	 */
	public String getStat_address() {
		return stat_address;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.stat_address
	 * @param stat_address  the value for base_station.stat_address
	 * @mbg.generated
	 */
	public void setStat_address(String stat_address) {
		this.stat_address = stat_address == null ? null : stat_address.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.stat_state
	 * @return  the value of base_station.stat_state
	 * @mbg.generated
	 */
	public String getStat_state() {
		return stat_state;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.stat_state
	 * @param stat_state  the value for base_station.stat_state
	 * @mbg.generated
	 */
	public void setStat_state(String stat_state) {
		this.stat_state = stat_state == null ? null : stat_state.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.stat_province
	 * @return  the value of base_station.stat_province
	 * @mbg.generated
	 */
	public String getStat_province() {
		return stat_province;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.stat_province
	 * @param stat_province  the value for base_station.stat_province
	 * @mbg.generated
	 */
	public void setStat_province(String stat_province) {
		this.stat_province = stat_province == null ? null : stat_province.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.stat_city
	 * @return  the value of base_station.stat_city
	 * @mbg.generated
	 */
	public String getStat_city() {
		return stat_city;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.stat_city
	 * @param stat_city  the value for base_station.stat_city
	 * @mbg.generated
	 */
	public void setStat_city(String stat_city) {
		this.stat_city = stat_city == null ? null : stat_city.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.stat_county
	 * @return  the value of base_station.stat_county
	 * @mbg.generated
	 */
	public String getStat_county() {
		return stat_county;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.stat_county
	 * @param stat_county  the value for base_station.stat_county
	 * @mbg.generated
	 */
	public void setStat_county(String stat_county) {
		this.stat_county = stat_county == null ? null : stat_county.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.licensed_contractor_name
	 * @return  the value of base_station.licensed_contractor_name
	 * @mbg.generated
	 */
	public String getLicensed_contractor_name() {
		return licensed_contractor_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.licensed_contractor_name
	 * @param licensed_contractor_name  the value for base_station.licensed_contractor_name
	 * @mbg.generated
	 */
	public void setLicensed_contractor_name(String licensed_contractor_name) {
		this.licensed_contractor_name = licensed_contractor_name == null ? null : licensed_contractor_name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.office_tel
	 * @return  the value of base_station.office_tel
	 * @mbg.generated
	 */
	public String getOffice_tel() {
		return office_tel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.office_tel
	 * @param office_tel  the value for base_station.office_tel
	 * @mbg.generated
	 */
	public void setOffice_tel(String office_tel) {
		this.office_tel = office_tel == null ? null : office_tel.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.office_fax
	 * @return  the value of base_station.office_fax
	 * @mbg.generated
	 */
	public String getOffice_fax() {
		return office_fax;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.office_fax
	 * @param office_fax  the value for base_station.office_fax
	 * @mbg.generated
	 */
	public void setOffice_fax(String office_fax) {
		this.office_fax = office_fax == null ? null : office_fax.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.office_zip_code
	 * @return  the value of base_station.office_zip_code
	 * @mbg.generated
	 */
	public String getOffice_zip_code() {
		return office_zip_code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.office_zip_code
	 * @param office_zip_code  the value for base_station.office_zip_code
	 * @mbg.generated
	 */
	public void setOffice_zip_code(String office_zip_code) {
		this.office_zip_code = office_zip_code == null ? null : office_zip_code.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.practices
	 * @return  the value of base_station.practices
	 * @mbg.generated
	 */
	public String getPractices() {
		return practices;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.practices
	 * @param practices  the value for base_station.practices
	 * @mbg.generated
	 */
	public void setPractices(String practices) {
		this.practices = practices == null ? null : practices.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.visit_director
	 * @return  the value of base_station.visit_director
	 * @mbg.generated
	 */
	public String getVisit_director() {
		return visit_director;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.visit_director
	 * @param visit_director  the value for base_station.visit_director
	 * @mbg.generated
	 */
	public void setVisit_director(String visit_director) {
		this.visit_director = visit_director == null ? null : visit_director.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.is_use_pda
	 * @return  the value of base_station.is_use_pda
	 * @mbg.generated
	 */
	public String getIs_use_pda() {
		return is_use_pda;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.is_use_pda
	 * @param is_use_pda  the value for base_station.is_use_pda
	 * @mbg.generated
	 */
	public void setIs_use_pda(String is_use_pda) {
		this.is_use_pda = is_use_pda == null ? null : is_use_pda.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.enable_flag
	 * @return  the value of base_station.enable_flag
	 * @mbg.generated
	 */
	public String getEnable_flag() {
		return enable_flag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.enable_flag
	 * @param enable_flag  the value for base_station.enable_flag
	 * @mbg.generated
	 */
	public void setEnable_flag(String enable_flag) {
		this.enable_flag = enable_flag == null ? null : enable_flag.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.create_user
	 * @return  the value of base_station.create_user
	 * @mbg.generated
	 */
	public String getCreate_user() {
		return create_user;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.create_user
	 * @param create_user  the value for base_station.create_user
	 * @mbg.generated
	 */
	public void setCreate_user(String create_user) {
		this.create_user = create_user == null ? null : create_user.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.create_timestamp
	 * @return  the value of base_station.create_timestamp
	 * @mbg.generated
	 */
	public Timestamp getCreate_timestamp() {
		return create_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.create_timestamp
	 * @param create_timestamp  the value for base_station.create_timestamp
	 * @mbg.generated
	 */
	public void setCreate_timestamp(Timestamp create_timestamp) {
		this.create_timestamp = create_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.last_update_user
	 * @return  the value of base_station.last_update_user
	 * @mbg.generated
	 */
	public String getLast_update_user() {
		return last_update_user;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.last_update_user
	 * @param last_update_user  the value for base_station.last_update_user
	 * @mbg.generated
	 */
	public void setLast_update_user(String last_update_user) {
		this.last_update_user = last_update_user == null ? null : last_update_user.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.last_update_timestamp
	 * @return  the value of base_station.last_update_timestamp
	 * @mbg.generated
	 */
	public Timestamp getLast_update_timestamp() {
		return last_update_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.last_update_timestamp
	 * @param last_update_timestamp  the value for base_station.last_update_timestamp
	 * @mbg.generated
	 */
	public void setLast_update_timestamp(Timestamp last_update_timestamp) {
		this.last_update_timestamp = last_update_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.last_update_remark
	 * @return  the value of base_station.last_update_remark
	 * @mbg.generated
	 */
	public String getLast_update_remark() {
		return last_update_remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.last_update_remark
	 * @param last_update_remark  the value for base_station.last_update_remark
	 * @mbg.generated
	 */
	public void setLast_update_remark(String last_update_remark) {
		this.last_update_remark = last_update_remark == null ? null : last_update_remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.sync_datetime
	 * @return  the value of base_station.sync_datetime
	 * @mbg.generated
	 */
	public Timestamp getSync_datetime() {
		return sync_datetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.sync_datetime
	 * @param sync_datetime  the value for base_station.sync_datetime
	 * @mbg.generated
	 */
	public void setSync_datetime(Timestamp sync_datetime) {
		this.sync_datetime = sync_datetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.source_id
	 * @return  the value of base_station.source_id
	 * @mbg.generated
	 */
	public String getSource_id() {
		return source_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.source_id
	 * @param source_id  the value for base_station.source_id
	 * @mbg.generated
	 */
	public void setSource_id(String source_id) {
		this.source_id = source_id == null ? null : source_id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.erp_stat_id
	 * @return  the value of base_station.erp_stat_id
	 * @mbg.generated
	 */
	public Integer getErp_stat_id() {
		return erp_stat_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.erp_stat_id
	 * @param erp_stat_id  the value for base_station.erp_stat_id
	 * @mbg.generated
	 */
	public void setErp_stat_id(Integer erp_stat_id) {
		this.erp_stat_id = erp_stat_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.region_state_name
	 * @return  the value of base_station.region_state_name
	 * @mbg.generated
	 */
	public String getRegion_state_name() {
		return region_state_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.region_state_name
	 * @param region_state_name  the value for base_station.region_state_name
	 * @mbg.generated
	 */
	public void setRegion_state_name(String region_state_name) {
		this.region_state_name = region_state_name == null ? null : region_state_name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.region_province_name
	 * @return  the value of base_station.region_province_name
	 * @mbg.generated
	 */
	public String getRegion_province_name() {
		return region_province_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.region_province_name
	 * @param region_province_name  the value for base_station.region_province_name
	 * @mbg.generated
	 */
	public void setRegion_province_name(String region_province_name) {
		this.region_province_name = region_province_name == null ? null : region_province_name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.region_city_name
	 * @return  the value of base_station.region_city_name
	 * @mbg.generated
	 */
	public String getRegion_city_name() {
		return region_city_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.region_city_name
	 * @param region_city_name  the value for base_station.region_city_name
	 * @mbg.generated
	 */
	public void setRegion_city_name(String region_city_name) {
		this.region_city_name = region_city_name == null ? null : region_city_name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_station.region_district_name
	 * @return  the value of base_station.region_district_name
	 * @mbg.generated
	 */
	public String getRegion_district_name() {
		return region_district_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_station.region_district_name
	 * @param region_district_name  the value for base_station.region_district_name
	 * @mbg.generated
	 */
	public void setRegion_district_name(String region_district_name) {
		this.region_district_name = region_district_name == null ? null : region_district_name.trim();
	}
}