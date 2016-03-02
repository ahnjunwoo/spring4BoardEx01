<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script type="text/javascript">
var bno = 1;
var str = '';
$(function() {
	$("#replyAddBtn").on("click",function(){
		var replyer = $("#newReplyWriter").val();
		var replytext = $("#newReplyText").val();
		
	 	$.ajax({
	 		type : 'post',
	 		url : '/replies',
	 		headers : {
	 			"Content-Type" : "application/json",
	 			"X-HTTP-Method-Override" : "POST"
	 		},
	 		dataType : 'text',
	 		data : JSON.stringify({
	 			bno : bno,
	 			replyer : replyer,
	 			replytext : replytext
	 		}),
	 		success : function(result){
	 			
	 			if(result == 'SUCCESS'){
	 				alert("��� �Ǿ����ϴ�.");
	 				getAllList();
	 			}
	 		}
	 	});
	});
});

function getAllList(){
	$.getJSON("/replies/all/"+bno,function(data){
		console.log(data.length);
		
		$(data).each(
			function(){
				str += "<li data-rno='"+this.rno+"' class='replyLi'>"
				+ this.rno + ":" + this.replytext
				+ "</li>";
			});	
		
		$("#replies").html(str);
	});
}
</script>

<body>
	<h2>Ajax Test Page</h2>
	<div>
		<div>
			REPLYER <input type="text" name="replyer" id="newReplyWriter">
		</div>
		<div>
			REPLY TEXT <input type="text" name="replytext" id="newReplyText">
		</div>
		<button id="replyAddBtn">ADD REPLY</button>
		<ul id="replies">
		</ul>
	</div>
</body>
</html>