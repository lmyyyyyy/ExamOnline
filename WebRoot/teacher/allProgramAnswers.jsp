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
    
    <title>My JSP 'studentListPage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<style type="text/css">
table { margin-left: auto; margin-right: auto;border-collapse: collapse;table-layout:fixed;  font-family: Futura, Arial, sans-serif;  }     
th,td {  padding: .65em;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;}  
th {  background: #555 nonerepeat scroll 0 0;   /* border: 1px solid #777; */   color: #000000;}  
td {    border: 0px solid #777;text-align:center;   }  
tbody tr:nth-child(odd) {    background: #ccc;   } 
th:first-child {  border-radius: 9px 0 0 0;  }   
th:last-child {  border-radius: 0 9px 0 0;  }   
tr:last-child td:first-child { border-radius: 0 0 0 9px;  }   
tr:last-child td:last-child {   border-radius: 0 0 9px 0;   }
</style>

  </head>
  
  <body>
    <div>
    	<table>
    	<s:if test="paList.isEmpty() == true">目前没有任何可批阅的试卷!</s:if >
		
		<s:else>
			<table>
				<tr>
					<th class="tdListHeader">科目</th>
					<th class="tdListHeader">学生学号</th>
					<th class="tdListHeader">学生姓名</th>
					<th class="tdListHeader">班级</th>
					<th class="tdListHeader">试卷名称</th>
					<th class="tdListHeader">每题分值</th>
					<th class="tdListHeader">试卷总分</th>
					<th class="tdListHeader">时间</th>
					<th class="tdListHeader">批阅</th>
				</tr>
				<s:iterator value="paList">
					<s:set var="sId" value="id" />
					<tr>
						<td><s:property value="subject" /></td>
						<td><s:property value="snumber" /></td>
						<td><s:property value="sname" /></td>
						<td><s:property value="sclas" /></td>
						<td><s:property value="ename" /></td>
						<td><s:property value="escore" /></td>
						<td><s:property value="score" /></td>
						<td><s:date name="regDate" format="MM/dd/yyyy" /></td>
						<td><s:a action="ProgramAnswersAction_modify?sid=%{#sId}" namespace="/" cssClass="aList">批阅</s:a></td>
					</tr>
				</s:iterator>
			</table>
		</s:else>
    	</table>
    </div>
  </body>
</html>
