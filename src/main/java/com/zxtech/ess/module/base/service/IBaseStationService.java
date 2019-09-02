package com.zxtech.ess.module.base.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxtech.ess.module.base.bean.BaseStationSearchBean;
import com.zxtech.ess.module.gen.bean.BaseStation;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

public interface IBaseStationService {
	
	public Map<String, Object> baseStationPagingList(BaseStationSearchBean bean, UserBean user);
	
	public String save(BaseStation bean, UserBean user);
	
	public String update(BaseStation bean, UserBean user);
	
	public String disable(BaseStation bean, UserBean user);
	
	public String able(BaseStation bean, UserBean user);
	
	public ResultBean export(BaseStationSearchBean bean, HttpServletRequest request, HttpServletResponse response,UserBean user);

	public List<Map<String, Object>> statList(BaseStationSearchBean queryInfo, String combobox_type, UserBean user);

	public List<Map<String, Object>> statPartList(BaseStationSearchBean queryInfo, String combobox_type, UserBean user);

	public String doForbidden(BaseStation bean, UserBean user);

	public List<Map<String, Object>> fetchWindowStatList(BaseStationSearchBean queryInfo, UserBean user);
	
	public Map<String, Object> statSyncByErpListPage(BaseStationSearchBean queryInfo,UserBean user);
}
