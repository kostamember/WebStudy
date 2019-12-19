package com.saeyan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.ProductDAO;
import com.saeyan.dto.ProductVO;
/**
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/productList.do")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductDAO pDao = ProductDAO.getInstance();  // instance를 만든다
		
		List<ProductVO> productList = pDao.selectAllProducts();  // 인스턴스 pDao로 selectAllProducts를 호출. 그러면 productList에 product에 모든게 저장
		request.setAttribute("productList", productList);  // 서블릿에서 product 테이블의 정보를 얻어온 후에 이를 jsp 페이지에 보내야 함. 서블릿에서 jsp 페이지로 데이터를 전송하려면 request 객체의 속성에 실어 보냄
		
		RequestDispatcher dispatcher = request						
				.getRequestDispatcher("product/productList.jsp");   // productList.jsp로 포워딩함. 여기서 작성한 서블릿이 제대로 동작하려면 상품 리스트를 위한 페이지가 필요함.
		dispatcher.forward(request, response);
		
		
		
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
