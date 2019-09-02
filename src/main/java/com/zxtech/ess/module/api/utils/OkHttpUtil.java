package com.zxtech.ess.module.api.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtil {
	private static Logger log = LoggerFactory.getLogger(OkHttpUtil.class);
	public static OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	
	static {
		client = client.newBuilder()
				.connectTimeout(3, TimeUnit.SECONDS)
				.readTimeout(30, TimeUnit.SECONDS)
				.build();
	}

	public static void getFunAsyn(String url) throws Exception {
		Request request = new Request.Builder().url(url).build();

		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if (response.isSuccessful()) {
					log.info(response.body().string());
				} else {
					throw new IOException("Some Error Happen: " + response);
				}
			}

			@Override
			public void onFailure(Call call, IOException err) {
				log.error(err.getMessage());
			}
		});
	}

	public static String getFunSyn(String url, Map<String, String> paramMap) throws Exception {
		HttpUrl httpurl = HttpUrl.parse(url);
		Builder builder = httpurl.newBuilder();
		for (String key : paramMap.keySet()) {
			builder.addQueryParameter(key, paramMap.get(key));
		}
		httpurl = builder.build();

		System.out.println(httpurl.toString());
//		log.info(httpurl.toString());

		Request request = new Request.Builder().url(httpurl).build();
		Response response = client.newCall(request).execute();
		String result = response.body().string();
		return result;

	}

	public static String getFunSyn(String url) throws Exception {
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		String result = response.body().string();
		log.info(result);
		return result;
	}

	public static String postSyn(String url, String json) throws IOException {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	public static String postFormSyn(String url, Map<String, String> paramMap) throws Exception {
		FormBody.Builder builder = new FormBody.Builder();
		for (String key : paramMap.keySet()) {
			builder.add(key, paramMap.get(key));
		}
		RequestBody body = builder.build();
		
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}
	
	public static void main(String[] arg)  throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("address", "中文jjj");
		map.put("kk", "asdfg1234");
		map.put("html", "<div>哈哈哈哈jjjj</div>");
		
		getFunSyn("http://192.168.0.22:8080/hmt/hyss/hhhh.do",map);
	}
}
