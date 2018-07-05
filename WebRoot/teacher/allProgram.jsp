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
    	<tbody>
    	<s:if test="programList.isEmpty() == true">目前没有任何编程题!</s:if >
		
		<s:else>
			<table>
				<tr>
					<th style="width:18%" class="tdListHeader">题号</th>
					<th style="width:15%" class="tdListHeader">问题</th>
					<th style="width:7%" class="tdListHeader">科目</th>
					<th style="width:7%" class="tdListHeader">作者</th>
					<th style="width:12%" class="tdListHeader">时间</th>
					<th style="width:7%" class="tdListHeader">难度</th>
					<th class="tdListHeader">查看</th>
					<th class="tdListHeader">修改</th>
					<th class="tdListHeader">删除</th>
				</tr>
				<s:iterator value="programList">
					<s:set var="sId" value="id" />
					<tr>
						<td><s:property value="number" /></td>
						<td ><s:property value="question"/></td>
						<td><s:property value="subject" /></td>
						<td><s:property value="author" /></td>
						<td><s:date name="regDate" format="MM/dd/yyyy" /></td>						
						<td><s:property value="difficult" /></td>
						
						<td><s:a action="ProgramAction_view?sid=%{#sId}" namespace="/" cssClass="aList">查看</s:a></td>
						<td><s:a action="ProgramAction_modify?sid=%{#sId}" namespace="/" cssClass="aList">修改</s:a></td>
						<td><s:a action="ProgramAction_delete?sid=%{#sId}" namespace="/" cssClass="aList">删除</s:a></td>
					</tr>
				</s:iterator>
			
			</table>
		</s:else>
		</tbody>
    	</table>
    </div>
    <div style="background:#FFFFFF;margin-left: auto; margin-right: auto;text-align:center">
    	<tr sytle="width:200px">
					<td>(当前有${request.page.totalCount }条记录，共${request.page.totalPage }页，当前第${page.currentPage }页)</td>
					<td align="center">
						<s:if test="#request.page.hasPrePage">
							<a href="ProgramAction_allProgram.action?currentPage=1">首页</a>
							<a href="ProgramAction_allProgram.action?currentPage=${page.currentPage -1 }">上一页</a>
						</s:if>
						<s:else>
							首页
							上一页
						</s:else>
						
						<s:if test="#request.page.hasNextPage">
							<a href="ProgramAction_allProgram.action?currentPage=${page.currentPage + 1 }">下一页</a>
							<a href="ProgramAction_allProgram.action?currentPage=${page.totalPage }">尾页</a>			
						</s:if>
						<s:else>
							下一页
							尾页
						</s:else>
					</td>
				</tr>
    </div>
  </body>
</html>
