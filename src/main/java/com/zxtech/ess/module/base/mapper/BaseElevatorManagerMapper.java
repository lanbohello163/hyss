package com.zxtech.ess.module.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zxtech.ess.module.base.bean.BaseCasualElevatorSearchBean;
import com.zxtech.ess.module.base.bean.BaseElevatorSearchBean;
import com.zxtech.ess.module.gen.mapper.BaseAreaMapper;
import com.zxtech.ess.module.gen.mapper.BaseCompanyMapper;
import com.zxtech.ess.module.gen.mapper.BaseDictionaryMapper;
import com.zxtech.ess.module.gen.mapper.BaseElevatorMapper;
import com.zxtech.ess.module.gen.mapper.BaseGroupMapper;
import com.zxtech.ess.module.gen.mapper.BaseKeyPropertyMapper;
import com.zxtech.ess.module.gen.mapper.BaseProjectMapper;
import com.zxtech.ess.module.gen.mapper.BasePropertyMapper;
import com.zxtech.ess.module.gen.mapper.BasePropertyPersonMapper;
import com.zxtech.ess.module.gen.mapper.BaseStationMapper;
import com.zxtech.ess.module.gen.mapper.FileAssetChangeMapper;
import com.zxtech.ess.module.gen.mapper.MtContractDtlMapper;
import com.zxtech.ess.module.gen.mapper.MtContractMapper;
import com.zxtech.ess.module.gen.mapper.MtWorkPlanMapper;
import com.zxtech.ess.module.gen.mapper.SysDictionaryMapper;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface BaseElevatorManagerMapper {

	@CacheSignatureMapper({ BaseAreaMapper.class, BaseCompanyMapper.class, BaseGroupMapper.class,
			BaseStationMapper.class, SysDictionaryMapper.class, BaseElevatorMapper.class, MtContractDtlMapper.class,
			MtContractMapper.class, BaseDictionaryMapper.class, BasePropertyMapper.class,
			BasePropertyPersonMapper.class, BaseKeyPropertyMapper.class, BaseProjectMapper.class })
	List<Map<String, Object>> elevList(BaseElevatorSearchBean queryInfo);

	@CacheSignatureMapper({ BaseAreaMapper.class, BaseCompanyMapper.class, BaseGroupMapper.class,
		BaseStationMapper.class, SysDictionaryMapper.class, BaseElevatorMapper.class, MtContractDtlMapper.class,
		MtContractMapper.class, BaseDictionaryMapper.class, BasePropertyMapper.class,
		BasePropertyPersonMapper.class, BaseKeyPropertyMapper.class, BaseProjectMapper.class })
	Integer elevListCount(BaseElevatorSearchBean queryInfo);
	
	@CacheSignatureMapper({ MtWorkPlanMapper.class })
	int elevExistEffectContract(@Param("asset_id") Integer id);
	
	@CacheSignatureMapper({ BaseElevatorMapper.class })
	List<Map<String, Object>> fetchElevatorBrandData();

	@CacheSignatureMapper({ FileAssetChangeMapper.class })
	List<Map<String, Object>> elevChangeStatusListPage(BaseElevatorSearchBean queryInfo);

	@CacheSignatureMapper({ BaseAreaMapper.class, BaseCompanyMapper.class, BaseGroupMapper.class,
		BaseStationMapper.class, SysDictionaryMapper.class, BaseElevatorMapper.class, MtContractDtlMapper.class,
		MtContractMapper.class, BaseDictionaryMapper.class, BasePropertyMapper.class,
		BaseKeyPropertyMapper.class, BaseProjectMapper.class })
	List<Map<String, Object>> casualCheckElevList(BaseCasualElevatorSearchBean queryInfo);
	
	@CacheSignatureMapper({ BaseElevatorMapper.class })
	List<Map<String, Object>> getAnnualCheckDateBeforeCurrent();
	
	@CacheSignatureMapper({ BaseElevatorMapper.class })
	List<Map<String, Object>> getAnnualCheckDateIsNull();
	
	@CacheSignatureMapper({ BaseElevatorMapper.class, SysDictionaryMapper.class, MtContractDtlMapper.class })
	List<Map<String, Object>> gyrusCheckElevatorList(BaseElevatorSearchBean queryInfo);

	List<Map<String, Object>> elevExportList(BaseElevatorSearchBean queryInfo);

	@CacheSignatureMapper({ BaseAreaMapper.class, BaseCompanyMapper.class, BaseGroupMapper.class,
		BaseStationMapper.class, SysDictionaryMapper.class, BaseElevatorMapper.class, MtContractDtlMapper.class,
		BaseDictionaryMapper.class, BasePropertyMapper.class,
		BasePropertyPersonMapper.class, BaseKeyPropertyMapper.class, BaseProjectMapper.class })
	long elevExportListCount(BaseElevatorSearchBean queryInfo);
}
