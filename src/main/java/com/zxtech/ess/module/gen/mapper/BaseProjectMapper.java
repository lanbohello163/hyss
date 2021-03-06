package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.BaseProject;
import java.util.List;

public interface BaseProjectMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_project
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_project
	 * @mbg.generated
	 */
	int insert(BaseProject record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_project
	 * @mbg.generated
	 */
	int insertSelective(BaseProject record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_project
	 * @mbg.generated
	 */
	BaseProject selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_project
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(BaseProject record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_project
	 * @mbg.generated
	 */
	int updateByPrimaryKey(BaseProject record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_project
	 * @mbg.generated
	 */
	List<BaseProject> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_project
	 * @mbg.generated
	 */
	int deleteBySqlConditions(BaseProject record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_project
	 * @mbg.generated
	 */
	List<BaseProject> selectBySqlConditions(BaseProject record);
}