package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.CalFaultAuditArea;
import java.util.List;

public interface CalFaultAuditAreaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_fault_audit_area
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_fault_audit_area
     *
     * @mbg.generated
     */
    int insert(CalFaultAuditArea record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_fault_audit_area
     *
     * @mbg.generated
     */
    int insertSelective(CalFaultAuditArea record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_fault_audit_area
     *
     * @mbg.generated
     */
    CalFaultAuditArea selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_fault_audit_area
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CalFaultAuditArea record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_fault_audit_area
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CalFaultAuditArea record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_fault_audit_area
     *
     * @mbg.generated
     */
    List<CalFaultAuditArea> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_fault_audit_area
     *
     * @mbg.generated
     */
    int deleteBySqlConditions(CalFaultAuditArea record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_fault_audit_area
     *
     * @mbg.generated
     */
    List<CalFaultAuditArea> selectBySqlConditions(CalFaultAuditArea record);
}