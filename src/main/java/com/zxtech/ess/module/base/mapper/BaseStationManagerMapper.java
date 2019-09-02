package com.zxtech.ess.module.base.mapper;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.base.bean.BaseStationSearchBean;
import com.zxtech.ess.module.gen.mapper.BaseAreaMapper;
import com.zxtech.ess.module.gen.mapper.BaseCompanyMapper;
import com.zxtech.ess.module.gen.mapper.BaseDeptMapper;
import com.zxtech.ess.module.gen.mapper.BaseDictionaryMapper;
import com.zxtech.ess.module.gen.mapper.BaseEmployeeMapper;
import com.zxtech.ess.module.gen.mapper.BaseStatSyncMapper;
import com.zxtech.ess.module.gen.mapper.BaseStationMapper;
import com.zxtech.ess.module.gen.mapper.SysDictionaryMapper;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface BaseStationManagerMapper {

	@CacheSignatureMapper({ BaseStationMapper.class ,BaseDeptMapper.class, SysDictionaryMapper.class, BaseCompanyMapper.class
		, BaseDictionaryMapper.class, BaseEmployeeMapper.class})
	List<Map<String, Object>> baseStationPagingList(BaseStationSearchBean bean);
	
	List<Map<String, Object>> getReportList(BaseStationSearchBean bean);

	@CacheSignatureMapper({ BaseAreaMapper.class, BaseCompanyMapper.class, BaseStationMapper.class })
	List<Map<String, Object>> statList(BaseStationSearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseAreaMapper.class, BaseCompanyMapper.class, BaseStationMapper.class })
	List<Map<String, Object>> statPartList(BaseStationSearchBean queryInfo);

	@CacheSignatureMapper({ BaseCompanyMapper.class, BaseStationMapper.class })
	List<Map<String, Object>> fetchWindowStatList(BaseStationSearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseStatSyncMapper.class })
	List<Map<String, Object>> statSyncByErpListPage(BaseStationSearchBean queryInfo);
}
