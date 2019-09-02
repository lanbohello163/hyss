package com.zxtech.ess.module.sys.mapper;

import java.util.List;
import java.util.Map;
import com.zxtech.ess.module.gen.mapper.LogDataMapper;
import com.zxtech.ess.module.gen.mapper.SysUserMapper;
import com.zxtech.ess.module.sys.bean.SysLogSearchBean;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface SysLogManagerMapper {
	
	
	@CacheSignatureMapper({ SysUserMapper.class, LogDataMapper.class })
	List<Map<String, Object>> sysLogList(SysLogSearchBean queryInfo);
	
	@CacheSignatureMapper({ SysUserMapper.class, LogDataMapper.class })
	Long contractExportListCount(SysLogSearchBean queryInfo);
	
	@CacheSignatureMapper({ SysUserMapper.class, LogDataMapper.class })
	List<Map<String, Object>> contractExportList(SysLogSearchBean queryInfo);
}
