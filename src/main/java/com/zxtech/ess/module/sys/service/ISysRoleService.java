package com.zxtech.ess.module.sys.service;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.gen.bean.SysRole;
import com.zxtech.ess.module.gen.bean.SysRoleMenuFuncRel;
import com.zxtech.ess.module.sys.bean.MenuTreeBean;
import com.zxtech.ess.module.sys.bean.SysRoleSearchBean;
import com.zxtech.platform.vo.UserBean;

public interface ISysRoleService {

	/**
	 * Description: get records by queryInfo with paging
	 * 
	 * @param queryInfo
	 * @return records
	 * @author
	 */
	public Map<String, Object> getListWithPaging(SysRoleSearchBean queryInfo);

	/**
	 * Description: add method
	 *
	 * @param bean
	 * @param menuList
	 * @param user
	 * @return "success" or "error" or user defined
	 * @author
	 */
	public String doAdd(SysRole bean, List<MenuTreeBean> menuList, UserBean user);

	/**
	 * Description: update method
	 *
	 * @param bean
	 * @param menuList
	 * @param user
	 * @return "success" or "error" or user defined
	 * @author
	 */
	public String doUpdate(SysRole bean, List<MenuTreeBean> menuList, UserBean user);

	/**
	 * Description: delete method
	 *
	 * @param bean
	 * @param user
	 * @return "success" or "error" or user defined
	 * @author
	 */
	public String doDelete(SysRole bean, UserBean user);

	/**
	 * Description: get menu tree
	 *
	 * @return menu tree list
	 * @author
	 */
	public List<Map<String, Object>> getMenuTreeBean();

	/**
	 * Description: get records by queryInfo without paging
	 * 
	 * @param queryInfo
	 * @return records
	 * @author
	 */
	public List<SysRoleMenuFuncRel> getRoleMenuFuncRelList(SysRoleMenuFuncRel queryInfo);
	
	public List<Map<String, Object>> getSysRolelist(SysRoleSearchBean queryInfo);
}