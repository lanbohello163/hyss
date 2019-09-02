package com.zxtech.ess.module.base.mapper;

import java.util.List;
import java.util.Map;
import com.zxtech.ess.module.base.bean.BaseMachineTypeSearchBean;
import com.zxtech.ess.module.gen.mapper.BaseMachineTypeMapper;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface BaseMachineTypeManagerMapper {


	@CacheSignatureMapper({ BaseMachineTypeMapper.class})
	List<Map<String, Object>> getListWithPaging(BaseMachineTypeSearchBean queryInfo);
	
	
	@CacheSignatureMapper({ BaseMachineTypeMapper.class })
	List<Map<String, Object>> fetchBaseMachineTypeComboboxList(BaseMachineTypeSearchBean queryInfo);
	
	
	Long baseMachineTypeExportListCount(BaseMachineTypeSearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseMachineTypeMapper.class})
	List<Map<String, Object>> baseMachineTypeExportList(BaseMachineTypeSearchBean queryInfo);
	
}
