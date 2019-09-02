package com.zxtech.ess.module.sys.service;

import java.util.Map;

import com.zxtech.ess.module.gen.bean.SysParameter;
import com.zxtech.ess.module.sys.bean.SysParameterSearchBean;
import com.zxtech.platform.vo.UserBean;

public interface ISysParameterService {

	public Map<String, Object> sysParameterPagingList(SysParameterSearchBean bean);
	
	public String update(SysParameter bean, UserBean user);

	public SysParameter getSysParameterValueByKey(SysParameter bean);
}
