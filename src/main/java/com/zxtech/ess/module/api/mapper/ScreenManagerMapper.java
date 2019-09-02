package com.zxtech.ess.module.api.mapper;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.gen.mapper.BaseCompSyncMapper;
import com.zxtech.ess.module.gen.mapper.BaseCompanyMapper;
import com.zxtech.ess.module.gen.mapper.BaseEmployeeMapper;
import com.zxtech.ess.module.gen.mapper.BaseStationMapper;
import com.zxtech.ess.module.gen.mapper.CalFaultSolveTaskMapper;
import com.zxtech.ess.module.gen.mapper.CalReturnCallMapper;
import com.zxtech.ess.module.gen.mapper.CalServiceRequestFormMapper;
import com.zxtech.ess.module.gen.mapper.PosEmpPositionMapper;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface ScreenManagerMapper {

	@CacheSignatureMapper({
		CalReturnCallMapper.class, CalServiceRequestFormMapper.class,
		BaseCompanyMapper.class,CalFaultSolveTaskMapper.class
		})
	List<Map<String, Object>> callBackList(String service_order);
	
	@CacheSignatureMapper({ PosEmpPositionMapper.class, BaseEmployeeMapper.class })
	List<Map<String, Object>> syncEmpPosition(Map<String, Object> paramMap);
	
	@CacheSignatureMapper({ 
		PosEmpPositionMapper.class, BaseEmployeeMapper.class,
		BaseCompanyMapper.class, BaseCompSyncMapper.class,
		BaseStationMapper.class
	})
	List<Map<String, Object>> syncEmpClock(Map<String, Object> paramMap);
}
