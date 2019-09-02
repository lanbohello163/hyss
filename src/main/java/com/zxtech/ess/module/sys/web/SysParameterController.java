package com.zxtech.ess.module.sys.web;

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
import com.zxtech.ess.module.gen.bean.SysParameter;
import com.zxtech.ess.module.sys.bean.SysParameterSearchBean;
import com.zxtech.ess.module.sys.service.ISysParameterService;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.vo.UserBean;

@Controller
@RequestMapping("/sys")
public class SysParameterController {

	private final static Logger LOGGER = LoggerFactory.getLogger(SysParameterController.class);
	
	@Autowired
	private ISysParameterService sysParameterService;
	
	/**
	 * 获取系统参数列表
	 * @param request
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/sysparameterlist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> baseAreaPagingList(HttpServletRequest request,SysParameterSearchBean bean) {
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		bean.setDataPermissionList(user);
		return sysParameterService.sysParameterPagingList(bean);
	}
	
	/**
	 * 修改系统参数
	 * @param request
	 * @param bean
	 * @return
	 * @author wsm
	 */
	@RequestMapping(value = "/sysparametermodify.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, SysParameter bean) {
		String result = ResultConstants.SUCCESS;
		UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		try {
			result = sysParameterService.update(bean, user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = ResultConstants.ERROR;
		}
		if (ResultConstants.SUCCESS.equals(result)) {
			SysTools.saveLog(user,
					StringUtils.join(OptBehaviorConstants.LOG_MODULE_SYS, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MENU_SYS_PARAMETER, OptBehaviorConstants.LOG_SEPARATOR,
							OptBehaviorConstants.LOG_MODIFY));
		}
		return result;
	}
	
	/**
	 * 根据参数key获取参数value
	 * @author whx
	 */
	@RequestMapping(value = "/getsysparametervaluebykey.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public SysParameter getSysParameterValueByKey(HttpServletRequest request, SysParameter bean) {
		return sysParameterService.getSysParameterValueByKey(bean);
	}
}
