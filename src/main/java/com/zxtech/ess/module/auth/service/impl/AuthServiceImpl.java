package com.zxtech.ess.module.auth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxtech.ess.app.GlobalVal;
import com.zxtech.ess.app.util.SysConstants;
import com.zxtech.ess.module.auth.mapper.AuthMapper;
import com.zxtech.ess.module.auth.service.IAuthService;
import com.zxtech.ess.module.gen.bean.BaseCompany;
import com.zxtech.ess.module.gen.bean.BaseEmployee;
import com.zxtech.ess.module.gen.bean.SysFunction;
import com.zxtech.ess.module.gen.bean.SysRole;
import com.zxtech.ess.module.gen.bean.SysRoleMenuFuncRel;
import com.zxtech.ess.module.gen.bean.SysUser;
import com.zxtech.ess.module.gen.bean.SysUserDataPermission;
import com.zxtech.ess.module.gen.mapper.BaseEmployeeMapper;
import com.zxtech.ess.module.gen.mapper.SysRoleMapper;
import com.zxtech.ess.module.gen.mapper.SysRoleMenuFuncRelMapper;
import com.zxtech.ess.module.gen.mapper.SysUserDataPermissionMapper;
import com.zxtech.ess.module.gen.mapper.SysUserMapper;
import com.zxtech.platform.auth.AuthConstants;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.utils.MD5Util;
import com.zxtech.platform.utils.StringUtil;
import com.zxtech.platform.vo.TreeBean;
import com.zxtech.platform.vo.UserBean;

@Service("authService")
public class AuthServiceImpl implements IAuthService {

	@Autowired
	private AuthMapper authMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysUserDataPermissionMapper SysUserDataPermissionMapper;
	@Autowired
	private SysRoleMenuFuncRelMapper sysRoleMenuFuncRelMapper;
	@Autowired
	private BaseEmployeeMapper baseEmployeeMapper;

	@Override
	public String checkAndGetUser(UserBean user, Boolean ssoLogin) {
		if(!ssoLogin && (StringUtil.isBlank(user.getUserCode()) || StringUtil.isBlank(user.getUserPasswd()))) {
			return AuthConstants.AUTH_USER_PASSWD_ERROR;
		}
		
		SysUser sysUserSearch = new SysUser();
		sysUserSearch.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
		sysUserSearch.setSso_code(user.getUserCode());
		List<SysUser> userList = sysUserMapper.selectBySqlConditions(sysUserSearch);
		if (CollectionUtils.isEmpty(userList)) {
			return AuthConstants.AUTH_NOT_USER;
		} else {
			SysUser loginUser = userList.get(0);
			if(loginUser.getEmp_code() == null) {
				return AuthConstants.AUTH_NOT_EMP;//未绑定员工禁止登陆
			}
			user.setEmpCode(loginUser.getEmp_code());
			
			if (ssoLogin || MD5Util.get32BitMd5EncString(user.getUserPasswd()).equals(loginUser.getUser_passwd())) {
				user.setUserId(loginUser.getId());
				
				BaseEmployee baseEmployee = new BaseEmployee();
				baseEmployee.setEmp_code(loginUser.getEmp_code());
				baseEmployee.setEnable_flag("1");
				List<BaseEmployee> baseEmployeeList = baseEmployeeMapper.selectBySqlConditions(baseEmployee);
				
				if (SysConstants.SA_USER.equalsIgnoreCase(user.getUserCode())) {
					user.setRealname("系统管理员");
					baseEmployee.setEmp_name("系统管理员");
					baseEmployee.setType_hotline("1");
					
					// sa user function
					user.setIsSa(true);
					user.setDataType("1");
					user.setIsHeadOffice(true);
					user.setCurrentRoleName(SysConstants.ADMIN_ROLE_NAME);
					for (SysFunction funBean : GlobalVal.getFunctionList()) {
						user.putFuncName(funBean.getFunc_method());
					}
				} else {
					if(CollectionUtils.isEmpty(baseEmployeeList)) {
						return AuthConstants.AUTH_NOT_EMP;
					}
					baseEmployee = baseEmployeeList.get(0);
					// user roles
					List<Map<String, Object>> roleList = authMapper.fetchUserRoleList(loginUser.getId());
					if (CollectionUtils.isNotEmpty(roleList)) {
						for (Map<String, Object> roleRecord : roleList) {
							SysRole sysRole = new SysRole();
							sysRole.setId((Integer) roleRecord.get("id"));
							sysRole.setRole_name(StringUtil.toSafeString(roleRecord.get("role_name")));
							sysRole.setRole_desc(StringUtil.toSafeString(roleRecord.get("role_desc")));
							user.addRoleList(sysRole);
						}
						user.setCurrentRoleId((Integer) roleList.get(0).get("id"));
						user.setCurrentRoleName(StringUtil.toSafeString(roleList.get(0).get("role_name")));
						
						user.setCompId(baseEmployee.getComp_id());
						setUserInfo(user, loginUser.getId(), (Integer) roleList.get(0).get("id"), baseEmployee.getComp_id());
					}
				}
				
				user.setRealname(baseEmployee.getEmp_name());
				user.setSourceId(baseEmployee.getSource_id());
				user.setMenuList(getUserMenu(user));
				return AuthConstants.AUTH_SUCCESS;
			}
			return AuthConstants.AUTH_USER_PASSWD_ERROR;
		}
	}
	
