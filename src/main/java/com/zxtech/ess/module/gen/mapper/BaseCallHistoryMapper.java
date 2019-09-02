package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.BaseCallHistory;
import java.util.List;

public interface BaseCallHistoryMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_call_history
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_call_history
	 * @mbg.generated
	 */
	int insert(BaseCallHistory record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_call_history
	 * @mbg.generated
	 */
	int insertSelective(BaseCallHistory record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_call_history
	 * @mbg.generated
	 */
	BaseCallHistory selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_call_history
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(BaseCallHistory record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_call_history
	 * @mbg.generated
	 */
	int updateByPrimaryKey(BaseCallHistory record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_call_history
	 * @mbg.generated
	 */
	List<BaseCallHistory> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_call_history
	 * @mbg.generated
	 */
	int deleteBySqlConditions(BaseCallHistory record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_call_history
	 * @mbg.generated
	 */
	List<BaseCallHistory> selectBySqlConditions(BaseCallHistory record);
}