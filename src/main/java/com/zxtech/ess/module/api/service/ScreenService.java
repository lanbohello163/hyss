package com.zxtech.ess.module.api.service;

import com.zxtech.ess.module.api.bean.Result;

import net.sf.json.JSONObject;

public interface ScreenService {
	
	Result syncEmpPosition(JSONObject param) throws Exception;
	
	Result syncEmpClock(JSONObject param) throws Exception;
	
}
