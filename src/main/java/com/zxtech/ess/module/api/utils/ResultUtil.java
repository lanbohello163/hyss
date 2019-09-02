package com.zxtech.ess.module.api.utils;


import java.util.HashMap;

import com.zxtech.ess.module.api.bean.Result;

public class ResultUtil {
	
	public final static String SUCCESS_FLG = "S";
	public final static String ERROR_FLG = "E";
	public final static String UNUSUAL_FLG = "U";
	public final static String WARNING_FLG = "W";
	public final static String SUCCESS_MSG = "请求成功";
	public final static String ERROR_MSG = "系统异常";
	public final static String SEND_SECOND_MSG = "错误信息二次推送";
	
	//请求成功
	public static Result resultSuccess(Object data){
		Result result = new Result();
		result.setRtnCode(SUCCESS_FLG);
		result.setRtnMsg(SUCCESS_MSG);
		result.setRtnData(data);
		return result;
	}
	
	//请求失败
	public static Result resultFail(String msg){
		Result result = new Result();
		result.setRtnCode(ERROR_FLG);
		result.setRtnMsg(msg);
		result.setRtnData(new HashMap<>());
		return result;
	}
}
