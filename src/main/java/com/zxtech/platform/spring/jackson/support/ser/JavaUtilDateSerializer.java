package com.zxtech.platform.spring.jackson.support.ser;

import java.io.IOException;
import java.text.DateFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase;

@JacksonStdImpl
@SuppressWarnings("serial")
public class JavaUtilDateSerializer extends DateTimeSerializerBase<java.util.Date> {
	
	public JavaUtilDateSerializer() {
		this(null, null);
	}

	protected JavaUtilDateSerializer(Boolean useDate, DateFormat customFormat) {
		super(java.util.Date.class, useDate, customFormat);
	}

	@Override
	public JavaUtilDateSerializer withFormat(Boolean Date, DateFormat customFormat) {
		return new JavaUtilDateSerializer(Date, customFormat);
	}

	@Override
	protected long _timestamp(java.util.Date value) {
		return (value == null) ? 0L : value.getTime();
	}

	@Override
	public void serialize(java.util.Date value, JsonGenerator g, SerializerProvider provider) throws IOException {
		g.writeString(new java.sql.Date(value.getTime()).toString());
	}
}