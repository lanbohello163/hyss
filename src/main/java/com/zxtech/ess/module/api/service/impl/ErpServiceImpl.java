package com.zxtech.ess.module.api.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
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

import com.zxtech.ess.app.util.SysConstants;
import com.zxtech.ess.module.api.bean.Result;
import com.zxtech.ess.module.api.mapper.ErpManagerMapper;
import com.zxtech.ess.module.api.service.ErpService;
import com.zxtech.ess.module.api.utils.CalculateUtil;
import com.zxtech.ess.module.api.utils.HttpUtil;
import com.zxtech.ess.module.api.utils.JsonDateValueProcessor;
import com.zxtech.ess.module.api.utils.JsonTimestampProcessor;
import com.zxtech.ess.module.api.utils.QueueUtil;
import com.zxtech.ess.module.api.utils.ResultUtil;
import com.zxtech.ess.module.gen.bean.BaseCompSync;
import com.zxtech.ess.module.gen.bean.BaseCompany;
import com.zxtech.ess.module.gen.bean.BaseDictionary;
import com.zxtech.ess.module.gen.bean.BaseElevator;
import com.zxtech.ess.module.gen.bean.BaseProperty;
import com.zxtech.ess.module.gen.bean.FileAssetChange;
import com.zxtech.ess.module.gen.bean.MtContract;
import com.zxtech.ess.module.gen.bean.MtContractDtl;
import com.zxtech.ess.module.gen.mapper.BaseCompSyncMapper;
import com.zxtech.ess.module.gen.mapper.BaseCompanyMapper;
import com.zxtech.ess.module.gen.mapper.BaseDictionaryMapper;
import com.zxtech.ess.module.gen.mapper.BaseElevatorMapper;
import com.zxtech.ess.module.gen.mapper.BasePropertyMapper;
import com.zxtech.ess.module.gen.mapper.FileAssetChangeMapper;
import com.zxtech.ess.module.gen.mapper.MtContractDtlMapper;
import com.zxtech.ess.module.gen.mapper.MtContractMapper;
import com.zxtech.platform.context.PlatformGlobalVar;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.StringUtil;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Service("erpServiceImpl")
public class ErpServiceImpl implements ErpService {
	
	@Autowired
	private BaseCompSyncMapper baseCompSyncMapper;
	
	@Autowired
	private MtContractMapper mtContractMapper;
	
	@Autowired
	private BaseCompanyMapper baseCompanyMapper;
	
	@Autowired
	private BaseElevatorMapper baseElevatorMapper;
	
	@Autowired
	private BaseDictionaryMapper baseDictionaryMapper;
	
	@Autowired
	private BasePropertyMapper basePropertyMapper;
	
	@Autowired
	private MtContractDtlMapper mtContractDtlMapper;
	
	@Autowired
	private FileAssetChangeMapper fileAssetChangeMapper;
	
	@Autowired 
	HttpServletRequest request;
	
	@Autowired
	private ErpManagerMapper erpManagerMapper;

