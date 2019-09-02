package com.zxtech.ess.module.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zxtech.ess.module.api.bean.Result;
import com.zxtech.ess.module.api.service.IotService;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/api")
public class IotController {
	
	@Autowired
	private IotService iotService;

	/**
	 * 更新物联网平台工号数据。
	 * @param param 
	 * @return
	 * @throws Exception
	 * @author syp660
	 */
	@RequestMapping(value = "/syncIotGetElevatorBase.api", method = {RequestMethod.POST})
	public Result syncIotGetElevatorBase(@RequestBody JSONObject param) throws Exception {
		Result result = iotService.syncIotGetElevatorBase(param);
		return result;
	}
	
	/**
	 * 根据工号更新运行次数、运行次数原始数据
	 * 2019年6月13日16:49:25
	 * @author syp660
	 */
	@RequestMapping(value = "/syncIotGetElevatorRun.api", method = {RequestMethod.POST})
	public Result syncIotGetElevatorRun(@RequestBody JSONObject param) throws Exception {
		Result result = iotService.syncIotGetElevatorRun(param);
		return result;
	}
}
