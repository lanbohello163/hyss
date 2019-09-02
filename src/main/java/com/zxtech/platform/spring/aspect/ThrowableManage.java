package com.zxtech.platform.spring.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;

public class ThrowableManage implements ThrowsAdvice {
	
	private static Logger log = LoggerFactory.getLogger(ThrowableManage.class);

	public void afterThrowing(Throwable ex) {
		log.error("error：", ex);
	}
}
