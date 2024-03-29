package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.CalServiceMulti;
import java.util.List;

public interface CalServiceMultiMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_service_multi
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_service_multi
     *
     * @mbg.generated
     */
    int insert(CalServiceMulti record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_service_multi
     *
     * @mbg.generated
     */
    int insertSelective(CalServiceMulti record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_service_multi
     *
     * @mbg.generated
     */
    CalServiceMulti selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_service_multi
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CalServiceMulti record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_service_multi
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CalServiceMulti record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_service_multi
     *
     * @mbg.generated
     */
    List<CalServiceMulti> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_service_multi
     *
     * @mbg.generated
     */
    int deleteBySqlConditions(CalServiceMulti record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cal_service_multi
     *
     * @mbg.generated
     */
    List<CalServiceMulti> selectBySqlConditions(CalServiceMulti record);
}