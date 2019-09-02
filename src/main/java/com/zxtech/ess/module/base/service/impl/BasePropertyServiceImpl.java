package com.zxtech.ess.module.base.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zxtech.ess.app.util.SysConstants;
import com.zxtech.ess.module.base.bean.BasePropertySearchBean;
import com.zxtech.ess.module.base.mapper.BasePropertyManagerMapper;
import com.zxtech.ess.module.base.service.IBasePropertyService;
import com.zxtech.ess.module.gen.bean.BaseKeyProperty;
import com.zxtech.ess.module.gen.bean.BaseProperty;
import com.zxtech.ess.module.gen.bean.BasePropertyPerson;
import com.zxtech.ess.module.gen.mapper.BaseKeyPropertyMapper;
import com.zxtech.ess.module.gen.mapper.BasePropertyMapper;
import com.zxtech.ess.module.gen.mapper.BasePropertyPersonMapper;
import com.zxtech.ess.module.pub.service.IPubService;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.StringUtil;
import com.zxtech.platform.utils.excel.EasyExcelUtil;
import com.zxtech.platform.utils.page.PageHandler;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

@Service("basePropertyServiceImpl")
public class BasePropertyServiceImpl implements IBasePropertyService {
	
	@Autowired
	private BasePropertyManagerMapper basePropertyManagerMapper;
	@Autowired
	private BasePropertyMapper basePropertyMapper;
	@Autowired
	private BasePropertyPersonMapper basePropertyPersonMapper;
	@Autowired
	private BaseKeyPropertyMapper baseKeyPropertyMapper;
	@Autowired
	private IPubService pubService;
	
	@Override
	public Map<String, Object> propertyListPage(BasePropertySearchBean queryInfo) {
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() -> basePropertyManagerMapper.propertyList(queryInfo));
		return PageHandler.wrapPageMap(pageResult);
	}

	private Boolean checkRepeatEmpCode(Integer id, String property_code) {
		BaseProperty bean = new BaseProperty();
		bean.setProperty_code(property_code);
		bean.setEnable_flag("1");
		List<BaseProperty> list = basePropertyMapper.selectBySqlConditions(bean);
		if( id == null )
			return list.size()>0;
		for(BaseProperty baseProperty : list) {
			if( ! baseProperty.getId().equals(id) )
				return true;
		}
		return false;
	}
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String save(BaseProperty bean, UserBean user) {
		int ret = 0;
		if (StringUtil.isNotBlank(bean.getProperty_code()) && checkRepeatEmpCode(null, bean.getProperty_code()))
			return ResultConstants.REPEAT;
		bean.setCreate_user(user.getRealname());
		bean.setCreate_timestamp(DateUtil.getNowTimestamp());
		bean.setSource_id(pubService.getSequenceOrder());
		bean.setSync_datetime(DateUtil.getNowTimestamp());
		ret = basePropertyMapper.insertSelective(bean);
		if (ret == 0)
			return ResultConstants.ERROR;
		return ResultConstants.SUCCESS;
	}

	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String update(BaseProperty bean, UserBean user) {
		int ret = 0;
		BaseProperty baseProperty = basePropertyMapper.selectByPrimaryKey(bean.getId());
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), baseProperty.getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
		if (StringUtil.isNotBlank(bean.getProperty_code()) && checkRepeatEmpCode(bean.getId(), bean.getProperty_code()))
			return ResultConstants.REPEAT;
		baseProperty.setProperty_name(bean.getProperty_name());
		baseProperty.setKey_property_code(bean.getKey_property_code());
		baseProperty.setProperty_tel(bean.getProperty_tel());
		baseProperty.setProperty_address(bean.getProperty_address());
		baseProperty.setProperty_email(bean.getProperty_email());
		baseProperty.setLast_update_user(user.getRealname());
		baseProperty.setLast_update_timestamp(DateUtil.getNowTimestamp());
		baseProperty.setComp_id(bean.getComp_id());
		ret = basePropertyMapper.updateByPrimaryKey(baseProperty);
		if (ret == 0)
			return ResultConstants.ERROR;
		return ResultConstants.SUCCESS;
	}

	@Override
	public Map<String, Object> propertyPersonListPage(BasePropertySearchBean queryInfo) {
		BasePropertyPerson basePropertyPerson = new BasePropertyPerson();
		basePropertyPerson.setProperty_id(queryInfo.getProperty_id());
		basePropertyPerson.setEnable_flag("1");
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() -> basePropertyPersonMapper.selectBySqlConditions(basePropertyPerson));
		return PageHandler.wrapPageMap(pageResult);
	}
	
	private Boolean checkRepeatPersName(Integer id, String person_name, Integer property_id) {
		BasePropertyPerson bean = new BasePropertyPerson();
		bean.setPerson_name(person_name);
		bean.setProperty_id(property_id);
		List<BasePropertyPerson> list = basePropertyPersonMapper.selectBySqlConditions(bean);
		if( id == null )
			return list.size()>0;
		for(BasePropertyPerson basePropertyPerson : list) {
			if( ! basePropertyPerson.getId().equals(id) )
				return true;
		}
		return false;
	}
	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String doPersionSave(BasePropertyPerson bean, UserBean user) {
		int ret = 0;
		if (StringUtil.isBlank(bean.getPerson_name()) || checkRepeatPersName(null, bean.getPerson_name(), bean.getProperty_id()))
			return ResultConstants.REPEAT;
		bean.setCreate_user(user.getRealname());
		bean.setCreate_timestamp(DateUtil.getNowTimestamp());
		bean.setSource_id(pubService.getSequenceOrder());
		bean.setSync_datetime(DateUtil.getNowTimestamp());
		ret = basePropertyPersonMapper.insertSelective(bean);
		if (ret == 0)
			return ResultConstants.ERROR;
		return ResultConstants.SUCCESS;
	}

	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String doPersionUpdate(BasePropertyPerson bean, UserBean user) {
		int ret = 0;
		BasePropertyPerson basePropertyPerson = basePropertyPersonMapper.selectByPrimaryKey(bean.getId());
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), basePropertyPerson.getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
		if (StringUtil.isBlank(bean.getPerson_name()) || checkRepeatPersName(bean.getId(), bean.getPerson_name(), bean.getProperty_id()))
			return ResultConstants.REPEAT;
		basePropertyPerson.setPerson_name(bean.getPerson_name());
		basePropertyPerson.setPerson_position(bean.getPerson_position());
		basePropertyPerson.setPerson_tel1(bean.getPerson_tel1());
		basePropertyPerson.setPerson_tel2(bean.getPerson_tel2());
		basePropertyPerson.setPerson_email(bean.getPerson_email());
		basePropertyPerson.setLast_update_user(user.getRealname());
		basePropertyPerson.setLast_update_timestamp(DateUtil.getNowTimestamp());
		ret = basePropertyPersonMapper.updateByPrimaryKey(basePropertyPerson);
		if (ret == 0)
			return ResultConstants.ERROR;
		return ResultConstants.SUCCESS;
	}

	@Override
	public ResultBean export(UserBean user, BasePropertySearchBean queryInfo) {
		return EasyExcelUtil.writeExcelMoreSheetMoreWrite(() -> PageHelper.count(() -> basePropertyManagerMapper.getReportList(queryInfo)),
				() -> basePropertyManagerMapper.getReportList(queryInfo), queryInfo);
	}

	@Override
	public List<Map<String, Object>> getKeyPropertyCombobox(String combobox_type, UserBean user, BaseKeyProperty bean) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(combobox_type)) {
			map.put("key_property_code", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_ALL));
			map.put("key_property_name", SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(map);
		}else if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(combobox_type)){
			map.put("key_property_code", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_SELECT));
			map.put("key_property_name", SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(map);
		}
		list.addAll(basePropertyManagerMapper.getKeyPropertyCombobox(bean));
		return list;
	}

	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String delete(BaseProperty bean, UserBean user) {
		int ret = 0;
		BaseProperty baseProperty = basePropertyMapper.selectByPrimaryKey(bean.getId());
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), baseProperty.getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
		bean.setEnable_flag("0");
		bean.setLast_update_user(user.getRealname());
		bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		ret = basePropertyMapper.updateByPrimaryKeySelective(bean);
		BasePropertyPerson deleteBean = new BasePropertyPerson();
		deleteBean.setProperty_id(bean.getId());
		basePropertyPersonMapper.deleteBySqlConditions(deleteBean);
		if (ret == 0)
			return ResultConstants.ERROR;
		return ResultConstants.SUCCESS;
	}

	@Override
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public String doPersionDelete(BasePropertyPerson bean, UserBean user) {
		int ret = 0;
		BasePropertyPerson basePropertyPerson = basePropertyPersonMapper.selectByPrimaryKey(bean.getId());
		if(!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), basePropertyPerson.getLast_update_timestamp()))
			return ResultConstants.DATA_CHANGES;
