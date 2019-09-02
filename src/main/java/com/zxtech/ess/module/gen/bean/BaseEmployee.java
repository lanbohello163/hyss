package com.zxtech.ess.module.gen.bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;



/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table base_employee
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class BaseEmployee implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.id
	 * @mbg.generated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.emp_code
	 * @mbg.generated
	 */
	private String emp_code;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.emp_name
	 * @mbg.generated
	 */
	private String emp_name;
	/**
	 * Database Column Remarks: 系统字典 emp_sex This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.emp_sex
	 * @mbg.generated
	 */
	private String emp_sex;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.id_number
	 * @mbg.generated
	 */
	private String id_number;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.born_date
	 * @mbg.generated
	 */
	private Date born_date;
	/**
	 * Database Column Remarks: 系统字典 emp_type This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.emp_type
	 * @mbg.generated
	 */
	private String emp_type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.emp_position
	 * @mbg.generated
	 */
	private String emp_position;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.position_sequence
	 * @mbg.generated
	 */
	private String position_sequence;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.pda_position
	 * @mbg.generated
	 */
	private String pda_position;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.employ_nature
	 * @mbg.generated
	 */
	private String employ_nature;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.emp_tel
	 * @mbg.generated
	 */
	private String emp_tel;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.emp_email
	 * @mbg.generated
	 */
	private String emp_email;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.hr_comp_code
	 * @mbg.generated
	 */
	private String hr_comp_code;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.hr_comp_name
	 * @mbg.generated
	 */
	private String hr_comp_name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.comp_id
	 * @mbg.generated
	 */
	private Integer comp_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.stat_id
	 * @mbg.generated
	 */
	private Integer stat_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.area_id
	 * @mbg.generated
	 */
	private Integer area_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.group_id
	 * @mbg.generated
	 */
	private Integer group_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.entry_date
	 * @mbg.generated
	 */
	private Date entry_date;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.departure_date
	 * @mbg.generated
	 */
	private Date departure_date;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.departure_reason
	 * @mbg.generated
	 */
	private String departure_reason;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.other_brand_skill
	 * @mbg.generated
	 */
	private String other_brand_skill;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.dispatch_send_first
	 * @mbg.generated
	 */
	private String dispatch_send_first;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.dispatch_send_second
	 * @mbg.generated
	 */
	private String dispatch_send_second;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.dispatch_send_third
	 * @mbg.generated
	 */
	private String dispatch_send_third;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.emp_remark
	 * @mbg.generated
	 */
	private String emp_remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.bi_work_type
	 * @mbg.generated
	 */
	private String bi_work_type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.bi_duty
	 * @mbg.generated
	 */
	private String bi_duty;
	/**
	 * Database Column Remarks: 系统字典 enable_flag This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.enable_flag
	 * @mbg.generated
	 */
	private String enable_flag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.create_user
	 * @mbg.generated
	 */
	private String create_user;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.create_timestamp
	 * @mbg.generated
	 */
	private Timestamp create_timestamp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.last_update_user
	 * @mbg.generated
	 */
	private String last_update_user;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.last_update_timestamp
	 * @mbg.generated
	 */
	private Timestamp last_update_timestamp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.last_update_remark
	 * @mbg.generated
	 */
	private String last_update_remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.sync_datetime
	 * @mbg.generated
	 */
	private Timestamp sync_datetime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.source_id
	 * @mbg.generated
	 */
	private String source_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.type_hotline
	 * @mbg.generated
	 */
	private String type_hotline;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.type_regular_check
	 * @mbg.generated
	 */
	private String type_regular_check;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.type_casual_check
	 * @mbg.generated
	 */
	private String type_casual_check;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.type_mt
	 * @mbg.generated
	 */
	private String type_mt;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.type_rota_engineer
	 * @mbg.generated
	 */
	private String type_rota_engineer;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.hotline_manager
	 * @mbg.generated
	 */
	private String hotline_manager;
	/**
	 * Database Column Remarks: 取自系统字典 This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.work_emp_level
	 * @mbg.generated
	 */
	private String work_emp_level;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_employee.position_description
	 * @mbg.generated
	 */
	private String position_description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table base_employee
	 * @mbg.generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.id
	 * @return  the value of base_employee.id
	 * @mbg.generated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.id
	 * @param id  the value for base_employee.id
	 * @mbg.generated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.emp_code
	 * @return  the value of base_employee.emp_code
	 * @mbg.generated
	 */
	public String getEmp_code() {
		return emp_code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.emp_code
	 * @param emp_code  the value for base_employee.emp_code
	 * @mbg.generated
	 */
	public void setEmp_code(String emp_code) {
		this.emp_code = emp_code == null ? null : emp_code.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.emp_name
	 * @return  the value of base_employee.emp_name
	 * @mbg.generated
	 */
	public String getEmp_name() {
		return emp_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.emp_name
	 * @param emp_name  the value for base_employee.emp_name
	 * @mbg.generated
	 */
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name == null ? null : emp_name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.emp_sex
	 * @return  the value of base_employee.emp_sex
	 * @mbg.generated
	 */
	public String getEmp_sex() {
		return emp_sex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.emp_sex
	 * @param emp_sex  the value for base_employee.emp_sex
	 * @mbg.generated
	 */
	public void setEmp_sex(String emp_sex) {
		this.emp_sex = emp_sex == null ? null : emp_sex.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.id_number
	 * @return  the value of base_employee.id_number
	 * @mbg.generated
	 */
	public String getId_number() {
		return id_number;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.id_number
	 * @param id_number  the value for base_employee.id_number
	 * @mbg.generated
	 */
	public void setId_number(String id_number) {
		this.id_number = id_number == null ? null : id_number.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.born_date
	 * @return  the value of base_employee.born_date
	 * @mbg.generated
	 */
	public Date getBorn_date() {
		return born_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.born_date
	 * @param born_date  the value for base_employee.born_date
	 * @mbg.generated
	 */
	public void setBorn_date(Date born_date) {
		this.born_date = born_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.emp_type
	 * @return  the value of base_employee.emp_type
	 * @mbg.generated
	 */
	public String getEmp_type() {
		return emp_type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.emp_type
	 * @param emp_type  the value for base_employee.emp_type
	 * @mbg.generated
	 */
	public void setEmp_type(String emp_type) {
		this.emp_type = emp_type == null ? null : emp_type.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.emp_position
	 * @return  the value of base_employee.emp_position
	 * @mbg.generated
	 */
	public String getEmp_position() {
		return emp_position;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.emp_position
	 * @param emp_position  the value for base_employee.emp_position
	 * @mbg.generated
	 */
	public void setEmp_position(String emp_position) {
		this.emp_position = emp_position == null ? null : emp_position.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.position_sequence
	 * @return  the value of base_employee.position_sequence
	 * @mbg.generated
	 */
	public String getPosition_sequence() {
		return position_sequence;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.position_sequence
	 * @param position_sequence  the value for base_employee.position_sequence
	 * @mbg.generated
	 */
	public void setPosition_sequence(String position_sequence) {
		this.position_sequence = position_sequence == null ? null : position_sequence.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.pda_position
	 * @return  the value of base_employee.pda_position
	 * @mbg.generated
	 */
	public String getPda_position() {
		return pda_position;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.pda_position
	 * @param pda_position  the value for base_employee.pda_position
	 * @mbg.generated
	 */
	public void setPda_position(String pda_position) {
		this.pda_position = pda_position == null ? null : pda_position.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.employ_nature
	 * @return  the value of base_employee.employ_nature
	 * @mbg.generated
	 */
	public String getEmploy_nature() {
		return employ_nature;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.employ_nature
	 * @param employ_nature  the value for base_employee.employ_nature
	 * @mbg.generated
	 */
	public void setEmploy_nature(String employ_nature) {
		this.employ_nature = employ_nature == null ? null : employ_nature.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.emp_tel
	 * @return  the value of base_employee.emp_tel
	 * @mbg.generated
	 */
	public String getEmp_tel() {
		return emp_tel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.emp_tel
	 * @param emp_tel  the value for base_employee.emp_tel
	 * @mbg.generated
	 */
	public void setEmp_tel(String emp_tel) {
		this.emp_tel = emp_tel == null ? null : emp_tel.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.emp_email
	 * @return  the value of base_employee.emp_email
	 * @mbg.generated
	 */
	public String getEmp_email() {
		return emp_email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.emp_email
	 * @param emp_email  the value for base_employee.emp_email
	 * @mbg.generated
	 */
	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email == null ? null : emp_email.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.hr_comp_code
	 * @return  the value of base_employee.hr_comp_code
	 * @mbg.generated
	 */
	public String getHr_comp_code() {
		return hr_comp_code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.hr_comp_code
	 * @param hr_comp_code  the value for base_employee.hr_comp_code
	 * @mbg.generated
	 */
	public void setHr_comp_code(String hr_comp_code) {
		this.hr_comp_code = hr_comp_code == null ? null : hr_comp_code.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.hr_comp_name
	 * @return  the value of base_employee.hr_comp_name
	 * @mbg.generated
	 */
	public String getHr_comp_name() {
		return hr_comp_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.hr_comp_name
	 * @param hr_comp_name  the value for base_employee.hr_comp_name
	 * @mbg.generated
	 */
	public void setHr_comp_name(String hr_comp_name) {
		this.hr_comp_name = hr_comp_name == null ? null : hr_comp_name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.comp_id
	 * @return  the value of base_employee.comp_id
	 * @mbg.generated
	 */
	public Integer getComp_id() {
		return comp_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.comp_id
	 * @param comp_id  the value for base_employee.comp_id
	 * @mbg.generated
	 */
	public void setComp_id(Integer comp_id) {
		this.comp_id = comp_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.stat_id
	 * @return  the value of base_employee.stat_id
	 * @mbg.generated
	 */
	public Integer getStat_id() {
		return stat_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.stat_id
	 * @param stat_id  the value for base_employee.stat_id
	 * @mbg.generated
	 */
	public void setStat_id(Integer stat_id) {
		this.stat_id = stat_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.area_id
	 * @return  the value of base_employee.area_id
	 * @mbg.generated
	 */
	public Integer getArea_id() {
		return area_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.area_id
	 * @param area_id  the value for base_employee.area_id
	 * @mbg.generated
	 */
	public void setArea_id(Integer area_id) {
		this.area_id = area_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.group_id
	 * @return  the value of base_employee.group_id
	 * @mbg.generated
	 */
	public Integer getGroup_id() {
		return group_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.group_id
	 * @param group_id  the value for base_employee.group_id
	 * @mbg.generated
	 */
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.entry_date
	 * @return  the value of base_employee.entry_date
	 * @mbg.generated
	 */
	public Date getEntry_date() {
		return entry_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.entry_date
	 * @param entry_date  the value for base_employee.entry_date
	 * @mbg.generated
	 */
	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.departure_date
	 * @return  the value of base_employee.departure_date
	 * @mbg.generated
	 */
	public Date getDeparture_date() {
		return departure_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.departure_date
	 * @param departure_date  the value for base_employee.departure_date
	 * @mbg.generated
	 */
	public void setDeparture_date(Date departure_date) {
		this.departure_date = departure_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.departure_reason
	 * @return  the value of base_employee.departure_reason
	 * @mbg.generated
	 */
	public String getDeparture_reason() {
		return departure_reason;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.departure_reason
	 * @param departure_reason  the value for base_employee.departure_reason
	 * @mbg.generated
	 */
	public void setDeparture_reason(String departure_reason) {
		this.departure_reason = departure_reason == null ? null : departure_reason.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.other_brand_skill
	 * @return  the value of base_employee.other_brand_skill
	 * @mbg.generated
	 */
	public String getOther_brand_skill() {
		return other_brand_skill;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.other_brand_skill
	 * @param other_brand_skill  the value for base_employee.other_brand_skill
	 * @mbg.generated
	 */
	public void setOther_brand_skill(String other_brand_skill) {
		this.other_brand_skill = other_brand_skill == null ? null : other_brand_skill.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.dispatch_send_first
	 * @return  the value of base_employee.dispatch_send_first
	 * @mbg.generated
	 */
	public String getDispatch_send_first() {
		return dispatch_send_first;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.dispatch_send_first
	 * @param dispatch_send_first  the value for base_employee.dispatch_send_first
	 * @mbg.generated
	 */
	public void setDispatch_send_first(String dispatch_send_first) {
		this.dispatch_send_first = dispatch_send_first == null ? null : dispatch_send_first.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.dispatch_send_second
	 * @return  the value of base_employee.dispatch_send_second
	 * @mbg.generated
	 */
	public String getDispatch_send_second() {
		return dispatch_send_second;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.dispatch_send_second
	 * @param dispatch_send_second  the value for base_employee.dispatch_send_second
	 * @mbg.generated
	 */
	public void setDispatch_send_second(String dispatch_send_second) {
		this.dispatch_send_second = dispatch_send_second == null ? null : dispatch_send_second.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.dispatch_send_third
	 * @return  the value of base_employee.dispatch_send_third
	 * @mbg.generated
	 */
	public String getDispatch_send_third() {
		return dispatch_send_third;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.dispatch_send_third
	 * @param dispatch_send_third  the value for base_employee.dispatch_send_third
	 * @mbg.generated
	 */
	public void setDispatch_send_third(String dispatch_send_third) {
		this.dispatch_send_third = dispatch_send_third == null ? null : dispatch_send_third.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.emp_remark
	 * @return  the value of base_employee.emp_remark
	 * @mbg.generated
	 */
	public String getEmp_remark() {
		return emp_remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.emp_remark
	 * @param emp_remark  the value for base_employee.emp_remark
	 * @mbg.generated
	 */
	public void setEmp_remark(String emp_remark) {
		this.emp_remark = emp_remark == null ? null : emp_remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.bi_work_type
	 * @return  the value of base_employee.bi_work_type
	 * @mbg.generated
	 */
	public String getBi_work_type() {
		return bi_work_type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.bi_work_type
	 * @param bi_work_type  the value for base_employee.bi_work_type
	 * @mbg.generated
	 */
	public void setBi_work_type(String bi_work_type) {
		this.bi_work_type = bi_work_type == null ? null : bi_work_type.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.bi_duty
	 * @return  the value of base_employee.bi_duty
	 * @mbg.generated
	 */
	public String getBi_duty() {
		return bi_duty;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.bi_duty
	 * @param bi_duty  the value for base_employee.bi_duty
	 * @mbg.generated
	 */
	public void setBi_duty(String bi_duty) {
		this.bi_duty = bi_duty == null ? null : bi_duty.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.enable_flag
	 * @return  the value of base_employee.enable_flag
	 * @mbg.generated
	 */
	public String getEnable_flag() {
		return enable_flag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.enable_flag
	 * @param enable_flag  the value for base_employee.enable_flag
	 * @mbg.generated
	 */
	public void setEnable_flag(String enable_flag) {
		this.enable_flag = enable_flag == null ? null : enable_flag.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.create_user
	 * @return  the value of base_employee.create_user
	 * @mbg.generated
	 */
	public String getCreate_user() {
		return create_user;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.create_user
	 * @param create_user  the value for base_employee.create_user
	 * @mbg.generated
	 */
	public void setCreate_user(String create_user) {
		this.create_user = create_user == null ? null : create_user.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.create_timestamp
	 * @return  the value of base_employee.create_timestamp
	 * @mbg.generated
	 */
	public Timestamp getCreate_timestamp() {
		return create_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.create_timestamp
	 * @param create_timestamp  the value for base_employee.create_timestamp
	 * @mbg.generated
	 */
	public void setCreate_timestamp(Timestamp create_timestamp) {
		this.create_timestamp = create_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.last_update_user
	 * @return  the value of base_employee.last_update_user
	 * @mbg.generated
	 */
	public String getLast_update_user() {
		return last_update_user;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.last_update_user
	 * @param last_update_user  the value for base_employee.last_update_user
	 * @mbg.generated
	 */
	public void setLast_update_user(String last_update_user) {
		this.last_update_user = last_update_user == null ? null : last_update_user.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.last_update_timestamp
	 * @return  the value of base_employee.last_update_timestamp
	 * @mbg.generated
	 */
	public Timestamp getLast_update_timestamp() {
		return last_update_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.last_update_timestamp
	 * @param last_update_timestamp  the value for base_employee.last_update_timestamp
	 * @mbg.generated
	 */
	public void setLast_update_timestamp(Timestamp last_update_timestamp) {
		this.last_update_timestamp = last_update_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.last_update_remark
	 * @return  the value of base_employee.last_update_remark
	 * @mbg.generated
	 */
	public String getLast_update_remark() {
		return last_update_remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.last_update_remark
	 * @param last_update_remark  the value for base_employee.last_update_remark
	 * @mbg.generated
	 */
	public void setLast_update_remark(String last_update_remark) {
		this.last_update_remark = last_update_remark == null ? null : last_update_remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.sync_datetime
	 * @return  the value of base_employee.sync_datetime
	 * @mbg.generated
	 */
	public Timestamp getSync_datetime() {
		return sync_datetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.sync_datetime
	 * @param sync_datetime  the value for base_employee.sync_datetime
	 * @mbg.generated
	 */
	public void setSync_datetime(Timestamp sync_datetime) {
		this.sync_datetime = sync_datetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.source_id
	 * @return  the value of base_employee.source_id
	 * @mbg.generated
	 */
	public String getSource_id() {
		return source_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.source_id
	 * @param source_id  the value for base_employee.source_id
	 * @mbg.generated
	 */
	public void setSource_id(String source_id) {
		this.source_id = source_id == null ? null : source_id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.type_hotline
	 * @return  the value of base_employee.type_hotline
	 * @mbg.generated
	 */
	public String getType_hotline() {
		return type_hotline;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.type_hotline
	 * @param type_hotline  the value for base_employee.type_hotline
	 * @mbg.generated
	 */
	public void setType_hotline(String type_hotline) {
		this.type_hotline = type_hotline == null ? null : type_hotline.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.type_regular_check
	 * @return  the value of base_employee.type_regular_check
	 * @mbg.generated
	 */
	public String getType_regular_check() {
		return type_regular_check;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.type_regular_check
	 * @param type_regular_check  the value for base_employee.type_regular_check
	 * @mbg.generated
	 */
	public void setType_regular_check(String type_regular_check) {
		this.type_regular_check = type_regular_check == null ? null : type_regular_check.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.type_casual_check
	 * @return  the value of base_employee.type_casual_check
	 * @mbg.generated
	 */
	public String getType_casual_check() {
		return type_casual_check;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.type_casual_check
	 * @param type_casual_check  the value for base_employee.type_casual_check
	 * @mbg.generated
	 */
	public void setType_casual_check(String type_casual_check) {
		this.type_casual_check = type_casual_check == null ? null : type_casual_check.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.type_mt
	 * @return  the value of base_employee.type_mt
	 * @mbg.generated
	 */
	public String getType_mt() {
		return type_mt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.type_mt
	 * @param type_mt  the value for base_employee.type_mt
	 * @mbg.generated
	 */
	public void setType_mt(String type_mt) {
		this.type_mt = type_mt == null ? null : type_mt.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.type_rota_engineer
	 * @return  the value of base_employee.type_rota_engineer
	 * @mbg.generated
	 */
	public String getType_rota_engineer() {
		return type_rota_engineer;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.type_rota_engineer
	 * @param type_rota_engineer  the value for base_employee.type_rota_engineer
	 * @mbg.generated
	 */
	public void setType_rota_engineer(String type_rota_engineer) {
		this.type_rota_engineer = type_rota_engineer == null ? null : type_rota_engineer.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.hotline_manager
	 * @return  the value of base_employee.hotline_manager
	 * @mbg.generated
	 */
	public String getHotline_manager() {
		return hotline_manager;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.hotline_manager
	 * @param hotline_manager  the value for base_employee.hotline_manager
	 * @mbg.generated
	 */
	public void setHotline_manager(String hotline_manager) {
		this.hotline_manager = hotline_manager == null ? null : hotline_manager.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.work_emp_level
	 * @return  the value of base_employee.work_emp_level
	 * @mbg.generated
	 */
	public String getWork_emp_level() {
		return work_emp_level;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.work_emp_level
	 * @param work_emp_level  the value for base_employee.work_emp_level
	 * @mbg.generated
	 */
	public void setWork_emp_level(String work_emp_level) {
		this.work_emp_level = work_emp_level == null ? null : work_emp_level.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_employee.position_description
	 * @return  the value of base_employee.position_description
	 * @mbg.generated
	 */
	public String getPosition_description() {
		return position_description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_employee.position_description
	 * @param position_description  the value for base_employee.position_description
	 * @mbg.generated
	 */
	public void setPosition_description(String position_description) {
		this.position_description = position_description == null ? null : position_description.trim();
	}
}