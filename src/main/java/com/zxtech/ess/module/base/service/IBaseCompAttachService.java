package com.zxtech.ess.module.base.service;

import java.util.Map;

import com.zxtech.ess.module.base.bean.BaseAttAndMagBean;
import com.zxtech.ess.module.gen.bean.BaseCompAttach;
import com.zxtech.platform.vo.UserBean;

public interface IBaseCompAttachService {

	public String save(BaseCompAttach bean, UserBean user);
	
	public String delete(BaseCompAttach bean, UserBean user);
	
	public Map<String, Object> compAttachPagingList(BaseAttAndMagBean bean, UserBean user);
}
