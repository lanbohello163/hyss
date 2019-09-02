package com.zxtech.ess.module.base.service;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.base.bean.BaseAreaSearchBean;
import com.zxtech.ess.module.gen.bean.BaseArea;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

public interface IBaseAreaService {

	List<Map<String, Object>> areaList(BaseAreaSearchBean queryInfo, String combobox_type);

	public Map<String, Object> baseAreaPagingList(BaseAreaSearchBean bean);

	public String save(BaseArea bean, UserBean user);

	public String update(BaseArea bean, UserBean user);

	public String disable(BaseArea bean, UserBean user);

	public String able(BaseArea bean, UserBean user);

	public ResultBean export(UserBean user, BaseAreaSearchBean queryInfo);

	List<Map<String, Object>> fetchWindowAreaList(BaseAreaSearchBean queryInfo, UserBean user);

	public String doForbiddenArea(BaseArea bean, UserBean user);
}
