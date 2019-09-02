package com.zxtech.ess.module.hsync.service;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.hsync.bean.SyncHiotFetchElevatorSearchBean;

public interface ISyncHiotService {

	List<Map<String, Object>> comboboxBaseCompanyData();

	List<Map<String, Object>> comboboxBaseStationData(Integer comp_id);
	
	Map<String, Object> fetchBaseElevatorData(SyncHiotFetchElevatorSearchBean queryInfo);

}
