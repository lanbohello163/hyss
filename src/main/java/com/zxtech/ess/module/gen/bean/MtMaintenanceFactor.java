package com.zxtech.ess.module.gen.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table mt_maintenance_factor
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class MtMaintenanceFactor implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_maintenance_factor.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_maintenance_factor.factor_code
     *
     * @mbg.generated
     */
    private String factor_code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_maintenance_factor.factor_column
     *
     * @mbg.generated
     */
    private String factor_column;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_maintenance_factor.factor_value_type
     *
     * @mbg.generated
     */
    private String factor_value_type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_maintenance_factor.factor_evaluation
     *
     * @mbg.generated
     */
    private String factor_evaluation;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_maintenance_factor.factor_evaluation_desc
     *
     * @mbg.generated
     */
    private String factor_evaluation_desc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_maintenance_factor.enable_flag
     *
     * @mbg.generated
     */
    private String enable_flag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_maintenance_factor.create_user
     *
     * @mbg.generated
     */
    private String create_user;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_maintenance_factor.create_timestamp
     *
     * @mbg.generated
     */
    private Timestamp create_timestamp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_maintenance_factor.last_update_user
     *
     * @mbg.generated
     */
    private String last_update_user;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_maintenance_factor.last_update_timestamp
     *
     * @mbg.generated
     */
    private Timestamp last_update_timestamp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_maintenance_factor.last_update_remark
     *
     * @mbg.generated
     */
    private String last_update_remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mt_maintenance_factor
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_maintenance_factor.id
     *
     * @return the value of mt_maintenance_factor.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_maintenance_factor.id
     *
     * @param id the value for mt_maintenance_factor.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_maintenance_factor.factor_code
     *
     * @return the value of mt_maintenance_factor.factor_code
     *
     * @mbg.generated
     */
    public String getFactor_code() {
        return factor_code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_maintenance_factor.factor_code
     *
     * @param factor_code the value for mt_maintenance_factor.factor_code
     *
     * @mbg.generated
     */
    public void setFactor_code(String factor_code) {
        this.factor_code = factor_code == null ? null : factor_code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_maintenance_factor.factor_column
     *
     * @return the value of mt_maintenance_factor.factor_column
     *
     * @mbg.generated
     */
    public String getFactor_column() {
        return factor_column;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_maintenance_factor.factor_column
     *
     * @param factor_column the value for mt_maintenance_factor.factor_column
     *
     * @mbg.generated
     */
    public void setFactor_column(String factor_column) {
        this.factor_column = factor_column == null ? null : factor_column.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_maintenance_factor.factor_value_type
     *
     * @return the value of mt_maintenance_factor.factor_value_type
     *
     * @mbg.generated
     */
    public String getFactor_value_type() {
        return factor_value_type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_maintenance_factor.factor_value_type
     *
     * @param factor_value_type the value for mt_maintenance_factor.factor_value_type
     *
     * @mbg.generated
     */
    public void setFactor_value_type(String factor_value_type) {
        this.factor_value_type = factor_value_type == null ? null : factor_value_type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_maintenance_factor.factor_evaluation
     *
     * @return the value of mt_maintenance_factor.factor_evaluation
     *
     * @mbg.generated
     */
    public String getFactor_evaluation() {
        return factor_evaluation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_maintenance_factor.factor_evaluation
     *
     * @param factor_evaluation the value for mt_maintenance_factor.factor_evaluation
     *
     * @mbg.generated
     */
    public void setFactor_evaluation(String factor_evaluation) {
        this.factor_evaluation = factor_evaluation == null ? null : factor_evaluation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_maintenance_factor.factor_evaluation_desc
     *
     * @return the value of mt_maintenance_factor.factor_evaluation_desc
     *
     * @mbg.generated
     */
    public String getFactor_evaluation_desc() {
        return factor_evaluation_desc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_maintenance_factor.factor_evaluation_desc
     *
     * @param factor_evaluation_desc the value for mt_maintenance_factor.factor_evaluation_desc
     *
     * @mbg.generated
     */
    public void setFactor_evaluation_desc(String factor_evaluation_desc) {
        this.factor_evaluation_desc = factor_evaluation_desc == null ? null : factor_evaluation_desc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_maintenance_factor.enable_flag
     *
     * @return the value of mt_maintenance_factor.enable_flag
     *
     * @mbg.generated
     */
    public String getEnable_flag() {
        return enable_flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_maintenance_factor.enable_flag
     *
     * @param enable_flag the value for mt_maintenance_factor.enable_flag
     *
     * @mbg.generated
     */
    public void setEnable_flag(String enable_flag) {
        this.enable_flag = enable_flag == null ? null : enable_flag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_maintenance_factor.create_user
     *
     * @return the value of mt_maintenance_factor.create_user
     *
     * @mbg.generated
     */
    public String getCreate_user() {
        return create_user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_maintenance_factor.create_user
     *
     * @param create_user the value for mt_maintenance_factor.create_user
     *
     * @mbg.generated
     */
    public void setCreate_user(String create_user) {
        this.create_user = create_user == null ? null : create_user.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_maintenance_factor.create_timestamp
     *
     * @return the value of mt_maintenance_factor.create_timestamp
     *
     * @mbg.generated
     */
    public Timestamp getCreate_timestamp() {
        return create_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_maintenance_factor.create_timestamp
     *
     * @param create_timestamp the value for mt_maintenance_factor.create_timestamp
     *
     * @mbg.generated
     */
    public void setCreate_timestamp(Timestamp create_timestamp) {
        this.create_timestamp = create_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_maintenance_factor.last_update_user
     *
     * @return the value of mt_maintenance_factor.last_update_user
     *
     * @mbg.generated
     */
    public String getLast_update_user() {
        return last_update_user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_maintenance_factor.last_update_user
     *
     * @param last_update_user the value for mt_maintenance_factor.last_update_user
     *
     * @mbg.generated
     */
    public void setLast_update_user(String last_update_user) {
        this.last_update_user = last_update_user == null ? null : last_update_user.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_maintenance_factor.last_update_timestamp
     *
     * @return the value of mt_maintenance_factor.last_update_timestamp
     *
     * @mbg.generated
     */
    public Timestamp getLast_update_timestamp() {
        return last_update_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_maintenance_factor.last_update_timestamp
     *
     * @param last_update_timestamp the value for mt_maintenance_factor.last_update_timestamp
     *
     * @mbg.generated
     */
    public void setLast_update_timestamp(Timestamp last_update_timestamp) {
        this.last_update_timestamp = last_update_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_maintenance_factor.last_update_remark
     *
     * @return the value of mt_maintenance_factor.last_update_remark
     *
     * @mbg.generated
     */
    public String getLast_update_remark() {
        return last_update_remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_maintenance_factor.last_update_remark
     *
     * @param last_update_remark the value for mt_maintenance_factor.last_update_remark
     *
     * @mbg.generated
     */
    public void setLast_update_remark(String last_update_remark) {
        this.last_update_remark = last_update_remark == null ? null : last_update_remark.trim();
    }
}