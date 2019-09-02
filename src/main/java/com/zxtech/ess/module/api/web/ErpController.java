package com.zxtech.ess.module.api.web;


import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zxtech.ess.module.api.bean.Result;
import com.zxtech.ess.module.api.service.ErpService;
import com.zxtech.ess.module.api.service.SiebelService;
import com.zxtech.ess.module.api.utils.OkHttpUtil;
import com.zxtech.ess.module.gen.bean.BaseElevator;
import com.zxtech.platform.context.PlatformGlobalVar;

import net.sf.json.JSONObject;

/**
 *基础数据 模块接口
 * @author syp661
 * @throws Exception 
 * 
 * */
@RestController
@RequestMapping("/api")
public class ErpController {
	
	private static Logger log = LoggerFactory.getLogger(ErpController.class);
	
	@Autowired
	private ErpService erpService;
	
	@Autowired
	private SiebelService siebelService;
	
	/**
	 *合同数据（含工号参数）
	 * @param param
	 * @author syp661
	 * @创建时间 2018/12/28
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/syncGetContractHead.api", method = {RequestMethod.POST})
	public Result syncGetContractHead(@RequestBody JSONObject param) {
		Result result = new Result();
		try {
			result = erpService.syncGetContractHead(param);
			//获取双开门层数
			List<BaseElevator> doubleList = (List<BaseElevator>) result.getRtnData();
			result.setRtnData(new HashMap<>());
			siebelService.syncSiebelGetDoubleDoorLayer(doubleList);
			//更新是否在保、是否最新
//			String url = PlatformGlobalVar.SYS_PROPERTIES.get("hmtPath") + "mt/updateContractStatus.job";
//			OkHttpUtil.getFunAsyn(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 *获取出厂安装信息
	 * @param param
	 * @author syp661
	 * @创建时间 2019/1/14
	 * 
	 * */
	@RequestMapping(value = "/syncGetOutAndInstallInfo.api", method = {RequestMethod.POST})
	public Result syncGetOutAndInstallInfo(@RequestBody JSONObject param) {
		Result result = new Result();
		try {
			result = erpService.syncGetOutAndInstallInfo(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}