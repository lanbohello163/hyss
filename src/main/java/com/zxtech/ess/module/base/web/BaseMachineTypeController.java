package com.zxtech.ess.module.base.web;


import java.util.HashMap;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.gson.Gson;
import com.zxtech.ess.app.util.OptBehaviorConstants;
import com.zxtech.ess.app.util.SysTools;
import com.zxtech.ess.app.util.wrap.ComboboxDefined;
import com.zxtech.ess.module.base.bean.BaseDictionarySearchBean;
import com.zxtech.ess.module.base.bean.BaseElevatorSearchBean;
import com.zxtech.ess.module.base.bean.BaseMachineTypeSearchBean;
import com.zxtech.ess.module.base.service.IBaseDictionaryService;
import com.zxtech.ess.module.base.service.IBaseMachineTypeService;
import com.zxtech.ess.module.gen.bean.BaseDictionary;
import com.zxtech.ess.module.gen.bean.BaseMachineType;
import com.zxtech.ess.module.pub.service.IPubService;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

@Controller
@RequestMapping("/base")
public class BaseMachineTypeController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(BaseMachineTypeController.class);
	
	@Autowired
	private IBaseMachineTypeService baseMachineTypeService;
	@Autowired
	private CommonsMultipartResolver multipartResolver;
	/**
	 * 
	 * @param queryInfo
	 * @return
	 * @author: syp799
	 */
	@RequestMapping(value = "/fetchbasemachinetypecomboboxlist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> fetchBaseDictionaryComboboxList(HttpServletRequest request, BaseMachineTypeSearchBean queryInfo) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		List<Map<String, Object>> resultComboboxList = SysTools.getResultComboboxList(baseMachineTypeService.fetchBaseMachineTypeComboboxList(queryInfo), () -> {
			return new ComboboxDefined("type_id", "type_desc", queryInfo.getCombobox_type(), user);
	    });
		
		return resultComboboxList;
	}
	
	
	@RequestMapping(value = "/baseMachineTypelist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getListWithPaging(HttpServletRequest request, BaseMachineTypeSearchBean queryInfo) {
		return baseMachineTypeService.getListWithPaging(queryInfo);
	}
	
	@RequestMapping(value = "/baseMachineTypeadd.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doAdd(HttpServletRequest request, BaseMachineType bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseMachineTypeService.doAdd(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							"机器码管理", OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ADD));
		}
		return result;
	}
	
	@RequestMapping(value = "/baseMachineTypemodify.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doUpdate(HttpServletRequest request, BaseMachineType bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseMachineTypeService.doUpdate(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							"机器码管理", OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MODIFY));
		}
		return result;
	}
	
	@RequestMapping(value = "/basemachinetypeactive.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doActive(HttpServletRequest request, BaseMachineType bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseMachineTypeService.doActive(bean, user);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							"机器码管理", OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ACTIVE));
		}
		return result;
	}
	
	@RequestMapping(value = "/basemachinetypeinactive.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doInactive(HttpServletRequest request, BaseMachineType bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseMachineTypeService.doInactive(bean, user);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							"机器码管理", OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_INACTIVE));
		}
		return result;
	}
	/**
	 * @author syp799
	 */
	@RequestMapping(value = "/basemachinetypeExport.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResultBean export(HttpServletRequest request, HttpServletResponse response, BaseMachineTypeSearchBean queryInfo)  {
		ResultBean resultBean = new ResultBean();
		
		try {
			UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
			queryInfo.setDataPermissionList(user);
			resultBean = baseMachineTypeService.export(request, response, user, queryInfo);
		    if (ResultConstants.SUCCESS_CODE.equals(resultBean.getStatus())) {
				SysTools.saveLog(user,
						StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
								"机器码管理", OptBehaviorConstants.LOG_SEPARATOR,
								OptBehaviorConstants.LOG_EXPORT));
			}
		   
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			resultBean.setStatus(ResultConstants.ERROR_CODE);
			resultBean.setMsg(ResultConstants.ERROR);
		} 
		 return resultBean;
	}
	
	@RequestMapping(value = "/baseMachineTypeImportCheck.do")
	@ResponseBody
	public String importCheck(HttpServletRequest request, String upload_type) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (this.multipartResolver != null && this.multipartResolver.isMultipart(request)) {
			if (request instanceof MultipartHttpServletRequest) {
				MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
				Map<String, MultipartFile> fileMap = mRequest.getFileMap();
				for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
					MultipartFile mf = entity.getValue();
					try {
						UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
						map = baseMachineTypeService.importCheck(mf, user);
					} catch (Exception e) {
						LOGGER.error(e.getMessage(),e);
						map.put("return_code", ResultConstants.ERROR);
					}
				}
			}
		}
		return new Gson().toJson(map);
	}
	
	@RequestMapping(value = "/baseMachineTypeImportData.do")
	@ResponseBody
	public Map<String, Object> importData(HttpServletRequest request, String paramData) {
		Map<String, Object> map = new HashMap<String, Object>();
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			map = baseMachineTypeService.importData(paramData, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			map.put("return_code", ResultConstants.ERROR);
		}
		
		if (ResultConstants.SUCCESS.equals(map.get("return_code"))) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							"机器码管理", OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_IMPORT));
		}
		return map;
	}
	
}
