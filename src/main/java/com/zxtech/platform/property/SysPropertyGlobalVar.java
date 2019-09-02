package com.zxtech.platform.property;

import java.util.List;

import com.zxtech.platform.property.bean.PlatformSysConfig;
/**
 * 数据库中动态配置表操作组件中的统一常量类。定义了缓存配置名值对集合、配置表表名、配置表key字段名、配置表value字段名、配置表description字段名等常量信息
 * 
 * @author 
 * @version 1.0.0
 */
public class SysPropertyGlobalVar
{
	// -----------------该全局变量供其他组件和程序使用，保证稳定无变化。不可改变------------------------
	public static List<PlatformSysConfig> SYS_PROPERTY_LIST;
	// ------------------END--------------------------------------------------------

	// --------------------------组件内部使用的常量---------------------------------------
	public static String DEFUALT_DATA_SOURCE_NAME = "dataSource";// 默认使用的数据源名字
	public static String DEFUALT_TABLE_NAME = "plt_config";// 默认使用的系统配置表的表名
	public static String DEFUALT_KEY_COLUMN_NAME = "sysconfig_key";// 默认系统配置表中key值的字段名
	public static String DEFUALT_VALUE_COLUMN__NAME = "sysconfig_value";// 默认系统配置表中value值的字段名
	public static String DEFUALT_DESCRIPTION_COLUMN_NAME = "sysconfig_description";// 默认系统配置表中description描述的字段名

	/**
	 * 对常量变量的字符串进行初始值设定。将各个常量信息回复到初始状态
	 */
	public static void initConstants()
	{
		DEFUALT_DATA_SOURCE_NAME = "dataSource";// 默认使用的数据源名字
		DEFUALT_TABLE_NAME = "plt_config";// 默认使用的系统配置表的表名
		DEFUALT_KEY_COLUMN_NAME = "sysconfig_key";// 默认系统配置表中key值的字段名
		DEFUALT_VALUE_COLUMN__NAME = "sysconfig_value";// 默认系统配置表中value值的字段名
		DEFUALT_DESCRIPTION_COLUMN_NAME = "sysconfig_description";// 默认系统配置表中description描述的字段名
	}
}