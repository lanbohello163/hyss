package com.zxtech.platform.property.persist.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.zxtech.platform.database.ConnectionUtil;
import com.zxtech.platform.property.SysPropertyGlobalVar;
import com.zxtech.platform.property.bean.PlatformSysConfig;
import com.zxtech.platform.property.persist.ISysPropertyDao;

/**
 * 对数据库中的配置表进行操作的DAO实现
 * 
 * @author
 * @version 1.0.0
 */

@Repository("platformSysPropertyDao")
public class SysPropertyDaoImpl implements ISysPropertyDao {

	private static Logger log = LoggerFactory.getLogger(SysPropertyDaoImpl.class);

	/**
	 * 判断某个数据源下是否含有某张表
	 * 
	 * @param String
	 *            dataSourceName，数据源的名字
	 * @param String
	 *            tableName，表名
	 * @return boolean 判断给定数据源下是否存在给定的表
	 */
	public boolean tableExist(String dataSourceName, String tableName) {
		try {
			Connection conn = ConnectionUtil.getDataSourceConByName(dataSourceName).getConnection();
			ResultSet rs = conn.getMetaData().getTables(null, null, tableName, new String[] { "TABLE" });
			return rs.next();
		} catch (SQLException e) {
			log.error("SysPropertyDaoImpl::tableExist------查询表是否存在异常!", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查找给定数据源下，指定配置表名中的所有名值对属性
	 * 
	 * @param String
	 *            dataSourceName，数据源的名字
	 * @param String
	 *            tableName，表名
	 * @return List&lt;SysProperty&gt; 指定数据源和表名下的所有名值对属性
	 */
	public List<PlatformSysConfig> findSysPropertyByDataSourceNameAndTableName(String dataSourceName,
			String tableName) {
		List<PlatformSysConfig> list = new Vector<PlatformSysConfig>();
		try {
			Connection conn = ConnectionUtil.getDataSourceConByName(dataSourceName).getConnection();
			// SQL语句，查询
			String sql = "select * from " + tableName;

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			// 处理结果集
			while (rs.next()) {
				PlatformSysConfig sp = new PlatformSysConfig();
				sp.setKey(rs.getString(SysPropertyGlobalVar.DEFUALT_KEY_COLUMN_NAME));
				sp.setValue(rs.getString(SysPropertyGlobalVar.DEFUALT_VALUE_COLUMN__NAME));
				sp.setDescription(rs.getString(SysPropertyGlobalVar.DEFUALT_DESCRIPTION_COLUMN_NAME));

				sp.setDataSourceName(dataSourceName);
				sp.setTableName(tableName);

				list.add(sp);
			}
		} catch (SQLException e) {
			log.error("SysPropertyDaoImpl::findSysPropertyByDataSourceNameAndTableName------查询配置表中信息异常!", e);
			throw new RuntimeException(e);
		}

		return list;
	}

	/**
	 * 更新数据库配置表中的名值对属性
	 * 
	 * @param PlatformSysConfig
	 *            sp，需要更新的名值对属性
	 */
	public void updateSysPropertyByDataSourceNameAndTableName(PlatformSysConfig sp) {
		try {
			Connection conn = ConnectionUtil.getDataSourceConByName(sp.getDataSourceName()).getConnection();
			// SQL语句，查询
			String sql = "update " + sp.getTableName() + " set " + SysPropertyGlobalVar.DEFUALT_VALUE_COLUMN__NAME
					+ "=? where " + SysPropertyGlobalVar.DEFUALT_KEY_COLUMN_NAME + "=?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sp.getValue());
			ps.setString(2, sp.getKey());

			ps.executeUpdate();

		} catch (SQLException e) {
			log.error("SysPropertyDaoImpl::updateSysPropertyByDataSourceNameAndTableName------更新配置表中信息异常!", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 在数据库名值对属性中插入新的名值对属性
	 * 
	 * @param PlatformSysConfig
	 *            sp，需要插入的名值对属性
	 */
	public void insertSysPropertyByDataSourceNameAndTableName(PlatformSysConfig sp) {
		try {
			Connection conn = ConnectionUtil.getDataSourceConByName(sp.getDataSourceName()).getConnection();
			// SQL语句，查询
			String sql = "insert into " + SysPropertyGlobalVar.DEFUALT_TABLE_NAME + "("
					+ SysPropertyGlobalVar.DEFUALT_KEY_COLUMN_NAME + ","
					+ SysPropertyGlobalVar.DEFUALT_VALUE_COLUMN__NAME + ","
					+ SysPropertyGlobalVar.DEFUALT_DESCRIPTION_COLUMN_NAME + ") values(?,?,?)";

			// 绑定对象数据
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sp.getKey());
			ps.setString(2, sp.getValue());
			ps.setString(3, sp.getDescription());

			ps.executeUpdate();

		} catch (SQLException e) {
			log.error("SysPropertyDaoImpl::updateSysPropertyByDataSourceNameAndTableName------更新配置表中信息异常!", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 判断某一个属性在系统配置表中是否存在
	 * 
	 * @param PlatformSysConfig
	 *            sp，需要判断的名值对属性
	 * @return boolean 判断给定的名值对属性在数据库配置表中是否存在
	 */
	public boolean isPropertyExist(PlatformSysConfig sp) {
		try {
			Connection conn = ConnectionUtil.getDataSourceConByName(sp.getDataSourceName()).getConnection();
			// SQL语句，查询
			String sql = "select * from " + sp.getTableName();

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			return rs.next();

		} catch (SQLException e) {
			log.error("SysPropertyDaoImpl::isPropertyExist------判断属性在表中是否存在异常!", e);
			throw new RuntimeException(e);
		}
	}

}
