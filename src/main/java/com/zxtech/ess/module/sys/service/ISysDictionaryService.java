package com.zxtech.ess.module.sys.service;

import java.util.List;

import com.zxtech.ess.module.gen.bean.SysDictionary;

public interface ISysDictionaryService {
	
	List<SysDictionary> getListByDictType(SysDictionary queryInfo, String combobox_type, String modelSign);
	
}