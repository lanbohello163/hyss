package com.zxtech.platform.spring.jackson.support.ser;

import java.io.IOException;
import java.text.DateFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase;

@JacksonStdImpl
@SuppressWarnings("serial")
public class JavaSqlTimestampSerializer extends DateTimeSerializerBase<java.sql.Timestamp> {
	
	public JavaSqlTimestampSerializer() {
		this(null, null);
	}

	protected JavaSqlTimestampSerializer(Boolean useTimestamp, DateFormat customFormat) {
		super(java.sql.Timestamp.class, useTimestamp, customFormat);
	}

	@Override
	public JavaSqlTimestampSerializer withFormat(Boolean timestamp, DateFormat customFormat) {
		return new JavaSqlTimestampSerializer(timestamp, customFormat);
	}

	@Override
	protected long _timestamp(java.sql.Timestamp value) {
		return (value == null) ? 0L : value.getTime();
	}

	@Override
	public void serialize(java.sql.Timestamp value, JsonGenerator g, SerializerProvider provider) throws IOException {
		g.writeString(value.toString().substring(0, value.toString().indexOf(".")));
	}
}