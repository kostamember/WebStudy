package util;					// 커넥션을 얻는 작업을 하는 클래스임. 자바와 오라클은 별개의 시스템이기 떄문에 자바에서 오라클을 사용하기 위해서는 오라클에 접속을 요청해서 이에 성공해야 함.

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
	public static Connection getConnection() {   // connection 객체를 얻는 메소드. 객체 생성 없이 메소드를 호출하기 위해서 static 메소드로 선언함.
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void close(Connection conn, Statement stmt, ResultSet rs) {    // select를 수행한 후 리소스 해체를 위한 메소드
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection conn, Statement stmt) {  // insert, update, delecte 작업을 수행한 후 리소스 해제를 위한 메소드
		try {
			stmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}





