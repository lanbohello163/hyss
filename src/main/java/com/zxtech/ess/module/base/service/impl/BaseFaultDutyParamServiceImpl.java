package com.zxtech.ess.module.base.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.zxtech.ess.module.base.bean.BaseFaultDutyParamSearchBean;
import com.zxtech.ess.module.base.mapper.BaseFaultDutyParamManagerMapper;
import com.zxtech.ess.module.base.service.IBaseFaultDutyParamService;
import com.zxtech.ess.module.gen.bean.CalFaultDutyParam;
import com.zxtech.ess.module.gen.mapper.CalFaultDutyParamMapper;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.utils.page.PageHandler;
import com.zxtech.platform.vo.UserBean;

@Service("baseFaultDutyParamServiceImpl")
public class BaseFaultDutyParamServiceImpl implements IBaseFaultDutyParamService {
	
	@Autowired
	private CalFaultDutyParamMapper calFaultDutyParamMapper;
	@Autowired
	private BaseFaultDutyParamManagerMapper baseFaultDutyParamManagerMapper;

	@Override
	public Map<String, Object> getListWithPaging(BaseFaultDutyParamSearchBean queryInfo) {
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() -> baseFaultDutyParamManagerMapper.getListWithPaging(queryInfo));
		return PageHandler.wrapPageMap(pageResult);
	}

	//新增、编辑时判断不良分类编码是否存在重复记录
	private Boolean checkRepeatColumn(CalFaultDutyParam bean){
		CalFaultDutyParam calFaultDutyParam = new CalFaultDutyParam();
		calFaultDutyParam.setBad_code(bean.getBad_code());
		List<CalFaultDutyParam> list = calFaultDutyParamMapper.selectBySqlConditions(calFaultDutyParam);
		if (list.stream().filter(javaBean ->!javaBean.getId().equals(bean.getId())).count()==0)
			return false;
		return true;
	}
	
	@Override
	public String add(CalFaultDutyParam bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		if (checkRepeatColumn(bean))
			return ResultConstants.REPEAT;
		int ret = calFaultDutyParamMapper.insertSelective(bean);
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}
	
	@Override
	public String update(CalFaultDutyParam bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		if (checkRepeatColumn(bean))
			return ResultConstants.REPEAT;
		CalFaultDutyParam calFaultDutyParam = calFaultDutyParamMapper.selectByPrimaryKey(bean.getId());
		bean.setEnable_flag(calFaultDutyParam.getEnable_flag());
		int ret = calFaultDutyParamMapper.updateByPrimaryKey(bean);
		if(ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	public String enable(CalFaultDutyParam bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		bean.setEnable_flag("1");
		int ret = calFaultDutyParamMapper.updateByPrimaryKeySelective(bean);
		if(ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}
	
	@Override
	public String disable(CalFaultDutyParam bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		bean.setEnable_flag("0");
		int ret = calFaultDutyParamMapper.updateByPrimaryKeySelective(bean);
		if(ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}
	
}
