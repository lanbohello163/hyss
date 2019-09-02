package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.CalConsultProcess;
import java.util.List;

public interface CalConsultProcessMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_consult_process
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_consult_process
     *
     * @mbg.generated
     */
    int insert(CalConsultProcess record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_consult_process
     *
     * @mbg.generated
     */
    int insertSelective(CalConsultProcess record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_consult_process
     *
     * @mbg.generated
     */
    CalConsultProcess selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_consult_process
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CalConsultProcess record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_consult_process
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CalConsultProcess record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_consult_process
     *
     * @mbg.generated
     */
    List<CalConsultProcess> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_consult_process
     *
     * @mbg.generated
     */
    int deleteBySqlConditions(CalConsultProcess record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_consult_process
     *
     * @mbg.generated
     */
    List<CalConsultProcess> selectBySqlConditions(CalConsultProcess record);
}