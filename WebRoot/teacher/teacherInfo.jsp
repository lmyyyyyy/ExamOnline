<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'teacherInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
table {  
margin-left: auto; 
margin-right: auto;
     border-collapse: collapse;  
     font-family: Futura, Arial, sans-serif;   
     background-color: #DDDDDD;
     border-top-left-radius:1em;
	border-top-right-radius:1em;
	border-bottom-right-radius:1em;
	border-bottom-left-radius:1em;
}   
td {     padding: .65em;   }
</style>
  </head>
  
  <body>
    <div>
    	<table class="t_info">
    		<tr>
    			<td>姓名:</td>
    			<td>
					 <s:property value="#session['teacher'].name" />&nbsp;&nbsp;
				</td>
    		</tr>
    		<tr>
    			<td>编号:</td>
    			<td>
					 <s:property value="#session['teacher'].number" />&nbsp;&nbsp;
				</td>
    		</tr>
    		<tr>
    			<td>注册时间:</td>
    			<td>
					 <s:date name="#session['teacher'].regDate" format="MM/dd/yy" />
				</td>
    		</tr>
    	</table>
    </div>
  </body>
</html>
