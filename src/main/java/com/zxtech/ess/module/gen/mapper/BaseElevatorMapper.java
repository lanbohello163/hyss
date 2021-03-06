package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.BaseElevator;
import java.util.List;

public interface BaseElevatorMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_elevator
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_elevator
	 * @mbg.generated
	 */
	int insert(BaseElevator record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_elevator
	 * @mbg.generated
	 */
	int insertSelective(BaseElevator record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_elevator
	 * @mbg.generated
	 */
	BaseElevator selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_elevator
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(BaseElevator record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_elevator
	 * @mbg.generated
	 */
	int updateByPrimaryKey(BaseElevator record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_elevator
	 * @mbg.generated
	 */
	List<BaseElevator> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_elevator
	 * @mbg.generated
	 */
	int deleteBySqlConditions(BaseElevator record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_elevator
	 * @mbg.generated
	 */
	List<BaseElevator> selectBySqlConditions(BaseElevator record);
}