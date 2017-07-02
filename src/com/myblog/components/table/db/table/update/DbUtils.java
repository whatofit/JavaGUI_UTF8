package com.myblog.components.table.db.table.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtils {

	// private static final String
	// URL="jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8";
	// private static final String USER="root";
	// private static final String PASSWORD="scme";//此处为数据库密码，更改为自己数据库的密码
	//
	// static{
	// try {
	// Class.forName("com.mysql.jdbc.Driver");
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// }
	// }

	private static final String dbDriver = "org.sqlite.JDBC";
	private static final String dbUrl = "jdbc:sqlite:";
	private static final String DB_NAME = "testDB.db3"; // "c:/testDB.db3";
	private static final String dbUser = "";
	private static final String dbPwd = "";
	
	static {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbUrl + DB_NAME, dbUser, dbPwd);
		// return DriverManager.getConnection(URL, USER, PASSWORD);
	}

	/*
	 * execSQL 执行带参数的增、删、改的方法
	 */
	public static int executeUpdate(String sql, Object[] param) {
		int count = 0;
		PreparedStatement pStmt = null;
		try {
			pStmt = getConnection().prepareStatement(sql);
			if (param != null && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					pStmt.setObject(i + 1, param);
				}
			}
			count = pStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			closeStmt(pStmt); //可以关闭
			// CloseConn();
		}
		return count;
	}

	/*
	 * execSQL 执行无参的增、删、改的方法
	 */
	public static int executeUpdate(String sql) {
		return executeUpdate(sql, null);
	}
	// 关闭方法
	public static void close(ResultSet rs, Statement stat, Connection conn)
			throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (stat != null) {
			stat.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public static void main(String[] args) {

	}
}