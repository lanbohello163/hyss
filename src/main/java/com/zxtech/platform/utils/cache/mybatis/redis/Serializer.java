package com.zxtech.platform.utils.cache.mybatis.redis;

public interface Serializer {

  /**
   * Serialize method
   * @param object
   * @return serialized bytes
   */
  public byte[] serialize(Object object);

  /**
   * Unserialize method
   * @param bytes
   * @return unserialized object
   */
  public Object unserialize(byte[] bytes);

}
