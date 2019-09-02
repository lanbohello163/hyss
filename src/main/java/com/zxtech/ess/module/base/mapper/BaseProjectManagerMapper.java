package com.zxtech.ess.module.base.mapper;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.base.bean.BaseProjectSearchBean;
import com.zxtech.ess.module.gen.mapper.BaseCompanyMapper;
import com.zxtech.ess.module.gen.mapper.BaseDictionaryMapper;
import com.zxtech.ess.module.gen.mapper.BaseProjectMapper;
import com.zxtech.ess.module.gen.mapper.BaseStationMapper;
import com.zxtech.ess.module.gen.mapper.SysDictionaryMapper;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface BaseProjectManagerMapper {

	@CacheSignatureMapper({BaseProjectMapper.class, BaseCompanyMapper.class, BaseStationMapper.class,
		SysDictionaryMapper.class, BaseDictionaryMapper.class})
	List<Map<String, Object>> baseProjectPagingList(BaseProjectSearchBean bean);

	List<Map<String, Object>> getReportList(BaseProjectSearchBean queryInfo);
}
