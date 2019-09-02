package com.zxtech.ess.module.sys.bean;

import java.util.List;

import com.zxtech.ess.module.gen.bean.SysUserPromptRel;
import com.zxtech.platform.utils.page.PageParameter;

public class SysUserPromptSearchBean extends PageParameter {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer user_id;
	
	private Integer role_id;
	
	private String sso_code;
	
	private String prompt_type;
	
	private String prompt_url;
	
	private String emp_code;
	
	private Integer index;
	
	private List<SysUserPromptRel> promptList;

	public String getSso_code() {
		return sso_code;
	}

	public void setSso_code(String sso_code) {
		this.sso_code = sso_code;
	}

	public String getPrompt_type() {
		return prompt_type;
	}

	public void setPrompt_type(String prompt_type) {
		this.prompt_type = prompt_type;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public List<SysUserPromptRel> getPromptList() {
		return promptList;
	}

	public void setPromptList(List<SysUserPromptRel> promptList) {
		this.promptList = promptList;
	}

	public String getPrompt_url() {
		return prompt_url;
	}

	public void setPrompt_url(String prompt_url) {
		this.prompt_url = prompt_url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public String getEmp_code() {
		return emp_code;
	}

	public void setEmp_code(String emp_code) {
		this.emp_code = emp_code;
	}
}