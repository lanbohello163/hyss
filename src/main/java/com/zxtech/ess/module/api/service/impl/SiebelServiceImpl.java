package com.zxtech.ess.module.api.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zxtech.ess.app.util.SysConstants;
import com.zxtech.ess.module.api.bean.Result;
import com.zxtech.ess.module.api.service.ErpService;
import com.zxtech.ess.module.api.service.SiebelService;
import com.zxtech.ess.module.api.utils.CalculateUtil;
import com.zxtech.ess.module.api.utils.HttpUtil;
import com.zxtech.ess.module.api.utils.QueueUtil;
import com.zxtech.ess.module.api.utils.ResultUtil;
import com.zxtech.ess.module.gen.bean.BaseDictionary;
import com.zxtech.ess.module.gen.bean.BaseElevator;
import com.zxtech.ess.module.gen.bean.BaseKeyProperty;
import com.zxtech.ess.module.gen.bean.BaseProperty;
import com.zxtech.ess.module.gen.bean.BasePropertyPerson;
import com.zxtech.ess.module.gen.bean.MtContractDtl;
import com.zxtech.ess.module.gen.mapper.BaseDictionaryMapper;
import com.zxtech.ess.module.gen.mapper.BaseElevatorMapper;
import com.zxtech.ess.module.gen.mapper.BaseKeyPropertyMapper;
import com.zxtech.ess.module.gen.mapper.BasePropertyMapper;
import com.zxtech.ess.module.gen.mapper.BasePropertyPersonMapper;
import com.zxtech.ess.module.gen.mapper.MtContractDtlMapper;
import com.zxtech.platform.context.PlatformGlobalVar;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.JsonUtil;
import com.zxtech.platform.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("siebelServiceImpl")
public class SiebelServiceImpl implements SiebelService {
	
	@Autowired
	private BasePropertyMapper basePropertyMapper;
	
	@Autowired
	private BasePropertyPersonMapper basePropertyPersonMapper;
	
	@Autowired
	private BaseElevatorMapper baseElevatorMapper;
	
	@Autowired
	private BaseDictionaryMapper baseDictionaryMapper;
	
	@Autowired
	private BaseKeyPropertyMapper baseKeyPropertyMapper;
	
	@Autowired
	private MtContractDtlMapper mtContractDtlMapper;
	
	@Autowired
	private ErpService erpService;
	
	@Autowired 
	HttpServletRequest request;
	
	private static GsonBuilder gsonBulder = new GsonBuilder();
	
	static{
		gsonBulder.registerTypeAdapter(java.sql.Timestamp.class, JsonUtil.TIMESTAMP);
		gsonBulder.registerTypeAdapter(java.sql.Date.class, JsonUtil.DATE);
	}

