package cn.edu.zucc.kitchen.util;

import java.sql.Connection;

public class DBUtil {
	private static final String jdbcUrl="jdbc:mysql://localhost:3306/kitchen?useUnicode=true&characterEncoding=UTF-8&useSSL = false&serverTimezone = GMT%2B8&&allowPublicKeyRetrieval=true&useSSL=false";
	private static final String dbUser="root";
	private static final String dbPwd="123456";
	static{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws java.sql.SQLException{
		return java.sql.DriverManager.getConnection(jdbcUrl, dbUser, dbPwd);
//		return java.sql.DriverManager.getConnection(jdbcUrl)
	}
}
