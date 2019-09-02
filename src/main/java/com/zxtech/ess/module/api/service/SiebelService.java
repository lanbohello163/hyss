package com.zxtech.ess.module.api.service;

import java.util.List;

import com.zxtech.ess.module.api.bean.Result;
import com.zxtech.ess.module.gen.bean.BaseElevator;

import net.sf.json.JSONObject;

public interface SiebelService {
	
	Result syncGetCustomerInfo(JSONObject param) throws Exception;
	
	Result syncSiebelExternalFactoryEle(JSONObject param) throws Exception;
	
	Result syncSiebelGetDoubleDoorLayer(List<BaseElevator> doubleList) throws Exception ;
	
}