/*		bean.setEnable_flag("0");
		bean.setLast_update_user(user.getRealname());
		bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		ret = basePropertyPersonMapper.updateByPrimaryKeySelective(bean);*/
		ret = basePropertyPersonMapper.deleteByPrimaryKey(bean.getId());
		if (ret == 0)
			return ResultConstants.ERROR;
		return ResultConstants.SUCCESS;
	}

	@Override
	public List<BaseKeyProperty> keyPropertyList(String combobox_type) {
		List<BaseKeyProperty> list = new ArrayList<BaseKeyProperty>();
		BaseKeyProperty baseKeyProperty = new BaseKeyProperty();
		if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(combobox_type)) {
			baseKeyProperty.setKey_property_code(SysConstants.SYSTEM_SELECT_VALUE_ALL);
			baseKeyProperty.setKey_property_name(SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(baseKeyProperty);
		}else if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(combobox_type)){
			baseKeyProperty.setKey_property_code(SysConstants.SYSTEM_SELECT_VALUE_SELECT);
			baseKeyProperty.setKey_property_name(SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(baseKeyProperty);
		}
		list.addAll(baseKeyPropertyMapper.selectAll());
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getKeyPropertyNotCommonCombobox(String combobox_type, UserBean user) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(combobox_type)) {
			map.put("key_property_code", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_ALL));
			map.put("key_property_name", SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(map);
		}else if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(combobox_type)){
			map.put("key_property_code", Integer.valueOf(SysConstants.SYSTEM_SELECT_VALUE_SELECT));
			map.put("key_property_name", SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(map);
		}
		list.addAll(basePropertyManagerMapper.getKeyPropertyNotCommonCombobox());
		return list;
	}

	@Override
	public String initPropertyCode(UserBean user) {
		// TODO Auto-generated method stub
		return basePropertyManagerMapper.initPropertyCode();
	}
}
