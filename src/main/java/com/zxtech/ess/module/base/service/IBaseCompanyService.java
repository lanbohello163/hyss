package com.zxtech.ess.module.base.service;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.base.bean.BaseCompanySearchBean;
import com.zxtech.ess.module.base.bean.BaseUserCompanySearchBean;
import com.zxtech.ess.module.gen.bean.BaseCompany;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

public interface IBaseCompanyService {

	/**
	 * 获取普通主页上部统计数据
	 * @param user
	 * @return
	 */
	List<Map<String, Object>> getCompTreeList(UserBean user);


	List<Map<String, Object>> getCompChilTreeList(UserBean user,String compType, String id);
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(BaseCompany bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(BaseCompany bean, UserBean user);
	
	/**
	 * 禁用机构
	 * @param pk
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String disable(BaseCompany bean, UserBean user);
	/**
	 * 启用机构
	 * @param pk
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String able(BaseCompany bean, UserBean user);
	
	/**
	 * 导出机构列表
	 */
	public ResultBean export();

	/**
	 * 获取公司集合
	 * @author whx
	 */
	List<Map<String, Object>> compList(BaseCompanySearchBean queryInfo, String combobox_type, UserBean user);


	Map<String, Object> compSyncListPage(BaseCompanySearchBean queryInfo);


	List<Map<String, Object>> userCompList(BaseUserCompanySearchBean queryInfo, String combobox_type, UserBean user);


	List<Map<String, Object>> pareaList(BaseCompanySearchBean queryInfo, String combobox_type, UserBean user);


	List<Map<String, Object>> areaCompList(BaseCompanySearchBean queryInfo, String combobox_type, UserBean user);


	Map<String, Object> compSyncByErpListPage(BaseCompanySearchBean queryInfo);

	/**
	 * 获取公司集合-无权限限制
	 * @author whx
	 */
	List<Map<String, Object>> compListNoRight(BaseCompanySearchBean queryInfo, String combobox_type, UserBean user);


	List<Map<String, Object>> getpareaidbycompid(BaseCompanySearchBean queryInfo);
	
	List<Map<String, Object>> getFirstLevelCompList(BaseCompanySearchBean queryInfo, String combobox_type, UserBean user);

}