package com.zxtech.ess.module.base.service;

import java.util.Map;

import com.zxtech.ess.module.base.bean.BaseProjectSearchBean;
import com.zxtech.ess.module.gen.bean.BaseProject;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

public interface IBaseProjectService {

	public Map<String, Object> baseProjectPagingList(BaseProjectSearchBean bean);
	
	public String save(BaseProject bean, UserBean user);
	
	public String update(BaseProject bean, UserBean user);
	
	public String disable(BaseProject bean, UserBean user);
	
	public String able(BaseProject bean, UserBean user);

	public ResultBean export(UserBean user, BaseProjectSearchBean queryInfo);
}
