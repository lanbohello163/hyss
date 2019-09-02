package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.SysRole;
import java.util.List;

public interface SysRoleMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbg.generated
	 */
	int insert(SysRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbg.generated
	 */
	int insertSelective(SysRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbg.generated
	 */
	SysRole selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(SysRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbg.generated
	 */
	int updateByPrimaryKey(SysRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbg.generated
	 */
	List<SysRole> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbg.generated
	 */
	int deleteBySqlConditions(SysRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbg.generated
	 */
	List<SysRole> selectBySqlConditions(SysRole record);
}