package com.zxtech.platform.utils.cache;

import org.springframework.data.redis.core.StringRedisTemplate;

import com.zxtech.platform.context.PlatformGlobalVar;

public class CacheTemplate {

	public final static StringRedisTemplate REDIS = (StringRedisTemplate) PlatformGlobalVar.APPLICATION_CONTEXT
			.getBean("redisTemplate");

}
