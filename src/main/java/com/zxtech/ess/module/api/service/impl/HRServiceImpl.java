package com.zxtech.ess.module.api.service.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zxtech.ess.app.util.SysConstants;
import com.zxtech.ess.module.api.bean.Result;
import com.zxtech.ess.module.api.mapper.HRManagerMapper;
import com.zxtech.ess.module.api.service.HRService;
import com.zxtech.ess.module.api.utils.ApiJsonUtil;
import com.zxtech.ess.module.api.utils.HttpUtil;
import com.zxtech.ess.module.api.utils.QueueUtil;
import com.zxtech.ess.module.api.utils.ResultUtil;
import com.zxtech.ess.module.gen.bean.BaseEmpCertificate;
import com.zxtech.ess.module.gen.bean.BaseEmployee;
import com.zxtech.ess.module.gen.bean.BaseTraining;
import com.zxtech.ess.module.gen.mapper.BaseEmpCertificateMapper;
import com.zxtech.ess.module.gen.mapper.BaseEmployeeMapper;
import com.zxtech.ess.module.gen.mapper.BaseTrainingMapper;
import com.zxtech.platform.context.PlatformGlobalVar;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.JsonUtil;
import com.zxtech.platform.utils.StringUtil;

import net.sf.json.JSONObject;

@Service
public class HRServiceImpl implements HRService{


	@Autowired
	private BaseEmployeeMapper baseEmployeeMapper;

	@Autowired
	private BaseTrainingMapper baseTrainingMapper;
	
	@Autowired 
	private HttpServletRequest request;
	
	@Autowired
	private BaseEmpCertificateMapper baseEmpCertificateMapper;
	
	@Autowired
	private HRManagerMapper hRManagerMapper;
	
	private static GsonBuilder gsonBulder = new GsonBuilder();
	
