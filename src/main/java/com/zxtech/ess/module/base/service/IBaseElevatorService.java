package com.zxtech.ess.module.base.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxtech.ess.module.base.bean.BaseCasualElevatorSearchBean;
import com.zxtech.ess.module.base.bean.BaseElevatorSearchBean;
import com.zxtech.ess.module.gen.bean.BaseElevator;
import com.zxtech.ess.module.gen.bean.FileAssetChange;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

public interface IBaseElevatorService {

	public Map<String, Object> elevatorListPage(BaseElevatorSearchBean queryInfo);

	public String update(BaseElevator bean, UserBean user);

	public ResultBean export(HttpServletRequest request, HttpServletResponse response, UserBean user,
			BaseElevatorSearchBean queryInfo);
	
	public Map<String, Object> elevChangeStatusListPage(BaseElevatorSearchBean queryInfo);

	public String saveChangeStatus(FileAssetChange bean, UserBean user);

	public String deleteChangeStatus(FileAssetChange bean, UserBean user);

	public String doDisable(BaseElevator bean, UserBean user);
	
	public String enable(BaseElevator bean, UserBean user);

	public List<Map<String, Object>> fetchElevatorBrandData();
	
	public String elevatorbatchmodify(String rows, UserBean user);

	public Map<String, Object> casualCheckElevatorListPage(BaseCasualElevatorSearchBean queryInfo);
	
	public String updateAnnualCheckDate(UserBean user);
	
	public Map<String, Object> gyrusCheckElevatorListPage(BaseElevatorSearchBean queryInfo, UserBean user);

}
