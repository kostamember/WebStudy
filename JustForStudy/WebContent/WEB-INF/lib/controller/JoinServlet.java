package com.saeyan.controller;  // 회원 정보 입력 폼을 위한 서블릿 클래스 만들기

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saeyan.dao.MemberDAO;
import com.saeyan.dto.MemberVO;
/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/join.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request
					.getRequestDispatcher("member/join.jsp");
		dispatcher.forward(request, response);
		
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		
		String name = request.getParameter("name");  // 폼에서 입력한 회원 정보를 얻어옴
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String admin = request.getParameter("admin");
		
		MemberVO mVo = new MemberVO();		// 회원 정보를 저장한 객체를 생성함.
		mVo.setName(name);					// MemberVO 객체인 mVo에 회원 가입 폼에서 입력 받은 데이터를 저장함.
		mVo.setUserid(userid);
		mVo.setPwd(pwd);
		mVo.setEmail(email);
		mVo.setPhone(phone);
		mVo.setAdmin(Integer.parseInt(admin));
		
		MemberDAO mDao = MemberDAO.getInstance();
		int result = mDao.insertMember(mVo);  // MemberVO 객체를 전달인자 주어 MemberDAO 객체로  insertMember 메소드를 호출함.
		
		HttpSession session=request.getSession();  // session에 userid를 키로 하여 지금 막 회원 가입한 회원의 아이디를 값으로 저장해 두고 login.jsp로 제어를 이동하여 로그인 작업을 할 떄 아이디를 입력받는 수고를 덜어줌.
		
		if(result==1) {
			session.setAttribute("userid", mVo.getUserid());
			request.setAttribute("message", "회원 가입에 성공했습니다.");
		} else {
			request.setAttribute("message", "회원 가입에 실패했습니다.");
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("member/login.jsp");   // 회원 가입에 성공했으면 로그인 페이지로 이동함.
		dispatcher.forward(request, response);
				
	
		//doGet(request, response);
	}

}
