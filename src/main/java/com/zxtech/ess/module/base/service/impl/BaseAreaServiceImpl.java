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
import com.zxtech.ess.module.base.bean.BaseAreaSearchBean;
import com.zxtech.ess.module.base.mapper.BaseAreaManagerMapper;
import com.zxtech.ess.module.base.service.IBaseAreaService;
import com.zxtech.ess.module.gen.bean.BaseArea;
import com.zxtech.ess.module.gen.bean.BaseEmployee;
import com.zxtech.ess.module.gen.bean.BaseGroup;
import com.zxtech.ess.module.gen.mapper.BaseAreaMapper;
import com.zxtech.ess.module.gen.mapper.BaseEmployeeMapper;
import com.zxtech.ess.module.gen.mapper.BaseGroupMapper;
import com.zxtech.ess.module.pub.service.IPubService;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.constant.WebConstants;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.StringUtil;
import com.zxtech.platform.utils.excel.EasyExcelUtil;
import com.zxtech.platform.utils.page.PageHandler;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

@Service("baseAreaServiceImpl")
public class BaseAreaServiceImpl implements IBaseAreaService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseAreaServiceImpl.class);
	
	@Autowired
	private BaseAreaMapper baseAreaMapper;
	@Autowired
	private BaseAreaManagerMapper baseAreaManagerMapper;
	@Autowired
	private BaseGroupMapper baseGroupMapper;
	@Autowired
	private BaseEmployeeMapper baseEmployeeMapper;
	@Autowired
	private IPubService pubService;
	
	@Override
	public List<Map<String, Object>> areaList(BaseAreaSearchBean queryInfo, String combobox_type) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<>();
		if (SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(combobox_type)) {
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_ALL));
			map.put("area_name", SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(map);
		} else if (SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(combobox_type)) {
			map.put("id", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_SELECT));
			map.put("area_name", SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(map);
		}
		list.addAll(baseAreaManagerMapper.fetchWindowAreaList(queryInfo));
		return list;
	}

	@Override
	public Map<String, Object> baseAreaPagingList(BaseAreaSearchBean bean) {
		if(WebConstants.SELECT_VALUE_ALL.equals(String.valueOf(bean.getComp_id()))) {
			bean.setComp_id(null);
		}
		if(WebConstants.SELECT_VALUE_ALL.equals(String.valueOf(bean.getStat_id()))) {
			bean.setStat_id(null);
		}
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(bean)
				.doSelectPage(() -> baseAreaManagerMapper.baseAreaPagingList(bean));
		return PageHandler.wrapPageMap(pageResult);
	}

	@Override
	public String save(BaseArea bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		if(StringUtil.isNotBlank(bean.getArea_name()) && checkRepeatAreaName(bean)) {
			return ResultConstants.REPEAT_ITEM2;
		}
		bean.setCreate_user(user.getRealname());
		bean.setCreate_timestamp(DateUtil.getNowTimestamp());
		bean.setSource_id(bean.getArea_code());
		bean.setSync_datetime(DateUtil.getNowTimestamp());
		ret = baseAreaMapper.insertSelective(bean);
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	public String update(BaseArea bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		BaseArea dbBean = baseAreaMapper.selectByPrimaryKey(bean.getId());
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
				dbBean.getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
		if(StringUtil.isNotBlank(bean.getArea_name()) && checkRepeatAreaName(bean)) {
			return ResultConstants.REPEAT_ITEM2;
		}
		dbBean.setArea_name(bean.getArea_name());
		dbBean.setComp_id(bean.getComp_id());
		dbBean.setStat_id(bean.getStat_id());
		dbBean.setArea_manager(bean.getArea_manager());
		dbBean.setMgr_area(bean.getMgr_area());
		dbBean.setArea_remark(bean.getArea_remark());
		dbBean.setLast_update_user(user.getRealname());
		dbBean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		ret = baseAreaMapper.updateByPrimaryKey(dbBean);
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	public String disable(BaseArea bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		try {
			//查询数据库中时间戳和前台传入的时间戳来验证是否是同一条数据
			if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
					baseAreaMapper.selectByPrimaryKey(bean.getId()).getLast_update_timestamp()))
				return ResultConstants.DATA_CHANGES;
			
			BaseGroup baseGroupaBean = new BaseGroup();
			baseGroupaBean.setArea_id(bean.getId());
			baseGroupaBean.setEnable_flag("1");
			List<BaseGroup> baseAreaList = baseGroupMapper.selectBySqlConditions(baseGroupaBean);
			if(!CollectionUtils.isEmpty(baseAreaList))
				return ResultConstants.REPEAT_ITEM1;
			
			BaseEmployee baseEmpBean = new BaseEmployee();
			baseEmpBean.setArea_id(bean.getId());
			baseEmpBean.setEnable_flag("1");
			List<BaseEmployee> baseEmpList = baseEmployeeMapper.selectBySqlConditions(baseEmpBean);
			if(!CollectionUtils.isEmpty(baseEmpList))
				return ResultConstants.REPEAT_ITEM1;
			
			bean.setEnable_flag("0");
			bean.setLast_update_user(user.getRealname());
			bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
			ret = baseAreaMapper.updateByPrimaryKeySelective(bean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(ResultConstants.ERROR);
		}
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	public String able(BaseArea bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		try {
			//查询数据库中时间戳和前台传入的时间戳来验证是否是同一条数据
			if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
					baseAreaMapper.selectByPrimaryKey(bean.getId()).getLast_update_timestamp()))
				return ResultConstants.DATA_CHANGES;
			bean.setEnable_flag("1");
			bean.setLast_update_user(user.getRealname());
			bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
			ret = baseAreaMapper.updateByPrimaryKeySelective(bean);
			BaseGroup baseGroupBean = new BaseGroup();
			baseGroupBean.setEnable_flag("0");
			baseGroupBean.setArea_id(bean.getId());
		    List<BaseGroup> baseGroupList = baseGroupMapper.selectBySqlConditions(baseGroupBean);
			for (BaseGroup baseGroup : baseGroupList) {
				BaseGroup groupBean = new BaseGroup();
				groupBean.setEnable_flag("1");
				groupBean.setId(baseGroup.getId());
				groupBean.setLast_update_user(user.getRealname());
				groupBean.setLast_update_timestamp(DateUtil.getNowTimestamp());
				ret = baseGroupMapper.updateByPrimaryKeySelective(groupBean);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(ResultConstants.ERROR);
		}
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}
	//导出
	@Override
	public ResultBean export(UserBean user, BaseAreaSearchBean queryInfo) {
		if(WebConstants.SELECT_VALUE_ALL.equals(String.valueOf(queryInfo.getComp_id()))) {
			queryInfo.setComp_id(null);
		}
		if(WebConstants.SELECT_VALUE_ALL.equals(String.valueOf(queryInfo.getStat_id()))) {
			queryInfo.setStat_id(null);
		}
		return EasyExcelUtil.writeExcelMoreSheetMoreWrite(() -> PageHelper.count(() -> baseAreaManagerMapper.getReportList(queryInfo)),
				() -> baseAreaManagerMapper.getReportList(queryInfo), queryInfo);
	}
	private Boolean checkRepeatColumn(BaseArea bean){
		BaseArea record = new BaseArea();
		record.setArea_code(bean.getArea_code());
		record.setStat_id(bean.getStat_id());
		List<BaseArea> list = baseAreaMapper.selectBySqlConditions(record);
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
	private Boolean checkRepeatAreaName(BaseArea bean) {
		BaseArea record = new BaseArea();
		record.setArea_name(bean.getArea_name());
		record.setStat_id(bean.getStat_id());
		List<BaseArea> list = baseAreaMapper.selectBySqlConditions(record);
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

	@Override
	public List<Map<String, Object>> fetchWindowAreaList(BaseAreaSearchBean queryInfo, UserBean user) {
		return baseAreaManagerMapper.fetchWindowAreaList(queryInfo);
	}

	@Override
	public String doForbiddenArea(BaseArea bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		try {
			//查询数据库中时间戳和前台传入的时间戳来验证是否是同一条数据
			if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
					baseAreaMapper.selectByPrimaryKey(bean.getId()).getLast_update_timestamp()))
				return ResultConstants.DATA_CHANGES;
			bean.setEnable_flag("0");
			bean.setLast_update_user(user.getRealname());
			bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
			ret = baseAreaMapper.updateByPrimaryKeySelective(bean);
			BaseGroup baseGroupBean = new BaseGroup();
			baseGroupBean.setEnable_flag("1");
			baseGroupBean.setArea_id(bean.getId());
		    List<BaseGroup> baseGroupList = baseGroupMapper.selectBySqlConditions(baseGroupBean);
			for (BaseGroup baseGroup : baseGroupList) {
				BaseGroup groupBean = new BaseGroup();
				groupBean.setEnable_flag("0");
				groupBean.setId(baseGroup.getId());
				groupBean.setLast_update_user(user.getRealname());
				groupBean.setLast_update_timestamp(DateUtil.getNowTimestamp());
				ret = baseGroupMapper.updateByPrimaryKeySelective(groupBean);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(ResultConstants.ERROR);
		}
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}
}
