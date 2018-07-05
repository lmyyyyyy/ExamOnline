<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Home</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
 
   <%
   Thread.sleep(1000);
   if (session.getAttribute("student") != null) {
    session.removeAttribute("student");
   }
   if (session.getAttribute("teacher") != null) {
   	session.removeAttribute("teacher");
   }
   if (session.getAttribute("manager") != null) {
	   session.removeAttribute("manager");
   }

   	
   	session.invalidate();
   	out.print("<script>alert('用户即将退出，确定后退出该页面。');window.top.location.href='exam/login.jsp'</script>");
	/* response.sendRedirect("http://localhost:8080/ExamOL/login.jsp"); */
    %>
<%--      <% 
			String url = (String)request.getAttribute("url1");
			response.setHeader("Refresh","0.5;url=" + url);
		%> --%>
  </body>
</html>
