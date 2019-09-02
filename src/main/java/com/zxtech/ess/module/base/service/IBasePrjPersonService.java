package com.zxtech.ess.module.base.service;

import java.util.Map;

import com.zxtech.ess.module.base.bean.BasePrjPersonSearchBean;
import com.zxtech.ess.module.gen.bean.BaseProjectPerson;
import com.zxtech.platform.vo.UserBean;

public interface IBasePrjPersonService {
	
	public Map<String, Object> basePrjPersonPagingList(BasePrjPersonSearchBean bean, UserBean user);
	
	public String save(BaseProjectPerson bean, UserBean user);
	
	public String delete(BaseProjectPerson bean, UserBean user);
	
	public String update(BaseProjectPerson bean, UserBean user);
}
