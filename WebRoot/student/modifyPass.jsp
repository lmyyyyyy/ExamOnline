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
    
    <title>My JSP 'modifyPass.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<style>
table {  
width:500px;
height:300px;
margin-left: auto; 
margin-right: auto;
border-collapse: collapse;       
font-family: Futura, Arial, sans-serif;  
background:#f2f2f2;
 border-top-left-radius: 10px;
 border-top-right-radius: 10px;
 border-bottom-right-radius: 10px;
 border-bottom-left-radius: 10px;
}  
td {    border: 0px solid #777;text-align:center;   }  
.modify{
	width:60px;
	height:40px;
	background:#33AECC;
	border:1px solid #33AECC;
	 border-top-left-radius:0.5em;
	border-top-right-radius:0.5em;
	border-bottom-right-radius:0.5em;
	border-bottom-left-radius:0.5em;
	cursor:pointer;
	color:#fff;
	font-size:15px;
	font-weight:bold;
}
</style>
  </head>
  
  <body>
    <div>
    	<form action="ModifySPasswordAction_modifyPass" method="post">
    	<table>
    		<tr>
    			<td>旧密码:</td>
    			<td><input type="text" name="oldPassword"></input></td>
    			<td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>oldPassword</s:param></s:fielderror></div></td>
    		</tr>
    		<tr>
    			<td>新密码:</td>
    			<td><input type="password" name="newPassword"></input></td>
    			<td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>newPassword</s:param></s:fielderror></div></td>
    		</tr>
    		<tr>
    			<td>确认新密码:</td>
    			<td><input type="password" name="confirmPassword"></input></td>
    			<td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>confirmPassword</s:param></s:fielderror></div></td>
    		</tr>
    		<tr>
    			<td style="margin:0 auto;"><input type="submit" class="modify" value="修改"/></td>
    		</tr>
    	</table>
    	</form>
    </div>
  </body>
</html>
