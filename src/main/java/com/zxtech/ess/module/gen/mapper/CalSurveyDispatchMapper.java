package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.CalSurveyDispatch;
import java.util.List;

public interface CalSurveyDispatchMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_survey_dispatch
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_survey_dispatch
	 * @mbg.generated
	 */
	int insert(CalSurveyDispatch record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_survey_dispatch
	 * @mbg.generated
	 */
	int insertSelective(CalSurveyDispatch record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_survey_dispatch
	 * @mbg.generated
	 */
	CalSurveyDispatch selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_survey_dispatch
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(CalSurveyDispatch record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_survey_dispatch
	 * @mbg.generated
	 */
	int updateByPrimaryKey(CalSurveyDispatch record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_survey_dispatch
	 * @mbg.generated
	 */
	List<CalSurveyDispatch> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_survey_dispatch
	 * @mbg.generated
	 */
	int deleteBySqlConditions(CalSurveyDispatch record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cal_survey_dispatch
	 * @mbg.generated
	 */
	List<CalSurveyDispatch> selectBySqlConditions(CalSurveyDispatch record);
}