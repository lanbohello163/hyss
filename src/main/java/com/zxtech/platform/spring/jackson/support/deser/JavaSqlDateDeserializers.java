package com.zxtech.platform.spring.jackson.support.deser;

import java.io.IOException;
import java.text.DateFormat;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.SqlDateDeserializer;

public class JavaSqlDateDeserializers extends SqlDateDeserializer {

	private static final long serialVersionUID = 1L;

	public JavaSqlDateDeserializers() {
		super();
	}

	public JavaSqlDateDeserializers(JavaSqlDateDeserializers src, DateFormat df, String formatString) {
		super(src, df, formatString);
	}

	@Override
	protected JavaSqlDateDeserializers withDateFormat(DateFormat df, String formatString) {
		return new JavaSqlDateDeserializers(this, df, formatString);
	}

	@Override
	public java.sql.Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		switch (p.getCurrentTokenId()) {
		case JsonTokenId.ID_STRING:
			return java.sql.Date.valueOf(p.getText().trim());
		case JsonTokenId.ID_NUMBER_INT: {
			long ts;
			try {
				ts = p.getLongValue();
			} catch (JsonParseException e) {
				Number v = (Number) ctxt.handleWeirdNumberValue(_valueClass, p.getNumberValue(),
						"not a valid 64-bit long for creating class");
				ts = v.longValue();
			}
			return new java.sql.Date(ts);
		}
		case JsonTokenId.ID_NULL:
			return null;
		}
		return (java.sql.Date) ctxt.handleUnexpectedToken(_valueClass, p);
	}

}
