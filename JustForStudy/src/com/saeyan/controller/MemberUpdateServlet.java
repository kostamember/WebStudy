package com.saeyan.controller;
// 회원 정보 수정을 위한 폼으로 이동하는 처리를 위한 서블릿 클래스
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.oracle.jrockit.jfr.RequestDelegate;
import com.saeyan.dao.MemberDAO;
import com.saeyan.dto.MemberVO;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/MemberUpdateServlet")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userid = request.getParameter("userid");
		MemberDAO mDao = MemberDAO.getInstance();   // memberdao의 instance 객체 mVo생성
		
		MemberVO mVo = mDao.getMember(userid);		// mVo로 getmember 메소드 호출. 테이블에서 회원 정보를 가져온다.
		request.setAttribute("mVo", mVo);
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("member/memberUpdate.jsp"); // 회원 정보를 수정하기 위한 폼으로 이동.
		dispatcher.forward(request, response);
		
		
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
															// 폼에서 입력한 회원 정보를 얻어옴
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String admin = request.getParameter("admin");
		
		MemberVO mVo = new MemberVO();		// 회원 정보를 저장한 객체를 생성함.
											// MemberVO 객체인 mVo에 회원 가입 폼에서 입력 받은 데이터를 저장함.
		mVo.setUserid(userid);
		mVo.setPwd(pwd);
		mVo.setEmail(email);
		mVo.setPhone(phone);
		mVo.setAdmin(Integer.parseInt(admin));
		
		MemberDAO mDao = MemberDAO.getInstance();
		
		mDao.updateMember(mVo);
		response.sendRedirect("login.do");
		
		//doGet(request, response);
	}

}
