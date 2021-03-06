package com.zxtech.ess.module.gen.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table base_dept
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class BaseDept implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.id
	 * @mbg.generated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.erp_dept_name
	 * @mbg.generated
	 */
	private String erp_dept_name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.hr_dept_name
	 * @mbg.generated
	 */
	private String hr_dept_name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.dept_name
	 * @mbg.generated
	 */
	private String dept_name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.dept_type
	 * @mbg.generated
	 */
	private String dept_type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.dept_manager
	 * @mbg.generated
	 */
	private String dept_manager;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.comp_id
	 * @mbg.generated
	 */
	private Integer comp_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.p_dept_id
	 * @mbg.generated
	 */
	private Integer p_dept_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.dept_state
	 * @mbg.generated
	 */
	private String dept_state;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.dept_province
	 * @mbg.generated
	 */
	private String dept_province;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.dept_city
	 * @mbg.generated
	 */
	private String dept_city;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.dept_district
	 * @mbg.generated
	 */
	private String dept_district;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.dept_tel
	 * @mbg.generated
	 */
	private String dept_tel;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.dept_fax
	 * @mbg.generated
	 */
	private String dept_fax;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.is_use_pda
	 * @mbg.generated
	 */
	private String is_use_pda;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.dept_sub
	 * @mbg.generated
	 */
	private String dept_sub;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.dept_remark
	 * @mbg.generated
	 */
	private String dept_remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.enable_flag
	 * @mbg.generated
	 */
	private String enable_flag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.create_user
	 * @mbg.generated
	 */
	private String create_user;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.create_timestamp
	 * @mbg.generated
	 */
	private Timestamp create_timestamp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.last_update_user
	 * @mbg.generated
	 */
	private String last_update_user;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.last_update_timestamp
	 * @mbg.generated
	 */
	private Timestamp last_update_timestamp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.last_update_remark
	 * @mbg.generated
	 */
	private String last_update_remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.sync_datetime
	 * @mbg.generated
	 */
	private Timestamp sync_datetime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.source_id
	 * @mbg.generated
	 */
	private String source_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.region_state_name
	 * @mbg.generated
	 */
	private String region_state_name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.region_province_name
	 * @mbg.generated
	 */
	private String region_province_name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.region_city_name
	 * @mbg.generated
	 */
	private String region_city_name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_dept.region_district_name
	 * @mbg.generated
	 */
	private String region_district_name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table base_dept
	 * @mbg.generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.id
	 * @return  the value of base_dept.id
	 * @mbg.generated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.id
	 * @param id  the value for base_dept.id
	 * @mbg.generated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.erp_dept_name
	 * @return  the value of base_dept.erp_dept_name
	 * @mbg.generated
	 */
	public String getErp_dept_name() {
		return erp_dept_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.erp_dept_name
	 * @param erp_dept_name  the value for base_dept.erp_dept_name
	 * @mbg.generated
	 */
	public void setErp_dept_name(String erp_dept_name) {
		this.erp_dept_name = erp_dept_name == null ? null : erp_dept_name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.hr_dept_name
	 * @return  the value of base_dept.hr_dept_name
	 * @mbg.generated
	 */
	public String getHr_dept_name() {
		return hr_dept_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.hr_dept_name
	 * @param hr_dept_name  the value for base_dept.hr_dept_name
	 * @mbg.generated
	 */
	public void setHr_dept_name(String hr_dept_name) {
		this.hr_dept_name = hr_dept_name == null ? null : hr_dept_name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.dept_name
	 * @return  the value of base_dept.dept_name
	 * @mbg.generated
	 */
	public String getDept_name() {
		return dept_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.dept_name
	 * @param dept_name  the value for base_dept.dept_name
	 * @mbg.generated
	 */
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name == null ? null : dept_name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.dept_type
	 * @return  the value of base_dept.dept_type
	 * @mbg.generated
	 */
	public String getDept_type() {
		return dept_type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.dept_type
	 * @param dept_type  the value for base_dept.dept_type
	 * @mbg.generated
	 */
	public void setDept_type(String dept_type) {
		this.dept_type = dept_type == null ? null : dept_type.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.dept_manager
	 * @return  the value of base_dept.dept_manager
	 * @mbg.generated
	 */
	public String getDept_manager() {
		return dept_manager;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.dept_manager
	 * @param dept_manager  the value for base_dept.dept_manager
	 * @mbg.generated
	 */
	public void setDept_manager(String dept_manager) {
		this.dept_manager = dept_manager == null ? null : dept_manager.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.comp_id
	 * @return  the value of base_dept.comp_id
	 * @mbg.generated
	 */
	public Integer getComp_id() {
		return comp_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.comp_id
	 * @param comp_id  the value for base_dept.comp_id
	 * @mbg.generated
	 */
	public void setComp_id(Integer comp_id) {
		this.comp_id = comp_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.p_dept_id
	 * @return  the value of base_dept.p_dept_id
	 * @mbg.generated
	 */
	public Integer getP_dept_id() {
		return p_dept_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.p_dept_id
	 * @param p_dept_id  the value for base_dept.p_dept_id
	 * @mbg.generated
	 */
	public void setP_dept_id(Integer p_dept_id) {
		this.p_dept_id = p_dept_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.dept_state
	 * @return  the value of base_dept.dept_state
	 * @mbg.generated
	 */
	public String getDept_state() {
		return dept_state;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.dept_state
	 * @param dept_state  the value for base_dept.dept_state
	 * @mbg.generated
	 */
	public void setDept_state(String dept_state) {
		this.dept_state = dept_state == null ? null : dept_state.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.dept_province
	 * @return  the value of base_dept.dept_province
	 * @mbg.generated
	 */
	public String getDept_province() {
		return dept_province;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.dept_province
	 * @param dept_province  the value for base_dept.dept_province
	 * @mbg.generated
	 */
	public void setDept_province(String dept_province) {
		this.dept_province = dept_province == null ? null : dept_province.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.dept_city
	 * @return  the value of base_dept.dept_city
	 * @mbg.generated
	 */
	public String getDept_city() {
		return dept_city;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.dept_city
	 * @param dept_city  the value for base_dept.dept_city
	 * @mbg.generated
	 */
	public void setDept_city(String dept_city) {
		this.dept_city = dept_city == null ? null : dept_city.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.dept_district
	 * @return  the value of base_dept.dept_district
	 * @mbg.generated
	 */
	public String getDept_district() {
		return dept_district;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.dept_district
	 * @param dept_district  the value for base_dept.dept_district
	 * @mbg.generated
	 */
	public void setDept_district(String dept_district) {
		this.dept_district = dept_district == null ? null : dept_district.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.dept_tel
	 * @return  the value of base_dept.dept_tel
	 * @mbg.generated
	 */
	public String getDept_tel() {
		return dept_tel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.dept_tel
	 * @param dept_tel  the value for base_dept.dept_tel
	 * @mbg.generated
	 */
	public void setDept_tel(String dept_tel) {
		this.dept_tel = dept_tel == null ? null : dept_tel.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.dept_fax
	 * @return  the value of base_dept.dept_fax
	 * @mbg.generated
	 */
	public String getDept_fax() {
		return dept_fax;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.dept_fax
	 * @param dept_fax  the value for base_dept.dept_fax
	 * @mbg.generated
	 */
	public void setDept_fax(String dept_fax) {
		this.dept_fax = dept_fax == null ? null : dept_fax.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.is_use_pda
	 * @return  the value of base_dept.is_use_pda
	 * @mbg.generated
	 */
	public String getIs_use_pda() {
		return is_use_pda;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.is_use_pda
	 * @param is_use_pda  the value for base_dept.is_use_pda
	 * @mbg.generated
	 */
	public void setIs_use_pda(String is_use_pda) {
		this.is_use_pda = is_use_pda == null ? null : is_use_pda.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.dept_sub
	 * @return  the value of base_dept.dept_sub
	 * @mbg.generated
	 */
	public String getDept_sub() {
		return dept_sub;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.dept_sub
	 * @param dept_sub  the value for base_dept.dept_sub
	 * @mbg.generated
	 */
	public void setDept_sub(String dept_sub) {
		this.dept_sub = dept_sub == null ? null : dept_sub.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.dept_remark
	 * @return  the value of base_dept.dept_remark
	 * @mbg.generated
	 */
	public String getDept_remark() {
		return dept_remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.dept_remark
	 * @param dept_remark  the value for base_dept.dept_remark
	 * @mbg.generated
	 */
	public void setDept_remark(String dept_remark) {
		this.dept_remark = dept_remark == null ? null : dept_remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.enable_flag
	 * @return  the value of base_dept.enable_flag
	 * @mbg.generated
	 */
	public String getEnable_flag() {
		return enable_flag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.enable_flag
	 * @param enable_flag  the value for base_dept.enable_flag
	 * @mbg.generated
	 */
	public void setEnable_flag(String enable_flag) {
		this.enable_flag = enable_flag == null ? null : enable_flag.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.create_user
	 * @return  the value of base_dept.create_user
	 * @mbg.generated
	 */
	public String getCreate_user() {
		return create_user;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.create_user
	 * @param create_user  the value for base_dept.create_user
	 * @mbg.generated
	 */
	public void setCreate_user(String create_user) {
		this.create_user = create_user == null ? null : create_user.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.create_timestamp
	 * @return  the value of base_dept.create_timestamp
	 * @mbg.generated
	 */
	public Timestamp getCreate_timestamp() {
		return create_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.create_timestamp
	 * @param create_timestamp  the value for base_dept.create_timestamp
	 * @mbg.generated
	 */
	public void setCreate_timestamp(Timestamp create_timestamp) {
		this.create_timestamp = create_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.last_update_user
	 * @return  the value of base_dept.last_update_user
	 * @mbg.generated
	 */
	public String getLast_update_user() {
		return last_update_user;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.last_update_user
	 * @param last_update_user  the value for base_dept.last_update_user
	 * @mbg.generated
	 */
	public void setLast_update_user(String last_update_user) {
		this.last_update_user = last_update_user == null ? null : last_update_user.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.last_update_timestamp
	 * @return  the value of base_dept.last_update_timestamp
	 * @mbg.generated
	 */
	public Timestamp getLast_update_timestamp() {
		return last_update_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.last_update_timestamp
	 * @param last_update_timestamp  the value for base_dept.last_update_timestamp
	 * @mbg.generated
	 */
	public void setLast_update_timestamp(Timestamp last_update_timestamp) {
		this.last_update_timestamp = last_update_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.last_update_remark
	 * @return  the value of base_dept.last_update_remark
	 * @mbg.generated
	 */
	public String getLast_update_remark() {
		return last_update_remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.last_update_remark
	 * @param last_update_remark  the value for base_dept.last_update_remark
	 * @mbg.generated
	 */
	public void setLast_update_remark(String last_update_remark) {
		this.last_update_remark = last_update_remark == null ? null : last_update_remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.sync_datetime
	 * @return  the value of base_dept.sync_datetime
	 * @mbg.generated
	 */
	public Timestamp getSync_datetime() {
		return sync_datetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.sync_datetime
	 * @param sync_datetime  the value for base_dept.sync_datetime
	 * @mbg.generated
	 */
	public void setSync_datetime(Timestamp sync_datetime) {
		this.sync_datetime = sync_datetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.source_id
	 * @return  the value of base_dept.source_id
	 * @mbg.generated
	 */
	public String getSource_id() {
		return source_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.source_id
	 * @param source_id  the value for base_dept.source_id
	 * @mbg.generated
	 */
	public void setSource_id(String source_id) {
		this.source_id = source_id == null ? null : source_id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.region_state_name
	 * @return  the value of base_dept.region_state_name
	 * @mbg.generated
	 */
	public String getRegion_state_name() {
		return region_state_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.region_state_name
	 * @param region_state_name  the value for base_dept.region_state_name
	 * @mbg.generated
	 */
	public void setRegion_state_name(String region_state_name) {
		this.region_state_name = region_state_name == null ? null : region_state_name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.region_province_name
	 * @return  the value of base_dept.region_province_name
	 * @mbg.generated
	 */
	public String getRegion_province_name() {
		return region_province_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.region_province_name
	 * @param region_province_name  the value for base_dept.region_province_name
	 * @mbg.generated
	 */
	public void setRegion_province_name(String region_province_name) {
		this.region_province_name = region_province_name == null ? null : region_province_name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.region_city_name
	 * @return  the value of base_dept.region_city_name
	 * @mbg.generated
	 */
	public String getRegion_city_name() {
		return region_city_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.region_city_name
	 * @param region_city_name  the value for base_dept.region_city_name
	 * @mbg.generated
	 */
	public void setRegion_city_name(String region_city_name) {
		this.region_city_name = region_city_name == null ? null : region_city_name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_dept.region_district_name
	 * @return  the value of base_dept.region_district_name
	 * @mbg.generated
	 */
	public String getRegion_district_name() {
		return region_district_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_dept.region_district_name
	 * @param region_district_name  the value for base_dept.region_district_name
	 * @mbg.generated
	 */
	public void setRegion_district_name(String region_district_name) {
		this.region_district_name = region_district_name == null ? null : region_district_name.trim();
	}
}