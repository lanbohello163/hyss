package com.zxtech.platform.utils.page;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface PageFetchNext {
	
	List<Map<String, Object>> getPageFetchNext();

}
