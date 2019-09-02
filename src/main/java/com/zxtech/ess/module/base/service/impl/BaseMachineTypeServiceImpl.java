package com.zxtech.ess.module.base.service.impl;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zxtech.ess.module.base.bean.BaseMachineTypeSearchBean;
import com.zxtech.ess.module.base.mapper.BaseMachineTypeManagerMapper;
import com.zxtech.ess.module.base.service.IBaseMachineTypeService;
import com.zxtech.ess.module.gen.bean.BaseMachineType;
import com.zxtech.ess.module.gen.mapper.BaseMachineTypeMapper;
import com.zxtech.ess.module.pub.service.IPubService;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.constant.WebConstants;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.PoiImportExcelUtil;
import com.zxtech.platform.utils.StringUtil;
import com.zxtech.platform.utils.excel.EasyExcelUtil;
import com.zxtech.platform.utils.page.PageHandler;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;


@Service("baseMachineTypeServiceImpl")
public class BaseMachineTypeServiceImpl implements IBaseMachineTypeService {
	
	@Autowired
	private BaseMachineTypeManagerMapper baseMachineTypeManagerMapper;
	@Autowired
	private IPubService pubService;
	@Autowired
	private BaseMachineTypeMapper baseMachineTypeMapper;
	
	private static GsonBuilder gsonBulder = new GsonBuilder();
	
	@Override
	public List<Map<String, Object>> fetchBaseMachineTypeComboboxList(BaseMachineTypeSearchBean queryInfo) {
		if(queryInfo.getP_type_id() ==null ||queryInfo.getP_type_id().isEmpty()) {
			queryInfo.setpTypeSign("1");
		}
		return baseMachineTypeManagerMapper.fetchBaseMachineTypeComboboxList(queryInfo);
	}
	
