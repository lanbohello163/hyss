package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.SysUserPrompt;
import java.util.List;

public interface SysUserPromptMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user_prompt
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user_prompt
	 * @mbg.generated
	 */
	int insert(SysUserPrompt record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user_prompt
	 * @mbg.generated
	 */
	int insertSelective(SysUserPrompt record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user_prompt
	 * @mbg.generated
	 */
	SysUserPrompt selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user_prompt
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(SysUserPrompt record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user_prompt
	 * @mbg.generated
	 */
	int updateByPrimaryKey(SysUserPrompt record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user_prompt
	 * @mbg.generated
	 */
	List<SysUserPrompt> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user_prompt
	 * @mbg.generated
	 */
	int deleteBySqlConditions(SysUserPrompt record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user_prompt
	 * @mbg.generated
	 */
	List<SysUserPrompt> selectBySqlConditions(SysUserPrompt record);
}