package com.zxtech.ess.module.sys.bean;

import com.zxtech.platform.utils.page.PageParameter;

/*
 * @version 3.0
 */
public class SysLogSearchBean extends PageParameter {

	private static final long serialVersionUID = 1L;
	private String opt_person;
	private String opt_behavior;
	private String begin_time;
	private String end_time;
	
	public String getOpt_person() {
		return opt_person;
	}
	public void setOpt_person(String opt_person) {
		this.opt_person = opt_person;
	}
	public String getOpt_behavior() {
		return opt_behavior;
	}
	public void setOpt_behavior(String opt_behavior) {
		this.opt_behavior = opt_behavior;
	}
	public String getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

}	