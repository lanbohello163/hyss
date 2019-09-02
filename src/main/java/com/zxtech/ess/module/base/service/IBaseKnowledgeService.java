package com.zxtech.ess.module.base.service;

import java.util.Map;

import com.zxtech.ess.module.base.bean.BaseKnowledgeSearchBean;
import com.zxtech.ess.module.gen.bean.BaseKnowledge;
import com.zxtech.platform.vo.UserBean;

public interface IBaseKnowledgeService {

	public Map<String, Object> getListWithPaging(BaseKnowledgeSearchBean queryInfo, UserBean user);
	
	public String doAdd(BaseKnowledge bean, UserBean user);
	
	public String doUpdate(BaseKnowledge bean, UserBean user);
	
	public String doDelete(BaseKnowledge bean, UserBean user);
}
