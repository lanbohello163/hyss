package com.zxtech.ess.module.gen.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table cal_fault_audit_comp
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class CalFaultAuditComp implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.fault_id
     *
     * @mbg.generated
     */
    private Integer fault_id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.stat_confirm_person
     *
     * @mbg.generated
     */
    private String stat_confirm_person;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.stat_confirm_datetime
     *
     * @mbg.generated
     */
    private Timestamp stat_confirm_datetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.reject_reason
     *
     * @mbg.generated
     */
    private String reject_reason;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.rota_no
     *
     * @mbg.generated
     */
    private String rota_no;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.audit_person
     *
     * @mbg.generated
     */
    private String audit_person;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.audit_datetime
     *
     * @mbg.generated
     */
    private Timestamp audit_datetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.machine_type
     *
     * @mbg.generated
     */
    private String machine_type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.type_first
     *
     * @mbg.generated
     */
    private String type_first;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.type_second
     *
     * @mbg.generated
     */
    private String type_second;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.type_third
     *
     * @mbg.generated
     */
    private String type_third;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.fault_def
     *
     * @mbg.generated
     */
    private String fault_def;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.bad_class
     *
     * @mbg.generated
     */
    private String bad_class;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.bad_category
     *
     * @mbg.generated
     */
    private String bad_category;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.is_fault
     *
     * @mbg.generated
     */
    private String is_fault;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.responsibility_attribution
     *
     * @mbg.generated
     */
    private String responsibility_attribution;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.responsibility_type
     *
     * @mbg.generated
     */
    private String responsibility_type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.enable_flag
     *
     * @mbg.generated
     */
    private String enable_flag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.create_user
     *
     * @mbg.generated
     */
    private String create_user;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.create_timestamp
     *
     * @mbg.generated
     */
    private Timestamp create_timestamp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.last_update_user
     *
     * @mbg.generated
     */
    private String last_update_user;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.last_update_timestamp
     *
     * @mbg.generated
     */
    private Timestamp last_update_timestamp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_audit_comp.last_update_remark
     *
     * @mbg.generated
     */
    private String last_update_remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cal_fault_audit_comp
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.id
     *
     * @return the value of cal_fault_audit_comp.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.id
     *
     * @param id the value for cal_fault_audit_comp.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.fault_id
     *
     * @return the value of cal_fault_audit_comp.fault_id
     *
     * @mbg.generated
     */
    public Integer getFault_id() {
        return fault_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.fault_id
     *
     * @param fault_id the value for cal_fault_audit_comp.fault_id
     *
     * @mbg.generated
     */
    public void setFault_id(Integer fault_id) {
        this.fault_id = fault_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.stat_confirm_person
     *
     * @return the value of cal_fault_audit_comp.stat_confirm_person
     *
     * @mbg.generated
     */
    public String getStat_confirm_person() {
        return stat_confirm_person;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.stat_confirm_person
     *
     * @param stat_confirm_person the value for cal_fault_audit_comp.stat_confirm_person
     *
     * @mbg.generated
     */
    public void setStat_confirm_person(String stat_confirm_person) {
        this.stat_confirm_person = stat_confirm_person == null ? null : stat_confirm_person.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.stat_confirm_datetime
     *
     * @return the value of cal_fault_audit_comp.stat_confirm_datetime
     *
     * @mbg.generated
     */
    public Timestamp getStat_confirm_datetime() {
        return stat_confirm_datetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.stat_confirm_datetime
     *
     * @param stat_confirm_datetime the value for cal_fault_audit_comp.stat_confirm_datetime
     *
     * @mbg.generated
     */
    public void setStat_confirm_datetime(Timestamp stat_confirm_datetime) {
        this.stat_confirm_datetime = stat_confirm_datetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.reject_reason
     *
     * @return the value of cal_fault_audit_comp.reject_reason
     *
     * @mbg.generated
     */
    public String getReject_reason() {
        return reject_reason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.reject_reason
     *
     * @param reject_reason the value for cal_fault_audit_comp.reject_reason
     *
     * @mbg.generated
     */
    public void setReject_reason(String reject_reason) {
        this.reject_reason = reject_reason == null ? null : reject_reason.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.rota_no
     *
     * @return the value of cal_fault_audit_comp.rota_no
     *
     * @mbg.generated
     */
    public String getRota_no() {
        return rota_no;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.rota_no
     *
     * @param rota_no the value for cal_fault_audit_comp.rota_no
     *
     * @mbg.generated
     */
    public void setRota_no(String rota_no) {
        this.rota_no = rota_no == null ? null : rota_no.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.audit_person
     *
     * @return the value of cal_fault_audit_comp.audit_person
     *
     * @mbg.generated
     */
    public String getAudit_person() {
        return audit_person;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.audit_person
     *
     * @param audit_person the value for cal_fault_audit_comp.audit_person
     *
     * @mbg.generated
     */
    public void setAudit_person(String audit_person) {
        this.audit_person = audit_person == null ? null : audit_person.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.audit_datetime
     *
     * @return the value of cal_fault_audit_comp.audit_datetime
     *
     * @mbg.generated
     */
    public Timestamp getAudit_datetime() {
        return audit_datetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.audit_datetime
     *
     * @param audit_datetime the value for cal_fault_audit_comp.audit_datetime
     *
     * @mbg.generated
     */
    public void setAudit_datetime(Timestamp audit_datetime) {
        this.audit_datetime = audit_datetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.machine_type
     *
     * @return the value of cal_fault_audit_comp.machine_type
     *
     * @mbg.generated
     */
    public String getMachine_type() {
        return machine_type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.machine_type
     *
     * @param machine_type the value for cal_fault_audit_comp.machine_type
     *
     * @mbg.generated
     */
    public void setMachine_type(String machine_type) {
        this.machine_type = machine_type == null ? null : machine_type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.type_first
     *
     * @return the value of cal_fault_audit_comp.type_first
     *
     * @mbg.generated
     */
    public String getType_first() {
        return type_first;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.type_first
     *
     * @param type_first the value for cal_fault_audit_comp.type_first
     *
     * @mbg.generated
     */
    public void setType_first(String type_first) {
        this.type_first = type_first == null ? null : type_first.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.type_second
     *
     * @return the value of cal_fault_audit_comp.type_second
     *
     * @mbg.generated
     */
    public String getType_second() {
        return type_second;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.type_second
     *
     * @param type_second the value for cal_fault_audit_comp.type_second
     *
     * @mbg.generated
     */
    public void setType_second(String type_second) {
        this.type_second = type_second == null ? null : type_second.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.type_third
     *
     * @return the value of cal_fault_audit_comp.type_third
     *
     * @mbg.generated
     */
    public String getType_third() {
        return type_third;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.type_third
     *
     * @param type_third the value for cal_fault_audit_comp.type_third
     *
     * @mbg.generated
     */
    public void setType_third(String type_third) {
        this.type_third = type_third == null ? null : type_third.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.fault_def
     *
     * @return the value of cal_fault_audit_comp.fault_def
     *
     * @mbg.generated
     */
    public String getFault_def() {
        return fault_def;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.fault_def
     *
     * @param fault_def the value for cal_fault_audit_comp.fault_def
     *
     * @mbg.generated
     */
    public void setFault_def(String fault_def) {
        this.fault_def = fault_def == null ? null : fault_def.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.bad_class
     *
     * @return the value of cal_fault_audit_comp.bad_class
     *
     * @mbg.generated
     */
    public String getBad_class() {
        return bad_class;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.bad_class
     *
     * @param bad_class the value for cal_fault_audit_comp.bad_class
     *
     * @mbg.generated
     */
    public void setBad_class(String bad_class) {
        this.bad_class = bad_class == null ? null : bad_class.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.bad_category
     *
     * @return the value of cal_fault_audit_comp.bad_category
     *
     * @mbg.generated
     */
    public String getBad_category() {
        return bad_category;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.bad_category
     *
     * @param bad_category the value for cal_fault_audit_comp.bad_category
     *
     * @mbg.generated
     */
    public void setBad_category(String bad_category) {
        this.bad_category = bad_category == null ? null : bad_category.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.is_fault
     *
     * @return the value of cal_fault_audit_comp.is_fault
     *
     * @mbg.generated
     */
    public String getIs_fault() {
        return is_fault;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.is_fault
     *
     * @param is_fault the value for cal_fault_audit_comp.is_fault
     *
     * @mbg.generated
     */
    public void setIs_fault(String is_fault) {
        this.is_fault = is_fault == null ? null : is_fault.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.responsibility_attribution
     *
     * @return the value of cal_fault_audit_comp.responsibility_attribution
     *
     * @mbg.generated
     */
    public String getResponsibility_attribution() {
        return responsibility_attribution;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.responsibility_attribution
     *
     * @param responsibility_attribution the value for cal_fault_audit_comp.responsibility_attribution
     *
     * @mbg.generated
     */
    public void setResponsibility_attribution(String responsibility_attribution) {
        this.responsibility_attribution = responsibility_attribution == null ? null : responsibility_attribution.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.responsibility_type
     *
     * @return the value of cal_fault_audit_comp.responsibility_type
     *
     * @mbg.generated
     */
    public String getResponsibility_type() {
        return responsibility_type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.responsibility_type
     *
     * @param responsibility_type the value for cal_fault_audit_comp.responsibility_type
     *
     * @mbg.generated
     */
    public void setResponsibility_type(String responsibility_type) {
        this.responsibility_type = responsibility_type == null ? null : responsibility_type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.enable_flag
     *
     * @return the value of cal_fault_audit_comp.enable_flag
     *
     * @mbg.generated
     */
    public String getEnable_flag() {
        return enable_flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.enable_flag
     *
     * @param enable_flag the value for cal_fault_audit_comp.enable_flag
     *
     * @mbg.generated
     */
    public void setEnable_flag(String enable_flag) {
        this.enable_flag = enable_flag == null ? null : enable_flag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.create_user
     *
     * @return the value of cal_fault_audit_comp.create_user
     *
     * @mbg.generated
     */
    public String getCreate_user() {
        return create_user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.create_user
     *
     * @param create_user the value for cal_fault_audit_comp.create_user
     *
     * @mbg.generated
     */
    public void setCreate_user(String create_user) {
        this.create_user = create_user == null ? null : create_user.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.create_timestamp
     *
     * @return the value of cal_fault_audit_comp.create_timestamp
     *
     * @mbg.generated
     */
    public Timestamp getCreate_timestamp() {
        return create_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.create_timestamp
     *
     * @param create_timestamp the value for cal_fault_audit_comp.create_timestamp
     *
     * @mbg.generated
     */
    public void setCreate_timestamp(Timestamp create_timestamp) {
        this.create_timestamp = create_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.last_update_user
     *
     * @return the value of cal_fault_audit_comp.last_update_user
     *
     * @mbg.generated
     */
    public String getLast_update_user() {
        return last_update_user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.last_update_user
     *
     * @param last_update_user the value for cal_fault_audit_comp.last_update_user
     *
     * @mbg.generated
     */
    public void setLast_update_user(String last_update_user) {
        this.last_update_user = last_update_user == null ? null : last_update_user.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.last_update_timestamp
     *
     * @return the value of cal_fault_audit_comp.last_update_timestamp
     *
     * @mbg.generated
     */
    public Timestamp getLast_update_timestamp() {
        return last_update_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.last_update_timestamp
     *
     * @param last_update_timestamp the value for cal_fault_audit_comp.last_update_timestamp
     *
     * @mbg.generated
     */
    public void setLast_update_timestamp(Timestamp last_update_timestamp) {
        this.last_update_timestamp = last_update_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_audit_comp.last_update_remark
     *
     * @return the value of cal_fault_audit_comp.last_update_remark
     *
     * @mbg.generated
     */
    public String getLast_update_remark() {
        return last_update_remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_audit_comp.last_update_remark
     *
     * @param last_update_remark the value for cal_fault_audit_comp.last_update_remark
     *
     * @mbg.generated
     */
    public void setLast_update_remark(String last_update_remark) {
        this.last_update_remark = last_update_remark == null ? null : last_update_remark.trim();
    }
}