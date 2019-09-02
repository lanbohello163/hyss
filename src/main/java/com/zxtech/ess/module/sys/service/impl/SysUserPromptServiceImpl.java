package com.zxtech.ess.module.sys.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.zxtech.ess.module.gen.bean.SysUserPromptRel;
import com.zxtech.ess.module.gen.mapper.SysUserPromptRelMapper;
import com.zxtech.ess.module.sys.bean.SysUserPromptSearchBean;
import com.zxtech.ess.module.sys.mapper.SysUserPromptManagerMapper;
import com.zxtech.ess.module.sys.service.ISysUserPromptService;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.utils.page.PageHandler;
import com.zxtech.platform.vo.UserBean;

@Service("sysUserPromptService")
public class SysUserPromptServiceImpl implements ISysUserPromptService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SysUserPromptServiceImpl.class);
	
	@Autowired
	private SysUserPromptManagerMapper sysUserPromptManagerMapper;
	@Autowired
	private SysUserPromptRelMapper sysUserPromptRelMapper;
	
	@Override
	public Map<String, Object> getListWithPaging(UserBean user, SysUserPromptSearchBean queryInfo) {
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() -> sysUserPromptManagerMapper.getListWithPaging(queryInfo));
		return PageHandler.wrapPageMap(pageResult);
	}

	@Override
	@Transactional(rollbackFor = { RuntimeException.class, SQLException.class })
	public String update(UserBean user, SysUserPromptSearchBean queryInfo) {
		String result = ResultConstants.SUCCESS;
		try {
			SysUserPromptRel sysUserPromptRel = null;
			if(queryInfo.getId() != null) {
				sysUserPromptRel = new SysUserPromptRel();
				sysUserPromptRel.setPrompt_id(queryInfo.getId());
				sysUserPromptRel.setUser_id(user.getUserId());
				
				List<SysUserPromptRel> list = sysUserPromptRelMapper.selectBySqlConditions(sysUserPromptRel);
				if(CollectionUtils.isEmpty(list)) {
					sysUserPromptRelMapper.insertSelective(sysUserPromptRel);
				}
			}else {//全部
				if("0".equals(queryInfo.getPrompt_type())) {
					queryInfo.setPrompt_type(null);
				}
				List<Map<String, Object>> listWithPaging = sysUserPromptManagerMapper.getListWithPaging(queryInfo);
				
				if(CollectionUtils.isNotEmpty(listWithPaging)) {
					for (Map<String, Object> map: listWithPaging) {
						sysUserPromptRel = new SysUserPromptRel();
						sysUserPromptRel.setPrompt_id((Integer)map.get("id"));
						sysUserPromptRel.setUser_id(user.getUserId());
						List<SysUserPromptRel> list = sysUserPromptRelMapper.selectBySqlConditions(sysUserPromptRel);
						if(CollectionUtils.isEmpty(list)) {
							sysUserPromptRelMapper.insertSelective(sysUserPromptRel);
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(ResultConstants.ERROR);
		}
		return result;
	}
}
