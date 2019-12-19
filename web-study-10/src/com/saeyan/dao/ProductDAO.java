package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import com.saeyan.dto.ProductVO;

import util.DBManager;

public class ProductDAO {
	
	private ProductDAO() {				
		
	}
	private static ProductDAO instance = new ProductDAO();
	
	public static ProductDAO getInstance() {
		return instance;
	}
	
	public List<ProductVO> selectAllProducts() {  // crud 부분에서 read 부분을 담당하는 메소드
		String sql = "select * from product order by code desc";  // 상품 리스트 페이지에 상품 정보를 모두 철력하기 위한 select 문을 문자열 변수 sql에 저장해 둔다. 최근 등록한 상품을 먼저 출력하기 위해 order by 절을 추가하여 code 컬럼을 기준으로 내림차순으로 정렬하였음.
		List<ProductVO> list = new ArrayList<ProductVO>();   // 상품 정보를 한 개 이상 저장하기 위해서 ArrayList 클래스로 객체 생성을 하였음
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();  // 쿼리문 실행을 위해 DBManager 클래스의 정적 메소드인 getConnection을 호출
			pstmt = conn.prepareStatement(sql);  // conn을 통해 스테이트먼트 객체를 얻어옴
			rs = pstmt.executeQuery();			// 스테이트먼트 객체로 쿼리문을 실행한 후 이 결과를 ResultSet 객체에 실어둠.
			while (rs.next()) {					// ResultSet 객체에 있는 정보를 뺴 내려면, 반복문을 돌면서  rs의 next메소드를 호출하여 행 단위로 이동해야 함.
				ProductVO pVo = new ProductVO();
				pVo.setCode(rs.getInt("code"));
				pVo.setName(rs.getString("name"));
				pVo.setPrice(rs.getInt("price"));
				pVo.setPictureUrl(rs.getString("pictureUrl"));
				pVo.setDescription(rs.getString("description"));
				list.add(pVo);   // Arraylist 객체에 ProductVO 객체를 추가함
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;   // product 테이블의 모든 정보가 Arraylist 객체에 저장되었으므로 이를 리턴함. selectAllProducts() 메소드를 호출하면 product 테이블의 모든 정보를 ArrayList 객체를 통해서 얻을 수 있게 되었음.
	}
	public void insertProduct(ProductVO pVo) {
		String sql = "insert into product values(product_seq.nextval, ?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pVo.getName());
			pstmt.setInt(2, pVo.getPrice());
			pstmt.setString(3, pVo.getPictureUrl());
			pstmt.setString(4, pVo.getDescription());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);  // 매개변수로 보낸거 닫아주세요
		}
	}
	public ProductVO selectProductByCode(String code) {
		String sql = "select * from product where code=?";
		ProductVO pVo = null;
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = DBManager.getConnection();  // 쿼리문 실행을 위해 DBManager 클래스의 정적 메소드인 getConnection을 호출
				pstmt = conn.prepareStatement(sql);  // conn을 통해 스테이트먼트 객체를 얻어옴
				pstmt.setString(1, code);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					pVo = new ProductVO();
					pVo.setCode(rs.getInt("code"));
					pVo.setName(rs.getString("name"));
					pVo.setPrice(rs.getInt("price"));
					pVo.setPictureUrl(rs.getString("pictureUrl"));
					pVo.setDescription(rs.getString("description"));					
				} 
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn,pstmt, rs);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return pVo;
			
	}
	public void updateProduct(ProductVO pVo) {
		String sql = "update product set name=?, price=?, pictureurl=?, description=? where code=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pVo.getName());
			pstmt.setInt(2, pVo.getPrice());
			pstmt.setString(3, pVo.getPictureUrl());
			pstmt.setString(4, pVo.getDescription());
			pstmt.setInt(5, pVo.getCode());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	public void deleteProduct(String code) {
		String sql = "delete product where code=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
}
