package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.CalSurvey;
import java.util.List;

public interface CalSurveyMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_survey
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_survey
	 * @mbg.generated
	 */
	int insert(CalSurvey record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_survey
	 * @mbg.generated
	 */
	int insertSelective(CalSurvey record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_survey
	 * @mbg.generated
	 */
	CalSurvey selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_survey
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(CalSurvey record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_survey
	 * @mbg.generated
	 */
	int updateByPrimaryKey(CalSurvey record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_survey
	 * @mbg.generated
	 */
	List<CalSurvey> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_survey
	 * @mbg.generated
	 */
	int deleteBySqlConditions(CalSurvey record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_survey
	 * @mbg.generated
	 */
	List<CalSurvey> selectBySqlConditions(CalSurvey record);
}