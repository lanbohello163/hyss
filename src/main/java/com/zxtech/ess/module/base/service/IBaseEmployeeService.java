package com.zxtech.ess.module.base.service;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.base.bean.BaseEmployeeSearchBean;
import com.zxtech.ess.module.gen.bean.BaseEmpCertificate;
import com.zxtech.ess.module.gen.bean.BaseEmpWorkExperience;
import com.zxtech.ess.module.gen.bean.BaseEmployee;
import com.zxtech.ess.module.gen.bean.BaseTraining;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

public interface IBaseEmployeeService {

	public Map<String, Object> empListPage(BaseEmployeeSearchBean queryInfo);

	public String save(BaseEmployee bean, UserBean user);

	public String update(BaseEmployee bean, UserBean user);

	public Map<String, Object> empWorkExperienceListPage(BaseEmployeeSearchBean queryInfo);

	public Map<String, Object> empTrainingListPage(BaseEmployeeSearchBean queryInfo);

	public String doSaveTraining(BaseTraining bean, UserBean user);

	public String updateTraining(BaseTraining bean, UserBean user);

	public ResultBean export(UserBean user, BaseEmployeeSearchBean queryInfo);

	public List<Map<String, Object>> empList(BaseEmployeeSearchBean queryInfo, String combobox_type);

	public String doSaveWorkExperience(BaseEmpWorkExperience bean, UserBean user);

	public String updateWorkExperience(BaseEmpWorkExperience bean, UserBean user);

	public String doSaveCertificate(BaseEmpCertificate bean, UserBean user);

	public String updateCertificate(BaseEmpCertificate bean, UserBean user);

	public Map<String, Object> empCertificateListPage(BaseEmployeeSearchBean queryInfo);

	public String initEmpTypeSeq(UserBean user);

	public Map<String, Object> regularCheckEmpPageList(BaseEmployeeSearchBean queryInfo);

	public List<Map<String, Object>> empAreaList(BaseEmployeeSearchBean queryInfo, String combobox_type);

	public Map<String, Object> gyrusEmpListPage(BaseEmployeeSearchBean queryInfo, UserBean user);

	public BaseEmployee fetchUserEmpInfo(UserBean user);

	public List<Map<String, Object>> gyrusEmpList(BaseEmployeeSearchBean queryInfo, UserBean user,
			String combobox_type);
}
