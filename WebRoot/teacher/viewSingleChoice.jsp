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
    	<s:form action="SingleChoiceAction_saveOrUpdate" namespace="/" method="post">
    		<s:hidden name="sid"/>
    		<s:hidden name="id"/>
            <table>
              <tr><s:a action="SingleChoiceAction_allSingleChoice">返回</s:a></tr>
              <tr>
                <td><span>题号：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="number"  cssClass="text" readOnly="readOnly"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>number</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>问题： &nbsp;&nbsp; </span></td>
                <td><s:textfield name="question" cssClass="text"  readOnly="readOnly"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>question</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>选项A：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="choicea" cssClass="text" readOnly="readOnly"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>choicea</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>选项B：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="choiceb" cssClass="text" readOnly="readOnly"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>choiceb</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>选项C：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="choicec" cssClass="text" readOnly="readOnly"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>choicec</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>选项D：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="choiced" cssClass="text" readOnly="readOnly"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>choiced</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>答案：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="answer" cssClass="text" readOnly="readOnly"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>answer</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>作者：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="author" cssClass="text" readOnly="readOnly"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>author</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>科目：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="subject" cssClass="text" readOnly="readOnly"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>subject</s:param></s:fielderror></div></td>
              </tr>
               <tr>
                <td><span>创建时间：&nbsp;&nbsp; </span></td>
                <td><s:date name="regDate" format="yyyy-MM-dd"/></td>
              </tr>
             <tr>
                <td><span>难度：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="difficult" cssClass="text" readOnly="readOnly"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>difficult</s:param></s:fielderror></div></td>
              </tr>
            </table>
            <br>
          </s:form>
    </div>
  </body>
</html>
