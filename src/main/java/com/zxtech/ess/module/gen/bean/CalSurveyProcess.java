package com.zxtech.ess.module.gen.bean;

import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table cal_survey_process
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class CalSurveyProcess implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_survey_process.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_survey_process.survey_code
     *
     * @mbg.generated
     */
    private String survey_code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_survey_process.deal_datetime
     *
     * @mbg.generated
     */
    private String deal_datetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_survey_process.deal_person
     *
     * @mbg.generated
     */
    private String deal_person;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_survey_process.deal_content
     *
     * @mbg.generated
     */
    private String deal_content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cal_survey_process
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_survey_process.id
     *
     * @return the value of cal_survey_process.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_survey_process.id
     *
     * @param id the value for cal_survey_process.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_survey_process.survey_code
     *
     * @return the value of cal_survey_process.survey_code
     *
     * @mbg.generated
     */
    public String getSurvey_code() {
        return survey_code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_survey_process.survey_code
     *
     * @param survey_code the value for cal_survey_process.survey_code
     *
     * @mbg.generated
     */
    public void setSurvey_code(String survey_code) {
        this.survey_code = survey_code == null ? null : survey_code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_survey_process.deal_datetime
     *
     * @return the value of cal_survey_process.deal_datetime
     *
     * @mbg.generated
     */
    public String getDeal_datetime() {
        return deal_datetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_survey_process.deal_datetime
     *
     * @param deal_datetime the value for cal_survey_process.deal_datetime
     *
     * @mbg.generated
     */
    public void setDeal_datetime(String deal_datetime) {
        this.deal_datetime = deal_datetime == null ? null : deal_datetime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_survey_process.deal_person
     *
     * @return the value of cal_survey_process.deal_person
     *
     * @mbg.generated
     */
    public String getDeal_person() {
        return deal_person;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_survey_process.deal_person
     *
     * @param deal_person the value for cal_survey_process.deal_person
     *
     * @mbg.generated
     */
    public void setDeal_person(String deal_person) {
        this.deal_person = deal_person == null ? null : deal_person.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_survey_process.deal_content
     *
     * @return the value of cal_survey_process.deal_content
     *
     * @mbg.generated
     */
    public String getDeal_content() {
        return deal_content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_survey_process.deal_content
     *
     * @param deal_content the value for cal_survey_process.deal_content
     *
     * @mbg.generated
     */
    public void setDeal_content(String deal_content) {
        this.deal_content = deal_content == null ? null : deal_content.trim();
    }
}