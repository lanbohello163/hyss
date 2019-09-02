package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.MtMaintenanceFactorExpression;
import java.util.List;

public interface MtMaintenanceFactorExpressionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mt_maintenance_factor_expression
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mt_maintenance_factor_expression
     *
     * @mbg.generated
     */
    int insert(MtMaintenanceFactorExpression record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mt_maintenance_factor_expression
     *
     * @mbg.generated
     */
    int insertSelective(MtMaintenanceFactorExpression record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mt_maintenance_factor_expression
     *
     * @mbg.generated
     */
    MtMaintenanceFactorExpression selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mt_maintenance_factor_expression
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MtMaintenanceFactorExpression record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mt_maintenance_factor_expression
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MtMaintenanceFactorExpression record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mt_maintenance_factor_expression
     *
     * @mbg.generated
     */
    List<MtMaintenanceFactorExpression> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mt_maintenance_factor_expression
     *
     * @mbg.generated
     */
    int deleteBySqlConditions(MtMaintenanceFactorExpression record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mt_maintenance_factor_expression
     *
     * @mbg.generated
     */
    List<MtMaintenanceFactorExpression> selectBySqlConditions(MtMaintenanceFactorExpression record);
}