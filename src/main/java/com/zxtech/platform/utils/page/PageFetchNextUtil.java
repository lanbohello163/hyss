package com.zxtech.platform.utils.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.collection.CollectionUtil;

public class PageFetchNextUtil {
	
	private static final String RESULT_KEY = "rows";
	private static final String TOTAL_PARAM = "total";
	private static final String PAGE_PARAM = "page";
	
	public static Map<String, Object> warp(PageFetchNextCount countFunc, PageFetchNext fetchFunc) {
		Map<String, Object> resultMap = new HashMap<>();
		Integer count = countFunc.getPageFetchNextCount();
		List<Map<String, Object>> resultList = null;
		if (count != null && count > 0) {
			resultList = fetchFunc.getPageFetchNext();
		}
		if (CollectionUtil.isEmpty(resultList)) {
			resultList = new ArrayList<>();
		}
		resultMap.put(RESULT_KEY, resultList);
		resultMap.put(TOTAL_PARAM, count == null ? 0 : count);
		//resultMap.put(PAGE_PARAM, pageResult.getPageNum());
		return resultMap;
	}

}
