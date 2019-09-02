package com.zxtech.ess.module.hsync.mapper;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.hsync.bean.SyncHiotFetchElevatorSearchBean;

public interface SyncHiotMapper {

	List<Map<String, Object>> fetchBaseElevatorData(SyncHiotFetchElevatorSearchBean queryInfo);

}
