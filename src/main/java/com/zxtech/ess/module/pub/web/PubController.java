package com.zxtech.ess.module.pub.web;

import java.util.HashMap;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.gson.Gson;
import com.zxtech.ess.module.base.bean.BaseElevatorSearchBean;
import com.zxtech.ess.module.gen.bean.BaseMachineType;
import com.zxtech.ess.module.gen.bean.QueryDefinition;
import com.zxtech.ess.module.gen.bean.QueryItem;
import com.zxtech.ess.module.gen.bean.SysRole;
import com.zxtech.ess.module.pub.bean.PubSearchBean;
import com.zxtech.ess.module.pub.service.IPubService;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

@Controller
@RequestMapping("/pub")
public class PubController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(PubController.class);
	
	@Autowired
	private IPubService pubService;
	@Autowired
	private CommonsMultipartResolver multipartResolver;

	/**
	 * Description: get records by queryInfo without paging
	 * 
	 * @param queryInfo
	 * @return records
	 * @author
	 */
	@RequestMapping(value = "/fetchsysrolecomboboxlist.do", method = { RequestMethod.GET })
	@ResponseBody
	public List<SysRole> fetchSysRoleComboboxList() {
		return pubService.fetchPublicSysRole();
	}
	
	@RequestMapping(value = "/getlistbycompany.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> getListByCompany(HttpServletRequest request, String combobox_type) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return pubService.getListByCompany(user, combobox_type);
	}
	
	@RequestMapping(value = "/getProjectList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getCommonProjectList(HttpServletRequest request, PubSearchBean queryInfo) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		queryInfo.setDataPermissionList(user);
		return pubService.getCommonProjectList(queryInfo, user);
	}
	
	/**
	 * Description: fetch user query helper list
	 * 
	 * @param queryInfo
	 * @return records
	 * @author
	 */
	@RequestMapping(value = "/fetchqueryhelpercomboboxlist.do", method = { RequestMethod.GET })
	@ResponseBody
	public List<Map<String, Object>> fetchQueryHelperComboboxList(HttpServletRequest request, String query_url) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return pubService.fetchQueryHelperComboboxList(query_url, user.getEmpCode());
	}
	
	/**
	 * Description: fetch user query helper list
	 * 
	 * @param queryInfo
	 * @return records
	 * @author
	 */
	@RequestMapping(value = "/fetchqueryhelperitems.do", method = { RequestMethod.GET })
	@ResponseBody
	public List<QueryItem> fetchQueryHelperItems(String query_url) {
		return pubService.fetchQueryHelperItems(query_url);
	}
	
	/**
	 * add query helper definition
	 * @param request
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/querydefinitionadd.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doQueryDefinitionSave(HttpServletRequest request, QueryDefinition bean) {
		String result = "";
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = pubService.saveQueryDefinition(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		return result;
	}
	
	/**
	 * delete query helper definition
	 * @param request
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/querydefinitiondelete.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doQueryDefinitionDelete(HttpServletRequest request, QueryDefinition bean) {
		String result = "";
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = pubService.deleteQueryDefinition(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		return result;
	}
	
	/**
	 * 导入模板大小格式验证,返回模板数据
	 * @param request
	 * @param upload_type
	 * @return
	 * @author: syp637
	 */
	@RequestMapping(value = "/importtemplatecheck.do")
	@ResponseBody
	public String importTemplateCheck(HttpServletRequest request, String upload_type) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (this.multipartResolver != null && this.multipartResolver.isMultipart(request)) {
			if (request instanceof MultipartHttpServletRequest) {
				MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
				Map<String, MultipartFile> fileMap = mRequest.getFileMap();
				for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
					MultipartFile mf = entity.getValue();
					try {
						UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
						map = pubService.importCheck(mf, user);
					} catch (Exception e) {
						LOGGER.error(e.getMessage());
						map.put("status", ResultConstants.ERROR);
					}
				}
			}
		}
		return new Gson().toJson(map);
	}
	
	/**
	 * 机器分类
	 * @param bean
	 * @return
	 * @author: syp637
	 */
	@RequestMapping(value = "/fetchpublicmachinetype.do", method = { RequestMethod.GET })
	@ResponseBody
	public List<BaseMachineType> fetchPublicMachineType(BaseMachineType bean) {
		return pubService.fetchPublicMachineType(bean);
	}
	
	/**
	 * 唯一编码
	 */
	@RequestMapping(value = "/fetchSequenceOrder.do", method = { RequestMethod.GET })
	@ResponseBody
	public String getSequenceOrder() {
		String result = "";
		try {
			result = pubService.getSequenceOrder();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "/getCommonElevatorPagingList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getCommonElevatorPagingList(HttpServletRequest request, BaseElevatorSearchBean queryInfo) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		queryInfo.setDataPermissionList(user);
		return pubService.getCommonElevatorPagingList(queryInfo);
	}
	
	/**
	 * 
	 * @param request
	 * @param queryInfo
	 * @return
	 */
	@RequestMapping(value = "/fetchexportexcelschedule.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultBean fetchExportExcelSchedule(HttpServletRequest request, String guid) {
		return pubService.fetchExportExcelSchedule(guid);
	}
}
