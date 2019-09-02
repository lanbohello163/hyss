package com.zxtech.ess.module.base.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zxtech.ess.app.util.SysConstants;
import com.zxtech.ess.module.base.bean.BaseDeptSearchBean;
import com.zxtech.ess.module.base.mapper.BaseDeptManagerMapper;
import com.zxtech.ess.module.base.service.IBaseDeptService;
import com.zxtech.ess.module.gen.bean.BaseDept;
import com.zxtech.ess.module.gen.bean.BaseStation;
import com.zxtech.ess.module.gen.mapper.BaseDeptMapper;
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

@Service
public class BaseDeptServiceImpl implements IBaseDeptService {
	
	@Autowired
	private BaseDeptMapper baseDeptMapper;
	@Autowired
	private BaseDeptManagerMapper baseDeptManagerMapper;
	@Autowired
	private BaseStationMapper baseStationMapper;
	@Autowired
	private IPubService pubService;
	
	@Override
	public Map<String, Object> getListWithPaging(BaseDeptSearchBean bean) {
		if(WebConstants.SELECT_VALUE_ALL.equals(String.valueOf(bean.getComp_id()))) {
			bean.setComp_id(null);
		}
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(bean)
				.doSelectPage(() -> baseDeptManagerMapper.getListWithPaging(bean));
		return PageHandler.wrapPageMap(pageResult);
	}

	@Override
	@Transactional(rollbackFor = { RuntimeException.class, SQLException.class })
	public String doAdd(BaseDept bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		// check repeat, role_name
		if (StringUtil.isNotBlank(bean.getDept_name()) && checkRepeatColumn(bean)) {
			return ResultConstants.REPEAT_ITEM1;
		}
		// insert record
		bean.setCreate_user(user.getRealname());
		bean.setCreate_timestamp(DateUtil.getNowTimestamp());
		bean.setSource_id(pubService.getSequenceOrder());
		bean.setSync_datetime(DateUtil.getNowTimestamp());
		int ret = baseDeptMapper.insertSelective(bean);
		if(bean.getP_dept_id() != null) {
			updateDeptSub(bean.getP_dept_id(), user);
		}
		
		if(ret == 0) {
			resStr = ResultConstants.ERROR;
		}
		return resStr;
	}

	@Override
	@Transactional(rollbackFor = { RuntimeException.class, SQLException.class })
	public String doUpdate(BaseDept bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		BaseDept dbBean = baseDeptMapper.selectByPrimaryKey(bean.getId());
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
				dbBean.getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
		if(StringUtil.isNotBlank(bean.getDept_name()) && checkRepeatColumn(bean)) {
			return ResultConstants.REPEAT_ITEM1;
		}
		//上级部门历史id
		Integer dbParDept = dbBean.getP_dept_id();
		dbBean.setComp_id(bean.getComp_id());
		dbBean.setP_dept_id(bean.getP_dept_id());
		dbBean.setDept_name(bean.getDept_name());
		dbBean.setDept_type(bean.getDept_type());
		dbBean.setDept_state(bean.getDept_state());
		dbBean.setDept_province(bean.getDept_province());
		dbBean.setDept_city(bean.getDept_city());
		dbBean.setDept_district(bean.getDept_district());
		dbBean.setDept_manager(bean.getDept_manager());
		dbBean.setErp_dept_name(bean.getErp_dept_name());
		dbBean.setHr_dept_name(bean.getHr_dept_name());
		dbBean.setDept_tel(bean.getDept_tel());
		dbBean.setDept_fax(bean.getDept_fax());
		dbBean.setIs_use_pda(bean.getIs_use_pda());
		dbBean.setDept_remark(bean.getDept_remark());
		dbBean.setLast_update_user(user.getRealname());
		dbBean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		dbBean.setRegion_state_name(bean.getRegion_state_name());
		dbBean.setRegion_province_name(bean.getRegion_province_name());
		dbBean.setRegion_city_name(bean.getRegion_city_name());
		dbBean.setRegion_district_name(bean.getRegion_district_name());
		ret = baseDeptMapper.updateByPrimaryKey(dbBean);
		if(dbParDept != bean.getP_dept_id()) {
			if(null != dbParDept) {
				updateDeptSub(dbParDept, user);
			}
			if(null != bean.getP_dept_id()) {
				updateDeptSub(bean.getP_dept_id(), user);
			}
		} 
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}
	
