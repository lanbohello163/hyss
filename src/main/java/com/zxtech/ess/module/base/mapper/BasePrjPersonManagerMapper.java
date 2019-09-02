package com.zxtech.ess.module.base.mapper;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.base.bean.BasePrjPersonSearchBean;
import com.zxtech.ess.module.gen.mapper.BaseProjectPersonMapper;
import com.zxtech.ess.module.gen.mapper.SysDictionaryMapper;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface BasePrjPersonManagerMapper {

	@CacheSignatureMapper({BaseProjectPersonMapper.class, SysDictionaryMapper.class})
	List<Map<String, Object>> basePrjPersonPagingList(BasePrjPersonSearchBean bean);
}
