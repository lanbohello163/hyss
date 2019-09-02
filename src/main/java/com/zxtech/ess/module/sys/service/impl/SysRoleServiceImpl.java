package com.zxtech.ess.module.sys.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.google.gson.Gson;
import com.zxtech.ess.app.util.SysConstants;
import com.zxtech.ess.app.util.SysTools;
import com.zxtech.ess.module.gen.bean.SysFunction;
import com.zxtech.ess.module.gen.bean.SysMenu;
import com.zxtech.ess.module.gen.bean.SysRole;
import com.zxtech.ess.module.gen.bean.SysRoleMenuFuncRel;
import com.zxtech.ess.module.gen.bean.SysUserRoleRel;
import com.zxtech.ess.module.gen.mapper.SysFunctionMapper;
import com.zxtech.ess.module.gen.mapper.SysMenuMapper;
import com.zxtech.ess.module.gen.mapper.SysRoleMapper;
import com.zxtech.ess.module.gen.mapper.SysRoleMenuFuncRelMapper;
import com.zxtech.ess.module.gen.mapper.SysUserRoleRelMapper;
import com.zxtech.ess.module.sys.bean.MenuTreeBean;
import com.zxtech.ess.module.sys.bean.SysRoleSearchBean;
import com.zxtech.ess.module.sys.mapper.SysRolePageMapper;
import com.zxtech.ess.module.sys.service.ISysRoleService;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.StringUtil;
import com.zxtech.platform.utils.page.PageHandler;
import com.zxtech.platform.vo.UserBean;

@Service("sysRoleService")
public class SysRoleServiceImpl implements ISysRoleService {
	
	private final static String MENU_PREFIX = "M_";
	private final static String FUNCTION_PREFIX = "F_";

	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysRolePageMapper sysRolePageMapper;
	@Autowired
	private SysUserRoleRelMapper sysUserRoleRelMapper;
	@Autowired
	private SysMenuMapper sysMenuMapper;
	@Autowired
	private SysFunctionMapper sysFunctionMapper;
	@Autowired
	private SysRoleMenuFuncRelMapper sysRoleMenuFuncRelMapper;

