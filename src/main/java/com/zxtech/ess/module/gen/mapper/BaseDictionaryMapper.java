package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.BaseDictionary;
import java.util.List;

public interface BaseDictionaryMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_dictionary
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_dictionary
	 * @mbg.generated
	 */
	int insert(BaseDictionary record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_dictionary
	 * @mbg.generated
	 */
	int insertSelective(BaseDictionary record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_dictionary
	 * @mbg.generated
	 */
	BaseDictionary selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_dictionary
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(BaseDictionary record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_dictionary
	 * @mbg.generated
	 */
	int updateByPrimaryKey(BaseDictionary record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_dictionary
	 * @mbg.generated
	 */
	List<BaseDictionary> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_dictionary
	 * @mbg.generated
	 */
	int deleteBySqlConditions(BaseDictionary record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_dictionary
	 * @mbg.generated
	 */
	List<BaseDictionary> selectBySqlConditions(BaseDictionary record);
}