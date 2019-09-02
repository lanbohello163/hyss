package com.zxtech.ess.module.base.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxtech.ess.module.base.bean.BaseEmployeeSearchBean;
import com.zxtech.ess.module.base.service.IBasePersonService;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.vo.UserBean;

@Controller
@RequestMapping("/base")
public class BasePersonController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(BasePersonController.class);
	
	@Autowired
	private IBasePersonService basePersonService;
	
	/**
	 * 
	 * @Title: checkPersonList
	 * @Description: 获取检查人员列表-下拉框(对于复回梯:必须有合同)
	 * @param request
	 * @param queryInfo
	 * @param combobox_type
	 * @return List<Map<String,Object>>
	 * @throws
	 * @author max
	 */
	@RequestMapping(value = "/checkPersonlist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> checkPersonList(HttpServletRequest request, BaseEmployeeSearchBean queryInfo, String combobox_type) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		queryInfo.setDataPermissionList(user);
		return basePersonService.checkPersonList(queryInfo, combobox_type, user );
	}
	
	/**
	 * @Title: rectifyPersonList
	 * @Description: 获取整改负责人列表-下拉框
	 * @param request
	 * @param queryInfo
	 * @param combobox_type
	 * @return List<Map<String,Object>>
	 * @throws
	 * @author max
	 */
	@RequestMapping(value = "/rectifyPersonList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> rectifyPersonList(HttpServletRequest request, BaseEmployeeSearchBean queryInfo, String combobox_type) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return basePersonService.rectifyPersonList(queryInfo, combobox_type, user );
	}
	
	/**
	 * 
	 * @Title: guaranteePersonlist
	 * @Description: 获取保障人员列表-下拉框
	 * @param request
	 * @param queryInfo
	 * @param combobox_type
	 * @return List<Map<String,Object>>
	 * @throws
	 * @author jic
	 */
	@RequestMapping(value = "/guaranteePersonlist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> guaranteePersonlist(HttpServletRequest request, BaseEmployeeSearchBean queryInfo, String combobox_type) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		queryInfo.setDataPermissionList(user);
		return basePersonService.guaranteePersonlist(queryInfo, combobox_type, user );
	}
	
	
	/**
	 * @Title: gyrusCheckPersonlist
	 * @Description: 获取检查人员列表-下拉框(复回梯)
	 * @param request
	 * @param queryInfo
	 * @return List<Map<String,Object>>
	 * @throws
	 * @author max
	 */
	@RequestMapping(value = "/gyrusCheckPersonlist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> gyrusCheckPersonlist(HttpServletRequest request, BaseEmployeeSearchBean queryInfo) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return basePersonService.gyrusCheckPersonlist(queryInfo, user);
	}
	
	/**
	 * 
	 * @Title: guaranteePersonlist
	 * @Description: 获取抽检整改人列表-弹出框
	 * @param request
	 * @param queryInfo
	 * @param combobox_type
	 * @return List<Map<String,Object>>
	 * @throws
	 * @author jic
	 */
	@RequestMapping(value = "/getCasualCheckRectificationPersionList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> getCasualCheckRectificationPersionList(HttpServletRequest request, BaseEmployeeSearchBean queryInfo) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return basePersonService.getCasualCheckRectificationPersionList(queryInfo, user );
	}
	
}