package com.zxtech.platform.spring.jackson.support.ser;

import java.io.IOException;
import java.text.DateFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase;

@JacksonStdImpl
@SuppressWarnings("serial")
public class JavaSqlTimeSerializer extends DateTimeSerializerBase<java.sql.Time> {
	
	public JavaSqlTimeSerializer() {
		this(null, null);
	}

	protected JavaSqlTimeSerializer(Boolean useTime, DateFormat customFormat) {
		super(java.sql.Time.class, useTime, customFormat);
	}

	@Override
	public JavaSqlTimeSerializer withFormat(Boolean Time, DateFormat customFormat) {
		return new JavaSqlTimeSerializer(Time, customFormat);
	}

	@Override
	protected long _timestamp(java.sql.Time value) {
		return (value == null) ? 0L : value.getTime();
	}

	@Override
	public void serialize(java.sql.Time value, JsonGenerator g, SerializerProvider provider) throws IOException {
		g.writeString(value.toString());
	}
}