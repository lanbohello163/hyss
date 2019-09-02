package com.zxtech.ess.module.base.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zxtech.ess.app.util.SysConstants;
import com.zxtech.ess.module.base.bean.BaseEmployeeSearchBean;
import com.zxtech.ess.module.base.mapper.BaseCompanyManagerMapper;
import com.zxtech.ess.module.base.mapper.BaseEmployeeManagerMapper;
import com.zxtech.ess.module.base.service.IBaseEmployeeService;
import com.zxtech.ess.module.gen.bean.BaseCompany;
import com.zxtech.ess.module.gen.bean.BaseEmpCertificate;
import com.zxtech.ess.module.gen.bean.BaseEmpWorkExperience;
import com.zxtech.ess.module.gen.bean.BaseEmployee;
import com.zxtech.ess.module.gen.bean.BaseTraining;
import com.zxtech.ess.module.gen.bean.MtContractDtl;
import com.zxtech.ess.module.gen.bean.SysUser;
import com.zxtech.ess.module.gen.mapper.BaseCompanyMapper;
import com.zxtech.ess.module.gen.mapper.BaseEmpCertificateMapper;
import com.zxtech.ess.module.gen.mapper.BaseEmpWorkExperienceMapper;
import com.zxtech.ess.module.gen.mapper.BaseEmployeeMapper;
import com.zxtech.ess.module.gen.mapper.BaseTrainingMapper;
import com.zxtech.ess.module.gen.mapper.MtContractDtlMapper;
import com.zxtech.ess.module.gen.mapper.SysUserMapper;
import com.zxtech.ess.module.pub.service.IPubService;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.constant.WebConstants;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.StringUtil;
import com.zxtech.platform.utils.excel.EasyExcelUtil;
import com.zxtech.platform.utils.page.PageHandler;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

@Service("baseEmployeeServiceImpl")
public class BaseEmpolyeeServiceImpl implements IBaseEmployeeService {
	
	@Autowired
	private BaseEmployeeManagerMapper baseEmployeeManagerMapper;
	@Autowired
	private BaseEmployeeMapper baseEmployeeMapper;
	@Autowired
	private BaseEmpWorkExperienceMapper baseEmpWorkExperienceMapper;
	@Autowired
	private BaseTrainingMapper baseTrainingMapper;
	@Autowired
	private BaseEmpCertificateMapper baseEmpCertificateMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private BaseCompanyMapper baseCompanyMapper;
	@Autowired
	private BaseCompanyManagerMapper baseCompanyManagerMapper;
	@Autowired
	private IPubService pubService;
	@Autowired
	private MtContractDtlMapper mtContractDtlMapper;
	
