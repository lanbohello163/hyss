package com.zxtech.ess.module.base.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zxtech.ess.app.util.SysConstants;
import com.zxtech.ess.module.base.bean.BaseStationSearchBean;
import com.zxtech.ess.module.base.mapper.BaseStationManagerMapper;
import com.zxtech.ess.module.base.service.IBaseStationService;
import com.zxtech.ess.module.gen.bean.BaseArea;
import com.zxtech.ess.module.gen.bean.BaseEmployee;
import com.zxtech.ess.module.gen.bean.BaseGroup;
import com.zxtech.ess.module.gen.bean.BaseStation;
import com.zxtech.ess.module.gen.mapper.BaseAreaMapper;
import com.zxtech.ess.module.gen.mapper.BaseEmployeeMapper;
import com.zxtech.ess.module.gen.mapper.BaseGroupMapper;
import com.zxtech.ess.module.gen.mapper.BaseStationMapper;
import com.zxtech.ess.module.pub.service.IPubService;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.constant.WebConstants;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.StringUtil;
import com.zxtech.platform.utils.excel.EasyExcelUtil;
import com.zxtech.platform.utils.page.PageHandler;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

@Service("baseStationServiceImpl")
public class BaseStationServiceImpl implements IBaseStationService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseStationServiceImpl.class);
	
	@Autowired
	private BaseStationManagerMapper baseStationManagerMapper;
	@Autowired
	private BaseStationMapper baseStationMapper;
	@Autowired
	private BaseAreaMapper baseAreaMapper;
	@Autowired
	private BaseEmployeeMapper baseEmployeeMapper;
	@Autowired
	private IPubService pubService;
	@Autowired
	private BaseGroupMapper baseGroupMapper;
	
	@Override
	public Map<String, Object> baseStationPagingList(BaseStationSearchBean bean, UserBean user) {
		if(WebConstants.SELECT_VALUE_ALL.equals(String.valueOf(bean.getComp_id())))
			bean.setComp_id(null);
		if(WebConstants.SELECT_VALUE_ALL.equals(String.valueOf(bean.getStat_type())))
			bean.setStat_type(null);
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(bean)
				.doSelectPage(() -> baseStationManagerMapper.baseStationPagingList(bean));
		return PageHandler.wrapPageMap(pageResult);
	}

	@Override
	public String save(BaseStation bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		if(StringUtil.isNotBlank(bean.getStat_name()) && checkRepeatColumn(bean)) {
			return ResultConstants.REPEAT_ITEM1;
		}
		bean.setCreate_user(user.getRealname());
		bean.setCreate_timestamp(DateUtil.getNowTimestamp());
		bean.setSource_id(pubService.getSequenceOrder());
		bean.setSync_datetime(DateUtil.getNowTimestamp());
		ret = baseStationMapper.insertSelective(bean);
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	public String update(BaseStation bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		BaseStation dbBean = baseStationMapper.selectByPrimaryKey(bean.getId());
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
				baseStationMapper.selectByPrimaryKey(bean.getId()).getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
		if(StringUtil.isNotBlank(bean.getStat_name()) && checkRepeatColumn(bean)) {
			return ResultConstants.REPEAT_ITEM1;
		}
		dbBean.setComp_id(bean.getComp_id());
		dbBean.setDept_id(bean.getDept_id());
		dbBean.setStat_name(bean.getStat_name());
		dbBean.setStat_manager(bean.getStat_manager());
		dbBean.setStat_type(bean.getStat_type());
		dbBean.setMgr_area(bean.getMgr_area());
		dbBean.setStat_address(bean.getStat_address());
		dbBean.setStat_state(bean.getStat_state());
		dbBean.setStat_province(bean.getStat_province());
		dbBean.setStat_city(bean.getStat_city());
		dbBean.setStat_county(bean.getStat_county());
		dbBean.setLicensed_contractor_name(bean.getLicensed_contractor_name());
		dbBean.setOffice_fax(bean.getOffice_fax());
		dbBean.setOffice_tel(bean.getOffice_tel());
		dbBean.setOffice_zip_code(bean.getOffice_zip_code());
		dbBean.setPractices(bean.getPractices());
		dbBean.setVisit_director(bean.getVisit_director());
		dbBean.setIs_use_pda(bean.getIs_use_pda());
		dbBean.setErp_stat_id(bean.getErp_stat_id());
		dbBean.setLast_update_user(user.getRealname());
		dbBean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		dbBean.setRegion_state_name(bean.getRegion_state_name());
		dbBean.setRegion_province_name(bean.getRegion_province_name());
		dbBean.setRegion_city_name(bean.getRegion_city_name());
		dbBean.setRegion_district_name(bean.getRegion_district_name());
		ret = baseStationMapper.updateByPrimaryKey(dbBean);
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	public String disable(BaseStation bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		try {
			//查询数据库中时间戳和前台传入的时间戳来验证是否是同一条数据
			if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
					baseStationMapper.selectByPrimaryKey(bean.getId()).getLast_update_timestamp()))
				return ResultConstants.DATA_CHANGES;
			
			BaseArea baseAreaBean = new BaseArea();
			baseAreaBean.setStat_id(bean.getId());
			baseAreaBean.setEnable_flag("1");
			List<BaseArea> baseAreaList = baseAreaMapper.selectBySqlConditions(baseAreaBean);
			if(!CollectionUtils.isEmpty(baseAreaList))
				return ResultConstants.REPEAT_ITEM1;
			
			BaseEmployee baseEmpBean = new BaseEmployee();
			baseEmpBean.setStat_id(bean.getId());
			baseEmpBean.setEnable_flag("1");
			List<BaseEmployee> baseEmpList = baseEmployeeMapper.selectBySqlConditions(baseEmpBean);
			if(!CollectionUtils.isEmpty(baseEmpList))
				return ResultConstants.REPEAT_ITEM1;
			
			bean.setEnable_flag("0");
			bean.setLast_update_user(user.getRealname());
			bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
			ret = baseStationMapper.updateByPrimaryKeySelective(bean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(ResultConstants.ERROR);
		}
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String able(BaseStation bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		try {
			if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
					baseStationMapper.selectByPrimaryKey(bean.getId()).getLast_update_timestamp()))
				return ResultConstants.DATA_CHANGES;
			bean.setEnable_flag("1");
			bean.setLast_update_user(user.getRealname());
			bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
			ret = baseStationMapper.updateByPrimaryKeySelective(bean);
			
			BaseArea baseAreaBean = new BaseArea();
			baseAreaBean.setEnable_flag("0");
			baseAreaBean.setStat_id(bean.getId());
			List<BaseArea> baseAreaList = baseAreaMapper.selectBySqlConditions(baseAreaBean);
			for (BaseArea baseArea : baseAreaList) {
				BaseArea areaBean = new BaseArea();
				areaBean.setEnable_flag("1");
				areaBean.setId(baseArea.getId());
				areaBean.setLast_update_user(user.getRealname());
				areaBean.setLast_update_timestamp(DateUtil.getNowTimestamp());
				ret = baseAreaMapper.updateByPrimaryKeySelective(areaBean);
			}
			BaseGroup baseGroupBean = new BaseGroup();
			baseGroupBean.setEnable_flag("0");
			baseGroupBean.setStat_id(bean.getId());
		    List<BaseGroup> baseGroupList = baseGroupMapper.selectBySqlConditions(baseGroupBean);
			for (BaseGroup baseGroup : baseGroupList) {
				BaseGroup groupBean = new BaseGroup();
				groupBean.setEnable_flag("1");
				groupBean.setId(baseGroup.getId());
				groupBean.setLast_update_user(user.getRealname());
				groupBean.setLast_update_timestamp(DateUtil.getNowTimestamp());
				ret = baseGroupMapper.updateByPrimaryKeySelective(groupBean);
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
	public ResultBean export(BaseStationSearchBean bean, HttpServletRequest request, HttpServletResponse response, UserBean user) {
		if(WebConstants.SELECT_VALUE_ALL.equals(String.valueOf(bean.getComp_id())))
			bean.setComp_id(null);
		if(WebConstants.SELECT_VALUE_ALL.equals(String.valueOf(bean.getStat_type())))
			bean.setStat_type(null);
		return EasyExcelUtil.writeExcelMoreSheetMoreWrite(() -> PageHelper.count(() -> baseStationManagerMapper.getReportList(bean)),
				() -> baseStationManagerMapper.getReportList(bean), bean);
	}
	private Boolean checkRepeatColumn(BaseStation bean){
		BaseStation record = new BaseStation();
		record.setStat_name(bean.getStat_name());
		record.setComp_id(bean.getComp_id());
		List<BaseStation> list = baseStationMapper.selectBySqlConditions(record);
		if(null == bean.getId()) {
			if(list.size() == 0) {
				return false;
			}
		} else {
			if (list.stream().filter(javaBean ->!bean.getId().equals(javaBean.getId())).count()==0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<Map<String, Object>> statList(BaseStationSearchBean queryInfo, String combobox_type, UserBean user) {
		queryInfo.setCompIdList(user.getCompIdList());
		queryInfo.setStatIdList(user.getStatIdList());
		queryInfo.setAreaIdList(user.getAreaIdList());
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(combobox_type)) {
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_ALL));
			map.put("stat_name", SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(map);
		}else if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(combobox_type)){
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_SELECT));
			map.put("stat_name", SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(map);
		}
		list.addAll(baseStationManagerMapper.statList(queryInfo));
		return list;
	}

	@Override
	public List<Map<String, Object>> statPartList(BaseStationSearchBean queryInfo, String combobox_type,
			UserBean user) {
		queryInfo.setCompIdList(user.getCompIdList());
		queryInfo.setStatIdList(user.getStatIdList());
		queryInfo.setAreaIdList(user.getAreaIdList());
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(combobox_type)) {
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_ALL));
			map.put("stat_name", SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(map);
		}else if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(combobox_type)){
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_SELECT));
			map.put("stat_name", SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(map);
		}
		list.addAll(baseStationManagerMapper.statPartList(queryInfo));
		return list;
	}

	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String doForbidden(BaseStation bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		try {
			if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
					baseStationMapper.selectByPrimaryKey(bean.getId()).getLast_update_timestamp()))
				return ResultConstants.DATA_CHANGES;
			bean.setEnable_flag("0");
			bean.setLast_update_user(user.getRealname());
			bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
			ret = baseStationMapper.updateByPrimaryKeySelective(bean);
			
			BaseArea baseAreaBean = new BaseArea();
			baseAreaBean.setEnable_flag("1");
			baseAreaBean.setStat_id(bean.getId());
			List<BaseArea> baseAreaList = baseAreaMapper.selectBySqlConditions(baseAreaBean);
			for (BaseArea baseArea : baseAreaList) {
				BaseArea areaBean = new BaseArea();
				areaBean.setEnable_flag("0");
				areaBean.setId(baseArea.getId());
				areaBean.setLast_update_user(user.getRealname());
				areaBean.setLast_update_timestamp(DateUtil.getNowTimestamp());
				ret = baseAreaMapper.updateByPrimaryKeySelective(areaBean);
			}
			BaseGroup baseGroupBean = new BaseGroup();
			baseGroupBean.setEnable_flag("1");
			baseGroupBean.setStat_id(bean.getId());
		    List<BaseGroup> baseGroupList = baseGroupMapper.selectBySqlConditions(baseGroupBean);
			for (BaseGroup baseGroup : baseGroupList) {
				BaseGroup groupBean = new BaseGroup();
				groupBean.setEnable_flag("0");
				groupBean.setId(baseGroup.getId());
				groupBean.setLast_update_user(user.getRealname());
				groupBean.setLast_update_timestamp(DateUtil.getNowTimestamp());
				ret = baseGroupMapper.updateByPrimaryKeySelective(groupBean);
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
	public List<Map<String, Object>> fetchWindowStatList(BaseStationSearchBean queryInfo, UserBean user) {
		queryInfo.setCompIdList(user.getCompIdList());
		queryInfo.setStatIdList(user.getStatIdList());
		queryInfo.setAreaIdList(user.getAreaIdList());
		return baseStationManagerMapper.fetchWindowStatList(queryInfo);
	}
	@Override
	public Map<String, Object> statSyncByErpListPage(BaseStationSearchBean queryInfo,UserBean user) {
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() -> baseStationManagerMapper.statSyncByErpListPage(queryInfo));
		return PageHandler.wrapPageMap(pageResult);
	}
}
