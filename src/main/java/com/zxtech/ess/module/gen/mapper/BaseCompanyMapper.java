package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.BaseCompany;
import java.util.List;

public interface BaseCompanyMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_company
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_company
	 * @mbg.generated
	 */
	int insert(BaseCompany record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_company
	 * @mbg.generated
	 */
	int insertSelective(BaseCompany record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_company
	 * @mbg.generated
	 */
	BaseCompany selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_company
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(BaseCompany record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_company
	 * @mbg.generated
	 */
	int updateByPrimaryKey(BaseCompany record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_company
	 * @mbg.generated
	 */
	List<BaseCompany> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_company
	 * @mbg.generated
	 */
	int deleteBySqlConditions(BaseCompany record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_company
	 * @mbg.generated
	 */
	List<BaseCompany> selectBySqlConditions(BaseCompany record);
}