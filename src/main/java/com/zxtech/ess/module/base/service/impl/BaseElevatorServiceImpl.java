package com.zxtech.ess.module.base.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zxtech.ess.app.util.SysConstants;
import com.zxtech.ess.module.base.bean.BaseCasualElevatorSearchBean;
import com.zxtech.ess.module.base.bean.BaseElevatorSearchBean;
import com.zxtech.ess.module.base.mapper.BaseCompanyManagerMapper;
import com.zxtech.ess.module.base.mapper.BaseElevatorManagerMapper;
import com.zxtech.ess.module.base.service.IBaseElevatorService;
import com.zxtech.ess.module.gen.bean.BaseElevator;
import com.zxtech.ess.module.gen.bean.FileAssetChange;
import com.zxtech.ess.module.gen.bean.MtContractDtl;
import com.zxtech.ess.module.gen.mapper.BaseElevatorMapper;
import com.zxtech.ess.module.gen.mapper.FileAssetChangeMapper;
import com.zxtech.ess.module.gen.mapper.MtContractDtlMapper;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.constant.WebConstants;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.JsonUtil;
import com.zxtech.platform.utils.StringUtil;
import com.zxtech.platform.utils.excel.EasyExcelUtil;
import com.zxtech.platform.utils.page.PageFetchNextUtil;
import com.zxtech.platform.utils.page.PageHandler;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

@Service("baseElevatorServiceImpl")
public class BaseElevatorServiceImpl implements IBaseElevatorService {
	
	private static GsonBuilder gsonBulder = new GsonBuilder();
	private final static Logger LOGGER = LoggerFactory.getLogger(BaseElevatorServiceImpl.class);
	static{
		gsonBulder.registerTypeAdapter(java.sql.Timestamp.class, JsonUtil.TIMESTAMP);
		gsonBulder.registerTypeAdapter(java.sql.Date.class, JsonUtil.DATE);
	}
	
	@Autowired
	private BaseElevatorManagerMapper baseElevatorManagerMapper;
	@Autowired
	private BaseElevatorMapper baseElevatorMapper;
	@Autowired
	private FileAssetChangeMapper fileAssetChangeMapper;
	@Autowired
	private MtContractDtlMapper mtContractDtlMapper;
	@Autowired
	private BaseCompanyManagerMapper baseCompanyManagerMapper;
	
