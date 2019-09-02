package com.zxtech.platform.property.persist;

import java.util.List;

import com.zxtech.platform.property.bean.PlatformSysConfig;

/**
 * 对数据库中的配置表进行操作
 * 
 * @author 
 * @version 1.0.0
 */
public interface ISysPropertyDao
{
	/**
	 * 判断某个数据源下是否含有某张表
	 * 
	 * @param String dataSourceName，数据源的名字
	 * @param String tableName，表名
	 * @return boolean 判断给定数据源下是否存在给定的表
	 */
	public boolean tableExist(String dataSourceName, String tableName);
	/**
	 * 查找给定数据源下，指定配置表名中的所有名值对属性
	 * 
	 * @param String dataSourceName，数据源的名字
	 * @param String tableName，表名
	 * @return List&lt;SysProperty&gt; 指定数据源和表名下的所有名值对属性
	 */
	public List<PlatformSysConfig> findSysPropertyByDataSourceNameAndTableName(
			String dataSourceName, String tableName);
	/**
	 * 更新数据库配置表中的名值对属性
	 * 
	 * @param PlatformSysConfig sp，需要更新的名值对属性
	 */
	public void updateSysPropertyByDataSourceNameAndTableName(PlatformSysConfig sp);
	/**
	 * 在数据库名值对属性中插入新的名值对属性
	 * 
	 * @param PlatformSysConfig sp，需要插入的名值对属性
	 */
	public void insertSysPropertyByDataSourceNameAndTableName(PlatformSysConfig sp);
	/**
	 * 判断某一个属性在系统配置表中是否存在
	 * 
	 * @param PlatformSysConfig sp，需要判断的名值对属性
	 * @return boolean 判断给定的名值对属性在数据库配置表中是否存在
	 */
	public boolean isPropertyExist(PlatformSysConfig sp);
}
