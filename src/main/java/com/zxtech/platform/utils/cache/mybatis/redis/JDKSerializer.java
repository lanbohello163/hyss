package com.zxtech.platform.utils.cache.mybatis.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.ibatis.cache.CacheException;

public enum JDKSerializer implements Serializer {
  //Enum singleton, which is preferred approach since Java 1.5
  INSTANCE;

  private JDKSerializer() {
    // prevent instantiation
  }

  public byte[] serialize(Object object) {
    ObjectOutputStream oos = null;
    ByteArrayOutputStream baos = null;
    try {
      baos = new ByteArrayOutputStream();
      oos = new ObjectOutputStream(baos);
      oos.writeObject(object);
      return baos.toByteArray();
    } catch (Exception e) {
      throw new CacheException(e);
    }
  }

  public Object unserialize(byte[] bytes) {
    if (bytes == null) {
      return null;
    }
    ByteArrayInputStream bais = null;
    try {
      bais = new ByteArrayInputStream(bytes);
      ObjectInputStream ois = new ObjectInputStream(bais);
      return ois.readObject();
    } catch (Exception e) {
      throw new CacheException(e);
    }
  }

}
