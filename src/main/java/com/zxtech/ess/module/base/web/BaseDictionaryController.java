package com.zxtech.ess.module.base.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.zxtech.ess.app.util.wrap.ComboboxDefined;
import com.zxtech.ess.module.base.bean.BaseDictionarySearchBean;
import com.zxtech.ess.module.base.service.IBaseDictionaryService;
import com.zxtech.ess.module.gen.bean.BaseDictionary;
import com.zxtech.ess.module.pub.service.IPubService;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.vo.UserBean;

@Controller
@RequestMapping("/base")
public class BaseDictionaryController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(BaseDictionaryController.class);
	
	@Autowired
	private IBaseDictionaryService baseDictionaryService;
	
	/**
	 * 
	 * @param queryInfo
	 * @return
	 * @author: syp637
	 */
	@RequestMapping(value = "/fetchbasedictionarycomboboxlist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> fetchBaseDictionaryComboboxList(HttpServletRequest request, BaseDictionarySearchBean queryInfo) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		List<Map<String, Object>> resultComboboxList = SysTools.getResultComboboxList(baseDictionaryService.fetchBaseDictionaryComboboxList(queryInfo), () -> {
			return new ComboboxDefined("dict_code", "dict_name", queryInfo.getCombobox_type(), user);
	    });
		
		return resultComboboxList;
	}
	
	@RequestMapping(value = "/basedictionarylist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getListWithPaging(HttpServletRequest request, BaseDictionarySearchBean queryInfo) {
		return baseDictionaryService.getListWithPaging(queryInfo);
	}
	@RequestMapping(value = "/baseContractdictionarylist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getContractListWithPaging(HttpServletRequest request, BaseDictionarySearchBean queryInfo) {
		return baseDictionaryService.getContractListWithPaging(queryInfo);
	}
	@RequestMapping(value = "/baseHotLinedictionarylist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getHotLineListWithPaging(HttpServletRequest request, BaseDictionarySearchBean queryInfo) {
		return baseDictionaryService.getHotLineListWithPaging(queryInfo);
	}
	
	@RequestMapping(value = "/basedictionaryadd.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doAdd(HttpServletRequest request, BaseDictionary bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseDictionaryService.doAdd(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_DICTIONARY, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ADD));
		}
		return result;
	}
	
	@RequestMapping(value = "/basedictionarymodify.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doUpdate(HttpServletRequest request, BaseDictionary bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseDictionaryService.doUpdate(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_DICTIONARY, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MODIFY));
		}
		return result;
	}
	
	@RequestMapping(value = "/basedictionaryactive.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doActive(HttpServletRequest request, BaseDictionary bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseDictionaryService.doActive(bean, user);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_DICTIONARY, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ACTIVE));
		}
		return result;
	}
	
	@RequestMapping(value = "/basedictionaryinactive.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doInactive(HttpServletRequest request, BaseDictionary bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseDictionaryService.doInactive(bean, user);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_DICTIONARY, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_INACTIVE));
		}
		return result;
	}
	
	/**
	 * 
	 * @Title：getBaseDictionaryInfoByCode 
	 * @date ：2018年12月25日 下午3:16:36 
	 * @author ：syp814
	 * @Description：根据code 获取详细信息info
	 */
	@RequestMapping(value = "/basedictionaryinfobycode.do", method = { RequestMethod.POST })
	@ResponseBody
	public BaseDictionary getBaseDictionaryInfoByCode(HttpServletRequest request,BaseDictionary bean) {
		
		return baseDictionaryService.getBaseDictionaryInfoByCode(bean);
	}
	
	/**
	 * @author ：jic
	 * @Description：
	 */
	@RequestMapping(value = "/chinalistbytype.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getChinaListByType(HttpServletRequest request,BaseDictionary bean) {
		
		return baseDictionaryService.getChinaListByType(bean);
	}
	
	/**
	 * 
	 * @Title: getBaseDictListByType
	 * @Description: 获取维保字典-下拉框
	 * @param request
	 * @param queryInfo
	 * @param combobox_type
	 * @return List<SysDictionary>
	 * @throws
	 * @author max
	 */
	@RequestMapping(value = "/getBaseDictListByType.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<BaseDictionary> getBaseDictListByType(HttpServletRequest request, BaseDictionary queryInfo, String combobox_type) {
		return baseDictionaryService.getBaseDictListByType(queryInfo, combobox_type);
	}
	
	
}