	@Override
	public Map<String, Object> empListPage(BaseEmployeeSearchBean queryInfo) {
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getEmp_type()))
			queryInfo.setEmp_type(null);
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getComp_id()))
			queryInfo.setComp_id(null);
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getStat_id()))
			queryInfo.setStat_id(null);
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getPda_position()))
			queryInfo.setPda_position(null);
		//值班工程师负责人选择不限制数据权限改为可选所有机构-2018-12-28与王迪确认
		if(StringUtil.isNotBlank(queryInfo.getComp_or_region()))
			queryInfo.setCompIdList(null);
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() -> baseEmployeeManagerMapper.empList(queryInfo));
		return PageHandler.wrapPageMap(pageResult);
	}
	@Override
	public List<Map<String, Object>> empList(BaseEmployeeSearchBean queryInfo, String combobox_type) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(combobox_type)) {
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_ALL));
			map.put("emp_code", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_ALL));
			map.put("emp_name", SysConstants.SYSTEM_SELECT_TEXT_ALL);
			map.put("emp_code_emp_name", SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(map);
		}else if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(combobox_type)){
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_SELECT));
			map.put("emp_code", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_SELECT));
			map.put("emp_name", SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			map.put("emp_code_emp_name", SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(map);
		}
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getComp_id()) || StringUtil.isBlank(queryInfo.getComp_id()) )
			return list;
		list.addAll(baseEmployeeManagerMapper.empList(queryInfo));
		return list;
	}
	private Boolean checkRepeatEmpCode(Integer id, String emp_code) {
		BaseEmployee bean = new BaseEmployee();
		bean.setEmp_code(emp_code);
		List<BaseEmployee> list = baseEmployeeMapper.selectBySqlConditions(bean);
		if( id == null )
			return list.size()>0;
		for(BaseEmployee baseEmployee : list) {
			if( !baseEmployee.getId().equals(id) )
				return true;
		}
		return false;
	}
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String save(BaseEmployee bean, UserBean user) {
		int ret = 0;
		if (StringUtil.isBlank(bean.getEmp_code()) || checkRepeatEmpCode(null, bean.getEmp_code()))
			return ResultConstants.REPEAT;
		bean.setCreate_user(user.getRealname());
		bean.setCreate_timestamp(DateUtil.getNowTimestamp());
		bean.setEmp_type("2");
		bean.setSource_id(pubService.getSequenceOrder());
		ret = baseEmployeeMapper.insertSelective(bean);
		if (ret == 0)
			return ResultConstants.ERROR;
		return ResultConstants.SUCCESS;
	}

	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String update(BaseEmployee bean, UserBean user) {
		int ret = 0;
		BaseEmployee baseEmployee = baseEmployeeMapper.selectByPrimaryKey(bean.getId());
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), baseEmployee.getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
		if (StringUtil.isBlank(bean.getEmp_code()) || checkRepeatEmpCode(bean.getId(), bean.getEmp_code()))
			return ResultConstants.REPEAT;
		bean.setLast_update_user(user.getRealname());
		bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		if(bean.getEnable_flag()!=null && "1".equals(bean.getEnable_flag())) {
			ret = baseEmployeeMapper.updateByPrimaryKeySelective(bean);
		}else if(bean.getEnable_flag()!=null && bean.getEnable_flag().equals("0")){
			ret = baseEmployeeMapper.updateByPrimaryKeySelective(bean);
		}else{
			baseEmployee.setLast_update_user(user.getRealname());
			baseEmployee.setLast_update_timestamp(DateUtil.getNowTimestamp());
			baseEmployee.setComp_id(bean.getComp_id());
			baseEmployee.setStat_id(bean.getStat_id());
			baseEmployee.setArea_id(bean.getArea_id());
			baseEmployee.setGroup_id(bean.getGroup_id());
			baseEmployee.setEmp_name(bean.getEmp_name());
			baseEmployee.setEmp_sex(bean.getEmp_sex());
			baseEmployee.setId_number(bean.getId_number());
			baseEmployee.setBorn_date(bean.getBorn_date());
			baseEmployee.setPda_position(bean.getPda_position());
			baseEmployee.setWork_emp_level(bean.getWork_emp_level());
			baseEmployee.setEmp_tel(bean.getEmp_tel());
			baseEmployee.setEntry_date(bean.getEntry_date());
			baseEmployee.setDeparture_date(bean.getDeparture_date());
			baseEmployee.setDeparture_reason(bean.getDeparture_reason());
			baseEmployee.setOther_brand_skill(bean.getOther_brand_skill());
			baseEmployee.setEmp_remark(bean.getEmp_remark());
			baseEmployee.setDispatch_send_first(bean.getDispatch_send_first());
			baseEmployee.setDispatch_send_second(bean.getDispatch_send_second());
			baseEmployee.setDispatch_send_third(bean.getDispatch_send_third());
			baseEmployee.setType_casual_check(bean.getType_casual_check());
			baseEmployee.setType_hotline(bean.getType_hotline());
			baseEmployee.setType_mt(bean.getType_mt());
			baseEmployee.setType_regular_check(bean.getType_regular_check());
			baseEmployee.setType_rota_engineer(bean.getType_rota_engineer());
			baseEmployee.setHotline_manager(bean.getHotline_manager());
			baseEmployee.setPosition_description(bean.getPosition_description());
			baseEmployee.setHr_comp_name(bean.getHr_comp_name());
			ret = baseEmployeeMapper.updateByPrimaryKey(baseEmployee);
		}
		if (ret == 0) {
			return ResultConstants.ERROR;
		}else {
			if("0".equals(bean.getEnable_flag()) && StringUtil.isNotBlank(bean.getEmp_code())) {
				SysUser sysUser = new SysUser();
				sysUser.setEmp_code(bean.getEmp_code());
				List<SysUser> list = sysUserMapper.selectBySqlConditions(sysUser);
				for(SysUser s : list) {
					sysUserMapper.updateByPrimaryKeySelective(s);
				}
			}
		}
		return ResultConstants.SUCCESS;
	}

	@Override
	public Map<String, Object> empWorkExperienceListPage(BaseEmployeeSearchBean queryInfo) {
		if(queryInfo.getEmp_code() != null) {
			BaseEmpWorkExperience baseEmpWorkExperience = new BaseEmpWorkExperience();
			baseEmpWorkExperience.setEmp_code(queryInfo.getEmp_code());
			baseEmpWorkExperience.setEnable_flag("1");
			Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
					.doSelectPage(() -> baseEmpWorkExperienceMapper.selectBySqlConditions(baseEmpWorkExperience));
			return PageHandler.wrapPageMap(pageResult);
		}else {
			return null;
		}
	}

	@Override
	public Map<String, Object> empTrainingListPage(BaseEmployeeSearchBean queryInfo) {
		if(queryInfo.getEmp_code() != null) {
			BaseTraining baseTraining = new BaseTraining();
			baseTraining.setEmp_code(queryInfo.getEmp_code());
			baseTraining.setEnable_flag("1");
			Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo, "train_begin_date desc")
					.doSelectPage(() -> baseTrainingMapper.selectBySqlConditions(baseTraining));
			return PageHandler.wrapPageMap(pageResult);
		}else {
			return null;
		}
	}
	
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String doSaveWorkExperience(BaseEmpWorkExperience bean, UserBean user) {
		int ret = 0;
		bean.setCreate_user(user.getRealname());
		bean.setCreate_timestamp(DateUtil.getNowTimestamp());
		bean.setSource_id(pubService.getSequenceOrder());
		bean.setSync_datetime(DateUtil.getNowTimestamp());
		ret = baseEmpWorkExperienceMapper.insertSelective(bean);
		if (ret == 0)
			return ResultConstants.ERROR;
		return ResultConstants.SUCCESS;
	}
	
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String updateWorkExperience(BaseEmpWorkExperience bean, UserBean user) {
		int ret = 0;
		BaseEmpWorkExperience baseEmpWorkExperience = baseEmpWorkExperienceMapper.selectByPrimaryKey(bean.getId());
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), baseEmpWorkExperience.getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
		bean.setLast_update_user(user.getRealname());
		bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		ret = baseEmpWorkExperienceMapper.updateByPrimaryKeySelective(bean);
		if (ret == 0)
			return ResultConstants.ERROR;
		return ResultConstants.SUCCESS;
	}
	
	private Boolean checkRepeatTrain(Integer id, String emp_code, String train_year, String train_name) {
		BaseTraining bean = new BaseTraining();
		bean.setTrain_year(train_year);
		bean.setTrain_name(train_name);
		bean.setEmp_code(emp_code);
		List<BaseTraining> list = baseTrainingMapper.selectBySqlConditions(bean);
		if( id == null )
			return list.size()>0;
		for(BaseTraining baseTraining : list) {
			if( !baseTraining.getId().equals(id) )
				return true;
		}
		return false;
	}
	
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String doSaveTraining(BaseTraining bean, UserBean user) {
		int ret = 0;
		if (StringUtil.isBlank(bean.getTrain_year()) || StringUtil.isBlank(bean.getTrain_name()) ||
				 checkRepeatTrain(null, bean.getEmp_code(), bean.getTrain_year(), bean.getTrain_name()))
			return ResultConstants.REPEAT;
		bean.setCreate_user(user.getRealname());
		bean.setCreate_timestamp(DateUtil.getNowTimestamp());
		bean.setSource_id(pubService.getSequenceOrder());
		bean.setSync_datetime(DateUtil.getNowTimestamp());
		ret = baseTrainingMapper.insertSelective(bean);
		if (ret == 0)
			return ResultConstants.ERROR;
		return ResultConstants.SUCCESS;
	}
	
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String updateTraining(BaseTraining bean, UserBean user) {
		int ret = 0;
		BaseTraining baseTraining = baseTrainingMapper.selectByPrimaryKey(bean.getId());
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), baseTraining.getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
		bean.setLast_update_user(user.getRealname());
		bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		ret = baseTrainingMapper.updateByPrimaryKeySelective(bean);
		if (ret == 0)
			return ResultConstants.ERROR;
		return ResultConstants.SUCCESS;
	}
	@Override
	public ResultBean export(UserBean user, BaseEmployeeSearchBean queryInfo) {
		if (WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getEmp_type()))
			queryInfo.setEmp_type(null);
		if (WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getComp_id()))
			queryInfo.setComp_id(null);
		if (WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getStat_id()))
			queryInfo.setStat_id(null);
		if (WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getPda_position()))
			queryInfo.setPda_position(null);

		return EasyExcelUtil.writeExcelMoreSheetMoreWrite(() -> PageHelper.count(() -> baseEmployeeManagerMapper.getReportList(queryInfo)),
				() -> baseEmployeeManagerMapper.getReportList(queryInfo), queryInfo);
	}
	
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String doSaveCertificate(BaseEmpCertificate bean, UserBean user) {
		int ret = 0;
		bean.setCreate_user(user.getRealname());
		bean.setCreate_timestamp(DateUtil.getNowTimestamp());
		bean.setSource_id(pubService.getSequenceOrder());
		bean.setSync_datetime(DateUtil.getNowTimestamp());
		ret = baseEmpCertificateMapper.insertSelective(bean);
		if (ret == 0)
			return ResultConstants.ERROR;
		return ResultConstants.SUCCESS;
	}
	
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String updateCertificate(BaseEmpCertificate bean, UserBean user) {
		int ret = 0;
		BaseEmpCertificate caseEmpCertificate = baseEmpCertificateMapper.selectByPrimaryKey(bean.getId());
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), caseEmpCertificate.getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
		bean.setLast_update_user(user.getRealname());
		bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		ret = baseEmpCertificateMapper.updateByPrimaryKeySelective(bean);
		if (ret == 0)
			return ResultConstants.ERROR;
		return ResultConstants.SUCCESS;
	}
	
	@Override
	public Map<String, Object> empCertificateListPage(BaseEmployeeSearchBean queryInfo) {
		if(queryInfo.getEmp_code() != null) {
			BaseEmpCertificate baseEmpCertificate = new BaseEmpCertificate();
			baseEmpCertificate.setEmp_code(queryInfo.getEmp_code());
			baseEmpCertificate.setEnable_flag("1");
			Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo, "certificate_date desc")
					.doSelectPage(() -> baseEmpCertificateMapper.selectBySqlConditions(baseEmpCertificate));
			return PageHandler.wrapPageMap(pageResult);
		}else {
			return null;
		}
	}
	@Override
	public String initEmpTypeSeq(UserBean user) {
		return baseEmployeeManagerMapper.initEmpTypeSeq();
	}
	
	@SuppressWarnings("resource")
	@Override
	public Map<String, Object> regularCheckEmpPageList(BaseEmployeeSearchBean queryInfo) {
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getEmp_type()))
			queryInfo.setEmp_type(null);
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getStat_id()))
			queryInfo.setStat_id(null);
		//根据select_emp_type判断要选择的是定检人员列表还是配合人员列表
		String selectEmpType = queryInfo.getSelect_emp_type();
		Page<Map<String, Object>> pageResult = new Page<Map<String, Object>>();
		if("1".equals(selectEmpType)) {
			pageResult = PageHandler.buildPage(queryInfo)
					.doSelectPage(() -> baseEmployeeManagerMapper.regularCheckEmpPageList(queryInfo));
		}else {
			pageResult = PageHandler.buildPage(queryInfo)
					.doSelectPage(() -> baseEmployeeManagerMapper.coordinatorPageList(queryInfo));
		}

		return PageHandler.wrapPageMap(pageResult);
	}
	
	@Override
	public List<Map<String, Object>> empAreaList(BaseEmployeeSearchBean queryInfo, String combobox_type) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(combobox_type)) {
			map.put("emp_code", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_ALL));
			map.put("emp_name", SysConstants.SYSTEM_SELECT_TEXT_ALL);
			map.put("emp_name_type_level", SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(map);
		}else if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(combobox_type)){
			map.put("emp_code", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_SELECT));
			map.put("emp_name", SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			map.put("emp_name_type_level", SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(map);
		}
		list.addAll(baseEmployeeManagerMapper.empList(queryInfo));
		return list;
	}
	
	@Override
	public Map<String, Object> gyrusEmpListPage(BaseEmployeeSearchBean queryInfo, UserBean user) {
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getEmp_type()))
			queryInfo.setEmp_type(null);
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getComp_id()))
			queryInfo.setComp_id(null);
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getStat_id()))
			queryInfo.setStat_id(null);
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getArea_id()))
			queryInfo.setArea_id(null);
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getPda_position()))
			queryInfo.setPda_position(null);
		if(StringUtil.isNotBlank(queryInfo.getComp_or_region()))
			queryInfo.setCompIdList(null);
