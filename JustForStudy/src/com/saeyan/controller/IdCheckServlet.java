package com.saeyan.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.MemberDAO;
/**
 * Servlet implementation class IdCheckServlet
 */
@WebServlet("/idCheck.do")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userid = request.getParameter("userid");  // 회원 가입시 입력한 아이디를 얻어옴
		
		MemberDAO mDao = MemberDAO.getInstance();  // dao 객체를 얻어옴
		
		int result = mDao.confirmID(userid);  // 아이디 중복 체크를 위한 confirmID() 메소드에 아이디를 전달해 주어 결과값을 얻어옴.
		
		request.setAttribute("userid", userid);  // 아이디 중복 체크 후 얻어온 confirmID() 메소드의 결과값을 사용자 아이디와 함께 idcheck.jsp 페이지에 어트리뷰트에 실어 보냄.
		request.setAttribute("result", result);
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("member/idcheck.jsp");
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
