package com.zxtech.ess.module.api.service;

import com.zxtech.ess.module.api.bean.Result;

import net.sf.json.JSONObject;

public interface ErpService {
	
	Result syncGetContractHead(JSONObject param) throws Exception ;
	
	Result syncGetOutAndInstallInfo(JSONObject param) throws Exception ;
	
	void addFileAssetChange(int asset_id) throws Exception ;
	
}