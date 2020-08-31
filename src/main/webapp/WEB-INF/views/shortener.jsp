<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>
<head>
<meta charset="UTF-8">
<title>URL Shortener</title>
</head>
<body>

Enter URL <input type="text"  onkeyup="noSpace(this);" onchange="noSpace(this);" id="url"> <button id="shortenBtn" onclick="onShortening();">Shortening</button>

<div style="height:30px"></div>

<div id="result" style="width:300px;height:100px">
&nbsp;<font color="gray">Result URL</font>
</div>

<script type="text/javascript">
function noSpace(obj) { //공백 불가.
    var str_space = /\s/; 
    if(str_space.exec(obj.value)) { 
        obj.value = obj.value.replace(' ',''); // 공백제거
        return false;
    }
    
	if (window.event.keyCode == 13) { //엔터키 누르면 실행.
    	onShortening()
	}

    return true;
}

function checkLength(){ 
	
	//빈문자열 체크.
	if(document.getElementById("url").value == "") {
		alert("변환하려는 문자열을 입력해주세요.");
		return false;
	} 
	
	//글자수 체크.
	if(document.getElementById("url").value.length < 4) {
		alert("최소 4글자 이상을 입력해주세요.");
		return false;
	}
	 
	return true;
}

function onShortening() { //shortening
	if(!checkLength()) {
		return false;
	}
	
	$("#result").empty(); //result div 비우기.
	
	var data = {
	        url : document.getElementById("url").value
	    };
	
    $.ajax({
    	url : "${contextPath}/",
    	type : "POST", 
    	contentType : "application/json",
    	dataType : "json",
    	data : JSON.stringify(data),
    	success : function(data){
    		if(data.status=="success") {
    			var url = location.href + data.message;
    			result.innerHTML = "<a href='" + url +"'>" + url +"</a>";
    		} else {
    			alert("fail:" + data.message);
    		}
    		
		}, error : function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
    });

}
</script>

</body>
</html>
