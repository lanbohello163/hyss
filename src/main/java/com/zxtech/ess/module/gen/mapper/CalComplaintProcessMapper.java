package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.CalComplaintProcess;
import java.util.List;

public interface CalComplaintProcessMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_complaint_process
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_complaint_process
     *
     * @mbg.generated
     */
    int insert(CalComplaintProcess record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_complaint_process
     *
     * @mbg.generated
     */
    int insertSelective(CalComplaintProcess record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_complaint_process
     *
     * @mbg.generated
     */
    CalComplaintProcess selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_complaint_process
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CalComplaintProcess record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_complaint_process
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CalComplaintProcess record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_complaint_process
     *
     * @mbg.generated
     */
    List<CalComplaintProcess> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_complaint_process
     *
     * @mbg.generated
     */
    int deleteBySqlConditions(CalComplaintProcess record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_complaint_process
     *
     * @mbg.generated
     */
    List<CalComplaintProcess> selectBySqlConditions(CalComplaintProcess record);
}