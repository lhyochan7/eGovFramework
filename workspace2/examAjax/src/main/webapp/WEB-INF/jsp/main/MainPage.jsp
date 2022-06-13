<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link type="text/css" rel="stylesheet"
    href="<c:url value='/css/egovframework/com/com.css' />">
<link
    href="<c:url value='/css/egovframework/com/cop/tpl/egovBaseTemplate.css' />"
    rel="stylesheet" type="text/css">
<script type="text/javascript"
    src="https://code.jquery.com/jquery-1.7.1.min.js"></script>
</head>

<script>
function fn_getClsList(type) {
    
    //초기화 조건
    if(type == 1) {
        var searchText = "";
        var searchOpt = "0";
    } else {
        //초기화 조건
        var searchText = $("#searchText").val();
        var searchOpt = $("#searchOpt").val();
    }
    
    
    const listBody = document.getElementById("listBody");
    
    while (listBody.hasChildNodes()) {
          listBody.removeChild(listBody.firstChild);
    }
    
    $.ajax({
        async : true, // 기본값은 true
        type : "POST",
        url : ($("#contextPath").val() + "/main/actionSearch.do"),
        data :
        {
            searchText : searchText,
            searchOpt : searchOpt
        },
        dataType : "json",
        success : function(res) {
            console.log(res);

            var dataList = "";
            
            res.clsList.forEach(function(item, index) {
            //tempHtml += "<h2 id=" + index + " value=" + item.intteNm + ">" + item.intteNm + "</h2>";
               
                dataList +=
                   "<tr>" +
                   "<td>" + (index + 1) + "</td>" +
                   "<td>" + item.clsNm + "</td>" +
                   "<td>" + item.subjNm + "</td>" +
                   "<td>" + item.clsStDate + " ~ " + item.clsFnsDate + "</td>" +
                   "<td>" + item.useYn + "</td>" +
                   "<td>" + item.cretDt + "</td>" +
                   "</tr>";
            });

            $("#listBody").append(dataList);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(errorThrown)
            alert("통신 실패.");
        }
    });
}

$(document).ready(function(){
    fn_getClsList()
}
);

</script>

<body>
<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>">

    </br>

    <div class="board">

        <h3 style="float: left; width: 50%; margin:0px;">휴스타 (로그인된 학원명) - 강의</h3>
        <div style="float: left; width: 50%;">
            <form id ="searchForm" name="searchForm" action="javascript:fn_getClsList();" method="post">
                <select id="searchOpt" name="searchOpt">
                    <option value="0">전체</option>
                    <option value="1">강의명</option>
                    <option value="2">과목명</option>
                </select> <input type="text" id="searchText" name="searchText" placeholder="검색어를 입력하세요" />
                <button type="button" id="searchBTN" onclick="javascript:fn_getClsList(0);">검색</button>
                <button type="reset" id="resetBTN" onclick="javascript:fn_getClsList(1);">초기화</button>
            </form>
        </div>

        <table class="board_list">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>강의명</th>
                    <th>과목명</th>
                    <th>강의시간</th>
                    <th>사용여부</th>
                    <th>등록일</th>
                </tr>
            </thead>

            <tbody class="ov" id="listBody">
              
            </tbody>
        </table>
    </div>

    <div>
        </br>
        <button>강의등록</button>
    </div>


</body>
</html>