	/**
	 * 基础数据——（Siebel售前获取）客户信息
	 * @author syp661
	 * @throws Exception 
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public Result syncGetCustomerInfo(JSONObject param) throws Exception {
		String url = request.getRequestURL().toString();
		Result result = new Result();
		try{
			List<Map<String, Object>> proList =  (List<Map<String, Object>>)param.get("list");
			int num = proList.size();
			BaseProperty baseProperty;
			Map<String, Object> bpMap;
			List<Map<String, Object>> proPersonList;
			for (int i = 0; i < num; i++) {
				bpMap = proList.get(i);
				// 新增 更新 客户信息
				baseProperty =  new BaseProperty();
				baseProperty.setEnable_flag("1");
				List<BaseProperty> basePropertyList = new ArrayList<>();
				if(bpMap.containsKey("source_id")){
					baseProperty.setSource_id(String.valueOf(bpMap.get("source_id")));
					basePropertyList = basePropertyMapper.selectBySqlConditions(baseProperty);
				}
				if(bpMap.containsKey("property_name")){
					baseProperty.setProperty_name(String.valueOf(bpMap.get("property_name")));
				}
				if(bpMap.containsKey("property_code")){
					baseProperty.setProperty_code(String.valueOf(bpMap.get("property_code")));
				}
				if(bpMap.containsKey("key_property_code")){
					String key_property_code = String.valueOf(bpMap.get("key_property_code"));
					baseProperty.setKey_property_code(key_property_code);
					if (!"".equals(key_property_code) && !"普通".equals(key_property_code)) {
						String key_property_name = "";
						if(bpMap.containsKey("key_property_name")){
							key_property_name = String.valueOf(bpMap.get("key_property_name"));
						}
						BaseKeyProperty baseKeyProperty = new BaseKeyProperty();
						baseKeyProperty.setKey_property_code(key_property_code);
						List<BaseKeyProperty> keyList = baseKeyPropertyMapper.selectBySqlConditions(baseKeyProperty);
						baseKeyProperty.setKey_property_name(key_property_name);
						if (keyList != null && keyList.size() > 0) {
							baseKeyProperty.setId(keyList.get(0).getId());
							baseKeyPropertyMapper.updateByPrimaryKey(baseKeyProperty);
						} else {
							baseKeyPropertyMapper.insert(baseKeyProperty);
						}
					}
				}
				if(bpMap.containsKey("mt_key_property")){
					baseProperty.setMt_key_property(String.valueOf(bpMap.get("mt_key_property")));
				}
				baseProperty.setSync_datetime(DateUtil.getNowTimestamp());
				baseProperty.setIs_sync("1");
				baseProperty.setLast_update_timestamp(DateUtil.getNowTimestamp());
				baseProperty.setLast_update_user("system");
				if(basePropertyList.size() > 0){//更新客户信息
					baseProperty.setId(basePropertyList.get(0).getId());
					basePropertyMapper.updateByPrimaryKeySelective(baseProperty);
					if(bpMap.containsKey("employee_list")){
						proPersonList = (List<Map<String, Object>>) bpMap.get("employee_list");
						updatePropertyPerson(proPersonList, baseProperty);
					}
				}else{//新增客户信息
					baseProperty.setCreate_timestamp(DateUtil.getNowTimestamp());
					baseProperty.setCreate_user("system");
					basePropertyMapper.insert(baseProperty);
					//新增联系人信息
					if(bpMap.containsKey("employee_list")){
						proPersonList = (List<Map<String, Object>>) bpMap.get("employee_list");
						insertPropertyPerson(proPersonList, baseProperty);
					}
				}
			}
			//保存操作信息
		    result = ResultUtil.resultSuccess(new HashMap<>());
		  //保存同步日志
			QueueUtil.put(1, 2, url, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "基础数据——客户信息", "");
		}catch (Exception e){
			//获取异常数据
			String exceptionStr = ExceptionUtils.getStackTrace(e);
			result = ResultUtil.resultFail(exceptionStr); 
			//保存错误日志
			QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "基础数据——客户信息", exceptionStr);
			throw new RuntimeException();	
		}
		return result;
	}
	
	//修改联系人信息
	void updatePropertyPerson (List<Map<String, Object>> proPersonList,BaseProperty baseProperty) throws Exception {
		//删除全部联系人，重新创建
//		BasePropertyPerson basePropertyPerson = new BasePropertyPerson();
//		basePropertyPerson.setProperty_id(baseProperty.getId());
//		basePropertyPersonMapper.deleteBySqlConditions(basePropertyPerson);
//		
//		insertPropertyPerson(proPersonList, baseProperty);
		
		Map<String, Object> proPersonMap;
		BasePropertyPerson basePropertyPerson;
		for(int j = 0; j < proPersonList.size() ;j++){
			proPersonMap = proPersonList.get(j);
			
			basePropertyPerson = new BasePropertyPerson();
			basePropertyPerson.setProperty_id(baseProperty.getId());
			basePropertyPerson.setEnable_flag("1");
			if(proPersonMap.containsKey("person_name")){
				basePropertyPerson.setPerson_name(String.valueOf(proPersonMap.get("person_name")));
			}
			List<BasePropertyPerson> basePropertyPersonList = basePropertyPersonMapper.selectBySqlConditions(basePropertyPerson);
//			if(proPersonMap.containsKey("source_id")){
//				basePropertyPerson.setSource_id(String.valueOf(proPersonMap.get("source_id")));
//			}
			if(proPersonMap.containsKey("person_position")){
				basePropertyPerson.setPerson_position(String.valueOf(proPersonMap.get("person_position")));
			}
			if(proPersonMap.containsKey("person_tel1")){
				basePropertyPerson.setPerson_tel1(String.valueOf(proPersonMap.get("person_tel1")));
			}
			if(proPersonMap.containsKey("person_tel2")){
				basePropertyPerson.setPerson_tel2(String.valueOf(proPersonMap.get("person_tel2")));
			}
			if(proPersonMap.containsKey("person_email")){
				basePropertyPerson.setPerson_email(String.valueOf(proPersonMap.get("person_email")));
			}
			basePropertyPerson.setSync_datetime(DateUtil.getNowTimestamp());
			basePropertyPerson.setLast_update_user("system");
			basePropertyPerson.setLast_update_timestamp(DateUtil.getNowTimestamp());
			if(basePropertyPersonList.size() > 0){
				//更新 客户联系人信息
				basePropertyPerson.setId(basePropertyPersonList.get(0).getId());
				basePropertyPersonMapper.updateByPrimaryKeySelective(basePropertyPerson);
			}else{//插入新的联系人信息
				basePropertyPerson.setCreate_timestamp(DateUtil.getNowTimestamp());
				basePropertyPerson.setCreate_user("system");
				basePropertyPersonMapper.insert(basePropertyPerson);
			}
		}
	}
	
	//新增联系人信息
	void insertPropertyPerson (List<Map<String, Object>> proPersonList,BaseProperty baseProperty) throws Exception {
		Map<String, Object> proPersonMap;
		BasePropertyPerson basePropertyPerson;
		if (proPersonList != null && proPersonList.size() > 0) {
			for(int j = 0; j < proPersonList.size() ;j++){
				proPersonMap = proPersonList.get(j);
				basePropertyPerson = new BasePropertyPerson();
				basePropertyPerson.setProperty_id(baseProperty.getId());
				basePropertyPerson.setEnable_flag("1");
				//if(proPersonMap.containsKey("source_id")){
//					basePropertyPerson.setSource_id(String.valueOf(baseProperty.getId()) + (j + 1));
				//}
				if(proPersonMap.containsKey("person_name")){
					basePropertyPerson.setPerson_name(String.valueOf(proPersonMap.get("person_name")));
				}
				if(proPersonMap.containsKey("person_position")){
					basePropertyPerson.setPerson_position(String.valueOf(proPersonMap.get("person_position")));
				}
				if(proPersonMap.containsKey("person_tel1")){
					basePropertyPerson.setPerson_tel1(String.valueOf(proPersonMap.get("person_tel1")));
				}
				if(proPersonMap.containsKey("person_tel2")){
					basePropertyPerson.setPerson_tel2(String.valueOf(proPersonMap.get("person_tel2")));
				}
				if(proPersonMap.containsKey("person_email")){
					basePropertyPerson.setPerson_email(String.valueOf(proPersonMap.get("person_email")));
				}
				basePropertyPerson.setSync_datetime(DateUtil.getNowTimestamp());
				basePropertyPerson.setCreate_timestamp(DateUtil.getNowTimestamp());
				basePropertyPerson.setCreate_user("system");
				basePropertyPersonMapper.insert(basePropertyPerson);
			}
		}
	}

	/**
	 * 基础数据——获取外厂梯
	 * @author syp602
	 * @throws Exception 
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public Result syncSiebelExternalFactoryEle(JSONObject param) throws Exception {
		String url = request.getRequestURL().toString();
		Result result = new Result();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			List<Map<String, Object>> listTemp = (List<Map<String, Object>>) param.get("list");
			if (listTemp != null && listTemp.size() > 0) {
				for (Map<String, Object> mapTemp : listTemp) {
					if (mapTemp.containsKey("double_door_layer") && "".equals(String.valueOf(mapTemp.get("double_door_layer")))) {
						mapTemp.put("double_door_layer", null);
					}
				}
			}
			List<BaseElevator> list = gsonBulder.create().fromJson(new Gson().toJson(listTemp), 
					new TypeToken<List<BaseElevator>>() {}.getType());
			if (list != null && list.size() > 0) {
				for (BaseElevator baseElevator : list) {
 		            if ("直梯".equals(baseElevator.getEle_category())) {
		            	baseElevator.setEle_category("1");
		            } else if ("扶梯".equals(baseElevator.getEle_category())) {
		            	baseElevator.setEle_category("2");
		            }
 		            //技监发证日期
 					if(baseElevator.getCertificate_date() != null && baseElevator.getCertificate_date().length() > 10){
 						String certificate_date = String.valueOf(baseElevator.getCertificate_date());
 						if (certificate_date.length() > 10) {
 							certificate_date = certificate_date.substring(0, 10);
 						}
 						baseElevator.setCertificate_date(certificate_date);
 					}
 		            //移交维保日期
 					if(baseElevator.getTrans_mt_date() != null && baseElevator.getTrans_mt_date().length() > 10){
 						String trans_mt_date = String.valueOf(baseElevator.getTrans_mt_date());
 						if (trans_mt_date.length() > 10) {
 							trans_mt_date = trans_mt_date.substring(0, 10);
 						}
 						baseElevator.setTrans_mt_date(trans_mt_date);
 					}
 		            //移交客户时间
 					if(baseElevator.getTrans_cust_date() != null && baseElevator.getTrans_cust_date().length() > 10){
 						String trans_cust_date = String.valueOf(baseElevator.getTrans_cust_date());
 						if (trans_cust_date.length() > 10) {
 							trans_cust_date = trans_cust_date.substring(0, 10);
 						}
 						baseElevator.setTrans_cust_date(trans_cust_date);
 					}
 		            //上报完工日期
 					if(baseElevator.getCompletion_date() != null && baseElevator.getCompletion_date().length() > 10){
 						String completion_date = String.valueOf(baseElevator.getCompletion_date());
 						if (completion_date.length() > 10) {
 							completion_date = completion_date.substring(0, 10);
 						}
 						baseElevator.setCompletion_date(completion_date);
 					}
 		            //省、直辖市
 		 			if (baseElevator.getEle_province() != null) {
 						 BaseDictionary baseDictionary =  new BaseDictionary();
 				         baseDictionary.setDict_name(String.valueOf(baseElevator.getEle_province()));
 				         baseDictionary.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
 				         baseDictionary.setDict_type("province");
 				         List<BaseDictionary> baseDictionaryList = baseDictionaryMapper.selectBySqlConditions(baseDictionary);
 				         if (baseDictionaryList.size() > 0) {
 				        	 
 				        	BaseDictionary db =  new BaseDictionary();
 	 				        db.setDict_code(baseDictionaryList.get(0).getP_dict_code());
 	 				        db.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
 	 				        db.setDict_type("state");
 	 				        List<BaseDictionary> dbl = baseDictionaryMapper.selectBySqlConditions(db);
 	 				        baseElevator.setRegion_state_name(dbl.get(0).getDict_name());
 	 				        
 				        	baseElevator.setRegion_province_name(baseDictionaryList.get(0).getDict_name());
 				         	baseElevator.setEle_province(baseDictionaryList.get(0).getDict_code());
 				         	baseElevator.setEle_state(baseDictionaryList.get(0).getP_dict_code());
 				         }
 					}
 		            //城市
 					if (baseElevator.getEle_city() != null) {
 						 BaseDictionary baseDictionary1 =  new BaseDictionary();
 				         baseDictionary1.setDict_name(String.valueOf(baseElevator.getEle_city()));
 				         baseDictionary1.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
 				         baseDictionary1.setDict_type("city");
 				         List<BaseDictionary> baseDictionaryList1 = baseDictionaryMapper.selectBySqlConditions(baseDictionary1);
 				         if (baseDictionaryList1.size() > 0) {
 				        	baseElevator.setRegion_city_name(baseElevator.getEle_city());
 				         	baseElevator.setEle_city(baseDictionaryList1.get(0).getDict_code());
 				         }
 					}
 					//大项目标识
 					if (baseElevator.getIs_key_project() != null && !"".equals(baseElevator.getIs_key_project())){
 						if ("KP".equals(baseElevator.getIs_key_project())) {
 							baseElevator.setIs_key_project("2");
 						} else {
 							baseElevator.setIs_key_project("1");
 						}
 					} else {
 						baseElevator.setIs_key_project("1");
 					}
 					baseElevator.setUpdated_datetime(sdf.format(new Date()));
	            	baseElevator.setSync_datetime(DateUtil.getNowTimestamp());
	            	//计算折合层数
	            	BigDecimal conversion_layer = CalculateUtil.calculateConversionLayer(
	            			baseElevator.getThrough_door(),
	            			baseElevator.getEle_door(),
	            			baseElevator.getEle_floor(),
	            			baseElevator.getDouble_door_layer()
	            	);
	            	baseElevator.setConversion_layer(conversion_layer);
					BaseElevator baseElevatorTemp = new BaseElevator();
					baseElevatorTemp.setAsset_num(baseElevator.getAsset_num());
					List<BaseElevator> baseElevatorList = baseElevatorMapper.selectBySqlConditions(baseElevatorTemp);
					if (baseElevatorList != null && baseElevatorList.size() > 0) {
						baseElevator.setId(baseElevatorList.get(0).getId());
						if (baseElevatorList.get(0).getHigh_end_customer() == null || "".equals(baseElevatorList.get(0).getHigh_end_customer())) {
							baseElevator.setHigh_end_customer("1");
						}
						baseElevator.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
		            	baseElevator.setLast_update_user("system");
		            	baseElevator.setLast_update_timestamp(DateUtil.getNowTimestamp());
		            	baseElevatorMapper.updateByPrimaryKeySelective(baseElevator);
					} else {
						baseElevator.setHigh_end_customer("1");
						baseElevator.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
						baseElevator.setEle_status("正常");
						baseElevator.setContract_code(""); 
						baseElevator.setCreate_user("system");
						baseElevator.setCreate_timestamp(DateUtil.getNowTimestamp());
		            	baseElevator.setLast_update_user("system");
		            	baseElevator.setLast_update_timestamp(DateUtil.getNowTimestamp());
		            	if (baseElevator.getCertificate_date() != null && !"".equals(baseElevator.getCertificate_date())) {
							String annual_check_date = calculateAnnualCheckDate(baseElevator.getCertificate_date());
							baseElevator.setAnnual_check_date(annual_check_date);
						}
						baseElevatorMapper.insert(baseElevator);
						MtContractDtl mtContractDtl = new MtContractDtl();
						mtContractDtl.setContract_code(""); 
						mtContractDtl.setAsset_id(baseElevator.getId());
						mtContractDtl.setSource_id("-1");
						mtContractDtl.setEnable_flag("0");
						mtContractDtl.setCreate_user("system");
						mtContractDtl.setCreate_timestamp(DateUtil.getNowTimestamp());
						mtContractDtl.setLast_update_user("system");
						mtContractDtl.setLast_update_timestamp(DateUtil.getNowTimestamp());
						mtContractDtlMapper.insert(mtContractDtl);
						erpService.addFileAssetChange(baseElevator.getId());
					}
				}
			}
			
			//保存操作信息
		    result = ResultUtil.resultSuccess(new HashMap<>());
		  //保存同步日志
			QueueUtil.put(1, 2, url, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "基础数据——获取外厂梯", "");
		}catch (Exception e){
			e.printStackTrace();
			//获取异常数据
			String exceptionStr = ExceptionUtils.getStackTrace(e);
			result = ResultUtil.resultFail(exceptionStr); 
			//保存错误日志
			QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "基础数据——获取外厂梯", exceptionStr);
			throw new RuntimeException();	
		}
		return result;
	}

	/**
	 * 基础数据——获取双开门层数
	 * @param param
	 * @author syp602
	 * @创建时间 2019/4/9
	 * @throws Exception 
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public Result syncSiebelGetDoubleDoorLayer(List<BaseElevator> doubleList) throws Exception {
		Result result = new Result();
		JSONObject param = new JSONObject();
		String httpcode = "";
		try {
			if (doubleList != null && doubleList.size() > 0) {
				JSONArray array = new JSONArray();
				Map<String, BaseElevator> map = new HashMap<>();
				for (BaseElevator baseElevator : doubleList) {
					String asset_num = baseElevator.getAsset_num();
					String contract_code = baseElevator.getContract_code();
					map.put(asset_num, baseElevator);
					JSONObject paramTemp = new JSONObject();
					paramTemp.put("contract_code", contract_code);
					paramTemp.put("asset_num", asset_num);
					array.add(paramTemp);
				}
				param.put("list", array);
				httpcode = PlatformGlobalVar.SYS_PROPERTIES.get("hesbPath") + "/syncSiebelGetDoubleDoorLayer.api";
				Result resultObj = HttpUtil.doPostWithParam(httpcode, param);
				if (ResultUtil.SUCCESS_FLG.equals(resultObj.getRtnCode())) {
					Map<String, Object> rtnData = (Map<String, Object>) resultObj.getRtnData();
					List<Map<String, Object>> listTemp = (List<Map<String, Object>>) rtnData.get("list");
					if (listTemp != null && listTemp.size() > 0) {
						for (Map<String, Object> mapTemp : listTemp) {
							if (mapTemp.containsKey("asset_num") && mapTemp.get("asset_num") != null
									&& !"".equals(String.valueOf(mapTemp.get("asset_num")))) {
								String asset_num = String.valueOf(mapTemp.get("asset_num"));
								if (map.containsKey(asset_num)) {
									BaseElevator baseElevator = map.get(asset_num);
									if (mapTemp.containsKey("double_door_layer") && mapTemp.get("double_door_layer") != null
											&& !"".equals(String.valueOf(mapTemp.get("double_door_layer")))) {
										baseElevator.setDouble_door_layer(BigDecimal.valueOf(Double.valueOf(String.valueOf(mapTemp.get("double_door_layer")))));
									}
					 				BigDecimal conversionLayer = CalculateUtil.calculateConversionLayer(
					 						baseElevator.getThrough_door(),
					 						baseElevator.getEle_door(),
					 						baseElevator.getEle_floor(),
					 						baseElevator.getDouble_door_layer()
					 				);
					 				baseElevator.setConversion_layer(conversionLayer);
					 				baseElevatorMapper.updateByPrimaryKeySelective(baseElevator);
								}
							}
						}
					}
					
					result = ResultUtil.resultSuccess(new HashMap<>());
					QueueUtil.put(1, 1, httpcode, StringUtil.stringConvertor(param),
							StringUtil.stringConvertor(JSONObject.fromObject(resultObj)), "基础数据——获取双开门层数", "");
				} else {
					result = ResultUtil.resultFail(resultObj.getRtnMsg());
					//当返回参数异常时，保存错误日志
					QueueUtil.put(2, 1, httpcode, StringUtil.stringConvertor(param),
							StringUtil.stringConvertor(JSONObject.fromObject(resultObj)), "基础数据——获取双开门层数", resultObj.getRtnMsg());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			//获取异常信息
			String exceptionStr = ExceptionUtils.getStackTrace(e);
			result = ResultUtil.resultFail(exceptionStr);
			//保存错误日志
			QueueUtil.put(2, 1, httpcode, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "基础数据——获取双开门层数", exceptionStr);
			
			throw new RuntimeException();
		}
		return result;
	}
	
	/**
	 * 根据技监发证日期算出下次年检日期
	 * @param certificate_date
	 * @return
	 * @throws Exception
	 */
	private String calculateAnnualCheckDate(String certificate_date) throws Exception {
		String rtnDate = "";
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_STRING);
		SimpleDateFormat sdfMontn = new SimpleDateFormat(DateUtil.MONTH_STRING);
        Date dt = sdf.parse(certificate_date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        calendar.add(Calendar.MONTH, 1);
        Date dtMonth = calendar.getTime();
        Date dtNow = sdf.parse(sdf.format(new Date()));
        if (dtMonth.getTime() > dtNow.getTime()) {
        	rtnDate = sdfMontn.format(dtMonth);
        } else {
        	rtnDate = sdfMontn.format(addYear(dtMonth));
        }
		return rtnDate;
	}
	
	/**
	 * 累计加一年
	 * @param date
	 * @return
	 * @throws Exception
	 */
	private Date addYear(Date date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_STRING);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, 1);
        Date dtYear = calendar.getTime();
        Date dtNow = sdf.parse(sdf.format(new Date()));
        if (dtYear.getTime() > dtNow.getTime()) {
        	return dtYear;
        } else {
        	return addYear(dtYear);
        }
	}
}
