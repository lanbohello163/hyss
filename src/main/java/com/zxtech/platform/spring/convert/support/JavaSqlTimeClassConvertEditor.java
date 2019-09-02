package com.zxtech.platform.spring.convert.support;

import java.beans.PropertyEditorSupport;
import java.sql.Time;

import org.springframework.util.StringUtils;

public class JavaSqlTimeClassConvertEditor extends PropertyEditorSupport {
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			try {
				setValue(Time.valueOf(text.trim()));
			} catch (Exception e) {
				throw new IllegalArgumentException("Could not parse text to java.sql.Time.class: " + text);
			}
		} else {
			setValue(null);
		}
	}
	
}