	private void setUserInfo(UserBean user, Integer user_id, Integer role_id, Integer comp_id) {
		//权限集合
		List<Integer> compIdList = new ArrayList<>();
		List<Integer> statIdList = new ArrayList<>();
		List<Integer> areaIdList = new ArrayList<>();
		
		SysUserDataPermission userDataPermission = new SysUserDataPermission();
		userDataPermission.setUser_id(user_id);
		userDataPermission.setRole_id(role_id);
		List<SysUserDataPermission> userDataList = SysUserDataPermissionMapper.selectBySqlConditions(userDataPermission);
		for (SysUserDataPermission sysUserDataPermissionBean : userDataList) {
			if("1".equals(sysUserDataPermissionBean.getData_type())) {
				user.setIsHeadOffice(true);
			}
			user.setDataType(sysUserDataPermissionBean.getData_type());
			
			if("5".equals(sysUserDataPermissionBean.getData_type())) {
				areaIdList.add(sysUserDataPermissionBean.getData_id());
			}else if("4".equals(sysUserDataPermissionBean.getData_type())) {
				statIdList.add(sysUserDataPermissionBean.getData_id());
			}else if("2".equals(sysUserDataPermissionBean.getData_type())
					|| "3".equals(sysUserDataPermissionBean.getData_type())){
				List<BaseCompany> selectBySqlConditions = authMapper.getDeepCompanyListByDataid(sysUserDataPermissionBean.getData_id());
				for (BaseCompany baseCompanyBean : selectBySqlConditions) {
					compIdList.add(baseCompanyBean.getId());
				}
			}
		}
		user.setCompId(comp_id);
		user.setAreaIdList(areaIdList);
		user.setStatIdList(statIdList);
		user.setCompIdList(compIdList);
		
		setCurrentRoleFuncName(user);
	}
	
