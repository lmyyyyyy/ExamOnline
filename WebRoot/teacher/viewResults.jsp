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
background:#DDDDDD;
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
            <table>
              <tr><s:a action="ResultsAction_allResults">返回</s:a></tr>
              <tr>
                <td><span>科目：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="subjcet"  cssClass="text" readOnly="readOnly"/></td>
              </tr>
              <tr>
                <td><span>学号： &nbsp;&nbsp; </span></td>
                <td><s:textfield name="snumber" cssClass="text" readOnly="readOnly" /></td>
              </tr>
              <tr>
                <td><span>姓名：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="sname" cssClass="text" readOnly="readOnly" /></td>
              </tr>
              <tr>
                <td><span>班级：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="sclas" cssClass="text" readOnly="readOnly" /></td>
              </tr>
              <tr>
                <td><span>试卷号：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="enumber" cssClass="text" readOnly="readOnly" /></td>
              </tr>
              <tr>
                <td><span>试卷名称：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="ename" cssClass="text" readOnly="readOnly" /></td>
              </tr>
              <tr>
                <td><span>总分：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="totalScore" cssClass="text" readOnly="readOnly" /></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>author</s:param></s:fielderror></div></td>
              </tr>
             
            </table>
            <br>
    </div>
  </body>
</html>
