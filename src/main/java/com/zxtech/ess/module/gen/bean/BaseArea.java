package com.zxtech.ess.module.gen.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table base_area
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class BaseArea implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_area.id
	 * @mbg.generated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_area.area_code
	 * @mbg.generated
	 */
	private String area_code;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_area.area_name
	 * @mbg.generated
	 */
	private String area_name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_area.area_manager
	 * @mbg.generated
	 */
	private String area_manager;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_area.comp_id
	 * @mbg.generated
	 */
	private Integer comp_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_area.stat_id
	 * @mbg.generated
	 */
	private Integer stat_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_area.mgr_area
	 * @mbg.generated
	 */
	private String mgr_area;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_area.area_remark
	 * @mbg.generated
	 */
	private String area_remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_area.enable_flag
	 * @mbg.generated
	 */
	private String enable_flag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_area.create_user
	 * @mbg.generated
	 */
	private String create_user;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_area.create_timestamp
	 * @mbg.generated
	 */
	private Timestamp create_timestamp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_area.last_update_user
	 * @mbg.generated
	 */
	private String last_update_user;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_area.last_update_timestamp
	 * @mbg.generated
	 */
	private Timestamp last_update_timestamp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_area.last_update_remark
	 * @mbg.generated
	 */
	private String last_update_remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_area.sync_datetime
	 * @mbg.generated
	 */
	private Timestamp sync_datetime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column base_area.source_id
	 * @mbg.generated
	 */
	private String source_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table base_area
	 * @mbg.generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_area.id
	 * @return  the value of base_area.id
	 * @mbg.generated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_area.id
	 * @param id  the value for base_area.id
	 * @mbg.generated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_area.area_code
	 * @return  the value of base_area.area_code
	 * @mbg.generated
	 */
	public String getArea_code() {
		return area_code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_area.area_code
	 * @param area_code  the value for base_area.area_code
	 * @mbg.generated
	 */
	public void setArea_code(String area_code) {
		this.area_code = area_code == null ? null : area_code.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_area.area_name
	 * @return  the value of base_area.area_name
	 * @mbg.generated
	 */
	public String getArea_name() {
		return area_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_area.area_name
	 * @param area_name  the value for base_area.area_name
	 * @mbg.generated
	 */
	public void setArea_name(String area_name) {
		this.area_name = area_name == null ? null : area_name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_area.area_manager
	 * @return  the value of base_area.area_manager
	 * @mbg.generated
	 */
	public String getArea_manager() {
		return area_manager;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_area.area_manager
	 * @param area_manager  the value for base_area.area_manager
	 * @mbg.generated
	 */
	public void setArea_manager(String area_manager) {
		this.area_manager = area_manager == null ? null : area_manager.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_area.comp_id
	 * @return  the value of base_area.comp_id
	 * @mbg.generated
	 */
	public Integer getComp_id() {
		return comp_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_area.comp_id
	 * @param comp_id  the value for base_area.comp_id
	 * @mbg.generated
	 */
	public void setComp_id(Integer comp_id) {
		this.comp_id = comp_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_area.stat_id
	 * @return  the value of base_area.stat_id
	 * @mbg.generated
	 */
	public Integer getStat_id() {
		return stat_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_area.stat_id
	 * @param stat_id  the value for base_area.stat_id
	 * @mbg.generated
	 */
	public void setStat_id(Integer stat_id) {
		this.stat_id = stat_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_area.mgr_area
	 * @return  the value of base_area.mgr_area
	 * @mbg.generated
	 */
	public String getMgr_area() {
		return mgr_area;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_area.mgr_area
	 * @param mgr_area  the value for base_area.mgr_area
	 * @mbg.generated
	 */
	public void setMgr_area(String mgr_area) {
		this.mgr_area = mgr_area == null ? null : mgr_area.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_area.area_remark
	 * @return  the value of base_area.area_remark
	 * @mbg.generated
	 */
	public String getArea_remark() {
		return area_remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_area.area_remark
	 * @param area_remark  the value for base_area.area_remark
	 * @mbg.generated
	 */
	public void setArea_remark(String area_remark) {
		this.area_remark = area_remark == null ? null : area_remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_area.enable_flag
	 * @return  the value of base_area.enable_flag
	 * @mbg.generated
	 */
	public String getEnable_flag() {
		return enable_flag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_area.enable_flag
	 * @param enable_flag  the value for base_area.enable_flag
	 * @mbg.generated
	 */
	public void setEnable_flag(String enable_flag) {
		this.enable_flag = enable_flag == null ? null : enable_flag.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_area.create_user
	 * @return  the value of base_area.create_user
	 * @mbg.generated
	 */
	public String getCreate_user() {
		return create_user;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_area.create_user
	 * @param create_user  the value for base_area.create_user
	 * @mbg.generated
	 */
	public void setCreate_user(String create_user) {
		this.create_user = create_user == null ? null : create_user.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_area.create_timestamp
	 * @return  the value of base_area.create_timestamp
	 * @mbg.generated
	 */
	public Timestamp getCreate_timestamp() {
		return create_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_area.create_timestamp
	 * @param create_timestamp  the value for base_area.create_timestamp
	 * @mbg.generated
	 */
	public void setCreate_timestamp(Timestamp create_timestamp) {
		this.create_timestamp = create_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_area.last_update_user
	 * @return  the value of base_area.last_update_user
	 * @mbg.generated
	 */
	public String getLast_update_user() {
		return last_update_user;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_area.last_update_user
	 * @param last_update_user  the value for base_area.last_update_user
	 * @mbg.generated
	 */
	public void setLast_update_user(String last_update_user) {
		this.last_update_user = last_update_user == null ? null : last_update_user.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_area.last_update_timestamp
	 * @return  the value of base_area.last_update_timestamp
	 * @mbg.generated
	 */
	public Timestamp getLast_update_timestamp() {
		return last_update_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_area.last_update_timestamp
	 * @param last_update_timestamp  the value for base_area.last_update_timestamp
	 * @mbg.generated
	 */
	public void setLast_update_timestamp(Timestamp last_update_timestamp) {
		this.last_update_timestamp = last_update_timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_area.last_update_remark
	 * @return  the value of base_area.last_update_remark
	 * @mbg.generated
	 */
	public String getLast_update_remark() {
		return last_update_remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_area.last_update_remark
	 * @param last_update_remark  the value for base_area.last_update_remark
	 * @mbg.generated
	 */
	public void setLast_update_remark(String last_update_remark) {
		this.last_update_remark = last_update_remark == null ? null : last_update_remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_area.sync_datetime
	 * @return  the value of base_area.sync_datetime
	 * @mbg.generated
	 */
	public Timestamp getSync_datetime() {
		return sync_datetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_area.sync_datetime
	 * @param sync_datetime  the value for base_area.sync_datetime
	 * @mbg.generated
	 */
	public void setSync_datetime(Timestamp sync_datetime) {
		this.sync_datetime = sync_datetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column base_area.source_id
	 * @return  the value of base_area.source_id
	 * @mbg.generated
	 */
	public String getSource_id() {
		return source_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column base_area.source_id
	 * @param source_id  the value for base_area.source_id
	 * @mbg.generated
	 */
	public void setSource_id(String source_id) {
		this.source_id = source_id == null ? null : source_id.trim();
	}
}