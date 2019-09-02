package com.zxtech.platform.spring.convert;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.zxtech.platform.spring.convert.support.JavaSqlDateClassConvertEditor;
import com.zxtech.platform.spring.convert.support.JavaSqlTimeClassConvertEditor;
import com.zxtech.platform.spring.convert.support.JavaSqlTimeStampClassConvertEditor;
import com.zxtech.platform.spring.convert.support.JavaUtilDateClassConvertEditor;

public class ExtendsWebBinding implements WebBindingInitializer {

	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(java.util.Date.class, new JavaUtilDateClassConvertEditor());
		binder.registerCustomEditor(java.sql.Date.class, new JavaSqlDateClassConvertEditor());
		binder.registerCustomEditor(java.sql.Time.class, new  JavaSqlTimeClassConvertEditor());
		binder.registerCustomEditor(java.sql.Timestamp.class, new  JavaSqlTimeStampClassConvertEditor());
	}

}
