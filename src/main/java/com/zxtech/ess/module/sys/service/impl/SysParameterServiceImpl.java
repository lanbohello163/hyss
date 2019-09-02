package com.zxtech.ess.module.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.zxtech.ess.module.gen.bean.SysParameter;
import com.zxtech.ess.module.gen.mapper.SysParameterMapper;
import com.zxtech.ess.module.sys.bean.SysParameterSearchBean;
import com.zxtech.ess.module.sys.mapper.SysParameterManagerMapper;
import com.zxtech.ess.module.sys.service.ISysParameterService;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.page.PageHandler;
import com.zxtech.platform.vo.UserBean;

@Service("sysParameterServiceImpl")
public class SysParameterServiceImpl implements ISysParameterService{

	@Autowired
	private SysParameterMapper sysParameterMapper;
	
	@Autowired
	private SysParameterManagerMapper sysParameterManagerMapper;

	@Override
	public Map<String, Object> sysParameterPagingList(SysParameterSearchBean bean) {
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(bean)
				.doSelectPage(() -> sysParameterManagerMapper.sysParameterPagingList());
		return PageHandler.wrapPageMap(pageResult);
	}

	@Override
	public String update(SysParameter bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
//		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
//				sysParameterMapper.selectByPrimaryKey(bean.getId()).getLast_update_timestamp()))
//			return ResultConstants.DATA_CHANGES;
		bean.setLast_update_user(user.getRealname());
		bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		ret = sysParameterMapper.updateByPrimaryKeySelective(bean);
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	public SysParameter getSysParameterValueByKey(SysParameter record) {
		List<SysParameter> list = sysParameterMapper.selectBySqlConditions(record);
		if(list.size() > 0 ) {
			return list.get(0);
		}else {
			return new SysParameter();
		}
	}
}