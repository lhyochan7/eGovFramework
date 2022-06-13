/**
 * 
 */

$(document).ready(function() {
    
});

function fn_getAjax() {
    var params = null;

    $.ajax({
	    async: true, // 기본값은 true
        type: "GET",
        url: ($("#contextPath").val() + "/test/ajax.do"),
        data: params,
        dataType: "json",
        success: function(res){
            console.log(res);

            //$("#notiList").empty();
            var tempHtml = "";
            res.notiList.forEach(function(item, index) {
                tempHtml += "<li>" + item.notiTitle + "</li>";
            });

            $("#notiList").append(tempHtml);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert("통신 실패.")
        }
    });
}

function fn_getClsAjax() {
    var dataForm = $('<form></form>');
    dataForm.append($('<input/>', {type: 'hidden', name: 'intteSeq', value: '1'}));
    dataForm.append($('<input/>', {type: 'hidden', name: 'clsSeq', value: '1'}));

    $.ajax({
        async: true, // 기본값은 true
        type: "GET",
        url: ($("#contextPath").val() + "/main/intteClsList.do"),
        data: dataForm.serialize(),
        dataType: "json",
        success: function(res){
            console.log(res);

            $("#notiList").empty();
            var tempHtml = "";
            res.clsList.forEach(function(item, index) {
                tempHtml += "<li>" + item.clsNm + "</li>";
            });

            $("#notiList").append(tempHtml);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert("통신 실패.")
        }
    });
}

function fn_getSubmit() {
    $("#frm").submit();
}