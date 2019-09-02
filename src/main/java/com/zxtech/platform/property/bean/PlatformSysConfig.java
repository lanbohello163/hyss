package com.zxtech.platform.property.bean;

import java.io.Serializable;

public class PlatformSysConfig implements Serializable
{
	private static final long serialVersionUID = 5723559874849726138L;
	// Property属性的key值
	private String key;
	// Property属性的value值
	private String value;
	// Property的描述信息
	private String description;
	// Property属性所在的datasource名字
	private String dataSourceName;
	// property属性所在的表名
	private String tableName;

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getDataSourceName()
	{
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName)
	{
		this.dataSourceName = dataSourceName;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

}
