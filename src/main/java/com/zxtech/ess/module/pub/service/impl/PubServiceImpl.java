package com.zxtech.ess.module.pub.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zxtech.ess.app.util.SysConstants;
import com.zxtech.ess.module.base.bean.BaseElevatorSearchBean;
import com.zxtech.ess.module.gen.bean.BaseEmployee;
import com.zxtech.ess.module.gen.bean.BaseMachineType;
import com.zxtech.ess.module.gen.bean.LogData;
import com.zxtech.ess.module.gen.bean.LogDataDtl;
import com.zxtech.ess.module.gen.bean.QueryDefinition;
import com.zxtech.ess.module.gen.bean.QueryItem;
import com.zxtech.ess.module.gen.bean.SysRole;
import com.zxtech.ess.module.gen.mapper.LogDataDtlMapper;
import com.zxtech.ess.module.gen.mapper.LogDataMapper;
import com.zxtech.ess.module.gen.mapper.QueryDefinitionMapper;
import com.zxtech.ess.module.gen.mapper.QueryItemMapper;
import com.zxtech.ess.module.pub.bean.PubSearchBean;
import com.zxtech.ess.module.pub.mapper.PubMapper;
import com.zxtech.ess.module.pub.service.IPubService;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.constant.WebConstants;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.HexadecimalConversionUtil;
import com.zxtech.platform.utils.PoiImportExcelUtil;
import com.zxtech.platform.utils.StringUtil;
import com.zxtech.platform.utils.cache.CacheTemplate;
import com.zxtech.platform.utils.excel.EasyExcelUtil;
import com.zxtech.platform.utils.page.PageHandler;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

@Service("pubService")
public class PubServiceImpl implements IPubService {

	@Autowired
	private PubMapper pubMapper;
	@Autowired
	private QueryDefinitionMapper queryDefinitionMapper;
	@Autowired
	private QueryItemMapper queryItemMapper;
	@Autowired
	private LogDataMapper logDataMapper;
	@Autowired
	private LogDataDtlMapper logDataDtlMapper;

	@Override
	public List<SysRole> fetchPublicSysRole() {
		return pubMapper.fetchPublicSysRole();
	}

