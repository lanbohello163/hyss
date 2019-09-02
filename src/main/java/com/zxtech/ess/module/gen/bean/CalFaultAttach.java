package com.zxtech.ess.module.gen.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table cal_fault_attach
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class CalFaultAttach implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_attach.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_attach.fault_id
     *
     * @mbg.generated
     */
    private Integer fault_id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_attach.attach_name
     *
     * @mbg.generated
     */
    private String attach_name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_attach.attach_url
     *
     * @mbg.generated
     */
    private String attach_url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_attach.attach_byte_size
     *
     * @mbg.generated
     */
    private Integer attach_byte_size;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_attach.attach_remark
     *
     * @mbg.generated
     */
    private String attach_remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_attach.enable_flag
     *
     * @mbg.generated
     */
    private String enable_flag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_attach.create_user
     *
     * @mbg.generated
     */
    private String create_user;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_attach.create_timestamp
     *
     * @mbg.generated
     */
    private Timestamp create_timestamp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_attach.last_update_user
     *
     * @mbg.generated
     */
    private String last_update_user;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_attach.last_update_timestamp
     *
     * @mbg.generated
     */
    private Timestamp last_update_timestamp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_fault_attach.last_update_remark
     *
     * @mbg.generated
     */
    private String last_update_remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cal_fault_attach
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_attach.id
     *
     * @return the value of cal_fault_attach.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_attach.id
     *
     * @param id the value for cal_fault_attach.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_attach.fault_id
     *
     * @return the value of cal_fault_attach.fault_id
     *
     * @mbg.generated
     */
    public Integer getFault_id() {
        return fault_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_attach.fault_id
     *
     * @param fault_id the value for cal_fault_attach.fault_id
     *
     * @mbg.generated
     */
    public void setFault_id(Integer fault_id) {
        this.fault_id = fault_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_attach.attach_name
     *
     * @return the value of cal_fault_attach.attach_name
     *
     * @mbg.generated
     */
    public String getAttach_name() {
        return attach_name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_attach.attach_name
     *
     * @param attach_name the value for cal_fault_attach.attach_name
     *
     * @mbg.generated
     */
    public void setAttach_name(String attach_name) {
        this.attach_name = attach_name == null ? null : attach_name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_attach.attach_url
     *
     * @return the value of cal_fault_attach.attach_url
     *
     * @mbg.generated
     */
    public String getAttach_url() {
        return attach_url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_attach.attach_url
     *
     * @param attach_url the value for cal_fault_attach.attach_url
     *
     * @mbg.generated
     */
    public void setAttach_url(String attach_url) {
        this.attach_url = attach_url == null ? null : attach_url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_attach.attach_byte_size
     *
     * @return the value of cal_fault_attach.attach_byte_size
     *
     * @mbg.generated
     */
    public Integer getAttach_byte_size() {
        return attach_byte_size;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_attach.attach_byte_size
     *
     * @param attach_byte_size the value for cal_fault_attach.attach_byte_size
     *
     * @mbg.generated
     */
    public void setAttach_byte_size(Integer attach_byte_size) {
        this.attach_byte_size = attach_byte_size;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_attach.attach_remark
     *
     * @return the value of cal_fault_attach.attach_remark
     *
     * @mbg.generated
     */
    public String getAttach_remark() {
        return attach_remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_attach.attach_remark
     *
     * @param attach_remark the value for cal_fault_attach.attach_remark
     *
     * @mbg.generated
     */
    public void setAttach_remark(String attach_remark) {
        this.attach_remark = attach_remark == null ? null : attach_remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_attach.enable_flag
     *
     * @return the value of cal_fault_attach.enable_flag
     *
     * @mbg.generated
     */
    public String getEnable_flag() {
        return enable_flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_attach.enable_flag
     *
     * @param enable_flag the value for cal_fault_attach.enable_flag
     *
     * @mbg.generated
     */
    public void setEnable_flag(String enable_flag) {
        this.enable_flag = enable_flag == null ? null : enable_flag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_attach.create_user
     *
     * @return the value of cal_fault_attach.create_user
     *
     * @mbg.generated
     */
    public String getCreate_user() {
        return create_user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_attach.create_user
     *
     * @param create_user the value for cal_fault_attach.create_user
     *
     * @mbg.generated
     */
    public void setCreate_user(String create_user) {
        this.create_user = create_user == null ? null : create_user.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_attach.create_timestamp
     *
     * @return the value of cal_fault_attach.create_timestamp
     *
     * @mbg.generated
     */
    public Timestamp getCreate_timestamp() {
        return create_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_attach.create_timestamp
     *
     * @param create_timestamp the value for cal_fault_attach.create_timestamp
     *
     * @mbg.generated
     */
    public void setCreate_timestamp(Timestamp create_timestamp) {
        this.create_timestamp = create_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_attach.last_update_user
     *
     * @return the value of cal_fault_attach.last_update_user
     *
     * @mbg.generated
     */
    public String getLast_update_user() {
        return last_update_user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_attach.last_update_user
     *
     * @param last_update_user the value for cal_fault_attach.last_update_user
     *
     * @mbg.generated
     */
    public void setLast_update_user(String last_update_user) {
        this.last_update_user = last_update_user == null ? null : last_update_user.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_attach.last_update_timestamp
     *
     * @return the value of cal_fault_attach.last_update_timestamp
     *
     * @mbg.generated
     */
    public Timestamp getLast_update_timestamp() {
        return last_update_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_attach.last_update_timestamp
     *
     * @param last_update_timestamp the value for cal_fault_attach.last_update_timestamp
     *
     * @mbg.generated
     */
    public void setLast_update_timestamp(Timestamp last_update_timestamp) {
        this.last_update_timestamp = last_update_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_fault_attach.last_update_remark
     *
     * @return the value of cal_fault_attach.last_update_remark
     *
     * @mbg.generated
     */
    public String getLast_update_remark() {
        return last_update_remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_fault_attach.last_update_remark
     *
     * @param last_update_remark the value for cal_fault_attach.last_update_remark
     *
     * @mbg.generated
     */
    public void setLast_update_remark(String last_update_remark) {
        this.last_update_remark = last_update_remark == null ? null : last_update_remark.trim();
    }
}