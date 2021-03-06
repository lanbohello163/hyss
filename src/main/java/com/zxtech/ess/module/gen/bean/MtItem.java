package com.zxtech.ess.module.gen.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table mt_item
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class MtItem implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_item.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_item.item_no
     *
     * @mbg.generated
     */
    private String item_no;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_item.item_name
     *
     * @mbg.generated
     */
    private String item_name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_item.item_position
     *
     * @mbg.generated
     */
    private String item_position;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_item.ele_category
     *
     * @mbg.generated
     */
    private String ele_category;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_item.std_minute
     *
     * @mbg.generated
     */
    private BigDecimal std_minute;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_item.std_scope
     *
     * @mbg.generated
     */
    private String std_scope;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_item.national_std_no
     *
     * @mbg.generated
     */
    private String national_std_no;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_item.std_picture_1
     *
     * @mbg.generated
     */
    private String std_picture_1;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_item.std_picture_2
     *
     * @mbg.generated
     */
    private String std_picture_2;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_item.is_measure
     *
     * @mbg.generated
     */
    private String is_measure;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_item.is_photo
     *
     * @mbg.generated
     */
    private String is_photo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_item.work_content
     *
     * @mbg.generated
     */
    private String work_content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_item.work_require
     *
     * @mbg.generated
     */
    private String work_require;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_item.enable_flag
     *
     * @mbg.generated
     */
    private String enable_flag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_item.create_user
     *
     * @mbg.generated
     */
    private String create_user;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_item.create_timestamp
     *
     * @mbg.generated
     */
    private Timestamp create_timestamp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_item.last_update_user
     *
     * @mbg.generated
     */
    private String last_update_user;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_item.last_update_timestamp
     *
     * @mbg.generated
     */
    private Timestamp last_update_timestamp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mt_item.last_update_remark
     *
     * @mbg.generated
     */
    private String last_update_remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mt_item
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_item.id
     *
     * @return the value of mt_item.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_item.id
     *
     * @param id the value for mt_item.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_item.item_no
     *
     * @return the value of mt_item.item_no
     *
     * @mbg.generated
     */
    public String getItem_no() {
        return item_no;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_item.item_no
     *
     * @param item_no the value for mt_item.item_no
     *
     * @mbg.generated
     */
    public void setItem_no(String item_no) {
        this.item_no = item_no == null ? null : item_no.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_item.item_name
     *
     * @return the value of mt_item.item_name
     *
     * @mbg.generated
     */
    public String getItem_name() {
        return item_name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_item.item_name
     *
     * @param item_name the value for mt_item.item_name
     *
     * @mbg.generated
     */
    public void setItem_name(String item_name) {
        this.item_name = item_name == null ? null : item_name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_item.item_position
     *
     * @return the value of mt_item.item_position
     *
     * @mbg.generated
     */
    public String getItem_position() {
        return item_position;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_item.item_position
     *
     * @param item_position the value for mt_item.item_position
     *
     * @mbg.generated
     */
    public void setItem_position(String item_position) {
        this.item_position = item_position == null ? null : item_position.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_item.ele_category
     *
     * @return the value of mt_item.ele_category
     *
     * @mbg.generated
     */
    public String getEle_category() {
        return ele_category;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_item.ele_category
     *
     * @param ele_category the value for mt_item.ele_category
     *
     * @mbg.generated
     */
    public void setEle_category(String ele_category) {
        this.ele_category = ele_category == null ? null : ele_category.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_item.std_minute
     *
     * @return the value of mt_item.std_minute
     *
     * @mbg.generated
     */
    public BigDecimal getStd_minute() {
        return std_minute;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_item.std_minute
     *
     * @param std_minute the value for mt_item.std_minute
     *
     * @mbg.generated
     */
    public void setStd_minute(BigDecimal std_minute) {
        this.std_minute = std_minute;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_item.std_scope
     *
     * @return the value of mt_item.std_scope
     *
     * @mbg.generated
     */
    public String getStd_scope() {
        return std_scope;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_item.std_scope
     *
     * @param std_scope the value for mt_item.std_scope
     *
     * @mbg.generated
     */
    public void setStd_scope(String std_scope) {
        this.std_scope = std_scope == null ? null : std_scope.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_item.national_std_no
     *
     * @return the value of mt_item.national_std_no
     *
     * @mbg.generated
     */
    public String getNational_std_no() {
        return national_std_no;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_item.national_std_no
     *
     * @param national_std_no the value for mt_item.national_std_no
     *
     * @mbg.generated
     */
    public void setNational_std_no(String national_std_no) {
        this.national_std_no = national_std_no == null ? null : national_std_no.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_item.std_picture_1
     *
     * @return the value of mt_item.std_picture_1
     *
     * @mbg.generated
     */
    public String getStd_picture_1() {
        return std_picture_1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_item.std_picture_1
     *
     * @param std_picture_1 the value for mt_item.std_picture_1
     *
     * @mbg.generated
     */
    public void setStd_picture_1(String std_picture_1) {
        this.std_picture_1 = std_picture_1 == null ? null : std_picture_1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_item.std_picture_2
     *
     * @return the value of mt_item.std_picture_2
     *
     * @mbg.generated
     */
    public String getStd_picture_2() {
        return std_picture_2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_item.std_picture_2
     *
     * @param std_picture_2 the value for mt_item.std_picture_2
     *
     * @mbg.generated
     */
    public void setStd_picture_2(String std_picture_2) {
        this.std_picture_2 = std_picture_2 == null ? null : std_picture_2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_item.is_measure
     *
     * @return the value of mt_item.is_measure
     *
     * @mbg.generated
     */
    public String getIs_measure() {
        return is_measure;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_item.is_measure
     *
     * @param is_measure the value for mt_item.is_measure
     *
     * @mbg.generated
     */
    public void setIs_measure(String is_measure) {
        this.is_measure = is_measure == null ? null : is_measure.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_item.is_photo
     *
     * @return the value of mt_item.is_photo
     *
     * @mbg.generated
     */
    public String getIs_photo() {
        return is_photo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_item.is_photo
     *
     * @param is_photo the value for mt_item.is_photo
     *
     * @mbg.generated
     */
    public void setIs_photo(String is_photo) {
        this.is_photo = is_photo == null ? null : is_photo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_item.work_content
     *
     * @return the value of mt_item.work_content
     *
     * @mbg.generated
     */
    public String getWork_content() {
        return work_content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_item.work_content
     *
     * @param work_content the value for mt_item.work_content
     *
     * @mbg.generated
     */
    public void setWork_content(String work_content) {
        this.work_content = work_content == null ? null : work_content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_item.work_require
     *
     * @return the value of mt_item.work_require
     *
     * @mbg.generated
     */
    public String getWork_require() {
        return work_require;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_item.work_require
     *
     * @param work_require the value for mt_item.work_require
     *
     * @mbg.generated
     */
    public void setWork_require(String work_require) {
        this.work_require = work_require == null ? null : work_require.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_item.enable_flag
     *
     * @return the value of mt_item.enable_flag
     *
     * @mbg.generated
     */
    public String getEnable_flag() {
        return enable_flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_item.enable_flag
     *
     * @param enable_flag the value for mt_item.enable_flag
     *
     * @mbg.generated
     */
    public void setEnable_flag(String enable_flag) {
        this.enable_flag = enable_flag == null ? null : enable_flag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_item.create_user
     *
     * @return the value of mt_item.create_user
     *
     * @mbg.generated
     */
    public String getCreate_user() {
        return create_user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_item.create_user
     *
     * @param create_user the value for mt_item.create_user
     *
     * @mbg.generated
     */
    public void setCreate_user(String create_user) {
        this.create_user = create_user == null ? null : create_user.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_item.create_timestamp
     *
     * @return the value of mt_item.create_timestamp
     *
     * @mbg.generated
     */
    public Timestamp getCreate_timestamp() {
        return create_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_item.create_timestamp
     *
     * @param create_timestamp the value for mt_item.create_timestamp
     *
     * @mbg.generated
     */
    public void setCreate_timestamp(Timestamp create_timestamp) {
        this.create_timestamp = create_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_item.last_update_user
     *
     * @return the value of mt_item.last_update_user
     *
     * @mbg.generated
     */
    public String getLast_update_user() {
        return last_update_user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_item.last_update_user
     *
     * @param last_update_user the value for mt_item.last_update_user
     *
     * @mbg.generated
     */
    public void setLast_update_user(String last_update_user) {
        this.last_update_user = last_update_user == null ? null : last_update_user.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_item.last_update_timestamp
     *
     * @return the value of mt_item.last_update_timestamp
     *
     * @mbg.generated
     */
    public Timestamp getLast_update_timestamp() {
        return last_update_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_item.last_update_timestamp
     *
     * @param last_update_timestamp the value for mt_item.last_update_timestamp
     *
     * @mbg.generated
     */
    public void setLast_update_timestamp(Timestamp last_update_timestamp) {
        this.last_update_timestamp = last_update_timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mt_item.last_update_remark
     *
     * @return the value of mt_item.last_update_remark
     *
     * @mbg.generated
     */
    public String getLast_update_remark() {
        return last_update_remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mt_item.last_update_remark
     *
     * @param last_update_remark the value for mt_item.last_update_remark
     *
     * @mbg.generated
     */
    public void setLast_update_remark(String last_update_remark) {
        this.last_update_remark = last_update_remark == null ? null : last_update_remark.trim();
    }
}