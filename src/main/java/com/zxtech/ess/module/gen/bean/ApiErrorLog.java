package com.zxtech.ess.module.gen.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table api_error_log
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class ApiErrorLog implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column api_error_log.id
	 * @mbg.generated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column api_error_log.operation_log_id
	 * @mbg.generated
	 */
	private Integer operation_log_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column api_error_log.error_log_information
	 * @mbg.generated
	 */
	private String error_log_information;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column api_error_log.results
	 * @mbg.generated
	 */
	private Integer results;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column api_error_log.handler
	 * @mbg.generated
	 */
	private String handler;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column api_error_log.remark
	 * @mbg.generated
	 */
	private String remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column api_error_log.enable_flag
	 * @mbg.generated
	 */
	private String enable_flag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column api_error_log.create_timestamp
	 * @mbg.generated
	 */
	private Timestamp create_timestamp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column api_error_log.last_update_timestamp
	 * @mbg.generated
	 */
	private Timestamp last_update_timestamp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table api_error_log
	 * @mbg.generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column api_error_log.id
	 * @return  the value of api_error_log.id
	 * @mbg.generated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column api_error_log.id
	 * @param id  the value for api_error_log.id
	 * @mbg.generated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column api_error_log.operation_log_id
	 * @return  the value of api_error_log.operation_log_id
	 * @mbg.generated
	 */
	public Integer getOperation_log_id() {
		return operation_log_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column api_error_log.operation_log_id
	 * @param operation_log_id  the value for api_error_log.operation_log_id
	 * @mbg.generated
	 */
	public void setOperation_log_id(Integer operation_log_id) {
		this.operation_log_id = operation_log_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column api_error_log.error_log_information
	 * @return  the value of api_error_log.error_log_information
	 * @mbg.generated
	 */
	public String getError_log_information() {
		return error_log_information;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column api_error_log.error_log_information
	 * @param error_log_information  the value for api_error_log.error_log_information
	 * @mbg.generated
	 */
	public void setError_log_information(String error_log_information) {
		this.error_log_information = error_log_information == null ? null : error_log_information.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column api_error_log.results
	 * @return  the value of api_error_log.results
	 * @mbg.generated
	 */
	public Integer getResults() {
		return results;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column api_error_log.results
	 * @param results  the value for api_error_log.results
	 * @mbg.generated
	 */
	public void setResults(Integer results) {
		this.results = results;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column api_error_log.handler
	 * @return  the value of api_error_log.handler
	 * @mbg.generated
	 */
	public String getHandler() {
		return handler;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column api_error_log.handler
	 * @param handler  the value for api_error_log.handler
	 * @mbg.generated
	 */
	public void setHandler(String handler) {
		this.handler = handler == null ? null : handler.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column api_error_log.remark
	 * @return  the value of api_error_log.remark
	 * @mbg.generated
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column api_error_log.remark
	 * @param remark  the value for api_error_log.remark
	 * @mbg.generated
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column api_error_log.enable_flag
	 * @return  the value of api_error_log.enable_flag
	 * @mbg.generated
	 */
	public String getEnable_flag() {
		return enable_flag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column api_error_log.enable_flag
	 * @param enable_flag  the value for api_error_log.enable_flag
	 * @mbg.generated
	 */
	public void setEnable_flag(String enable_flag) {
		this.enable_flag = enable_flag == null ? null : enable_flag.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column api_error_log.create_timestamp
	 * @return  the value of api_error_log.create_timestamp
	 * @mbg.generated
	 */
	public Timestamp getCreate_timestamp() {
		return create_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column api_error_log.create_timestamp
	 * @param create_timestamp  the value for api_error_log.create_timestamp
	 * @mbg.generated
	 */
	public void setCreate_timestamp(Timestamp create_timestamp) {
		this.create_timestamp = create_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column api_error_log.last_update_timestamp
	 * @return  the value of api_error_log.last_update_timestamp
	 * @mbg.generated
	 */
	public Timestamp getLast_update_timestamp() {
		return last_update_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column api_error_log.last_update_timestamp
	 * @param last_update_timestamp  the value for api_error_log.last_update_timestamp
	 * @mbg.generated
	 */
	public void setLast_update_timestamp(Timestamp last_update_timestamp) {
		this.last_update_timestamp = last_update_timestamp;
	}
}