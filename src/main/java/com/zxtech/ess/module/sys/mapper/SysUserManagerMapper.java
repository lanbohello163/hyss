package com.zxtech.ess.module.sys.mapper;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.gen.bean.SysUserDataPermission;
import com.zxtech.ess.module.gen.mapper.BaseCompanyMapper;
import com.zxtech.ess.module.gen.mapper.BaseEmployeeMapper;
import com.zxtech.ess.module.gen.mapper.SysUserDataPermissionMapper;
import com.zxtech.ess.module.gen.mapper.SysUserMapper;
import com.zxtech.ess.module.sys.bean.SysUserSearchBean;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface SysUserManagerMapper {

	@CacheSignatureMapper({ SysUserMapper.class, BaseEmployeeMapper.class, BaseCompanyMapper.class })
	List<Map<String, Object>> getListWithPaging(SysUserSearchBean queryInfo);
	
	@CacheSignatureMapper({ SysUserDataPermissionMapper.class })
	List<Map<String, Object>> getSysUserDataPermissionByUserIdRoleId(SysUserDataPermission bean);
}
