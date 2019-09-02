package com.zxtech.platform.property.service.impl;

import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxtech.platform.context.PlatformGlobalVar;
import com.zxtech.platform.property.SysPropertyGlobalVar;
import com.zxtech.platform.property.bean.PlatformSysConfig;
import com.zxtech.platform.property.exception.CannotAddPropertyException;
import com.zxtech.platform.property.persist.ISysPropertyDao;
import com.zxtech.platform.property.service.ISysPropertyService;
import com.zxtech.platform.utils.ArraysUtil;

/**
 * 从数据库中加载配置表中的系统全局配置信息。并将这些信息保存到全局常量中。并且，给Web层提供业务接口和处理。
 * 
 * @author
 * @version 1.0.0
 */

@Service("platformSysPropertyService")
public class SysPropertyServiceImpl implements ISysPropertyService {
	private static Logger log = LoggerFactory.getLogger(SysPropertyServiceImpl.class);
	@Autowired
	private ISysPropertyDao platformSysPropertyDao;

	public ISysPropertyDao getPlatformSysPropertyDao() {
		return platformSysPropertyDao;
	}

	public void setPlatformSysPropertyDao(ISysPropertyDao platformSysPropertyDao) {
		this.platformSysPropertyDao = platformSysPropertyDao;
	}

	/**
	 * 将数据库中的配置表中的信息加载到全局常量中。以便程序可以访问和使用这些常量。 使用常量请从
	 * <code>PlatformGlobalVar.sysDbProperties</code>中获取
	 */
	public void readSysPropertyIntoGlobalVar() {
		// 获取spring中配置的所有数据源对象
		String[] names = PlatformGlobalVar.APPLICATION_CONTEXT.getBeanNamesForType(DataSource.class);

		// 初始化常量类中的常量信息
		SysPropertyGlobalVar.initConstants();

		// 默认的表名
		String tableName = SysPropertyGlobalVar.DEFUALT_TABLE_NAME;
		// 判断properties文本文件的配置中是否含有对系统配置表表名的配置，如果含有，那么按配置的表名来处理系统配置表
		if (PlatformGlobalVar.SYS_PROPERTIES.containsKey("sysconfig.tablename")) {
			tableName = PlatformGlobalVar.SYS_PROPERTIES.get("sysconfig.tablename");
			SysPropertyGlobalVar.DEFUALT_TABLE_NAME = tableName;
			log.debug("SysPropertyServiceImpl::readSysPropertyIntoGlobalVar，Properties配置文件中已经制定系统配置表的表名：" + tableName);
		}
		// 判断properties文件中是否配置了默认的系统配置表的key字段名字
		if (PlatformGlobalVar.SYS_PROPERTIES.containsKey("sysconfig.keycolumnname")) {
			SysPropertyGlobalVar.DEFUALT_KEY_COLUMN_NAME = PlatformGlobalVar.SYS_PROPERTIES
					.get("sysconfig.keycolumnname");
			log.debug("SysPropertyServiceImpl::readSysPropertyIntoGlobalVar，Properties配置文件中已经制定系统配置表的key字段名："
					+ tableName);
		}
		// 判断properties文件中是否配置了默认的系统配置表的value字段名字
		if (PlatformGlobalVar.SYS_PROPERTIES.containsKey("sysconfig.valuecolumnname")) {
			SysPropertyGlobalVar.DEFUALT_VALUE_COLUMN__NAME = PlatformGlobalVar.SYS_PROPERTIES
					.get("sysconfig.valuecolumnname");
			log.debug("SysPropertyServiceImpl::readSysPropertyIntoGlobalVar，Properties配置文件中已经制定系统配置表的value字段名："
					+ tableName);
		}
		// 判断properties文件中是否配置了默认的系统配置表的description字段名字
		if (PlatformGlobalVar.SYS_PROPERTIES.containsKey("sysconfig.descriptioncolumnname")) {
			SysPropertyGlobalVar.DEFUALT_DESCRIPTION_COLUMN_NAME = PlatformGlobalVar.SYS_PROPERTIES
					.get("sysconfig.descriptioncolumnname");
			log.debug("SysPropertyServiceImpl::readSysPropertyIntoGlobalVar，Properties配置文件中已经制定系统配置表的description字段名："
					+ tableName);
		}

		// 用来装载多个配置表中的配置信息的全局集合
		List<PlatformSysConfig> all = new Vector<PlatformSysConfig>();
		for (String str : names) {
			if (platformSysPropertyDao.tableExist(str, tableName)) {
				// 表存在，将配置表中的数据加载到全局
				// 查询配置表中的配置名值对信息
				List<PlatformSysConfig> list = platformSysPropertyDao.findSysPropertyByDataSourceNameAndTableName(str,
						tableName);
				all.addAll(list);
			}
		}

		log.debug("SysPropertyServiceImpl::readSysPropertyIntoGlobalVar，已经从数据库配置表中读取到【" + all.size() + "】条名值对！");

		// 将配置信息添加到全局常量中
		for (PlatformSysConfig sp : all) {
			PlatformGlobalVar.SYS_DB_PROPERTIES.put(sp.getKey(), sp.getValue());
		}

		// 将集合保存到当前组件自身的常量类中
		SysPropertyGlobalVar.SYS_PROPERTY_LIST = all;
	}

