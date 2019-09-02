package com.zxtech.ess.module.pub.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zxtech.ess.module.base.bean.BaseElevatorSearchBean;
import com.zxtech.ess.module.gen.bean.BaseCompany;
import com.zxtech.ess.module.gen.bean.BaseElevator;
import com.zxtech.ess.module.gen.bean.BaseEmployee;
import com.zxtech.ess.module.gen.bean.BaseMachineType;
import com.zxtech.ess.module.gen.bean.SysDictionary;
import com.zxtech.ess.module.gen.bean.SysRole;
import com.zxtech.ess.module.gen.mapper.BaseAreaMapper;
import com.zxtech.ess.module.gen.mapper.BaseCompanyMapper;
import com.zxtech.ess.module.gen.mapper.BaseElevatorMapper;
import com.zxtech.ess.module.gen.mapper.BaseEmployeeMapper;
import com.zxtech.ess.module.gen.mapper.BaseGroupMapper;
import com.zxtech.ess.module.gen.mapper.BaseMachineTypeMapper;
import com.zxtech.ess.module.gen.mapper.BaseProjectMapper;
import com.zxtech.ess.module.gen.mapper.BasePropertyMapper;
import com.zxtech.ess.module.gen.mapper.BaseStationMapper;
import com.zxtech.ess.module.gen.mapper.MtContractDtlMapper;
import com.zxtech.ess.module.gen.mapper.QueryDefinitionMapper;
import com.zxtech.ess.module.gen.mapper.SysDictionaryMapper;
import com.zxtech.ess.module.gen.mapper.SysRoleMapper;
import com.zxtech.ess.module.gen.mapper.SysUserMapper;
import com.zxtech.ess.module.pub.bean.PubSearchBean;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;
import com.zxtech.platform.vo.UserBean;

public interface PubMapper {

	@CacheSignatureMapper({ SysRoleMapper.class })
	List<SysRole> fetchPublicSysRole();

	@CacheSignatureMapper({ BaseCompanyMapper.class })
	List<Map<String, Object>> getListByCompany(UserBean user);

	@CacheSignatureMapper({ BaseCompanyMapper.class })
	List<BaseCompany> getTreeListByCompany(UserBean user);

	@CacheSignatureMapper({ BaseStationMapper.class })
	List<BaseCompany> getTreeListByStat(@Param("compIdList") List<Integer> compIdList);

	@CacheSignatureMapper({ BaseAreaMapper.class })
	List<BaseCompany> getTreeListByArea(@Param("statIdList") List<Integer> statIdList);

	String getSequenceOrder();

	@CacheSignatureMapper({ BaseProjectMapper.class })
	List<Map<String, Object>> getCommonProjectList(PubSearchBean queryInfo);

	@CacheSignatureMapper({ QueryDefinitionMapper.class })
	List<Map<String, Object>> fetchQueryHelperComboboxList(@Param("query_url") String query_url,
			@Param("empCode") String empCode);

	@CacheSignatureMapper({ BaseEmployeeMapper.class, SysUserMapper.class })
	List<Map<String, Object>> getHotlineUserList(BaseEmployee baseEmployee);

	@CacheSignatureMapper({ BaseMachineTypeMapper.class })
	List<BaseMachineType> fetchPublicMachineType(BaseMachineType bean);

	@CacheSignatureMapper({ SysDictionaryMapper.class })
	List<SysDictionary> fetchSysDictionaryData(SysDictionary queryInfo);

	@CacheSignatureMapper({ BaseElevatorMapper.class, MtContractDtlMapper.class, BaseProjectMapper.class,
			BasePropertyMapper.class, BaseCompanyMapper.class, BaseStationMapper.class, BaseAreaMapper.class,
			BaseGroupMapper.class })
	List<Map<String, Object>> getCommonElevatorPagingList(BaseElevatorSearchBean queryInfo);
	
	@CacheSignatureMapper({ SysDictionaryMapper.class })
	List<SysDictionary> fetchSysDictionaryData_contract(SysDictionary queryInfo);
	
	@CacheSignatureMapper({ SysDictionaryMapper.class })
	List<SysDictionary> fetchSysDictionaryData_hotline(SysDictionary queryInfo);
	
	@CacheSignatureMapper({ SysDictionaryMapper.class })
	List<SysDictionary> fetchSysDictionaryData_maintenance(SysDictionary queryInfo);
	
}
