package com.zxtech.platform.auth;

import java.util.HashMap;
import java.util.Map;

public class ResponseData {
	
	private final String message;
	private final int code;
	private final Map<String, Object> data = new HashMap<String, Object>();

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public ResponseData putDataValue(String key, Object value) {
		data.put(key, value);
		return this;
	}

	private ResponseData(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public static ResponseData ok() {
		return new ResponseData(200, "Ok.");
	}

	public static ResponseData badRequest() {
		return new ResponseData(400, "Fail.");
	}

	public static ResponseData unauthorized() {
		return new ResponseData(401, "No authority.");
	}
	
	public static ResponseData forbidden() {
		return new ResponseData(403, "Request forbidden.");
	}
	
	public static ResponseData notFound() {
		return new ResponseData(404, "Not found.");
	}

	public static ResponseData serverInternalError() {
		return new ResponseData(500, "Server internal error.");
	}

	public static ResponseData jwtExpireError() {
		return new ResponseData(498, "Timeout.");
	}
	
	public static ResponseData otherDevicesLogin() {
		return new ResponseData(498, "Other Devices Login.");
	}
}
