package com.zxtech.ess.module.pub.bean;

import com.zxtech.platform.utils.page.PageParameter;

public class PubSearchBean extends PageParameter{
	
	private static final long serialVersionUID = 1L;
	
	private String sequence_order_type;//服务请求类型
	
	private String proj_code;//地盘编码
	
	private String proj_name;//地盘名称
	
	private String comp_id;//所属司id
	
	private String stat_id;//所属站id

	public String getSequence_order_type() {
		return sequence_order_type;
	}

	public void setSequence_order_type(String sequence_order_type) {
		this.sequence_order_type = sequence_order_type;
	}

	public String getProj_code() {
		return proj_code;
	}

	public void setProj_code(String proj_code) {
		this.proj_code = proj_code;
	}

	public String getProj_name() {
		return proj_name;
	}

	public void setProj_name(String proj_name) {
		this.proj_name = proj_name;
	}

	public String getComp_id() {
		return comp_id;
	}

	public void setComp_id(String comp_id) {
		this.comp_id = comp_id;
	}

	public String getStat_id() {
		return stat_id;
	}

	public void setStat_id(String stat_id) {
		this.stat_id = stat_id;
	}
}
