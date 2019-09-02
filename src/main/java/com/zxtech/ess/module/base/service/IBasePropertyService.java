package com.zxtech.ess.module.base.service;

import java.util.List;
import java.util.Map;
import com.zxtech.ess.module.base.bean.BasePropertySearchBean;
import com.zxtech.ess.module.gen.bean.BaseKeyProperty;
import com.zxtech.ess.module.gen.bean.BaseProperty;
import com.zxtech.ess.module.gen.bean.BasePropertyPerson;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

public interface IBasePropertyService {

	public Map<String, Object> propertyListPage(BasePropertySearchBean queryInfo);

	public String save(BaseProperty bean, UserBean user) ;

	public String update(BaseProperty bean, UserBean user);

	public Map<String, Object> propertyPersonListPage(BasePropertySearchBean queryInfo);

	public String doPersionSave(BasePropertyPerson bean, UserBean user);

	public String doPersionUpdate(BasePropertyPerson bean, UserBean user);

	public ResultBean export(UserBean user, BasePropertySearchBean queryInfo);
	
	List<Map<String, Object>> getKeyPropertyCombobox(String combobox_type, UserBean user, BaseKeyProperty bean);

	public String delete(BaseProperty bean, UserBean user);

	public String doPersionDelete(BasePropertyPerson bean, UserBean user);

	public List<BaseKeyProperty> keyPropertyList(String combobox_type);
	
	List<Map<String, Object>> getKeyPropertyNotCommonCombobox(String combobox_type, UserBean user);

	public String initPropertyCode(UserBean user);

}
