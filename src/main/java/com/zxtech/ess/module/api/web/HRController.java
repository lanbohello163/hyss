package com.zxtech.ess.module.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zxtech.ess.module.api.bean.Result;
import com.zxtech.ess.module.api.service.HRService;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/api")
public class HRController {

	@Autowired
	private HRService hRService;
	
	/**
	 * 基础数据——员工（HR）
	 * @author syp661
	 * @throws Exception 
	 * 
	 * */
	@RequestMapping(value = "/syncStaffInfo.api", method = {RequestMethod.POST})
	public Result syncStaffInfo(@RequestBody JSONObject param) throws Exception {
		Result result = hRService.syncStaffInfo(param);
		return result;
	}
	
	/**
	 * 基础数据——员工培训信息（HR）
	 * @author syp661
	 * @throws Exception 
	 * 
	 * */
	@RequestMapping(value = "/syncStaffTrainInfo.api", method = {RequestMethod.POST})
	public Result syncStaffTrainInfo(@RequestBody JSONObject param) throws Exception {
		Result result= hRService.syncStaffTrainInfo(param);
		return result;
	}
	
	/**
	 * 基础数据——员工证书信息（HR）
	 * @author syp661
	 * @throws Exception 
	 * 
	 * */
	@RequestMapping(value = "/syncStaffCertificateInfo.api", method = {RequestMethod.POST})
	public Result syncStaffCertificateInfo(@RequestBody JSONObject param) throws Exception {
		Result result = hRService.syncStaffCertificateInfo(param);
		return result;
	}
	
	/**
	 * 获取维保人员信息
	 *
	 * @author syp660
	 * 创建时间：2019年6月10日下午4:26:48
	 */
	@RequestMapping(value = "/syncStaffGetTmEmpInfo.api", method = {RequestMethod.POST})
	public void syncStaffGetTmEmpInfo(@RequestBody JSONObject param) throws Exception {
		hRService.syncStaffGetTmEmpInfo(param);
	}
	
}
