package com.zxtech.ess.module.api.utils;

import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.zxtech.ess.module.api.bean.Result;

import net.sf.json.JSONObject;


public class HttpUtil {
	
	public static Result doPostWithParam(String url, JSONObject param) throws Exception {
		
		Result result;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个post对象
		HttpPost post = new HttpPost(url);
		
		if(param != null) {
			StringEntity entity = new StringEntity(param.toString(), "utf-8");//解决中文乱码问题
			entity.setContentEncoding("UTF-8");    
			entity.setContentType("application/json;charset=UTF-8");    
			post.setEntity(entity);
		}
		
		
		//执行post请求
        CloseableHttpResponse response = httpClient.execute(post);
		if (response != null && response.getStatusLine().getStatusCode() == 200) {
			String resultStr = EntityUtils.toString(response.getEntity());
			JSONObject jsonObject = JSONObject.fromObject(resultStr);
			result = new Result();
			result.setRtnCode(jsonObject.getString("rtnCode"));
			result.setRtnMsg(jsonObject.getString("rtnMsg"));
			result.setRtnData(JSONObject.fromObject(jsonObject.getString("rtnData")));
		} else {
			result = new Result();
			result.setRtnCode(ResultUtil.ERROR_FLG);
			result.setRtnData(new HashMap<>());
			if(response!=null) {
				result.setRtnMsg(url + " 错误码：" + response.getStatusLine().getStatusCode());
			}else {
				result.setRtnMsg(url + " No Response!!");
			}
			
		}
		if(response!=null) {
			response.close();
		}
		
        httpClient.close();
		return result;
	}
}
