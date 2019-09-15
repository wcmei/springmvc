<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("button").click(function(){
			$.ajax({
				url:"/my/test7",
				success:function(data){
					alert(data);
				}
			});
		});
	});
</script>
</head>
<body>
	<a href="${pageContext.request.contextPath }/my/test1">test1</a>
	<br>============================================================<br>
	<a href="${pageContext.request.contextPath }/my/test2">test2</a>
	<br>============================================================<br>
	<form action="${pageContext.request.contextPath }/my/test3">
		姓名：<input type="text" name="name"/><br>
		年龄：<input type="text" name="age"/><br>
		爱好：<input type="checkbox" name="interests"/>篮球
			<input type="checkbox" name="interests"/>游泳
			<input type="checkbox" name="interests"/>读书
			<br>
		<input type="submit" value="test3">
	</form>
	<br>============================================================<br>
	<a href="${pageContext.request.contextPath }/my/xiaowan/123456">test4</a>
	<br>============================================================<br>
	<a href="${pageContext.request.contextPath }/my/test5">test5</a>
	<br>============================================================<br>
	<form action="${pageContext.request.contextPath }/my/test6" method="post">
		姓名：<input type="text" name="name"/><br>
		年龄：<input type="text" name="age"/><br>
		爱好：<input type="checkbox" name="interests" value="篮球"/>篮球
			<input type="checkbox" name="interests" value="游泳"/>游泳
			<input type="checkbox" name="interests" value="读书"/>读书<br>
		学校：<input type="text" name="school.sname"><br>
		地址：<input type="text" name="school.saddress"><br>
		<input type="submit" value="test6">
	</form>
	<br>============================================================<br>
	<a href="${pageContext.request.contextPath }/my/test7">test7</a>
	<br>============================================================<br>
	<a href="${pageContext.request.contextPath }/my/test8">test8</a>
	<br>============================================================<br>
	<a href="${pageContext.request.contextPath }/my/test9">test9</a>
	<br>============================================================<br>
	<form action="${pageContext.request.contextPath }/my/test10" method="post" enctype="multipart/form-data">
		文件上传：<input type="file" name="file"><br>
		<input type="submit" value="test10">
	</form>
	<br>============================================================<br>
	<a href="${pageContext.request.contextPath }/my/test11">test11</a>
	<br>============================================================<br>
	<form action="${pageContext.request.contextPath }/my/test12" method="post">
		年龄：<input type="text" name="age" value="${age }"/>${ageErrors }<br>
		生日：<input type="text" name="birthday" value="${birthday }"/>${birthErrors }<br>
		<input type="submit" value="test12">
	</form>
	<br>============================================================<br>
	<form action="${pageContext.request.contextPath }/my/test13" method="post">
		姓名：<input type="text" name="name"/>${nameErrorMSG }<br>
		分数：<input type="text" name="score"/>${scoreErrorMSG }<br>
		电话：<input type="text" name="mobile">${mobileErrorMSG }<br>
		<input type="submit" value="test13">
	</form>
</body>
</html>