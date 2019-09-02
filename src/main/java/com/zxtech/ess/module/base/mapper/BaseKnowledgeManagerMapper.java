package com.zxtech.ess.module.base.mapper;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.base.bean.BaseKnowledgeSearchBean;
import com.zxtech.ess.module.gen.mapper.BaseDictionaryMapper;
import com.zxtech.ess.module.gen.mapper.BaseKnowledgeMapper;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface BaseKnowledgeManagerMapper {

	@CacheSignatureMapper({ BaseKnowledgeMapper.class, BaseDictionaryMapper.class })
	List<Map<String, Object>> getListWithPaging(BaseKnowledgeSearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseKnowledgeMapper.class, BaseDictionaryMapper.class })
	List<Map<String, Object>> getListWithPagingPart(BaseKnowledgeSearchBean queryInfo);
}
