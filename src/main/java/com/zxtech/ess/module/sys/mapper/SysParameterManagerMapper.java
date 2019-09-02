package com.zxtech.ess.module.sys.mapper;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.gen.mapper.SysParameterMapper;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface SysParameterManagerMapper {
	
	@CacheSignatureMapper({ SysParameterMapper.class })
	List<Map<String, Object>> sysParameterPagingList();
}