	/**
	 * 将系统配置表中的名值对信息进行更新操作。
	 * 
	 * @param PlatformSysConfig
	 *            sp，需要更新的属性对象。
	 */
	public void updateSysProperty(PlatformSysConfig sp) {
		log.debug("SysPropertyServiceImpl::updateSysProperty--开始更新配置表中的属性。数据源为：" + sp.getDataSourceName() + "，表名是："
				+ sp.getTableName() + "。名值对为【" + sp.getKey() + ":" + sp.getValue() + ":" + sp.getDescription() + "】");

		// 判断dataSourceName是否给定，如果不给，使用默认值
		if (sp.getDataSourceName() == null || sp.getDataSourceName().length() <= 0)
			sp.setDataSourceName(SysPropertyGlobalVar.DEFUALT_DATA_SOURCE_NAME);
		// 判断tableName是否给定，如果不给，使用默认值
		if (sp.getTableName() == null || sp.getTableName().length() <= 0)
			sp.setTableName(SysPropertyGlobalVar.DEFUALT_TABLE_NAME);

		// 将名值对按给定信息更新到数据库中
		platformSysPropertyDao.updateSysPropertyByDataSourceNameAndTableName(sp);
	}

	/**
	 * 获取所有系统配置表中的名值对信息。主要提供对前台显示名值对信息的功能
	 * 
	 * @return List&lt;SysProperty&gt; 返回数据库中所有配置表中的属性集合
	 */
	public List<PlatformSysConfig> getAllSysProperty() {
		return SysPropertyGlobalVar.SYS_PROPERTY_LIST;
	}

	/**
	 * 将新的属性保存到数据库中，如果数据源或者表名不存在将抛出无法添加的异常。 如果新添加的属性没有指定数据源名字和表的名字，那么将使用系统默认值。
	 * {@link com.zxtech.platform.property.SysPropertyGlobalVar}<br />
	 * 
	 * @param PlatformSysConfig
	 *            sp，需要新添加到数据库中的属性
	 */
	public void addSysPropertyByDataSourceName(PlatformSysConfig sp) throws CannotAddPropertyException {
		// 获取spring中配置的所有数据源对象
		String[] names = PlatformGlobalVar.APPLICATION_CONTEXT.getBeanNamesForType(DataSource.class);

		// 判断dataSourceName是否给定，如果不给，使用默认值
		if (sp.getDataSourceName() == null || sp.getDataSourceName().length() <= 0)
			sp.setDataSourceName(SysPropertyGlobalVar.DEFUALT_DATA_SOURCE_NAME);
		// 判断tableName是否给定，如果不给，使用默认值
		if (sp.getTableName() == null || sp.getTableName().length() <= 0)
			sp.setTableName(SysPropertyGlobalVar.DEFUALT_TABLE_NAME);

		// 判断数据源是否存在，如果给定的数据源名字不存在，那么无法添加名值对属性
		boolean dataSourceNameExist = ArraysUtil.contains(names, sp.getDataSourceName());
		if (!dataSourceNameExist) {
			throw new CannotAddPropertyException("无法添加属性，指定的数据源不存在！");
		}

		// 判断数据库表是否存在，如果数据库表不存在，无法添加数据源
		boolean tableNameExist = platformSysPropertyDao.tableExist(sp.getDataSourceName(), sp.getTableName());
		if (!tableNameExist) {
			throw new CannotAddPropertyException("无法添加属性，指定的表名不存在！");
		}

		// 判断表中Property属性是否已经存在
		boolean propertyExist = platformSysPropertyDao.isPropertyExist(sp);
		if (!propertyExist) {
			throw new CannotAddPropertyException("无法添加属性，该属性在数据库中已经存在！");
		}

		// 将名值对信息插入到数据库中
		platformSysPropertyDao.insertSysPropertyByDataSourceNameAndTableName(sp);
	}
}
