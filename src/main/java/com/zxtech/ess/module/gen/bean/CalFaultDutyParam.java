package com.zxtech.ess.module.gen.bean;

import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table cal_fault_duty_param
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class CalFaultDutyParam implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_fault_duty_param.id
	 * @mbg.generated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_fault_duty_param.fault_def
	 * @mbg.generated
	 */
	private String fault_def;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_fault_duty_param.bad_code
	 * @mbg.generated
	 */
	private String bad_code;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_fault_duty_param.bad_category
	 * @mbg.generated
	 */
	private String bad_category;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_fault_duty_param.bad_class
	 * @mbg.generated
	 */
	private String bad_class;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_fault_duty_param.is_fault
	 * @mbg.generated
	 */
	private String is_fault;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_fault_duty_param.responsibility_attribution
	 * @mbg.generated
	 */
	private String responsibility_attribution;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_fault_duty_param.responsibility_type
	 * @mbg.generated
	 */
	private String responsibility_type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cal_fault_duty_param.enable_flag
	 * @mbg.generated
	 */
	private String enable_flag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table cal_fault_duty_param
	 * @mbg.generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_fault_duty_param.id
	 * @return  the value of cal_fault_duty_param.id
	 * @mbg.generated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_fault_duty_param.id
	 * @param id  the value for cal_fault_duty_param.id
	 * @mbg.generated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_fault_duty_param.fault_def
	 * @return  the value of cal_fault_duty_param.fault_def
	 * @mbg.generated
	 */
	public String getFault_def() {
		return fault_def;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_fault_duty_param.fault_def
	 * @param fault_def  the value for cal_fault_duty_param.fault_def
	 * @mbg.generated
	 */
	public void setFault_def(String fault_def) {
		this.fault_def = fault_def == null ? null : fault_def.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_fault_duty_param.bad_code
	 * @return  the value of cal_fault_duty_param.bad_code
	 * @mbg.generated
	 */
	public String getBad_code() {
		return bad_code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_fault_duty_param.bad_code
	 * @param bad_code  the value for cal_fault_duty_param.bad_code
	 * @mbg.generated
	 */
	public void setBad_code(String bad_code) {
		this.bad_code = bad_code == null ? null : bad_code.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_fault_duty_param.bad_category
	 * @return  the value of cal_fault_duty_param.bad_category
	 * @mbg.generated
	 */
	public String getBad_category() {
		return bad_category;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_fault_duty_param.bad_category
	 * @param bad_category  the value for cal_fault_duty_param.bad_category
	 * @mbg.generated
	 */
	public void setBad_category(String bad_category) {
		this.bad_category = bad_category == null ? null : bad_category.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_fault_duty_param.bad_class
	 * @return  the value of cal_fault_duty_param.bad_class
	 * @mbg.generated
	 */
	public String getBad_class() {
		return bad_class;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_fault_duty_param.bad_class
	 * @param bad_class  the value for cal_fault_duty_param.bad_class
	 * @mbg.generated
	 */
	public void setBad_class(String bad_class) {
		this.bad_class = bad_class == null ? null : bad_class.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_fault_duty_param.is_fault
	 * @return  the value of cal_fault_duty_param.is_fault
	 * @mbg.generated
	 */
	public String getIs_fault() {
		return is_fault;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_fault_duty_param.is_fault
	 * @param is_fault  the value for cal_fault_duty_param.is_fault
	 * @mbg.generated
	 */
	public void setIs_fault(String is_fault) {
		this.is_fault = is_fault == null ? null : is_fault.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_fault_duty_param.responsibility_attribution
	 * @return  the value of cal_fault_duty_param.responsibility_attribution
	 * @mbg.generated
	 */
	public String getResponsibility_attribution() {
		return responsibility_attribution;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_fault_duty_param.responsibility_attribution
	 * @param responsibility_attribution  the value for cal_fault_duty_param.responsibility_attribution
	 * @mbg.generated
	 */
	public void setResponsibility_attribution(String responsibility_attribution) {
		this.responsibility_attribution = responsibility_attribution == null ? null : responsibility_attribution.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_fault_duty_param.responsibility_type
	 * @return  the value of cal_fault_duty_param.responsibility_type
	 * @mbg.generated
	 */
	public String getResponsibility_type() {
		return responsibility_type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_fault_duty_param.responsibility_type
	 * @param responsibility_type  the value for cal_fault_duty_param.responsibility_type
	 * @mbg.generated
	 */
	public void setResponsibility_type(String responsibility_type) {
		this.responsibility_type = responsibility_type == null ? null : responsibility_type.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cal_fault_duty_param.enable_flag
	 * @return  the value of cal_fault_duty_param.enable_flag
	 * @mbg.generated
	 */
	public String getEnable_flag() {
		return enable_flag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cal_fault_duty_param.enable_flag
	 * @param enable_flag  the value for cal_fault_duty_param.enable_flag
	 * @mbg.generated
	 */
	public void setEnable_flag(String enable_flag) {
		this.enable_flag = enable_flag == null ? null : enable_flag.trim();
	}
}