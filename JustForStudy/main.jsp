<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<c:if test="${empty loginUser}">
	<jsp:forward page='login.do' />
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="member.js"></script>
</head>
<body>
<h2> 회원 전용 페이지 </h2>
<form action="Logout.do">
<table>
	<tr>
	<td>
		안녕하세요. ${loginUser.name}(${loginUser.userid})님
	</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="로그아웃"> &nbsp;&nbsp;   <!-- 로그아웃 버튼 클릭하면 logout.do가 요청. 이 요청을 받는 서블릿에서 logout.jsp로 이동시킴. 여기서 인증된 사용자의 인증을 무효화 -->
			<input type="button" value="회원정보변경"				
				onClick="location.href='MemberUpdateServlet?userid=${loginUser.userid}'"> <!-- 회원정보수정 페이지로 이동. 이전에 입력된 회원 정보를 보여주어야 하기 떄문에 memberUpdate.do 요청시 사용자 아이디를 파라미터로 전달. -->
		</td>
	</tr>
					
</table>
</form>
</body>
</html>