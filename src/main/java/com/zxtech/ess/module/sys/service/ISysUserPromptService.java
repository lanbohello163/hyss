package com.zxtech.ess.module.sys.service;

import java.util.Map;

import com.zxtech.ess.module.sys.bean.SysUserPromptSearchBean;
import com.zxtech.platform.vo.UserBean;

public interface ISysUserPromptService {

    /**
     * Description: get record list records by queryInfo with paging
     *
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(UserBean user, SysUserPromptSearchBean queryInfo);
    
    
    public String update(UserBean user, SysUserPromptSearchBean queryInfo);
}