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
table { margin-left: auto; margin-right: auto;border-collapse: collapse;width:80%;table-layout:fixed;   font-family: Futura, Arial, sans-serif;  }     
th,td {  padding: .65em;  }  
th {  background: #555 nonerepeat scroll 0 0;   /* border: 1px solid #777; */    color: #000000;  }  
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
    	<s:if test="teacherList.isEmpty() == true">目前没有任何教师!</s:if >
		
		<s:else>
			<table>
				<tr>
					<th class="tdListHeader">ID</th>
					<th class="tdListHeader">教师编号</th>
					<th class="tdListHeader">教师姓名</th>
					<th class="tdListHeader">教师注册时间</th>
					<th class="tdListHeader">修改</th>
					<th class="tdListHeader">删除</th>
				</tr>
				<s:iterator value="teacherList">
					<s:set var="tId" value="id" />
					<tr>
						<td><s:property value="id" /></td>
						<td><s:property value="number" /></td>
						<td><s:property value="name" /></td>
						<td><s:date name="regDate" format="MM/dd/yyyy" /></td>
						<td><s:a action="TeacherListAction_modifyTea?tid=%{#tId}" namespace="/" cssClass="aList">修改</s:a></td>
						<td><s:a action="TeacherListAction_deleteTea?tid=%{#tId}" namespace="/" cssClass="aList">删除</s:a></td>
					</tr>
				</s:iterator>
				
				<tr><td><s:a href="manager/addTeacher.jsp">添加教师</s:a></td></tr>
			</table>
		</s:else>
		
    	</table>
    </div>
     <div style="background:#FFFFFF;margin-left: auto; margin-right: auto;text-align:center">
     
     <tr>
					<td>(当前有${request.page.totalCount }条记录，共${request.page.totalPage }页，当前第${page.currentPage }页)</td>
					<td align="center">
						<s:if test="#request.page.hasPrePage">
							<a href="TeacherListAction_findTeacherList.action?currentPage=1">首页</a>
							<a href="TeacherListAction_findTeacherList?currentPage=${page.currentPage -1 }">上一页</a>
						</s:if>
						<s:else>
							首页
							上一页
						</s:else>
						
						<s:if test="#request.page.hasNextPage">
							<a href="TeacherListAction_findTeacherList.action?currentPage=${page.currentPage + 1 }">下一页</a>
							<a href="TeacherListAction_findTeacherList.action?currentPage=${page.totalPage }">尾页</a>			
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
