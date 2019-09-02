package com.zxtech.ess.module.hsync.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zxtech.ess.module.hsync.bean.SyncHiotFetchElevatorSearchBean;
import com.zxtech.ess.module.hsync.service.ISyncHiotService;

@RestController
@RequestMapping("/hsync")
public class SyncHiotController {

	@Autowired
	private ISyncHiotService syncHiotService;

	/**
	 * fetch combobox base company data list
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/combobox/BaseCompanyData.apii", method = { RequestMethod.GET })
	public List<Map<String, Object>> comboboxBaseCompanyData(HttpServletRequest request) {
		return syncHiotService.comboboxBaseCompanyData();
	}

	/**
	 * fetch combobox base station data list
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/combobox/BaseStationData.apii", method = { RequestMethod.GET })
	public List<Map<String, Object>> comboboxBaseStationData(HttpServletRequest request, Integer comp_id) {
		return syncHiotService.comboboxBaseStationData(comp_id);
	}

	/**
	 * fetch base elevator data
	 * 
	 * @param request
	 * @param queryInfo
	 * @return
	 */
	@RequestMapping(value = "/api/fetch/BaseElevatorData.api", method = { RequestMethod.POST })
	public Map<String, Object> fetchBaseElevatorData(HttpServletRequest request,
			SyncHiotFetchElevatorSearchBean queryInfo) {
		return syncHiotService.fetchBaseElevatorData(queryInfo);
	}
}
