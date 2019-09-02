package com.zxtech.ess.module.api.bean;

public class Result {

	private String rtnCode;
	
	private String rtnMsg;
	
	private Object rtnData;

	public String getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(String rtnCode) {
		this.rtnCode = rtnCode;
	}

	public String getRtnMsg() {
		return rtnMsg;
	}

	public void setRtnMsg(String rtnMsg) {
		this.rtnMsg = rtnMsg;
	}

	public Object getRtnData() {
		return rtnData;
	}

	public void setRtnData(Object rtnData) {
		this.rtnData = rtnData;
	}

	@Override
	public String toString() {
		return "Result [rtnCode=" + rtnCode + ", rtnMsg=" + rtnMsg + ", rtnData=" + rtnData + "]";
	}
	
	
}
