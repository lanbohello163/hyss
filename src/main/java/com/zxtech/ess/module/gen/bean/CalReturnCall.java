package com.zxtech.ess.module.gen.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table cal_return_call
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class CalReturnCall implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.id
	 * @mbg.generated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.service_order
	 * @mbg.generated
	 */
	private String service_order;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.return_code
	 * @mbg.generated
	 */
	private String return_code;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.return_status
	 * @mbg.generated
	 */
	private String return_status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.return_finish_datetime
	 * @mbg.generated
	 */
	private Timestamp return_finish_datetime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.return_desc
	 * @mbg.generated
	 */
	private String return_desc;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.is_return_finish
	 * @mbg.generated
	 */
	private String is_return_finish;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.finish_remark
	 * @mbg.generated
	 */
	private String finish_remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.unreasonable_demands
	 * @mbg.generated
	 */
	private String unreasonable_demands;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.unreasonable_remark
	 * @mbg.generated
	 */
	private String unreasonable_remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.fault_score
	 * @mbg.generated
	 */
	private String fault_score;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.fault_remark
	 * @mbg.generated
	 */
	private String fault_remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.attitude_score
	 * @mbg.generated
	 */
	private String attitude_score;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.attitude_remark
	 * @mbg.generated
	 */
	private String attitude_remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.timeliness_score
	 * @mbg.generated
	 */
	private String timeliness_score;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.timeliness_remark
	 * @mbg.generated
	 */
	private String timeliness_remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.mt_score
	 * @mbg.generated
	 */
	private String mt_score;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.mt_remark
	 * @mbg.generated
	 */
	private String mt_remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.spare_score
	 * @mbg.generated
	 */
	private String spare_score;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.spare_remark
	 * @mbg.generated
	 */
	private String spare_remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.cust_return
	 * @mbg.generated
	 */
	private String cust_return;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.comp_return
	 * @mbg.generated
	 */
	private String comp_return;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.comp_return_person
	 * @mbg.generated
	 */
	private String comp_return_person;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.comp_return_datetime
	 * @mbg.generated
	 */
	private Timestamp comp_return_datetime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.enable_flag
	 * @mbg.generated
	 */
	private String enable_flag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.create_user
	 * @mbg.generated
	 */
	private String create_user;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.create_timestamp
	 * @mbg.generated
	 */
	private Timestamp create_timestamp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.last_update_user
	 * @mbg.generated
	 */
	private String last_update_user;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.last_update_timestamp
	 * @mbg.generated
	 */
	private Timestamp last_update_timestamp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.last_update_remark
	 * @mbg.generated
	 */
	private String last_update_remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.dispatch_code
	 * @mbg.generated
	 */
	private String dispatch_code;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.comp_return_person_tel
	 * @mbg.generated
	 */
	private String comp_return_person_tel;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_return_call.return_complate_datetime
	 * @mbg.generated
	 */
	private Timestamp return_complate_datetime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table cal_return_call
	 * @mbg.generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.id
	 * @return  the value of cal_return_call.id
	 * @mbg.generated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.id
	 * @param id  the value for cal_return_call.id
	 * @mbg.generated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.service_order
	 * @return  the value of cal_return_call.service_order
	 * @mbg.generated
	 */
	public String getService_order() {
		return service_order;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.service_order
	 * @param service_order  the value for cal_return_call.service_order
	 * @mbg.generated
	 */
	public void setService_order(String service_order) {
		this.service_order = service_order == null ? null : service_order.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.return_code
	 * @return  the value of cal_return_call.return_code
	 * @mbg.generated
	 */
	public String getReturn_code() {
		return return_code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.return_code
	 * @param return_code  the value for cal_return_call.return_code
	 * @mbg.generated
	 */
	public void setReturn_code(String return_code) {
		this.return_code = return_code == null ? null : return_code.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.return_status
	 * @return  the value of cal_return_call.return_status
	 * @mbg.generated
	 */
	public String getReturn_status() {
		return return_status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.return_status
	 * @param return_status  the value for cal_return_call.return_status
	 * @mbg.generated
	 */
	public void setReturn_status(String return_status) {
		this.return_status = return_status == null ? null : return_status.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.return_finish_datetime
	 * @return  the value of cal_return_call.return_finish_datetime
	 * @mbg.generated
	 */
	public Timestamp getReturn_finish_datetime() {
		return return_finish_datetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.return_finish_datetime
	 * @param return_finish_datetime  the value for cal_return_call.return_finish_datetime
	 * @mbg.generated
	 */
	public void setReturn_finish_datetime(Timestamp return_finish_datetime) {
		this.return_finish_datetime = return_finish_datetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.return_desc
	 * @return  the value of cal_return_call.return_desc
	 * @mbg.generated
	 */
	public String getReturn_desc() {
		return return_desc;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.return_desc
	 * @param return_desc  the value for cal_return_call.return_desc
	 * @mbg.generated
	 */
	public void setReturn_desc(String return_desc) {
		this.return_desc = return_desc == null ? null : return_desc.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.is_return_finish
	 * @return  the value of cal_return_call.is_return_finish
	 * @mbg.generated
	 */
	public String getIs_return_finish() {
		return is_return_finish;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.is_return_finish
	 * @param is_return_finish  the value for cal_return_call.is_return_finish
	 * @mbg.generated
	 */
	public void setIs_return_finish(String is_return_finish) {
		this.is_return_finish = is_return_finish == null ? null : is_return_finish.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.finish_remark
	 * @return  the value of cal_return_call.finish_remark
	 * @mbg.generated
	 */
	public String getFinish_remark() {
		return finish_remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.finish_remark
	 * @param finish_remark  the value for cal_return_call.finish_remark
	 * @mbg.generated
	 */
	public void setFinish_remark(String finish_remark) {
		this.finish_remark = finish_remark == null ? null : finish_remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.unreasonable_demands
	 * @return  the value of cal_return_call.unreasonable_demands
	 * @mbg.generated
	 */
	public String getUnreasonable_demands() {
		return unreasonable_demands;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.unreasonable_demands
	 * @param unreasonable_demands  the value for cal_return_call.unreasonable_demands
	 * @mbg.generated
	 */
	public void setUnreasonable_demands(String unreasonable_demands) {
		this.unreasonable_demands = unreasonable_demands == null ? null : unreasonable_demands.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.unreasonable_remark
	 * @return  the value of cal_return_call.unreasonable_remark
	 * @mbg.generated
	 */
	public String getUnreasonable_remark() {
		return unreasonable_remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.unreasonable_remark
	 * @param unreasonable_remark  the value for cal_return_call.unreasonable_remark
	 * @mbg.generated
	 */
	public void setUnreasonable_remark(String unreasonable_remark) {
		this.unreasonable_remark = unreasonable_remark == null ? null : unreasonable_remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.fault_score
	 * @return  the value of cal_return_call.fault_score
	 * @mbg.generated
	 */
	public String getFault_score() {
		return fault_score;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.fault_score
	 * @param fault_score  the value for cal_return_call.fault_score
	 * @mbg.generated
	 */
	public void setFault_score(String fault_score) {
		this.fault_score = fault_score == null ? null : fault_score.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.fault_remark
	 * @return  the value of cal_return_call.fault_remark
	 * @mbg.generated
	 */
	public String getFault_remark() {
		return fault_remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.fault_remark
	 * @param fault_remark  the value for cal_return_call.fault_remark
	 * @mbg.generated
	 */
	public void setFault_remark(String fault_remark) {
		this.fault_remark = fault_remark == null ? null : fault_remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.attitude_score
	 * @return  the value of cal_return_call.attitude_score
	 * @mbg.generated
	 */
	public String getAttitude_score() {
		return attitude_score;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.attitude_score
	 * @param attitude_score  the value for cal_return_call.attitude_score
	 * @mbg.generated
	 */
	public void setAttitude_score(String attitude_score) {
		this.attitude_score = attitude_score == null ? null : attitude_score.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.attitude_remark
	 * @return  the value of cal_return_call.attitude_remark
	 * @mbg.generated
	 */
	public String getAttitude_remark() {
		return attitude_remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.attitude_remark
	 * @param attitude_remark  the value for cal_return_call.attitude_remark
	 * @mbg.generated
	 */
	public void setAttitude_remark(String attitude_remark) {
		this.attitude_remark = attitude_remark == null ? null : attitude_remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.timeliness_score
	 * @return  the value of cal_return_call.timeliness_score
	 * @mbg.generated
	 */
	public String getTimeliness_score() {
		return timeliness_score;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.timeliness_score
	 * @param timeliness_score  the value for cal_return_call.timeliness_score
	 * @mbg.generated
	 */
	public void setTimeliness_score(String timeliness_score) {
		this.timeliness_score = timeliness_score == null ? null : timeliness_score.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.timeliness_remark
	 * @return  the value of cal_return_call.timeliness_remark
	 * @mbg.generated
	 */
	public String getTimeliness_remark() {
		return timeliness_remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.timeliness_remark
	 * @param timeliness_remark  the value for cal_return_call.timeliness_remark
	 * @mbg.generated
	 */
	public void setTimeliness_remark(String timeliness_remark) {
		this.timeliness_remark = timeliness_remark == null ? null : timeliness_remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.mt_score
	 * @return  the value of cal_return_call.mt_score
	 * @mbg.generated
	 */
	public String getMt_score() {
		return mt_score;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.mt_score
	 * @param mt_score  the value for cal_return_call.mt_score
	 * @mbg.generated
	 */
	public void setMt_score(String mt_score) {
		this.mt_score = mt_score == null ? null : mt_score.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.mt_remark
	 * @return  the value of cal_return_call.mt_remark
	 * @mbg.generated
	 */
	public String getMt_remark() {
		return mt_remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.mt_remark
	 * @param mt_remark  the value for cal_return_call.mt_remark
	 * @mbg.generated
	 */
	public void setMt_remark(String mt_remark) {
		this.mt_remark = mt_remark == null ? null : mt_remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.spare_score
	 * @return  the value of cal_return_call.spare_score
	 * @mbg.generated
	 */
	public String getSpare_score() {
		return spare_score;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.spare_score
	 * @param spare_score  the value for cal_return_call.spare_score
	 * @mbg.generated
	 */
	public void setSpare_score(String spare_score) {
		this.spare_score = spare_score == null ? null : spare_score.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.spare_remark
	 * @return  the value of cal_return_call.spare_remark
	 * @mbg.generated
	 */
	public String getSpare_remark() {
		return spare_remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.spare_remark
	 * @param spare_remark  the value for cal_return_call.spare_remark
	 * @mbg.generated
	 */
	public void setSpare_remark(String spare_remark) {
		this.spare_remark = spare_remark == null ? null : spare_remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.cust_return
	 * @return  the value of cal_return_call.cust_return
	 * @mbg.generated
	 */
	public String getCust_return() {
		return cust_return;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.cust_return
	 * @param cust_return  the value for cal_return_call.cust_return
	 * @mbg.generated
	 */
	public void setCust_return(String cust_return) {
		this.cust_return = cust_return == null ? null : cust_return.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.comp_return
	 * @return  the value of cal_return_call.comp_return
	 * @mbg.generated
	 */
	public String getComp_return() {
		return comp_return;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.comp_return
	 * @param comp_return  the value for cal_return_call.comp_return
	 * @mbg.generated
	 */
	public void setComp_return(String comp_return) {
		this.comp_return = comp_return == null ? null : comp_return.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.comp_return_person
	 * @return  the value of cal_return_call.comp_return_person
	 * @mbg.generated
	 */
	public String getComp_return_person() {
		return comp_return_person;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.comp_return_person
	 * @param comp_return_person  the value for cal_return_call.comp_return_person
	 * @mbg.generated
	 */
	public void setComp_return_person(String comp_return_person) {
		this.comp_return_person = comp_return_person == null ? null : comp_return_person.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.comp_return_datetime
	 * @return  the value of cal_return_call.comp_return_datetime
	 * @mbg.generated
	 */
	public Timestamp getComp_return_datetime() {
		return comp_return_datetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.comp_return_datetime
	 * @param comp_return_datetime  the value for cal_return_call.comp_return_datetime
	 * @mbg.generated
	 */
	public void setComp_return_datetime(Timestamp comp_return_datetime) {
		this.comp_return_datetime = comp_return_datetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.enable_flag
	 * @return  the value of cal_return_call.enable_flag
	 * @mbg.generated
	 */
	public String getEnable_flag() {
		return enable_flag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.enable_flag
	 * @param enable_flag  the value for cal_return_call.enable_flag
	 * @mbg.generated
	 */
	public void setEnable_flag(String enable_flag) {
		this.enable_flag = enable_flag == null ? null : enable_flag.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.create_user
	 * @return  the value of cal_return_call.create_user
	 * @mbg.generated
	 */
	public String getCreate_user() {
		return create_user;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.create_user
	 * @param create_user  the value for cal_return_call.create_user
	 * @mbg.generated
	 */
	public void setCreate_user(String create_user) {
		this.create_user = create_user == null ? null : create_user.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.create_timestamp
	 * @return  the value of cal_return_call.create_timestamp
	 * @mbg.generated
	 */
	public Timestamp getCreate_timestamp() {
		return create_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.create_timestamp
	 * @param create_timestamp  the value for cal_return_call.create_timestamp
	 * @mbg.generated
	 */
	public void setCreate_timestamp(Timestamp create_timestamp) {
		this.create_timestamp = create_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.last_update_user
	 * @return  the value of cal_return_call.last_update_user
	 * @mbg.generated
	 */
	public String getLast_update_user() {
		return last_update_user;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.last_update_user
	 * @param last_update_user  the value for cal_return_call.last_update_user
	 * @mbg.generated
	 */
	public void setLast_update_user(String last_update_user) {
		this.last_update_user = last_update_user == null ? null : last_update_user.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.last_update_timestamp
	 * @return  the value of cal_return_call.last_update_timestamp
	 * @mbg.generated
	 */
	public Timestamp getLast_update_timestamp() {
		return last_update_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.last_update_timestamp
	 * @param last_update_timestamp  the value for cal_return_call.last_update_timestamp
	 * @mbg.generated
	 */
	public void setLast_update_timestamp(Timestamp last_update_timestamp) {
		this.last_update_timestamp = last_update_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.last_update_remark
	 * @return  the value of cal_return_call.last_update_remark
	 * @mbg.generated
	 */
	public String getLast_update_remark() {
		return last_update_remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.last_update_remark
	 * @param last_update_remark  the value for cal_return_call.last_update_remark
	 * @mbg.generated
	 */
	public void setLast_update_remark(String last_update_remark) {
		this.last_update_remark = last_update_remark == null ? null : last_update_remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.dispatch_code
	 * @return  the value of cal_return_call.dispatch_code
	 * @mbg.generated
	 */
	public String getDispatch_code() {
		return dispatch_code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.dispatch_code
	 * @param dispatch_code  the value for cal_return_call.dispatch_code
	 * @mbg.generated
	 */
	public void setDispatch_code(String dispatch_code) {
		this.dispatch_code = dispatch_code == null ? null : dispatch_code.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.comp_return_person_tel
	 * @return  the value of cal_return_call.comp_return_person_tel
	 * @mbg.generated
	 */
	public String getComp_return_person_tel() {
		return comp_return_person_tel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.comp_return_person_tel
	 * @param comp_return_person_tel  the value for cal_return_call.comp_return_person_tel
	 * @mbg.generated
	 */
	public void setComp_return_person_tel(String comp_return_person_tel) {
		this.comp_return_person_tel = comp_return_person_tel == null ? null : comp_return_person_tel.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_return_call.return_complate_datetime
	 * @return  the value of cal_return_call.return_complate_datetime
	 * @mbg.generated
	 */
	public Timestamp getReturn_complate_datetime() {
		return return_complate_datetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_return_call.return_complate_datetime
	 * @param return_complate_datetime  the value for cal_return_call.return_complate_datetime
	 * @mbg.generated
	 */
	public void setReturn_complate_datetime(Timestamp return_complate_datetime) {
		this.return_complate_datetime = return_complate_datetime;
	}
}