package com.zxtech.ess.module.wx.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.zxtech.ess.module.wx.bean.WechatElevatorPropertyInfoBean;
import com.zxtech.ess.module.wx.mapper.WxSynchronizeManagerMapper;
import com.zxtech.ess.module.wx.service.IWxSynchronizeService;
import com.zxtech.platform.utils.StringUtil;

@Service("wxSynchronizeServiceImpl")
public class WxSynchronizeServiceImpl implements IWxSynchronizeService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WxSynchronizeServiceImpl.class);
	
	@Autowired
	private WxSynchronizeManagerMapper wxSynchronizeManagerMapper;

	@Override
	public String wxSynchronizeElevatorInfo(String last_sync_time) {
		Gson gson = new Gson();
		List<WechatElevatorPropertyInfoBean> dataList = new ArrayList<WechatElevatorPropertyInfoBean>();
		
		Timestamp lastSyncTime = null;
		if(StringUtil.isNotBlank(last_sync_time)) {
			lastSyncTime = Timestamp.valueOf(last_sync_time);
		}
		try {
			dataList = wxSynchronizeManagerMapper.getSynchronizedElevatorList(lastSyncTime);			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return gson.toJson(dataList);
	}



	@Override
	public String WxSynchronizeUpdateElevatorInfo(String last_sync_time) {
		Gson gson = new Gson();
		List<WechatElevatorPropertyInfoBean> dataList = new ArrayList<WechatElevatorPropertyInfoBean>();
		
		Timestamp lastSyncTime = null;
		if(StringUtil.isNotBlank(last_sync_time)) {
			lastSyncTime = Timestamp.valueOf(last_sync_time);
		}
		try {
			dataList = wxSynchronizeManagerMapper.getSynchronizedUpdateElevatorList(lastSyncTime);			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return gson.toJson(dataList);
	}

}
