package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.CalSurveyAttach;
import java.util.List;

public interface CalSurveyAttachMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_survey_attach
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_survey_attach
     *
     * @mbg.generated
     */
    int insert(CalSurveyAttach record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_survey_attach
     *
     * @mbg.generated
     */
    int insertSelective(CalSurveyAttach record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_survey_attach
     *
     * @mbg.generated
     */
    CalSurveyAttach selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_survey_attach
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CalSurveyAttach record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_survey_attach
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CalSurveyAttach record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_survey_attach
     *
     * @mbg.generated
     */
    List<CalSurveyAttach> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_survey_attach
     *
     * @mbg.generated
     */
    int deleteBySqlConditions(CalSurveyAttach record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_survey_attach
     *
     * @mbg.generated
     */
    List<CalSurveyAttach> selectBySqlConditions(CalSurveyAttach record);
}