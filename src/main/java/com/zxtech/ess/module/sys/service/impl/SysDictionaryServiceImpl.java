package com.zxtech.ess.module.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxtech.ess.app.util.SysConstants;
import com.zxtech.ess.module.gen.bean.SysDictionary;
import com.zxtech.ess.module.pub.mapper.PubMapper;
import com.zxtech.ess.module.sys.service.ISysDictionaryService;

@Service("sysDictionaryService")
public class SysDictionaryServiceImpl implements ISysDictionaryService {

	@Autowired
	private PubMapper pubMapper;
	
	@Override
	public List<SysDictionary> getListByDictType(SysDictionary queryInfo, String combobox_type, String modelSign) {
		SysDictionary sysDictionary = new SysDictionary();
		List<SysDictionary> list = new ArrayList<SysDictionary>();
		if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(combobox_type)) {
			sysDictionary.setDict_value(SysConstants.SYSTEM_SELECT_VALUE_ALL);
			sysDictionary.setDict_name(SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(sysDictionary);
		}else if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(combobox_type)){
			sysDictionary.setDict_value(SysConstants.SYSTEM_SELECT_VALUE_SELECT);
			sysDictionary.setDict_name(SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(sysDictionary);
		}
		List<SysDictionary> listRes = new ArrayList<SysDictionary>();
		//1 维保数据字典 2.合同数据字典 3.热线故障数据字典
		if("1".equals(modelSign)) {
			 listRes = pubMapper.fetchSysDictionaryData_maintenance(queryInfo);
		}else if("2".equals(modelSign)) {
			listRes = pubMapper.fetchSysDictionaryData_contract(queryInfo);
		}else if("3".equals(modelSign)) {
			listRes = pubMapper.fetchSysDictionaryData_hotline(queryInfo);
		}else {
			listRes = pubMapper.fetchSysDictionaryData(queryInfo);
		}
		list.addAll(listRes);
		return list;
	}

}
