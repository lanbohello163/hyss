package com.zxtech.ess.app.interceptor;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.types.Expiration;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zxtech.platform.utils.MD5Util;
import com.zxtech.platform.utils.cache.CacheTemplate;

/**
 * @author zhaow
 * @version 1.1.0
 * @since 2018-03-02
 */
@Intercepts({
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
				RowBounds.class, ResultHandler.class }),
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
				RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class }),
		@Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class MybatisQueryCacheInterceptor implements Interceptor {

	private final static Logger LOGGER = LoggerFactory.getLogger(MybatisQueryCacheInterceptor.class);

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		String name = invocation.getMethod().getName();
		Object result = null;
		if ("query".equals(name)) {
			result = this.processQuery(invocation);
		} else if ("update".equals(name)) {
			result = this.processUpdate(invocation);
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	private Object processQuery(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
		MappedStatement ms = (MappedStatement) args[0];
		Object parameter = args[1];
		RowBounds rowBounds = (RowBounds) args[2];
		ResultHandler resultHandler = (ResultHandler) args[3];
		Executor executor = (Executor) invocation.getTarget();
		CacheKey cacheKey;
		BoundSql boundSql;
		if (args.length == 4) {
			boundSql = ms.getBoundSql(parameter);
			cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
		} else {
			cacheKey = (CacheKey) args[4];
			boundSql = (BoundSql) args[5];
		}

		// 处理缓存
		String methodNameFinal = CacheSignatureMapperUtil.INDENTITY + ms.getId();
		String cacheKeyFinal = CacheSignatureMapperUtil.INDENTITY + MD5Util.get32BitMd5EncString(cacheKey.toString());
		if (ms.getCache() != null) {
			CacheTemplate.REDIS.execute((RedisConnection conn) -> {
				if (conn.exists(methodNameFinal.getBytes())) {
					byte[] tempSetBytes = conn.get(methodNameFinal.getBytes());
					Set<String> tempSet = new HashSet<>();
					if (tempSetBytes != null) {
						tempSet = new Gson().fromJson(new String(tempSetBytes), new TypeToken<Set<String>>() {
						}.getType());
						for (String mapperNameFinal : tempSet) {
							byte[] cacheKeyUpdateTimeMillis = conn.get(cacheKeyFinal.getBytes());
							byte[] mapperUpdateTimeMillis = conn.get(mapperNameFinal.getBytes());
							if (cacheKeyUpdateTimeMillis == null || mapperUpdateTimeMillis == null
									|| Long.valueOf(new String(mapperUpdateTimeMillis)) >= Long
											.valueOf(new String(cacheKeyUpdateTimeMillis))) {
								Long num = conn.hDel(ms.getCache().getId().getBytes(), cacheKey.toString().getBytes());
								LOGGER.info(String.valueOf(num));
								break;
							}
						}
					}
				}
				return null;
			});
		}
		Object result = executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
		if (ms.getCache() != null) {
			CacheTemplate.REDIS.execute((RedisConnection conn) -> {
				if (conn.exists(methodNameFinal.getBytes())) {
					conn.set(cacheKeyFinal.getBytes(), String.valueOf(System.currentTimeMillis()).getBytes(),
							Expiration.milliseconds(CacheSignatureMapperUtil.EXPIRE_MILLIS), null);
				}
				return null;
			});
		}
		return result;
	}

	private Object processUpdate(Invocation invocation) throws Throwable {
		Object result = invocation.proceed();
		Object[] args = invocation.getArgs();
		MappedStatement ms = (MappedStatement) args[0];
		if (ms.getCache() != null) {
			String mapperName = ms.getId().substring(0, ms.getId().lastIndexOf("."));
			CacheTemplate.REDIS.execute((RedisConnection conn) -> {
				conn.set((CacheSignatureMapperUtil.CACHE_PREFIX + mapperName).getBytes(),
						String.valueOf(System.currentTimeMillis()).getBytes());
				return null;
			});
		}
		return result;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

}
