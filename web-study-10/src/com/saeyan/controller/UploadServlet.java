package com.saeyan.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;  // 중복되는 파일 이름을 알아서 처리해주기 위한 import
/**
 * Servlet implementation class UploadServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Upload.do" })
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String savePath = "upload"; // 여기를 바꿔주면 다운받는 경로가 바뀜
		int uploadFileSizeLimit = 5*1024*1024;
		String encType = "UTF-8";
		
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		System.out.println("서버상의 실제 디렉토리: ");
		System.out.println(uploadFilePath);
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request,  // request 객체
					uploadFilePath, // 서버상의 실제 디렉토리
					uploadFileSizeLimit,  // 최대 업로드 파일 크기
					encType,  // 인코딩 방법
					new DefaultFileRenamePolicy());  // 동일한 이름이 존재하면 새로운 이름이 부여됨
			
			String fileName = multi.getFilesystemName("uploadFile");  // 업로드된 파일의 이름 얻기
			
			if(fileName == null) {  // 파일이 업로드 되지 않았을 떄
				System.out.println("파일 업로드 되지 않았음");				
			} else {  // 파일이 업로드 되었을 떄
				out.println("<br> 글쓴이 : "+multi.getParameter("name"));
				out.println("<br> 제 &nbsp; 목 : "+multi.getParameter("title"));
				out.println("<br> 파일명 : "+multi.getParameter("fileName"));
			}
		} catch(Exception e) {
			System.out.println("예외 발생 : "+e);
			}
					
		}	
		
		//doGet(request, response);
	}