	@Override
	public void changeRole(UserBean user, Integer role_id) {
		SysRole sysRole = sysRoleMapper.selectByPrimaryKey(role_id);
		user.setCurrentRoleId(role_id);
		user.setCurrentRoleName(sysRole.getRole_name());
		
		//是否总公司
		SysUserDataPermission sysUserDataPermission = new SysUserDataPermission();
		sysUserDataPermission.setData_type("1");
		sysUserDataPermission.setUser_id(user.getUserId());
		sysUserDataPermission.setRole_id(role_id);
		List<SysUserDataPermission> sysUserDataPermissionList = SysUserDataPermissionMapper.selectBySqlConditions(sysUserDataPermission);
		if(CollectionUtils.isNotEmpty(sysUserDataPermissionList)) {
			user.setIsHeadOffice(true);
		}else {
			user.setIsHeadOffice(false);
		}
		
		//权限集合
		List<Integer> compIdList = new ArrayList<>();
		List<Integer> statIdList = new ArrayList<>();
		List<Integer> areaIdList = new ArrayList<>();
		
		SysUserDataPermission userDataPermission = new SysUserDataPermission();
		userDataPermission.setUser_id(user.getUserId());
		userDataPermission.setRole_id(role_id);
		List<SysUserDataPermission> userDataList = SysUserDataPermissionMapper.selectBySqlConditions(userDataPermission);
		for (SysUserDataPermission sysUserDataPermissionBean : userDataList) {
			if("1".equals(sysUserDataPermissionBean.getData_type())) {
				user.setIsHeadOffice(true);
			}
			user.setDataType(sysUserDataPermissionBean.getData_type());
			
			if("5".equals(sysUserDataPermissionBean.getData_type())) {
				areaIdList.add(sysUserDataPermissionBean.getData_id());
			}else if("4".equals(sysUserDataPermissionBean.getData_type())) {
				statIdList.add(sysUserDataPermissionBean.getData_id());
			}else if("2".equals(sysUserDataPermissionBean.getData_type())
					|| "3".equals(sysUserDataPermissionBean.getData_type())){
				List<BaseCompany> selectBySqlConditions = authMapper.getDeepCompanyListByDataid(sysUserDataPermissionBean.getData_id());
				for (BaseCompany baseCompanyBean : selectBySqlConditions) {
					compIdList.add(baseCompanyBean.getId());
				}
			}
		}
		
		user.setAreaIdList(areaIdList);
		user.setStatIdList(statIdList);
		user.setCompIdList(compIdList);
		
		setCurrentRoleFuncName(user);
	}
	
	private void setCurrentRoleFuncName(UserBean user) {
		user.cleanUserFunc();
		List<Map<String, Object>> funcMethodList = authMapper.fetchRoleFunctionList(user.getCurrentRoleId());
		for (Map<String, Object> method : funcMethodList) {
			user.putFuncName(StringUtil.toSafeString(method.get("func_method")));
		}
	}

