package com.zxtech.ess.module.base.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.zxtech.ess.module.base.bean.BasePrjPersonSearchBean;
import com.zxtech.ess.module.base.mapper.BasePrjPersonManagerMapper;
import com.zxtech.ess.module.base.service.IBasePrjPersonService;
import com.zxtech.ess.module.gen.bean.BaseProjectPerson;
import com.zxtech.ess.module.gen.mapper.BaseProjectPersonMapper;
import com.zxtech.ess.module.pub.service.IPubService;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.page.PageHandler;
import com.zxtech.platform.vo.UserBean;

@Service("basePrjPersonServiceImpl")
public class BasePrjPersonServiceImpl implements IBasePrjPersonService{

	@Autowired
	private BasePrjPersonManagerMapper basePrjPersonManagerMapper;
	@Autowired
	private BaseProjectPersonMapper baseProjectPersonMapper;
	@Autowired
	private IPubService pubService;
	
	@Override
	public Map<String, Object> basePrjPersonPagingList(BasePrjPersonSearchBean bean, UserBean user) {
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(bean)
				.doSelectPage(() -> basePrjPersonManagerMapper.basePrjPersonPagingList(bean));
		return PageHandler.wrapPageMap(pageResult);
	}

	@Override
	public String save(BaseProjectPerson bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		bean.setCreate_user(user.getRealname());
		bean.setCreate_timestamp(DateUtil.getNowTimestamp());
		bean.setSource_id(pubService.getSequenceOrder());
		bean.setSync_datetime(DateUtil.getNowTimestamp());
		ret = baseProjectPersonMapper.insertSelective(bean);
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	public String delete(BaseProjectPerson bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
				baseProjectPersonMapper.selectByPrimaryKey(bean.getId()).getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
		bean.setLast_update_user(user.getRealname());
		bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		bean.setEnable_flag("0");
		ret = baseProjectPersonMapper.updateByPrimaryKeySelective(bean);
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	public String update(BaseProjectPerson bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		BaseProjectPerson dbBean = baseProjectPersonMapper.selectByPrimaryKey(bean.getId()); 
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
				dbBean.getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
		dbBean.setIs_important(bean.getIs_important());
		dbBean.setPerson_name(bean.getPerson_name());
		dbBean.setPerson_sex(bean.getPerson_sex());
		dbBean.setPerson_position(bean.getPerson_position());
		dbBean.setPerson_tel(bean.getPerson_tel());
		dbBean.setPerson_email(bean.getPerson_email());
		dbBean.setThe_title(bean.getThe_title());
		dbBean.setIs_cust_sign(bean.getIs_cust_sign());
		dbBean.setAuthorized_stop_desc(bean.getAuthorized_stop_desc());
		dbBean.setIs_authorized_stop(bean.getIs_authorized_stop());
		dbBean.setAuthorized_person(bean.getAuthorized_person());
		dbBean.setLast_update_user(user.getRealname());
		dbBean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		ret = baseProjectPersonMapper.updateByPrimaryKey(dbBean);
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}
}
