package com.zxtech.ess.module.base.service;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.base.bean.BaseGroupSearchBean;
import com.zxtech.ess.module.gen.bean.BaseGroup;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

public interface IBaseGroupService {

	List<Map<String, Object>> groupList(BaseGroupSearchBean queryInfo, String combobox_type);

	public Map<String, Object> baseGroupPagingList(BaseGroupSearchBean bean);
	
	public String save(BaseGroup bean, UserBean user);
	
	public String update(BaseGroup bean, UserBean user);
	
	public String able(BaseGroup bean, UserBean user);
	
	public String disable(BaseGroup bean, UserBean user);
	
	public ResultBean export(BaseGroupSearchBean queryInfo, UserBean user);
	
}
