package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;  	// 프로그램이 로드될 떄, jdbc 드라이버가 driverManager에 등록이 되어야, 데이터베이스와의 연동이 가능해진다. 

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			// jdbc/myoracle이란 이름의 객체를 찾아서 datasource가 받는다.
			DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
			// ds가 생성되었으므로 Connection을 구함. 이 모든 과정은 결과적으로 데이터베이스와의 연결을 위해 connection 객체를 구하는 것이 목적임.
			conn = ds.getConnection();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	// select을 수행한 후 리소스 해제를 위한 메소드
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// DML(insert, update, delete)을 수행한 후 리소스 해제를 위한 메소드
	public static void close(Connection conn, Statement stmt) {
		try {
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
