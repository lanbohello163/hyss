package com.zxtech.ess.module.base.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.zxtech.ess.module.base.bean.BaseMachineTypeSearchBean;
import com.zxtech.ess.module.gen.bean.BaseMachineType;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

public interface IBaseMachineTypeService {

	public Map<String, Object> getListWithPaging(BaseMachineTypeSearchBean queryInfo);
	
	public String doAdd(BaseMachineType bean, UserBean user);
	
	public String doUpdate(BaseMachineType bean, UserBean user);
	
	public String doActive(BaseMachineType bean, UserBean user);
	
	public String doInactive(BaseMachineType bean, UserBean user);
	
	public List<Map<String, Object>> fetchBaseMachineTypeComboboxList(BaseMachineTypeSearchBean queryInfo);
	
	public ResultBean export(HttpServletRequest request, HttpServletResponse response, UserBean user,
			BaseMachineTypeSearchBean queryInfo);
	
	public Map<String, Object> importCheck(MultipartFile mf, UserBean user) throws Exception; 
	
	public Map<String, Object> importData(String paramData, UserBean user);
	
}
