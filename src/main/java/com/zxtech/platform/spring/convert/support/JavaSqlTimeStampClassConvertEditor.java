package com.zxtech.platform.spring.convert.support;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;

import org.springframework.util.StringUtils;

public class JavaSqlTimeStampClassConvertEditor extends PropertyEditorSupport {
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			try {
				setValue(Timestamp.valueOf(text.trim()));
			} catch (Exception e) {
				throw new IllegalArgumentException("Could not parse text to java.sql.Timestamp.class: " + text);
			}
		} else {
			setValue(null);
		}
	}
	
}
