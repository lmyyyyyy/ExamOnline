<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%  
  Object student = session.getAttribute("student");  
  if(null == student){  
	  out.print("<script>alert('用户已经退出，确定后返回登录页面。');window.location.href='../exam/login.jsp'</script>");
  }  
%> 
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
tbody tr:nth-child(odd) {    background: #33AA99;   } 
th:first-child {  border-radius: 9px 0 0 0;  }   
th:last-child {  border-radius: 0 9px 0 0;  }   
tr:last-child td:first-child { border-radius: 0 0 0 9px;  }   
tr:last-child td:last-child {   border-radius: 0 0 9px 0;   }
</style>
  </head>
  
  <body>
    <div>
    	<table>
    	<s:if test="resultsList.isEmpty() == true">目前没有任何成绩可以查询!</s:if >
		
		<s:else>
			<table>
				<tr>
					<th class="tdListHeader">学科</th>
					<th class="tdListHeader">学号</th>
					<!-- <td class="tdListHeader">单选数量</td>
					<td class="tdListHeader">多选数量</td>
					<td class="tdListHeader">填空数量</td>
					<td class="tdListHeader">判断数量</td>
					<td class="tdListHeader">编程数量</td> -->
					<th class="tdListHeader">姓名</th>
					<th class="tdListHeader">班级</th>
					<th class="tdListHeader">试卷号</th>
					<th class="tdListHeader">试卷名称</th>
					<th class="tdListHeader">状态</th>
					<th class="tdListHeader">我的成绩</th>
				</tr>
				<s:iterator value="resultsList">
					<s:set var="sId" value="id" />
					<tr>
						<td><s:property value="subjcet" /></td>
						<td><s:property value="snumber" /></td>
						<%-- <td><s:property value="singleCount" /></td>
						<td><s:property value="multipleCount" /></td>
						<td><s:property value="blankCount" /></td>
						<td><s:property value="judgmentCount" /></td>
						<td><s:property value="programCount" /></td> --%>
						<td><s:property value="sname" /></td>
						<td><s:property value="sclas" /></td>
						<td><s:property value="enumber" /></td>
						<td><s:property value="ename" /></td>
						<td>
						<s:if test='totalScore == null'>未批阅</s:if>
							<s:else>已批阅</s:else>
						</td>
						<td><s:property value="totalScore" /></td>
					</tr>
				</s:iterator>
				
			</table>
		</s:else>
    	</table>
    </div>
    <div style="background:#FFFFFF;margin-left: auto; margin-right: auto;text-align:center">
    <tr>
					<td>(当前有${request.page.totalCount }条记录，共${request.page.totalPage }页，当前第${page.currentPage }页)</td>
					<td align="center">
						<s:if test="#request.page.hasPrePage">
							<a href="ResultsAction_toQueryReuslts.action?currentPage=1">首页</a>
							<a href="ResultsAction_toQueryReuslts.action?currentPage=${page.currentPage -1 }">上一页</a>
						</s:if>
						<s:else>
							首页
							上一页
						</s:else>
						
						<s:if test="#request.page.hasNextPage">
							<a href="ResultsAction_toQueryReuslts.action?currentPage=${page.currentPage + 1 }">下一页</a>
							<a href="ResultsAction_toQueryReuslts.action?currentPage=${page.totalPage }">尾页</a>			
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
