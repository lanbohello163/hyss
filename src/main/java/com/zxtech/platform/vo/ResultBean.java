package com.zxtech.platform.vo;

import com.zxtech.platform.constant.ResultConstants;

public class ResultBean {

	private String status = ResultConstants.SUCCESS_CODE;
	
	private Object data;
	
	private String msg = ResultConstants.SUCCESS;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "{status=" + status + ", msg=" + msg + ", data=" + data + "}";
	}
	
}
