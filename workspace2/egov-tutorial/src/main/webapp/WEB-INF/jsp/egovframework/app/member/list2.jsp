<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link href="<c:url value='/css/egovframework/com/cop/tpl/egovBaseTemplate.css' />" rel="stylesheet" type="text/css">

</head>
<body>
<div class="board">
	<table class="board_list">
		<thead>
			<tr>
				<th></th>
				<th>이름</th>
				<th>아이디</th>
				<th>생성일</th>
			</tr>
		</thead>
		
		<tbody class="ov">
			<c:forEach var="member" items="${memberList}" varStatus="memberStatus">
			<tr>
				<td>${memberStatus.index + 1}</td>
				<td>${member.memberName}</td>
				<td>${member.memberPhone}</td>
				<td>${member.createAt}</td>
			</tr>
			</c:forEach>
		
		</tbody>
		
	</table>

</div>
</body>
</html>