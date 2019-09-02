package com.zxtech.platform.utils.cache;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AppKeyGenerator implements KeyGenerator {
	
	private static Gson gson = new GsonBuilder().create();
	private final static String SEPARATOR = "@";

	@Override
	public Object generate(Object target, Method method, Object... params) {
		String key = target.getClass().getCanonicalName() + SEPARATOR + method.getName() + SEPARATOR;
		if (params != null && params.length > 0) {
			key += gson.toJson(params);
		}
		return key;
	}

}
