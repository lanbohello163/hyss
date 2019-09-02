package com.zxtech.ess.module.base.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zxtech.ess.app.util.SysConstants;
import com.zxtech.ess.module.base.bean.BaseGroupSearchBean;
import com.zxtech.ess.module.base.mapper.BaseGroupManagerMapper;
import com.zxtech.ess.module.base.service.IBaseGroupService;
import com.zxtech.ess.module.gen.bean.BaseEmployee;
import com.zxtech.ess.module.gen.bean.BaseGroup;
import com.zxtech.ess.module.gen.mapper.BaseEmployeeMapper;
import com.zxtech.ess.module.gen.mapper.BaseGroupMapper;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.constant.WebConstants;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.StringUtil;
import com.zxtech.platform.utils.excel.EasyExcelUtil;
import com.zxtech.platform.utils.page.PageHandler;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

@Service("baseGroupServiceImpl")
public class BaseGroupServiceImpl implements IBaseGroupService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseGroupServiceImpl.class);

	@Autowired
	private BaseGroupMapper baseGroupMapper;
	@Autowired
	private BaseGroupManagerMapper baseGroupManagerMapper;
	@Autowired
	private BaseEmployeeMapper baseEmployeeMapper;
	
	@Override
	public List<Map<String, Object>> groupList(BaseGroupSearchBean queryInfo, String combobox_type) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<>();
		if (SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(combobox_type)) {
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_ALL));
			map.put("group_name", SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(map);
		} else if (SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(combobox_type)) {
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_SELECT));
			map.put("group_name", SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(map);
		}
		list.addAll(baseGroupManagerMapper.groupList(queryInfo));
		return list;
	}

	@Override
	public Map<String, Object> baseGroupPagingList(BaseGroupSearchBean bean) {
		if(WebConstants.SELECT_VALUE_ALL.equals(String.valueOf(bean.getComp_id()))) {
			bean.setComp_id(null);
		}
		if(WebConstants.SELECT_VALUE_ALL.equals(String.valueOf(bean.getStat_id()))) {
			bean.setStat_id(null);
		}
		if(WebConstants.SELECT_VALUE_ALL.equals(String.valueOf(bean.getArea_id()))) {
			bean.setArea_id(null);
		}
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(bean)
				.doSelectPage(() -> baseGroupManagerMapper.baseGroupPagingList(bean));
		return PageHandler.wrapPageMap(pageResult);
	}

	@Override
	public String save(BaseGroup bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		if(StringUtil.isNotBlank(bean.getGroup_name()) && checkRepeatGroup(bean)) {
			return ResultConstants.REPEAT_ITEM2;
		}
		bean.setCreate_user(user.getRealname());
		bean.setCreate_timestamp(DateUtil.getNowTimestamp());
		bean.setSource_id(bean.getGroup_code());
		bean.setSync_datetime(DateUtil.getNowTimestamp());
		ret = baseGroupMapper.insertSelective(bean);
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	public String update(BaseGroup bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		BaseGroup dbBean =  baseGroupMapper.selectByPrimaryKey(bean.getId());
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
				dbBean.getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
		if(StringUtil.isNotBlank(bean.getGroup_name()) && checkRepeatGroup(bean)) {
			return ResultConstants.REPEAT_ITEM2;
		}
		dbBean.setGroup_name(bean.getGroup_name());
		dbBean.setComp_id(bean.getComp_id());
		dbBean.setStat_id(bean.getStat_id());
		dbBean.setArea_id(bean.getArea_id());
		dbBean.setGroup_manager(bean.getGroup_manager());
		dbBean.setGroup_type(bean.getGroup_type());
		dbBean.setMgr_area(bean.getMgr_area());
		dbBean.setGroup_remark(bean.getGroup_remark());
		dbBean.setLast_update_user(user.getRealname());
		dbBean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		ret = baseGroupMapper.updateByPrimaryKey(dbBean);
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	public String able(BaseGroup bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		try {
			//查询数据库中时间戳和前台传入的时间戳来验证是否是同一条数据
			if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
					baseGroupMapper.selectByPrimaryKey(bean.getId()).getLast_update_timestamp()))
				return ResultConstants.DATA_CHANGES;
			bean.setEnable_flag("1");
			bean.setLast_update_user(user.getRealname());
			bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
			ret = baseGroupMapper.updateByPrimaryKeySelective(bean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(ResultConstants.ERROR);
		}
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	public String disable(BaseGroup bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		try {
			//查询数据库中时间戳和前台传入的时间戳来验证是否是同一条数据
			if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
					baseGroupMapper.selectByPrimaryKey(bean.getId()).getLast_update_timestamp()))
				return ResultConstants.DATA_CHANGES;
			
			BaseEmployee baseEmpBean = new BaseEmployee();
			baseEmpBean.setGroup_id(bean.getId());
			baseEmpBean.setEnable_flag("1");
			List<BaseEmployee> baseEmpList = baseEmployeeMapper.selectBySqlConditions(baseEmpBean);
			if(!CollectionUtils.isEmpty(baseEmpList))
				return ResultConstants.REPEAT_ITEM1;
			
			bean.setEnable_flag("0");
			bean.setLast_update_user(user.getRealname());
			bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
			ret = baseGroupMapper.updateByPrimaryKeySelective(bean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(ResultConstants.ERROR);
		}
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	public ResultBean export(BaseGroupSearchBean queryInfo, UserBean user) {
		if(WebConstants.SELECT_VALUE_ALL.equals(String.valueOf(queryInfo.getComp_id()))) {
			queryInfo.setComp_id(null);
		}
		if(WebConstants.SELECT_VALUE_ALL.equals(String.valueOf(queryInfo.getStat_id()))) {
			queryInfo.setStat_id(null);
		}
		if(WebConstants.SELECT_VALUE_ALL.equals(String.valueOf(queryInfo.getArea_id()))) {
			queryInfo.setArea_id(null);
		}
		
		return EasyExcelUtil.writeExcelMoreSheetMoreWrite(() -> PageHelper.count(() -> baseGroupManagerMapper.getReportList(queryInfo)),
				() -> baseGroupManagerMapper.getReportList(queryInfo), queryInfo);
	}
	
	
	private Boolean checkRepeatColumn(BaseGroup bean){
		BaseGroup record = new BaseGroup();
		record.setGroup_code(bean.getGroup_code());
		record.setArea_id(bean.getArea_id());
		List<BaseGroup> list = baseGroupMapper.selectBySqlConditions(record);
		if(null == bean.getId()) {
			if(list.size() == 0) {
				return false;
			}
		} else {
			if (list.stream().filter(javaBean ->!bean.getId().equals(javaBean.getId())).count()==0) {
				return false;
			}
		}
		return true;
	}
	private Boolean checkRepeatGroup(BaseGroup bean){
		BaseGroup record = new BaseGroup();
		record.setGroup_name(bean.getGroup_name());
		record.setArea_id(bean.getArea_id());
		List<BaseGroup> list = baseGroupMapper.selectBySqlConditions(record);
		if(null == bean.getId()) {
			if(list.size() == 0) {
				return false;
			}
		} else {
			if (list.stream().filter(javaBean ->!bean.getId().equals(javaBean.getId())).count()==0) {
				return false;
			}
		}
		return true;
	}
}
