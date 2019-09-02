package com.zxtech.ess.module.base.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zxtech.ess.module.base.bean.BaseProjectSearchBean;
import com.zxtech.ess.module.base.mapper.BaseProjectManagerMapper;
import com.zxtech.ess.module.base.service.IBaseProjectService;
import com.zxtech.ess.module.gen.bean.BaseElevator;
import com.zxtech.ess.module.gen.bean.BaseProject;
import com.zxtech.ess.module.gen.mapper.BaseElevatorMapper;
import com.zxtech.ess.module.gen.mapper.BaseProjectMapper;
import com.zxtech.ess.module.pub.service.IPubService;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.constant.WebConstants;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.StringUtil;
import com.zxtech.platform.utils.excel.EasyExcelUtil;
import com.zxtech.platform.utils.page.PageHandler;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

@Service("baseProjectServiceImpl")
public class BaseProjectServiceImpl implements IBaseProjectService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseProjectServiceImpl.class);
	
	@Autowired
	private BaseProjectManagerMapper baseProjectManagerMapper;
	@Autowired
	private BaseProjectMapper baseProjectMapper;
	@Autowired
	private BaseElevatorMapper baseElevatorMapper;
	@Autowired
	private IPubService pubService;

	@Override
	public Map<String, Object> baseProjectPagingList(BaseProjectSearchBean bean) {
		if(WebConstants.SELECT_VALUE_ALL.equals(String.valueOf(bean.getComp_id()))) {
			bean.setComp_id(null);
		}
		if(WebConstants.SELECT_VALUE_ALL.equals(String.valueOf(bean.getStat_id()))) {
			bean.setStat_id(null);
		}
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(bean)
				.doSelectPage(() -> baseProjectManagerMapper.baseProjectPagingList(bean));
		return PageHandler.wrapPageMap(pageResult);
	}

	@Override
	public String save(BaseProject bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		if(StringUtil.isNotBlank(bean.getProj_name()) && checkRepeatColumn(bean)) {
			return ResultConstants.REPEAT;
		}
		bean.setCreate_user(user.getRealname());
		bean.setCreate_timestamp(DateUtil.getNowTimestamp());
		bean.setSource_id(bean.getProj_code());
		bean.setSync_datetime(DateUtil.getNowTimestamp());
		ret = baseProjectMapper.insertSelective(bean);
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}



	@Override
	public String update(BaseProject bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		BaseProject dbBean = baseProjectMapper.selectByPrimaryKey(bean.getId());
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
				dbBean.getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
		if(StringUtil.isNotBlank(bean.getProj_name()) && checkRepeatColumn(bean)) {
			return ResultConstants.REPEAT;
		}
		dbBean.setComp_id(bean.getComp_id());
		dbBean.setStat_id(bean.getStat_id());
		dbBean.setProj_name(bean.getProj_name());
		dbBean.setProj_address(bean.getProj_address());
		dbBean.setProj_state(bean.getProj_state());
		dbBean.setProj_province(bean.getProj_province());
		dbBean.setProj_city(bean.getProj_city());
		dbBean.setProj_district(bean.getProj_district());
		dbBean.setLast_update_user(user.getRealname());
		dbBean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		dbBean.setRegion_state_name(bean.getRegion_state_name());
		dbBean.setRegion_province_name(bean.getRegion_province_name());
		dbBean.setRegion_city_name(bean.getRegion_city_name());
		dbBean.setRegion_district_name(bean.getRegion_district_name());
		ret = baseProjectMapper.updateByPrimaryKey(dbBean);
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	public String disable(BaseProject bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		try {
			//查询数据库中时间戳和前台传入的时间戳来验证是否是同一条数据
			if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
					baseProjectMapper.selectByPrimaryKey(bean.getId()).getLast_update_timestamp()))
				return ResultConstants.DATA_CHANGES;
			
			BaseElevator baseElaBean = new BaseElevator();
			baseElaBean.setProj_id(bean.getId());
			baseElaBean.setEnable_flag("1");
			List<BaseElevator> baseElaList = baseElevatorMapper.selectBySqlConditions(baseElaBean);
			if(!CollectionUtils.isEmpty(baseElaList))
				return ResultConstants.USED;
			
			bean.setEnable_flag("0");
			bean.setLast_update_user(user.getRealname());
			bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
			ret = baseProjectMapper.updateByPrimaryKeySelective(bean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(ResultConstants.ERROR);
		}
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	public String able(BaseProject bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		int ret = 0;
		try {
			//查询数据库中时间戳和前台传入的时间戳来验证是否是同一条数据
			if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), 
					baseProjectMapper.selectByPrimaryKey(bean.getId()).getLast_update_timestamp()))
				return ResultConstants.DATA_CHANGES;
			bean.setEnable_flag("1");
			bean.setLast_update_user(user.getRealname());
			bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
			ret = baseProjectMapper.updateByPrimaryKeySelective(bean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(ResultConstants.ERROR);
		}
		if (ret == 0)
			resStr = ResultConstants.ERROR;
		return resStr;
	}

	@Override
	public ResultBean export(UserBean user, BaseProjectSearchBean queryInfo) {
		String fileName = null;
		if(WebConstants.SELECT_VALUE_ALL.equals(String.valueOf(queryInfo.getComp_id()))) {
			queryInfo.setComp_id(null);
		}
		if(WebConstants.SELECT_VALUE_ALL.equals(String.valueOf(queryInfo.getStat_id()))) {
			queryInfo.setStat_id(null);
		}
		queryInfo.setDataPermissionList(user);
		
		return EasyExcelUtil.writeExcelMoreSheetMoreWrite(() -> PageHelper.count(() -> baseProjectManagerMapper.getReportList(queryInfo)),
				() -> baseProjectManagerMapper.getReportList(queryInfo), queryInfo);
	}
	
	private Boolean checkRepeatColumn(BaseProject bean){
		BaseProject record = new BaseProject();
		record.setProj_name(bean.getProj_name());
		record.setStat_id(bean.getStat_id());
		List<BaseProject> list = baseProjectMapper.selectBySqlConditions(record);
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
	private Boolean checkRepeatProjCode(Integer id, String proj_code) {
		// TODO Auto-generated method stub
		BaseProject record = new BaseProject();
		record.setProj_code(proj_code);
		List<BaseProject> list = baseProjectMapper.selectBySqlConditions(record);
		if( id == null )
			return list.size()>0;
		for(BaseProject baseProject : list) {
			if( ! baseProject.getId().equals(id) )
				return true;
		}
		return false;
	}

}
