package com.zxtech.ess.module.base.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxtech.ess.app.util.SysConstants;
import com.zxtech.ess.module.base.bean.BaseEmployeeSearchBean;
import com.zxtech.ess.module.base.mapper.BaseEmployeeManagerMapper;
import com.zxtech.ess.module.base.service.IBasePersonService;
import com.zxtech.platform.constant.WebConstants;
import com.zxtech.platform.utils.StringUtil;
import com.zxtech.platform.vo.UserBean;

@Service("personService")
public class BasePersonServiceImpl implements IBasePersonService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BasePersonServiceImpl.class);
	
	@Autowired
	private BaseEmployeeManagerMapper baseEmployeeManagerMapper;


	@Override
	public List<Map<String, Object>> checkPersonList(BaseEmployeeSearchBean queryInfo, String combobox_type,
			UserBean user) {
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
		
		if(!"-1".equals(queryInfo.getComp_id()) || !"-1".equals(queryInfo.getStat_id()) || !"-1".equals(queryInfo.getArea_id())) {
			if(!"-1".equals(queryInfo.getArea_id())) {
				queryInfo.setComp_id(null);
				queryInfo.setStat_id(null);
			}else if(!"-1".equals(queryInfo.getStat_id())) {
				queryInfo.setComp_id(null);
				queryInfo.setArea_id(null);
			}else {
				queryInfo.setStat_id(null);
				queryInfo.setArea_id(null);
			}
		}else {
			queryInfo.setComp_id(null);
			queryInfo.setStat_id(null);
			queryInfo.setArea_id(null);
		}
		
		list.addAll(baseEmployeeManagerMapper.checkPersonList(queryInfo));
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> rectifyPersonList(BaseEmployeeSearchBean queryInfo, String combobox_type,
			UserBean user) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(combobox_type)) {
			map.put("emp_code", SysConstants.SYSTEM_SELECT_VALUE_ALL);
			map.put("code_name", SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(map);
		}else if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(combobox_type)){
			map.put("emp_code", SysConstants.SYSTEM_SELECT_VALUE_SELECT);
			map.put("code_name", SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(map);
		}
		
		if(!"-1".equals(queryInfo.getComp_id()) || !"-1".equals(queryInfo.getStat_id()) || !"-1".equals(queryInfo.getArea_id())) {
			if(!"-1".equals(queryInfo.getArea_id())) {
				queryInfo.setComp_id(null);
				queryInfo.setStat_id(null);
			}else if(!"-1".equals(queryInfo.getStat_id())) {
				queryInfo.setComp_id(null);
				queryInfo.setArea_id(null);
			}else {
				queryInfo.setStat_id(null);
				queryInfo.setArea_id(null);
			}
			list.addAll(baseEmployeeManagerMapper.rectifyPersonList(queryInfo));
		}else {
			return list;
		}
		
		return list;
	}


	@Override
	public List<Map<String, Object>> guaranteePersonlist(BaseEmployeeSearchBean queryInfo, String combobox_type,
			UserBean user) {
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
		if(!"-1".equals(queryInfo.getComp_id()) || !"-1".equals(queryInfo.getStat_id()) || !"-1".equals(queryInfo.getArea_id())) {
			if(!"-1".equals(queryInfo.getArea_id())) {
				queryInfo.setComp_id(null);
				queryInfo.setStat_id(null);
			}else if(!"-1".equals(queryInfo.getStat_id())) {
				queryInfo.setComp_id(null);
				queryInfo.setArea_id(null);
			}else {
				queryInfo.setStat_id(null);
				queryInfo.setArea_id(null);
			}
		}else {
			queryInfo.setComp_id(null);
			queryInfo.setStat_id(null);
			queryInfo.setArea_id(null);
		}
		
		list.addAll(baseEmployeeManagerMapper.guaranteePersonlist(queryInfo));
		
		return list;
	}

	@Override
	public List<Map<String, Object>> gyrusCheckPersonlist(BaseEmployeeSearchBean queryInfo, UserBean user) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getEmp_type()))
			queryInfo.setEmp_type(null);
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getComp_id()))
			queryInfo.setComp_id(null);
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getStat_id()))
			queryInfo.setStat_id(null);
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getArea_id()))
			queryInfo.setArea_id(null);
		
		if(StringUtils.isNotBlank(queryInfo.getContract_stat_id()) || StringUtils.isNotBlank(queryInfo.getContract_area_id())) {			
			if(StringUtils.isBlank(queryInfo.getContract_stat_id()))
				queryInfo.setContract_stat_id(null);
			if(StringUtils.isBlank(queryInfo.getContract_area_id()))
				queryInfo.setContract_area_id(null);
			list.addAll(baseEmployeeManagerMapper.gyrusCheckPersonList(queryInfo));
		}else {
			return list;
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> getCasualCheckRectificationPersionList(BaseEmployeeSearchBean queryInfo,
			UserBean user) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			list.addAll(baseEmployeeManagerMapper.getCasualCheckRectificationPersionList(queryInfo));		
		return list;
	}
	
}