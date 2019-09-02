package com.zxtech.platform.utils.page;

import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

public class PageHandler {
	
	private static final String RESULT_KEY = "rows";
	private static final String TOTAL_PARAM = "total";
	private static final String PAGE_PARAM = "page";

	public static <E, T extends PageParameter> Page<E> buildPage(T parameter) {
		return PageHelper.startPage(parameter.getPage(), parameter.getRows());
	}
	
	public static <E, T extends PageParameter> Page<E> buildPage(T parameter, String oderbyStr) {
		return PageHelper.startPage(parameter.getPage(), parameter.getRows(), oderbyStr);
	}
	
	public static <E> Map<String, Object> wrapPageMap(Page<E> pageResult) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put(RESULT_KEY, pageResult.getResult());
		resultMap.put(TOTAL_PARAM, pageResult.getTotal());
		resultMap.put(PAGE_PARAM, pageResult.getPageNum());
		return resultMap;
	}
	
}
