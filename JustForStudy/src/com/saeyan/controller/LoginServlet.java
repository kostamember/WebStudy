package com.saeyan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import com.saeyan.dao.MemberDAO;
import com.saeyan.dto.MemberVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url="member/login.jsp";
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginUser")!= null) {
			url="main.jsp";
		}
	
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "member/login.jsp";  // 회원 인증이 실패되었을 경우 이동할 login.jsp 페이지를 url 변수에 저장함
		
		String userid = request.getParameter("userid");  // 로그인 폼에서 입력한 회원의 아이디와 암호를 얻어 와서 변수에 저장함.
		String pwd = request.getParameter("pwd");
		
		MemberDAO mDao = MemberDAO.getInstance();  // 입력받은 아이디와 암호로 회원 인증 여부를 물어보기 위해서, member 테이블에 존재하는 아이디인지를 확인해야 함. MemberDAO의 userCheck()
		int result = mDao.userCheck(userid, pwd);  // 메소드에서는 아이디에 해당하는 회원이 존재하는지를 조회
		
		if(result==1) {
			MemberVO mVo=mDao.getMember(userid);
			HttpSession session = request.getSession();  // 세선 객체 생성.
			session.setAttribute("loginUser", mVo);				// 세선에 회원 정보를 저장.. 로그인 인증 처리된 회원 정보는 다른 사이트에 갔다 돌아와도 다시 로그인하지 않아도 될 수 있도록 세션에 등록해 두어야 함.
			request.setAttribute("message", "회원가입에 성공했습니다."); // jsp 페이지에 보낼 메시지를 요청 객체에 저장
			url="main.jsp";										// 인증에 성공해야만 이동 가능한 main.jsp 페이지로 가도록 main.jsp를 url 변수에 저장
		} else if(result==0) {
			request.setAttribute("message", "비밀번호가 맞지 않습니다.");
		} else if(result==-1) {
			request.setAttribute("message", "존재하지 않는 회원입니다.");
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(url);   // 인증이 됐다면 main 페이지로, 아니면 다시 login 페이지로 이동.
		dispatcher.forward(request, response);
		
		
		
		// doGet(request, response);
	}

}
