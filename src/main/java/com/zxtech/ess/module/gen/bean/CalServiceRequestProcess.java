package com.zxtech.ess.module.gen.bean;

import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table cal_service_request_process
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class CalServiceRequestProcess implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_service_request_process.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_service_request_process.service_order
     *
     * @mbg.generated
     */
    private String service_order;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_service_request_process.deal_datetime
     *
     * @mbg.generated
     */
    private String deal_datetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_service_request_process.deal_person
     *
     * @mbg.generated
     */
    private String deal_person;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cal_service_request_process.deal_content
     *
     * @mbg.generated
     */
    private String deal_content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cal_service_request_process
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_service_request_process.id
     *
     * @return the value of cal_service_request_process.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_service_request_process.id
     *
     * @param id the value for cal_service_request_process.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_service_request_process.service_order
     *
     * @return the value of cal_service_request_process.service_order
     *
     * @mbg.generated
     */
    public String getService_order() {
        return service_order;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_service_request_process.service_order
     *
     * @param service_order the value for cal_service_request_process.service_order
     *
     * @mbg.generated
     */
    public void setService_order(String service_order) {
        this.service_order = service_order == null ? null : service_order.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_service_request_process.deal_datetime
     *
     * @return the value of cal_service_request_process.deal_datetime
     *
     * @mbg.generated
     */
    public String getDeal_datetime() {
        return deal_datetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_service_request_process.deal_datetime
     *
     * @param deal_datetime the value for cal_service_request_process.deal_datetime
     *
     * @mbg.generated
     */
    public void setDeal_datetime(String deal_datetime) {
        this.deal_datetime = deal_datetime == null ? null : deal_datetime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_service_request_process.deal_person
     *
     * @return the value of cal_service_request_process.deal_person
     *
     * @mbg.generated
     */
    public String getDeal_person() {
        return deal_person;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_service_request_process.deal_person
     *
     * @param deal_person the value for cal_service_request_process.deal_person
     *
     * @mbg.generated
     */
    public void setDeal_person(String deal_person) {
        this.deal_person = deal_person == null ? null : deal_person.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cal_service_request_process.deal_content
     *
     * @return the value of cal_service_request_process.deal_content
     *
     * @mbg.generated
     */
    public String getDeal_content() {
        return deal_content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cal_service_request_process.deal_content
     *
     * @param deal_content the value for cal_service_request_process.deal_content
     *
     * @mbg.generated
     */
    public void setDeal_content(String deal_content) {
        this.deal_content = deal_content == null ? null : deal_content.trim();
    }
}