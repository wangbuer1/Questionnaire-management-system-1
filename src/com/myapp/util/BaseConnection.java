package com.myapp.util;

import java.sql.*;

public class BaseConnection {
	private static Connection conn = null;


private static final String url = "jdbc:mysql://localhost:3306/questions?useUnicode=true&characterEncoding=UTF8&useSSL=true";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String user = "root";
	private static final String password = "root";

	private String sql = null;
	private Statement st = null;
	private ResultSet rs = null;
	// 静态代码块，BaseConnection类加载的时候同时加载驱动
	static {
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 私有构造方法，防止其他类生成 BaseConnection对象，保证整个项目的数据库连接都是同一个对象
	private BaseConnection() {
	}

	// 取得数据库连接的方法，返回一个 Connection 对象
	public static Connection getConnection() throws Exception {
		if (conn == null)
			conn = DriverManager.getConnection(url, user, password);
		return conn;
	}

	// 登陆的验证
	public boolean login_check(String username, String password) {
		boolean temp = false;
		sql = "select * from users where username='" + username + "' and password='" + password + "'";
		try {
			rs = st.executeQuery(sql);
			if (rs.next()) {
				temp = true;
			}
		} catch (Exception e) {
			temp = false;
		}
		return temp;
	}

	public static void closeRes(ResultSet rs,PreparedStatement ps,Connection conn){
		try {
			if(rs!=null){
				rs.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void closeRes(PreparedStatement ps,Connection conn){
		try {
			if(ps!=null){
				ps.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}