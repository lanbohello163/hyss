package com.zxtech.ess.module.base.mapper;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.base.bean.BaseDictionarySearchBean;
import com.zxtech.ess.module.gen.bean.BaseDictionary;
import com.zxtech.ess.module.gen.mapper.BaseDictionaryMapper;
import com.zxtech.ess.module.gen.mapper.SysDictionaryMapper;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface BaseDictionaryManagerMapper {

	@CacheSignatureMapper({ BaseDictionaryMapper.class })
	List<Map<String, Object>> fetchBaseDictionaryComboboxList(BaseDictionarySearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseDictionaryMapper.class, SysDictionaryMapper.class })
	List<Map<String, Object>> getListWithPaging(BaseDictionarySearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseDictionaryMapper.class })
	List<Map<String, Object>> getPDictType();
	
	@CacheSignatureMapper({ BaseDictionaryMapper.class })
	String getChinaListByType(BaseDictionary bean);
	
	@CacheSignatureMapper({ BaseDictionaryMapper.class, SysDictionaryMapper.class })
	List<Map<String, Object>> getContractListWithPaging(BaseDictionarySearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseDictionaryMapper.class, SysDictionaryMapper.class })
	List<Map<String, Object>> getHotLineListWithPaging(BaseDictionarySearchBean queryInfo);
}
