package com.zxtech.platform.spring.jackson.support.ser;

import java.io.IOException;
import java.text.DateFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase;

@JacksonStdImpl
@SuppressWarnings("serial")
public class JavaSqlDateSerializer extends DateTimeSerializerBase<java.sql.Date> {
	
	public JavaSqlDateSerializer() {
		this(null, null);
	}

	protected JavaSqlDateSerializer(Boolean useDate, DateFormat customFormat) {
		super(java.sql.Date.class, useDate, customFormat);
	}

	@Override
	public JavaSqlDateSerializer withFormat(Boolean Date, DateFormat customFormat) {
		return new JavaSqlDateSerializer(Date, customFormat);
	}

	@Override
	protected long _timestamp(java.sql.Date value) {
		return (value == null) ? 0L : value.getTime();
	}

	@Override
	public void serialize(java.sql.Date value, JsonGenerator g, SerializerProvider provider) throws IOException {
		g.writeString(value.toString());
	}
}