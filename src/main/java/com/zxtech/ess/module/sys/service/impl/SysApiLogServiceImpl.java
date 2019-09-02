package com.zxtech.ess.module.sys.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.zxtech.ess.module.gen.bean.ApiErrorLog;
import com.zxtech.ess.module.gen.mapper.ApiErrorLogMapper;
import com.zxtech.ess.module.sys.bean.SysApiLogSearchBean;
import com.zxtech.ess.module.sys.mapper.SysApiLogManagerMapper;
import com.zxtech.ess.module.sys.service.ISysApiLogService;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.constant.WebConstants;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.page.PageHandler;
import com.zxtech.platform.vo.UserBean;

@Service("sysApiLogServiceImpl")
public class SysApiLogServiceImpl implements ISysApiLogService{
	
	@Autowired
	private SysApiLogManagerMapper sysApiLogManagerMapper;
	
	@Autowired
	private ApiErrorLogMapper apiErrorLogMapper;
	
	@Override
	public Map<String, Object> sysApiLogPagingList(SysApiLogSearchBean bean, UserBean user) {
		if(WebConstants.SELECT_VALUE_ALL.equals(String.valueOf(bean.getResults()))) {
			bean.setResults(null);
		}
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(bean)
				.doSelectPage(() -> sysApiLogManagerMapper.sysApiLogPagingList(bean));
		return PageHandler.wrapPageMap(pageResult);
	}

	@Override
	public String sysApiLogModify(ApiErrorLog bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		ApiErrorLog dbBean = apiErrorLogMapper.selectByPrimaryKey(bean.getId());
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
				dbBean.getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
		
		dbBean.setResults(bean.getResults());
		dbBean.setHandler(user.getRealname());
		dbBean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		ret = apiErrorLogMapper.updateByPrimaryKey(dbBean);
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

}
