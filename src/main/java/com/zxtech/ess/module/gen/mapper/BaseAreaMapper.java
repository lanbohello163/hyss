package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.BaseArea;
import java.util.List;

public interface BaseAreaMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_area
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_area
	 * @mbg.generated
	 */
	int insert(BaseArea record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_area
	 * @mbg.generated
	 */
	int insertSelective(BaseArea record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_area
	 * @mbg.generated
	 */
	BaseArea selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_area
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(BaseArea record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_area
	 * @mbg.generated
	 */
	int updateByPrimaryKey(BaseArea record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_area
	 * @mbg.generated
	 */
	List<BaseArea> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_area
	 * @mbg.generated
	 */
	int deleteBySqlConditions(BaseArea record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_area
	 * @mbg.generated
	 */
	List<BaseArea> selectBySqlConditions(BaseArea record);
}