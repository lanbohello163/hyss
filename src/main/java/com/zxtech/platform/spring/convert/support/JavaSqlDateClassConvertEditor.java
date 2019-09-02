package com.zxtech.platform.spring.convert.support;

import java.beans.PropertyEditorSupport;
import java.sql.Date;

import org.springframework.util.StringUtils;

public class JavaSqlDateClassConvertEditor extends PropertyEditorSupport {
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			try {
				setValue(Date.valueOf(text.trim()));
			} catch (Exception e) {
				throw new IllegalArgumentException("Could not parse text to java.sql.Date.class: " + text);
			}
		} else {
			setValue(null);
		}
	}

}
