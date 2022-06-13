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
<link type="text/css" rel="stylesheet"
    href="<c:url value='/css/mainPage.css'/>">
<title>로그인 페이지</title>
</head>

<script type="text/javascript"
    src="https://code.jquery.com/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
    function fn_getIntteList() {
        $.ajax({
            async : true, // 기본값은 true
            type : "GET",
            url : ($("#contextPath").val() + "/main/intteNameList.do"),
            data : "",
            dataType : "json",
            success : function(res) {
                console.log(res);

                var tempHtml = "";
                res.nameList.forEach(function(item, index) {
                //tempHtml += "<h2 id=" + index + " value=" + item.intteNm + ">" + item.intteNm + "</h2>";
                    tempHtml += "<option id=" + item.intteSeq + " value=" + item.intteSeq +">" + item.intteNm + "</option>";
                });

                $("#intteList").append(tempHtml);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert("통신 실패.");
            }
        });
    }
    
    var message = '${message}';
    
    if(message == 'true') {
        alert("로그인이 되었습니다");
        window.location.href="<%=request.getContextPath()%>/main/mainPage.do"
    } else if (message == 'false') {
        alert("입력된 정보로 조회된 내용이 없습니다 ");
    }
    
</script>

<body onload="fn_getIntteList()">
<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>">

    <div class="container">
    <h3 id="loginTitle">로그인</h3>

    <fieldset id="form">
    <legend>로그인 정보 입력 폼</legend>
        <form name="loginForm" id="loginForm" action="<%=request.getContextPath()%>/main/actionLogin.do" method="post">
            <div id="formInputs">
                <select class="formInputs" id="intteList" name="intteSeq">
                    <option value="" disabled selected>학원을 선택해 주세요</option>
                </select>
                <input class="formInputs" type="text" name="userId" placeholder="사용자 아이디"></input></br> 
            </div>
            <button type="submit" id="loginBTN">로그인</button>
        </form>
    </fieldset>
</div>
</body>
</html>