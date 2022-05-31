<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<style type="text/css">
* {
	text-decoration: none;
}

.menu_a a {
	font-size: 25px;
	color: darkgoldenrod;
	font-family: "隶书";
}

.one {
	font-size: 30px;
	font-family: "隶书";
}

#title {
	text-align: center;
	font-size: 40px;
	font-family: "隶书";
}

.text_css {
	font-size: 25px;
	color: darkgoldenrod;
	font-family: "隶书";
}

b {
	color: red;
	font-size: 20px;
}

p {
	font-size: 40px;
	font-family: "隶书";
}
.masg{
	width: 300px;
	height: 35px;
    font-size: 20px;
    margin-left: 1100px;
     margin-top: -80px;
}
.masg span{
    color: red;
    font-size: 30px;
    font-family: "隶书";
}
</style>
</head>
<body>
	<p id="title">
		<font color="lightskyblue" face="微软雅黑">用户管理系统</font>
	<div class="masg">
		欢迎:<span>【${currentUserInfo.user_name}】</span>&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="../userInfo/logout.do" target="_parent">退出</a>
	</div>
	</p>
	
	
</body>
</html>