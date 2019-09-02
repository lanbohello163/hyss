package com.zxtech.ess.module.sys.mapper;

import java.util.List;
import java.util.Map;

import com.zxtech.ess.module.sys.bean.SysUserPromptSearchBean;

public interface SysUserPromptManagerMapper {

	List<Map<String, Object>> getListWithPaging(SysUserPromptSearchBean queryInfo);
}
