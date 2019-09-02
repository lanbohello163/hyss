package com.zxtech.ess.module.base.mapper;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.base.bean.BaseGroupSearchBean;
import com.zxtech.ess.module.gen.mapper.BaseAreaMapper;
import com.zxtech.ess.module.gen.mapper.BaseCompanyMapper;
import com.zxtech.ess.module.gen.mapper.BaseDictionaryMapper;
import com.zxtech.ess.module.gen.mapper.BaseEmployeeMapper;
import com.zxtech.ess.module.gen.mapper.BaseGroupMapper;
import com.zxtech.ess.module.gen.mapper.BaseStationMapper;
import com.zxtech.ess.module.gen.mapper.SysDictionaryMapper;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface BaseGroupManagerMapper {

	@CacheSignatureMapper({BaseCompanyMapper.class, BaseStationMapper.class, BaseAreaMapper.class , 
		BaseGroupMapper.class, SysDictionaryMapper.class ,  BaseEmployeeMapper.class, BaseDictionaryMapper.class})
	List<Map<String, Object>> baseGroupPagingList(BaseGroupSearchBean bean);

	@CacheSignatureMapper({BaseGroupMapper.class})
	List<Map<String, Object>> groupList(BaseGroupSearchBean queryInfo);

	List<Map<String, Object>> getReportList(BaseGroupSearchBean queryInfo);
}