	private void updateDeptSub(int p_dept_id, UserBean user) {
		BaseDept pBean = new BaseDept();
		pBean.setP_dept_id(p_dept_id);
		pBean.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
		List<BaseDept> childLis = baseDeptMapper.selectBySqlConditions(pBean);
		String deptSub = "";
		if(null != childLis && childLis.size() > 0) {
			for(BaseDept mbean: childLis) {
				deptSub += mbean.getDept_name() + ",";
			}
			deptSub = deptSub.substring(0, deptSub.length()-1);
		}
		BaseDept updateBean = new BaseDept();
		updateBean.setId(p_dept_id);
		updateBean.setDept_sub(deptSub);
		updateBean.setLast_update_user(user.getRealname());
		updateBean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		baseDeptMapper.updateByPrimaryKeySelective(updateBean);

	}

	@Override
	public String disable(BaseDept bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		BaseDept dbBean = baseDeptMapper.selectByPrimaryKey(bean.getId());
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
				dbBean.getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
		
		BaseStation basestatBean = new BaseStation();
		basestatBean.setDept_id(bean.getId());
		basestatBean.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
		List<BaseStation> baseStatList =  baseStationMapper.selectBySqlConditions(basestatBean);
		if(!CollectionUtils.isEmpty(baseStatList))
			return ResultConstants.USED;
		
		bean.setEnable_flag(SysConstants.UNENABLE_FLAG);
		bean.setLast_update_user(user.getRealname());
		bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		int ret = baseDeptMapper.updateByPrimaryKeySelective(bean);
		if(null != dbBean.getP_dept_id()) {
			updateDeptSub(dbBean.getP_dept_id(), user);
		}
		if(ret == 0) {
			resStr = ResultConstants.ERROR;
		}
		return resStr;
	}

	@Override
	public String enable(BaseDept bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		BaseDept dbBean = baseDeptMapper.selectByPrimaryKey(bean.getId());
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
				dbBean.getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
		
		bean.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
		bean.setLast_update_user(user.getRealname());
		bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		int ret = baseDeptMapper.updateByPrimaryKeySelective(bean);
		if(null != dbBean.getP_dept_id()) {
			updateDeptSub(dbBean.getP_dept_id(), user);
		}
		if(ret == 0) {
			resStr = ResultConstants.ERROR;
		}
		return resStr;
	}
	//导出
	@Override
	public ResultBean export(HttpServletRequest request, HttpServletResponse response, UserBean user,
			BaseDeptSearchBean queryInfo) {
		if (WebConstants.SELECT_VALUE_ALL.equals(String.valueOf(queryInfo.getComp_id()))) {
			queryInfo.setComp_id(null);
		}
		return EasyExcelUtil.writeExcelMoreSheetMoreWrite(() -> PageHelper.count(() -> baseDeptManagerMapper.getReportList(queryInfo)),
				() -> baseDeptManagerMapper.getReportList(queryInfo), queryInfo);
	}
	private Boolean checkRepeatColumn(BaseDept bean){
		BaseDept record = new BaseDept();
		record.setDept_name(bean.getDept_name());
		record.setComp_id(bean.getComp_id());
		List<BaseDept> list = baseDeptMapper.selectBySqlConditions(record);
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
	public List<Map<String, Object>> deptList(BaseDeptSearchBean bean, String combobox_type, UserBean user) {
		bean.setEnable_flag("1");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(combobox_type)) {
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_ALL));
			map.put("dept_name", SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(map);
		}else if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(combobox_type)){
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_SELECT));
			map.put("dept_name", SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(map);
		}
		list.addAll(baseDeptManagerMapper.getListWithPaging(bean));
		return list;
	}
}
