package com.zxtech.ess.module.api.utils;

import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.zxtech.ess.app.GlobalVal;
import com.zxtech.ess.module.api.bean.ApiSync;
import com.zxtech.ess.module.api.service.ApiDataErrorService;

public class QueueUtil {
	
	static LinkedBlockingQueue<ApiSync> queue;
	
	@Autowired
	static HttpServletRequest request;
	
	@Autowired
	static ApiDataErrorService apiDataErrorService;
	
	//队列初始化
	public static void init() {
		queue = new LinkedBlockingQueue<>();
		new Thread(() -> {
			try {
				while(true) {   
					ApiSync apiSync = queue.take();
					if (apiSync != null) {
				        int saveType = apiSync.getSaveType();
				        switch(saveType){
				        case 1:
				        	saveApiOperationLog(apiSync.getCategory(), apiSync.getUrl(), apiSync.getRequestData(),
				        			apiSync.getResponseData(), apiSync.getApiName());
				            break;
				        case 2:
				        	saveApiErrorLog(apiSync.getCategory(), apiSync.getUrl(), apiSync.getRequestData(),
				        			apiSync.getResponseData(), apiSync.getApiName(), apiSync.getMsg());
				            break;
				        case 3:
				        	saveFailureDataApi(apiSync.getCategory(), apiSync.getUrl(), apiSync.getRequestData(),
				        			apiSync.getResponseData(), apiSync.getApiName(), apiSync.getMsg());
				            break;
				        default:
				            break;
				        }
					}
	            }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
	}
	
	//添加队列
	public static void put(int saveType, int category, String url, String requestData,
			String responseData, String apiName, String msg) throws Exception {
		//保存队列对象
		ApiSync apiSync = new ApiSync();
		apiSync.setSaveType(saveType);
		apiSync.setCategory(category);
		apiSync.setUrl(url);
		apiSync.setRequestData(requestData);
		apiSync.setResponseData(responseData);
		apiSync.setApiName(apiName);
		apiSync.setMsg(msg);
		
		if (queue != null) {
			queue.put(apiSync);
		} else {
			queue = new LinkedBlockingQueue<>();
			queue.put(apiSync);
		}
	}
	
	//保存同步日志
	public static int saveApiOperationLog(int category, String url, String requestData,
			String responseData, String apiName) throws Exception {
		ApiDataErrorService apiDataErrorService = (ApiDataErrorService) GlobalVal.ctx.getBean("apiDataErrorService");
		return apiDataErrorService.saveApiOperationLog(category, url, requestData, responseData, apiName);
	}
	
	//保存错误日志
	public static void saveApiErrorLog(int category, String url, String requestData,
			String responseData, String apiName, String msg) throws Exception {
		ApiDataErrorService apiDataErrorService = (ApiDataErrorService) GlobalVal.ctx.getBean("apiDataErrorService");
		int operationLogId = saveApiOperationLog(category, url, requestData, responseData, apiName);
		apiDataErrorService.saveApiErrorLog(operationLogId, msg);
	}

	//保存推送失败的日志
	public static void saveFailureDataApi(int category, String url, String requestData,
			String responseData, String apiName, String msg) throws Exception {
		ApiDataErrorService apiDataErrorService = (ApiDataErrorService) GlobalVal.ctx.getBean("apiDataErrorService");
		saveApiErrorLog(category, url, requestData, responseData, apiName, msg);
		apiDataErrorService.saveFailureDataApi(url, requestData);
	}
	
}
