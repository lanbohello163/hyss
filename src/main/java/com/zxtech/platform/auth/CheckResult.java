package com.zxtech.platform.auth;

import io.jsonwebtoken.Claims;

public class CheckResult {

	private boolean success;
	
	private Claims claims;
	
	private JWTErrorCode errCode;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Claims getClaims() {
		return claims;
	}

	public void setClaims(Claims claims) {
		this.claims = claims;
	}
	
	public JWTErrorCode getErrCode() {
		return errCode;
	}

	public void setErrCode(JWTErrorCode errCode) {
		this.errCode = errCode;
	}
	
}
