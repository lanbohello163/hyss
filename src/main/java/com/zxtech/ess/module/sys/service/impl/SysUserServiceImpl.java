package com.zxtech.ess.module.sys.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zxtech.ess.app.util.SysConstants;
import com.zxtech.ess.app.util.SysTools;
import com.zxtech.ess.module.auth.mapper.AuthMapper;
import com.zxtech.ess.module.gen.bean.BaseCompany;
import com.zxtech.ess.module.gen.bean.SysRole;
import com.zxtech.ess.module.gen.bean.SysUser;
import com.zxtech.ess.module.gen.bean.SysUserDataPermission;
import com.zxtech.ess.module.gen.bean.SysUserRoleRel;
import com.zxtech.ess.module.gen.mapper.SysUserDataPermissionMapper;
import com.zxtech.ess.module.gen.mapper.SysUserMapper;
import com.zxtech.ess.module.gen.mapper.SysUserRoleRelMapper;
import com.zxtech.ess.module.pub.mapper.PubMapper;
import com.zxtech.ess.module.sys.bean.SysUserSearchBean;
import com.zxtech.ess.module.sys.mapper.SysUserManagerMapper;
import com.zxtech.ess.module.sys.service.ISysUserService;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.MD5Util;
import com.zxtech.platform.utils.StringUtil;
import com.zxtech.platform.utils.page.PageHandler;
import com.zxtech.platform.vo.UserBean;

