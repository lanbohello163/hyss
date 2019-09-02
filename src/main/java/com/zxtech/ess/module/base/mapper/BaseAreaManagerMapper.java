package com.zxtech.ess.module.base.mapper;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.base.bean.BaseAreaSearchBean;
import com.zxtech.ess.module.gen.mapper.BaseAreaMapper;
import com.zxtech.ess.module.gen.mapper.BaseCompanyMapper;
import com.zxtech.ess.module.gen.mapper.BaseEmployeeMapper;
import com.zxtech.ess.module.gen.mapper.BaseStationMapper;
import com.zxtech.ess.module.gen.mapper.SysDictionaryMapper;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface BaseAreaManagerMapper {

	@CacheSignatureMapper({BaseCompanyMapper.class, BaseStationMapper.class, BaseAreaMapper.class, 
		SysDictionaryMapper.class ,  BaseEmployeeMapper.class})
	List<Map<String, Object>> baseAreaPagingList(BaseAreaSearchBean bean);

	@CacheSignatureMapper({BaseCompanyMapper.class, BaseStationMapper.class, BaseAreaMapper.class})
	List<Map<String, Object>> fetchWindowAreaList(BaseAreaSearchBean queryInfo);

	List<Map<String, Object>> getReportList(BaseAreaSearchBean queryInfo);
}
