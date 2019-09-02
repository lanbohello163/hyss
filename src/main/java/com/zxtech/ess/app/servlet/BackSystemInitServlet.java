package com.zxtech.ess.app.servlet;

import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.data.redis.connection.RedisConnection;

import com.zxtech.ess.app.GlobalVal;
import com.zxtech.platform.constant.PlatformConstants;
import com.zxtech.platform.context.PlatformGlobalVar;
import com.zxtech.platform.utils.cache.CacheTemplate;

public class BackSystemInitServlet extends HttpServlet {
	private static final long serialVersionUID = 4124099489926662290L;

	@Override
	public void init() throws ServletException {

		if (PlatformConstants.COMPONENT_SWITCH_TRUE
				.equals(PlatformGlobalVar.SYS_PROPERTIES.get("startup.redis.flushdb"))) {
			// clear all cache
			CacheTemplate.REDIS.execute((RedisConnection connection) -> {
				connection.flushDb();
				return null;
			});
		} else {
			// clear some cache
			CacheTemplate.REDIS.execute((RedisConnection connection) -> {
				Set<byte[]> keys = connection.keys("com.zxtech.ess.module.gen.mapper.*".getBytes());
				for (byte[] key : keys) {
					connection.del(key);
				}
				return null;
			});
		}

		GlobalVal.init();
	}

}
