package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.BaseCompSync;
import java.util.List;

public interface BaseCompSyncMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_comp_sync
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_comp_sync
	 * @mbg.generated
	 */
	int insert(BaseCompSync record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_comp_sync
	 * @mbg.generated
	 */
	int insertSelective(BaseCompSync record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_comp_sync
	 * @mbg.generated
	 */
	BaseCompSync selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_comp_sync
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(BaseCompSync record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_comp_sync
	 * @mbg.generated
	 */
	int updateByPrimaryKey(BaseCompSync record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_comp_sync
	 * @mbg.generated
	 */
	List<BaseCompSync> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_comp_sync
	 * @mbg.generated
	 */
	int deleteBySqlConditions(BaseCompSync record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_comp_sync
	 * @mbg.generated
	 */
	List<BaseCompSync> selectBySqlConditions(BaseCompSync record);
}