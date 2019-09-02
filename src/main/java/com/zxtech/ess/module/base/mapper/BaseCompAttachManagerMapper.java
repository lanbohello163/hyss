package com.zxtech.ess.module.base.mapper;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.base.bean.BaseAttAndMagBean;
import com.zxtech.ess.module.gen.mapper.BaseCompAttachMapper;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface BaseCompAttachManagerMapper {

	@CacheSignatureMapper({ BaseCompAttachMapper.class })
	List<Map<String, Object>> compAttachPagingList(BaseAttAndMagBean bean);
}
