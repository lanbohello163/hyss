package com.zxtech.ess.module.api.mapper;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.gen.mapper.BaseCompSyncMapper;
import com.zxtech.ess.module.gen.mapper.BaseCompanyMapper;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface HRManagerMapper {

	@CacheSignatureMapper({ BaseCompanyMapper.class,BaseCompSyncMapper.class })
	List<Map<String, Object>> getCompIdListByCompCode();
	
}
