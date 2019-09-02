package com.zxtech.platform.utils.cache.mybatis.redis;

import redis.clients.jedis.Jedis;

public interface RedisCallback {

  Object doWithRedis(Jedis jedis);
}
