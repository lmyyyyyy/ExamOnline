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
    
    <title>My JSP 'addStudent.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style>
table {  
margin-left: auto; 
margin-right: auto;
border-collapse: collapse;       
font-family: Futura, Arial, sans-serif;  
background:#f2f2f2;
width:30%; 
height:55%;
 border-top-left-radius: 10px;
 border-top-right-radius: 10px;
 border-bottom-right-radius: 10px;
 border-bottom-left-radius: 10px;
}  
td {    border: 0px solid #777;   }  
</style>
  </head>
  
  <body>
    <div>
    	<s:form action="TeacherListAction_doAdd" namespace="/" method="post">
            <table>
              <tr><s:a action="TeacherListAction_findTeacherList">返回</s:a></tr>
              <tr>
                <td><span>编号：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="number" cssClass="text"  onblur="isExist(this.value)"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>number</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>密码： &nbsp;&nbsp; </span></td>
                <td><s:password name="password" cssClass="text" /></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>password</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>姓名：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="name" cssClass="text" /></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>name</s:param></s:fielderror></div></td>
              </tr>
              <tr>
               <td><s:submit value="添加"/> </td>
               <td><input type="reset" value="重置"></td>
              </tr>
            </table>
            <br>
          </s:form>
    </div>
  </body>
</html>
