package com.zxtech.platform.database;

import java.sql.Connection;
import java.util.Hashtable;
import java.util.Map;

import javax.sql.DataSource;

/**
 * Title: 数据库连接工具类<br>
 * Description: 全局数据库连接工具类，该类程序员不需要直接使用。组件开发人员可以使用。除极特殊情况。可以与项目经理沟通后确定。<br>
 * Date: 2012-08-02<br>
 * Modify-Date:2012-08-02<br>
 * Copyright (c) 2012 zxtech <br>
 * 
 * @version 1.0
 */
public class ConnectionUtil {
	private ConnectionUtil() {
	}

	private static Map<String, DataSource> map = new Hashtable<String, DataSource>();

	/**
	 * 设置数据库源对象，保存到全局静态变量中
	 * 
	 * @param String
	 *            name，数据源的名字
	 * @param DataSource
	 *            arg，系统数据源对象，由启动组件在系统启动后传入到当前工具类
	 * 
	 * @CreateTime:2012-8-2
	 * @ModifyTime: 2012-8-2
	 * 
	 * */
	public static synchronized boolean setDataSourceCon(String name,
			DataSource arg) {
		if (!testConnection(arg)) {
			return false;
		} else {
			map.put(name, arg);
			return true;
		}
	}

	/**
	 * 根据名字获取全局数据源对象
	 * 
	 * @param String
	 *            name，查询的数据源名字
	 * @return DataSource 根据数据源名字返回全局数据源对象
	 * 
	 * @CreateTime:2012-8-2
	 * @ModifyTime: 2012-8-2
	 * 
	 * */
	public static DataSource getDataSourceConByName(String name) {
		if (map.containsKey(name))
			return map.get(name);
		else
			return null;
	}

	/**
	 * 测试数据源是否能获取连接，并执行查询。
	 * 
	 * @param DataSource
	 *            arg，需要测试的数据源对象
	 * @return boolean 数据源是否正确
	 * 
	 * @CreateTime:2012-8-2
	 * @ModifyTime: 2017-11-24
	 * 
	 * */
	private static boolean testConnection(DataSource arg) {
		try {
			String testSql = CheckConnectionSql.CHECK_SIMPLE_SQL.getSql();
			if ("com.zaxxer.hikari.HikariDataSource".equals(arg.getClass().getName())) {
				com.zaxxer.hikari.HikariDataSource ds = (com.zaxxer.hikari.HikariDataSource) arg;
				if ("oracle.jdbc.pool.OracleDataSource".equals(ds.getDataSourceClassName())) {
					testSql = CheckConnectionSql.CHECK_ORACLE_SQL.getSql();
				}
			}
			Connection conn = arg.getConnection();
			conn.prepareStatement(testSql).execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private enum CheckConnectionSql {
		
		CHECK_SIMPLE_SQL("select 1"),
		CHECK_ORACLE_SQL("select 1 from dual");
		
		private String sql;
		
		private CheckConnectionSql(String sql) {
			this.sql = sql;
		}
		
		public String getSql() {
			return this.sql;
		}
	}
}