/**
 * 查询方式变来变去，不知是否会再变动，留此代码，以备后用。
 */		
/*		//判断是否为系统管理员
		if("admin".equals(user.getUserCode())) {
			queryInfo.setLogin_user_comp_id(null);
		}else {
			//获取当前登录人所属司、所属站及所属片
			BaseEmployee bean = baseEmployeeManagerMapper.getSysUserInfoById(user.getUserId());
			if(null != bean) {
				String loginUserCompId = StringUtil.toSafeString(bean.getComp_id());
				String loginUserStatId = StringUtil.toSafeString(bean.getStat_id());
				String loginUserAreaId = StringUtil.toSafeString(bean.getArea_id());
				if(StringUtil.isNotBlank(loginUserAreaId)) {
					queryInfo.setLogin_user_comp_id(null);
					queryInfo.setLogin_user_stat_id(null);
					queryInfo.setLogin_user_area_id(loginUserAreaId);
				}else if(StringUtil.isNotBlank(loginUserStatId)){
					queryInfo.setLogin_user_comp_id(null);
					queryInfo.setLogin_user_stat_id(loginUserStatId);
					queryInfo.setLogin_user_area_id(null);
				}else if(StringUtil.isNotBlank(loginUserCompId)) {
					String compType = "";
					//查询机构表，判断comp_type类型 1:工程总部 2:区域 3:分公司
					BaseCompany compBean = baseCompanyMapper.selectByPrimaryKey(Integer.valueOf(loginUserCompId));
					if(null != compBean) {
						compType = compBean.getComp_type();
						if("3".equals(compType)) {
							queryInfo.setLogin_user_comp_id(loginUserCompId);
						}else if("2".equals(compType)) {
							List<Integer> compIdList = getAreaCompIdList(loginUserCompId);
							queryInfo.setLogin_user_comp_id(null);
							queryInfo.setCompIdsList(compIdList);
						}else if("1".equals(compType)) {
							queryInfo.setLogin_user_comp_id(null);
						}else {
							throw new RuntimeException("error");
						}
					}
					queryInfo.setLogin_user_stat_id(null);
					queryInfo.setLogin_user_area_id(null);
				}else {
					queryInfo.setLogin_user_comp_id(null);
					queryInfo.setLogin_user_stat_id(null);
					queryInfo.setLogin_user_area_id(null);
				}			
			}			
		}*/
		
		MtContractDtl mdlBean = new MtContractDtl();
		mdlBean.setAsset_id(Integer.valueOf(StringUtil.toSafeString(queryInfo.getSelected_asset_id())));
		mdlBean.setContract_code(queryInfo.getSelected_contract_code());
		mdlBean.setEnable_flag("1");
		List<MtContractDtl> mdlList = mtContractDtlMapper.selectBySqlConditions(mdlBean);
		if(CollectionUtils.isNotEmpty(mdlList)) {
			MtContractDtl retBean = mdlList.get(0);
			if(null != retBean) {
				String compId = StringUtil.toSafeString(retBean.getComp_id());
				String statId = StringUtil.toSafeString(retBean.getStat_id());
				String areaId = StringUtil.toSafeString(retBean.getArea_id());
				if(StringUtil.isNotBlank(areaId)) {
					queryInfo.setLogin_user_comp_id(null);
					queryInfo.setLogin_user_stat_id(null);
					queryInfo.setLogin_user_area_id(areaId);
				}else if(StringUtil.isNotBlank(statId)){
					queryInfo.setLogin_user_comp_id(null);
					queryInfo.setLogin_user_stat_id(statId);
					queryInfo.setLogin_user_area_id(null);
				}else if(StringUtil.isNotBlank(compId)) {
					String compType = "";
					//查询机构表，判断comp_type类型 1:工程总部 2:区域 3:分公司
					BaseCompany compBean = baseCompanyMapper.selectByPrimaryKey(Integer.valueOf(compId));
					if(null != compBean) {
						compType = compBean.getComp_type();
						if("3".equals(compType)) {
							queryInfo.setLogin_user_comp_id(compId);
						}else if("2".equals(compType)) {
							List<Integer> compIdList = getAreaCompIdList(compId);
							queryInfo.setLogin_user_comp_id(null);
							queryInfo.setCompIdsList(compIdList);
						}else if("1".equals(compType)) {
							queryInfo.setLogin_user_comp_id(null);
						}else {
							throw new RuntimeException("error");
						}
					}
					queryInfo.setLogin_user_stat_id(null);
					queryInfo.setLogin_user_area_id(null);
				}else {
					queryInfo.setLogin_user_comp_id(null);
					queryInfo.setLogin_user_stat_id(null);
					queryInfo.setLogin_user_area_id(null);
				}			
			}			
		}else {
			queryInfo.setLogin_user_comp_id(null);
			queryInfo.setLogin_user_stat_id(null);
			queryInfo.setLogin_user_area_id(null);
		}
		
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() -> baseEmployeeManagerMapper.gyrusCheckEmpPageList(queryInfo));
		return PageHandler.wrapPageMap(pageResult);
	}
	
	@Override
	public BaseEmployee fetchUserEmpInfo(UserBean user) {
		BaseEmployee baseEmployee = new BaseEmployee();
		baseEmployee.setEmp_code(user.getEmpCode());
		List<BaseEmployee> list = baseEmployeeMapper.selectBySqlConditions(baseEmployee);
		if(list.size() > 0 ) {
			return list.get(0);
		}else {
			return baseEmployee;
		}
	}
	
	@Override
	public List<Map<String, Object>> gyrusEmpList(BaseEmployeeSearchBean queryInfo, UserBean user, String combobox_type) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(combobox_type)) {
			map.put("emp_code", SysConstants.SYSTEM_SELECT_VALUE_ALL);
			map.put("emp_name", SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(map);
		}else if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(combobox_type)){
			map.put("emp_code", SysConstants.SYSTEM_SELECT_VALUE_SELECT);
			map.put("emp_name", SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(map);
		}
		
		//获取当前登录人所属司、所属站及所属片
		BaseEmployee bean = baseEmployeeManagerMapper.getSysUserInfoById(user.getUserId());
		if(null != bean) {
			String loginUserCompId = StringUtil.toSafeString(bean.getComp_id());
			String loginUserStatId = StringUtil.toSafeString(bean.getStat_id());
			String loginUserAreaId = StringUtil.toSafeString(bean.getArea_id());
			if(StringUtil.isNotBlank(loginUserAreaId)) {
				queryInfo.setLogin_user_comp_id(null);
				queryInfo.setLogin_user_stat_id(null);
				queryInfo.setLogin_user_area_id(loginUserAreaId);
			}else if(StringUtil.isNotBlank(loginUserStatId)){
				queryInfo.setLogin_user_comp_id(null);
				queryInfo.setLogin_user_stat_id(loginUserStatId);
				queryInfo.setLogin_user_area_id(null);
			}else if(StringUtil.isNotBlank(loginUserCompId)) {
				queryInfo.setLogin_user_comp_id(loginUserCompId);
				queryInfo.setLogin_user_stat_id(null);
				queryInfo.setLogin_user_area_id(null);
			}else {
				queryInfo.setLogin_user_comp_id(null);
				queryInfo.setLogin_user_stat_id(null);
				queryInfo.setLogin_user_area_id(null);
			}
			
			list.addAll(baseEmployeeManagerMapper.gyrusEmpList(queryInfo));
		}
		
		return list;
	}
	
	
	private List<Integer> getAreaCompIdList(String compId){		
		List<Integer> compIdList = baseCompanyManagerMapper.fetchAllPCompIdsByAreaId(Integer.valueOf(StringUtil.toSafeString(compId)));
		//最后放入区域id
		compIdList.add(Integer.valueOf(StringUtil.toSafeString(compId)));
		
		return compIdList;
	}
	
}
