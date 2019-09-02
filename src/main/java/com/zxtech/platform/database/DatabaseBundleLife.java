package com.zxtech.platform.database;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxtech.platform.context.PlatformGlobalVar;
import com.zxtech.platform.context.lifecycle.BundleLife;

public class DatabaseBundleLife implements BundleLife {
	private static Logger logger = LoggerFactory.getLogger(DatabaseBundleLife.class);

	public void init() {
		String[] names = PlatformGlobalVar.APPLICATION_CONTEXT.getBeanNamesForType(DataSource.class);
		for (String str : names) {
			logger.debug("DatabaseBundleLife::init---datasource名字为：" + str);
			DataSource ds = (DataSource) PlatformGlobalVar.APPLICATION_CONTEXT.getBean(str);
			boolean flag = ConnectionUtil.setDataSourceCon(str, ds);
			if (!flag) {
				logger.error("Establish Database Connection Failed!!Spring配置文件中数据源名字为：" + str);
			}
		}
	}

	public void destroy() {
	}

}
