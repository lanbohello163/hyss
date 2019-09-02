package com.zxtech.ess.module.wx.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zxtech.ess.module.gen.mapper.BaseCompanyMapper;
import com.zxtech.ess.module.gen.mapper.BaseElevatorMapper;
import com.zxtech.ess.module.gen.mapper.BasePropertyMapper;
import com.zxtech.ess.module.gen.mapper.MtContractDtlMapper;
import com.zxtech.ess.module.wx.bean.WechatElevatorPropertyInfoBean;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface WxSynchronizeManagerMapper {
	
	@CacheSignatureMapper({BaseElevatorMapper.class, MtContractDtlMapper.class, BasePropertyMapper.class, BaseCompanyMapper.class})
	List<WechatElevatorPropertyInfoBean> getSynchronizedElevatorList(@Param("last_sync_time") Timestamp last_sync_time);
	
	@CacheSignatureMapper({BaseElevatorMapper.class, MtContractDtlMapper.class, BasePropertyMapper.class, BaseCompanyMapper.class})
	List<WechatElevatorPropertyInfoBean> getSynchronizedUpdateElevatorList(@Param("last_sync_time") Timestamp last_sync_time);
}
