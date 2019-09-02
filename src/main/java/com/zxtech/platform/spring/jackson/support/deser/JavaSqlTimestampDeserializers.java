package com.zxtech.platform.spring.jackson.support.deser;

import java.io.IOException;
import java.text.DateFormat;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.TimestampDeserializer;

public class JavaSqlTimestampDeserializers extends TimestampDeserializer {

	private static final long serialVersionUID = 1L;

	public JavaSqlTimestampDeserializers() {
		super();
	}

	public JavaSqlTimestampDeserializers(JavaSqlTimestampDeserializers src, DateFormat df, String formatString) {
		super(src, df, formatString);
	}

	@Override
	protected JavaSqlTimestampDeserializers withDateFormat(DateFormat df, String formatString) {
		return new JavaSqlTimestampDeserializers(this, df, formatString);
	}

	@Override
	public java.sql.Timestamp deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		switch (p.getCurrentTokenId()) {
		case JsonTokenId.ID_STRING:
			return java.sql.Timestamp.valueOf(p.getText().trim());
		case JsonTokenId.ID_NUMBER_INT: {
			long ts;
			try {
				ts = p.getLongValue();
			} catch (JsonParseException e) {
				Number v = (Number) ctxt.handleWeirdNumberValue(_valueClass, p.getNumberValue(),
						"not a valid 64-bit long for creating class");
				ts = v.longValue();
			}
			return new java.sql.Timestamp(ts);
		}
		case JsonTokenId.ID_NULL:
			return null;
		}
		return (java.sql.Timestamp) ctxt.handleUnexpectedToken(_valueClass, p);
	}

}
