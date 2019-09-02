package com.zxtech.ess.module.sys;

import java.util.List;
import java.util.Map;

import com.zxtech.platform.utils.page.PageParameter;

@FunctionalInterface
public interface IPageFetchNextData {

	List<Map<String, Object>> getPageFetchNext(Class<? extends PageParameter> param);
	
}
