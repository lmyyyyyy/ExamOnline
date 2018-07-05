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
     background-color: #DDDDDD;
     border-top-left-radius:1em;
	border-top-right-radius:1em;
	border-bottom-right-radius:1em;
	border-bottom-left-radius:1em;
}   
th,td {     padding: .65em;   }   
</style>
  </head>
  
  <body>
    <div>
    	<s:form action="MultipleChoiceAction_doAdd" namespace="/" method="post">
            <table>
              <tr>
                <td><span>题号：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="number" value="%{MxNumber}" cssClass="text" readOnly="readOnly"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>number</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>问题： &nbsp;&nbsp; </span></td>
                <td><s:textfield name="question" cssClass="text" /></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>question</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>选项A：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="choicea" cssClass="text" /></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>choicea</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>选项B：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="choiceb" cssClass="text" /></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>choiceb</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>选项C：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="choicec" cssClass="text" /></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>choicec</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>选项D：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="choiced" cssClass="text" /></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>choiced</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>选项E：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="choicee" cssClass="text" /></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>choicee</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>选项F：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="choicef" cssClass="text" /></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>choicef</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>答案：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="answer" cssClass="text" /></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>answer</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>科目：&nbsp;&nbsp; </span></td>
                
                <%-- <s:select label="科目" name="subject" list="subjectsList" headerKey="" headerValue="" value="name" listKey="name" listValue="name"></s:select> --%>
                <td><select name="subject">
                	<s:iterator value="subjectsList">
                		<option value='<s:property value="name"/>'><s:property value="name"/></option>
                	</s:iterator>
                </select></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>subject</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>难度：&nbsp;&nbsp; </span></td>
                <td>
	                <select name="difficult">
	                	<option value="1">1</option>
	                	<option value="2">2</option>
	                	<option value="3">3</option>
	                	<option value="4">4</option>
	                	<option value="5">5</option>
	                </select>（1-5难度递增）
               	</td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>difficult</s:param></s:fielderror></div></td>
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
