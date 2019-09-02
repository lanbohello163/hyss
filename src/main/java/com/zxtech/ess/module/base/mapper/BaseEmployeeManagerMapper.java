package com.zxtech.ess.module.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zxtech.ess.module.base.bean.BaseEmployeeSearchBean;
import com.zxtech.ess.module.gen.bean.BaseEmployee;
import com.zxtech.ess.module.gen.mapper.BaseAreaMapper;
import com.zxtech.ess.module.gen.mapper.BaseCompSyncMapper;
import com.zxtech.ess.module.gen.mapper.BaseCompanyMapper;
import com.zxtech.ess.module.gen.mapper.BaseEmployeeMapper;
import com.zxtech.ess.module.gen.mapper.BaseGroupMapper;
import com.zxtech.ess.module.gen.mapper.BaseStationMapper;
import com.zxtech.ess.module.gen.mapper.ChRegularCheckEmpMapper;
import com.zxtech.ess.module.gen.mapper.SysDictionaryMapper;
import com.zxtech.ess.module.gen.mapper.SysUserMapper;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface BaseEmployeeManagerMapper {

	@CacheSignatureMapper({ BaseAreaMapper.class, BaseCompanyMapper.class, BaseGroupMapper.class, 
		BaseStationMapper.class, BaseEmployeeMapper.class, BaseCompSyncMapper.class })
	List<Map<String, Object>> empList(BaseEmployeeSearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseEmployeeMapper.class, ChRegularCheckEmpMapper.class, BaseStationMapper.class })
	List<Map<String, Object>> regularCheckEmpPageList(BaseEmployeeSearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseEmployeeMapper.class, ChRegularCheckEmpMapper.class, BaseStationMapper.class })
	List<Map<String, Object>> coordinatorPageList(BaseEmployeeSearchBean queryInfo);

	String initEmpTypeSeq();
	
	@CacheSignatureMapper({ BaseEmployeeMapper.class })
	List<Map<String, Object>> checkPersonList(BaseEmployeeSearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseEmployeeMapper.class })
	List<Map<String, Object>> rectifyPersonList(BaseEmployeeSearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseEmployeeMapper.class })
	List<Map<String, Object>> guaranteePersonlist(BaseEmployeeSearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseEmployeeMapper.class, BaseCompanyMapper.class, BaseStationMapper.class,
			BaseAreaMapper.class, SysDictionaryMapper.class })
	List<Map<String, Object>> gyrusCheckEmpPageList(BaseEmployeeSearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseEmployeeMapper.class, SysUserMapper.class })
	BaseEmployee getSysUserInfoById(@Param("id") Integer id);
	
	@CacheSignatureMapper({ BaseEmployeeMapper.class })
	List<Map<String, Object>> gyrusEmpList(BaseEmployeeSearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseEmployeeMapper.class, BaseCompanyMapper.class, BaseStationMapper.class,
			BaseAreaMapper.class, SysDictionaryMapper.class })
	List<Map<String, Object>> gyrusCheckPersonList(BaseEmployeeSearchBean queryInfo);

	@CacheSignatureMapper({ BaseEmployeeMapper.class })
	List<Map<String, Object>> getCasualCheckRectificationPersionList(BaseEmployeeSearchBean queryInfo);

	List<Map<String, Object>> getReportList(BaseEmployeeSearchBean queryInfo);
}