	@Override
	public List<Map<String, Object>> getListByCompany(UserBean user, String combobox_type) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> listByCompany = pubMapper.getListByCompany(user);
		if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_ALL.equals(combobox_type)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", SysConstants.SYSTEM_SELECT_VALUE_ALL);
			map.put("comp_name", SysConstants.SYSTEM_SELECT_TEXT_ALL);
			//map.put("comp_group", SysConstants.SYSTEM_SELECT_TEXT_ALL);
			list.add(map);
		}
		if(SysConstants.SYSTEM_SELECT_COMBOBOX_TYPE_SELECT.equals(combobox_type)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", SysConstants.SYSTEM_SELECT_VALUE_SELECT);
			map.put("comp_name", SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			//map.put("comp_group", SysConstants.SYSTEM_SELECT_TEXT_SELECT);
			list.add(map);
		}
		list.addAll(listByCompany);
		return list;
	}

	@Override
	public String getSequenceOrder() {
		String sequenceOrder = pubMapper.getSequenceOrder();
		return HexadecimalConversionUtil.numericToNHexString(Long.valueOf(sequenceOrder), 32);
	}

	@Override
	public Map<String, Object> getCommonProjectList(PubSearchBean queryInfo, UserBean user) {
		if (WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getComp_id())) {
			queryInfo.setComp_id(null);
		}
		if (WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getStat_id())) {
			queryInfo.setStat_id(null);
		}

		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() -> pubMapper.getCommonProjectList(queryInfo));
		return PageHandler.wrapPageMap(pageResult);
	}

	@Override
	public List<Map<String, Object>> fetchQueryHelperComboboxList(String query_url, String empCode) {
		return pubMapper.fetchQueryHelperComboboxList(query_url, empCode);
	}

	@Override
	public List<QueryItem> fetchQueryHelperItems(String query_url) {
		QueryItem queryInfo = new QueryItem();
		queryInfo.setQuery_url(query_url);
		queryInfo.setEnable_flag("1");
		return queryItemMapper.selectBySqlConditions(queryInfo);
	}

	@Override
	public String saveQueryDefinition(QueryDefinition bean, UserBean user) {
		bean.setOwner_code(user.getEmpCode());
		// check repeat, query_url:query_name:owner_code:is_shared
		QueryDefinition checkRepeatBean = new QueryDefinition();
		checkRepeatBean.setQuery_url(bean.getQuery_url());
		checkRepeatBean.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
		if ("1".equals(bean.getIs_shared())) {
			bean.setQuery_name(SysConstants.HEAD_OFFICE_SHARED + bean.getQuery_name());
		} else {
			if (bean.getQuery_name().indexOf(SysConstants.HEAD_OFFICE_SHARED) >= 0) {
				return ResultConstants.USED;
			}
			checkRepeatBean.setOwner_code(bean.getOwner_code());
		}
		checkRepeatBean.setQuery_name(bean.getQuery_name());
		if (StringUtil.isNotBlank(checkRepeatBean.getQuery_name())
				&& StringUtil.isNotBlank(checkRepeatBean.getQuery_url()) && checkRepeatColumn(checkRepeatBean)) {
			return ResultConstants.REPEAT_ITEM1;
		}
		bean.setCreate_user(user.getRealname());
		bean.setCreate_timestamp(DateUtil.getNowTimestamp());
		queryDefinitionMapper.insertSelective(bean);
		return ResultConstants.SUCCESS;
	}

	private Boolean checkRepeatColumn(QueryDefinition bean) {
		List<QueryDefinition> list = queryDefinitionMapper.selectBySqlConditions(bean);
		if (list.size() == 0) {
			return false;
		}
		return true;
	}

	@Override
	public String deleteQueryDefinition(QueryDefinition bean, UserBean user) {
		if (bean.getId() == null) {
			return ResultConstants.ERROR;
		}
		QueryDefinition checkBean = queryDefinitionMapper.selectByPrimaryKey(bean.getId());
		if (user.getEmpCode() != null && user.getEmpCode().equals(checkBean.getOwner_code())) {
			bean.setEnable_flag(SysConstants.UNENABLE_FLAG);
			bean.setLast_update_user(user.getRealname());
			bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
			queryDefinitionMapper.updateByPrimaryKeySelective(bean);
		} else {
			return ResultConstants.NO_PERMISSION;
		}
		return ResultConstants.SUCCESS;
	}

	@Override
	public void saveLogData(UserBean user, String opt_behavior) {
		LogData logData = new LogData();
		logData.setOpt_code(user.getEmpCode());
		logData.setOpt_person(user.getRealname());
		logData.setOpt_datetime(DateUtil.getNowTimestamp());
		logData.setOpt_behavior(opt_behavior);
		logDataMapper.insertSelective(logData);
	}

	@Override
	public void saveLogData(UserBean user, String opt_behavior, String method_url, Integer rec_id) {
		LogDataDtl logDataDtl = new LogDataDtl();
		logDataDtl.setOpt_code(user.getEmpCode());
		logDataDtl.setOpt_person(user.getRealname());
		logDataDtl.setOpt_datetime(DateUtil.getNowTimestamp());
		logDataDtl.setOpt_behavior(opt_behavior);
		logDataDtl.setMethod_url(method_url);
		logDataDtl.setRec_id(rec_id);
		logDataDtlMapper.insertSelective(logDataDtl);
	}

	@Override
	public Map<String, Object> importCheck(MultipartFile mf, UserBean user) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "success");
		long fileSize = mf.getSize();
		if (fileSize > 50 * 1024 * 1024) {
			map.put("status", "maxsizeerror");
			return map;
		}
		List<List<Object>> listob = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		listob = new PoiImportExcelUtil().getBankListByExcel(mf.getInputStream(), mf.getOriginalFilename());

		if (CollectionUtils.isNotEmpty(listob)) {
			Map<String, Object> mapData = null;
			for (int i = 1; i < listob.size(); i++) {
				List<Object> lo = listob.get(i);

				if ("".equals(StringUtil.toSafeString(lo.get(0)))
						|| "".equals(StringUtil.toSafeString(1 < lo.size() ? lo.get(1) : ""))) {
					map.put("status", "formaterror");
					return map;
				} else {
					int j = 0;
					mapData = new HashMap<>();

					for (int k = 0; k < listob.get(0).size(); k++) {
						mapData.put("item" + (k + 1), k < lo.size() ? lo.get(j) : null);
						++j;
					}
					list.add(mapData);
				}
			}
		}
		map.put("data", list);
		return map;
	}

	@Override
	public List<Map<String, Object>> getHotlineUserList() {
		BaseEmployee baseEmployee = new BaseEmployee();
		baseEmployee.setType_hotline("1");
		return pubMapper.getHotlineUserList(baseEmployee);
	}

	@Override
	public List<BaseMachineType> fetchPublicMachineType(BaseMachineType bean) {
		return pubMapper.fetchPublicMachineType(bean);
	}

	@Override
	public Map<String, Object> getCommonElevatorPagingList(BaseElevatorSearchBean queryInfo) {
		if(SysConstants.SYSTEM_SELECT_VALUE_ALL.equals(queryInfo.getComp_id())) {
			queryInfo.setComp_id(null);
		}
		if(SysConstants.SYSTEM_SELECT_VALUE_ALL.equals(queryInfo.getStat_id())) {
			queryInfo.setStat_id(null);
		}
		if(SysConstants.SYSTEM_SELECT_VALUE_ALL.equals(queryInfo.getEle_category())) {
			queryInfo.setEle_category(null);
		}
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() -> pubMapper.getCommonElevatorPagingList(queryInfo));
		return PageHandler.wrapPageMap(pageResult);
	}

	@Override
	public ResultBean fetchExportExcelSchedule(String guid) {
		return CacheTemplate.REDIS.execute((RedisConnection conn) -> {
			byte[] excelBytes = conn.get((EasyExcelUtil.EXPORT_GUID_HEAD+guid).getBytes());
			ResultBean resultBean = new ResultBean();
			if (excelBytes != null) {
				resultBean = new Gson().fromJson(new String(excelBytes, StandardCharsets.UTF_8), new TypeToken<ResultBean>() {}.getType());
			}else {
				resultBean.setStatus(ResultConstants.ERROR_CODE);
				resultBean.setMsg(ResultConstants.ERROR_NAME);
			}
			return resultBean;
		});
	}
}
