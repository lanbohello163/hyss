package com.zxtech.ess.module.base.web;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zxtech.ess.app.util.OptBehaviorConstants;
import com.zxtech.ess.app.util.SysTools;
import com.zxtech.ess.module.base.bean.BaseEmployeeSearchBean;
import com.zxtech.ess.module.base.service.IBaseEmployeeService;
import com.zxtech.ess.module.gen.bean.BaseEmpCertificate;
import com.zxtech.ess.module.gen.bean.BaseEmpWorkExperience;
import com.zxtech.ess.module.gen.bean.BaseEmployee;
import com.zxtech.ess.module.gen.bean.BaseTraining;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

@Controller
@RequestMapping("/base")
public class BaseEmployeeController {

	private final static Logger LOGGER = LoggerFactory.getLogger(BaseEmployeeController.class);

	@Autowired
	private IBaseEmployeeService baseEmployeeService;

	/**
	 * Description: 获取员工分页列表
	 * @author whx
	 */
	@RequestMapping(value = "/emplistpage.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> empListPage(HttpServletRequest request, BaseEmployeeSearchBean queryInfo) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		queryInfo.setDataPermissionList(user);
		return baseEmployeeService.empListPage(queryInfo);
	}
	/**
	 * Description: 获取员工下拉框列表
	 * @author whx
	 */
	@RequestMapping(value = "/emplist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> empList(HttpServletRequest request, BaseEmployeeSearchBean queryInfo, String combobox_type) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		queryInfo.setDataPermissionList(user);
		return baseEmployeeService.empList(queryInfo, combobox_type);
	}
	
