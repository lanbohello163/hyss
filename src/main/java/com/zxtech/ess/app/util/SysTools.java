package com.zxtech.ess.app.util;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.util.Strings;

import com.zxtech.ess.app.GlobalVal;
import com.zxtech.ess.app.util.wrap.ComboboxDefinedWrapper;
import com.zxtech.ess.module.gen.bean.SysFunction;
import com.zxtech.ess.module.gen.bean.SysMenu;
import com.zxtech.ess.module.pub.service.IPubService;
import com.zxtech.platform.vo.TreeBean;
import com.zxtech.platform.vo.UserBean;

/**
 * 是系统管理模块的工具类
 */
public class SysTools {

	/**
	 * 获取GUID
	 */
	public static String getGUID() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	public static void fillMenu(List<SysMenu> list, List<TreeBean> menuList) {
		// 获取一级菜单
		List<SysMenu> topMenuList = getSamePropertyListBean(list, "menu_level", 1);
		if (topMenuList != null && topMenuList.size() > 0) {
			// 递归菜单下的所有菜单和功能按钮
			for (SysMenu menu : topMenuList) {
				TreeBean bean = new TreeBean(menu.getMenu_name(), Strings.EMPTY);
				bean.setId(menu.getId() + Strings.EMPTY);
				bean.setUrl(menu.getMenu_url());
				bean.setRemark(menu.getMenu_remark());
				bean.setIcon(menu.getMenu_icon());
				if ("1".equals(menu.getMenu_sa())) {
					bean.setIssa(true);
				}
				bean.setC_menu_flag(menu.getC_menu_flag());
				bean.setChildren(getChildMenu(list, bean));
				menuList.add(bean);
			}
		}
	}

	private static List<TreeBean> getChildMenu(List<SysMenu> menuList, TreeBean parentTreeBean) {
		List<TreeBean> treeList = new ArrayList<TreeBean>();
		String parentId = parentTreeBean.getId();
		for (SysMenu menu : menuList) {
			String pid = menu.getP_menu_id() + Strings.EMPTY;
			if (parentId.equals(pid)) {
				TreeBean treeBean = new TreeBean(menu.getMenu_name(), Strings.EMPTY);
				treeBean.setId(menu.getId() + Strings.EMPTY);
				String menu_url = menu.getMenu_url();
				if (menu_url.indexOf(":") > -1) {
					treeBean.setUrl(menu_url.split(":")[0]);
				} else {
					treeBean.setUrl(menu_url);
				}
				if ("1".equals(menu.getMenu_sa())) {
					treeBean.setIssa(true);
				}
				treeBean.setC_menu_flag(menu.getC_menu_flag());
				treeBean.setRemark(menu.getMenu_remark());
				treeBean.setIcon(menu.getMenu_icon());
				treeList.add(treeBean);
				treeBean.setChildren(getChildMenu(menuList, treeBean));
			}
		}

		if (treeList.size() == 0) {
			return null;
		}
		return treeList;
	}

	/**
	 * 获取List中指定属性的值为propertyVal的列表
	 * 
	 * @param List
	 * @param propertyName
	 * @param propertyVal
	 */
	public static List<Map<String, Object>> getSamePropertyList(List<Map<String, Object>> list, String propertyName,
			String propertyVal) {
		if (list == null || list.size() == 0) {
			return null;
		}
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			String val = map.get(propertyName).toString();
			if (val == null) {
				continue;
			}
			if (val.equals(propertyVal)) {
				resultList.add(map);
			}
		}
		if (resultList.size() == 0) {
			return null;
		}
		return resultList;
	}

	/**
	 * 根据传入的属性名称、遍历list返回属性值与propertyVal相等的记录
	 * 
	 * @param list
	 * @param propertyName
	 * @param propertyVal
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <T> List<T> getSamePropertyListBean(Collection<T> list, String propertyName, Object propertyVal) {
		if (list == null || list.size() == 0) {
			return null;
		}
		List<T> resultList = new ArrayList<T>();
		for (T bean : list) {
			Object val = null;
			try {
				if (bean instanceof java.util.Map) {
					val = ((java.util.Map) bean).get(propertyName);
				} else {
					PropertyDescriptor p = new PropertyDescriptor(propertyName, bean.getClass());
					val = p.getReadMethod().invoke(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (val == null) {
				continue;
			}

			if (val instanceof java.lang.String) {
				if (val.equals(String.valueOf(propertyVal))) {
					resultList.add(bean);
				}
			} else {
				if (val.equals(propertyVal)) {
					resultList.add(bean);
				}
			}

		}
		if (resultList.size() == 0) {
			return null;
		}
		return resultList;
	}

	/**
	 * menu对应所有功能点列表
	 * 
	 * @param funcList
	 * @param menuFuncMap
	 */
	public static void fillMenuFunctionMap(List<SysFunction> funcList, Map<Integer, List<SysFunction>> menuFuncMap) {
		if (CollectionUtils.isNotEmpty(funcList)) {
			for (SysFunction function : funcList) {
				Integer menuId = function.getMenu_id();
				if (!menuFuncMap.containsKey(menuId)) {
					menuFuncMap.put(menuId, new ArrayList<SysFunction>());
				}
				menuFuncMap.get(menuId).add(function);
			}
		}
	}

	/**
	 * 封装下拉列表数据
	 * 
	 * @param list
	 * @param wrapper
	 * @return
	 */
	public static List<Map<String, Object>> getResultComboboxList(List<Map<String, Object>> list,
			ComboboxDefinedWrapper wrapper) {
		return wrapper.wrap(list);
	}

	public static void saveLog(UserBean user, String opt_behavior) {
		try {
			IPubService pubService = (IPubService) GlobalVal.ctx.getBean("pubService");
			pubService.saveLogData(user, opt_behavior);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveLog(UserBean user, String opt_behavior, String method_url, Integer rec_id) {
		try {
			IPubService pubService = (IPubService) GlobalVal.ctx.getBean("pubService");
			pubService.saveLogData(user, opt_behavior, method_url, rec_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
