package com.zxtech.ess.module.sys.bean;

import com.zxtech.platform.utils.page.PageParameter;

public class SysApiLogSearchBean extends PageParameter{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3422281199182227871L;

	private String api_name;
	
	private String results;

	private String error_log_information;
	
	private String handler;

	public String getApi_name() {
		return api_name;
	}

	public void setApi_name(String api_name) {
		this.api_name = api_name;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public String getError_log_information() {
		return error_log_information;
	}

	public void setError_log_information(String error_log_information) {
		this.error_log_information = error_log_information;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	
}
