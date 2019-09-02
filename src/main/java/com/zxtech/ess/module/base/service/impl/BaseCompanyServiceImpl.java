package com.zxtech.ess.module.base.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zxtech.ess.app.util.SysConstants;
import com.zxtech.ess.module.base.bean.BaseCompanySearchBean;
import com.zxtech.ess.module.base.bean.BaseUserCompanySearchBean;
import com.zxtech.ess.module.base.mapper.BaseCompanyManagerMapper;
import com.zxtech.ess.module.base.service.IBaseCompanyService;
import com.zxtech.ess.module.gen.bean.BaseCompSync;
import com.zxtech.ess.module.gen.bean.BaseCompany;
import com.zxtech.ess.module.gen.bean.BaseStation;
import com.zxtech.ess.module.gen.bean.SysUserDataPermission;
import com.zxtech.ess.module.gen.mapper.BaseCompSyncMapper;
import com.zxtech.ess.module.gen.mapper.BaseCompanyMapper;
import com.zxtech.ess.module.gen.mapper.BaseStationMapper;
import com.zxtech.ess.module.gen.mapper.SysUserDataPermissionMapper;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.StringUtil;
import com.zxtech.platform.utils.excel.EasyExcelUtil;
import com.zxtech.platform.utils.page.PageHandler;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

import cn.hutool.core.collection.CollectionUtil;

