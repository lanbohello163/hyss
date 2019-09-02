package com.zxtech.platform.database;

import java.util.HashMap;
import java.util.Map;

public abstract class TableBean {
	
	protected Map<String, Object> beanMap = new HashMap<String, Object>();
	protected Map<String, Object> sqlConditionsMap = new HashMap<String, Object>();
	protected Map<String, Object> sqlUpdateColumnsMap = new HashMap<String, Object>();
	protected Map<String, Object> sqlUpdateColumnAliasMap = new HashMap<String, Object>();
	
	protected void setSqlUpdateColumnAliasMap(Map<String, Object> sqlUpdateColumnAliasMap){
		this.sqlUpdateColumnAliasMap = sqlUpdateColumnAliasMap;
	}
	
	public void clearAllConditions() {
		sqlConditionsMap.clear();
		sqlUpdateColumnsMap.clear();
		sqlUpdateColumnAliasMap.clear();
	}
	
	/**
	 * get table name, mapping database table
	 * @return
	 */
	protected abstract String getSingleTableName();
	
	/**
	 * whether exist primary key
	 * @return
	 */
	protected abstract boolean hasPrimaryKey();
	
	/**
	 * get table column names array
	 * @return
	 */
	protected abstract String[] getColumnNameArr();
	
	/**
	 * put bean content into bean-map
	 * @return
	 */
	protected abstract void putInBeanMap();
	
	/**
	 * get update all sql
	 * @return
	 */
	protected abstract String getSqlUpdateAll();
	
	/**
	 * get select all sql, exclude where condition
	 * @return
	 */
	protected abstract String getSqlSelectAll();
	
	/**
	 * get insert batch sql
	 * @return
	 */
	protected abstract String getSqlInsertBatch();
}
