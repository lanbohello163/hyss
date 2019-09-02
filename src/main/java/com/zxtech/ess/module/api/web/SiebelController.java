package com.zxtech.ess.module.api.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zxtech.ess.module.api.bean.Result;
import com.zxtech.ess.module.api.service.SiebelService;
import com.zxtech.ess.module.gen.bean.BaseElevator;

import net.sf.json.JSONObject;

/**
 *基础数据 模块接口
 * @author syp661
 * @throws Exception 
 * 
 * */
@RestController
@RequestMapping("/api")
public class SiebelController {
	
	@Autowired
	private SiebelService siebelService;

	/**
	 *基础数据——客户信息
	 *Siebel售前->ESB->新维保->ESB-> Siebel售前
	 *@param param
	 * @author syp661
	 * @创建时间 2018/12/28
	 * 
	 * */
	@RequestMapping(value = "/syncGetCustomerInfo.api", method = {RequestMethod.POST})
	public Result syncGetCustomerInfo(@RequestBody JSONObject param) {
		Result result = new Result();
		try {
			result = siebelService.syncGetCustomerInfo(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 基础数据——获取外厂梯
	 *Siebel售前->ESB->新维保->ESB-> Siebel售前
	 *@param param
	 * @author syp602
	 * @创建时间 2019/3/30
	 * 
	 */
	@RequestMapping(value = "/syncSiebelExternalFactoryEle.api", method = {RequestMethod.POST})
	public Result syncSiebelExternalFactoryEle(@RequestBody JSONObject param) {
		Result result = new Result();
		try {
			result = siebelService.syncSiebelExternalFactoryEle(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 基础数据——获取双开门层数
	 * 新维保->ESB->Siebel售前->ESB-> 新维保
	 * @param param
	 * @author syp602
	 * @创建时间 2019/4/12
	 * 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/syncSiebelGetDoubleDoorLayer.api", method = {RequestMethod.POST})
	public Result syncSiebelGetDoubleDoorLayer(@RequestBody JSONObject param) {
		Result result = new Result();
		try {
			List<BaseElevator> list = new ArrayList<>();
			List<Map<String, Object>> doubleList = (List<Map<String, Object>>) param.get("list");
			for (int i = 0; i < doubleList.size(); i++) {
				Map<String, Object> map = doubleList.get(i);
				BaseElevator baseElevator = new BaseElevator();
				baseElevator.setContract_code(String.valueOf(map.get("contract_code")));
				baseElevator.setAsset_num(String.valueOf(map.get("asset_num")));
				baseElevator.setId(Integer.valueOf(String.valueOf(map.get("id"))));
				baseElevator.setThrough_door(String.valueOf(map.get("through_door")));
				baseElevator.setEle_floor(Integer.valueOf(String.valueOf(map.get("ele_floor"))));
				baseElevator.setEle_door(Integer.valueOf(String.valueOf(map.get("ele_door"))));
				list.add(baseElevator);
			}
			result = siebelService.syncSiebelGetDoubleDoorLayer(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}