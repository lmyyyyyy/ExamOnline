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
    	<s:if test="subjectsList.isEmpty() == true">目前没有任何科目!</s:if >
		
		<s:else>
			<table>
				<tr>
					<th class="tdListHeader">ID</th>
					<th class="tdListHeader">科目名称</th>
					<th class="tdListHeader">科目描述</th>
					<th class="tdListHeader">修改</th>
					<th class="tdListHeader">删除</th>
				</tr>
				<s:iterator value="subjectsList">
					<s:set var="sId" value="id" />
					<tr>
						<td><s:property value="id" /></td>
						<td><s:property value="name" /></td>
						<td><s:property value="des" /></td>
						<td><s:a action="SubjectsAction_modify?sid=%{#sId}" namespace="/" cssClass="aList">修改</s:a></td>
						<td><s:a action="SubjectsAction_delete?sid=%{#sId}" namespace="/" cssClass="aList">删除</s:a></td>
					</tr>
				</s:iterator>
				
			</table>
		</s:else>
		</tbody>
    	</table>
    </div>
    <div style="background:#FFFFFF;margin-left: auto; margin-right: auto;text-align:center">
   	 <tr>
					<td>(当前有${request.page.totalCount }条记录，共${request.page.totalPage }页，当前第${page.currentPage }页)</td>
					<td align="center">
						<s:if test="#request.page.hasPrePage">
							<a href="SubjectsAction_allSubjects.action?currentPage=1">首页</a>
							<a href="SubjectsAction_allSubjects.action?currentPage=${page.currentPage -1 }">上一页</a>
						</s:if>
						<s:else>
							首页
							上一页
						</s:else>
						
						<s:if test="#request.page.hasNextPage">
							<a href="SubjectsAction_allSubjects.action?currentPage=${page.currentPage + 1 }">下一页</a>
							<a href="SubjectsAction_allSubjects.action?currentPage=${page.totalPage }">尾页</a>			
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
