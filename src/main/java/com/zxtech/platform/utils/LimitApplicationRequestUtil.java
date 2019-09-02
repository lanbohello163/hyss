package com.zxtech.platform.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LimitApplicationRequestUtil {
	
	private static Map<String, String> VALID_CLINET_NUMBER_MAP = new ConcurrentHashMap<String, String>();
	
	private static final Integer MAX_CLINETS = 2;
	
	/**
	 * 判断此次请求是否超出并发请求最大限制,如果超出返回false，否则返回true并取得一个请求数量
	 */
	public synchronized static boolean checkLimitApplicationRequest(String guidFile){
		if(fetchLimit() > 0) {
			fetchOneLimit(guidFile);
			return true;
		}
		return false;
	}
	
	/**
	 * 获取一个请求数量,返回剩余可用的数量
	 */
	public synchronized static Integer fetchOneLimit(String guidFile){
		VALID_CLINET_NUMBER_MAP.put(guidFile, guidFile);
		return fetchLimit();
	}
	
	/**
	 * 释放一个请求数量,返回剩余可用的数量
	 */
	public synchronized static Integer freeOneLimit(String guidFile){
		VALID_CLINET_NUMBER_MAP.remove(guidFile);
		return fetchLimit();
	}
	
	/**
	 * 返回当前请求剩余数量
	 */
	public synchronized static Integer fetchLimit(){
		return MAX_CLINETS - VALID_CLINET_NUMBER_MAP.size();
	}
	
}
