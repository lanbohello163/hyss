package com.zxtech.ess.module.base.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxtech.ess.module.base.bean.BaseDeptSearchBean;
import com.zxtech.ess.module.gen.bean.BaseDept;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

public interface IBaseDeptService {

	public Map<String, Object> getListWithPaging(BaseDeptSearchBean queryInfo);
	
	public String doAdd(BaseDept bean, UserBean user);
	
	public String doUpdate(BaseDept bean, UserBean user);
	
	public String disable(BaseDept bean, UserBean user);
	
	public String enable(BaseDept bean, UserBean user);
	
	public List<Map<String, Object>> deptList(BaseDeptSearchBean bean, String combobox_type, UserBean user);

	public ResultBean export(HttpServletRequest request, HttpServletResponse response, UserBean user,
			BaseDeptSearchBean queryInfo);
}