	@Override
	public List<Map<String, Object>> getUserMenu(UserBean user) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		List<TreeBean> menuList = GlobalVal.getMenuList();
		if (user.getIsSa()) {
			menuList.stream().forEach(gmenu -> {
				Map<String, Object> parentMenu = new HashMap<>();
				putMenuContent(parentMenu, gmenu);
				List<Map<String, Object>> childList = new ArrayList<>();
				if (CollectionUtils.isNotEmpty(gmenu.getChildren())) {
					gmenu.getChildren().stream().forEach(treeNode -> {
							Map<String, Object> childMenu = new HashMap<>();
							putMenuContent(childMenu, treeNode);
							childList.add(childMenu);
					});
				}
			    
			    parentMenu.put("children", childList);
			    
				resultList.add(parentMenu);
			});
		} else {
			if (user.getCurrentRoleId() != null) {
				Set<String> menuIdSet = new HashSet<>();
				SysRoleMenuFuncRel searchMenuFuncRel = new SysRoleMenuFuncRel();
				searchMenuFuncRel.setRole_id(user.getCurrentRoleId());
				sysRoleMenuFuncRelMapper.selectBySqlConditions(searchMenuFuncRel).stream().forEach(row -> {
					menuIdSet.add(row.getMenu_id() + Strings.EMPTY);
				});
				menuList.stream().forEach(gmenu -> {
					if (menuIdSet.contains(gmenu.getId())) {
						Map<String, Object> parentMenu = new HashMap<>();
						putMenuContent(parentMenu, gmenu);
						List<Map<String, Object>> childList = new ArrayList<>();
						if (CollectionUtils.isNotEmpty(gmenu.getChildren())) {
							gmenu.getChildren().stream().forEach(treeNode -> {
								if (menuIdSet.contains(treeNode.getId())) {
									Map<String, Object> childMenu = new HashMap<>();
									putMenuContent(childMenu, treeNode);
									childList.add(childMenu);
								}
							});
						}
						parentMenu.put("children", childList);
						resultList.add(parentMenu);
					}
				});
			}
		}
		return resultList;
	}

	private void putMenuContent(Map<String, Object> menu, TreeBean treeBean) {
		menu.put("path", treeBean.getUrl());
		menu.put("name", treeBean.getText());
		menu.put("component", "Layout");
		
		if("1".equals(treeBean.getC_menu_flag())) {
			menu.put("redirect", treeBean.getRemark());
		}
		
		// meta
		Map<String, String> metaMap = new HashMap<String, String>();
		metaMap.put("title", treeBean.getText());
		metaMap.put("icon", treeBean.getIcon());
		menu.put("meta", metaMap);
	}

	@Override
	public String changeUserPasswd(UserBean user, String oldPasswd) {
		if (StringUtil.isBlank(user.getUserCode()) || StringUtil.isBlank(user.getUserPasswd())
				|| StringUtil.isBlank(oldPasswd)) {
			return AuthConstants.AUTH_USER_PASSWD_ERROR;
		}
		SysUser sysUserSearch = new SysUser();
		sysUserSearch.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
		sysUserSearch.setSso_code(user.getUserCode());
		List<SysUser> userList = sysUserMapper.selectBySqlConditions(sysUserSearch);
		if (CollectionUtils.isEmpty(userList)) {
			return AuthConstants.AUTH_NOT_USER;
		}
		SysUser loginUser = userList.get(0);
		if (MD5Util.get32BitMd5EncString(oldPasswd).equals(loginUser.getUser_passwd())) {
			SysUser sysUser = new SysUser();
			sysUser.setId(loginUser.getId());
			sysUser.setUser_passwd(MD5Util.get32BitMd5EncString(user.getUserPasswd()));
			sysUserMapper.updateByPrimaryKeySelective(sysUser);
			return ResultConstants.SUCCESS;
		}
		return ResultConstants.ERROR;
	}

	@Override
	public Map<String, String> autoLoginCheckSsoCode(UserBean user) {
		Map<String, String> map = new HashMap<String, String>();
		SysUser sysUser = new SysUser();
		sysUser.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
		sysUser.setSso_code(user.getUserCode());
		List<SysUser> userList = sysUserMapper.selectBySqlConditions(sysUser);
		if (CollectionUtils.isEmpty(userList)) {
			map.put("return_code", AuthConstants.AUTH_NOT_USER);
			return map;
		} else {
			SysUser loginUser = userList.get(0);
			if(loginUser.getEmp_code() == null) {
				map.put("return_code", AuthConstants.AUTH_NOT_EMP);
				return map;
			}
			if("admin".equals(loginUser.getSso_code())) {
				map.put("return_code", AuthConstants.AUTH_NOT_USER);
				return map;
			}
			
			List<Map<String, Object>> roleList = authMapper.fetchUserRoleList(loginUser.getId());
			
			BaseEmployee baseEmployeeBean = new BaseEmployee();
			baseEmployeeBean.setEmp_code(loginUser.getEmp_code());
			baseEmployeeBean.setEnable_flag("1");
			List<BaseEmployee> baseEmployeeList = baseEmployeeMapper.selectBySqlConditions(baseEmployeeBean);
			BaseEmployee baseEmployee = baseEmployeeList.get(0);
			
			user.setUserId(loginUser.getId());
			user.setEmpCode(loginUser.getEmp_code());
			user.setCompId(baseEmployee.getComp_id());
			setUserInfo(user, loginUser.getId(), (Integer) roleList.get(0).get("id"), baseEmployee.getComp_id());
			
			map.put("data_type", user.getDataType());
			map.put("emp_code", loginUser.getEmp_code());
			map.put("emp_name", baseEmployee.getEmp_name());
		}
		map.put("return_code", JWTUtils.token(user));
		return map;
	}
}
