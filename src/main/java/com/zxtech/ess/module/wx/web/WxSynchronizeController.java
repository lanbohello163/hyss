package com.zxtech.ess.module.wx.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxtech.ess.module.wx.service.IWxSynchronizeService;

@Controller
@RequestMapping("/wx")
public class WxSynchronizeController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(WxSynchronizeController.class);
	
	@Autowired
	private IWxSynchronizeService wxSynchronizeService;
	
	/**
	 * 
	 * @Title: WxSynchronizeElevatorInfo
	 * @Description: 定时任务-查询所有需要同步的电梯信息
	 * @param request
	 * @param response
	 * @return Result
	 * @throws Exception 
	 * @throws
	 * @author max
	 */
	@RequestMapping(value = "/synchronizeElevatorInfo.wx", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String WxSynchronizeElevatorInfo(String last_sync_time) {
		String param ="";
		try {
			param = wxSynchronizeService.wxSynchronizeElevatorInfo(last_sync_time);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return param;
	}
	
	/**
	 * 
	 * @Title: WxSynchronizeUpdateElevatorInfo
	 * @Description: 定时任务-查询所有需要更新的电梯信息
	 * @param request
	 * @param response
	 * @return Result
	 * @throws Exception 
	 * @throws
	 * @author lanb
	 */
	@RequestMapping(value = "/synchronizeUpdateElevatorInfo.wx", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String WxSynchronizeUpdateElevatorInfo(String last_sync_time) {
		String param ="";
		try {
			param = wxSynchronizeService.WxSynchronizeUpdateElevatorInfo(last_sync_time);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return param;
	}
}




