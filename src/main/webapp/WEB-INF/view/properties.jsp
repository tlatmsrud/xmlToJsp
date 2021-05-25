<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
	<script src="http://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="collapse navbar-collapse" id="navbarNav">
	    <ul class="navbar-nav">
	    	<c:forEach items="${fileNameList}" var="fileNameList" varStatus="status">
	    		<li class="nav-item">
	        		<a class="nav-link">${fileNameList}</a>
	      		</li>
			</c:forEach>
	    </ul>
	</div>
</nav>
<div class="printArea">

</div>
<button type="button" class="saveBtn" style="display:none">저장</button>
<button type="button" class="addBtn" style="display:none">추가</button>
<script>
$(document).ready(function(){
	$(".nav-link").click(function(){
		$(".addBtn").css('display','');
		$(".saveBtn").css('display','');
		$(".printArea").empty();
		
		var click_val = $(this);
		
		$.ajax({
			url : "http://localhost:8080/test/properties/"
			,type : "POST"
			,dataType : "json"
			,data : {
				"fileName" : click_val.text()
			}
			,success : function(res){
				var xmlFileName = "<input type='hidden' class = 'xmlFileName' value = "+click_val.text()+">";
				$(".printArea").append(xmlFileName);
				$.each(res.fileData, function(key, value){
					var propObj = "<div class='propDiv'><input type='text' class = 'propKey' value = "+key+">"
									+"<input type='text' class = 'propValue' value = "+value+">"
									+"<input type='button' class = 'delBtn' value = '삭제'></div>";
					$(".printArea").append(propObj);
					
				})
			}
			,error : function(error){
				alert("error : "+ JSON.stringify(error));
			}
		}) 
	})
	
	$(".saveBtn").click(function(){
		var param = new Array();
 		var length = $(".propKey").length;
 		
		for(var i =0; i<length; i++){
			var data ={
					'propKey' : $(".propKey")[i].value
					,'propValue' : $(".propValue")[i].value
			}
			param.push(data);
		}
		
		alert(JSON.stringify(param));
		$.ajax({
			url : "http://localhost:8080/test/saveProperties/"
			,type : "POST"
			,traditional : true
			,data : {
				"fileName" : $(".xmlFileName").val()
				,"fileData" : JSON.stringify(param)
			}
			,success : function(res){
				alert("success");
			}
			,error : function(error){
				alert("error : "+ JSON.stringify(error));
			}
		}) 
	})
	
	$(".addBtn").click(function(){
		var propObj = "<div class='propDiv'><input type='text' class = 'propKey'>"
						+"<input type='text' class = 'propValue'>"
						+"<input type='button' class = 'delBtn' value = '삭제'></div>";
		$(".printArea").append(propObj);
	})
	
	$(document).on("click",".delBtn",function(){
		$(this).closest("div").remove();
	})
})
</script>
</body>
</html>
