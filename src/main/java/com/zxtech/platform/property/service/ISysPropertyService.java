package com.zxtech.platform.property.service;

import java.util.List;

import com.zxtech.platform.property.bean.PlatformSysConfig;
import com.zxtech.platform.property.exception.CannotAddPropertyException;
/**
 * 加载系统配置表中信息的业务层对象。负责完成对系统配置信息的加载，更新和添加工作
 * 
 * @author 
 * @version 1.0.0
 */
public interface ISysPropertyService
{
	/**
	 * 读取系统数据库中配置表中的信息，装载到全局常量，供各个组件和应用访问
	 */
	public void readSysPropertyIntoGlobalVar();

	/**
	 * 系统管理过程中，可以调整属性的value值。该方法完成属性值的更新操作
	 * 
	 * @param PlatformSysConfig sp，需要更新的属性名值对
	 */
	public void updateSysProperty(PlatformSysConfig sp);

	/**
	 * 系统管理过程中，有可能需要添加新的属性（这种需求极少）。该方法完成添加新属性的功能
	 * 
	 * @param PlatformSysConfig sp，需要添加到数据库中的名值对属性
	 */
	public void addSysPropertyByDataSourceName(PlatformSysConfig sp)
			throws CannotAddPropertyException;
	/**
	 * 返回属性列表，用于动态配置。
	 * 
	 * @return List&lt;SysProperty&gt; 返回数据库中配置表所有属性
	 */
	public List<PlatformSysConfig> getAllSysProperty();
}
