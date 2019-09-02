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
import com.zxtech.ess.module.base.bean.BaseCompanySearchBean;
import com.zxtech.ess.module.base.bean.BaseUserCompanySearchBean;
import com.zxtech.ess.module.base.service.IBaseCompanyService;
import com.zxtech.ess.module.gen.bean.BaseCompany;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

@Controller
@RequestMapping("/base")
public class BaseCompanyController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(BaseCompanyController.class);
	
	@Autowired
	private IBaseCompanyService baseCompanyService;
	
	/**
	 * 获得公司树形菜单集合
	 * @param queryInfo
	 * @return record list
	 * @author wsm
	 */
	@RequestMapping(value = "/company/getcomptreelist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<Map<String, Object>> getCompTreeList(HttpServletRequest request) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		List<Map<String, Object>> list = baseCompanyService.getCompTreeList(user);
		return list;
	}
	
	@RequestMapping(value = "/company/getcompchiltreelist.do", method = {RequestMethod.POST})
	@ResponseBody
	public List<Map<String, Object>> getCompChilTreeList(HttpServletRequest request,String compyTpye, String id) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		List<Map<String, Object>> list = baseCompanyService.getCompChilTreeList(user, compyTpye, id);
		return list;
	}

	/**
	 * 增加机构
	 * @param request
	 * @param bean
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/basecompanyadd.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, BaseCompany bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseCompanyService.save(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_COMPANY, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ADD));
		}
		return result;
	}
	
	/**
	 * 修改机构
	 * @param request
	 * @param bean
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/basecompanymodify.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, BaseCompany bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseCompanyService.update(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_COMPANY, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MODIFY));
		}
		return result;
	}
	/**
	 * 禁用机构
	 * @param request
	 * @param bean
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/basecompanyinactive.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doDisable(HttpServletRequest request, BaseCompany bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseCompanyService.disable(bean, user);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_COMPANY, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_INACTIVE));
		}
		return result;
	}
	/**
	 * 启用机构
	 * @param request
	 * @param bean
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/basecompanyactive.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doAble(HttpServletRequest request, BaseCompany bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = baseCompanyService.able(bean, user);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_BASE_COMPANY, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_ACTIVE));
		}
		return result;
	}
	/**
	 * 导出机构列表
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/basecompanyexport.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResultBean export(HttpServletRequest request, HttpServletResponse response, BaseCompanySearchBean queryInfo) throws Exception  {
		ResultBean resultBean = new ResultBean();
		try {
			UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
			resultBean = baseCompanyService.export();
			if (ResultConstants.SUCCESS_CODE.equals(resultBean.getStatus())) {
				SysTools.saveLog(user,
						StringUtils.join(OptBehaviorConstants.LOG_MODULE_BASE, OptBehaviorConstants.LOG_SEPARATOR,
								OptBehaviorConstants.LOG_MENU_BASE_COMPANY, OptBehaviorConstants.LOG_SEPARATOR,
								OptBehaviorConstants.LOG_EXPORT));
			}
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage());
			resultBean.setStatus(ResultConstants.ERROR_CODE);
			resultBean.setMsg(ResultConstants.ERROR);
		}
		return resultBean;
	}
	/**
	 * Description: 获取分公司公司集合-下拉框用
	 * @author whx
	 */
	@RequestMapping(value = "/complist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> compList(HttpServletRequest request, BaseCompanySearchBean queryInfo, String combobox_type) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		queryInfo.setComp_type("3");
		return baseCompanyService.compList(queryInfo, combobox_type, user );
	}
	/**
	 * Description: 获取全部公司公司集合-下拉框用
	 * @author whx
	 */
	@RequestMapping(value = "/compAllList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> compAllList(HttpServletRequest request, BaseCompanySearchBean queryInfo, String combobox_type) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return baseCompanyService.compList(queryInfo, combobox_type, user );
	}
	/**
	 * Description: 获取全部公司公司集合-下拉框用-无权限限制
	 * @author syp799
	 */
	@RequestMapping(value = "/compAllListNoRight.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> compAllListNoRight(HttpServletRequest request, BaseCompanySearchBean queryInfo, String combobox_type) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return baseCompanyService.compListNoRight(queryInfo, combobox_type, user );
	}
	/**
	 * Description: 基础数据管理——机构同步分页列表
	 * @author whx
	 */
	@RequestMapping(value = "/compsynclistpage.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> compSyncListPage(HttpServletRequest request, BaseCompanySearchBean queryInfo) {
		return baseCompanyService.compSyncListPage(queryInfo);
	}
	
	/**
	 * Description: 基础数据管理——机构同步分页列表根据 “业务实体编码”、“ERP名称”查询
	 * @author jic
	 */
	@RequestMapping(value = "/compsyncbyerplistpage.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> compSyncByErpListPage(HttpServletRequest request, BaseCompanySearchBean queryInfo) {
		return baseCompanyService.compSyncByErpListPage(queryInfo);
	}
	/**
	 * Description: 根据登录人获取分公司公司集合-下拉框用
	 * @author jic
	 */
	@RequestMapping(value = "/usercomplist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> userCompList(HttpServletRequest request, BaseUserCompanySearchBean queryInfo, String combobox_type) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		queryInfo.setComp_type("3");
		return baseCompanyService.userCompList(queryInfo, combobox_type, user );
	}
	
	/**
	 * Description: 获取区域集合-下拉框用
	 * @author jic
	 */
	@RequestMapping(value = "/parealist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> parealist(HttpServletRequest request, BaseCompanySearchBean queryInfo, String combobox_type) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return baseCompanyService.pareaList(queryInfo, combobox_type, user );
	}
	
	/**
	 * Description: 获取区域下分公司集合-下拉框用
	 * @author jic
	 */
	@RequestMapping(value = "/areacomplist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> areaComplist(HttpServletRequest request, BaseCompanySearchBean queryInfo, String combobox_type) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return baseCompanyService.areaCompList(queryInfo, combobox_type, user );
	}
	
	/**
	 * 根据所属司查询区域
	 * @author jic
	 */
	@RequestMapping(value = "/getpareaidbycompid.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> getpareaidbycompid(HttpServletRequest request,BaseCompanySearchBean queryInfo) {
		return baseCompanyService.getpareaidbycompid(queryInfo);
	}
	
	/**
	 * @Title: getFirstLevelCompList
	 * @Description: 获取一级分公司列表-下拉框用
	 * @param request
	 * @param queryInfo
	 * @param combobox_type
	 * @return List<Map<String,Object>>
	 * @throws
	 * @author max
	 */
	@RequestMapping(value = "/fetchFirstLevelCompLst.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> getFirstLevelCompList(HttpServletRequest request, BaseCompanySearchBean queryInfo, String combobox_type){
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		return baseCompanyService.getFirstLevelCompList(queryInfo, combobox_type, user);
	}
}