	@Override
	public Map<String, Object> getListWithPaging(BaseMachineTypeSearchBean queryInfo) {
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getMachine_type())) {
			queryInfo.setMachine_type(null);
		}
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getEnable_flag())) {
			queryInfo.setEnable_flag(null);;
		}
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() -> baseMachineTypeManagerMapper.getListWithPaging(queryInfo));
		return PageHandler.wrapPageMap(pageResult);
	}

	@Override
	public String doAdd(BaseMachineType bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		// check repeat
		BaseMachineType baseMachineTypeBean = new BaseMachineType();
		
		baseMachineTypeBean.setType_code(bean.getType_code());
		baseMachineTypeBean.setEle_category(bean.getEle_category());
		if (StringUtil.isNotBlank(baseMachineTypeBean.getEle_category()) && StringUtil.isNotBlank(baseMachineTypeBean.getType_code()) && checkRepeatColumn(baseMachineTypeBean)) {
			return ResultConstants.REPEAT;
		}
		baseMachineTypeBean.setMachine_type(bean.getMachine_type());
		baseMachineTypeBean.setType_id(pubService.getSequenceOrder());
		baseMachineTypeBean.setType_desc(bean.getType_desc());
		
		baseMachineTypeBean.setP_type_id(bean.getP_type_id());
		
		baseMachineTypeBean.setCreate_user(user.getRealname());
		baseMachineTypeBean.setLast_update_user(user.getRealname());
		baseMachineTypeBean.setCreate_timestamp(DateUtil.getNowTimestamp());
		baseMachineTypeBean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		int ret = baseMachineTypeMapper.insertSelective(baseMachineTypeBean);
		if (ret == 0) {
			resStr = ResultConstants.ERROR;
		}
		return resStr;
	}
	
	private Boolean checkRepeatColumn(BaseMachineType bean) {
		List<BaseMachineType> list = baseMachineTypeMapper.selectBySqlConditions(bean);
		if (list.size() == 0) {
			return false;
		}
		return true;
	}

	@Override
	public String doUpdate(BaseMachineType bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		// check timestamp
		BaseMachineType baseMachineType = baseMachineTypeMapper.selectByPrimaryKey(bean.getType_id());
		if (!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), baseMachineType.getLast_update_timestamp())) {
			return ResultConstants.DATA_CHANGES;
		}
		// check repeat, role_name
	
		if (StringUtil.isNotBlank(bean.getEle_category()) && StringUtil.isNotBlank(bean.getType_code()) && (!bean.getEle_category().equals(baseMachineType.getEle_category()) || !bean.getType_code().equals(baseMachineType.getType_code()))) {
			BaseMachineType baseMachineTypeBean = new BaseMachineType();
			baseMachineTypeBean.setEle_category(bean.getEle_category());
			baseMachineTypeBean.setType_code(bean.getType_code());
			if (checkRepeatColumn(baseMachineTypeBean)) {
				return ResultConstants.REPEAT;
			}
		}
		baseMachineType.setMachine_type(bean.getMachine_type());
		baseMachineType.setType_code(bean.getType_code());
		baseMachineType.setType_desc(bean.getType_desc());
		baseMachineType.setEle_category(bean.getEle_category());
		baseMachineType.setP_type_id(bean.getP_type_id());
		
		baseMachineType.setLast_update_user(user.getRealname());
		baseMachineType.setLast_update_timestamp(DateUtil.getNowTimestamp());
		int ret = baseMachineTypeMapper.updateByPrimaryKey(baseMachineType);
		if(ret == 0) {
			resStr = ResultConstants.ERROR;
		}
		
		return resStr;
	}

	@Override
	public String doActive(BaseMachineType bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		// check timestamp
		BaseMachineType baseMachineType = baseMachineTypeMapper.selectByPrimaryKey(bean.getType_id());
		if (!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), baseMachineType.getLast_update_timestamp())) {
			return ResultConstants.DATA_CHANGES;
		}
		
		bean.setEnable_flag("1");
		bean.setLast_update_user(user.getRealname());
		bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		baseMachineTypeMapper.updateByPrimaryKeySelective(bean);
		return resStr;
	}

	@Override
	public String doInactive(BaseMachineType bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		// check timestamp
		BaseMachineType baseMachineType = baseMachineTypeMapper.selectByPrimaryKey(bean.getType_id());
		if (!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), baseMachineType.getLast_update_timestamp())) {
			return ResultConstants.DATA_CHANGES;
		}
		
		bean.setEnable_flag("0");
		bean.setLast_update_user(user.getRealname());
		bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		baseMachineTypeMapper.updateByPrimaryKeySelective(bean);
		return resStr;
	}

	@Override
	public ResultBean export(HttpServletRequest request, HttpServletResponse response, UserBean user,
			BaseMachineTypeSearchBean queryInfo) {
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getMachine_type())) {
			queryInfo.setMachine_type(null);
		}
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getEnable_flag())) {
			queryInfo.setEnable_flag(null);;
		}
		
		return EasyExcelUtil.writeExcelMoreSheetMoreWrite(() -> baseMachineTypeManagerMapper.baseMachineTypeExportListCount(queryInfo),
				() -> baseMachineTypeManagerMapper.baseMachineTypeExportList(queryInfo), queryInfo);
	}

	@Override
	public Map<String, Object> importCheck(MultipartFile mf, UserBean user) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("return_code", "success");
		long fileSize = mf.getSize();
		if (fileSize > 50 * 1024 * 1024) {
			map.put("return_code", "maxsizeerror");
			return map;
		}

		List<List<Object>> listob = new PoiImportExcelUtil().getBankListByExcel(mf.getInputStream(),
				mf.getOriginalFilename());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		if (listob == null || listob.size() == 1) {// 空白模板
			map.put("return_code", "empty_template");
			return map;
		}

		for (int i = 1; i < listob.size(); i++) {
			if (listob.get(0).size() != 5) {// 模板列数
				map.put("return_code", "formaterror");
				return map;
			}
			List<Object> lo = listob.get(i);
			if (StringUtil.isObjectBlank(lo.get(0)) || StringUtil.isObjectBlank(lo.get(1))
					|| StringUtil.isObjectBlank(lo.get(2)) || StringUtil.isObjectBlank(lo.get(4))) {
				map.put("return_code", "requiredfield");
				return map;
			} else {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("ele_category_name", StringUtil.toSafeString(lo.get(0)));
				dataMap.put("type_code", StringUtil.toSafeString(lo.get(1)));
				dataMap.put("type_desc", StringUtil.toSafeString(lo.get(2)));
				dataMap.put("p_type_code", StringUtil.toSafeString(lo.get(3)));
				dataMap.put("enable_flag_name", StringUtil.toSafeString(lo.get(4)));

				list.add(dataMap);
			}
		}
		map.put("data", list);
		return map;
	}

	@Override
	public Map<String, Object> importData(String paramData, UserBean user) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Map<String, Object>> paramList = gsonBulder.create().fromJson(paramData,
				new TypeToken<List<Map<String, Object>>>() {
				}.getType());
		
		for(Map<String, Object> param:paramList) {
			BaseMachineType bean = new BaseMachineType();
			if("扶梯".equals(StringUtil.toSafeString(param.get("ele_category_name")))) {
				bean.setEle_category("2");
			}else {
				bean.setEle_category("1");
			}
			
			if(StringUtils.isNotEmpty(StringUtil.toSafeString(param.get("p_type_code")))) {
				BaseMachineType queryBean = new BaseMachineType();
				queryBean.setType_code(StringUtil.toSafeString(param.get("p_type_code")));
				queryBean.setEle_category(bean.getEle_category());
				List<BaseMachineType>  rs = baseMachineTypeMapper.selectBySqlConditions(queryBean);
				if(rs != null && rs.size() > 0) {
					bean.setP_type_id(rs.get(0).getType_id());
					bean.setMachine_type(Integer.parseInt(rs.get(0).getMachine_type())+1+"");
					
					if(Integer.parseInt(bean.getMachine_type()) < 4) {
						BaseMachineType checkRepeatBean = new BaseMachineType();
						checkRepeatBean.setMachine_type(bean.getMachine_type());
						checkRepeatBean.setType_code(StringUtil.toSafeString(param.get("type_code")));
						checkRepeatBean.setEle_category(bean.getEle_category());
						if(checkRepeatColumn(checkRepeatBean)) {
							map.put("return_code", "repeat");
							map.put("return_data", "故障分类编码"+StringUtil.toSafeString(param.get("type_code"))+",故障分类描述"+StringUtil.toSafeString(param.get("type_desc"))+"已存在");
							return map;
						}
						
					}else {
						map.put("return_code", "p_type_invalid");
						map.put("return_data", "故障分类编码"+StringUtil.toSafeString(param.get("type_code"))+",故障分类描述"+StringUtil.toSafeString(param.get("type_desc"))+"父分类编码无效");
						return map;
					}
				}else {
					map.put("return_code", "p_type_invalid");
					map.put("return_data", "故障分类编码"+StringUtil.toSafeString(param.get("type_code"))+",故障分类描述"+StringUtil.toSafeString(param.get("type_desc"))+"父分类编码无效");
					return map;
				}
				
			}else {
				BaseMachineType checkRepeatBean = new BaseMachineType();
				checkRepeatBean.setMachine_type("1");
				checkRepeatBean.setType_code(StringUtil.toSafeString(param.get("type_code")));
				checkRepeatBean.setEle_category(bean.getEle_category());
				if(checkRepeatColumn(checkRepeatBean)) {
					map.put("return_code", "repeat");
					map.put("return_data", "故障分类编码"+StringUtil.toSafeString(param.get("type_code"))+",故障分类描述"+StringUtil.toSafeString(param.get("type_desc"))+"已存在");
					return map;
				}
				bean.setMachine_type("1");
			}

			bean.setType_code(StringUtil.toSafeString(param.get("type_code")));
			bean.setType_desc(StringUtil.toSafeString(param.get("type_desc")));
			if("禁用".equals(StringUtil.toSafeString(param.get("enable_flag_name")))) {
				bean.setEnable_flag("0");
			}else {
				bean.setEnable_flag("1");
			}
			bean.setType_id(pubService.getSequenceOrder());
			bean.setCreate_user(user.getRealname());
			bean.setLast_update_user(user.getRealname());
			bean.setCreate_timestamp(DateUtil.getNowTimestamp());
			bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
			baseMachineTypeMapper.insert(bean);
		}
		map.put("return_code", ResultConstants.SUCCESS);
		return map;
	}
	

	
}
