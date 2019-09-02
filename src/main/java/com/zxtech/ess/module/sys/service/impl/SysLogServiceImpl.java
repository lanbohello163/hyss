package com.zxtech.ess.module.sys.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.ibm.icu.text.SimpleDateFormat;
import com.zxtech.ess.app.util.ExcelUtil;
import com.zxtech.ess.module.sys.bean.SysLogSearchBean;
import com.zxtech.ess.module.sys.mapper.SysLogManagerMapper;
import com.zxtech.ess.module.sys.service.ISysLogService;
import com.zxtech.platform.utils.GlobalFileUtil;
import com.zxtech.platform.utils.excel.EasyExcelUtil;
import com.zxtech.platform.utils.page.PageHandler;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

@Service("sysLogServiceImpl")
public class SysLogServiceImpl implements ISysLogService{
	
	@Autowired
	private SysLogManagerMapper sysLogManagerMapper;
	//查询
	@Override
	public Map<String, Object> sysLogListPage(SysLogSearchBean queryInfo) {
		Page<Map<String, Object>> pageResult = PageHandler.buildPage(queryInfo)
				.doSelectPage(() ->sysLogManagerMapper.sysLogList(queryInfo));
		return PageHandler.wrapPageMap(pageResult);
	}
	//导出
	@Override
	public ResultBean export(SysLogSearchBean queryInfo,HttpServletRequest request, HttpServletResponse response, UserBean user) {

		return EasyExcelUtil.writeExcelMoreSheetMoreWrite(() -> sysLogManagerMapper.contractExportListCount(queryInfo),
				() -> sysLogManagerMapper.contractExportList(queryInfo), queryInfo);
	}
}