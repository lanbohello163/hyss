package com.zxtech.ess.module.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxtech.ess.app.util.SysConstants;
import com.zxtech.ess.module.api.bean.Result;
import com.zxtech.ess.module.api.service.IotService;
import com.zxtech.ess.module.api.utils.HttpUtil;
import com.zxtech.ess.module.api.utils.QueueUtil;
import com.zxtech.ess.module.api.utils.ResultUtil;
import com.zxtech.ess.module.gen.bean.BaseElevator;
import com.zxtech.ess.module.gen.mapper.BaseElevatorMapper;
import com.zxtech.platform.context.PlatformGlobalVar;
import com.zxtech.platform.utils.StringUtil;

import net.sf.json.JSONObject;

@Service
public class IotServiceImpl implements IotService{
	
	@Autowired 
	HttpServletRequest request;
	
	@Autowired
	private BaseElevatorMapper baseElevatorMapper;

	@Override
	@SuppressWarnings("unchecked")
	public Result syncIotGetElevatorBase(JSONObject param) throws Exception {
		Result result;
		String httpcode = PlatformGlobalVar.SYS_PROPERTIES.get("hesbPath") + "/syncIotGetElevatorBase.api";
		try {
			Result resultObj = HttpUtil.doPostWithParam(httpcode, param);
			if (ResultUtil.SUCCESS_FLG.equals(resultObj.getRtnCode())) {
				Map<String, String> rtnData = (Map<String, String>) resultObj.getRtnData();
				if(MapUtils.isNotEmpty(rtnData)) {
					BaseElevator dbBean = new BaseElevator();
					dbBean.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
					List<BaseElevator> dbList = baseElevatorMapper.selectBySqlConditions(dbBean);
					if(CollectionUtils.isNotEmpty(dbList)) {
						Map<String, BaseElevator> dbMap = new HashMap<>();
						for(BaseElevator bEla : dbList) {
							dbMap.put(bEla.getAsset_num(), bEla);
						}
						if(dbMap.containsKey(StringUtil.toSafeString(param.get("ASSET_NUM")))) {
							BaseElevator record = new BaseElevator();
							record.setControl_operation_series(StringUtil.toSafeString(rtnData.get("ControlOperationSeries")));
							record.setControl_operation_type(StringUtil.toSafeString(rtnData.get("ControlOperationType")));
							record.setEle_model(StringUtil.toSafeString(rtnData.get("ModelType")));
							record.setManufacture_licence(StringUtil.toSafeString(rtnData.get("LicenceNum"))); 
							record.setMonitor_teminal_type(StringUtil.toSafeString(rtnData.get("PROTOCOL_DESC")));
							record.setId(dbMap.get(StringUtil.toSafeString(param.get("ASSET_NUM"))).getId());
							baseElevatorMapper.updateByPrimaryKeySelective(record);
						}
					}
				}
				result = ResultUtil.resultSuccess(new HashMap<>());
				QueueUtil.put(1, 1, httpcode, StringUtil.stringConvertor(param),
						StringUtil.stringConvertor(JSONObject.fromObject(resultObj)), "物联网平台-电梯基础数据", "");
			} else {
				result = resultObj;
				QueueUtil.put(2, 1, httpcode, StringUtil.stringConvertor(param),
						StringUtil.stringConvertor(JSONObject.fromObject(resultObj)), "物联网平台-电梯基础数据", resultObj.getRtnMsg());
			}
		} catch (Exception e) {
			String exceptionStr = ExceptionUtils.getStackTrace(e);
			result = ResultUtil.resultFail(exceptionStr);
			QueueUtil.put(2, 1, httpcode, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "物联网平台-电梯基础数据", exceptionStr);
			throw new RuntimeException();
		}
		
		return result;
	}

	/**
	 * 物联网-电梯运行次数-更新工号
	 *
	 * @author syp660
	 * 创建时间：2019年6月13日下午5:01:56
	 */
	@Override
	public Result syncIotGetElevatorRun(JSONObject param) throws Exception {
		Result result;
		String url = request.getRequestURL().toString();
		try {
			result = ResultUtil.resultSuccess(new HashMap<>());
			List<Map<String, Object>> list = (List<Map<String, Object>>) param.get("list");
			if(CollectionUtils.isNotEmpty(list)) {
				List<BaseElevator> l = baseElevatorMapper.selectAll();
				Map<String, BaseElevator> dbm = new HashMap<>();
				for(BaseElevator b : l) {
					dbm.put(b.getAsset_num(), b);
				}
				
				for(Map<String, Object> m : list) {
					BaseElevator be = new BaseElevator();
					be.setAsset_num(StringUtil.toSafeString(m.get("ASSET_NUM")));
					if(dbm.containsKey(StringUtil.toSafeString(m.get("ASSET_NUM")))) {
						be.setId(dbm.get(StringUtil.toSafeString(m.get("ASSET_NUM"))).getId());
						be.setRun_reading((Integer)m.get("RUN_TIMES"));
						be.setTotal_reading((Integer)m.get("RUN_TIMES_OR"));
						baseElevatorMapper.updateByPrimaryKeySelective(be);
					}
				}
				QueueUtil.put(1, 2, url, StringUtil.stringConvertor(param),
						StringUtil.stringConvertor(JSONObject.fromObject(result)), "物联网-电梯运行次数-更新工号", "");
			}
		} catch (Exception e) {
			//获取异常数据
			String exceptionStr = ExceptionUtils.getStackTrace(e);
			result = ResultUtil.resultFail(exceptionStr);
			//保存错误日志
			QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "基础数据——员工（HR）", exceptionStr);
			throw new RuntimeException();
		}
		return result;
	}
	
}
