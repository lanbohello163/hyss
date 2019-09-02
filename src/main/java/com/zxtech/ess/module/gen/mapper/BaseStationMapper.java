package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.BaseStation;
import java.util.List;

public interface BaseStationMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_station
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_station
	 * @mbg.generated
	 */
	int insert(BaseStation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_station
	 * @mbg.generated
	 */
	int insertSelective(BaseStation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_station
	 * @mbg.generated
	 */
	BaseStation selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_station
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(BaseStation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_station
	 * @mbg.generated
	 */
	int updateByPrimaryKey(BaseStation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_station
	 * @mbg.generated
	 */
	List<BaseStation> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_station
	 * @mbg.generated
	 */
	int deleteBySqlConditions(BaseStation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_station
	 * @mbg.generated
	 */
	List<BaseStation> selectBySqlConditions(BaseStation record);
}