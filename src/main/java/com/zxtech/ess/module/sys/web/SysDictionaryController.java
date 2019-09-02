package com.zxtech.ess.module.sys.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxtech.ess.module.gen.bean.SysDictionary;
import com.zxtech.ess.module.sys.service.ISysDictionaryService;

@Controller
@RequestMapping("/sys")
public class SysDictionaryController {

	@Autowired
	private ISysDictionaryService sysDictionaryService;

	/**
	 * Description: get records by queryInfo with paging
	 *
	 * @param queryInfo
	 * @return records
	 * @author
	 */
	@RequestMapping(value = "/getdictlistbytype.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<SysDictionary> getListByDictType(HttpServletRequest request, SysDictionary queryInfo, String combobox_type, String modelSign) {
		return sysDictionaryService.getListByDictType(queryInfo, combobox_type, modelSign);
	}
}