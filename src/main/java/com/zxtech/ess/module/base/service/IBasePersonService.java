package com.zxtech.ess.module.base.service;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.base.bean.BaseEmployeeSearchBean;
import com.zxtech.platform.vo.UserBean;

public interface IBasePersonService {
	
	/**
	 * 
	 * @Title: checkPersonList
	 * @Description: 获取检查人员下拉列表
	 * @param queryInfo
	 * @param combobox_type
	 * @param user
	 * @return List<Map<String,Object>>
	 * @throws
	 * @author max
	 */
	List<Map<String, Object>> checkPersonList(BaseEmployeeSearchBean queryInfo, String combobox_type, UserBean user);
	
	/**
	 * @Title: rectifyPersonList
	 * @Description: 获取整改负责人下拉列表
	 * @param queryInfo
	 * @param combobox_type
	 * @param user
	 * @return List<Map<String,Object>>
	 * @throws
	 * @author max
	 */
	List<Map<String, Object>> rectifyPersonList(BaseEmployeeSearchBean queryInfo, String combobox_type, UserBean user);
	/**
	 * 
	 * @Title: checkPersonList
	 * @Description: 获取保障人员下拉列表
	 * @param queryInfo
	 * @param combobox_type
	 * @param user
	 * @return List<Map<String,Object>>
	 * @throws
	 * @author jic
	 */
	List<Map<String, Object>> guaranteePersonlist(BaseEmployeeSearchBean queryInfo, String combobox_type,
			UserBean user);
	
	
	/**
	 * @Title: gyrusCheckPersonlist
	 * @Description: 获取检查人员列表-下拉框(复回梯)
	 * @param queryInfo
	 * @param user
	 * @return List<Map<String,Object>>
	 * @throws
	 * @author max
	 */
	List<Map<String, Object>> gyrusCheckPersonlist(BaseEmployeeSearchBean queryInfo, UserBean user);

	/**
	 * 
	 * @Title: checkPersonList
	 * @Description: 获取抽检整改人列表
	 * @param queryInfo
	 * @param combobox_type
	 * @param user
	 * @return List<Map<String,Object>>
	 * @throws
	 * @author jic
	 */
	List<Map<String, Object>> getCasualCheckRectificationPersionList(BaseEmployeeSearchBean queryInfo, UserBean user);

}