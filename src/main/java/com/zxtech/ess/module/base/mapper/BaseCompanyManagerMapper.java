package com.zxtech.ess.module.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zxtech.ess.module.base.bean.BaseCompanySearchBean;
import com.zxtech.ess.module.base.bean.BaseUserCompanySearchBean;
import com.zxtech.ess.module.gen.bean.BaseCompany;
import com.zxtech.ess.module.gen.mapper.BaseAreaMapper;
import com.zxtech.ess.module.gen.mapper.BaseCompSyncMapper;
import com.zxtech.ess.module.gen.mapper.BaseCompanyMapper;
import com.zxtech.ess.module.gen.mapper.BaseDeptMapper;
import com.zxtech.ess.module.gen.mapper.BaseEmployeeMapper;
import com.zxtech.ess.module.gen.mapper.BaseGroupMapper;
import com.zxtech.ess.module.gen.mapper.BaseStationMapper;
import com.zxtech.ess.module.gen.mapper.SysUserMapper;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface BaseCompanyManagerMapper {

	@CacheSignatureMapper({ BaseCompanyMapper.class, BaseCompSyncMapper.class })
	List<Map<String, Object>> getCompanyList();
	
	List<Map<String, Object>> getReportList(BaseCompanySearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseDeptMapper.class })
	List<Map<String, Object>> getDeptList(String id);
	
	@CacheSignatureMapper({ BaseStationMapper.class })
	List<Map<String, Object>> getStationList(String id);
	
	@CacheSignatureMapper({ BaseAreaMapper.class })
	List<Map<String, Object>> getAreaList(String id);
	
	@CacheSignatureMapper({ BaseGroupMapper.class })
	List<Map<String, Object>> getGroupList(String id);
	
	@CacheSignatureMapper({ BaseAreaMapper.class, BaseCompanyMapper.class, BaseStationMapper.class })
	List<Map<String, Object>> compList(BaseCompanySearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseStationMapper.class, BaseCompanyMapper.class, BaseDeptMapper.class })
	List<Map<String, Object>> getDeptStdFar(String id);
	
	@CacheSignatureMapper({ BaseAreaMapper.class, BaseCompanyMapper.class, BaseStationMapper.class,BaseEmployeeMapper.class,SysUserMapper.class })
	List<Map<String, Object>> userCompList(BaseUserCompanySearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseCompanyMapper.class })
	List<Map<String, Object>> pareaList(BaseCompanySearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseCompanyMapper.class })
	List<Map<String, Object>> areaCompList(BaseCompanySearchBean queryInfo);

	@CacheSignatureMapper({ BaseCompSyncMapper.class })
	List<Map<String, Object>> compSyncByErpListPage(BaseCompanySearchBean queryInfo);

	@CacheSignatureMapper({ BaseCompanyMapper.class })
	List<Map<String, Object>> getUserCompanyListByDataid(List<Integer> idList);
	
	Integer fetchPAreaIdByCompId(@Param("p_comp_id") Integer p_comp_id);
	
	@CacheSignatureMapper({ BaseCompanyMapper.class })
	List<Integer> fetchAllPCompIdsByAreaId(@Param("p_comp_id") Integer p_comp_id);
	
	@CacheSignatureMapper({ BaseCompanyMapper.class })
	List<Map<String, Object>> getpareaidbycompid(BaseCompanySearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseCompanyMapper.class })
	List<BaseCompany> getCompInfoById(BaseCompany queryInfo);
	
	@CacheSignatureMapper({ BaseCompanyMapper.class })
	List<Map<String, Object>> getFirstLevelCompList(BaseCompanySearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseCompanyMapper.class })
	List<Map<String, Object>> getItsFirstLevelCompByCompId(BaseCompanySearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseCompanyMapper.class })
	BaseCompany getPCompInfoByPCompId(@Param("p_comp_id") Integer p_comp_id);

}
