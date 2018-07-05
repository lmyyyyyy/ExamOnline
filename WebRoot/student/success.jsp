<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%  
  Object student = session.getAttribute("student");  
  if(null == student){  
	  out.print("<script>alert('用户已经退出，确定后返回登录页面。');window.location.href='../exam/login.jsp'</script>");
  }  
%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'success.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
.about-title
{
	color: #FFF;
	background: #9B59B6;
	font-family: 'Open Sans', sans-serif;
	text-align: center;
	font-size: 2.5em;
	padding: 0.6em 0em;
}

</style>
<script type="text/javascript">
text = "您已完成本次考试，请耐心等待，系统正在玩命的计算成绩！";
i = 0;
function type(){
	str = text.substr(0,i);
	document.getElementById("about-title").innerHTML = str + "_";
	i++;
	if (i > text.length)
		i=0;
	setTimeout("type()",200);
}
</script>
  </head> 
  
  <body onload="type()">
    <div>
    	<div class="about-title" id="about-title">您已完成本次考试，请耐心等待，系统正在玩命的计算成绩！</div>
    	<div class="about-title" style="color:#000;font-size:20px;font-weight:bold;background:#fff;">点击<s:a action="PaperAction_scurrentPaper">这里</s:a>返回</div>
    </div>
  </body>
</html>
