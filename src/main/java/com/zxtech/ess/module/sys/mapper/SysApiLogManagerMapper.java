package com.zxtech.ess.module.sys.mapper;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.gen.mapper.ApiErrorLogMapper;
import com.zxtech.ess.module.gen.mapper.ApiOperationLogMapper;
import com.zxtech.ess.module.sys.bean.SysApiLogSearchBean;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface SysApiLogManagerMapper {

	@CacheSignatureMapper({ ApiErrorLogMapper.class, ApiOperationLogMapper.class })
	List<Map<String, Object>> sysApiLogPagingList(SysApiLogSearchBean bean);
}
