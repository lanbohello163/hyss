package com.zxtech.ess.app.util.wrap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zxtech.ess.app.util.SysConstants;

@FunctionalInterface
public interface ComboboxDefinedWrapper {

	ComboboxDefined define();

	default List<Map<String, Object>> wrap(List<Map<String, Object>> list) {
		ComboboxDefined define = define();
		if (SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(define.getCombobox_type())) {
			Map<String, Object> map = new HashMap<>();
			map.put(define.getId(), SysConstants.SYSTEM_SELECT_VALUE_ALL);
			map.put(define.getText(), SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(0, map);
		}
		if (SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(define.getCombobox_type())) {
			Map<String, Object> map = new HashMap<>();
			map.put(define.getId(), SysConstants.SYSTEM_SELECT_VALUE_SELECT);
			map.put(define.getText(), SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(0, map);
		}
		return list;
	}

}
