package com.zxtech.ess.module.gen.bean;

import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table sys_user_prompt_rel
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class SysUserPromptRel implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_user_prompt_rel.id
	 * @mbg.generated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_user_prompt_rel.user_id
	 * @mbg.generated
	 */
	private Integer user_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_user_prompt_rel.prompt_id
	 * @mbg.generated
	 */
	private Integer prompt_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table sys_user_prompt_rel
	 * @mbg.generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_user_prompt_rel.id
	 * @return  the value of sys_user_prompt_rel.id
	 * @mbg.generated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_user_prompt_rel.id
	 * @param id  the value for sys_user_prompt_rel.id
	 * @mbg.generated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_user_prompt_rel.user_id
	 * @return  the value of sys_user_prompt_rel.user_id
	 * @mbg.generated
	 */
	public Integer getUser_id() {
		return user_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_user_prompt_rel.user_id
	 * @param user_id  the value for sys_user_prompt_rel.user_id
	 * @mbg.generated
	 */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_user_prompt_rel.prompt_id
	 * @return  the value of sys_user_prompt_rel.prompt_id
	 * @mbg.generated
	 */
	public Integer getPrompt_id() {
		return prompt_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_user_prompt_rel.prompt_id
	 * @param prompt_id  the value for sys_user_prompt_rel.prompt_id
	 * @mbg.generated
	 */
	public void setPrompt_id(Integer prompt_id) {
		this.prompt_id = prompt_id;
	}
}