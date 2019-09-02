package com.zxtech.ess.module.base.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.zxtech.ess.module.base.bean.BaseAttAndMagBean;
import com.zxtech.ess.module.base.mapper.BaseCompAttachManagerMapper;
import com.zxtech.ess.module.base.service.IBaseCompAttachService;
import com.zxtech.ess.module.gen.bean.BaseCompAttach;
import com.zxtech.ess.module.gen.mapper.BaseCompAttachMapper;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.page.PageHandler;
import com.zxtech.platform.vo.UserBean;

@Service("baseCompAttachServiceImpl")
public class BaseCompAttachServiceImpl implements IBaseCompAttachService{

	@Autowired
	private BaseCompAttachMapper baseCompAttachMapper;
	
	@Autowired
	private BaseCompAttachManagerMapper baseCompAttachManagerMapper;
	
	@Override
	public String save(BaseCompAttach bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		bean.setCreate_user(user.getRealname());
		bean.setCreate_timestamp(DateUtil.getNowTimestamp());
		ret = baseCompAttachMapper.insertSelective(bean);
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	public String delete(BaseCompAttach bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
				baseCompAttachMapper.selectByPrimaryKey(bean.getId()).getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
		ret = baseCompAttachMapper.deleteByPrimaryKey(bean.getId());
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	public Map<String, Object> compAttachPagingList(BaseAttAndMagBean bean, UserBean user) {
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(bean)
				.doSelectPage(() -> baseCompAttachManagerMapper.compAttachPagingList(bean));
		return PageHandler.wrapPageMap(pageResult);
	}

}
