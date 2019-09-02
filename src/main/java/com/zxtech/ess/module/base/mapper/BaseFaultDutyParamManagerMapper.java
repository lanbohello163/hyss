package com.zxtech.ess.module.base.mapper;

import java.util.List;
import java.util.Map;
import com.zxtech.ess.module.base.bean.BaseFaultDutyParamSearchBean;
import com.zxtech.ess.module.gen.mapper.CalFaultDutyParamMapper;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface BaseFaultDutyParamManagerMapper {

	@CacheSignatureMapper({ CalFaultDutyParamMapper.class })
	List<Map<String, Object>> getListWithPaging(BaseFaultDutyParamSearchBean queryInfo);
	
}
