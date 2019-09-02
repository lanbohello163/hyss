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
import com.zxtech.ess.module.base.bean.BaseCasualElevatorSearchBean;
import com.zxtech.ess.module.base.bean.BaseElevatorSearchBean;
import com.zxtech.ess.module.base.service.IBaseElevatorService;
import com.zxtech.ess.module.gen.bean.BaseElevator;
import com.zxtech.ess.module.gen.bean.FileAssetChange;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

@Controller
@RequestMapping("/base")
public class BaseElevatorController {

	private final static Logger LOGGER = LoggerFactory.getLogger(BaseElevatorController.class);

	@Autowired
	private IBaseElevatorService baseElevatorService;

	@RequestMapping(value = "/elevatorlistpage.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> elevatorListPage(HttpServletRequest request, BaseElevatorSearchBean queryInfo) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		queryInfo.setDataPermissionList(user);
		return baseElevatorService.elevatorListPage(queryInfo);
	}
	
	/**
	 * Description: modify method
	 * @author whx
	 */
	@RequestMapping(value = "/elevatormodify.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doUpdate(HttpServletRequest request, BaseElevator bean) {
		String resStr = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			resStr = baseElevatorService.update(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(resStr)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_ELEVATOR, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MODIFY));
		}
		return resStr;
	}
	/**
	 * @author whx
	 */
	@RequestMapping(value = "/elevatorenable.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doEnable(HttpServletRequest request, BaseElevator bean) {
		String resStr = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		bean.setEnable_flag("1");
		try {
			resStr = baseElevatorService.enable(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(resStr)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_ELEVATOR, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ACTIVE));
		}
		return resStr;
	}
	/**
	 * Description: doDisable method
	 * @author whx
	 */
	@RequestMapping(value = "/elevatordisable.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doDisable(HttpServletRequest request, BaseElevator bean) {
		String resStr = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			resStr = baseElevatorService.doDisable(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(resStr)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_ELEVATOR, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_INACTIVE));
		}
		return resStr;
	}
	
	/**
	 * @author whx
	 */
	@RequestMapping(value = "/elevatorexport.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResultBean export(HttpServletRequest request, HttpServletResponse response, BaseElevatorSearchBean queryInfo) {
		ResultBean resultBean = new ResultBean();
		try {
			UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
			queryInfo.setDataPermissionList(user);
			resultBean = baseElevatorService.export(request, response, user, queryInfo);
		    if (ResultConstants.SUCCESS_CODE.equals(resultBean.getStatus())) {
				SysTools.saveLog(user,
						StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
								OptBehaviorConstants.LOG_MENU_BASE_ELEVATOR, OptBehaviorConstants.LOG_SEPARATOR,
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
	 * Description: 工号状态变化信息列表
	 * @author syp639
	 */
	@RequestMapping(value = "/elevchangestatuslistpage.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> elevChangeStatusListPage(HttpServletRequest request, BaseElevatorSearchBean queryInfo) {
		return baseElevatorService.elevChangeStatusListPage(queryInfo);
	}
	
	/**
	 * Description: 新增工号状态变化
	 * @author whx
	 */
	@RequestMapping(value = "/elevchangestatusadd.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doSaveChangeStatus(HttpServletRequest request, FileAssetChange bean) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		String resStr = ResultConstants.SUCCESS;
		try {
			resStr = baseElevatorService.saveChangeStatus(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(resStr)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_ELEVATOR, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_ELEVATOR_CHANGE_STATUS+OptBehaviorConstants.LOG_ADD));
		}
		return resStr;
	}
	
	/**
	 * Description: 删除工号状态变化
	 * @author whx
	 */
	@RequestMapping(value = "/elevchangestatusdelete.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doDeleteChangeStatus(HttpServletRequest request, FileAssetChange bean) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		String resStr = ResultConstants.SUCCESS;
		try {
			resStr = baseElevatorService.deleteChangeStatus(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(resStr)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_ELEVATOR, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_ELEVATOR_CHANGE_STATUS+OptBehaviorConstants.LOG_DELETE));
		}
		return resStr;
	}
	
	/**
	 * Description: 工号状态变化信息列表
	 * @author syp639
	 */
	@RequestMapping(value = "/fetchElevatorBrandData.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> fetchElevatorBrandData(HttpServletRequest request) {
		return baseElevatorService.fetchElevatorBrandData();
	}
	/**
	 * Description: batch modify method
	 * @author 799
	 */
	@RequestMapping(value = "/elevatorbatchmodify.do", method = { RequestMethod.POST })
	@ResponseBody
	public String elevatorbatchmodify(HttpServletRequest request,String rows) {
		String resStr = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			resStr = baseElevatorService.elevatorbatchmodify(rows, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(resStr)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_ELEVATOR, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MODIFY));
		}
		return resStr;
	}
	/**
	 * 抽检工号信息
	 * @author jic
	 */
	@RequestMapping(value = "/casualcheckelevatorlistpage.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> casualcheckelevatorlistpage(HttpServletRequest request, BaseCasualElevatorSearchBean queryInfo) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		queryInfo.setDataPermissionList(user);
		return baseElevatorService.casualCheckElevatorListPage(queryInfo);
	}
	/**
	 * 定时修改下次年间日期
	 * @author jic
	 */
	@RequestMapping(value = "/updateAnnualCheckDate.job", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String updateAnnualCheckDate(HttpServletRequest request, BaseElevatorSearchBean queryInfo) {
		String result = ResultConstants.SUCCESS;
		UserBean user = new UserBean();

		user.setRealname("系统定时任务");
		try {
			result = baseElevatorService.updateAnnualCheckDate(user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join("基础数据管理", OptBehaviorConstants.LOG_SEPARATOR,
							"工号管理", OptBehaviorConstants.LOG_SEPARATOR,
							"更新下次年检时间"));
		}
		return result;
	}
	
	/**
	 * @Title: gyrusCheckElevatorListPage
	 * @Description: 复回检查-获取电梯工号信息列表
	 * @param request
	 * @param queryInfo
	 * @return Map<String,Object>
	 * @throws
	 * @author max
	 */
	@RequestMapping(value = "/gyrusCheckElevatorListPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> gyrusCheckElevatorListPage(HttpServletRequest request, BaseElevatorSearchBean queryInfo) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		queryInfo.setDataPermissionList(user);
		return baseElevatorService.gyrusCheckElevatorListPage(queryInfo, user);
	}
}