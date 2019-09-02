package com.zxtech.ess.module.api.service;

import com.zxtech.ess.module.api.bean.Result;

import net.sf.json.JSONObject;

public interface HRService {

	Result syncStaffInfo(JSONObject param) throws Exception;
	
	Result syncStaffTrainInfo(JSONObject param) throws Exception;
	
	Result syncStaffCertificateInfo(JSONObject param) throws Exception;
	
	void syncStaffGetTmEmpInfo(JSONObject param) throws Exception;
}
