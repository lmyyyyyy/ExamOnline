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
table { margin-left: auto; margin-right: auto;border-collapse: collapse; width:90%;table-layout:fixed;  font-family: Futura, Arial, sans-serif;  }     
th,td {  padding: .65em;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;}  
th {  background: #555 nonerepeat scroll 0 0;   /* border: 1px solid #777; */   color: #000000;}  
td {    border: 0px solid #777;   }  
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
    	<tbody>
    	<s:if test="paperList.isEmpty() == true">目前没有任何试卷!</s:if >
		
		<s:else>
			<table>
				<tr>
					<th class="tdListHeader"style="width:17%">试卷号</th>
					<th class="tdListHeader"style="width:15%">试卷名称</th>
					<!-- <td class="tdListHeader">单选数量</td>
					<td class="tdListHeader">多选数量</td>
					<td class="tdListHeader">填空数量</td>
					<td class="tdListHeader">判断数量</td>
					<td class="tdListHeader">编程数量</td> -->
					<th class="tdListHeader">科目</th>
					<th class="tdListHeader"style="width:10%">作者</th>
					<th class="tdListHeader">难度</th>
					<th class="tdListHeader"style="width:12%">状态</th>
					<th class="tdListHeader">总分</th>
					<th class="tdListHeader"style="width:10%">类型</th>
					<th class="tdListHeader">查看</th>
				</tr>
				<s:iterator value="paperList">
					<s:set var="sId" value="id" />
					<tr>
						<td><s:property value="number" /></td>
						<td><s:property value="name" /></td>
						<%-- <td><s:property value="singleCount" /></td>
						<td><s:property value="multipleCount" /></td>
						<td><s:property value="blankCount" /></td>
						<td><s:property value="judgmentCount" /></td>
						<td><s:property value="programCount" /></td> --%>
						<td><s:property value="subject" /></td>
						<td><s:property value="author" /></td>
						<%-- <td><s:date name="regDate" format="MM/dd/yyyy" /></td> --%>
						<td><s:property value="difficult" /></td>
						<td>
						<s:if test='status == 0'>关闭</s:if>
							<s:else>正在考试</s:else>
						</td>
						<td><s:property value="score" /></td>
						<td><s:property value="style" /></td>
						<td><s:a action="PaperAction_view?sid=%{#sId}" namespace="/" cssClass="aList">查看</s:a></td>
					</tr>
				</s:iterator>
			</table>
		</s:else>
		</tbody>
    	</table>
    </div>
  </body>
</html>
