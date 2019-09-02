package com.zxtech.ess.module.wx.service;

public interface IWxSynchronizeService {
	
	public String wxSynchronizeElevatorInfo(String last_sync_time);
	
	public String WxSynchronizeUpdateElevatorInfo(String last_sync_time);
}