	public BaseElevatorSearchBean checkQueryInfo(BaseElevatorSearchBean queryInfo) {
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getComp_id()))
			queryInfo.setComp_id(null);
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getStat_id()))
			queryInfo.setStat_id(null);
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getEle_category_name()))
			queryInfo.setEle_category_name(null);
		if (SysConstants.SYSTEM_SELECT_TEXT_ALL.equals(queryInfo.getKey_property_name()))
			queryInfo.setKey_property_name(null);
		return queryInfo;
	}
	
	@Override
	public Map<String, Object> elevatorListPage(BaseElevatorSearchBean queryInfo) {
		BaseElevatorSearchBean searchBean = checkQueryInfo(queryInfo);
		searchBean.setPageQeury(true);
		return PageFetchNextUtil.warp(
				() -> baseElevatorManagerMapper.elevListCount(searchBean),
				() -> baseElevatorManagerMapper.elevList(searchBean));
	}
	
	@Override
	public ResultBean export(HttpServletRequest request, HttpServletResponse response, UserBean user,
			BaseElevatorSearchBean queryInfo) {
		BaseElevatorSearchBean searchBean = checkQueryInfo(queryInfo);
		//		数据量较小的导出，没有单独写count查询的情况
		/*return EasyExcelUtil.writeExcelMoreSheetMoreWrite(() -> PageHelper.count(() -> baseElevatorManagerMapper.elevExportList(queryInfo)),
				() -> baseElevatorManagerMapper.elevExportList(searchBean), searchBean);*/
		return EasyExcelUtil.writeExcelMoreSheetMoreWrite(() -> baseElevatorManagerMapper.elevExportListCount(searchBean),
				() -> baseElevatorManagerMapper.elevExportList(searchBean), searchBean);
	}
	
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String update(BaseElevator bean, UserBean user) {
		int ret = 0;
		BaseElevator baseElevator = baseElevatorMapper.selectByPrimaryKey(bean.getId());
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), baseElevator.getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
		baseElevator.setLast_update_user(user.getRealname());
		baseElevator.setLast_update_timestamp(DateUtil.getNowTimestamp());
		baseElevator.setEle_state(bean.getEle_state());
		baseElevator.setEle_province(bean.getEle_province());
		baseElevator.setEle_city(bean.getEle_city());
		baseElevator.setEle_district(bean.getEle_district());
		baseElevator.setProj_type(bean.getProj_type());
		baseElevator.setUse_customer(bean.getUse_customer());
		baseElevator.setProj_id(bean.getProj_id());
		baseElevator.setMansion_name(bean.getMansion_name());
		baseElevator.setEle_no(bean.getEle_no());
		baseElevator.setAsset_num_address(bean.getAsset_num_address());
		baseElevator.setEle_brand(bean.getEle_brand());
		baseElevator.setIs_domestic(bean.getIs_domestic());
		baseElevator.setIs_import(bean.getIs_import());
		baseElevator.setIs_recombination(bean.getIs_recombination());
		baseElevator.setImport_asset_num(bean.getImport_asset_num());
		baseElevator.setAsset_num_before(bean.getAsset_num_before());
		baseElevator.setParts_asset_num(bean.getParts_asset_num());
		baseElevator.setInstall_contract_code(bean.getInstall_contract_code());
		baseElevator.setEquipment_code(bean.getEquipment_code());
		baseElevator.setRegistered_code(bean.getRegistered_code());
		baseElevator.setTrans_mt_date(bean.getTrans_mt_date());
		baseElevator.setTrans_cust_date(bean.getTrans_cust_date());
		baseElevator.setCompletion_date(bean.getCompletion_date());
		baseElevator.setWarranty_begin_date(bean.getWarranty_begin_date());
		baseElevator.setWarranty_end_date(bean.getWarranty_end_date());
		baseElevator.setAnnual_check_date(bean.getAnnual_check_date());
		baseElevator.setSpecial_label(bean.getSpecial_label());
		baseElevator.setUse_no(bean.getUse_no());
		baseElevator.setService_num(bean.getService_num());
		baseElevator.setFile_num(bean.getFile_num());
		baseElevator.setEle_category(bean.getEle_category());
		baseElevator.setEle_type(bean.getEle_type());
		baseElevator.setEle_speed(bean.getEle_speed());
		baseElevator.setEle_carry(bean.getEle_carry());
		baseElevator.setEle_high(bean.getEle_high());
		baseElevator.setStep_width(bean.getStep_width());
		baseElevator.setEle_floor(bean.getEle_floor());
		baseElevator.setEle_stop(bean.getEle_stop());
		baseElevator.setEle_door(bean.getEle_door());
		baseElevator.setConversion_layer(bean.getConversion_layer());
		baseElevator.setEle_angle(bean.getEle_angle());
		baseElevator.setDrive_way(bean.getDrive_way());
		baseElevator.setDouble_door_layer(bean.getDouble_door_layer());
		baseElevator.setCylinders_num(bean.getCylinders_num());
		baseElevator.setJack_up_way(bean.getJack_up_way());
		baseElevator.setProjection_length(bean.getProjection_length());
		baseElevator.setExtent_length(bean.getExtent_length());
		baseElevator.setSpecial_function(bean.getSpecial_function());
		baseElevator.setCust_special(bean.getCust_special());
		baseElevator.setUpdated_datetime(bean.getUpdated_datetime());
		baseElevator.setEle_remark(bean.getEle_remark());
		baseElevator.setNo_machine_room(bean.getNo_machine_room());
		baseElevator.setThrough_door(bean.getThrough_door());
		baseElevator.setEle_product(bean.getEle_product());
		baseElevator.setModel_desc(bean.getModel_desc());
		baseElevator.setEle_model(bean.getEle_model());
		baseElevator.setDemolition_date(bean.getDemolition_date());
		baseElevator.setCertificate_date(bean.getCertificate_date());
		baseElevator.setHost_model(bean.getHost_model());
		baseElevator.setRegion_state_name(bean.getRegion_state_name());
		baseElevator.setRegion_province_name(bean.getRegion_province_name());
		baseElevator.setRegion_city_name(bean.getRegion_city_name());
		baseElevator.setRegion_district_name(bean.getRegion_district_name());
		baseElevator.setHigh_end_customer(bean.getHigh_end_customer());
		ret = baseElevatorMapper.updateByPrimaryKey(baseElevator);
		if (ret == 0)
			return ResultConstants.ERROR;
		return ResultConstants.SUCCESS;
	}
	
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String enable(BaseElevator bean, UserBean user) {
		int ret = 0;
		BaseElevator baseElevator = baseElevatorMapper.selectByPrimaryKey(bean.getId());
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), baseElevator.getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
		bean.setEnable_flag("1");
		bean.setLast_update_user(user.getRealname());
		bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		ret = baseElevatorMapper.updateByPrimaryKeySelective(bean);
		if (ret == 0)
			return ResultConstants.ERROR;
		return ResultConstants.SUCCESS;
	}
	
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String doDisable(BaseElevator bean, UserBean user) {
		int ret = 0;
		BaseElevator baseElevator = baseElevatorMapper.selectByPrimaryKey(bean.getId());
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), baseElevator.getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
		//检查是否存在在保和同，如果是在保的工号不允许禁用
		if(baseElevatorManagerMapper.elevExistEffectContract(bean.getId()) > 0)
			return ResultConstants.ELEV_EXIST_EFFECT_CONTRACT;
		bean.setEnable_flag("0");
		bean.setLast_update_user(user.getRealname());
		bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		ret = baseElevatorMapper.updateByPrimaryKeySelective(bean);
		if (ret == 0)
			return ResultConstants.ERROR;
		return ResultConstants.SUCCESS;
	}

	@Override
	public Map<String, Object> elevChangeStatusListPage(BaseElevatorSearchBean queryInfo) {
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() -> baseElevatorManagerMapper.elevChangeStatusListPage(queryInfo));
		return PageHandler.wrapPageMap(pageResult);
	}

	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String saveChangeStatus(FileAssetChange bean, UserBean user) {
		int ret = 0;
		BaseElevator baseElevator = new BaseElevator();
		baseElevator.setId(bean.getAsset_id());
		baseElevator.setEle_status(bean.getAsset_change_status());
		baseElevator.setLast_update_user(user.getRealname());
		baseElevator.setLast_update_timestamp(DateUtil.getNowTimestamp());
		ret = baseElevatorMapper.updateByPrimaryKeySelective(baseElevator);
		if (ret == 0)
			return ResultConstants.ERROR;
		bean.setCreate_user(user.getRealname());
		bean.setCreate_timestamp(DateUtil.getNowTimestamp());
		fileAssetChangeMapper.insertSelective(bean);
		return ResultConstants.SUCCESS;
	}

	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String deleteChangeStatus(FileAssetChange bean, UserBean user) {
		int ret = 0;
		ret = fileAssetChangeMapper.deleteByPrimaryKey(bean.getId());
		if (ret == 0) {
			return ResultConstants.ERROR;
		}else {
			BaseElevatorSearchBean queryInfo = new BaseElevatorSearchBean();
			queryInfo.setAsset_id(bean.getAsset_id());
			List<Map<String, Object>> list = baseElevatorManagerMapper.elevChangeStatusListPage(queryInfo);
			BaseElevator baseElevator = new BaseElevator();
			baseElevator.setId(bean.getAsset_id());
			baseElevator.setLast_update_user(user.getRealname());
			baseElevator.setLast_update_timestamp(DateUtil.getNowTimestamp());
			if(list.size()>0) {
				baseElevator.setEle_status(StringUtil.toSafeString(list.get(0).get("asset_change_status")));
			}else {
				baseElevator.setEle_status("");
			}
			baseElevatorMapper.updateByPrimaryKeySelective(baseElevator);
		}
		return ResultConstants.SUCCESS;
	}

	@Override
	public List<Map<String, Object>> fetchElevatorBrandData() {
		return baseElevatorManagerMapper.fetchElevatorBrandData();
	}

	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String elevatorbatchmodify(String rows, UserBean user) {
		int ret = 0;
		String resStr = ResultConstants.SUCCESS;
		Gson gson = gsonBulder.create();
		List<BaseElevator> idList = gson.fromJson(rows, new TypeToken<List<BaseElevator>>() {}.getType());
		for(BaseElevator eleBean:idList) {
			BaseElevator baseElevator = baseElevatorMapper.selectByPrimaryKey(eleBean.getId());
			if(!DateUtil.equalsTimestamp(eleBean.getLast_update_timestamp(), baseElevator.getLast_update_timestamp())) {
				resStr= ResultConstants.DATA_CHANGES;
				throw new RuntimeException(ResultConstants.DATA_CHANGES);
			}
			if(eleBean.getAsset_comp_id() != null) {
				MtContractDtl mtContractDtl = new MtContractDtl();
				mtContractDtl.setContract_code(baseElevator.getContract_code());
				mtContractDtl.setAsset_id(baseElevator.getId());
				List<MtContractDtl> rs = mtContractDtlMapper.selectBySqlConditions(mtContractDtl);
				if(rs != null && rs.size() >0) {
					MtContractDtl bean = rs.get(0);
					bean.setComp_id(eleBean.getAsset_comp_id());
					mtContractDtlMapper.updateByPrimaryKey(bean);
				}
			}
			if(eleBean.getEle_province() != null) {
				baseElevator.setEle_province(eleBean.getEle_province());
				baseElevator.setRegion_province_name(eleBean.getRegion_province_name());
				baseElevator.setEle_city(null); // 为了保证省、城市、区县的联动关系正确
				baseElevator.setRegion_city_name(null);
				baseElevator.setEle_district(null);
				baseElevator.setRegion_district_name(null);
			}
			if(eleBean.getEle_city() != null) {
				baseElevator.setEle_city(eleBean.getEle_city());
				baseElevator.setRegion_city_name(eleBean.getRegion_city_name());
				baseElevator.setEle_district(null); // 为了保证省、城市、区县的联动关系正确
				baseElevator.setRegion_district_name(null);
			}
			if(eleBean.getEle_district() != null) {
				baseElevator.setEle_district(eleBean.getEle_district());
				baseElevator.setRegion_district_name(eleBean.getRegion_district_name());
			}
			if(eleBean.getProj_type() != null) {
				baseElevator.setProj_type(eleBean.getProj_type());
			}
			if(eleBean.getUse_customer() != null) {
				if(eleBean.getUse_customer() == 0) {
					baseElevator.setUse_customer(null);
				}else {
					baseElevator.setUse_customer(eleBean.getUse_customer());
				}
				
			}
			if(eleBean.getProj_id() != null) {
				if(eleBean.getProj_id() == 0) {
					baseElevator.setProj_id(null);
				}else {
					baseElevator.setProj_id(eleBean.getProj_id());
				}
				
			}
			if(eleBean.getMansion_name() != null) {
				baseElevator.setMansion_name(eleBean.getMansion_name());
			}
			if(eleBean.getAsset_num_address() != null) {
				baseElevator.setAsset_num_address(eleBean.getAsset_num_address());
			}
			if(eleBean.getEle_brand() != null) {
				baseElevator.setEle_brand(eleBean.getEle_brand());
			}
			if(eleBean.getIs_domestic() != null) {
				if(WebConstants.SELECT_VALUE_ALL.equals(eleBean.getIs_domestic())) {
					baseElevator.setIs_domestic(null);
				}else {
					baseElevator.setIs_domestic(eleBean.getIs_domestic());
				}
				
			}
			if(eleBean.getIs_import() != null) {
				if(WebConstants.SELECT_VALUE_ALL.equals(eleBean.getIs_import())) {
					baseElevator.setIs_import(null);
				}else {
					baseElevator.setIs_import(eleBean.getIs_import());
				}
				
			}
			if(eleBean.getIs_recombination() != null) {
				if(WebConstants.SELECT_VALUE_ALL.equals(eleBean.getIs_recombination())) {
					baseElevator.setIs_recombination(null);
				}else {
					baseElevator.setIs_recombination(eleBean.getIs_recombination());
				}
				
			}
			if(eleBean.getInstall_contract_code() != null) {
				baseElevator.setInstall_contract_code(eleBean.getInstall_contract_code());
			}
			if(eleBean.getTrans_mt_date() != null) {
				baseElevator.setTrans_mt_date(eleBean.getTrans_mt_date());
			}
			if(eleBean.getTrans_cust_date() != null) {
				baseElevator.setTrans_cust_date(eleBean.getTrans_cust_date());
			}
			if(eleBean.getCompletion_date() != null) {
				baseElevator.setCompletion_date(eleBean.getCompletion_date());
			}
			
			if(eleBean.getCertificate_date() != null) {
				baseElevator.setCertificate_date(eleBean.getCertificate_date());
			}
			if(eleBean.getWarranty_begin_date() != null) {
				
				baseElevator.setWarranty_begin_date(eleBean.getWarranty_begin_date());
			}
			if(eleBean.getWarranty_end_date() != null) {
				baseElevator.setWarranty_end_date(eleBean.getWarranty_end_date());
			}
			if(eleBean.getAnnual_check_date() != null) {
				baseElevator.setAnnual_check_date(eleBean.getAnnual_check_date());
			}
			if(eleBean.getSpecial_label() != null) {
				baseElevator.setSpecial_label(eleBean.getSpecial_label());
			}
			if(eleBean.getEle_product() != null) {
				baseElevator.setEle_product(eleBean.getEle_product());
			}
			if(eleBean.getModel_desc() != null) {
				baseElevator.setModel_desc(eleBean.getModel_desc());	
			}
			if(eleBean.getEle_model() != null) {
				baseElevator.setEle_model(eleBean.getEle_model());
			}
			if(eleBean.getEle_type() != null) {
				if(WebConstants.SELECT_VALUE_ALL.equals(eleBean.getEle_type())) {
					baseElevator.setEle_type(null);
				}else {
					baseElevator.setEle_type(eleBean.getEle_type());
					baseElevator.setDrive_way(eleBean.getDrive_way());
				}
				
			}
			if(eleBean.getEle_speed() != null) {
				baseElevator.setEle_speed(eleBean.getEle_speed());
			}
			if(eleBean.getEle_carry() != null) {
				baseElevator.setEle_carry(eleBean.getEle_carry());
			}
			if(eleBean.getEle_high() != null) {
				baseElevator.setEle_high(eleBean.getEle_high());
			}
			if(eleBean.getStep_width() != null) {
				baseElevator.setStep_width(eleBean.getStep_width());
			}
			if(eleBean.getEle_floor() != null) {
				baseElevator.setEle_floor(eleBean.getEle_floor());
			}
			if(eleBean.getEle_stop() != null) {
				baseElevator.setEle_stop(eleBean.getEle_stop());
			}
			if(eleBean.getEle_door() != null) {
				baseElevator.setEle_door(eleBean.getEle_door());
			}
			if(eleBean.getSpecial_function() != null) {
				baseElevator.setSpecial_function(eleBean.getSpecial_function());
			}
			if(eleBean.getCust_special() != null) {
				baseElevator.setCust_special(eleBean.getCust_special());
			}
			if(eleBean.getEle_remark() != null) {
				baseElevator.setEle_remark(eleBean.getEle_remark());
			}
			if(eleBean.getNo_machine_room() != null) {
				if(WebConstants.SELECT_VALUE_ALL.equals(eleBean.getNo_machine_room())) {
					baseElevator.setNo_machine_room(null);
				}else {
					baseElevator.setNo_machine_room(eleBean.getNo_machine_room());
				}
				
			}
			if(eleBean.getThrough_door() != null) {
				if(WebConstants.SELECT_VALUE_ALL.equals(eleBean.getThrough_door())) {
					baseElevator.setThrough_door(null);
				}else {
					baseElevator.setThrough_door(eleBean.getThrough_door());
				}
				
			}
			if(eleBean.getEle_category() != null) {
				if(WebConstants.SELECT_VALUE_ALL.equals(eleBean.getEle_category())) {
					baseElevator.setEle_category(null);
				}else {
					baseElevator.setEle_category(eleBean.getEle_category());
				}
				
			}
//			if(eleBean.getDrive_way() != null) {
//				baseElevator.setDrive_way(eleBean.getDrive_way());
//			}
			if(eleBean.getDemolition_date() != null) {
				baseElevator.setDemolition_date(eleBean.getDemolition_date());
			}
			
			ret = baseElevatorMapper.updateByPrimaryKey(baseElevator);
			if(ret == 0) {
				resStr= ResultConstants.ERROR;
				throw new RuntimeException(ResultConstants.ERROR);
			}
		}

		return resStr;
	}

	@Override
	public Map<String, Object> casualCheckElevatorListPage(BaseCasualElevatorSearchBean queryInfo) {
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getComp_id()))
			queryInfo.setComp_id(null);
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getStat_id()))
			queryInfo.setStat_id(null);
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getEle_category_name()))
			queryInfo.setEle_category_name(null);
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() -> baseElevatorManagerMapper.casualCheckElevList(queryInfo));
		return PageHandler.wrapPageMap(pageResult);
	}

	@Override
	public String updateAnnualCheckDate(UserBean user) {
		List<Map<String, Object>> elevators= baseElevatorManagerMapper.getAnnualCheckDateBeforeCurrent();
		
		if(elevators != null && elevators.size() > 0) {
			for(int i=0;i<elevators.size();i++) {
				try {
					updateCalAnnualcheckDate(Integer.parseInt(StringUtil.toSafeString(elevators.get(i).get("id"))), StringUtil.toSafeString(elevators.get(i).get("annual_check_date")+"-01"));

				} catch (ParseException e) {
					LOGGER.error(e.getMessage(),e);
				}

			}
		}
		List<Map<String, Object>> nullElevators= baseElevatorManagerMapper.getAnnualCheckDateIsNull();
		
		if(nullElevators != null && nullElevators.size() > 0) {
			for(int i= 0;i<nullElevators.size();i++) {
				try {
					updateCalAnnualcheckDate(Integer.parseInt(StringUtil.toSafeString(nullElevators.get(i).get("id"))),StringUtil.toSafeString(nullElevators.get(i).get("certificate_date")));
			
				} catch (ParseException e) {
					LOGGER.error(e.getMessage(),e);
				}
				
			}
		}
		
		return ResultConstants.SUCCESS;
	}
	private void updateCalAnnualcheckDate(Integer id, String annualCheckDate) throws ParseException {
		Calendar cur = Calendar.getInstance();
		cur.setTime(new Date());
		Calendar cAnnualCheckDate = Calendar.getInstance();
		cAnnualCheckDate.setTime(DateUtil.string2Date(annualCheckDate));
		int diff= (cAnnualCheckDate.get(Calendar.YEAR) - cur.get(Calendar.YEAR)) * 12 +(cAnnualCheckDate.get(Calendar.MONTH) - cur.get(Calendar.MONTH)); 
		
		 while(diff < 0) {
			 cAnnualCheckDate.add(1, 1); 
			 diff= (cAnnualCheckDate.get(Calendar.YEAR) - cur.get(Calendar.YEAR)) * 12 +(cAnnualCheckDate.get(Calendar.MONTH) - cur.get(Calendar.MONTH));
		 }
		 BaseElevator updateBean = new BaseElevator();
		 updateBean.setId(id);
		 updateBean.setAnnual_check_date(cAnnualCheckDate.get(Calendar.YEAR)+"-"+(cAnnualCheckDate.get(Calendar.MONTH)+1));
		 baseElevatorMapper.updateByPrimaryKeySelective(updateBean);
	}
	@Override
	public Map<String, Object> gyrusCheckElevatorListPage(BaseElevatorSearchBean queryInfo, UserBean user) {
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getEle_category_name()))
			queryInfo.setEle_category_name(null);