	static{
		gsonBulder.registerTypeAdapter(java.sql.Timestamp.class, JsonUtil.TIMESTAMP);
		gsonBulder.registerTypeAdapter(java.sql.Date.class, ApiJsonUtil.DATE);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public Result syncStaffInfo(JSONObject param) throws Exception {
		Result result;
		String url = request.getRequestURL().toString();
		try {
			List<BaseEmployee> list = gsonBulder.create().fromJson(new Gson().toJson(param.get("list")), 
					new TypeToken<List<BaseEmployee>>() {}.getType());
			if(null != list && list.size() > 0){
				BaseEmployee record = new BaseEmployee();
				List<BaseEmployee> dbList = baseEmployeeMapper.selectBySqlConditions(record);
				Map<String,BaseEmployee> dbMap = new HashMap<>();
				if(null != dbList && dbList.size() >0) {
					for(BaseEmployee b : dbList) {
						if(null != b.getEmp_code()) {
							dbMap.put(b.getEmp_code(), b);
						}
					}
				}
				
				List<Map<String, Object>> compIdList =  hRManagerMapper.getCompIdListByCompCode();
				Map<String, Object> compIdMap = new HashMap<>();
				if(null != compIdList && compIdList.size() > 0) {
					for(Map<String, Object> o : compIdList) {
						compIdMap.put(StringUtil.toSafeString(o.get("hr_comp_code")), o);
					}
				}
				
				for(BaseEmployee bem : list) {
					// 验证员工是否存在，
					if (StringUtil.isNotBlank(bem.getEmp_code())) {
						if (dbMap.containsKey(bem.getEmp_code())) {
							BaseEmployee bemUpdate = dbMap.get(bem.getEmp_code());
							if (bemUpdate.getSource_id() == null || "".equals(bemUpdate.getSource_id())) {
								bemUpdate.setSource_id(bem.getSource_id());
							}
							bemUpdate.setEmp_name(bem.getEmp_name());
							if("男".equals(bem.getEmp_sex())) {
								bemUpdate.setEmp_sex("1");
							} else if ("女".equals(bem.getEmp_sex())) {
								bemUpdate.setEmp_sex("2");
							}
							//bemUpdate.setId_number(bem.getId_number());
							//bemUpdate.setBorn_date(bem.getBorn_date());
							bemUpdate.setEmp_position(bem.getEmp_position());
							if (bemUpdate.getWork_emp_level() == null || "".equals(bemUpdate.getWork_emp_level())) {
								if ("见习保养员".equals(bemUpdate.getEmp_position())
										|| "一级维保工".equals(bemUpdate.getEmp_position())
										|| "保养工".equals(bemUpdate.getEmp_position())) {
									bemUpdate.setWork_emp_level("2");
								} else if ("保养员".equals(bemUpdate.getEmp_position())
										|| "二级维保工".equals(bemUpdate.getEmp_position())
										|| "维保小组长".equals(bemUpdate.getEmp_position())) {
									bemUpdate.setWork_emp_level("3");
								} else if ("初级保养工程师".equals(bemUpdate.getEmp_position())) {
									bemUpdate.setWork_emp_level("4");
								} else if ("中级保养工程师".equals(bemUpdate.getEmp_position())) {
									bemUpdate.setWork_emp_level("5");
								} else if ("高级保养工程师".equals(bemUpdate.getEmp_position())) {
									bemUpdate.setWork_emp_level("6");
								} else if ("资深保养工程师".equals(bemUpdate.getEmp_position())) {
									bemUpdate.setWork_emp_level("7");
								}
							}
							bemUpdate.setPosition_sequence(bem.getPosition_sequence());
							if(bem.getEmp_position() == null) bem.setEmp_position("");
							if("保养工".equals(bem.getPosition_sequence()) && bem.getEmp_position().indexOf("急修") < 0) {
								bemUpdate.setPda_position("1");
							} else if (bem.getEmp_position().indexOf("站长") > -1 || 
										bem.getEmp_position().indexOf("片长") > -1 || 
										bem.getEmp_position().indexOf("站、组长") > -1) {
								bemUpdate.setPda_position("3");
							} else {
								bemUpdate.setPda_position("7");
							}
							if (bemUpdate.getEmp_tel() == null || "".equals(String.valueOf(bemUpdate.getEmp_tel()))) {
								bemUpdate.setEmp_tel(bem.getEmp_tel());
							}
							if(compIdMap.containsKey(bem.getHr_comp_code())) {
								Map<String, Object> compTemp = (Map<String, Object>) compIdMap.get(bem.getHr_comp_code());
								bemUpdate.setHr_comp_name(String.valueOf(compTemp.get("erp_comp_name")));
								if (compTemp.containsKey("id") && compTemp.get("id") != null && !"".equals(String.valueOf(compTemp.get("id")))) {
									bemUpdate.setComp_id(Integer.parseInt(String.valueOf(compTemp.get("id"))));
								}
							}
							//bemUpdate.setEntry_date(bem.getEntry_date());
							bemUpdate.setDeparture_date(bem.getDeparture_date());
							if (bem.getDeparture_date() != null) {
								bemUpdate.setEnable_flag(SysConstants.UNENABLE_FLAG);
							} else {
								bemUpdate.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
							}
							bemUpdate.setDeparture_reason(bem.getDeparture_reason());
							bemUpdate.setEmploy_nature(bem.getEmploy_nature());

							bemUpdate.setLast_update_user("system");
							bemUpdate.setLast_update_timestamp(DateUtil.getNowTimestamp());
							baseEmployeeMapper.updateByPrimaryKey(bemUpdate);
						} else {
							if("男".equals(bem.getEmp_sex())) {
								bem.setEmp_sex("1");
							} else if ("女".equals(bem.getEmp_sex())) {
								bem.setEmp_sex("2");
							}
							if ("见习保养员".equals(bem.getEmp_position())
									|| "一级维保工".equals(bem.getEmp_position())
									|| "保养工".equals(bem.getEmp_position())) {
								bem.setWork_emp_level("2");
							} else if ("保养员".equals(bem.getEmp_position())
									|| "二级维保工".equals(bem.getEmp_position())
									|| "维保小组长".equals(bem.getEmp_position())) {
								bem.setWork_emp_level("3");
							} else if ("初级保养工程师".equals(bem.getEmp_position())) {
								bem.setWork_emp_level("4");
							} else if ("中级保养工程师".equals(bem.getEmp_position())) {
								bem.setWork_emp_level("5");
							} else if ("高级保养工程师".equals(bem.getEmp_position())) {
								bem.setWork_emp_level("6");
							} else if ("资深保养工程师".equals(bem.getEmp_position())) {
								bem.setWork_emp_level("7");
							}
							if(compIdMap.containsKey(bem.getHr_comp_code())) {
								Map<String, Object> compTemp = (Map<String, Object>) compIdMap.get(bem.getHr_comp_code());
								bem.setHr_comp_name(String.valueOf(compTemp.get("erp_comp_name")));
								if (compTemp.containsKey("id") && compTemp.get("id") != null && !"".equals(String.valueOf(compTemp.get("id")))) {
									bem.setComp_id(Integer.parseInt(String.valueOf(compTemp.get("id"))));
								}
							}
							if(bem.getEmp_position() == null) bem.setEmp_position("");
							if("保养工".equals(bem.getPosition_sequence()) && bem.getEmp_position().indexOf("急修") < 0) {
								bem.setPda_position("1");
							} else if (bem.getEmp_position().indexOf("站长") > -1 || 
										bem.getEmp_position().indexOf("片长") > -1 || 
										bem.getEmp_position().indexOf("站、组长") > -1) {
								bem.setPda_position("3");
							} else {
								bem.setPda_position("7");
							}
							if (bem.getDeparture_date() != null) {
								bem.setEnable_flag(SysConstants.UNENABLE_FLAG);
							} else {
								bem.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
							}
							bem.setType_hotline("0");
							bem.setType_regular_check("0");
							bem.setType_casual_check("0");
							bem.setType_mt("0");
							bem.setType_rota_engineer("0");
							bem.setEmp_type("1");
							bem.setCreate_user("system");
							bem.setCreate_timestamp(DateUtil.getNowTimestamp());
							baseEmployeeMapper.insert(bem);
						}
					}
				}
			}
			result = ResultUtil.resultSuccess(new HashMap<>());
			//保存同步日志
			QueueUtil.put(1, 2, url, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "基础数据——员工（HR）", "");
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
	
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public Result syncStaffTrainInfo(JSONObject param) throws Exception {
		
		Result result;
		String url = request.getRequestURL().toString();
		
		try {
			List<BaseTraining> list = gsonBulder.create().fromJson(new Gson().toJson(param.get("list")), 
					new TypeToken<List<BaseTraining>>() {}.getType());
			if(null != list && list.size() > 0){
				
				BaseTraining record = new BaseTraining();
				record.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
				List<BaseTraining> dbList = baseTrainingMapper.selectBySqlConditions(record);
				Map<String, BaseTraining> dbMap =new  HashMap<>();
				for(BaseTraining b : dbList) {
					dbMap.put(b.getSource_id(), b);
				}
				
				for(BaseTraining bt : list) {
					// 人员培训情况 修改、新增
					if (StringUtil.isNotBlank(bt.getSource_id())) {
						if(dbMap.containsKey(bt.getSource_id())) {
							bt.setId(dbMap.get(bt.getSource_id()).getId());
							bt.setLast_update_user("system");
							bt.setLast_update_timestamp(DateUtil.getNowTimestamp());
							baseTrainingMapper.updateByPrimaryKeySelective(bt);
						} else {
							bt.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
							bt.setCreate_user("system");
							bt.setCreate_timestamp(DateUtil.getNowTimestamp());
							baseTrainingMapper.insert(bt);
						}
					}
				}
			}
			//保存操作信息
			result = ResultUtil.resultSuccess(new HashMap<>());
			//保存同步日志
			QueueUtil.put(1, 2, url, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "基础数据——员工培训信息（HR）", "");
		} catch (Exception e) {
			//获取异常数据
			String exceptionStr = ExceptionUtils.getStackTrace(e);
			result = ResultUtil.resultFail(exceptionStr);
			//保存错误日志
			QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "基础数据——员工培训信息（HR）", exceptionStr);
			throw new RuntimeException();
		}
		return result;
	}
	
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public Result syncStaffCertificateInfo(JSONObject param) throws Exception {
		
		Result result;
		String url = request.getRequestURL().toString();
		
		try {
			List<BaseEmpCertificate> list = gsonBulder.create().fromJson(new Gson().toJson(param.get("list")), 
					new TypeToken<List<BaseEmpCertificate>>() {}.getType());
			if(null != list && list.size() > 0){
				
				BaseEmpCertificate record = new BaseEmpCertificate();
				record.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
				List<BaseEmpCertificate> dbList = baseEmpCertificateMapper.selectBySqlConditions(record);
				Map<String, BaseEmpCertificate> dbMap =new  HashMap<>();
				for(BaseEmpCertificate b : dbList) {
					dbMap.put(b.getSource_id(), b);
				}
				
				for(BaseEmpCertificate bec : list) {
					if (StringUtil.isNotBlank(bec.getSource_id())) {
						if (dbMap.containsKey(bec.getSource_id())) {
							bec.setId(dbMap.get(bec.getSource_id()).getId());
							bec.setLast_update_user("system");
							bec.setLast_update_timestamp(DateUtil.getNowTimestamp());
							baseEmpCertificateMapper.updateByPrimaryKeySelective(bec);
						} else {
							bec.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
							bec.setCreate_user("system");
							bec.setCreate_timestamp(DateUtil.getNowTimestamp());
							baseEmpCertificateMapper.insert(bec);
						}
					}
				}
			}
			//返回数据对象，Object类型，若不需要返回数据，则返回一各空Mao，不要返回null
			result = ResultUtil.resultSuccess(new HashMap<>());
			//保存同步日志
			QueueUtil.put(1, 2, url, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "基础数据——员工证书信息（HR）", "");
		} catch (Exception e) {
			//获取异常数据
			String exceptionStr = ExceptionUtils.getStackTrace(e);
			result = ResultUtil.resultFail(exceptionStr);
			//保存错误日志
			QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "基础数据——员工证书信息（HR）", exceptionStr);
			
			throw new RuntimeException();
		}
		
		return result;
	}

	/**
	 * 获取维保人员信息
	 * 
	 * @author syp660
	 * 创建时间：2019年6月10日下午5:13:47
	 */
	@SuppressWarnings({ "unchecked", "serial" })
	@Override
	public void syncStaffGetTmEmpInfo(JSONObject param) throws Exception {
		Calendar calendar = Calendar.getInstance();  
		calendar.add(Calendar.MONTH, -1);
		//得到上月初  
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));  
      	Date strDateFrom = calendar.getTime();  
      	//得到上月末  
      	calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
      	Date strDateTo = calendar.getTime();  
      	String sync_start_date = DateUtil.date2String(strDateFrom) + DateUtil.DAY_START_TIME;
      	String sync_end_date = DateUtil.date2String(strDateTo) +  DateUtil.DAY_END_TIME;
      	param.put("sync_start_date", sync_start_date);
      	param.put("sync_end_date", sync_end_date);
      	
