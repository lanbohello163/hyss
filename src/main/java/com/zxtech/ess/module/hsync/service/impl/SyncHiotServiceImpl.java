package com.zxtech.ess.module.hsync.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.zxtech.ess.module.hsync.bean.SyncHiotFetchElevatorSearchBean;
import com.zxtech.ess.module.hsync.mapper.SyncHiotMapper;
import com.zxtech.ess.module.hsync.service.ISyncHiotService;
import com.zxtech.platform.utils.page.PageHandler;

@Service("syncHiotService")
public class SyncHiotServiceImpl implements ISyncHiotService {
	
	@Autowired
	private SyncHiotMapper syncHiotMapper;

	@Override
	public List<Map<String, Object>> comboboxBaseCompanyData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> comboboxBaseStationData(Integer comp_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> fetchBaseElevatorData(SyncHiotFetchElevatorSearchBean queryInfo) {
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() -> syncHiotMapper.fetchBaseElevatorData(queryInfo));
		return PageHandler.wrapPageMap(pageResult);
	}

}
