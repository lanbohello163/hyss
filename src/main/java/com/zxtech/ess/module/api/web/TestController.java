package com.zxtech.ess.module.api.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zxtech.ess.module.api.bean.Result;
import com.zxtech.ess.module.api.service.TestService;

import net.sf.json.JSONObject;

/**
 * 测试接口
 * @author syp602
 *
 */
@RestController
@RequestMapping("/api")
public class TestController {
	
	@Autowired
	private TestService testService;
	
	/**
	 * 新维保主动请求数据
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getInfo.api", method = {RequestMethod.POST})
	public Result getInfo() throws Exception {
		return testService.getInfo();
	}
	
	/**
	 * 新维保被动接收数据
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receiveInfo.api", method = {RequestMethod.POST})
	public Result receiveInfo(@RequestBody JSONObject param) throws Exception {
		return testService.receiveInfo(param);
	}

	
	/**
	 * 新维保主动推送数据
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendInfo.api", method = {RequestMethod.POST})
	public Result sendInfo() throws Exception {
		return testService.sendInfo();
	}
}