/**
 * 查询方式变来变去，不知是否会再变动，留此代码，以备后用。
 */
/*		//判断是否为系统管理员
		if("admin".equals(user.getUserCode())){
			queryInfo.setComp_id(null);
		}else {
			//获取当前登录人所属司
			BaseEmployee bean = baseEmployeeManagerMapper.getSysUserInfoById(user.getUserId());
			if(null != bean) {
				String loginUserCompId = StringUtil.toSafeString(bean.getComp_id());
				if(StringUtil.isNotBlank(loginUserCompId)) {
					String compType = "";
					//查询机构表，判断comp_type类型 1:工程总部 2:区域 3:分公司
					BaseCompany compBean = baseCompanyMapper.selectByPrimaryKey(Integer.valueOf(loginUserCompId));
					if(null != compBean) {
						compType = compBean.getComp_type();
						if("3".equals(compType)) {
							queryInfo.setComp_id(loginUserCompId);
						}else if("2".equals(compType)) {
							List<Integer> compIdList = getAreaCompIdList(loginUserCompId);
							queryInfo.setComp_id(null);
							queryInfo.setCompIdsList(compIdList);
						}else if("1".equals(compType)) {
							queryInfo.setComp_id(null);
						}else {
							throw new RuntimeException("error");
						}
					}
				}else {
					throw new RuntimeException("error");
				}			
			}else {
				throw new RuntimeException("error");
			}			
		}*/
		
		queryInfo.setComp_id(null);
		
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() -> baseElevatorManagerMapper.gyrusCheckElevatorList(queryInfo));
		return PageHandler.wrapPageMap(pageResult);
	}
	
	
	@SuppressWarnings("unused")
	private List<Integer> getAreaCompIdList(String compId){		
		List<Integer> compIdList = baseCompanyManagerMapper.fetchAllPCompIdsByAreaId(Integer.valueOf(StringUtil.toSafeString(compId)));
		//最后放入区域id
		compIdList.add(Integer.valueOf(StringUtil.toSafeString(compId)));
		
		return compIdList;
	}

}
