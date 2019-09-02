
package com.zxtech.ess.module.api.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class ApiJsonUtil {

	public static final TypeAdapter<java.sql.Date> DATE = new TypeAdapter<java.sql.Date>() {
		@Override
		public java.sql.Date read(JsonReader in) throws IOException {
			if (in.peek() == JsonToken.NULL) {
				return null;
			}
			try {
				String val = in.nextString();
				if(val == null || "".equals(val)) {
					return null;
				}
				return new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(val).getTime());
			} catch (Exception e) {
				throw new JsonSyntaxException(e);
			}
		}

		@Override
		public void write(JsonWriter out, java.sql.Date value) throws IOException {
			if(value == null) {
				out.nullValue();  
			} else {
				out.value(new SimpleDateFormat("yyyy-MM-dd").format(value));
			}
		}
	};
}
