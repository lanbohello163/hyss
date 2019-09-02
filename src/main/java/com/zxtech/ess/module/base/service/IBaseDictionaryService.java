package com.zxtech.ess.module.base.service;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.base.bean.BaseDictionarySearchBean;
import com.zxtech.ess.module.gen.bean.BaseDictionary;
import com.zxtech.platform.vo.UserBean;

public interface IBaseDictionaryService {

	List<Map<String, Object>> fetchBaseDictionaryComboboxList(BaseDictionarySearchBean queryInfo);
	
	public Map<String, Object> getListWithPaging(BaseDictionarySearchBean queryInfo);
	
	public String doAdd(BaseDictionary bean, UserBean user);
	
	public String doUpdate(BaseDictionary bean, UserBean user);
	
	String doInactive(BaseDictionary bean, UserBean user);
	
	String doActive(BaseDictionary bean, UserBean user);

	BaseDictionary getBaseDictionaryInfoByCode(BaseDictionary bean);

	String getChinaListByType(BaseDictionary bean);
	
	public List<BaseDictionary> getBaseDictListByType(BaseDictionary queryInfo, String combobox_type);
	
	public Map<String, Object> getContractListWithPaging(BaseDictionarySearchBean queryInfo);
	
	public Map<String, Object> getHotLineListWithPaging(BaseDictionarySearchBean queryInfo);
}