      	String httpcode = PlatformGlobalVar.SYS_PROPERTIES.get("hesbPath") + "/syncStaffGetTmEmpInfo.api";
      	Result result;
      	try{
			Result resultObj = HttpUtil.doPostWithParam(httpcode, param);
			if (ResultUtil.SUCCESS_FLG.equals(resultObj.getRtnCode())) {
				List<Map<String, Object>> list = (List<Map<String, Object>>) ((Map<String, Object>)resultObj.getRtnData()).get("list");
				List<Map<String, Object>> train_list = (List<Map<String, Object>>) ((Map<String, Object>)resultObj.getRtnData()).get("train_list");
				List<Map<String, Object>> certificate_list = (List<Map<String, Object>>) ((Map<String, Object>)resultObj.getRtnData()).get("certificate_list");
				updateStaffInfo(JSONObject.fromObject(new HashMap<String, Object>(){{put("list", list);}}));
				updateStaffTrainInfo(JSONObject.fromObject(new HashMap<String, Object>(){{put("list", train_list);}}));
				updateStaffCertificateInfo(JSONObject.fromObject(new HashMap<String, Object>(){{put("list", certificate_list);}}));
				QueueUtil.put(1, 1, httpcode, StringUtil.stringConvertor(param),
						StringUtil.stringConvertor(JSONObject.fromObject(resultObj)), "接口-获取维保人员信息", "");
			} else {
				QueueUtil.put(2, 1, httpcode, StringUtil.stringConvertor(param),
						StringUtil.stringConvertor(JSONObject.fromObject(resultObj)), "接口-获取维保人员信息", resultObj.getRtnMsg());
			}
      	} catch (Exception e) {
			String exceptionStr = ExceptionUtils.getStackTrace(e);
			result = ResultUtil.resultFail(exceptionStr);
			QueueUtil.put(2, 1, httpcode, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "接口-获取维保人员信息", exceptionStr);
			throw new RuntimeException();
      	}
	}
	
	private void updateStaffInfo(JSONObject param) throws Exception{
		List<BaseEmployee> list = gsonBulder.create().fromJson(new Gson().toJson(param.get("list")), 
				new TypeToken<List<BaseEmployee>>() {}.getType());
		if(CollectionUtils.isNotEmpty(list)) {
			List<BaseEmployee> dbList = baseEmployeeMapper.selectAll();
			Map<String,BaseEmployee> dbMap = new HashMap<>();
			if(null != dbList && dbList.size() >0) {
				for(BaseEmployee b : dbList) {
					if(null != b.getEmp_code()) {
						dbMap.put(b.getEmp_code(), b);
					}
				}
			}
			for(BaseEmployee bb : list) {
				if(dbMap.containsKey(bb.getEmp_code())) {
					bb.setId(dbMap.get(bb.getEmp_code()).getId());
					baseEmployeeMapper.updateByPrimaryKeySelective(bb);
				}
			}
		}
	}
	
	private void updateStaffTrainInfo (JSONObject param) throws Exception{
		List<BaseTraining> list = gsonBulder.create().fromJson(new Gson().toJson(param.get("list")), 
				new TypeToken<List<BaseTraining>>() {}.getType());
		if(CollectionUtils.isNotEmpty(list)){
			BaseTraining record = new BaseTraining();
			record.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
			List<BaseTraining> dbList = baseTrainingMapper.selectBySqlConditions(record);
			Map<String, BaseTraining> dbMap =new  HashMap<>();
			for(BaseTraining b : dbList) {
				dbMap.put(b.getSource_id(), b);
			}
			for(BaseTraining bt : list) {
				// 人员培训情况 修改、新增
				if (StringUtil.isNotBlank(bt.getSource_id())) {
					bt.setSync_datetime(DateUtil.getNowTimestamp());
					bt.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
					bt.setLast_update_user("system");
					bt.setLast_update_timestamp(DateUtil.getNowTimestamp());
					if(dbMap.containsKey(bt.getSource_id())) {
						bt.setId(dbMap.get(bt.getSource_id()).getId());
						baseTrainingMapper.updateByPrimaryKeySelective(bt);
					} else {
						bt.setCreate_user("system");
						bt.setCreate_timestamp(DateUtil.getNowTimestamp());
						baseTrainingMapper.insert(bt);
					}
				}
			}
		}
	}
	
	private void updateStaffCertificateInfo (JSONObject param) throws Exception{
		List<BaseEmpCertificate> list = gsonBulder.create().fromJson(new Gson().toJson(param.get("list")), 
				new TypeToken<List<BaseEmpCertificate>>() {}.getType());
		if(CollectionUtils.isNotEmpty(list)){
			BaseEmpCertificate record = new BaseEmpCertificate();
			record.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
			List<BaseEmpCertificate> dbList = baseEmpCertificateMapper.selectBySqlConditions(record);
			Map<String, BaseEmpCertificate> dbMap =new  HashMap<>();
			for(BaseEmpCertificate b : dbList) {
				dbMap.put(b.getSource_id(), b);
			}
			
			for(BaseEmpCertificate bec : list) {
				if (StringUtil.isNotBlank(bec.getSource_id())) {
					bec.setSync_datetime(DateUtil.getNowTimestamp());
					bec.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
					bec.setLast_update_user("system");
					bec.setLast_update_timestamp(DateUtil.getNowTimestamp());
					if (dbMap.containsKey(bec.getSource_id())) {
						bec.setId(dbMap.get(bec.getSource_id()).getId());
						baseEmpCertificateMapper.updateByPrimaryKeySelective(bec);
					} else {
						bec.setCreate_user("system");
						bec.setCreate_timestamp(DateUtil.getNowTimestamp());
						baseEmpCertificateMapper.insert(bec);
					}
				}
			}
		}
	}
	
}
