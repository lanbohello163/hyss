package com.zxtech.ess.module.auth.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zxtech.ess.module.gen.bean.BaseCompany;
import com.zxtech.ess.module.gen.mapper.BaseCompanyMapper;
import com.zxtech.ess.module.gen.mapper.SysFunctionMapper;
import com.zxtech.ess.module.gen.mapper.SysRoleMapper;
import com.zxtech.ess.module.gen.mapper.SysRoleMenuFuncRelMapper;
import com.zxtech.ess.module.gen.mapper.SysUserRoleRelMapper;
import com.zxtech.platform.utils.cache.annotation.CacheSignatureMapper;

public interface AuthMapper {

	/**
	 * 获取用户角色列表信息
	 * 
	 * @param user_id
	 * @return
	 */
	@CacheSignatureMapper({ SysRoleMapper.class, SysUserRoleRelMapper.class })
	List<Map<String, Object>> fetchUserRoleList(@Param("user_id") Integer user_id);

	/**
	 * 获取角色功能列表信息
	 * 
	 * @param role_id
	 * @return
	 */
	@CacheSignatureMapper({ SysFunctionMapper.class, SysRoleMenuFuncRelMapper.class })
	List<Map<String, Object>> fetchRoleFunctionList(@Param("role_id") Integer role_id);
	
	/**
	 * 获取指定机构层级及其下级所有机构
	 * 
	 * @param data_id
	 * @return
	 */
	@CacheSignatureMapper({ BaseCompanyMapper.class })
	List<BaseCompany> getDeepCompanyListByDataid(Integer data_id);
	
	/**
	 * 获取指定机构层级及其下级所有机构
	 * 
	 * @param comp_type
	 * @return
	 */
	@CacheSignatureMapper({ BaseCompanyMapper.class })
	List<BaseCompany> getDeepCompanyListByCompType(String comp_type);
}
