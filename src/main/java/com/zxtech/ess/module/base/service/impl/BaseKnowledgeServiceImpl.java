package com.zxtech.ess.module.base.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.zxtech.ess.app.util.SysConstants;
import com.zxtech.ess.module.base.bean.BaseKnowledgeSearchBean;
import com.zxtech.ess.module.base.mapper.BaseKnowledgeManagerMapper;
import com.zxtech.ess.module.base.service.IBaseKnowledgeService;
import com.zxtech.ess.module.gen.bean.BaseKnowledge;
import com.zxtech.ess.module.gen.mapper.BaseKnowledgeMapper;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.constant.WebConstants;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.StringUtil;
import com.zxtech.platform.utils.page.PageHandler;
import com.zxtech.platform.vo.UserBean;

@Service("baseKnowledgeServiceImpl")
public class BaseKnowledgeServiceImpl implements IBaseKnowledgeService {
	
	@Autowired
	private BaseKnowledgeMapper baseKnowledgeMapper;
	@Autowired
	private BaseKnowledgeManagerMapper baseKnowledgeManagerMapper;
	
	@Override
	public Map<String, Object> getListWithPaging(BaseKnowledgeSearchBean queryInfo, UserBean user) {
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getKnowledge_question_category())) {
			queryInfo.setKnowledge_question_category(null);
		}
		if(WebConstants.SELECT_VALUE_ALL.equals(queryInfo.getKnowledge_question_child_category())) {
			queryInfo.setKnowledge_question_child_category(null);
		}
		if(user.getIsHeadOffice()) {
			Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
					.doSelectPage(() -> baseKnowledgeManagerMapper.getListWithPaging(queryInfo));
			return PageHandler.wrapPageMap(pageResult);
		}else {
			Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
					.doSelectPage(() -> baseKnowledgeManagerMapper.getListWithPagingPart(queryInfo));
			return PageHandler.wrapPageMap(pageResult);
		}

		
	}
	
	@Override
	@Transactional(rollbackFor = { RuntimeException.class, SQLException.class })
	public String doAdd(BaseKnowledge bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		// check repeat, role_name
		BaseKnowledge checkRepeatBean = new BaseKnowledge();
		checkRepeatBean.setKnowledge_name(bean.getKnowledge_name());
		checkRepeatBean.setEnable_flag(SysConstants.DEFAULT_ENABLE_FLAG);
		if (StringUtil.isNotBlank(checkRepeatBean.getKnowledge_name()) && checkRepeatColumn(checkRepeatBean)) {
			return ResultConstants.REPEAT_ITEM1;
		}
		// insert record
		bean.setCreate_user(user.getRealname());
		bean.setCreate_timestamp(DateUtil.getNowTimestamp());
		int ret = baseKnowledgeMapper.insertSelective(bean);
		if(ret == 0) {
			resStr = ResultConstants.ERROR;
		}
		return resStr;
	}
	
	private Boolean checkRepeatColumn(BaseKnowledge bean) {
		List<BaseKnowledge> list = baseKnowledgeMapper.selectBySqlConditions(bean);
		if (list.size() == 0) {
			return false;
		}
		return true;
	}
	
	@Override
	@Transactional(rollbackFor = { RuntimeException.class, SQLException.class })
	public String doUpdate(BaseKnowledge bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		// check timestamp
		BaseKnowledge baseKnowledge = baseKnowledgeMapper.selectByPrimaryKey(bean.getId());
		if (!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), baseKnowledge.getLast_update_timestamp())) {
			return ResultConstants.DATA_CHANGES;
		}
		// check repeat, role_name
		if (StringUtil.isNotBlank(bean.getKnowledge_name()) && !bean.getKnowledge_name().equals(baseKnowledge.getKnowledge_name())) {
			BaseKnowledge checkRepeatBean = new BaseKnowledge();
			checkRepeatBean.setKnowledge_name(bean.getKnowledge_name());
			checkRepeatBean.setEnable_flag("1");
			if (checkRepeatColumn(checkRepeatBean)) {
				return ResultConstants.REPEAT_ITEM1;
			}
		}
		// update record
		baseKnowledge.setKnowledge_name(bean.getKnowledge_name());
		baseKnowledge.setKnowledge_question_category(bean.getKnowledge_question_category());
		baseKnowledge.setKnowledge_question_child_category(bean.getKnowledge_question_child_category());
		baseKnowledge.setKnowledge_question_third_category(bean.getKnowledge_question_third_category());
		baseKnowledge.setIs_comp_seen(bean.getIs_comp_seen());
		baseKnowledge.setQuestion_desc(bean.getQuestion_desc());
		baseKnowledge.setUse_scope(bean.getUse_scope());
		baseKnowledge.setSolve_desc(bean.getSolve_desc());
		baseKnowledge.setLast_update_user(user.getRealname());
		baseKnowledge.setLast_update_timestamp(DateUtil.getNowTimestamp());
		int ret = baseKnowledgeMapper.updateByPrimaryKey(baseKnowledge);
		if(ret == 0) {
			resStr = ResultConstants.ERROR;
		}
		
		return resStr;
	}
	
	@Override
	@Transactional(rollbackFor = { RuntimeException.class, SQLException.class })
	public String doDelete(BaseKnowledge bean, UserBean user) {
		String resStr = ResultConstants.SUCCESS;
		// check timestamp
		BaseKnowledge baseKnowledge = baseKnowledgeMapper.selectByPrimaryKey(bean.getId());
		if (!DateUtil.equalsTimestamp(bean.getLast_update_timestamp(), baseKnowledge.getLast_update_timestamp())) {
			return ResultConstants.DATA_CHANGES;
		}
		
		bean.setEnable_flag("0");
		bean.setLast_update_user(user.getRealname());
		bean.setLast_update_timestamp(DateUtil.getNowTimestamp());
		baseKnowledgeMapper.updateByPrimaryKeySelective(bean);
		return resStr;
	}
}