	/**
	 * @author whx
	 */
	@RequestMapping(value = "/initemptypeseq.do", method = { RequestMethod.POST })
	@ResponseBody
	public String initEmpTypeSeq(HttpServletRequest request) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return baseEmployeeService.initEmpTypeSeq(user);
	}
	/**
	 * Description: add method
	 * @author whx
	 */
	@RequestMapping(value = "/employeeadd.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doSave(HttpServletRequest request, BaseEmployee bean) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		String resStr = ResultConstants.SUCCESS;
		try {
			resStr = baseEmployeeService.save(bean, user);
		} catch (Exception e) {
			if(e.getMessage().contains("UNIQUE KEY") && e.getMessage().contains("uq_emp_code")){
				resStr =  ResultConstants.REPEAT;
		    }else {
		    	LOGGER.error(e.getMessage(), e);
		    	resStr = ResultConstants.ERROR;
		    }
		}
		if (ResultConstants.SUCCESS.equals(resStr)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_EMPLOYEE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ADD));
		}
		return resStr;
	}
	
	/**
	 * Description: modify method
	 * @author whx
	 */
	@RequestMapping(value = "/employeemodify.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doUpdate(HttpServletRequest request, BaseEmployee bean) {
		String resStr = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			resStr = baseEmployeeService.update(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(resStr)) {
			if(bean.getEnable_flag()!=null && "1".equals(bean.getEnable_flag())) {
				SysTools.saveLog(user,
						StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
								OptBehaviorConstants.LOG_MENU_BASE_EMPLOYEE, OptBehaviorConstants.LOG_SEPARATOR,
								OptBehaviorConstants.LOG_ACTIVE));
			}else if(bean.getEnable_flag()!=null && bean.getEnable_flag().equals("0")){
		    	  SysTools.saveLog(user,
							StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
									OptBehaviorConstants.LOG_MENU_BASE_EMPLOYEE, OptBehaviorConstants.LOG_SEPARATOR,
									OptBehaviorConstants.LOG_INACTIVE));
			}else{
		    	  SysTools.saveLog(user,
							StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
									OptBehaviorConstants.LOG_MENU_BASE_EMPLOYEE, OptBehaviorConstants.LOG_SEPARATOR,
									OptBehaviorConstants.LOG_MODIFY));
			}
		}
		return resStr;
	}
	/**
	 * Description: 获取员工工作经历列表
	 * @author whx
	 */
	@RequestMapping(value = "/empworkexperiencelistpage.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> empWorkExperienceListPage(HttpServletRequest request, BaseEmployeeSearchBean queryInfo) {
		return baseEmployeeService.empWorkExperienceListPage(queryInfo);
	}
	/**
	 * Description: 获取员工培训情况分页列表
	 * @author whx
	 */
	@RequestMapping(value = "/emptraininglistpage.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> empTrainingListPage(HttpServletRequest request, BaseEmployeeSearchBean queryInfo) {
		return baseEmployeeService.empTrainingListPage(queryInfo);
	}
	/**
	 * Description: 新增员工工作经历
	 * @author whx
	 */
	@RequestMapping(value = "/workexperienceadd.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doSaveWorkExperience(HttpServletRequest request, BaseEmpWorkExperience bean) {
		String resStr = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			resStr = baseEmployeeService.doSaveWorkExperience(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(resStr)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_WORK_EXPERIENCE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ADD));
		}
		return resStr;
	}
	
	/**
	 * Description: 修改员工工作经历
	 * @author whx
	 */
	@RequestMapping(value = "/workexperiencemodify.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doUpdateWorkExperience(HttpServletRequest request, BaseEmpWorkExperience bean) {
		String resStr = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			resStr = baseEmployeeService.updateWorkExperience(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(resStr)) {
			if(bean.getEnable_flag()!= null && "0".equals(bean.getEnable_flag())) {
			   SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_WORK_EXPERIENCE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_DELETE));
		    }else{			
			   SysTools.saveLog(user,
				    StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
						OptBehaviorConstants.LOG_MENU_BASE_WORK_EXPERIENCE, OptBehaviorConstants.LOG_SEPARATOR,
						OptBehaviorConstants.LOG_MODIFY));
		
         }			
    }
		return resStr;
  }	
	/**
	 * Description: 新增培训情况
	 * @author whx
	 */
	@RequestMapping(value = "/trainingadd.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doSaveTraining(HttpServletRequest request, BaseTraining bean) {
		String resStr = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			resStr = baseEmployeeService.doSaveTraining(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(resStr)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_TRAINING, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ADD));
		}
		return resStr;
	}
	
	/**
	 * Description: 修改培训情况
	 * @author whx
	 */
	@RequestMapping(value = "/trainingmodify.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doUpdateTraining(HttpServletRequest request, BaseTraining bean) {
		String resStr = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			resStr = baseEmployeeService.updateTraining(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(resStr)) {
			if(bean.getEnable_flag()!= null && "0".equals(bean.getEnable_flag())) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_TRAINING, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_DELETE));
		}else {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_TRAINING, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MODIFY));
		     }
		}
		return resStr;
	 }

	/**
	 * Description: 验证用户是否是总公司权限
	 * @author whx
	 */
	@RequestMapping(value = "/isHeadOffice.do", method = { RequestMethod.POST })
	@ResponseBody
	public boolean isHeadOffice(HttpServletRequest request) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return user.getIsHeadOffice();
	}
	
	/**
	 * 员工导出
	 * @author whx
	 */
	@RequestMapping(value = "/employeeexport.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResultBean export(HttpServletRequest request, HttpServletResponse response, BaseEmployeeSearchBean queryInfo)  {
		ResultBean resultBean = new ResultBean();
		try {
			UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
			queryInfo.setDataPermissionList(user);
			resultBean = baseEmployeeService.export(user, queryInfo);
			if (ResultConstants.SUCCESS_CODE.equals(resultBean.getStatus())) {
				SysTools.saveLog(user,
						StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
								OptBehaviorConstants.LOG_MENU_BASE_EMPLOYEE, OptBehaviorConstants.LOG_SEPARATOR,
								OptBehaviorConstants.LOG_EXPORT));
			}
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			resultBean.setStatus(ResultConstants.ERROR_CODE);
			resultBean.setMsg(ResultConstants.ERROR);
		}
		return resultBean;
	}
	/**
	 * Description: 获取员工证书分页列表
	 * @author whx
	 */
	@RequestMapping(value = "/empcertificatelistpage.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> empCertificateListPage(HttpServletRequest request, BaseEmployeeSearchBean queryInfo) {
		return baseEmployeeService.empCertificateListPage(queryInfo);
	}
	/**
	 * Description: 新增员工证书
	 * @author whx
	 */
	@RequestMapping(value = "/certificateadd.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doSaveCertificate(HttpServletRequest request, BaseEmpCertificate bean) {
		String resStr = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			resStr = baseEmployeeService.doSaveCertificate(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(resStr)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_CERTIFICATE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ADD));
		}
		return resStr;
	}
	
	/**
	 * Description: 修改员工证书
	 * @author whx
	 */
	@RequestMapping(value = "/certificatemodify.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doUpdateCertificate(HttpServletRequest request, BaseEmpCertificate bean) {
		String resStr = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			resStr = baseEmployeeService.updateCertificate(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(resStr)) {
			if(bean.getEnable_flag()!= null && "0".equals(bean.getEnable_flag())) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_CERTIFICATE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_DELETE));
		 }else {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_CERTIFICATE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MODIFY));
		      }
	    }
		return resStr;
    }
	
	/**
	 * 
	 * @Title: regularCheckEmpPageList
	 * @Description: 获取定检人员分页列表
	 * @param request
	 * @param queryInfo
	 * @return Map<String,Object>
	 * @throws
	 * @author max
	 */
	@RequestMapping(value = "/regularCheckEmpPageList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> regularCheckEmpPageList(HttpServletRequest request, BaseEmployeeSearchBean queryInfo) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		queryInfo.setDataPermissionList(user);
		return baseEmployeeService.regularCheckEmpPageList(queryInfo);
	}
	
	/**
	 * Description: 获取工号所属片员工下拉框列表
	 * @author whx
	 */
	@RequestMapping(value = "/empAreaList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> empAreaList(HttpServletRequest request, BaseEmployeeSearchBean queryInfo, String combobox_type) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		queryInfo.setDataPermissionList(user);
		return baseEmployeeService.empAreaList(queryInfo, combobox_type);
	}
	
	/**
	 * @Title: gyrusEmpListPage
	 * @Description: 获取当前登录人所属机构下的复回检查人员分页列表(司、站、片,下级机构优先)
	 * @param request
	 * @param queryInfo
	 * @return Map<String,Object>
	 * @throws
	 * @author max
	 */
	@RequestMapping(value = "/gyrusEmpListPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> gyrusEmpListPage(HttpServletRequest request, BaseEmployeeSearchBean queryInfo) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		queryInfo.setDataPermissionList(user);
		return baseEmployeeService.gyrusEmpListPage(queryInfo, user);
	}
	
	/**
	 * @Description: 获取当前登陆人的员工姓名和电话
	 * @author whx
	 */
	@RequestMapping(value = "/fetchuserempinfo.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public BaseEmployee fetchUserEmpInfo(HttpServletRequest request) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return baseEmployeeService.fetchUserEmpInfo(user);
	}
	
	
	/**
	 * @Title: gyrusEmpList
	 * @Description: 获取无合同的检查人员列表-复回梯
	 * @param request
	 * @param queryInfo
	 * @param combobox_type
	 * @return List<Map<String,Object>>
	 * @throws
	 * @author max
	 */
	@RequestMapping(value = "/gyrusEmpList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> gyrusEmpList(HttpServletRequest request, BaseEmployeeSearchBean queryInfo, String combobox_type) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		queryInfo.setDataPermissionList(user);
		return baseEmployeeService.gyrusEmpList(queryInfo, user, combobox_type);
	}
}