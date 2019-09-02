package com.zxtech.platform.spring.jackson.support.deser;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;

public class JavaUtilDateDeserializers extends DateDeserializer {

	private static final long serialVersionUID = 1L;
	
	private SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public JavaUtilDateDeserializers() {
		super();
	}

	public JavaUtilDateDeserializers(JavaUtilDateDeserializers src, DateFormat df, String formatString) {
		super(src, df, formatString);
	}

	@Override
	protected JavaUtilDateDeserializers withDateFormat(DateFormat df, String formatString) {
		return new JavaUtilDateDeserializers(this, df, formatString);
	}

	@Override
	public java.util.Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		switch (p.getCurrentTokenId()) {
		case JsonTokenId.ID_STRING:
			String text = p.getText().trim();
			try {
				if (text.indexOf(":") == -1 && text.length() == 10) {
					return this.dateFormat.parse(text);
				} else if (text.indexOf(":") > 0 && text.length() == 19) {
					return this.datetimeFormat.parse(text);
				} else {
					throw new IllegalArgumentException("Could not parse text to java.util.Date.class: " + text + ", date format is error ");
				}
			} catch (Exception e) {
				throw new IllegalArgumentException("Could not parse text to java.util.Date.class: " + text);
			}
		case JsonTokenId.ID_NUMBER_INT: {
			long ts;
			try {
				ts = p.getLongValue();
			} catch (JsonParseException e) {
				Number v = (Number) ctxt.handleWeirdNumberValue(_valueClass, p.getNumberValue(),
						"not a valid 64-bit long for creating class");
				ts = v.longValue();
			}
			return new java.util.Date(ts);
		}
		case JsonTokenId.ID_NULL:
			return null;
		}
		return (java.util.Date) ctxt.handleUnexpectedToken(_valueClass, p);
	}

}
