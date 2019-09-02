package com.zxtech.platform.spring.convert.support;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;

import org.springframework.util.StringUtils;

public class JavaUtilDateClassConvertEditor extends PropertyEditorSupport {
	
	private SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			try {
				if (text.indexOf(":") == -1 && text.length() == 10) {
					setValue(this.dateFormat.parse(text));
				} else if (text.indexOf(":") > 0 && text.length() == 19) {
					setValue(this.datetimeFormat.parse(text));
				} else {
					throw new IllegalArgumentException("Could not parse text to java.util.Date.class: " + text + ", date format is error ");
				}
			} catch (Exception e) {
				throw new IllegalArgumentException("Could not parse text to java.util.Date.class: " + text);
			}
		} else {
			setValue(null);
		}
	}
	
}