	/**
	 *合同数据（含工号参数）
	 * @param param
	 * @author syp661
	 * @创建时间 2018/12/28
	 * @throws Exception 
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public Result syncGetContractHead(JSONObject param)  throws Exception{
		//导入合同数据
		Result result = new Result();
		String url = request.getRequestURL().toString();
		try{
			//获取双开门层数的集合
			List<BaseElevator> doubleList = new ArrayList<>();
			//合同整体数据
			List<Map<String, Object>> contractList =  (List<Map<String, Object>>)param.get("list");
			if(CollectionUtil.isEmpty(contractList)) {
				String msg = "list不能为空！";
				result = ResultUtil.resultFail(msg);
				QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
						StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
				return result;
			}
			int num = contractList.size();
			Map<String, Object> contractMap;
			List<Map<String, Object>> elevatortList;
			//erp分公司 校验用
			List<Map<String, Object>> bcList = erpManagerMapper.erpCompanySelectAll();
			Map<String, Object> bcCheckMap = new HashMap<>();
			for(Map<String, Object> b : bcList) {
				bcCheckMap.put(StringUtil.toSafeString(b.get("erp_comp_id")), b);
			}
			//客户编码校验用
			List<BaseProperty> bpList = basePropertyMapper.selectAll();
			Map<String, BaseProperty> bpCheckMap = new HashMap<>();
			for(BaseProperty b : bpList) {
				if(StringUtil.isNotBlank(b.getProperty_code()))
					bpCheckMap.put(b.getProperty_code(), b);
			}
			//合同类型校验用
			BaseDictionary bd = new BaseDictionary();
			bd.setDict_type_name("合同类型");
			List<BaseDictionary> bdList = baseDictionaryMapper.selectBySqlConditions(bd);
			Map<String, BaseDictionary> bdCheckMap = new HashMap<>();
			for(BaseDictionary b : bdList) {
					bdCheckMap.put(b.getDict_name(), b);
			}
			//工号校验用
			List<BaseElevator> beList = baseElevatorMapper.selectAll();
			Map<String, BaseElevator> beCheckMap = new HashMap<>();
			for(BaseElevator be : beList) {
				if(StringUtil.isNotBlank(be.getAsset_num()))
					beCheckMap.put(be.getAsset_num(), be);
			}
			//所属站ID 校验用
			List<Map<String, Object>> bssList = erpManagerMapper.erpStationSelectAll();
			Map<String, Object> bssCheckMap = new HashMap<>();
			for(Map<String, Object> bs : bssList) {
				bssCheckMap.put(StringUtil.toSafeString(bs.get("erp_stat_id")), bs);
			}
			
			for (int i = 0; i < num; i++) {
				contractMap = new HashMap<>();
				//合同头 合同行 工号
				contractMap = contractList.get(0);
				//工号信息校验
				//合同ID
				if (StringUtil.isBlank(StringUtil.toSafeString(contractMap.get("source_id")))) {
					String msg = "合同ID:source_id不能为空！";
					result = ResultUtil.resultFail(msg);
					QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
							StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", "");
					return result;
				}
				//合同编号
				if (StringUtil.isBlank(StringUtil.toSafeString(contractMap.get("contract_code")))) {
					String msg = "合同编号contract_code:不能为空！";
					result = ResultUtil.resultFail(msg);
					QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
							StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", "");
					return result;
				}
				//签单分公司
				if (StringUtil.isBlank(StringUtil.toSafeString(contractMap.get("sign_comp_id")))) {
					String msg = "签单分公司sign_comp_id:不能为空！";
					result = ResultUtil.resultFail(msg);
					QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
							StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", "");
					return result;
				}
				if(!bcCheckMap.containsKey(StringUtil.toSafeString(contractMap.get("sign_comp_id")))) {
					String msg = "签单分公司sign_comp_id:新维保不存在！";
					result = ResultUtil.resultFail(msg);
					QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
							StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", "");
					return result;
				}
				//客户编码
				if (StringUtil.isBlank(StringUtil.toSafeString(contractMap.get("cust_code")))) {
					String msg = "客户编码cust_code:不能为空！";
					result = ResultUtil.resultFail(msg);
					QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
							StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", "");
					return result;
				}
				if(!bpCheckMap.containsKey(StringUtil.toSafeString(contractMap.get("cust_code")))) {
					String msg = "客户编码cust_code:新维保不存在！";
					result = ResultUtil.resultFail(msg);
					QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
							StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
					return result;
				}
				//合同类型
				if (StringUtil.isBlank(StringUtil.toSafeString(contractMap.get("contract_category")))) {
					String msg = "合同类型contract_category:不能为空！";
					result = ResultUtil.resultFail(msg);
					QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
							StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
					return result;
				}
				if(!bdCheckMap.containsKey(StringUtil.toSafeString(contractMap.get("contract_category")))) {
					String msg = "合同类型contract_category:新维保不存在！";
					result = ResultUtil.resultFail(msg);
					QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
							StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
					return result;
				}
				//业务分类 -- 保修、保养
				if (StringUtil.isBlank(StringUtil.toSafeString(contractMap.get("business_category")))) {
					String msg = "业务分类business_category:不能为空！";
					result = ResultUtil.resultFail(msg);
					QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
							StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
					return result;
				}
				if(!("保修".equals(StringUtil.toSafeString(contractMap.get("business_category"))) 
						|| "保养".equals(StringUtil.toSafeString(contractMap.get("business_category"))))){
					String msg = "业务分类business_category:新维保不存在！";
					result = ResultUtil.resultFail(msg);
					QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
							StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
					return result;
				}
				//合同性质 新签、续保、复回
				if (StringUtil.isBlank(StringUtil.toSafeString(contractMap.get("contract_type")))) {
					String msg = "合同性质contract_type:不能为空！";
					result = ResultUtil.resultFail(msg);
					QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
							StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
					return result;
				}
				if(!("新签".equals(StringUtil.toSafeString(contractMap.get("contract_type"))) 
						|| "续保".equals(StringUtil.toSafeString(contractMap.get("contract_type"))) 
						|| "复回".equals(StringUtil.toSafeString(contractMap.get("contract_type"))))){
					String msg = "合同性质contract_type:新维保不存在！";
					result = ResultUtil.resultFail(msg);
					QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
							StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
					return result;
				}
				List<Map<String, Object>> lineList = (List<Map<String, Object>>) contractMap.get("line_list");
				if(CollectionUtil.isEmpty(lineList)) {
					String msg = "line_list:不能为空！";
					result = ResultUtil.resultFail(msg);
					QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
							StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
					return result;
				}
				for(Map<String, Object> m : lineList) {
					//合同行ID
					if (StringUtil.isBlank(StringUtil.toSafeString(m.get("source_id")))) {
						String msg = "合同行IDsource_id:不能为空！";
						result = ResultUtil.resultFail(msg);
						QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
								StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
						return result;
					}
					//工号
					if (StringUtil.isBlank(StringUtil.toSafeString(m.get("asset_num")))) {
						String msg = "工号asset_num:不能为空！";
						result = ResultUtil.resultFail(msg);
						QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
								StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
						return result;
					}
					if("保养".equals(StringUtil.toSafeString(contractMap.get("business_category")))){
						if(!beCheckMap.containsKey(StringUtil.toSafeString(m.get("asset_num")))) {
							String msg = "工号asset_num:新维保不存在！";
							result = ResultUtil.resultFail(msg);
							QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
									StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
							return result;
						}
					}
					//所属站ID 若不为空，需与新维保维保站绑定；
					if (StringUtil.isNotBlank(StringUtil.toSafeString(m.get("stat_old_id")))) {
						if(!bssCheckMap.containsKey(StringUtil.toSafeString(m.get("stat_old_id")))) {
							String msg = "所属站IDstat_old_id:新维保不存在！";
							result = ResultUtil.resultFail(msg);
							QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
									StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
							return result;
						}
					}
					//保养公司 不可为空，需与新维保机构绑定；
					if (StringUtil.isBlank(StringUtil.toSafeString(m.get("comp_id")))) {
						String msg = "保养公司comp_id:不能为空！";
						result = ResultUtil.resultFail(msg);
						QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
								StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
						return result;
					}
					if(!bcCheckMap.containsKey(StringUtil.toSafeString(m.get("comp_id")))) {
						String msg = "保养公司comp_id:新维保不存在！";
						result = ResultUtil.resultFail(msg);
						QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
								StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
						return result;
					}
					// 合同类型-保修合同 时 工号ID、梯种类别、工号地址 不可为空
					if("保修".equals(StringUtil.toSafeString(contractMap.get("business_category")))){
						//工号ID
						if (StringUtil.isBlank(StringUtil.toSafeString(m.get("elevator_source_id")))) {
							String msg = "工号IDelevator_source_id:不能为空！";
							result = ResultUtil.resultFail(msg);
							QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
									StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
							return result;
						}
						//梯种类别 直梯、扶梯
						if (StringUtil.isBlank(StringUtil.toSafeString(m.get("ele_category")))) {
							String msg = "梯种类别ele_category:不能为空！";
							result = ResultUtil.resultFail(msg);
							QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
									StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
							return result;
						}
						if(!("直梯".equals(StringUtil.toSafeString(m.get("ele_category"))) 
								|| "扶梯".equals(StringUtil.toSafeString(m.get("ele_category"))))){
							String msg = "梯种类别ele_category:新维保不存在！";
							result = ResultUtil.resultFail(msg);
							QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
									StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
							return result;
						}
						//工号地址 长度不可大于200
						if (StringUtil.isBlank(StringUtil.toSafeString(m.get("asset_num_address")))) {
							String msg = "工号地址asset_num_address:不能为空！";
							result = ResultUtil.resultFail(msg);
							QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
									StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
							return result;
						}
						if(StringUtil.toSafeString(m.get("asset_num_address")).length() > 200) {
							String msg = "工号地址asset_num_address:长度不可大于200!";
							result = ResultUtil.resultFail(msg);
							QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
									StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
							return result;
						}
					} else if ("保养".equals(StringUtil.toSafeString(contractMap.get("business_category")))) {
						//保养合同校验
						//保养开始时间
						if (StringUtil.isBlank(StringUtil.toSafeString(m.get("mt_begin_date")))) {
							String msg = "保养合同-保养开始时间mt_begin_date:不能为空！";
							result = ResultUtil.resultFail(msg);
							QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
									StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
							return result;
						}
						//保养结束时间
						if (StringUtil.isBlank(StringUtil.toSafeString(m.get("mt_end_date")))) {
							String msg = "保养合同-保养结束时间mt_end_date:不能为空！";
							result = ResultUtil.resultFail(msg);
							QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
									StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", msg);
							return result;
						}
					}
				}
				
				//只同步佛山分公司(1089 BA06)、辽宁分公司(1515 BA22)、广州分公司(1091 BA01)的合同
//				if (contractMap.containsKey("sign_comp_id")
//						&& ("".equals(String.valueOf(contractMap.get("sign_comp_id")))
//								|| "1089".equals(String.valueOf(contractMap.get("sign_comp_id")))
//								|| "1515".equals(String.valueOf(contractMap.get("sign_comp_id")))
//								|| "1091".equals(String.valueOf(contractMap.get("sign_comp_id"))))) {
					//客户编码
					int cust_id = -1;
					if(contractMap.containsKey("cust_code")){
						BaseProperty baseProperty = new BaseProperty();
						baseProperty.setProperty_code(String.valueOf(contractMap.get("cust_code")));
						baseProperty.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
						List<BaseProperty> basePropertyL = basePropertyMapper.selectBySqlConditions(baseProperty);
						if(basePropertyL.size() > 0){
							cust_id = basePropertyL.get(0).getId();
						}
					}
					
					//工号处理
					elevatortList = new ArrayList<>();
					elevatortList = (List<Map<String, Object>>) contractMap.get("line_list");
					String contract_code = "";
					if(contractMap.containsKey("contract_code")){
						contract_code = String.valueOf(contractMap.get("contract_code"));
					}
					String business_category = "";
					if(contractMap.containsKey("business_category")){
						business_category = String.valueOf(contractMap.get("business_category"));
					}
					String contract_category = "";
					if(contractMap.containsKey("contract_category")){
						contract_category = String.valueOf(contractMap.get("contract_category"));
					}
					//工号
					List<BaseElevator> retBaseElevator = updateElevator(elevatortList, contract_code, business_category, cust_id, doubleList, contract_category);
					//判断工号是否为虚拟合同
					boolean hasMtContract = false;
					for (BaseElevator be : retBaseElevator) {
						if (StringUtil.isNotBlank(be.getContract_code())) {
							hasMtContract = true;
						}
					}
					//合同头
					MtContract retMtContract = new MtContract();
					if (hasMtContract) {
						retMtContract = updateMtContract(contractMap, cust_id);
					}
					//合同行 
					updateMtContractDtl(retMtContract, retBaseElevator, contractMap, business_category);
//				}
			}
			//保存操作信息
		    result = ResultUtil.resultSuccess(doubleList);
		  //保存同步日志
			QueueUtil.put(1, 2, url, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", "");
		}catch (Exception e){
			e.printStackTrace();
			//获取异常数据
			String exceptionStr = ExceptionUtils.getStackTrace(e);
			result = ResultUtil.resultFail(exceptionStr);
			//保存错误日志
			QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "合同数据（含工号参数）", exceptionStr);
			throw new RuntimeException();	
		}
		return result;
	}
	
	//工号处理  
	List<BaseElevator> updateElevator (List<Map<String, Object>> elevatortList, String contract_code,
			String business_category, int cust_id, List<BaseElevator> doubleList, String contract_category) throws Exception{
	
		List<BaseElevator> ret = new ArrayList<>();
		Map<String, Object> elevatortMap;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i = 0 ; i < elevatortList.size() ; i++){
			elevatortMap =  new HashMap<>();
			elevatortMap = elevatortList.get(i);
			BaseElevator baseElevator = new BaseElevator();
			List<BaseElevator> baseElevatorL = new ArrayList<>();
			//工号
 			if(elevatortMap.containsKey("asset_num") && !"".equals(String.valueOf(elevatortMap.get("asset_num")))){
				baseElevator.setAsset_num(String.valueOf(elevatortMap.get("asset_num")));
				baseElevatorL = baseElevatorMapper.selectBySqlConditions(baseElevator);
			}
 			if ((elevatortMap.containsKey("mt_begin_date")
 					&& StringUtil.isBlank(StringUtil.toSafeString(elevatortMap.get("mt_begin_date"))))
 					|| (elevatortMap.containsKey("mt_end_date")
 		 			&& StringUtil.isBlank(StringUtil.toSafeString(elevatortMap.get("mt_end_date"))))) {
 				baseElevator.setContract_code("");
 			} else if (CollectionUtil.isNotEmpty(baseElevatorL)
 					&& StringUtil.isNotBlank(baseElevatorL.get(0).getContract_code())) {
 				//赋合同号（当前日期 >=保养开始日期）
 	 			if (checkMtDate(String.valueOf(elevatortList.get(i).get("mt_begin_date")))) {
 	 				baseElevator.setContract_code(contract_code);
 				}
 			} else {
 				baseElevator.setContract_code(contract_code);
 			}
//			if ("电梯安装合同".equals(contract_category)
//					|| "电梯调验合同".equals(contract_category)
//					|| "电梯&楼宇安装合同".equals(contract_category)
//					|| "电梯&楼宇调验合同".equals(contract_category)
//					|| "全包保养合同".equals(contract_category)
//					|| "半包保养合同".equals(contract_category)
//					|| "清包保养合同".equals(contract_category)
//					|| "大包保养合同".equals(contract_category)) {
//				if (baseElevatorL.size() > 0) {
//					if (baseElevatorL.get(0).getContract_code() == null || "".equals(baseElevatorL.get(0).getContract_code())) {
//						baseElevator.setContract_code(contract_code);
//					}
//				} else {
//					baseElevator.setContract_code(contract_code);
//				}
//			}
			if(!"-1".equals(cust_id)){
				baseElevator.setUse_customer(cust_id);
			}
			//营业梯种
			if(elevatortMap.containsKey("ele_product") && !"".equals(String.valueOf(elevatortMap.get("ele_product")))){
				//梯种型号和营业梯种互换存值
				baseElevator.setEle_model(String.valueOf(elevatortMap.get("ele_product")));
			}
			//设计梯种
 			if(elevatortMap.containsKey("model_desc") && !"".equals(String.valueOf(elevatortMap.get("model_desc")))){
 				baseElevator.setModel_desc(String.valueOf(elevatortMap.get("model_desc")));
			}
			//梯种型号
 			if(elevatortMap.containsKey("ele_model") && !"".equals(String.valueOf(elevatortMap.get("ele_model")))){
				//梯种型号和营业梯种互换存值
 				baseElevator.setEle_product(String.valueOf(elevatortMap.get("ele_model")));
			}
			//层
			if(elevatortMap.containsKey("ele_floor") && !"".equals(String.valueOf(elevatortMap.get("ele_floor")))){
				baseElevator.setEle_floor(Integer.valueOf(String.valueOf(elevatortMap.get("ele_floor"))));
			}
			//站
			if(elevatortMap.containsKey("ele_stop") && !"".equals(String.valueOf(elevatortMap.get("ele_stop")))){
				baseElevator.setEle_stop(Integer.valueOf(String.valueOf(elevatortMap.get("ele_stop"))));
			}
			//门
			if(elevatortMap.containsKey("ele_door") && !"".equals(String.valueOf(elevatortMap.get("ele_door")))){
				baseElevator.setEle_door(Integer.valueOf(String.valueOf(elevatortMap.get("ele_door"))));
			}
 			//贯通门
 			if (baseElevator.getEle_model() != null && baseElevator.getEle_model().indexOf("贯通") > -1) {
 				baseElevator.setThrough_door("1");
 			} else {
 				baseElevator.setThrough_door("0");
 				BigDecimal conversionLayer = CalculateUtil.calculateConversionLayer("0", baseElevator.getEle_door(), baseElevator.getEle_floor(), baseElevator.getDouble_door_layer());
 				baseElevator.setConversion_layer(conversionLayer);
 			}
			//载重
 			if(elevatortMap.containsKey("ele_carry") && !"".equals(String.valueOf(elevatortMap.get("ele_carry")))){
 				baseElevator.setEle_carry(String.valueOf(elevatortMap.get("ele_carry")));
			}
			//速度
 			if(elevatortMap.containsKey("ele_speed") && !"".equals(String.valueOf(elevatortMap.get("ele_speed")))){
				 baseElevator.setEle_speed(String.valueOf(elevatortMap.get("ele_speed")));
			}
            //梯种类别
			if(elevatortMap.containsKey("ele_category") && !"".equals(String.valueOf(elevatortMap.get("ele_category")))){
				 String ele_category =   String.valueOf(elevatortMap.get("ele_category"));
 		            if("直梯".equals(ele_category)){
		            	baseElevator.setEle_category("1");
		            	
		            }else if ("扶梯".equals(ele_category)){
		            	baseElevator.setEle_category("2");
		            }
			}
            //倾斜角度
 			if(elevatortMap.containsKey("ele_angle") && !"".equals(String.valueOf(elevatortMap.get("ele_angle")))){
 				baseElevator.setEle_angle(String.valueOf(elevatortMap.get("ele_angle")));
			}
            //品牌
 			if(elevatortMap.containsKey("ele_brand") && !"".equals(String.valueOf(elevatortMap.get("ele_brand")))){
 				baseElevator.setEle_brand(String.valueOf(elevatortMap.get("ele_brand")));
			}
            //注释
 			if(elevatortMap.containsKey("ele_remark") && !"".equals(String.valueOf(elevatortMap.get("ele_brand")))){
 				baseElevator.setEle_remark(String.valueOf(elevatortMap.get("ele_remark")));
			}
            //状态
			baseElevator.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);

            //技监发证日期
			if(elevatortMap.containsKey("certificate_date") && !"".equals(String.valueOf(elevatortMap.get("certificate_date")))){
				String certificate_date = String.valueOf(elevatortMap.get("certificate_date"));
				if (certificate_date.length() > 10) {
					certificate_date = certificate_date.substring(0, 10);
				}
				baseElevator.setCertificate_date(certificate_date);
				baseElevator.setIs_install("0");
			} else {
				baseElevator.setIs_install("1");
			}
            //改造前工号
			if(elevatortMap.containsKey("asset_num_before") && !"".equals(String.valueOf(elevatortMap.get("asset_num_before")))){
				baseElevator.setAsset_num_before(String.valueOf(elevatortMap.get("asset_num_before")));
			}
            //改造部件更换
			if(elevatortMap.containsKey("parts_asset_num") && !"".equals(String.valueOf(elevatortMap.get("parts_asset_num")))){
				baseElevator.setParts_asset_num(String.valueOf(elevatortMap.get("parts_asset_num")));
			}
            //移交维保日期
			if(elevatortMap.containsKey("trans_mt_date") && !"".equals(String.valueOf(elevatortMap.get("trans_mt_date")))){
				String trans_mt_date = String.valueOf(elevatortMap.get("trans_mt_date"));
				if (trans_mt_date.length() > 10) {
					trans_mt_date = trans_mt_date.substring(0, 10);
				}
				baseElevator.setTrans_mt_date(trans_mt_date);
			}
            //移交客户时间
			if(elevatortMap.containsKey("trans_cust_date") && !"".equals(String.valueOf(elevatortMap.get("trans_cust_date")))){
				String trans_cust_date = String.valueOf(elevatortMap.get("trans_cust_date"));
				if (trans_cust_date.length() > 10) {
					trans_cust_date = trans_cust_date.substring(0, 10);
				}
				baseElevator.setTrans_cust_date(trans_cust_date);
			}
            //上报完工日期
			if(elevatortMap.containsKey("completion_date") && !"".equals(String.valueOf(elevatortMap.get("completion_date")))){
				String completion_date = String.valueOf(elevatortMap.get("completion_date"));
				if (completion_date.length() > 10) {
					completion_date = completion_date.substring(0, 10);
				}
				baseElevator.setCompletion_date(completion_date);
			}
            //安装进场日期
			if(elevatortMap.containsKey("install_begin_date") && !"".equals(String.valueOf(elevatortMap.get("install_begin_date")))){
				String install_begin_date = String.valueOf(elevatortMap.get("install_begin_date"));
				if (install_begin_date.length() > 10) {
					install_begin_date = install_begin_date.substring(0, 10);
				}
				baseElevator.setInstall_do_date(install_begin_date);
			}
            //设备分类
			if(elevatortMap.containsKey("equipment_category") && !"".equals(String.valueOf(elevatortMap.get("equipment_category")))){
				String  equipment_category = String.valueOf(elevatortMap.get("equipment_category"));
	            //是否国产梯
	            if("国产".equals(equipment_category)){
	            	baseElevator.setIs_domestic("1");
	            }else{
	            	baseElevator.setIs_domestic("0");
	            }
	            //是否进口梯	
	            if("进口".equals(equipment_category)){
	            	baseElevator.setIs_import("1");
	            }else{
	            	baseElevator.setIs_import("0");
	            }
	            //是否大复合梯
	            if("大复合".equals(equipment_category) || "国产+进口件".equals(equipment_category) || 
	            		"国产+进口件A".equals(equipment_category) || "国产+进口件A（控柜）".equals(equipment_category) ||
	            		"国产+进口件A（主机）".equals(equipment_category) || "国产+进口件A（主机+控柜）".equals(equipment_category) ||
	            		"国产+进口件B".equals(equipment_category)){
	            	baseElevator.setIs_recombination("1");
	            }else{
	            	baseElevator.setIs_recombination("0");
	            }
			}
			
            //工程监理
			if(elevatortMap.containsKey("install_supervision") && !"".equals(String.valueOf(elevatortMap.get("install_supervision")))){
				baseElevator.setEngineering_supervision(String.valueOf(elevatortMap.get("install_supervision")));
			}
            //主机功率
			if(elevatortMap.containsKey("host_power") && !"".equals(String.valueOf(elevatortMap.get("host_power")))){
				baseElevator.setHost_power(String.valueOf(elevatortMap.get("host_power")));
			}
            //提升高度
			if(elevatortMap.containsKey("ele_hight") && !"".equals(String.valueOf(elevatortMap.get("ele_hight")))){
				baseElevator.setEle_high(String.valueOf(elevatortMap.get("ele_hight")));
			}
            //梯级宽度
			if(elevatortMap.containsKey("step_width") && !"".equals(String.valueOf(elevatortMap.get("step_width")))){
				baseElevator.setStep_width(String.valueOf(elevatortMap.get("step_width")));
			}
            //主机型号
			if(elevatortMap.containsKey("host_model") && !"".equals(String.valueOf(elevatortMap.get("host_model")))){
				baseElevator.setHost_model(String.valueOf(elevatortMap.get("host_model")));
			}
            //双开门打开数
			if(elevatortMap.containsKey("double_door_layer") && 
					!"".equals(String.valueOf(elevatortMap.get("double_door_layer")))){
				baseElevator.setDouble_door_layer(BigDecimal.valueOf(Double.valueOf(String.valueOf(elevatortMap.get("double_door_layer")))));
			}
            //安装合同号
			if(elevatortMap.containsKey("install_contract_code") && 
					!"".equals(String.valueOf(elevatortMap.get("install_contract_code")))){
				baseElevator.setInstall_contract_code(String.valueOf(elevatortMap.get("install_contract_code")));
			}
			if ("保修".equals(business_category)) {
				//大项目标识
				if(elevatortMap.containsKey("is_key_project") && 
						!"".equals(String.valueOf(elevatortMap.get("is_key_project")))){
					if ("KP".equals(String.valueOf(elevatortMap.get("is_key_project")))) {
						baseElevator.setIs_key_project("2");
					} else {
						baseElevator.setIs_key_project("1");
					}
				} else {
					baseElevator.setIs_key_project("1");
				}
				
				baseElevator.setUpdated_datetime(sdf.format(new Date()));

				//三包开始时间
				if(elevatortMap.containsKey("mt_begin_date") && !"".equals(String.valueOf(elevatortMap.get("mt_begin_date")))){
					String mt_begin_date = String.valueOf(elevatortMap.get("mt_begin_date"));
					baseElevator.setWarranty_begin_date(sdf.parse(mt_begin_date));
				}
				//三包结束时间
				if(elevatortMap.containsKey("mt_end_date") && !"".equals(String.valueOf(elevatortMap.get("mt_end_date")))){
					String mt_end_date = String.valueOf(elevatortMap.get("mt_end_date"));
					baseElevator.setWarranty_end_date(sdf.parse(mt_end_date));
				}
			}
			
            baseElevator.setSync_datetime(DateUtil.getNowTimestamp());
            if(CollectionUtil.isNotEmpty(baseElevatorL)){//更新
            	baseElevator.setId(baseElevatorL.get(0).getId());
            	baseElevator.setLast_update_user("system");
            	baseElevator.setLast_update_timestamp(DateUtil.getNowTimestamp());
            	baseElevator.setCreate_user(baseElevatorL.get(0).getCreate_user());
            	baseElevator.setCreate_timestamp(baseElevatorL.get(0).getCreate_timestamp());
            	if(baseElevatorL.get(0).getSource_id() == null || "".equals(baseElevatorL.get(0).getSource_id())) {
            		if(elevatortMap.containsKey("elevator_source_id")){
    					baseElevator.setSource_id(String.valueOf(elevatortMap.get("elevator_source_id")));
    				}
            	}
            	if (baseElevatorL.get(0).getHigh_end_customer() == null || "".equals(baseElevatorL.get(0).getHigh_end_customer())) {
					baseElevator.setHigh_end_customer("1");
            	}
            	baseElevatorMapper.updateByPrimaryKeySelective(baseElevator);
			}else{//新增
				if(elevatortMap.containsKey("elevator_source_id")){
					baseElevator.setSource_id(String.valueOf(elevatortMap.get("elevator_source_id")));
				}
				if ("保修".equals(business_category)) {
		            //省、直辖市
		 			if(elevatortMap.containsKey("ele_province") && !"".equals(String.valueOf(elevatortMap.get("ele_province")))){
						 BaseDictionary baseDictionary =  new BaseDictionary();
				            baseDictionary.setDict_name(String.valueOf(elevatortMap.get("ele_province")));
				            baseDictionary.setDict_type("province");
				            List<BaseDictionary> baseDictionaryList = baseDictionaryMapper.selectBySqlConditions(baseDictionary);
				            if(baseDictionaryList.size() > 0){
								 BaseDictionary db =  new BaseDictionary();
								 db.setDict_code(baseDictionaryList.get(0).getP_dict_code());
							 	 db.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
								 db.setDict_type("state");
								 List<BaseDictionary> dbl = baseDictionaryMapper.selectBySqlConditions(db);
								 baseElevator.setRegion_state_name(dbl.get(0).getDict_name());
				            	
				            	 baseElevator.setRegion_province_name(String.valueOf(elevatortMap.get("ele_province")));
				            	 baseElevator.setEle_province(baseDictionaryList.get(0).getDict_code());
						         baseElevator.setEle_state(baseDictionaryList.get(0).getP_dict_code());
				            }
					}
		            //城市
					if(elevatortMap.containsKey("ele_city") && !"".equals(String.valueOf(elevatortMap.get("ele_city")))){
						 BaseDictionary baseDictionary1 =  new BaseDictionary();
				            baseDictionary1.setDict_name(String.valueOf(elevatortMap.get("ele_city")));
				            baseDictionary1.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
				            baseDictionary1.setDict_type("city");
				            List<BaseDictionary> baseDictionaryList1 = baseDictionaryMapper.selectBySqlConditions(baseDictionary1);
				            if(baseDictionaryList1.size() > 0){
				            	 baseElevator.setRegion_city_name(String.valueOf(elevatortMap.get("ele_city")));
				            	 baseElevator.setEle_city(baseDictionaryList1.get(0).getDict_code());
				            }
					}
		            //工号地址
					if(elevatortMap.containsKey("asset_num_address") && !"".equals(String.valueOf(elevatortMap.get("asset_num_address")))){
						baseElevator.setAsset_num_address(String.valueOf(elevatortMap.get("asset_num_address")));
					}
				}
				baseElevator.setHigh_end_customer("1");
				baseElevator.setEle_status("正常");
				baseElevator.setCreate_user("system");
				baseElevator.setCreate_timestamp(DateUtil.getNowTimestamp());
				if (baseElevator.getCertificate_date() != null && !"".equals(baseElevator.getCertificate_date())) {
					String annual_check_date = calculateAnnualCheckDate(baseElevator.getCertificate_date());
					baseElevator.setAnnual_check_date(annual_check_date);
				}
				baseElevatorMapper.insert(baseElevator);
				if ("保修".equals(business_category)) {
					addFileAssetChange(baseElevator.getId());
				}
			}

 			//双开门查询条件
 			if (baseElevator.getThrough_door() != null && "1".equals(baseElevator.getThrough_door())) {
 				BaseElevator doubleBaseElevator = new BaseElevator();
 				doubleBaseElevator.setId(baseElevator.getId());
 				doubleBaseElevator.setContract_code(contract_code);
 				doubleBaseElevator.setAsset_num(baseElevator.getAsset_num());
 				doubleBaseElevator.setThrough_door(baseElevator.getThrough_door());
 				doubleBaseElevator.setEle_door(baseElevator.getEle_door());
 				doubleBaseElevator.setEle_floor(baseElevator.getEle_floor());
 				doubleList.add(doubleBaseElevator);
 			}
 			
            ret.add(baseElevator);
		}
		return ret;
	} 
	
	//合同头处理  
	MtContract updateMtContract (Map<String, Object> mtContractMap, int cust_id) throws ParseException{
		
		MtContract mtContract = new MtContract();
		List<MtContract>  mtContractL = new ArrayList<>();
		if(mtContractMap.containsKey("contract_code")){
			mtContract.setContract_code(String.valueOf(mtContractMap.get("contract_code")));
			mtContractL =  mtContractMapper.selectBySqlConditions(mtContract);
		}
		//合同编号
		if(mtContractMap.containsKey("contract_code") && !"".equals(String.valueOf(mtContractMap.get("contract_code")))){
			mtContract.setContract_code(String.valueOf(mtContractMap.get("contract_code")));
		}
		//客户编码
		if(!"-1".equals(cust_id)){
			mtContract.setCust_id(cust_id);
		}
		//客户联系人姓名
		if(mtContractMap.containsKey("relation_person") && !"".equals(String.valueOf(mtContractMap.get("relation_person")))){
			mtContract.setRelation_person(String.valueOf(mtContractMap.get("relation_person")));
		}
		//联系电话
		if(mtContractMap.containsKey("relation_tel") && !"".equals(String.valueOf(mtContractMap.get("relation_tel")))){
			mtContract.setRelation_tel(String.valueOf(mtContractMap.get("relation_tel")));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//合同签订日期
		if(mtContractMap.containsKey("contract_sign_date") && !"".endsWith(StringUtil.toSafeString(mtContractMap.get("contract_sign_date")))){
			String contract_sign_date = String.valueOf(mtContractMap.get("contract_sign_date"));
			mtContract.setContract_sign_date(sdf.parse(contract_sign_date));
		}
		//合同类型
		if(mtContractMap.containsKey("contract_category") && !"".equals(String.valueOf(mtContractMap.get("contract_category")))){
			String contract_category = String.valueOf(mtContractMap.get("contract_category"));
			if ("电梯安装合同".equals(contract_category)
					|| "电梯调验合同".equals(contract_category)
					|| "电梯&楼宇安装合同".equals(contract_category)
					|| "电梯&楼宇调验合同".equals(contract_category)) {
				mtContract.setContract_category("1");
			} else {
				if("保修合同".equals(contract_category)){
					mtContract.setContract_category("1");
				}else if("保养合同".equals(contract_category)){
					mtContract.setContract_category("2");
				}else if("全包保养合同".equals(contract_category)){
					mtContract.setContract_category("3");
				}else if("半包保养合同".equals(contract_category)){
					mtContract.setContract_category("4");
				}else if("清包保养合同".equals(contract_category)){
					mtContract.setContract_category("5");
				}else if("电梯安装合同".equals(contract_category)){
					mtContract.setContract_category("6");
				}else if("合同".equals(contract_category)){
					mtContract.setContract_category("7");
				}else if("其他".equals(contract_category)){
					mtContract.setContract_category("8");
				}else if("技术支援合同".equals(contract_category)){
					mtContract.setContract_category("9");
				}else if("安装保修".equals(contract_category)){
					mtContract.setContract_category("10");
				}else if("改造保修".equals(contract_category)){
					mtContract.setContract_category("11");
				}else if("大包保养合同".equals(contract_category)){
					mtContract.setContract_category("12");
				}
			}
		}
		//合同状态
		mtContract.setContract_status("5");
//		if(mtContractMap.containsKey("contract_status") && !"".equals(StringUtil.toSafeString(mtContractMap.get("contract_status")))){
//			String contract_status = String.valueOf(mtContractMap.get("contract_status"));
//			if("已废弃".equals(contract_status)){
//				mtContract.setContract_status("0");
//			}else if("已退保".equals(contract_status)){
//				mtContract.setContract_status("1");
//			}else if("已驳回".equals(contract_status)){
//				mtContract.setContract_status("2");
//			}else if("已审核".equals(contract_status)){
//				mtContract.setContract_status("3");
//			}else if("已延长".equals(contract_status)){
//				mtContract.setContract_status("4");
//			}
//		}
		//业务分类
		if(mtContractMap.containsKey("business_category") && !"".equals(StringUtil.toSafeString(mtContractMap.get("business_category")))){
			String business_category_temp = String.valueOf(mtContractMap.get("business_category"));
			if ("保养".equals(business_category_temp)) {
				mtContract.setBusiness_category("1");
			} else if ("保修".equals(business_category_temp)) {
				mtContract.setBusiness_category("2");
			} else if ("改造".equals(business_category_temp)) {
				mtContract.setBusiness_category("3");
			} else {
				mtContract.setBusiness_category("");
			}
		}
		//保养周期
		if(mtContractMap.containsKey("mt_cycle") && !"".equals(String.valueOf(mtContractMap.get("mt_cycle")))){
			mtContract.setMt_cycle(BigDecimal.valueOf(Double.valueOf(String.valueOf(mtContractMap.get("mt_cycle")))));
		}
		//合同首次校对时间
		if(mtContractMap.containsKey("first_proof_datetime") && !"".equals(String.valueOf(mtContractMap.get("first_proof_datetime")))){
			String first_proof_datetime = String.valueOf(mtContractMap.get("first_proof_datetime"));
			mtContract.setFirst_proof_datetime(sdf.parse(first_proof_datetime));
		}
		//包配件金额
		if(mtContractMap.containsKey("amount_of_items") && !"".equals(StringUtil.toSafeString(mtContractMap.get("amount_of_items")))){
			mtContract.setAmount_of_items(String.valueOf(mtContractMap.get("amount_of_items")));
		}
		//急修要求到达时间（分钟）
		if(mtContractMap.containsKey("arrival_required") && !"".equals(StringUtil.toSafeString(mtContractMap.get("arrival_required")))){
			mtContract.setArrival_required(String.valueOf(mtContractMap.get("arrival_required")));
		}
		//最终客户
		if(mtContractMap.containsKey("final_customer") && !"".equals(StringUtil.toSafeString(mtContractMap.get("final_customer")))){
			mtContract.setFinal_customer(String.valueOf(mtContractMap.get("final_customer")));
		}
		//合同备注
		if(mtContractMap.containsKey("contract_remark") && !"".equals(StringUtil.toSafeString(mtContractMap.get("contract_remark")))){
			mtContract.setContract_remark(String.valueOf(mtContractMap.get("contract_remark")));
		}
		//合同来源
		if(mtContractMap.containsKey("contract_source") && !"".equals(StringUtil.toSafeString(mtContractMap.get("contract_source")))){
			mtContract.setContract_source(String.valueOf(mtContractMap.get("contract_source")));
		}
		//合同经办人
		if(mtContractMap.containsKey("contract_operator") && !"".equals(StringUtil.toSafeString(mtContractMap.get("contract_operator")))){
			mtContract.setContract_operator(String.valueOf(mtContractMap.get("contract_operator")));
		}
		//是否联合体合同
		if(mtContractMap.containsKey("is_joint_contract") && !"".equals(StringUtil.toSafeString(mtContractMap.get("is_joint_contract")))){
			mtContract.setIs_joint_contract(String.valueOf(mtContractMap.get("is_joint_contract")));
		}
		//是否发生转让
		if(mtContractMap.containsKey("is_transfer") && !"".equals(StringUtil.toSafeString(mtContractMap.get("is_transfer")))){
			mtContract.setIs_transfer(String.valueOf(mtContractMap.get("is_transfer")));
		}
		//是否多客户合同
		if(mtContractMap.containsKey("is_multi_customer") && !"".equals(StringUtil.toSafeString(mtContractMap.get("is_multi_customer")))){
			mtContract.setIs_multi_customer(String.valueOf(mtContractMap.get("is_multi_customer")));
		}
		//其他签订人
		if(mtContractMap.containsKey("other_sign_person") && !"".equals(StringUtil.toSafeString(mtContractMap.get("other_sign_person")))){
			mtContract.setOther_sign_person(String.valueOf(mtContractMap.get("other_sign_person")));
		}
		//人均月保养台量
		if(mtContractMap.containsKey("per_capita_num") && !"".equals(StringUtil.toSafeString(mtContractMap.get("per_capita_num")))){
			mtContract.setPer_capita_num(String.valueOf(mtContractMap.get("per_capita_num")));
		}
		//是否存在合同风险
		if(mtContractMap.containsKey("is_contractual_risk") && !"".equals(StringUtil.toSafeString(mtContractMap.get("is_contractual_risk")))){
			mtContract.setIs_contractual_risk(String.valueOf(mtContractMap.get("is_contractual_risk")));
		}
		//是否ITM或其他
		if(mtContractMap.containsKey("is_itm") && !"".equals(StringUtil.toSafeString(mtContractMap.get("is_itm")))){
			mtContract.setIs_itm(String.valueOf(mtContractMap.get("is_itm")));
		}
		//维保时间段
		if(mtContractMap.containsKey("mt_period") && !"".equals(StringUtil.toSafeString(mtContractMap.get("mt_period")))){
			mtContract.setMt_period(String.valueOf(mtContractMap.get("mt_period")));
		}
		//故障到达时间
		if(mtContractMap.containsKey("arrive_time") && !"".equals(StringUtil.toSafeString(mtContractMap.get("arrive_time")))){
			mtContract.setArrive_time(String.valueOf(mtContractMap.get("arrive_time")));
		}
		//困人到达时间
		if(mtContractMap.containsKey("trap_time") && !"".equals(StringUtil.toSafeString(mtContractMap.get("trap_time")))){
			mtContract.setTrap_time(String.valueOf(mtContractMap.get("trap_time")));
		}
		//救援时间
		if(mtContractMap.containsKey("rescue_time") && !"".equals(StringUtil.toSafeString(mtContractMap.get("rescue_time")))){
			mtContract.setRescue_time(String.valueOf(mtContractMap.get("rescue_time")));
		}
		//高折扣率解析
		if(mtContractMap.containsKey("discount_rate_analysis") && !"".equals(StringUtil.toSafeString(mtContractMap.get("discount_rate_analysis")))){
			mtContract.setDiscount_rate_analysis(String.valueOf(mtContractMap.get("discount_rate_analysis")));
		}
		//预计配件年销售额
		if(mtContractMap.containsKey("estimated_item_annual_sales") && !"".equals(StringUtil.toSafeString(mtContractMap.get("estimated_item_annual_sales")))){
			mtContract.setEstimated_item_annual_sales(String.valueOf(mtContractMap.get("estimated_item_annual_sales")));
		}
		//是否驻场
		if(mtContractMap.containsKey("is_on_site") && !"".equals(StringUtil.toSafeString(mtContractMap.get("is_on_site")))){
			String is_on_site = String.valueOf(mtContractMap.get("is_on_site"));
			if("是".equals(is_on_site)){
				mtContract.setIs_on_site("1");
			}else if("否".equals(is_on_site)){
				mtContract.setIs_on_site("0");
			}
		}
		mtContract.setSync_datetime(DateUtil.getNowTimestamp());

		String custCode = "";
		String signCompId = "";
		if (mtContractMap.containsKey("cust_code") && StringUtil.isNotBlank(String.valueOf(mtContractMap.get("cust_code")))) {
			custCode = String.valueOf(mtContractMap.get("cust_code"));
		}
		if (mtContractMap.containsKey("sign_comp_id") && StringUtil.isNotBlank(String.valueOf(mtContractMap.get("sign_comp_id")))) {
			signCompId = String.valueOf(mtContractMap.get("sign_comp_id"));
		}
		mtContract.setLast_update_remark("{\"cust_code\":\"" + custCode + "\",\"sign_comp_id\":\"" + signCompId + "\"}");
		
		mtContract.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
		if(mtContractL.size() > 0){//更新
			if(mtContractL.get(0).getSource_id() == null || "".equals(mtContractL.get(0).getSource_id())) {
				if(mtContractMap.containsKey("source_id")){
					mtContract.setSource_id(String.valueOf(mtContractMap.get("source_id")));
				}
			}
			mtContract.setId(mtContractL.get(0).getId());
			mtContract.setLast_update_user("system");
			mtContract.setLast_update_timestamp(DateUtil.getNowTimestamp());
			mtContract.setCreate_user(mtContractL.get(0).getCreate_user());
			mtContract.setCreate_timestamp(mtContractL.get(0).getCreate_timestamp());
			mtContractMapper.updateByPrimaryKeySelective(mtContract);
		}else{//新增
			if(mtContractMap.containsKey("source_id")){
				mtContract.setSource_id(String.valueOf(mtContractMap.get("source_id")));
			}
			//签单分公司
			if(mtContractMap.containsKey("sign_comp_id") && !"".equals(String.valueOf(mtContractMap.get("sign_comp_id")))){
				//查询机构
				BaseCompSync baseCompSync =  new BaseCompSync();
				baseCompSync.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
				baseCompSync.setErp_comp_id(String.valueOf(mtContractMap.get("sign_comp_id")));
				List<BaseCompSync> baseCompSyncL = baseCompSyncMapper.selectBySqlConditions(baseCompSync);
				if(baseCompSyncL.size() > 0 ){
					//查询分公司
					BaseCompany baseCompany = new BaseCompany();
					baseCompany.setErp_comp_id(baseCompSyncL.get(0).getId());
					baseCompany.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
					List<BaseCompany> baseCompanyL = baseCompanyMapper.selectBySqlConditions(baseCompany);
					if(baseCompanyL.size() > 0){
						mtContract.setSign_comp_id(baseCompanyL.get(0).getId());
					}
				}
			}
			mtContract.setCreate_user("system");
			mtContract.setCreate_timestamp(DateUtil.getNowTimestamp());
			mtContractMapper.insert(mtContract);
		}
		
		return mtContract;
	}
	
	//合同行处理
	@SuppressWarnings("unchecked")
	void updateMtContractDtl(MtContract mtContract, List<BaseElevator> retBaseElevator, Map<String, Object> contractMap, String business_category) throws ParseException{
		MtContractDtl mtContractDtl;
		List<Map<String, Object>> elevatortList = (List<Map<String, Object>>) contractMap.get("line_list");
		List<MtContractDtl> postList = new ArrayList<>();
		int num = retBaseElevator.size();
		for(int i = 0; i < num ; i++){
			mtContractDtl =  new MtContractDtl();
			List<MtContractDtl> mtContractDtlList = new ArrayList<MtContractDtl>();
			
//			if(elevatortList.get(i).containsKey("source_id") && !"".equals(String.valueOf(elevatortList.get(i).get("source_id")))){
//				mtContractDtl.setSource_id(String.valueOf(elevatortList.get(i).get("source_id")));
//				mtContractDtl.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
//				mtContractDtlList = mtContractDtlMapper.selectBySqlConditions(mtContractDtl);
//			}
			//工号id
			mtContractDtl.setAsset_id(retBaseElevator.get(i).getId());
			//合同编号
			mtContractDtl.setContract_code(retBaseElevator.get(i).getContract_code());
			//合同行原始数据
			mtContractDtlList = mtContractDtlMapper.selectBySqlConditions(mtContractDtl);
			
			if ("".equals(retBaseElevator.get(i).getContract_code())) {

				MtContractDtl fictitiousMtContractDtl = new MtContractDtl();
				fictitiousMtContractDtl.setContract_code(""); 
				fictitiousMtContractDtl.setAsset_id(mtContractDtl.getAsset_id());
				fictitiousMtContractDtl.setSource_id("-1");
				fictitiousMtContractDtl.setEnable_flag("0");
				fictitiousMtContractDtl.setLast_update_user("system");
				fictitiousMtContractDtl.setLast_update_timestamp(DateUtil.getNowTimestamp());
				fictitiousMtContractDtl.setSync_datetime(DateUtil.getNowTimestamp());
				//更改为虚拟合同行
				if (CollectionUtil.isNotEmpty(mtContractDtlList)) {
					fictitiousMtContractDtl.setId(mtContractDtlList.get(0).getId());
					mtContractDtlMapper.updateByPrimaryKeySelective(fictitiousMtContractDtl);
				} else {
					fictitiousMtContractDtl.setCreate_user("system");
					fictitiousMtContractDtl.setCreate_timestamp(DateUtil.getNowTimestamp());
					mtContractDtlMapper.insert(fictitiousMtContractDtl);
				}
			} else {
				mtContractDtl.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
				//合同性质
				if(contractMap.containsKey("contract_type") && !"".equals(String.valueOf(contractMap.get("contract_type")))){
					String contract_type = String.valueOf(contractMap.get("contract_type"));
					if(contract_type.indexOf("新签") > -1){
						mtContractDtl.setContract_nature("1");
					}else if(contract_type.indexOf("续保") > -1){
						mtContractDtl.setContract_nature("2");
					}else{
						mtContractDtl.setContract_nature("3");
					}
					
				}
				//设备保修月 equip_warranty_month
				if(elevatortList.get(i).containsKey("equip_warranty_month") && !"".equals(String.valueOf(elevatortList.get(i).get("equip_warranty_month")))){
					mtContractDtl.setEquip_warranty_month(Short.valueOf(String.valueOf(elevatortList.get(i).get("equip_warranty_month"))));
				}
				//安装保修月 install_warranty_month
				if(elevatortList.get(i).containsKey("install_warranty_month") && !"".equals(String.valueOf(elevatortList.get(i).get("install_warranty_month")))){
					mtContractDtl.setInstall_warranty_month(Short.valueOf(String.valueOf(elevatortList.get(i).get("install_warranty_month"))));
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//保养开始时间  mt_begin_date
				if(elevatortList.get(i).containsKey("mt_begin_date") && !"".equals(String.valueOf(elevatortList.get(i).get("mt_begin_date")))){
					String mt_begin_date = String.valueOf(elevatortList.get(i).get("mt_begin_date"));
					mtContractDtl.setMt_begin_date(sdf.parse(mt_begin_date));
				}
				
				//三包月份 mt_months
				if ("保修".equals(business_category)) {
					short equip_warranty_month = 0;
					short install_warranty_month = 0;
					if (mtContractDtl.getEquip_warranty_month() != null) {
						equip_warranty_month = mtContractDtl.getEquip_warranty_month();
					}
					if (mtContractDtl.getInstall_warranty_month() != null) {
						install_warranty_month = mtContractDtl.getInstall_warranty_month();
					}
					if (equip_warranty_month > install_warranty_month) {
						mtContractDtl.setMt_months(equip_warranty_month);
					} else {
						mtContractDtl.setMt_months(install_warranty_month);
					}
				} else {
					if(elevatortList.get(i).containsKey("mt_months") && !"".equals(String.valueOf(elevatortList.get(i).get("mt_months")))){
						mtContractDtl.setMt_months(Short.valueOf(String.valueOf(elevatortList.get(i).get("mt_months"))));
					}
				}
				
				//保养结束时间  mt_end_date
				if(elevatortList.get(i).containsKey("mt_end_date") && !"".equals(String.valueOf(elevatortList.get(i).get("mt_end_date")))){
					String mt_end_date = String.valueOf(elevatortList.get(i).get("mt_end_date"));
					mtContractDtl.setMt_end_date(sdf.parse(mt_end_date));
					mtContractDtl.setMt_end_date_org(sdf.parse(mt_end_date));
				}
				
				//合同行状态
				if (mtContractDtl.getMt_begin_date() == null || mtContractDtl.getMt_end_date() == null) {
					//已废弃
					mtContractDtl.setContract_dtl_status("0");
				} else {
					//已审核
					mtContractDtl.setContract_dtl_status("5");
				}
				
				//保养签约价 mt_contract_price
				if(elevatortList.get(i).containsKey("mt_contract_price") && !"".equals(String.valueOf(elevatortList.get(i).get("mt_contract_price")))){
					String mt_contract_price = String.valueOf(elevatortList.get(i).get("mt_contract_price"));
					mtContractDtl.setMt_contract_price(new BigDecimal(mt_contract_price));
				}
				//保养月单价 mt_month_price
//				if (mtContractDtl.getMt_contract_price() != null && mtContractDtl.getMt_months() != null) {
//					BigDecimal mt_contract_price = mtContractDtl.getMt_contract_price();
//					Short mt_months = mtContractDtl.getMt_months();
//					BigDecimal mt_month_price = mt_contract_price.divide(new BigDecimal(mt_months), 2, BigDecimal.ROUND_HALF_UP);
//					mtContractDtl.setMt_month_price(mt_month_price);
//					mtContractDtl.setAsset_num_price(mt_month_price);
//				} else {
//					mtContractDtl.setMt_month_price(null);
//					mtContractDtl.setAsset_num_price(null);
//				}
				if(elevatortList.get(i).containsKey("mt_month_price") && !"".equals(String.valueOf(elevatortList.get(i).get("mt_month_price")))){
					String mt_month_price = String.valueOf(elevatortList.get(i).get("mt_month_price"));
					mtContractDtl.setMt_month_price(new BigDecimal(mt_month_price));
					mtContractDtl.setAsset_num_price(mtContractDtl.getMt_month_price());
				}
				//合同行备注 contract_dtl_remark
				if(elevatortList.get(i).containsKey("contract_dtl_remark") && !"".equals(String.valueOf(elevatortList.get(i).get("contract_dtl_remark")))){
					mtContractDtl.setContract_dtl_remark(String.valueOf(elevatortList.get(i).get("contract_dtl_remark")));
				}
				//收款跟进人 payee_follow_person
				if(elevatortList.get(i).containsKey("payee_follow_person") && !"".equals(String.valueOf(elevatortList.get(i).get("payee_follow_person")))){
					mtContractDtl.setPayee_follow_person(String.valueOf(elevatortList.get(i).get("payee_follow_person")));
				}
				//供应商名称 supplier_name
				if(elevatortList.get(i).containsKey("supplier_name") && !"".equals(String.valueOf(elevatortList.get(i).get("supplier_name")))){
					mtContractDtl.setSupplier_name(String.valueOf(elevatortList.get(i).get("supplier_name")));
				}
				//作业方式  practices
				if(elevatortList.get(i).containsKey("practices") && !"".equals(String.valueOf(elevatortList.get(i).get("practices")))){
					String practices = String.valueOf(elevatortList.get(i).get("practices"));
					if("自保".equals(practices)){
						mtContractDtl.setPractices("1");
					}else if ("委外".equals(practices)){
						mtContractDtl.setPractices("2");
					}else if ("他保".equals(practices)){
						mtContractDtl.setPractices("3");
					}else if ("自保A".equals(practices)){
						mtContractDtl.setPractices("A1");
					}
				}
				if ("保修".equals(business_category)) {
					//合同属性
					if (elevatortList.get(i).containsKey("contract_properties") && !"".equals(String.valueOf(elevatortList.get(i).get("contract_properties")))){
						String contract_properties = String.valueOf(elevatortList.get(i).get("contract_properties"));
						if(contract_properties.contains("安装")){
							mtContractDtl.setContract_properties("0");
						}else if(contract_properties.contains("调验")){
							mtContractDtl.setContract_properties("1");
						}
					}
				}
				mtContractDtl.setSync_datetime(DateUtil.getNowTimestamp());
				
				if(mtContractDtlList.size() > 0 ){//更新
					if(mtContractDtlList.get(0).getSource_id() == null || "".equals(mtContractDtlList.get(0).getSource_id())) {
						if(elevatortList.get(i).containsKey("source_id") && !"".equals(String.valueOf(elevatortList.get(i).get("source_id")))){
							mtContractDtl.setSource_id(String.valueOf(elevatortList.get(i).get("source_id")));
						}
					}
					if (mtContractDtlList.get(0).getWork_cycle() == null) {
						if (mtContract.getMt_cycle().intValue() == 2) {
							mtContractDtl.setWork_cycle(new BigDecimal(2));
						} else if (mtContract.getMt_cycle().intValue() == 1) {
							mtContractDtl.setWork_cycle(new BigDecimal(4));
						}
					}
					
					//临时补充站信息
					if (mtContractDtlList.get(0).getStat_id() == null) {
						if(elevatortList.get(i).containsKey("stat_old_id") && !"".equals(String.valueOf(elevatortList.get(i).get("stat_old_id")))){
							List<Map<String, Object>> statIdList = erpManagerMapper.getStatIdByErpStatId(String.valueOf(elevatortList.get(i).get("stat_old_id")));
							if(statIdList.size() > 0 && statIdList != null){
						    	mtContractDtl.setStat_id(Integer.valueOf(StringUtil.toSafeString(statIdList.get(0).get("id"))));
						    }
						}
					}
					
					mtContractDtl.setId(mtContractDtlList.get(0).getId());
					mtContractDtl.setLast_update_user("system");
					mtContractDtl.setLast_update_timestamp(DateUtil.getNowTimestamp());
					mtContractDtl.setCreate_user(mtContractDtlList.get(0).getCreate_user());
					mtContractDtl.setCreate_timestamp(mtContractDtlList.get(0).getCreate_timestamp());
					mtContractDtlMapper.updateByPrimaryKeySelective(mtContractDtl);
				}else{
					//新增
					if(elevatortList.get(i).containsKey("source_id") && !"".equals(String.valueOf(elevatortList.get(i).get("source_id")))){
						mtContractDtl.setSource_id(String.valueOf(elevatortList.get(i).get("source_id")));
					}
					if (mtContract.getMt_cycle().intValue() == 2) {
						mtContractDtl.setWork_cycle(new BigDecimal(2));
					} else if (mtContract.getMt_cycle().intValue() == 1) {
						mtContractDtl.setWork_cycle(new BigDecimal(4));
					}
					//签单分公司
					if(mtContract.getSign_comp_id() != null){
						mtContractDtl.setSign_comp_id(mtContract.getSign_comp_id());
					}
					//保养公司  comp_id
					if(elevatortList.get(i).containsKey("comp_id") && !"".equals(String.valueOf(elevatortList.get(i).get("comp_id")))){
						mtContractDtl.setLast_update_remark("{\"comp_id\":\"" + String.valueOf(elevatortList.get(i).get("comp_id")) + "\"}");
						//查询机构
						BaseCompSync baseCompSync =  new BaseCompSync();
						baseCompSync.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
						baseCompSync.setErp_comp_id(String.valueOf(elevatortList.get(i).get("comp_id")));
						List<BaseCompSync> baseCompSyncL = baseCompSyncMapper.selectBySqlConditions(baseCompSync);
						if(baseCompSyncL.size() > 0 ){
							//查询分公司
							BaseCompany baseCompany = new BaseCompany();
							baseCompany.setErp_comp_id(baseCompSyncL.get(0).getId());
							baseCompany.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
							List<BaseCompany> baseCompanyL = baseCompanyMapper.selectBySqlConditions(baseCompany);
							if(baseCompanyL.size() > 0){
								mtContractDtl.setComp_id(baseCompanyL.get(0).getId());
							}
						}
					}
					//所属站ID  stat_old_id
					if(elevatortList.get(i).containsKey("stat_old_id") && !"".equals(String.valueOf(elevatortList.get(i).get("stat_old_id")))){
						List<Map<String, Object>> statIdList = erpManagerMapper.getStatIdByErpStatId(String.valueOf(elevatortList.get(i).get("stat_old_id")));
						if(statIdList.size() > 0 && statIdList != null){
					    	mtContractDtl.setStat_id(Integer.valueOf(StringUtil.toSafeString(statIdList.get(0).get("id"))));
					    }
					}
					//是否最新
					mtContractDtl.setIs_newest("0");
					//是否在保
					mtContractDtl.setIs_warranty_period("0");
					
					mtContractDtl.setCreate_user("system");
					mtContractDtl.setCreate_timestamp(DateUtil.getNowTimestamp());
					mtContractDtlMapper.insert(mtContractDtl);
				}
				postList.add(mtContractDtl);
			}
		}
		String httpcode = PlatformGlobalVar.SYS_PROPERTIES.get("hmtPath") + "mt/syncGyrusContractInfo.api";
		try {
			if(CollectionUtil.isNotEmpty(postList)) {
				Map<String, Object> m = new HashMap<>();
				m.put("list", postList);
				JsonConfig jsonConfig = new JsonConfig();  
				jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());  
				jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new JsonTimestampProcessor());  
				JSONObject paramPost = JSONObject.fromObject(m, jsonConfig);
				HttpUtil.doPostWithParam(httpcode, paramPost);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 创建工号时，插入固定资产状态变化信息
	 * @param param
	 * @author syp602
	 * @创建时间 2019/3/27
	 * @throws Exception 
	 */
	@Override
	public void addFileAssetChange(int asset_id) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		FileAssetChange fileAssetChange = new FileAssetChange();
		fileAssetChange.setAsset_id(asset_id);
		fileAssetChange.setAsset_change_status("正常");
		fileAssetChange.setOpt_date(sdf.format(new Date()));
		fileAssetChange.setOpt_content("系统管理员");
		fileAssetChange.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
		fileAssetChange.setCreate_user("系统管理员");
		fileAssetChange.setCreate_timestamp(DateUtil.getNowTimestamp());
		fileAssetChange.setLast_update_user("系统管理员");
		fileAssetChange.setLast_update_timestamp(DateUtil.getNowTimestamp());
		fileAssetChangeMapper.insert(fileAssetChange);
	}

	/**
	 * 基础数据——获取出厂安装信息
	 * @param param
	 * @author syp661
	 * @创建时间 2019/1/10
	 * @throws Exception 
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public Result syncGetOutAndInstallInfo(JSONObject param) throws Exception {
		Result result = new Result();
		String url = request.getRequestURL().toString();
		try{
			List<Map<String, Object>> bcsList =  (List<Map<String, Object>>)param.get("list");
			int num = bcsList.size();
			BaseElevator baseElevator;
			for(int i = 0; i < num; i++){
				baseElevator =  new BaseElevator();
				Map<String, Object> baseMap = bcsList.get(i); 
				if(baseMap.containsKey("asset_num")){
					//保存工号安装信息
					baseElevator.setAsset_num(String.valueOf(baseMap.get("asset_num")));
					List<BaseElevator> baseElevatorList= baseElevatorMapper.selectBySqlConditions(baseElevator);
					if(baseElevatorList.size() > 0 ){
						if(baseMap.containsKey("out_factory_date")){
							baseElevatorList.get(0).setOut_factory_date(String.valueOf(baseMap.get("out_factory_date")));
						}
						if(baseMap.containsKey("install_company")){
							baseElevatorList.get(0).setInstall_company(String.valueOf(baseMap.get("install_company")));
						}
						if(baseMap.containsKey("certificate_date")){
							baseElevatorList.get(0).setCertificate_date(String.valueOf(baseMap.get("certificate_date")));
						}
						if(baseMap.containsKey("install_finish_date")){
							baseElevatorList.get(0).setInstall_finish_date(String.valueOf(baseMap.get("install_finish_date")));
						}
						if(baseMap.containsKey("trans_mt_date")){
							baseElevatorList.get(0).setTrans_mt_date(String.valueOf(baseMap.get("trans_mt_date")));
						}
						if(baseMap.containsKey("trans_cust_date")){
							baseElevatorList.get(0).setTrans_cust_date(String.valueOf(baseMap.get("trans_cust_date")));
						}
						baseElevatorList.get(0).setLast_update_user("system");
						baseElevatorList.get(0).setLast_update_timestamp(DateUtil.getNowTimestamp());
						baseElevatorList.get(0).setSync_datetime(DateUtil.getNowTimestamp());
						baseElevatorMapper.updateByPrimaryKeySelective(baseElevatorList.get(0));
					}
				}
			}
			//保存操作信息
		    result = ResultUtil.resultSuccess(new HashMap<>());
		    QueueUtil.put(1, 2, url, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "获取出厂安装信息", "");
		}catch (Exception e){
			//获取异常数据
			String exceptionStr = ExceptionUtils.getStackTrace(e);
			result = ResultUtil.resultFail(exceptionStr);
			//保存错误日志
			QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "获取出厂安装信息", exceptionStr);
			throw new RuntimeException();	
		}
		return result;
	}
	
	/**
	 * 当前日期 >=保养开始日期
	 * @param mtDateStr
	 * @return
	 * @throws Exception
	 */
	private boolean checkMtDate(String mtDateStr) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = sdf.parse(sdf.format(new Date()));
		Date mtDate = sdf.parse(mtDateStr.substring(0, 10));
		if (now.getTime() < mtDate.getTime()) {
			return false;
		} else {
			return true;
		}
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
