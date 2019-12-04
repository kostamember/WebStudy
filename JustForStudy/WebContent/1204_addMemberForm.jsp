<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="1204_addMember.jsp">
		<table>
			<tr>
				<td>이름 :   </td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>아이디 :   </td>
				<td><input type="text" name="userid"></td>
			</tr>
			<tr>
				<td>별명 :   </td>
				<td><input type="text" name="nickname"></td>
			</tr>
			<tr>
				<td>비밀번호 :   </td>
				<td><input type="text" name="pwd"></td>
			</tr>
			<tr>
				<td>이메일 :   </td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>전화번호 :   </td>
				<td><input type="text" name="phone"></td>
			</tr>
						
			<tr>
				<td> <input type="submit" value="전송"> </td>
				<td> <input type="reset" value="취소"> </td>
			</tr>
		</table>
	</form>
</body>
</html>