@Service("companyService")
public class BaseCompanyServiceImpl implements IBaseCompanyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseCompanyServiceImpl.class);

	public final static String COMPANY_TYPE_MIX = "3";
	public final static String COMPANY_TYPE_DEPT = "4";
	public final static String COMPANY_TYPE_STATION = "5";
	public final static String COMPANY_TYPE_AREA = "6";
	public final static String COMPANY_TYPE_GROUP = "7";

	@Autowired
	private BaseCompanyMapper baseCompanyMapper;
	@Autowired
	private BaseCompanyManagerMapper baseCompanyManagerMapper;
	@Autowired
	private BaseStationMapper baseStationMapper;
	@Autowired
	private BaseCompSyncMapper baseCompSyncMapper;
	@Autowired
	private SysUserDataPermissionMapper SysUserDataPermissionMapper;

	@Override
	public List<Map<String, Object>> getCompTreeList(UserBean user) {
		Map<String, List<Map<String, Object>>> map = groupByPCompId(baseCompanyManagerMapper.getCompanyList());
		// 调用方法实现company树
		return createCompTreeTree(map);
	}

	private Map<String, List<Map<String, Object>>> groupByPCompId(List<Map<String, Object>> list) {
		return list.stream().collect(Collectors.groupingBy(
				(Function<Map<String, Object>, String>) map -> StringUtil.toSafeString(map.get("p_comp_id"))));
	}

	/**
	 * 将company封装成树
	 * 
	 * @param list
	 */
	private List<Map<String, Object>> createCompTreeTree(Map<String, List<Map<String, Object>>> resultMap) {
		List<Map<String, Object>> rootTreeList = new ArrayList<>();
		if (!resultMap.isEmpty()) {
			rootTreeList.addAll(resultMap.get(""));
			for (Map<String, Object> rootNode : rootTreeList) {
				rootNode.put("children",
						createCompTreeChildren(StringUtil.toSafeString(rootNode.get("id")), resultMap));
			}
		}
		return rootTreeList;
	}

	private List<Map<String, Object>> createCompTreeChildren(String groupKey,
			Map<String, List<Map<String, Object>>> resultMap) {
		List<Map<String, Object>> list = resultMap.get(groupKey);
		List<Map<String, Object>> childTreeList = new ArrayList<Map<String, Object>>();
		if (CollectionUtils.isNotEmpty(list)) {
			childTreeList.addAll(list);
			for (Map<String, Object> elementNode : childTreeList) {
				elementNode.put("children",
						createCompTreeChildren(StringUtil.toSafeString(elementNode.get("id")), resultMap));
			}
		}
		return childTreeList;
	}

	@Override
	@Transactional(rollbackFor = { RuntimeException.class, SQLException.class })
	public String save(BaseCompany bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		// 判断comp_code的值,不能重复
		if (StringUtil.isNotBlank(bean.getComp_code()) && checkRepeatColumnCompCode(bean)) {
			return ResultConstants.REPEAT_ITEM1;
		}
		// 判断comp_name的值,不能重复
		if (StringUtil.isNotBlank(bean.getComp_name()) && checkRepeatColumnCompName(bean)) {
			return ResultConstants.REPEAT_ITEM2;
		}
		// 判断comp_full_name的值,不能重复
		if (StringUtil.isNotBlank(bean.getComp_full_name()) && checkRepeatColumnCompFullName(bean)) {
			return ResultConstants.REPEAT_ITEM3;
		}
		bean.setCreate_user(user.getRealname());
		bean.setCreate_timestamp(DateUtil.getNowTimestamp());
		bean.setLast_update_user(user.getRealname());
		bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		bean.setSource_id(bean.getComp_code());

		setPAreaId(bean); // 设置上级区域，2019-05-03，zhaow
		ret = baseCompanyMapper.insertSelective(bean);
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	// 设置上级区域，冗余字段，有些业务会用到，避免递归查询上级区域
	private void setPAreaId(BaseCompany bean) {
		if (bean.getP_comp_id() != null) {
			Integer p_area_id = baseCompanyManagerMapper.fetchPAreaIdByCompId(bean.getP_comp_id());
			if (p_area_id != null) {
				bean.setP_area_id(p_area_id);
			}
		}
	}

	// 新增、编辑时判断机构列表是否为空
	private Boolean checkRepeatColumnCompName(BaseCompany bean) {
		BaseCompany baseCompany = new BaseCompany();
		baseCompany.setComp_name(bean.getComp_name());
		List<BaseCompany> list = baseCompanyMapper.selectBySqlConditions(baseCompany);
		if (null == bean.getId()) {
			if (list.size() == 0) {
				return false;
			}
		} else {
			if (list.stream().filter(javaBean -> !bean.getId().equals(javaBean.getId())).count() == 0) {
				return false;
			}
		}
		return true;
	}

	private Boolean checkRepeatColumnCompCode(BaseCompany bean) {
		BaseCompany baseCompany = new BaseCompany();
		baseCompany.setComp_code(bean.getComp_code());
		List<BaseCompany> list = baseCompanyMapper.selectBySqlConditions(baseCompany);
		if (null == bean.getId()) {
			if (list.size() == 0) {
				return false;
			}
		} else {
			if (list.stream().filter(javaBean -> !bean.getId().equals(javaBean.getId())).count() == 0) {
				return false;
			}
		}
		return true;
	}

	private Boolean checkRepeatColumnCompFullName(BaseCompany bean) {
		BaseCompany baseCompany = new BaseCompany();
		baseCompany.setComp_full_name(bean.getComp_full_name());
		List<BaseCompany> list = baseCompanyMapper.selectBySqlConditions(baseCompany);
		if (null == bean.getId()) {
			if (list.size() == 0) {
				return false;
			}
		} else {
			if (list.stream().filter(javaBean -> !bean.getId().equals(javaBean.getId())).count() == 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	@Transactional(rollbackFor = { RuntimeException.class, SQLException.class })
	public String update(BaseCompany bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		BaseCompany dbBean = baseCompanyMapper.selectByPrimaryKey(bean.getId());
		// 查询数据库中时间戳和前台传入的时间戳来验证是否是同一条数据
		if (!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), dbBean.getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;

		// 判断comp_code的值,不能重复
		if (StringUtil.isNotBlank(bean.getComp_code()) && checkRepeatColumnCompCode(bean)) {
			return ResultConstants.REPEAT_ITEM1;
		}
		// 判断comp_name的值,不能重复
		if (StringUtil.isNotBlank(bean.getComp_name()) && checkRepeatColumnCompName(bean)) {
			return ResultConstants.REPEAT_ITEM2;
		}
		// 判断comp_full_name的值,不能重复
		if (StringUtil.isNotBlank(bean.getComp_full_name()) && checkRepeatColumnCompFullName(bean)) {
			return ResultConstants.REPEAT_ITEM3;
		}
		dbBean.setComp_name(bean.getComp_name());
		dbBean.setComp_full_name(bean.getComp_full_name());
		dbBean.setComp_address(bean.getComp_address());
		dbBean.setComp_state(bean.getComp_state());
		dbBean.setComp_province(bean.getComp_province());
		dbBean.setComp_city(bean.getComp_city());
		dbBean.setComp_district(bean.getComp_district());
		dbBean.setComp_tel(bean.getComp_tel());
		dbBean.setErp_comp_id(bean.getErp_comp_id());
		dbBean.setP_comp_id(bean.getP_comp_id());
		dbBean.setLast_update_user(user.getRealname());
		dbBean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		setPAreaId(dbBean); // 设置上级区域，2019-05-03，zhaow
		dbBean.setRegion_state_name(bean.getRegion_state_name());
		dbBean.setRegion_province_name(bean.getRegion_province_name());
		dbBean.setRegion_city_name(bean.getRegion_city_name());
		dbBean.setRegion_district_name(bean.getRegion_district_name());
		ret = baseCompanyMapper.updateByPrimaryKey(dbBean);

		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	@Transactional(rollbackFor = { RuntimeException.class, SQLException.class })
	public String disable(BaseCompany bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		try {
			// 查询数据库中时间戳和前台传入的时间戳来验证是否是同一条数据
			if (!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(),
					baseCompanyMapper.selectByPrimaryKey(bean.getId()).getLast_update_timestamp()))
				return ResultConstants.DATA_CHANGES;
			BaseStation baseStationBean = new BaseStation();
			baseStationBean.setComp_id(bean.getId());
			baseStationBean.setEnable_flag("1");
			List<BaseStation> basStaList = baseStationMapper.selectBySqlConditions(baseStationBean);
			if (CollectionUtils.isEmpty(basStaList)) {
				bean.setEnable_flag("0");
				bean.setLast_update_user(user.getRealname());
				bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
				ret = baseCompanyMapper.updateByPrimaryKeySelective(bean);
			} else {
				return ResultConstants.USED;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(ResultConstants.ERROR);
		}
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	@Transactional(rollbackFor = { RuntimeException.class, SQLException.class })
	public String able(BaseCompany bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		try {
			// 查询数据库中时间戳和前台传入的时间戳来验证是否是同一条数据
			if (!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(),
					baseCompanyMapper.selectByPrimaryKey(bean.getId()).getLast_update_timestamp()))
				return ResultConstants.DATA_CHANGES;
			bean.setEnable_flag("1");
			bean.setLast_update_user(user.getRealname());
			bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
			ret = baseCompanyMapper.updateByPrimaryKeySelective(bean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(ResultConstants.ERROR);
		}
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	public List<Map<String, Object>> getCompChilTreeList(UserBean user, String compType, String id) {
		if (COMPANY_TYPE_MIX.equals(compType)) {
			return baseCompanyManagerMapper.getDeptStdFar(id);
		}
		if (COMPANY_TYPE_DEPT.equals(compType)) {
			return baseCompanyManagerMapper.getDeptList(id);
		}
		if (COMPANY_TYPE_STATION.equals(compType)) {
			return baseCompanyManagerMapper.getStationList(id);
		}
		if (COMPANY_TYPE_AREA.equals(compType)) {
			return baseCompanyManagerMapper.getAreaList(id);
		}
		if (COMPANY_TYPE_GROUP.equals(compType)) {
			return baseCompanyManagerMapper.getGroupList(id);
		}
		return null;
	}

	@Override
	public ResultBean export() {
		String[] nameArr = new String[] { "comp_code", "comp_name", "comp_full_name", "type", "comp_address",
				"p_comp_name", "state", "province", "city", "district", "comp_tel", "erp_sync_entity_code",
				"erp_sync_comp_name" };
		String[] showArr = new String[] { "机构编码", "名称", "全称", "机构类型", "地址", "上级机构", "国家", "省/直辖市", "城市", "区/县",
				"主要电话号码", "ERP业务实体编码", "ERP组织名称" };
		BaseCompanySearchBean queryInfo = new BaseCompanySearchBean();
		EasyExcelUtil.writeExcelTitle(nameArr, showArr, queryInfo);
		return EasyExcelUtil.writeExcelMoreSheetMoreWrite(
				() -> PageHelper.count(() -> baseCompanyManagerMapper.getReportList(queryInfo)),
				() -> baseCompanyManagerMapper.getReportList(queryInfo), queryInfo);
	}

	@Override
	public List<Map<String, Object>> compList(BaseCompanySearchBean queryInfo, String combobox_type, UserBean user) {
		queryInfo.setCompIdList(user.getCompIdList());
		queryInfo.setStatIdList(user.getStatIdList());
		queryInfo.setAreaIdList(user.getAreaIdList());
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(combobox_type)) {
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_ALL));
			map.put("comp_name", SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(map);
		} else if (SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(combobox_type)) {
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_SELECT));
			map.put("comp_name", SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(map);
		}
		list.addAll(baseCompanyManagerMapper.compList(queryInfo));
		return list;
	}

	@Override
	public List<Map<String, Object>> compListNoRight(BaseCompanySearchBean queryInfo, String combobox_type,
			UserBean user) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(combobox_type)) {
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_ALL));
			map.put("comp_name", SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(map);
		} else if (SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(combobox_type)) {
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_SELECT));
			map.put("comp_name", SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(map);
		}
		list.addAll(baseCompanyManagerMapper.compList(queryInfo));
		return list;
	}

	@Override
	public Map<String, Object> compSyncListPage(BaseCompanySearchBean queryInfo) {
		BaseCompSync baseCompSync = new BaseCompSync();
		baseCompSync.setErp_comp_code(queryInfo.getSync_comp_code());
		baseCompSync.setErp_comp_name(queryInfo.getSync_comp_name());

		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() -> baseCompSyncMapper.selectBySqlConditions(baseCompSync));
		return PageHandler.wrapPageMap(pageResult);
	}

	@Override
	public List<Map<String, Object>> userCompList(BaseUserCompanySearchBean queryInfo, String combobox_type,
			UserBean user) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(combobox_type)) {
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_ALL));
			map.put("comp_name", SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(map);
		} else if (SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(combobox_type)) {
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_SELECT));
			map.put("comp_name", SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(map);
		}

		SysUserDataPermission userDataPermission = new SysUserDataPermission();
		userDataPermission.setUser_id(user.getUserId());
		userDataPermission.setRole_id(user.getCurrentRoleId());
		List<Integer> idList = new ArrayList<Integer>();

		List<SysUserDataPermission> userDataList = SysUserDataPermissionMapper
				.selectBySqlConditions(userDataPermission);
		for (SysUserDataPermission sysUserDataPermission : userDataList) {
			idList.add(sysUserDataPermission.getData_id());
		}
		list.addAll(baseCompanyManagerMapper.getUserCompanyListByDataid(idList));

		return list;
	}

	@Override
	public List<Map<String, Object>> pareaList(BaseCompanySearchBean queryInfo, String combobox_type, UserBean user) {
		queryInfo.setCompIdList(user.getCompIdList());
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(combobox_type)) {
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_ALL));
			map.put("comp_name", SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(map);
		} else if (SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(combobox_type)) {
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_SELECT));
			map.put("comp_name", SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(map);
		}
		list.addAll(baseCompanyManagerMapper.pareaList(queryInfo));
		return list;
	}

	@Override
	public List<Map<String, Object>> areaCompList(BaseCompanySearchBean queryInfo, String combobox_type,
			UserBean user) {
		queryInfo.setCompIdList(user.getCompIdList());
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(combobox_type)) {
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_ALL));
			map.put("comp_name", SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(map);
		} else if (SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(combobox_type)) {
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_SELECT));
			map.put("comp_name", SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(map);
		}
		list.addAll(baseCompanyManagerMapper.areaCompList(queryInfo));
		return list;
	}

	@Override
	public Map<String, Object> compSyncByErpListPage(BaseCompanySearchBean queryInfo) {
		/*
		 * BaseCompSync baseCompSync = new BaseCompSync();
		 * baseCompSync.setErp_entity_code(queryInfo.getSync_entity_code());
		 * baseCompSync.setErp_comp_name(queryInfo.getSync_comp_name());
		 */

		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() -> baseCompanyManagerMapper.compSyncByErpListPage(queryInfo));
		return PageHandler.wrapPageMap(pageResult);
	}

	@Override
	public List<Map<String, Object>> getpareaidbycompid(BaseCompanySearchBean queryInfo) {
		// TODO Auto-generated method stub
		return baseCompanyManagerMapper.getpareaidbycompid(queryInfo);
	}

	@Override
	public List<Map<String, Object>> getFirstLevelCompList(BaseCompanySearchBean queryInfo, String combobox_type,
			UserBean user) {
		queryInfo.setCompIdList(user.getCompIdList());
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(combobox_type)) {
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_ALL));
			map.put("comp_name", SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(map);
		} else if (SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(combobox_type)) {
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_SELECT));
			map.put("comp_name", SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(map);
		}
		Integer id = 0;
		Integer pCompId = 0;
		String compType = "";
		Integer compId = queryInfo.getComp_id();
		BaseCompany compBean = new BaseCompany();
		compBean.setId(compId);
		compBean.setEnable_flag("1");
		List<BaseCompany> compList = baseCompanyManagerMapper.getCompInfoById(compBean);
		if(CollectionUtil.isNotEmpty(compList)) {
			BaseCompany dbBean = compList.get(0);
			id = dbBean.getId();
			compType = dbBean.getComp_type();
			pCompId = dbBean.getP_comp_id();
			
			if(id == Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_ALL) || "1".equals(compType)) {//执行分公司选择全部或者日立总部
				queryInfo.setComp_id(null);
				queryInfo.setP_comp_id(null);
			}else {
				if("2".equals(compType)) {//执行分公司选择区域
					queryInfo.setP_comp_id(null);
					queryInfo.setComp_id(id);
				}else if("3".equals(compType)){//执行分公司为所属司
					BaseCompany pCompBean = baseCompanyManagerMapper.getPCompInfoByPCompId(pCompId);
					String pCompType = pCompBean.getComp_type();
					if("2".equals(pCompType)) {//执行分公司为一级分公司时
						List<Map<String, Object>> firstSelfList = new ArrayList<Map<String, Object>>();
						Map<String, Object> firstSelfMap = new HashMap<String, Object>();
						firstSelfMap.put("id", dbBean.getId());
						firstSelfMap.put("comp_name", dbBean.getComp_name());
						firstSelfList.add(firstSelfMap);
						return firstSelfList;
					}else {
						List<Map<String, Object>> firstLevelCompList = new ArrayList<Map<String, Object>>();
						firstLevelCompList.addAll(baseCompanyManagerMapper.getItsFirstLevelCompByCompId(queryInfo));
						return firstLevelCompList;
					}
				}
			}
		}else {
			queryInfo.setComp_id(null);
		}
		
		list.addAll(baseCompanyManagerMapper.getFirstLevelCompList(queryInfo));
		return list;
	}

}