package com.zxtech.ess.module.sys.mapper;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.gen.mapper.SysRoleMapper;
import com.zxtech.ess.module.gen.mapper.SysUserMapper;
import com.zxtech.ess.module.sys.bean.SysRoleSearchBean;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface SysRolePageMapper {

	@CacheSignatureMapper({ SysRoleMapper.class })
	List<Map<String, Object>> getListWithPaging(SysRoleSearchBean queryInfo);

	@CacheSignatureMapper({ SysRoleMapper.class, SysUserMapper.class })
	List<Map<String, Object>> getSysRolelist(SysRoleSearchBean queryInfo);
}