@Service("sysUserService")
public class SysUserServiceImpl implements ISysUserService {
	private final static Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);
	
	private final static String Comp_PREFIX = "C_";
	private final static String Stat_PREFIX = "S_";
	private final static String Area_PREFIX = "A_";
	
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserManagerMapper sysUserManagerMapper;
	@Autowired
	private SysUserDataPermissionMapper sysUserDataPermissionMapper;
	@Autowired
	private SysUserRoleRelMapper sysUserRoleRelMapper;
	@Autowired
	private PubMapper pubMapper;
	@Autowired
	private AuthMapper authMapper;
	
	@Override
	public Map<String, Object> getListWithPaging(SysUserSearchBean queryInfo) {
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() -> sysUserManagerMapper.getListWithPaging(queryInfo));
		return PageHandler.wrapPageMap(pageResult);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor = { RuntimeException.class, SQLException.class })
	public String save(SysUser bean, UserBean user, List<SysRole> role_ids, String compTreeSelect) {
		String resStr = ResultConstants.SUCCESS;
		
		SysUser checkRepeatBean1 = new SysUser();
		checkRepeatBean1.setSso_code(bean.getSso_code());
		checkRepeatBean1.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
		if (StringUtil.isNotBlank(checkRepeatBean1.getSso_code()) && checkRepeatColumn(checkRepeatBean1)) {
			return ResultConstants.REPEAT_ITEM1;
		}
		
		SysUser checkRepeatBean3 = new SysUser();
		checkRepeatBean3.setEmp_code(bean.getEmp_code());
		checkRepeatBean3.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
		if (StringUtil.isNotBlank(checkRepeatBean3.getEmp_code()) && checkRepeatColumn(checkRepeatBean3)) {
			return ResultConstants.REPEAT_ITEM3;
		}
		
		// insert record
		bean.setUser_passwd(MD5Util.get32BitMd5EncString("123"));
		bean.setCreate_user(user.getRealname());
		bean.setCreate_timestamp(DateUtil.getNowTimestamp());
		
		String user_roles = "";
		
		for (SysRole sysRole : role_ids) {
			user_roles = user_roles + "," + sysRole.getRole_name();
		}
		
		if(StringUtil.isNotBlank(user_roles)) {
			bean.setLast_update_remark(user_roles.substring(1, user_roles.length()));
		}
		
		int ret = sysUserMapper.insertSelective(bean);
		if(ret == 0) {
			resStr = ResultConstants.ERROR;
		}else {
			Gson gson = new Gson();
			Map<String, Object> compMap = gson.fromJson(compTreeSelect,
					new TypeToken<Map<String, Object>>() {}.getType());
			
			SysUserRoleRel sysUserRoleRel = null;
			for (SysRole sysRole : role_ids) {
				sysUserRoleRel = new SysUserRoleRel();
				sysUserRoleRel.setUser_id(bean.getId());
				sysUserRoleRel.setRole_id(sysRole.getId());
				sysUserRoleRelMapper.insertSelective(sysUserRoleRel);
				
				String role_id = String.valueOf(sysRole.getId());
				if(compMap.get(role_id) != null){
					List<Map<String, Object>> compList = (List<Map<String, Object>>)compMap.get(role_id);
					for (Map<String, Object> itemMap : compList) {
						SysUserDataPermission sysUserDataPermission = new SysUserDataPermission();
						sysUserDataPermission.setUser_id(bean.getId());
						sysUserDataPermission.setRole_id(sysRole.getId());
						sysUserDataPermission.setData_id(Integer.valueOf((String)itemMap.get("data_id")));
						sysUserDataPermission.setData_type((String)itemMap.get("data_type"));
						sysUserDataPermissionMapper.insertSelective(sysUserDataPermission);
					}
				}
			}
		}
		return resStr;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor = { RuntimeException.class, SQLException.class })
	public String update(SysUser bean, UserBean user, List<SysRole> role_ids, String compTreeSelect) {
		String resStr = ResultConstants.SUCCESS;
		
		// check timestamp
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(bean.getId());
		if (!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), sysUser.getLast_update_timestamp())) {
			return ResultConstants.DATA_CHANGES;
		}
		
		// check repeat
		if (StringUtil.isNotBlank(bean.getSso_code()) && !bean.getSso_code().equals(sysUser.getSso_code())) {
			SysUser checkRepeatBean = new SysUser();
			checkRepeatBean.setSso_code(bean.getSso_code());
			checkRepeatBean.setEnable_flag("1");
			if (checkRepeatColumn(checkRepeatBean)) {
				return ResultConstants.REPEAT_ITEM1;
			}
		}
		
		if (StringUtil.isNotBlank(bean.getEmp_code()) && !bean.getEmp_code().equals(sysUser.getEmp_code())) {
			SysUser checkRepeatBean = new SysUser();
			checkRepeatBean.setEmp_code(bean.getEmp_code());
			checkRepeatBean.setEnable_flag("1");
			if (checkRepeatColumn(checkRepeatBean)) {
				return ResultConstants.REPEAT_ITEM3;
			}
		}
		
		// update record
		String user_roles = "";
		
		for (SysRole sysRole : role_ids) {
			user_roles = user_roles + "," + sysRole.getRole_name();
		}
		
		
		if(StringUtil.isNotBlank(user_roles)) {
			sysUser.setLast_update_remark(user_roles.substring(1, user_roles.length()));
		}
		
		sysUser.setSso_code(bean.getSso_code());
		sysUser.setEmp_code(bean.getEmp_code());
		sysUser.setLast_update_user(user.getRealname());
		sysUser.setLast_update_timestamp(DateUtil.getNowTimestamp());
		
		int ret = sysUserMapper.updateByPrimaryKey(sysUser);
		
		LOGGER.error("sys_user_debug ==> " + sysUser.getId()  + "," + sysUser.getEmp_code());
		
		if(ret == 0) {
			resStr = ResultConstants.ERROR;
		}else {
			//删除用户角色
			SysUserRoleRel userRoleRel = new SysUserRoleRel();
			userRoleRel.setUser_id(sysUser.getId());
			sysUserRoleRelMapper.deleteBySqlConditions(userRoleRel);
			
			//删除用户数据权限
			SysUserDataPermission userDataPermission = new SysUserDataPermission();
			userDataPermission.setUser_id(sysUser.getId());
			sysUserDataPermissionMapper.deleteBySqlConditions(userDataPermission);
			
			Gson gson = new Gson();
			Map<String, Object> compMap = gson.fromJson(compTreeSelect,
					new TypeToken<Map<String, Object>>() {}.getType());
			
			SysUserRoleRel sysUserRoleRel = null;
			for (SysRole sysRole : role_ids) {
				sysUserRoleRel = new SysUserRoleRel();
				sysUserRoleRel.setUser_id(bean.getId());
				sysUserRoleRel.setRole_id(sysRole.getId());
				sysUserRoleRelMapper.insertSelective(sysUserRoleRel);
				
				String role_id = String.valueOf(sysRole.getId());
				List<Map<String, Object>> compList = (List<Map<String, Object>>)compMap.get(role_id);
				if(compMap.get(role_id) != null){
					SysUserDataPermission sysUserDataPermission = null;
					for (Map<String, Object> itemMap : compList) {
						sysUserDataPermission = new SysUserDataPermission();
						sysUserDataPermission.setUser_id(bean.getId());
						sysUserDataPermission.setRole_id(sysRole.getId());
						sysUserDataPermission.setData_id(Integer.valueOf((String)itemMap.get("data_id")));
						sysUserDataPermission.setData_type((String)itemMap.get("data_type"));
						sysUserDataPermissionMapper.insertSelective(sysUserDataPermission);
					}
				}
			}
		}
		return resStr;
	}

	@Override
	@Transactional(rollbackFor = { RuntimeException.class, SQLException.class })
	public String delete(SysUser bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		// check timestamp
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(bean.getId());
		if (!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), sysUser.getLast_update_timestamp())) {
			return ResultConstants.DATA_CHANGES;
		}
		// delete record
		sysUserRoleRelMapper.deleteByPrimaryKey(bean.getId());
		sysUserDataPermissionMapper.deleteByPrimaryKey(bean.getId());

		bean.setEnable_flag("0");
		bean.setLast_update_user(user.getRealname());
		bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		sysUserMapper.updateByPrimaryKeySelective(bean);
		return resStr;
	}

	@Override
	public String updatePassword(SysUser bean, UserBean user) {
		return null;
	}
	
	@Override
	public List<Map<String, Object>> getMenuTreeBean(UserBean user) {
		// 存放转换后数据的集合
		List<Map<String, Object>> compTree = new ArrayList<Map<String, Object>>();
		
		String comp_type = "1";
		List<BaseCompany> menuList = new ArrayList<>();
		List<BaseCompany> compList = authMapper.getDeepCompanyListByCompType("1");
		if (CollectionUtils.isNotEmpty(compList)) {
			menuList.addAll(compList);
		}
		List<Integer> compIdList = new ArrayList<>();
		for (BaseCompany baseCompany : compList) {
			if ("3".equals(baseCompany.getComp_type())) {
				compIdList.add(baseCompany.getId());
			}
		}
		List<Integer> statIdList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(compIdList)) {
			List<BaseCompany> statList = pubMapper.getTreeListByStat(compIdList);
			menuList.addAll(statList);
			for (BaseCompany statCompany : statList) {
				statIdList.add(statCompany.getId());
			}
		}
		if (CollectionUtils.isNotEmpty(statIdList)) {
			List<BaseCompany> areaList = pubMapper.getTreeListByArea(statIdList);
			menuList.addAll(areaList);
		}
		
		// 获取一级菜单
		List<BaseCompany> topMenuList = SysTools.getSamePropertyListBean(menuList, "comp_type", comp_type);
		
		if (topMenuList == null)
			return null;
		// 递归菜单下的所有菜单和功能按钮
		for (BaseCompany topMap : topMenuList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", Comp_PREFIX + topMap.getId());
			map.put("text", topMap.getComp_name());
			map.put("type", Integer.valueOf(topMap.getComp_type()));
			map.put("children", getChildMenuFunc(menuList, map));

			compTree.add(map);
		}
		return compTree;
	}
	
	private List<Map<String, Object>> getChildMenuFunc(List<BaseCompany> menuList, Map<String, Object> mapFa) {
		List<Map<String, Object>> childrenList = new ArrayList<Map<String, Object>>();
		String mid = (String)mapFa.get("id");
		for (BaseCompany mapMenu : menuList) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			String pid = null;
			if(mapMenu.getP_comp_id() != null) {
				pid = mapMenu.getP_comp_id().toString();
			}
			String id = mapMenu.getId().toString();
			
			if (("5".equals(mapMenu.getComp_type()) && mid.equals(Stat_PREFIX + pid)) || (!"5".equals(mapMenu.getComp_type()) && mid.equals(Comp_PREFIX + pid))) {
				if("4".equals(mapMenu.getComp_type())) {
					map.put("id", Stat_PREFIX + id);
					map.put("attributes", Comp_PREFIX + pid);
				}else if("5".equals(mapMenu.getComp_type())) {
					map.put("id", Area_PREFIX + id);
					map.put("attributes", Stat_PREFIX + pid);
				}else {
					map.put("id", Comp_PREFIX + id);
					map.put("attributes", Comp_PREFIX + pid);
				}
				
				map.put("text", mapMenu.getComp_name());
				map.put("type", Integer.valueOf(mapMenu.getComp_type()));
				map.put("children", getChildMenuFunc(menuList, map));
				childrenList.add(map);
			}
		}
		
		if (childrenList.size() == 0)
			return null;
		return childrenList;
	}
	
	private Boolean checkRepeatColumn(SysUser bean) {
		List<SysUser> list = sysUserMapper.selectBySqlConditions(bean);
		if (list.size() == 0) {
			return false;
		}
		return true;
	}

	@Override
	public List<SysUserDataPermission> getSysUserDataPermissionByRoleId(Integer user_id, Integer role_id) {
		SysUserDataPermission searchBean = new SysUserDataPermission();
		searchBean.setUser_id(user_id);
		searchBean.setRole_id(role_id);
		return sysUserDataPermissionMapper.selectBySqlConditions(searchBean);
	}
	
	@Override
	public Map<String, Object> getSysUserDataPermission(SysUserDataPermission bean) {
		SysUserRoleRel sysUserRoleRel = new SysUserRoleRel();
		sysUserRoleRel.setUser_id(bean.getUser_id());
		List<SysUserRoleRel> UserRoleList = sysUserRoleRelMapper.selectBySqlConditions(sysUserRoleRel);
		Map<String, Object> map = new HashMap<>();
		for (SysUserRoleRel userRoleRel : UserRoleList) {
			bean.setRole_id(userRoleRel.getRole_id());
			List<Map<String, Object>> list = sysUserManagerMapper.getSysUserDataPermissionByUserIdRoleId(bean);
			
			map.put(String.valueOf(userRoleRel.getRole_id()), list);
		}
		
		return map;
	}
}
