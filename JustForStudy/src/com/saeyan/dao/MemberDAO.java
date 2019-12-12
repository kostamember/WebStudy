package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import com.saeyan.dto.MemberVO;


public class MemberDAO {
	private MemberDAO() {
		
		
	}
	private static MemberDAO instance = new MemberDAO(); // MemberDAO를 오직 하나의 instance만을 생성해서 쓰게 하는 싱글톤 패턴으로 정의한 예
	
	public static MemberDAO getInstance() {
		return instance; // MemberDAO는 게터를 통해서만 접근할 수 있다
	}
	
	public Connection getConnection() throws Exception {
	Connection conn = null;
	Context initContext = new InitialContext();
	Context envContext  = (Context)initContext.lookup("java:/comp/env");
	DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
	conn = ds.getConnection();
	return conn;
	}
	
	
	
	public int userCheck(String userid, String pwd) { // 사용자 인증시 사용하는 메소드. 회원 아이디와 암호를 전달 받음. 회원이 존재하면 1을, 존재하지 않으면 -1을 반환.
		int result = -1;  // result 초기값은 -1, 즉 일치하는 회원이 없는 것을 가정하고 시작.
		String sql = "select pwd from member where userid=?"; // 로그인 폼에서 입력받은 아이디로  member테이블을 조회하기 위해서 where절에서 userid로 검색하여 암호를 얻어옴.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();  // 33줄에 기술된 select 문의 ?에 매개 변수로 받아온 아이디를 바인딩시킵니다.
			if(rs.next()) {				// 쿼리문을 실행하여 결과값을 ResultSet 객체인 rs에 저장합니다.
				if(rs.getString("pwd")!= null && rs.getString("pwd").equals(pwd)) {  // 아이디가 일치하는 행이 존재하면 true이므로 result에 1을 저장
					result = 1;
				} else {			// 아이디가 일치하지만 암호가 불일치하면 result에 0을 저장
					result = 0;  
				}
			} else {     // 해당 아이디가 존재하지 않으면 result에 -1을 저장. *이미 초기값이 -1로 지정되어 있는데 굳이 필요한 코드인가? 
			result = -1;
				} 
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close( );
					if(conn != null) conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			return result;
	}	
	
	
			public MemberVO getMember(String userid) {   // 아이디가 일치하는 멤버의 정보를 얻어오는 메소드임.
				MemberVO mVo = null;
				String sql = "select * from member where userid=?";
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					conn = getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, userid);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						mVo = new MemberVO();
						mVo.setName(rs.getString("name"));
						mVo.setUserid(rs.getString("userid"));  // 아이디가 일치하는 로우가 존재하면 VO 객체에 디비에 저장된 회원 정보를 채웁니다.
						mVo.setPwd(rs.getString("pwd"));
						mVo.setEmail(rs.getString("email"));
						mVo.setPhone(rs.getString("phone"));
						mVo.setAdmin(rs.getInt("admin"));	
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if(rs != null) rs.close();
						if(pstmt != null) pstmt.close( );
						if(conn != null) conn.close();
					} catch(Exception e) {
						e.printStackTrace();
				}
			}
			return mVo;
			
			
		}
			public int confirmID(String userid) {
				int result = -1;
				String sql = "select userid from member where userid=?";
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					conn = getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, userid);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						result = 1;
					} else {
						result = -1;
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if(rs !=null) rs.close();
						if(pstmt !=null) pstmt.close();
						if(conn !=null) conn.close();
						
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				return result;
			}
			public int insertMember(MemberVO mVo) {    // 회원 가입을 위한 메소드로 회원 가입 폼에서 입력받은 회원 정보를 매개 변수로 전달 받는다
				int result = -1;
				String sql = "insert into member values(?,?,?,?,?,?)";  // 회원 정보를 member 테이블에 삽입하기 위한 insert 문임.
				Connection conn = null;
				PreparedStatement pstmt = null;
				try {
					conn = getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, mVo.getName());			// 컬럼에 저장할 값은 매개 변수로 받은 vo 객체에서 얻어와 바인딩한다.
					pstmt.setString(2, mVo.getUserid());
					pstmt.setString(3, mVo.getPwd());
					pstmt.setString(4, mVo.getEmail());
					pstmt.setString(5, mVo.getPhone());
					pstmt.setInt(6, mVo.getAdmin());
					result = pstmt.executeUpdate();				
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if(pstmt !=null) pstmt.close();
						if(conn != null) conn.close();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				return result;
			} 
			public int updateMember(MemberVO mVo) {
				int result = -1;
				String sql = "update member set pwd=?, email=?,"
							+ "phone=?, admin=? where userid=?";
				Connection conn = null;
				PreparedStatement pstmt = null;
				try {
					conn = getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, mVo.getName());			// 컬럼에 저장할 값은 매개 변수로 받은 vo 객체에서 얻어와 바인딩한다.
					pstmt.setString(2, mVo.getUserid());
					pstmt.setString(3, mVo.getPwd());
					pstmt.setString(4, mVo.getEmail());
					pstmt.setString(5, mVo.getPhone());
					pstmt.setInt(6, mVo.getAdmin());
					result = pstmt.executeUpdate();				
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if(pstmt !=null) pstmt.close();
						if(conn != null) conn.close();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				return result;
			} 
			
	}


