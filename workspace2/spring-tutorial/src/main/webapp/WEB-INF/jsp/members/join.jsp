<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/spring-mvc/members/joinV2" method="post">
		ID : <input type="text" name="id"/>	<br/>
		이름 : <input type="text" name="name"/>
		<button type="submit">등록</button>
	</form>

</body>
</html>