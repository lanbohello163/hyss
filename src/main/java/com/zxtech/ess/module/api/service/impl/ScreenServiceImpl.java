package com.zxtech.ess.module.api.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;
import com.zxtech.ess.module.api.bean.Result;
import com.zxtech.ess.module.api.mapper.ScreenManagerMapper;
import com.zxtech.ess.module.api.service.ScreenService;
import com.zxtech.ess.module.api.utils.QueueUtil;
import com.zxtech.ess.module.api.utils.ResultUtil;
import com.zxtech.platform.utils.JsonUtil;
import com.zxtech.platform.utils.StringUtil;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import net.sf.json.JSONObject;

@Service
public class ScreenServiceImpl implements ScreenService{

	@Autowired
	private ScreenManagerMapper screenManagerMapper;
	
	@Autowired 
	HttpServletRequest request;
	
	private static GsonBuilder gsonBulder = new GsonBuilder();
	
	static{
		gsonBulder.registerTypeAdapter(java.sql.Timestamp.class, JsonUtil.TIMESTAMP);
		gsonBulder.registerTypeAdapter(java.sql.Date.class, JsonUtil.DATE);
	}
	
	/**
	 * 人员定位
	 * syp660
	 */
	@Override
	public Result syncEmpPosition(JSONObject param) throws Exception {
		Result result;
		String url = request.getRequestURL().toString();
		try {
			//经度
			String lon = StringUtil.toSafeString(param.get("dimension"));
			//纬度
			String lat = StringUtil.toSafeString(param.get("latitude"));
			if("".equals(lon) || "".equals(lat)) {
				result = ResultUtil.resultFail("经度(dimension) 或 纬度(latitude) 不能为空！ ");
				return result;
			}
			Map<String, Object> map = new HashMap<>();
			map.put("lon", lon);
			map.put("lat", lat);
			List<Map<String, Object>> list = screenManagerMapper.syncEmpPosition(map);
			if(null != list && list.size() > 0) {
				Map<String, Object> retMap = new HashMap<>();
				retMap.put("list", list);
				result = ResultUtil.resultSuccess(retMap);
			} else {
				result = ResultUtil.resultFail("新维保系统中未检索到-人员定位-数据");
			}
			QueueUtil.put(1, 2, url, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "维保业务——人员定位", "");
		} catch (Exception e) {
			String exceptionStr = ExceptionUtils.getStackTrace(e);
			result = ResultUtil.resultFail(exceptionStr);
			QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "维保业务——人员定位", exceptionStr);
			throw new RuntimeException();
		}
		return result;
	}

	/**
	 * 人员打卡- V2.6新增
	 * syp660
	 */
	@Override
	public Result syncEmpClock(JSONObject param) throws Exception {
		Result result;
		String url = request.getRequestURL().toString();
		try {
			String companyId = StringUtil.toSafeString(param.get("companyId"));
			if("".equals(companyId)) {
				result = ResultUtil.resultFail("分公司id不能为空！ ");
				return result;
			}
			Map<String, Object> mapParam = new HashMap<>();
			mapParam.put("companyId", companyId);
			List<Map<String, Object>> list = screenManagerMapper.syncEmpClock(mapParam);
			if (CollectionUtil.isNotEmpty(list)) {
				Map<String, StringBuffer> checkMap = new HashMap<>();
				for(Map<String, Object> m : list) {
					String mKey =  StringUtil.toSafeString(m.get("userId") + "_" + m.get("clockDate"));
					StringBuffer temp = new StringBuffer();
					if(checkMap.containsKey(mKey)){
						temp = checkMap.get(mKey);
					}
					checkMap.put(mKey, temp.append("," + StringUtil.toSafeString(m.get("position_type")).trim() + ","));
					m.remove("position_type");
				}
				if(MapUtil.isNotEmpty(checkMap)) {
					Iterator<Map.Entry<String, StringBuffer>> entries = checkMap.entrySet().iterator(); 
					while (entries.hasNext()) {
						Map.Entry<String, StringBuffer> entry = entries.next();
						String k = entry.getKey(); 
						StringBuffer v = entry.getValue(); 
						if(v.indexOf(",1,") < 0 || v.indexOf(",3,") < 0 || v.indexOf(",4,") < 0 || v.indexOf(",5,") < 0) {
							checkMap.remove(k);
						}
					}
				}
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> m = list.get(i);
					String mKey =  StringUtil.toSafeString(m.get("userId") + "_" + m.get("clockDate"));
					if (!checkMap.containsKey(mKey)) {
						list.remove(i);
						i--;
					}
				}
				Map<String, Object> retMap = new HashMap<>();
				retMap.put("list", list);
				result = ResultUtil.resultSuccess(retMap);
			} else {
				result = ResultUtil.resultFail("新维保系统中未检索到-人员打卡-数据");
			}
			
			QueueUtil.put(1, 2, url, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "维保业务——人员打卡", "");
		} catch (Exception e) {
			String exceptionStr = ExceptionUtils.getStackTrace(e);
			result = ResultUtil.resultFail(exceptionStr);
			QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "维保业务——人员打卡", exceptionStr);
			throw new RuntimeException();
		}
		return result;
	}

}
