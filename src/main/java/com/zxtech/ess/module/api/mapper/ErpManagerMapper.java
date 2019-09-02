package com.zxtech.ess.module.api.mapper;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.gen.mapper.BaseCompSyncMapper;
import com.zxtech.ess.module.gen.mapper.BaseCompanyMapper;
import com.zxtech.ess.module.gen.mapper.BaseStatSyncMapper;
import com.zxtech.ess.module.gen.mapper.BaseStationMapper;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface ErpManagerMapper {

	@CacheSignatureMapper({ BaseStationMapper.class,BaseStatSyncMapper.class })
	List<Map<String, Object>> getStatIdByErpStatId(String erp_stat_id);
	
	@CacheSignatureMapper({ BaseStationMapper.class,BaseStatSyncMapper.class })
	List<Map<String, Object>> erpStationSelectAll();
	
	@CacheSignatureMapper({ BaseCompanyMapper.class,BaseCompSyncMapper.class })
	List<Map<String, Object>> erpCompanySelectAll();
}
