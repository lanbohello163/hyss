package com.zxtech.ess.module.base.mapper;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.base.bean.BaseDeptSearchBean;
import com.zxtech.ess.module.gen.mapper.BaseCompanyMapper;
import com.zxtech.ess.module.gen.mapper.BaseDeptMapper;
import com.zxtech.ess.module.gen.mapper.BaseDictionaryMapper;
import com.zxtech.ess.module.gen.mapper.SysDictionaryMapper;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface BaseDeptManagerMapper {

	@CacheSignatureMapper({BaseDeptMapper.class, BaseCompanyMapper.class, BaseDictionaryMapper.class, SysDictionaryMapper.class})
	List<Map<String, Object>> getListWithPaging(BaseDeptSearchBean queryInfo);
	
	List<Map<String, Object>> getReportList(BaseDeptSearchBean queryInfo);
	
}
