package com.zxtech.ess.module.gen.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table cal_multi_fault_resolve
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class CalMultiFaultResolve implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_multi_fault_resolve.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_multi_fault_resolve.service_order
     *
     * @mbg.generated
     */
    private String service_order;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_multi_fault_resolve.occur_reason
     *
     * @mbg.generated
     */
    private String occur_reason;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_multi_fault_resolve.resolve_method
     *
     * @mbg.generated
     */
    private String resolve_method;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_multi_fault_resolve.enable_flag
     *
     * @mbg.generated
     */
    private String enable_flag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_multi_fault_resolve.create_user
     *
     * @mbg.generated
     */
    private String create_user;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_multi_fault_resolve.create_timestamp
     *
     * @mbg.generated
     */
    private Timestamp create_timestamp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_multi_fault_resolve.last_update_user
     *
     * @mbg.generated
     */
    private String last_update_user;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_multi_fault_resolve.last_update_timestamp
     *
     * @mbg.generated
     */
    private Timestamp last_update_timestamp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_multi_fault_resolve.last_update_remark
     *
     * @mbg.generated
     */
    private String last_update_remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cal_multi_fault_resolve
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_multi_fault_resolve.id
     *
     * @return the value of cal_multi_fault_resolve.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_multi_fault_resolve.id
     *
     * @param id the value for cal_multi_fault_resolve.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_multi_fault_resolve.service_order
     *
     * @return the value of cal_multi_fault_resolve.service_order
     *
     * @mbg.generated
     */
    public String getService_order() {
        return service_order;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_multi_fault_resolve.service_order
     *
     * @param service_order the value for cal_multi_fault_resolve.service_order
     *
     * @mbg.generated
     */
    public void setService_order(String service_order) {
        this.service_order = service_order == null ? null : service_order.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_multi_fault_resolve.occur_reason
     *
     * @return the value of cal_multi_fault_resolve.occur_reason
     *
     * @mbg.generated
     */
    public String getOccur_reason() {
        return occur_reason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_multi_fault_resolve.occur_reason
     *
     * @param occur_reason the value for cal_multi_fault_resolve.occur_reason
     *
     * @mbg.generated
     */
    public void setOccur_reason(String occur_reason) {
        this.occur_reason = occur_reason == null ? null : occur_reason.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_multi_fault_resolve.resolve_method
     *
     * @return the value of cal_multi_fault_resolve.resolve_method
     *
     * @mbg.generated
     */
    public String getResolve_method() {
        return resolve_method;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_multi_fault_resolve.resolve_method
     *
     * @param resolve_method the value for cal_multi_fault_resolve.resolve_method
     *
     * @mbg.generated
     */
    public void setResolve_method(String resolve_method) {
        this.resolve_method = resolve_method == null ? null : resolve_method.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_multi_fault_resolve.enable_flag
     *
     * @return the value of cal_multi_fault_resolve.enable_flag
     *
     * @mbg.generated
     */
    public String getEnable_flag() {
        return enable_flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_multi_fault_resolve.enable_flag
     *
     * @param enable_flag the value for cal_multi_fault_resolve.enable_flag
     *
     * @mbg.generated
     */
    public void setEnable_flag(String enable_flag) {
        this.enable_flag = enable_flag == null ? null : enable_flag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_multi_fault_resolve.create_user
     *
     * @return the value of cal_multi_fault_resolve.create_user
     *
     * @mbg.generated
     */
    public String getCreate_user() {
        return create_user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_multi_fault_resolve.create_user
     *
     * @param create_user the value for cal_multi_fault_resolve.create_user
     *
     * @mbg.generated
     */
    public void setCreate_user(String create_user) {
        this.create_user = create_user == null ? null : create_user.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_multi_fault_resolve.create_timestamp
     *
     * @return the value of cal_multi_fault_resolve.create_timestamp
     *
     * @mbg.generated
     */
    public Timestamp getCreate_timestamp() {
        return create_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_multi_fault_resolve.create_timestamp
     *
     * @param create_timestamp the value for cal_multi_fault_resolve.create_timestamp
     *
     * @mbg.generated
     */
    public void setCreate_timestamp(Timestamp create_timestamp) {
        this.create_timestamp = create_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_multi_fault_resolve.last_update_user
     *
     * @return the value of cal_multi_fault_resolve.last_update_user
     *
     * @mbg.generated
     */
    public String getLast_update_user() {
        return last_update_user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_multi_fault_resolve.last_update_user
     *
     * @param last_update_user the value for cal_multi_fault_resolve.last_update_user
     *
     * @mbg.generated
     */
    public void setLast_update_user(String last_update_user) {
        this.last_update_user = last_update_user == null ? null : last_update_user.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_multi_fault_resolve.last_update_timestamp
     *
     * @return the value of cal_multi_fault_resolve.last_update_timestamp
     *
     * @mbg.generated
     */
    public Timestamp getLast_update_timestamp() {
        return last_update_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_multi_fault_resolve.last_update_timestamp
     *
     * @param last_update_timestamp the value for cal_multi_fault_resolve.last_update_timestamp
     *
     * @mbg.generated
     */
    public void setLast_update_timestamp(Timestamp last_update_timestamp) {
        this.last_update_timestamp = last_update_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_multi_fault_resolve.last_update_remark
     *
     * @return the value of cal_multi_fault_resolve.last_update_remark
     *
     * @mbg.generated
     */
    public String getLast_update_remark() {
        return last_update_remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_multi_fault_resolve.last_update_remark
     *
     * @param last_update_remark the value for cal_multi_fault_resolve.last_update_remark
     *
     * @mbg.generated
     */
    public void setLast_update_remark(String last_update_remark) {
        this.last_update_remark = last_update_remark == null ? null : last_update_remark.trim();
    }
}