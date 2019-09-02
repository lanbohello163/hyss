package com.zxtech.ess.module.base.service;

import java.util.Map;
import com.zxtech.ess.module.base.bean.BaseFaultDutyParamSearchBean;
import com.zxtech.ess.module.gen.bean.CalFaultDutyParam;
import com.zxtech.platform.vo.UserBean;

public interface IBaseFaultDutyParamService {

	public Map<String, Object> getListWithPaging(BaseFaultDutyParamSearchBean queryInfo);
	
	public String add(CalFaultDutyParam bean, UserBean user);
	
	public String update(CalFaultDutyParam bean, UserBean user);
	
	String disable(CalFaultDutyParam bean, UserBean user);
	
	String enable(CalFaultDutyParam bean, UserBean user);
	
}
