package com.zxtech.ess.module.gen.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table item_replace_record
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class ItemReplaceRecord implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.id
	 * @mbg.generated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.outbound_id
	 * @mbg.generated
	 */
	private Integer outbound_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.replace_status
	 * @mbg.generated
	 */
	private String replace_status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.replace_type
	 * @mbg.generated
	 */
	private String replace_type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.is_prevent_replace
	 * @mbg.generated
	 */
	private String is_prevent_replace;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.responsibility_category
	 * @mbg.generated
	 */
	private String responsibility_category;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.ele_category
	 * @mbg.generated
	 */
	private String ele_category;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.resource_order
	 * @mbg.generated
	 */
	private String resource_order;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.fault_reason
	 * @mbg.generated
	 */
	private String fault_reason;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.item_part
	 * @mbg.generated
	 */
	private String item_part;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.item_floor
	 * @mbg.generated
	 */
	private String item_floor;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.item_num
	 * @mbg.generated
	 */
	private Short item_num;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.replace_person
	 * @mbg.generated
	 */
	private String replace_person;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.replace_person_code
	 * @mbg.generated
	 */
	private String replace_person_code;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.replace_datetime
	 * @mbg.generated
	 */
	private Timestamp replace_datetime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.cost_desc
	 * @mbg.generated
	 */
	private String cost_desc;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.environmental_factor
	 * @mbg.generated
	 */
	private String environmental_factor;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.replace_asset_num
	 * @mbg.generated
	 */
	private String replace_asset_num;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.contract_code
	 * @mbg.generated
	 */
	private String contract_code;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.part_code
	 * @mbg.generated
	 */
	private String part_code;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.replace_reason
	 * @mbg.generated
	 */
	private String replace_reason;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.audit_person
	 * @mbg.generated
	 */
	private String audit_person;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.audit_datetime
	 * @mbg.generated
	 */
	private Timestamp audit_datetime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.return_person
	 * @mbg.generated
	 */
	private String return_person;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.return_datetime
	 * @mbg.generated
	 */
	private Timestamp return_datetime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.enable_flag
	 * @mbg.generated
	 */
	private String enable_flag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.create_user
	 * @mbg.generated
	 */
	private String create_user;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.create_timestamp
	 * @mbg.generated
	 */
	private Timestamp create_timestamp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.last_update_user
	 * @mbg.generated
	 */
	private String last_update_user;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.last_update_timestamp
	 * @mbg.generated
	 */
	private Timestamp last_update_timestamp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.last_update_remark
	 * @mbg.generated
	 */
	private String last_update_remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.sync_datetime
	 * @mbg.generated
	 */
	private Date sync_datetime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column item_replace_record.source_id
	 * @mbg.generated
	 */
	private String source_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table item_replace_record
	 * @mbg.generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.id
	 * @return  the value of item_replace_record.id
	 * @mbg.generated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.id
	 * @param id  the value for item_replace_record.id
	 * @mbg.generated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.outbound_id
	 * @return  the value of item_replace_record.outbound_id
	 * @mbg.generated
	 */
	public Integer getOutbound_id() {
		return outbound_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.outbound_id
	 * @param outbound_id  the value for item_replace_record.outbound_id
	 * @mbg.generated
	 */
	public void setOutbound_id(Integer outbound_id) {
		this.outbound_id = outbound_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.replace_status
	 * @return  the value of item_replace_record.replace_status
	 * @mbg.generated
	 */
	public String getReplace_status() {
		return replace_status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.replace_status
	 * @param replace_status  the value for item_replace_record.replace_status
	 * @mbg.generated
	 */
	public void setReplace_status(String replace_status) {
		this.replace_status = replace_status == null ? null : replace_status.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.replace_type
	 * @return  the value of item_replace_record.replace_type
	 * @mbg.generated
	 */
	public String getReplace_type() {
		return replace_type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.replace_type
	 * @param replace_type  the value for item_replace_record.replace_type
	 * @mbg.generated
	 */
	public void setReplace_type(String replace_type) {
		this.replace_type = replace_type == null ? null : replace_type.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.is_prevent_replace
	 * @return  the value of item_replace_record.is_prevent_replace
	 * @mbg.generated
	 */
	public String getIs_prevent_replace() {
		return is_prevent_replace;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.is_prevent_replace
	 * @param is_prevent_replace  the value for item_replace_record.is_prevent_replace
	 * @mbg.generated
	 */
	public void setIs_prevent_replace(String is_prevent_replace) {
		this.is_prevent_replace = is_prevent_replace == null ? null : is_prevent_replace.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.responsibility_category
	 * @return  the value of item_replace_record.responsibility_category
	 * @mbg.generated
	 */
	public String getResponsibility_category() {
		return responsibility_category;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.responsibility_category
	 * @param responsibility_category  the value for item_replace_record.responsibility_category
	 * @mbg.generated
	 */
	public void setResponsibility_category(String responsibility_category) {
		this.responsibility_category = responsibility_category == null ? null : responsibility_category.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.ele_category
	 * @return  the value of item_replace_record.ele_category
	 * @mbg.generated
	 */
	public String getEle_category() {
		return ele_category;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.ele_category
	 * @param ele_category  the value for item_replace_record.ele_category
	 * @mbg.generated
	 */
	public void setEle_category(String ele_category) {
		this.ele_category = ele_category == null ? null : ele_category.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.resource_order
	 * @return  the value of item_replace_record.resource_order
	 * @mbg.generated
	 */
	public String getResource_order() {
		return resource_order;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.resource_order
	 * @param resource_order  the value for item_replace_record.resource_order
	 * @mbg.generated
	 */
	public void setResource_order(String resource_order) {
		this.resource_order = resource_order == null ? null : resource_order.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.fault_reason
	 * @return  the value of item_replace_record.fault_reason
	 * @mbg.generated
	 */
	public String getFault_reason() {
		return fault_reason;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.fault_reason
	 * @param fault_reason  the value for item_replace_record.fault_reason
	 * @mbg.generated
	 */
	public void setFault_reason(String fault_reason) {
		this.fault_reason = fault_reason == null ? null : fault_reason.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.item_part
	 * @return  the value of item_replace_record.item_part
	 * @mbg.generated
	 */
	public String getItem_part() {
		return item_part;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.item_part
	 * @param item_part  the value for item_replace_record.item_part
	 * @mbg.generated
	 */
	public void setItem_part(String item_part) {
		this.item_part = item_part == null ? null : item_part.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.item_floor
	 * @return  the value of item_replace_record.item_floor
	 * @mbg.generated
	 */
	public String getItem_floor() {
		return item_floor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.item_floor
	 * @param item_floor  the value for item_replace_record.item_floor
	 * @mbg.generated
	 */
	public void setItem_floor(String item_floor) {
		this.item_floor = item_floor == null ? null : item_floor.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.item_num
	 * @return  the value of item_replace_record.item_num
	 * @mbg.generated
	 */
	public Short getItem_num() {
		return item_num;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.item_num
	 * @param item_num  the value for item_replace_record.item_num
	 * @mbg.generated
	 */
	public void setItem_num(Short item_num) {
		this.item_num = item_num;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.replace_person
	 * @return  the value of item_replace_record.replace_person
	 * @mbg.generated
	 */
	public String getReplace_person() {
		return replace_person;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.replace_person
	 * @param replace_person  the value for item_replace_record.replace_person
	 * @mbg.generated
	 */
	public void setReplace_person(String replace_person) {
		this.replace_person = replace_person == null ? null : replace_person.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.replace_person_code
	 * @return  the value of item_replace_record.replace_person_code
	 * @mbg.generated
	 */
	public String getReplace_person_code() {
		return replace_person_code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.replace_person_code
	 * @param replace_person_code  the value for item_replace_record.replace_person_code
	 * @mbg.generated
	 */
	public void setReplace_person_code(String replace_person_code) {
		this.replace_person_code = replace_person_code == null ? null : replace_person_code.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.replace_datetime
	 * @return  the value of item_replace_record.replace_datetime
	 * @mbg.generated
	 */
	public Timestamp getReplace_datetime() {
		return replace_datetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.replace_datetime
	 * @param replace_datetime  the value for item_replace_record.replace_datetime
	 * @mbg.generated
	 */
	public void setReplace_datetime(Timestamp replace_datetime) {
		this.replace_datetime = replace_datetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.cost_desc
	 * @return  the value of item_replace_record.cost_desc
	 * @mbg.generated
	 */
	public String getCost_desc() {
		return cost_desc;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.cost_desc
	 * @param cost_desc  the value for item_replace_record.cost_desc
	 * @mbg.generated
	 */
	public void setCost_desc(String cost_desc) {
		this.cost_desc = cost_desc == null ? null : cost_desc.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.environmental_factor
	 * @return  the value of item_replace_record.environmental_factor
	 * @mbg.generated
	 */
	public String getEnvironmental_factor() {
		return environmental_factor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.environmental_factor
	 * @param environmental_factor  the value for item_replace_record.environmental_factor
	 * @mbg.generated
	 */
	public void setEnvironmental_factor(String environmental_factor) {
		this.environmental_factor = environmental_factor == null ? null : environmental_factor.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.replace_asset_num
	 * @return  the value of item_replace_record.replace_asset_num
	 * @mbg.generated
	 */
	public String getReplace_asset_num() {
		return replace_asset_num;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.replace_asset_num
	 * @param replace_asset_num  the value for item_replace_record.replace_asset_num
	 * @mbg.generated
	 */
	public void setReplace_asset_num(String replace_asset_num) {
		this.replace_asset_num = replace_asset_num == null ? null : replace_asset_num.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.contract_code
	 * @return  the value of item_replace_record.contract_code
	 * @mbg.generated
	 */
	public String getContract_code() {
		return contract_code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.contract_code
	 * @param contract_code  the value for item_replace_record.contract_code
	 * @mbg.generated
	 */
	public void setContract_code(String contract_code) {
		this.contract_code = contract_code == null ? null : contract_code.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.part_code
	 * @return  the value of item_replace_record.part_code
	 * @mbg.generated
	 */
	public String getPart_code() {
		return part_code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.part_code
	 * @param part_code  the value for item_replace_record.part_code
	 * @mbg.generated
	 */
	public void setPart_code(String part_code) {
		this.part_code = part_code == null ? null : part_code.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.replace_reason
	 * @return  the value of item_replace_record.replace_reason
	 * @mbg.generated
	 */
	public String getReplace_reason() {
		return replace_reason;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.replace_reason
	 * @param replace_reason  the value for item_replace_record.replace_reason
	 * @mbg.generated
	 */
	public void setReplace_reason(String replace_reason) {
		this.replace_reason = replace_reason == null ? null : replace_reason.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.audit_person
	 * @return  the value of item_replace_record.audit_person
	 * @mbg.generated
	 */
	public String getAudit_person() {
		return audit_person;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.audit_person
	 * @param audit_person  the value for item_replace_record.audit_person
	 * @mbg.generated
	 */
	public void setAudit_person(String audit_person) {
		this.audit_person = audit_person == null ? null : audit_person.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.audit_datetime
	 * @return  the value of item_replace_record.audit_datetime
	 * @mbg.generated
	 */
	public Timestamp getAudit_datetime() {
		return audit_datetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.audit_datetime
	 * @param audit_datetime  the value for item_replace_record.audit_datetime
	 * @mbg.generated
	 */
	public void setAudit_datetime(Timestamp audit_datetime) {
		this.audit_datetime = audit_datetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.return_person
	 * @return  the value of item_replace_record.return_person
	 * @mbg.generated
	 */
	public String getReturn_person() {
		return return_person;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.return_person
	 * @param return_person  the value for item_replace_record.return_person
	 * @mbg.generated
	 */
	public void setReturn_person(String return_person) {
		this.return_person = return_person == null ? null : return_person.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.return_datetime
	 * @return  the value of item_replace_record.return_datetime
	 * @mbg.generated
	 */
	public Timestamp getReturn_datetime() {
		return return_datetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.return_datetime
	 * @param return_datetime  the value for item_replace_record.return_datetime
	 * @mbg.generated
	 */
	public void setReturn_datetime(Timestamp return_datetime) {
		this.return_datetime = return_datetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.enable_flag
	 * @return  the value of item_replace_record.enable_flag
	 * @mbg.generated
	 */
	public String getEnable_flag() {
		return enable_flag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.enable_flag
	 * @param enable_flag  the value for item_replace_record.enable_flag
	 * @mbg.generated
	 */
	public void setEnable_flag(String enable_flag) {
		this.enable_flag = enable_flag == null ? null : enable_flag.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.create_user
	 * @return  the value of item_replace_record.create_user
	 * @mbg.generated
	 */
	public String getCreate_user() {
		return create_user;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.create_user
	 * @param create_user  the value for item_replace_record.create_user
	 * @mbg.generated
	 */
	public void setCreate_user(String create_user) {
		this.create_user = create_user == null ? null : create_user.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.create_timestamp
	 * @return  the value of item_replace_record.create_timestamp
	 * @mbg.generated
	 */
	public Timestamp getCreate_timestamp() {
		return create_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.create_timestamp
	 * @param create_timestamp  the value for item_replace_record.create_timestamp
	 * @mbg.generated
	 */
	public void setCreate_timestamp(Timestamp create_timestamp) {
		this.create_timestamp = create_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.last_update_user
	 * @return  the value of item_replace_record.last_update_user
	 * @mbg.generated
	 */
	public String getLast_update_user() {
		return last_update_user;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.last_update_user
	 * @param last_update_user  the value for item_replace_record.last_update_user
	 * @mbg.generated
	 */
	public void setLast_update_user(String last_update_user) {
		this.last_update_user = last_update_user == null ? null : last_update_user.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.last_update_timestamp
	 * @return  the value of item_replace_record.last_update_timestamp
	 * @mbg.generated
	 */
	public Timestamp getLast_update_timestamp() {
		return last_update_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.last_update_timestamp
	 * @param last_update_timestamp  the value for item_replace_record.last_update_timestamp
	 * @mbg.generated
	 */
	public void setLast_update_timestamp(Timestamp last_update_timestamp) {
		this.last_update_timestamp = last_update_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.last_update_remark
	 * @return  the value of item_replace_record.last_update_remark
	 * @mbg.generated
	 */
	public String getLast_update_remark() {
		return last_update_remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.last_update_remark
	 * @param last_update_remark  the value for item_replace_record.last_update_remark
	 * @mbg.generated
	 */
	public void setLast_update_remark(String last_update_remark) {
		this.last_update_remark = last_update_remark == null ? null : last_update_remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.sync_datetime
	 * @return  the value of item_replace_record.sync_datetime
	 * @mbg.generated
	 */
	public Date getSync_datetime() {
		return sync_datetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.sync_datetime
	 * @param sync_datetime  the value for item_replace_record.sync_datetime
	 * @mbg.generated
	 */
	public void setSync_datetime(Date sync_datetime) {
		this.sync_datetime = sync_datetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column item_replace_record.source_id
	 * @return  the value of item_replace_record.source_id
	 * @mbg.generated
	 */
	public String getSource_id() {
		return source_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column item_replace_record.source_id
	 * @param source_id  the value for item_replace_record.source_id
	 * @mbg.generated
	 */
	public void setSource_id(String source_id) {
		this.source_id = source_id == null ? null : source_id.trim();
	}
}