package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.CalMuiltFaultElevTemp;
import java.util.List;

public interface CalMuiltFaultElevTempMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_muilt_fault_elev_temp
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_muilt_fault_elev_temp
	 * @mbg.generated
	 */
	int insert(CalMuiltFaultElevTemp record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_muilt_fault_elev_temp
	 * @mbg.generated
	 */
	int insertSelective(CalMuiltFaultElevTemp record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_muilt_fault_elev_temp
	 * @mbg.generated
	 */
	CalMuiltFaultElevTemp selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_muilt_fault_elev_temp
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(CalMuiltFaultElevTemp record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_muilt_fault_elev_temp
	 * @mbg.generated
	 */
	int updateByPrimaryKey(CalMuiltFaultElevTemp record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_muilt_fault_elev_temp
	 * @mbg.generated
	 */
	List<CalMuiltFaultElevTemp> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_muilt_fault_elev_temp
	 * @mbg.generated
	 */
	int deleteBySqlConditions(CalMuiltFaultElevTemp record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_muilt_fault_elev_temp
	 * @mbg.generated
	 */
	List<CalMuiltFaultElevTemp> selectBySqlConditions(CalMuiltFaultElevTemp record);
}