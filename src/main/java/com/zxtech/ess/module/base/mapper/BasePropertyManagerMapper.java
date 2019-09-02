package com.zxtech.ess.module.base.mapper;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.base.bean.BasePropertySearchBean;
import com.zxtech.ess.module.gen.bean.BaseKeyProperty;
import com.zxtech.ess.module.gen.mapper.BaseKeyPropertyMapper;
import com.zxtech.ess.module.gen.mapper.BasePropertyMapper;
import com.zxtech.ess.module.gen.mapper.SysDictionaryMapper;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface BasePropertyManagerMapper {

	@CacheSignatureMapper({ SysDictionaryMapper.class, BasePropertyMapper.class,BaseKeyPropertyMapper.class})
	List<Map<String, Object>> propertyList(BasePropertySearchBean queryInfo);

	@CacheSignatureMapper({ SysDictionaryMapper.class, BasePropertyMapper.class,BaseKeyPropertyMapper.class })
	List<Map<String, Object>> getKeyPropertyCombobox(BaseKeyProperty bean);
	
	@CacheSignatureMapper({BaseKeyPropertyMapper.class})
	List<Map<String, Object>> getKeyPropertyNotCommonCombobox();
	
	@CacheSignatureMapper({ BasePropertyMapper.class})
	String initPropertyCode();

	List<Map<String, Object>> getReportList(BasePropertySearchBean queryInfo);
}
