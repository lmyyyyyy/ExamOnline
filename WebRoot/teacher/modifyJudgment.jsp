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
    	<s:form action="JudgmentAction_saveOrUpdate" namespace="/" method="post">
    		<s:hidden name="sid"/>
    		<s:hidden name="id"/>
            <table>
              <tr><s:a action="JudgmentAction_allJudgment">返回</s:a></tr>
              <tr>
                <td><span>题号：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="number"  cssClass="text" readOnly="readOnly"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>number</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>问题： &nbsp;&nbsp; </span></td>
                <td><s:textfield name="question" cssClass="text" /></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>question</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>答案：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="answer" cssClass="text" /></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>answer</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>作者：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="author" cssClass="text" /></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>author</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>科目：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="subject" cssClass="text" /></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>subject</s:param></s:fielderror></div></td>
              </tr>
             <tr>
                <td><span>难度：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="difficult" cssClass="text" /></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>difficult</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><s:submit value="修改"/> </td>
                <td><input type="reset" value="重置"></td>
              </tr>
            </table>
            <br>
          </s:form>
    </div>
  </body>
</html>
