package com.zxtech.platform.property.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxtech.platform.context.PlatformGlobalVar;
import com.zxtech.platform.property.bean.PlatformSysConfig;
import com.zxtech.platform.property.service.ISysPropertyService;

@Controller
@RequestMapping("/platform")
public class SysPropertyController {
	@Autowired
	private ISysPropertyService platformSysPropertyService;

	public ISysPropertyService getPlatformSysPropertyService() {
		return platformSysPropertyService;
	}

	public void setPlatformSysPropertyService(ISysPropertyService platformSysPropertyService) {
		this.platformSysPropertyService = platformSysPropertyService;
	}

	@RequestMapping(value = "/showAllSysProperty")
	@ResponseBody
	public List<PlatformSysConfig> showAllSysProperty() {
		List<PlatformSysConfig> list = platformSysPropertyService.getAllSysProperty();
		return list;
	}

	@RequestMapping(value = "/updateSysProperty")
	@ResponseBody
	public String updateSysProperty(PlatformSysConfig config) {
		String msg = "";
		try {
			platformSysPropertyService.updateSysProperty(config);
			msg = "属性更新成功！";
		} catch (Exception e) {
			msg = "属性值更新失败！请重试，或者联系管理员！";
		}
		return msg;
	}

	@RequestMapping(value = "/restartContext")
	@ResponseBody
	public String restartContext() {
		String msg = "";
		try {
			PlatformGlobalVar.initComponent();
			msg = "重新加载各个组件，并使用更新后的配置信息！";
		} catch (Exception e) {
			msg = "无法重新加载各个组件，来使用更新的配置信息！请重试或者联系管理员！";
		}
		return msg;
	}
}
