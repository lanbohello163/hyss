package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.CalSurveyMultiFault;
import java.util.List;

public interface CalSurveyMultiFaultMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_survey_multi_fault
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_survey_multi_fault
     *
     * @mbg.generated
     */
    int insert(CalSurveyMultiFault record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_survey_multi_fault
     *
     * @mbg.generated
     */
    int insertSelective(CalSurveyMultiFault record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_survey_multi_fault
     *
     * @mbg.generated
     */
    CalSurveyMultiFault selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_survey_multi_fault
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CalSurveyMultiFault record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_survey_multi_fault
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CalSurveyMultiFault record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_survey_multi_fault
     *
     * @mbg.generated
     */
    List<CalSurveyMultiFault> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_survey_multi_fault
     *
     * @mbg.generated
     */
    int deleteBySqlConditions(CalSurveyMultiFault record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_survey_multi_fault
     *
     * @mbg.generated
     */
    List<CalSurveyMultiFault> selectBySqlConditions(CalSurveyMultiFault record);
}