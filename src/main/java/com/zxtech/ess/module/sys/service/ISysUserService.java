package com.zxtech.ess.module.sys.service;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.gen.bean.SysRole;
import com.zxtech.ess.module.gen.bean.SysUser;
import com.zxtech.ess.module.gen.bean.SysUserDataPermission;
import com.zxtech.ess.module.sys.bean.SysUserSearchBean;
import com.zxtech.platform.vo.UserBean;

public interface ISysUserService {

    /**
     * Description: get record list records by queryInfo with paging
     *
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(SysUserSearchBean queryInfo);
    
    /**
     * Description :add method
     *
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(SysUser bean, UserBean user, List<SysRole> role_ids, String compTreeSelect);
    
    /**
     * Description :update method
     *
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(SysUser bean, UserBean user, List<SysRole> role_ids, String compTreeSelect);
    
    /**
     * Description :delete method
     *
     * @param pk
     * @return "success" or "error" or user defined
     * @author gyy
     */
    public String delete(SysUser bean, UserBean user);

    /**
     * Description :update password method
     *
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author gyy
     */
    public String updatePassword(SysUser bean, UserBean user);
    
    /**
     * 五级用户数据权限tree
     * @return
     * @author: syp637
     */
    public List<Map<String, Object>> getMenuTreeBean(UserBean user);
    
    public List<SysUserDataPermission> getSysUserDataPermissionByRoleId(Integer user_id, Integer role_id);
    
    public Map<String, Object> getSysUserDataPermission(SysUserDataPermission bean);
}