	@Override
	public Map<String, Object> getListWithPaging(SysRoleSearchBean queryInfo) {
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() -> sysRolePageMapper.getListWithPaging(queryInfo));
		return PageHandler.wrapPageMap(pageResult);
	}

	@Override
	@Transactional(rollbackFor = { RuntimeException.class, SQLException.class })
	public String doAdd(SysRole bean, List<MenuTreeBean> menuList, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		// check repeat, role_name
		SysRole checkRepeatBean = new SysRole();
		checkRepeatBean.setRole_name(bean.getRole_name());
		checkRepeatBean.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
		if (StringUtil.isNotBlank(checkRepeatBean.getRole_name()) && checkRepeatColumn(checkRepeatBean)) {
			return ResultConstants.REPEAT_ITEM1;
		}
		// insert record
		bean.setCreate_user(user.getRealname());
		bean.setCreate_timestamp(DateUtil.getNowTimestamp());
		sysRoleMapper.insertSelective(bean);
		// 角色菜单权限
		if (CollectionUtils.isNotEmpty(menuList)) {
			// 角色与菜单关系
			List<MenuTreeBean> myMenuList = SysTools.getSamePropertyListBean(menuList, "type", 0);
			List<MenuTreeBean> myFuncList = SysTools.getSamePropertyListBean(menuList, "type", 1);
			List<SysRoleMenuFuncRel> roleMenuList = new ArrayList<SysRoleMenuFuncRel>();
			if (CollectionUtils.isNotEmpty(myMenuList)) {
				for (MenuTreeBean treeBean : myMenuList) {
					SysRoleMenuFuncRel vo = new SysRoleMenuFuncRel();
					vo.setRole_id(bean.getId());
					vo.setMenu_id(Integer.valueOf(treeBean.getId()));
					String refId = treeBean.getRefId();
					if (StringUtil.isNotBlank(refId)) {
						String ids[] = refId.split("\\|");
						for (String f_id : ids) {
							String json = new Gson().toJson(vo);
							SysRoleMenuFuncRel bean_copy = new Gson().fromJson(json, SysRoleMenuFuncRel.class);
							bean_copy.setFunc_id(Integer.valueOf(f_id));
							roleMenuList.add(bean_copy);
						}
					} else {
						roleMenuList.add(vo);
					}
				}
			}
			if (myFuncList != null) {
				for (MenuTreeBean treeBean : myFuncList) {
					SysRoleMenuFuncRel vo = new SysRoleMenuFuncRel();
					vo.setRole_id(bean.getId());
					String refId = treeBean.getRefId();
					if (StringUtil.isNotBlank(refId)) {
						vo.setMenu_id(Integer.valueOf(refId));
					}
					vo.setFunc_id(Integer.valueOf(treeBean.getId()));
					roleMenuList.add(vo);
				}
			}
			if (roleMenuList != null) {
				for (SysRoleMenuFuncRel relBean : roleMenuList) {
					sysRoleMenuFuncRelMapper.insertSelective(relBean);
				}
			}
		}
		return resStr;
	}

	private Boolean checkRepeatColumn(SysRole bean) {
		List<SysRole> list = sysRoleMapper.selectBySqlConditions(bean);
		if (list.size() == 0) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(rollbackFor = { RuntimeException.class, SQLException.class })
	public String doUpdate(SysRole bean, List<MenuTreeBean> menuList, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		// check timestamp
		SysRole sysRole = sysRoleMapper.selectByPrimaryKey(bean.getId());
		if (!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), sysRole.getLast_update_timestamp())) {
			return ResultConstants.DATA_CHANGES;
		}
		// check repeat, role_name
		if (StringUtil.isNotBlank(bean.getRole_name()) && !bean.getRole_name().equals(sysRole.getRole_name())) {
			SysRole checkRepeatBean = new SysRole();
			checkRepeatBean.setRole_name(bean.getRole_name());
			checkRepeatBean.setEnable_flag("1");
			if (checkRepeatColumn(checkRepeatBean)) {
				return ResultConstants.REPEAT_ITEM1;
			}
		}
		// update record
		bean.setLast_update_user(user.getRealname());
		bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		sysRoleMapper.updateByPrimaryKeySelective(bean);
		// 删除角色菜单权限
		SysRoleMenuFuncRel sysRoleMenuFuncRel = new SysRoleMenuFuncRel();
		sysRoleMenuFuncRel.setRole_id(bean.getId());
		sysRoleMenuFuncRelMapper.deleteBySqlConditions(sysRoleMenuFuncRel);
		// 角色权限
		if (CollectionUtils.isNotEmpty(menuList)) {
			// 角色与菜单关系
			List<MenuTreeBean> myMenuList = SysTools.getSamePropertyListBean(menuList, "type", 0);
			List<MenuTreeBean> myFuncList = SysTools.getSamePropertyListBean(menuList, "type", 1);
			List<SysRoleMenuFuncRel> roleMenuList = new ArrayList<SysRoleMenuFuncRel>();
			if (CollectionUtils.isNotEmpty(myMenuList)) {
				for (MenuTreeBean treeBean : myMenuList) {
					SysRoleMenuFuncRel vo = new SysRoleMenuFuncRel();
					vo.setRole_id(bean.getId());
					vo.setMenu_id(Integer.valueOf(treeBean.getId()));
					String refId = treeBean.getRefId();
					if (StringUtil.isNotBlank(refId)) {
						String ids[] = refId.split("\\|");
						for (String f_id : ids) {
							String json = new Gson().toJson(vo);
							SysRoleMenuFuncRel bean_copy = new Gson().fromJson(json, SysRoleMenuFuncRel.class);
							bean_copy.setFunc_id(Integer.valueOf(f_id));
							roleMenuList.add(bean_copy);
						}
					} else {
						roleMenuList.add(vo);
					}
				}
			}
			if (myFuncList != null) {
				for (MenuTreeBean treeBean : myFuncList) {
					SysRoleMenuFuncRel vo = new SysRoleMenuFuncRel();
					vo.setRole_id(bean.getId());
					String refId = treeBean.getRefId();
					if (StringUtil.isNotBlank(refId)) {
						vo.setMenu_id(Integer.valueOf(refId));
					}
					vo.setFunc_id(Integer.valueOf(treeBean.getId()));
					roleMenuList.add(vo);
				}
			}
			if (roleMenuList != null) {
				for (SysRoleMenuFuncRel relBean : roleMenuList) {
					sysRoleMenuFuncRelMapper.insertSelective(relBean);
				}
			}
		}
		return resStr;
	}

	@Override
	@Transactional(rollbackFor = { RuntimeException.class, SQLException.class })
	public String doDelete(SysRole bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		// check timestamp
		SysRole sysRole = sysRoleMapper.selectByPrimaryKey(bean.getId());
		if (!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), sysRole.getLast_update_timestamp())) {
			return ResultConstants.DATA_CHANGES;
		}
		// check used
		SysUserRoleRel checkUsedBean = new SysUserRoleRel();
		checkUsedBean.setRole_id(bean.getId());
		List<SysUserRoleRel> sysUserRoleRelList = sysUserRoleRelMapper.selectBySqlConditions(checkUsedBean);
		if (sysUserRoleRelList.size() > 0) {
			return ResultConstants.USED;
		}

		// delete record
		SysRoleMenuFuncRel roleMFBean = new SysRoleMenuFuncRel();
		roleMFBean.setRole_id(bean.getId());
		sysRoleMenuFuncRelMapper.deleteBySqlConditions(roleMFBean);
		bean.setEnable_flag("0");
		bean.setLast_update_user(user.getRealname());
		bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		sysRoleMapper.updateByPrimaryKeySelective(bean);
		return resStr;
	}

	@Override
	public List<Map<String, Object>> getMenuTreeBean() {
		// 存放转换后数据的集合
		List<Map<String, Object>> compTree = new ArrayList<Map<String, Object>>();
		List<SysMenu> menuList = sysMenuMapper.selectAll();
		List<SysFunction> funcList = sysFunctionMapper.selectAll();
		// 获取一级菜单
		List<SysMenu> topMenuList = SysTools.getSamePropertyListBean(menuList, "menu_level", 1);
		if (topMenuList == null)
			return null;
		// 递归菜单下的所有菜单和功能按钮
		for (SysMenu mapMenu : topMenuList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", MENU_PREFIX + mapMenu.getId());
			map.put("text", mapMenu.getMenu_name());
			map.put("type", 0);
			map.put("children", getChildMenuFunc(menuList, funcList, map));

			compTree.add(map);
		}
		return compTree;
	}

	private List<Map<String, Object>> getChildMenuFunc(List<SysMenu> menuList,
			List<SysFunction> funcList, Map<String, Object> mapFa) {
		List<Map<String, Object>> childrenList = new ArrayList<Map<String, Object>>();
		String mid = (String) mapFa.get("id");
		for (SysMenu mapMenu : menuList) {
			Map<String, Object> map = new HashMap<String, Object>();
			Integer pid = mapMenu.getP_menu_id();
			Integer id = mapMenu.getId();
			if (mid.equals(MENU_PREFIX + pid)) {
				map.put("id", MENU_PREFIX + id);
				map.put("text", mapMenu.getMenu_name());
				map.put("type", 0);
				map.put("children", getChildMenuFunc(menuList, funcList, map));
				childrenList.add(map);
			}
		}
		List<Map<String, Object>> childFuncList = transToTreeBean(
				SysTools.getSamePropertyListBean(funcList, "menu_id", Integer.valueOf(mid.substring(2))), mapFa);
		if (childFuncList != null)
			childrenList.addAll(childFuncList);
		if (childrenList.size() == 0)
			return null;
		return childrenList;
	}

	private List<Map<String, Object>> transToTreeBean(List<SysFunction> funcList, Map<String, Object> mapFa) {
		if (funcList == null)
			return null;
		List<Map<String, Object>> compTree = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String defaultFun = ",";
		for (SysFunction mapFun : funcList) {
			Map<String, Object> map = new HashMap<String, Object>();
			String defaultFlag = mapFun.getDefault_flag();
			if ("1".equals(defaultFlag)) {
				defaultFun = defaultFun + mapFun.getId() + "|";
				continue;
			}
			map.put("id", FUNCTION_PREFIX + mapFun.getId());
			map.put("text", mapFun.getFunc_name());
			map.put("type", 1);
			map.put("refId", mapFun.getMenu_id());
			map.put("children", list);
			compTree.add(map);
		}
		if (!defaultFun.equals(",")) {
			mapFa.put("refId", defaultFun.substring(1));
		}
		return compTree;
	}

	@Override
	public List<SysRoleMenuFuncRel> getRoleMenuFuncRelList(SysRoleMenuFuncRel queryInfo) {
		return sysRoleMenuFuncRelMapper.selectBySqlConditions(queryInfo);
	}
	
	@Override
	public List<Map<String, Object>> getSysRolelist(SysRoleSearchBean queryInfo) {
		return sysRolePageMapper.getSysRolelist(queryInfo);
	}
}
