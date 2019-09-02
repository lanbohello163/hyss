package com.zxtech.ess.module.api.service;

public interface ApiDataErrorService {

	/**
	 * 保存接口数据推送失败信息
	 * 
	 * @author syp661
	 * @beanParam {String} url api请求地址。
	 * @beanParam {String} data 推送的数据。
	 * @beanParam {String} category 分类。
	 * @throws Exception
	 */
	public int saveFailureDataApi(String url,String data);
	
	/**
	 * 推送失败信息定时自动调用重新推送
	 * 
	 * @author syp661
	 * @throws Exception
	 */
	public int sendErrorApiData();
	
	/**
	 * 保存api外部接口操作记录
	 * 
	 * @author syp661
	 * @throws Exception
	 */
	public int saveApiOperationLog(int category, String url,String requestData,String responseData,String apiName);
	
	/**
	 * 保存api外部接口错误信息
	 * 
	 * @author syp661
	 * @throws Exception
	 */
	public int saveApiErrorLog(int operationLogId, String errorLogInformation);
	
}
