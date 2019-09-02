package com.zxtech.ess.module.api.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zxtech.ess.module.api.bean.Result;
import com.zxtech.ess.module.api.service.ApiDataErrorService;
import com.zxtech.ess.module.api.utils.HttpUtil;
import com.zxtech.ess.module.api.utils.ResultUtil;
import com.zxtech.ess.module.gen.bean.ApiErrorData;
import com.zxtech.ess.module.gen.bean.ApiErrorLog;
import com.zxtech.ess.module.gen.bean.ApiOperationLog;
import com.zxtech.ess.module.gen.mapper.ApiErrorDataMapper;
import com.zxtech.ess.module.gen.mapper.ApiErrorLogMapper;
import com.zxtech.ess.module.gen.mapper.ApiOperationLogMapper;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.StringUtil;

import net.sf.json.JSONObject;

@Service("apiDataErrorService")
public class ApiDataErrorServiceImpl implements ApiDataErrorService{
	
	@Autowired
	private ApiErrorDataMapper apiErrorDataMapper;
	
	@Autowired
	private ApiOperationLogMapper apiOperationLogMapper;
	
	@Autowired
	private ApiErrorLogMapper apiErrorLogMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveFailureDataApi(String url, String data){
		int sum = 0;
		if(StringUtil.isNotBlank(url) && StringUtil.isNotBlank(data)){
			ApiErrorData apiED = new ApiErrorData();
			apiED.setUrl(url);
			apiED.setPush_data(data);
			apiED.setRequest_time(DateUtil.getNowTimestamp());
			apiED.setCreate_timestamp(DateUtil.getNowTimestamp());
			sum = apiErrorDataMapper.insert(apiED);
		}
		return sum;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int sendErrorApiData(){
		int sum  = 0;
		//查询 错误的推送数据
		try {
			List<ApiErrorData> aedList = apiErrorDataMapper.selectAll();
			int num = aedList.size();
			for(int i = 0; i < num ; i++){ 
				ApiErrorData aed = aedList.get(i); 
				//hesb
				String httpcode = aed.getUrl();
				JSONObject param = JSONObject.fromObject(aed.getPush_data());
				Result result;
				
					result = HttpUtil.doPostWithParam(httpcode, param);
				
				//操作记录
				int operationLogId = saveApiOperationLog(1, aed.getUrl(), aed.getPush_data(), "", ResultUtil.SEND_SECOND_MSG);
				if("S".equals(result.getRtnMsg())){
					sum += apiErrorDataMapper.deleteByPrimaryKey(aed.getId());
				}else{
					aed.setRequest_time(DateUtil.getNowTimestamp());
					sum += apiErrorDataMapper.updateByPrimaryKeySelective(aed);
					//存失败信息
					saveApiErrorLog(operationLogId,String.valueOf(result));
				}
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return sum;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveApiOperationLog(int category, String url, String requestData, String responseData, String apiName){
		ApiOperationLog  apiOL = new ApiOperationLog();
		apiOL.setCategory(category);
		apiOL.setUrl(url);
		apiOL.setRequest_data(requestData);
		apiOL.setResponse_data(responseData);
		apiOL.setApi_name(apiName);
		apiOL.setCreate_timestamp(DateUtil.getNowTimestamp());
		apiOperationLogMapper.insert(apiOL);
		return apiOL.getId();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveApiErrorLog(int operationLogId, String errorLogInformation) {
		
		ApiErrorLog apiEL = new  ApiErrorLog();
		apiEL.setOperation_log_id(operationLogId);
		apiEL.setError_log_information(errorLogInformation);
		apiEL.setResults(0);
		apiEL.setCreate_timestamp(DateUtil.getNowTimestamp());
		apiEL.setEnable_flag("1");
		apiEL.setLast_update_timestamp(DateUtil.getNowTimestamp());
		int num = apiErrorLogMapper.insert(apiEL);
		return num;
	}

}
