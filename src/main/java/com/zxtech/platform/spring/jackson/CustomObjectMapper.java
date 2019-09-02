package com.zxtech.platform.spring.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.zxtech.platform.spring.jackson.support.deser.JavaSqlDateDeserializers;
import com.zxtech.platform.spring.jackson.support.deser.JavaSqlTimestampDeserializers;
import com.zxtech.platform.spring.jackson.support.deser.JavaUtilDateDeserializers;
import com.zxtech.platform.spring.jackson.support.ser.JavaSqlDateSerializer;
import com.zxtech.platform.spring.jackson.support.ser.JavaSqlTimeSerializer;
import com.zxtech.platform.spring.jackson.support.ser.JavaSqlTimestampSerializer;
import com.zxtech.platform.spring.jackson.support.ser.JavaUtilDateSerializer;

/**
 * @description 覆盖Jackson处理日期的方式
 * @author zhaow
 * @date 2018-03-01
 */
public class CustomObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;

	public CustomObjectMapper() {
		super();
		registerModule(new DateModule());
	}

	private class DateModule extends SimpleModule {

		private static final long serialVersionUID = -7028459819354048027L;

		public DateModule() {
			super(PackageVersion.VERSION);
			// Serializer
			addSerializer(java.sql.Timestamp.class, new JavaSqlTimestampSerializer());
			addSerializer(java.sql.Date.class, new JavaSqlDateSerializer());
			addSerializer(java.sql.Time.class, new JavaSqlTimeSerializer());
			addSerializer(java.util.Date.class, new JavaUtilDateSerializer());
			// Deserializer
			addDeserializer(java.sql.Timestamp.class, new JavaSqlTimestampDeserializers());
			addDeserializer(java.sql.Date.class, new JavaSqlDateDeserializers());
			addDeserializer(java.util.Date.class, new JavaUtilDateDeserializers());
		}
	}
}
