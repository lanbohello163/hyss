package com.zxtech.ess.module.base.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.zxtech.ess.app.util.SysConstants;
import com.zxtech.ess.module.base.bean.BaseDictionarySearchBean;
import com.zxtech.ess.module.base.mapper.BaseDictionaryManagerMapper;
import com.zxtech.ess.module.base.service.IBaseDictionaryService;
import com.zxtech.ess.module.gen.bean.BaseDictionary;
import com.zxtech.ess.module.gen.bean.SysDictionary;
import com.zxtech.ess.module.gen.mapper.BaseDictionaryMapper;
import com.zxtech.ess.module.gen.mapper.SysDictionaryMapper;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.constant.WebConstants;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.StringUtil;
import com.zxtech.platform.utils.page.PageHandler;
import com.zxtech.platform.vo.UserBean;

@Service("baseDictionaryServiceImpl")
public class BaseDictionaryServiceImpl implements IBaseDictionaryService {
	
	@Autowired
	private BaseDictionaryMapper baseDictionaryMapper;
	@Autowired
	private BaseDictionaryManagerMapper baseDictionaryManagerMapper;
	@Autowired
	private SysDictionaryMapper sysDictionaryMapper;

	@Override
	public List<Map<String, Object>> fetchBaseDictionaryComboboxList(BaseDictionarySearchBean queryInfo) {
		return baseDictionaryManagerMapper.fetchBaseDictionaryComboboxList(queryInfo);
	}
	
