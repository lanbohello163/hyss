package com.zxtech.ess.module.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zxtech.ess.module.api.bean.Result;
import com.zxtech.ess.module.api.service.ScreenService;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/api")
public class ScreenController {

	@Autowired
	private ScreenService screenService;
	
	/**
	 * 接口-人员定位
	 * @author syp661
	 * @throws Exception 
	 * 
	 * */
	@RequestMapping(value = "/syncEmpPosition.api", method = {RequestMethod.POST})
	public Result syncEmpPosition(@RequestBody JSONObject param) throws Exception {
		return screenService.syncEmpPosition(param);
	}
	
	/**
	 * 接口-人员打卡接口
	 * @author syp661
	 * @throws Exception 
	 * 
	 * */
	@RequestMapping(value = "/syncEmpClock.api", method = {RequestMethod.POST})
	public Result syncEmpClock(@RequestBody JSONObject param) throws Exception {
		return screenService.syncEmpClock(param);
	}
	
}
