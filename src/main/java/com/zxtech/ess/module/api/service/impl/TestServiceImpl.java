package com.zxtech.ess.module.api.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zxtech.ess.module.api.bean.Result;
import com.zxtech.ess.module.api.service.TestService;
import com.zxtech.ess.module.api.utils.HttpUtil;
import com.zxtech.ess.module.api.utils.QueueUtil;
import com.zxtech.ess.module.api.utils.ResultUtil;
import com.zxtech.platform.context.PlatformGlobalVar;
import com.zxtech.platform.utils.StringUtil;

import net.sf.json.JSONObject;

@Service("testServiceImpl")
public class TestServiceImpl implements TestService {

	@Autowired 
	HttpServletRequest request;
	
	/**
	 * 新维保主动请求数据
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public Result getInfo() throws Exception {
		Result result;
		JSONObject param = null;;
		String httpcode = "";
		
		//1.获取参数，调用hesb
		//2.获取返回数据，保存数据库
		try {
			//请求参数，可以是传过来的，也可以检索数据库获取的
			param = new JSONObject();
			Result resultObj = HttpUtil.doPostWithParam(httpcode, param);
			
			if (ResultUtil.SUCCESS_FLG.equals(resultObj.getRtnCode())) {
				
				//执行数据库操作
				//返回数据对象，Object类型，若不需要返回数据，则返回一各空Mao，不要返回null
				result = ResultUtil.resultSuccess(new HashMap<>());
				
				//保存同步日志
				/*
				 * 参数说明：
				 * saveType-保存类别，1-同步、2-错误、3-推送
				 * category-请求类别，1-主动、2-被动
				 * url-请求URL
				 * requestData-请求数据
				 * responseData-返回数据
				 * apiName-接口名称
				 * msg-错误日志
				 */
				QueueUtil.put(1, 1, httpcode, StringUtil.stringConvertor(param),
						StringUtil.stringConvertor(JSONObject.fromObject(resultObj)), "新维保主动请求数据", "");
			} else {
				//当返回参数异常时
				result = resultObj;
				//保存错误日志
				QueueUtil.put(2, 1, httpcode, StringUtil.stringConvertor(param),
						StringUtil.stringConvertor(JSONObject.fromObject(resultObj)), "新维保主动请求数据", resultObj.getRtnMsg());
			}
		} catch (Exception e) {
			//获取异常数据
			String exceptionStr = ExceptionUtils.getStackTrace(e);
			result = ResultUtil.resultFail(exceptionStr);
			//保存错误日志
			QueueUtil.put(2, 1, httpcode, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "新维保主动请求数据", exceptionStr);
			
			throw new RuntimeException();
		}
		
		return result;
	}
	
	/**
	 * 新维保被动接收数据
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public Result receiveInfo(JSONObject param) throws Exception {

		Result result;
		String url = request.getRequestURL().toString();
		
		try {
			//执行数据库操作
			
			//返回数据对象，Object类型，若不需要返回数据，则返回一各空Mao，不要返回null
			result = ResultUtil.resultSuccess(new HashMap<>());
			
			//保存同步日志
			QueueUtil.put(1, 2, url, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "新维保被动接收数据", "");
		} catch (Exception e) {
			//获取异常数据
			String exceptionStr = ExceptionUtils.getStackTrace(e);
			result = ResultUtil.resultFail(exceptionStr);
			//保存错误日志
			QueueUtil.put(2, 2, url, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "新维保被动接收数据", exceptionStr);
			
			throw new RuntimeException();
		}
		
		return result;
	}
	
	/**
	 * 新维保主动推送数据
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public Result sendInfo() throws Exception {
		Result result;
		JSONObject param = null;
		String httpcode = "";
		
		try {
			//获取推送数据，可以是实体类，也可以是Map
			Map<String, Object> map = new HashMap<>();
			param = JSONObject.fromObject(map);
			
			//调用推送的接口
			httpcode = PlatformGlobalVar.SYS_PROPERTIES.get("ESBHttpPath") + "/syncElevatorBaseInfoSiebel.api";
			Result resultObj = HttpUtil.doPostWithParam(httpcode, param);
			if (ResultUtil.SUCCESS_FLG.equals(resultObj.getRtnCode())) {
				
				//执行数据库操作
				//返回数据对象，Object类型，若不需要返回数据，则返回一各空Mao，不要返回null
				result = ResultUtil.resultSuccess(new HashMap<>());
				
				//保存同步日志
				/*
				 * 参数说明：
				 * saveType-保存类别，1-同步、2-错误、3-推送
				 * category-请求类别，1-主动、2-被动
				 * url-请求URL
				 * requestData-请求数据
				 * responseData-返回数据
				 * apiName-接口名称
				 * msg-错误日志
				 */
				QueueUtil.put(3, 1, httpcode, StringUtil.stringConvertor(param),
						StringUtil.stringConvertor(JSONObject.fromObject(resultObj)), "新维保主动推送数据", "");
			} else {
				result = resultObj;
				//当返回参数异常时，保存错误日志
				QueueUtil.put(3, 1, httpcode, StringUtil.stringConvertor(param),
						StringUtil.stringConvertor(JSONObject.fromObject(resultObj)), "新维保主动推送数据", resultObj.getRtnMsg());
			}
		} catch (Exception e) {
			//获取异常信息
			String exceptionStr = ExceptionUtils.getStackTrace(e);
			result = ResultUtil.resultFail(exceptionStr);
			//保存错误日志
			QueueUtil.put(3, 1, httpcode, StringUtil.stringConvertor(param),
					StringUtil.stringConvertor(JSONObject.fromObject(result)), "新维保主动请求数据", exceptionStr);
			
			throw new RuntimeException();
		}
		
		return result;
	}

}