	@Override
	public Map<String, Object> getListWithPaging(BaseDictionarySearchBean queryInfo) {
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getDict_type())) {
			queryInfo.setDict_type(null);
		}
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() -> baseDictionaryManagerMapper.getListWithPaging(queryInfo));
		return PageHandler.wrapPageMap(pageResult);
	}

	@Override
	public String doAdd(BaseDictionary bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		// check repeat, role_name
		BaseDictionary checkRepeatBean = new BaseDictionary();
		checkRepeatBean.setDict_type(bean.getDict_type());
		checkRepeatBean.setDict_code(bean.getDict_code());
		if (StringUtil.isNotBlank(checkRepeatBean.getDict_type()) && StringUtil.isNotBlank(checkRepeatBean.getDict_code()) && checkRepeatColumn(checkRepeatBean)) {
			return ResultConstants.REPEAT_ITEM1;
		}
		BaseDictionary checkRepeatDictName = new BaseDictionary();
		checkRepeatDictName.setDict_type(bean.getDict_type());
		checkRepeatDictName.setDict_name(bean.getDict_name());
		if (StringUtil.isNotBlank(checkRepeatDictName.getDict_type()) && StringUtil.isNotBlank(checkRepeatDictName.getDict_name()) && checkRepeatDictNameColumn(checkRepeatDictName)) {
			return ResultConstants.REPEAT;
		}
		// insert record
		if("maintenance_factor".equals(bean.getDict_type())) {
			SysDictionary sd = new SysDictionary();
			sd.setDict_name(bean.getDict_name());
			sd.setDict_value(bean.getDict_code());
			sd.setDict_type("maintenance_factor");
			List<SysDictionary> list = sysDictionaryMapper.selectBySqlConditions(sd);
			if(null == list || list.size() ==0) {
				return ResultConstants.USED;
			}
			for (SysDictionary sysDictionary : list) {
				bean.setDict_additional_value(sysDictionary.getDict_remark());
			}			
		}		
		bean.setCreate_user(user.getRealname());
		bean.setCreate_timestamp(DateUtil.getNowTimestamp());
		int ret = baseDictionaryMapper.insertSelective(bean);
		if (ret == 0) {
			resStr = ResultConstants.ERROR;
		}
		return resStr;
	}
	
	private boolean checkRepeatDictNameColumn(BaseDictionary checkRepeatDictName) {
		List<BaseDictionary> list = baseDictionaryMapper.selectBySqlConditions(checkRepeatDictName);
		if (list.size() == 0) {
			return false;
		}
		return true;
	}

	private Boolean checkRepeatColumn(BaseDictionary bean) {
		List<BaseDictionary> list = baseDictionaryMapper.selectBySqlConditions(bean);
		if (list.size() == 0) {
			return false;
		}
		return true;
	}

	@Override
	public String doUpdate(BaseDictionary bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		// check timestamp
		BaseDictionary baseDictionary = baseDictionaryMapper.selectByPrimaryKey(bean.getId());
		if (!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), baseDictionary.getLast_update_timestamp())) {
			return ResultConstants.DATA_CHANGES;
		}
		// check repeat, role_name
		if (StringUtil.isNotBlank(bean.getDict_type()) && StringUtil.isNotBlank(bean.getDict_code()) && !(bean.getDict_type().equals(baseDictionary.getDict_type()) && bean.getDict_code().equals(baseDictionary.getDict_code()))) {
			BaseDictionary checkRepeatBean = new BaseDictionary();
			checkRepeatBean.setDict_type(bean.getDict_type());
			checkRepeatBean.setDict_code(bean.getDict_code());
			if (checkRepeatColumn(checkRepeatBean)) {
				return ResultConstants.REPEAT_ITEM1;
			}
		}
		if (StringUtil.isNotBlank(bean.getDict_type()) && StringUtil.isNotBlank(bean.getDict_name()) && !(bean.getDict_type().equals(baseDictionary.getDict_type()) && bean.getDict_name().equals(baseDictionary.getDict_name()))) {
			BaseDictionary checkRepeatDictName = new BaseDictionary();
			checkRepeatDictName.setDict_type(bean.getDict_type());
			checkRepeatDictName.setDict_name(bean.getDict_name());
			if (checkRepeatColumn(checkRepeatDictName)) {
				return ResultConstants.REPEAT;
			}
		}
		// update record
		if("maintenance_factor".equals(bean.getDict_type())) {
			SysDictionary sd = new SysDictionary();
			sd.setDict_name(bean.getDict_name());
			sd.setDict_value(bean.getDict_code());
			sd.setDict_type("maintenance_factor");
			List<SysDictionary> list = sysDictionaryMapper.selectBySqlConditions(sd);
			if(null == list || list.size() ==0) {
				return ResultConstants.USED;
			}
			for (SysDictionary sysDictionary : list) {
				bean.setDict_additional_value(sysDictionary.getDict_remark());
			}			
		}
		baseDictionary.setDict_type(bean.getDict_type());
		baseDictionary.setDict_type_name(bean.getDict_type_name());
		baseDictionary.setDict_name(bean.getDict_name());
		baseDictionary.setDict_code(bean.getDict_code());
		baseDictionary.setDict_sort(bean.getDict_sort());
		baseDictionary.setP_dict_code(bean.getP_dict_code());
		baseDictionary.setP_dict_type(bean.getP_dict_type());
		baseDictionary.setDict_remark(bean.getDict_remark());
		baseDictionary.setLast_update_user(user.getRealname());
		baseDictionary.setLast_update_timestamp(DateUtil.getNowTimestamp());
		int ret = baseDictionaryMapper.updateByPrimaryKey(baseDictionary);
		if(ret == 0) {
			resStr = ResultConstants.ERROR;
		}
		
		return resStr;
	}

	@Override
	public String doInactive(BaseDictionary bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		// check timestamp
		BaseDictionary baseDictionary = baseDictionaryMapper.selectByPrimaryKey(bean.getId());
		if (!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), baseDictionary.getLast_update_timestamp())) {
			return ResultConstants.DATA_CHANGES;
		}
		
		bean.setEnable_flag("0");
		bean.setLast_update_user(user.getRealname());
		bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		baseDictionaryMapper.updateByPrimaryKeySelective(bean);
		return resStr;
	}
	
	@Override
	public String doActive(BaseDictionary bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		// check timestamp
		BaseDictionary baseDictionary = baseDictionaryMapper.selectByPrimaryKey(bean.getId());
		if (!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), baseDictionary.getLast_update_timestamp())) {
			return ResultConstants.DATA_CHANGES;
		}
		
		bean.setEnable_flag("1");
		bean.setLast_update_user(user.getRealname());
		bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		baseDictionaryMapper.updateByPrimaryKeySelective(bean);
		return resStr;
	}

	@Override
	public BaseDictionary getBaseDictionaryInfoByCode(BaseDictionary bean) {
		return (BaseDictionary) baseDictionaryMapper.selectBySqlConditions(bean).get(0);
	}

	@Override
	public String getChinaListByType(BaseDictionary bean) {

		return baseDictionaryManagerMapper.getChinaListByType(bean);
	}

	@Override
	public List<BaseDictionary> getBaseDictListByType(BaseDictionary queryInfo, String combobox_type) {
		BaseDictionary baseDictionary = new BaseDictionary();
		List<BaseDictionary> list = new ArrayList<BaseDictionary>();
		if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(combobox_type)) {
			baseDictionary.setDict_code(SysConstants.SYSTEM_SELECT_VALUE_ALL);
			baseDictionary.setDict_name(SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(baseDictionary);
		}else if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(combobox_type)){
			baseDictionary.setDict_code(SysConstants.SYSTEM_SELECT_VALUE_SELECT);
			baseDictionary.setDict_name(SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(baseDictionary);
		}
		list.addAll(baseDictionaryMapper.selectBySqlConditions(queryInfo));
		return list;
	}

	@Override
	public Map<String, Object> getContractListWithPaging(BaseDictionarySearchBean queryInfo) {
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getDict_type())) {
			queryInfo.setDict_type(null);
		}
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() -> baseDictionaryManagerMapper.getContractListWithPaging(queryInfo));
		return PageHandler.wrapPageMap(pageResult);
	}

	@Override
	public Map<String, Object> getHotLineListWithPaging(BaseDictionarySearchBean queryInfo) {
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getDict_type())) {
			queryInfo.setDict_type(null);
		}
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() -> baseDictionaryManagerMapper.getHotLineListWithPaging(queryInfo));
		return PageHandler.wrapPageMap(pageResult);
	}
}
