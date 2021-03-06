package com.zxtech.ess.module.gen.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table base_machine_type
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class BaseMachineType implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_machine_type.type_id
     *
     * @mbg.generated
     */
    private String type_id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_machine_type.ele_category
     *
     * @mbg.generated
     */
    private String ele_category;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_machine_type.machine_type
     *
     * @mbg.generated
     */
    private String machine_type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_machine_type.type_code
     *
     * @mbg.generated
     */
    private String type_code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_machine_type.type_desc
     *
     * @mbg.generated
     */
    private String type_desc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_machine_type.p_type_id
     *
     * @mbg.generated
     */
    private String p_type_id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_machine_type.enable_flag
     *
     * @mbg.generated
     */
    private String enable_flag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_machine_type.create_user
     *
     * @mbg.generated
     */
    private String create_user;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_machine_type.create_timestamp
     *
     * @mbg.generated
     */
    private Timestamp create_timestamp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_machine_type.last_update_user
     *
     * @mbg.generated
     */
    private String last_update_user;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_machine_type.last_update_timestamp
     *
     * @mbg.generated
     */
    private Timestamp last_update_timestamp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_machine_type.last_update_remark
     *
     * @mbg.generated
     */
    private String last_update_remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table base_machine_type
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_machine_type.type_id
     *
     * @return the value of base_machine_type.type_id
     *
     * @mbg.generated
     */
    public String getType_id() {
        return type_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_machine_type.type_id
     *
     * @param type_id the value for base_machine_type.type_id
     *
     * @mbg.generated
     */
    public void setType_id(String type_id) {
        this.type_id = type_id == null ? null : type_id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_machine_type.ele_category
     *
     * @return the value of base_machine_type.ele_category
     *
     * @mbg.generated
     */
    public String getEle_category() {
        return ele_category;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_machine_type.ele_category
     *
     * @param ele_category the value for base_machine_type.ele_category
     *
     * @mbg.generated
     */
    public void setEle_category(String ele_category) {
        this.ele_category = ele_category == null ? null : ele_category.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_machine_type.machine_type
     *
     * @return the value of base_machine_type.machine_type
     *
     * @mbg.generated
     */
    public String getMachine_type() {
        return machine_type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_machine_type.machine_type
     *
     * @param machine_type the value for base_machine_type.machine_type
     *
     * @mbg.generated
     */
    public void setMachine_type(String machine_type) {
        this.machine_type = machine_type == null ? null : machine_type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_machine_type.type_code
     *
     * @return the value of base_machine_type.type_code
     *
     * @mbg.generated
     */
    public String getType_code() {
        return type_code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_machine_type.type_code
     *
     * @param type_code the value for base_machine_type.type_code
     *
     * @mbg.generated
     */
    public void setType_code(String type_code) {
        this.type_code = type_code == null ? null : type_code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_machine_type.type_desc
     *
     * @return the value of base_machine_type.type_desc
     *
     * @mbg.generated
     */
    public String getType_desc() {
        return type_desc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_machine_type.type_desc
     *
     * @param type_desc the value for base_machine_type.type_desc
     *
     * @mbg.generated
     */
    public void setType_desc(String type_desc) {
        this.type_desc = type_desc == null ? null : type_desc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_machine_type.p_type_id
     *
     * @return the value of base_machine_type.p_type_id
     *
     * @mbg.generated
     */
    public String getP_type_id() {
        return p_type_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_machine_type.p_type_id
     *
     * @param p_type_id the value for base_machine_type.p_type_id
     *
     * @mbg.generated
     */
    public void setP_type_id(String p_type_id) {
        this.p_type_id = p_type_id == null ? null : p_type_id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_machine_type.enable_flag
     *
     * @return the value of base_machine_type.enable_flag
     *
     * @mbg.generated
     */
    public String getEnable_flag() {
        return enable_flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_machine_type.enable_flag
     *
     * @param enable_flag the value for base_machine_type.enable_flag
     *
     * @mbg.generated
     */
    public void setEnable_flag(String enable_flag) {
        this.enable_flag = enable_flag == null ? null : enable_flag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_machine_type.create_user
     *
     * @return the value of base_machine_type.create_user
     *
     * @mbg.generated
     */
    public String getCreate_user() {
        return create_user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_machine_type.create_user
     *
     * @param create_user the value for base_machine_type.create_user
     *
     * @mbg.generated
     */
    public void setCreate_user(String create_user) {
        this.create_user = create_user == null ? null : create_user.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_machine_type.create_timestamp
     *
     * @return the value of base_machine_type.create_timestamp
     *
     * @mbg.generated
     */
    public Timestamp getCreate_timestamp() {
        return create_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_machine_type.create_timestamp
     *
     * @param create_timestamp the value for base_machine_type.create_timestamp
     *
     * @mbg.generated
     */
    public void setCreate_timestamp(Timestamp create_timestamp) {
        this.create_timestamp = create_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_machine_type.last_update_user
     *
     * @return the value of base_machine_type.last_update_user
     *
     * @mbg.generated
     */
    public String getLast_update_user() {
        return last_update_user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_machine_type.last_update_user
     *
     * @param last_update_user the value for base_machine_type.last_update_user
     *
     * @mbg.generated
     */
    public void setLast_update_user(String last_update_user) {
        this.last_update_user = last_update_user == null ? null : last_update_user.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_machine_type.last_update_timestamp
     *
     * @return the value of base_machine_type.last_update_timestamp
     *
     * @mbg.generated
     */
    public Timestamp getLast_update_timestamp() {
        return last_update_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_machine_type.last_update_timestamp
     *
     * @param last_update_timestamp the value for base_machine_type.last_update_timestamp
     *
     * @mbg.generated
     */
    public void setLast_update_timestamp(Timestamp last_update_timestamp) {
        this.last_update_timestamp = last_update_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_machine_type.last_update_remark
     *
     * @return the value of base_machine_type.last_update_remark
     *
     * @mbg.generated
     */
    public String getLast_update_remark() {
        return last_update_remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_machine_type.last_update_remark
     *
     * @param last_update_remark the value for base_machine_type.last_update_remark
     *
     * @mbg.generated
     */
    public void setLast_update_remark(String last_update_remark) {
        this.last_update_remark = last_update_remark == null ? null : last_update_remark.trim();
    }
}