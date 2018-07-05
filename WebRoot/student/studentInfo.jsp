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
    
    <title>My JSP 'teacherInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <style>
  body{
  	background:#fff;
  }
table {  
margin-top:50px;
margin-left: auto; 
margin-right: auto;
     border-collapse: collapse;  
     font-family: Futura, Arial, sans-serif;   
     background-color: #f2f2f2;
     border-top-left-radius:1em;
	border-top-right-radius:1em;
	border-bottom-right-radius:1em;
	border-bottom-left-radius:1em;
}   
th,td {     padding: .65em;
	border:1px solid #ccc;
   }   
</style>
  </head>
  
  <body>
    <div>
    	<table class="t_info">
    	<tbody>
    		<tr>
    			<td>姓名:</td>
    			<td>
					 <s:property value="#session['student'].name" />&nbsp;&nbsp;
				</td>
    		</tr>
    		<tr>
    			<td>编号:</td>
    			<td>
					 <s:property value="#session['student'].number" />&nbsp;&nbsp;
				</td>
    		</tr>
    		<tr>
    			<td>专业:</td>
    			<td>
					 <s:property value="#session['student'].major" />&nbsp;&nbsp;
				</td>
    		</tr>
    		<tr>
    			<td>班级:</td>
    			<td>
					 <s:property value="#session['student'].clas" />&nbsp;&nbsp;
				</td>
    		</tr>
    		<tr>
    			<td>入学年限:</td>
    			<td>
					 <s:property value="#session['student'].syear" />&nbsp;&nbsp;
				</td>
    		</tr>
    		<tr>
    			<td>注册时间:</td>
    			<td>
					 <s:date name="#session['student'].regDate" format="yyyy-MM-dd" />
				</td>
    		</tr>
    		</tbody>
    	</table>
    </div>
  </body>
</html>
