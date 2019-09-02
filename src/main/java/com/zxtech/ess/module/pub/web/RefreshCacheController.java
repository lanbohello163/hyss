package com.zxtech.ess.module.pub.web;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxtech.ess.app.interceptor.CacheSignatureMapperUtil;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.utils.StringUtil;
import com.zxtech.platform.utils.cache.CacheTemplate;

@Controller
@RequestMapping("/base")
public class RefreshCacheController {

	@RequestMapping(value = "/updateTableTimestampCache.api", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String updateTableTimestampCache(String tableName) {
		if (StringUtil.isBlank(tableName))
			return ResultConstants.ERROR;
		String[] tableNameArr = tableName.split(",");
		CacheTemplate.REDIS.execute((RedisConnection conn) -> {
			for (String classMapperName : tableNameArr) {
				if (StringUtil.isNotBlank(classMapperName)) {
					conn.set(
							(CacheSignatureMapperUtil.CACHE_PREFIX + "com.zxtech.ess.module.gen.mapper."
									+ format(classMapperName, true) + "Mapper").getBytes(),
							String.valueOf(System.currentTimeMillis()).getBytes());
				}
			}
			return null;
		});
		return ResultConstants.SUCCESS;
	}

	private String format(String str, boolean initCap) {
		StringBuffer name = new StringBuffer();
		String[] tmp = str.trim().toLowerCase().split("_");
		for (int i = 0; i < tmp.length; i++) {
			name.append(tmp[i].substring(0, 1).toUpperCase()).append(tmp[i].substring(1));
		}
		if (initCap) {
			return name.toString();
		} else {
			return name.substring(0, 1).toLowerCase() + name.substring(1);
		}
	}
}
