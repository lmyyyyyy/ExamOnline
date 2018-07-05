<%@ page language="java" import="java.util.*,com.exam.online.domain.PaperItem,com.exam.online.domain.SingleChoice,com.exam.online.service.impl.*" pageEncoding="utf-8"%>
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
     border-collapse: collapse;  
     font-family: Futura, Arial, sans-serif;   
     background-color: #DDDDDD;
     border-top-left-radius:1em;
	border-top-right-radius:1em;
	border-bottom-right-radius:1em;
	border-bottom-left-radius:1em;
}   
td { padding: .65em; }
</style>
  </head>
  
  <body>
    <div>
    <%
    	String[] count = {"一","二","三","四"};
    	int num = 0;
    %>
            <table>
             	<tr>
             		<td>试卷名称：${requestScope.paperList.name}</td>
             	</tr>
             	<s:if test="singleList.isEmpty() == true"></s:if >
             	<s:else>
             	<tr>
             		<td>
             			<%=count[num++] %>、单选题
             		</td>
             	</tr>
             	
             	<tr>
             		<td>
             			<table>
             			<%
             				int i = 0;
             			%>
             				<s:iterator value="singleList">
             					<s:set var="sId" value="id" />
             					
             					<tr>
             						<td><%=++i %>.<s:property value="question"/>(<font color="red"><s:property value="singleChoiceScore"/>分值</font>)</td>
             					</tr>
             					<tr>
             						<td>
             							<input type="radio"  value="A" name="<s:property value="number"/>"> A.<s:property value="choicea"/><br>
             							<input type="radio"  value="B" name="<s:property value="number"/>"> B.<s:property value="choiceb"/><br>
             							<input type="radio"  value="C" name="<s:property value="number"/>"> C.<s:property value="choicec"/><br>
             							<input type="radio"  value="D" name="<s:property value="number"/>"> D.<s:property value="choiced"/>
             						</td>
             					</tr>
             				</s:iterator>
             			</table>
             		</td>
             	</tr>
             	</s:else>
             	
             	
             	<s:if test="multipleList.isEmpty() == true"></s:if >
             	<s:else>
             	<tr>
             		<td>
             			<%=count[num++] %>、多选题
             		</td>
             	</tr>
             	
             	<tr>
             		<td>
             			<table>
             			<%
             				int j = 0;
             			%>
             				<s:iterator value="multipleList" var="multiple">
             					<s:set var="sId" value="id" />
             					
             					<tr>
             						<td><%=++j %>.<s:property value="question"/>(<font color="red"><s:property value="multipleChoiceScore"/>分值</font>)</td>
             					</tr>
             					<tr>
             						<td>
             							<input type="checkbox"  value="A" name="<s:property value="number"/>"> A.<s:property value="choicea"/><br>
             							<input type="checkbox"  value="B" name="<s:property value="number"/>"> B.<s:property value="choiceb"/><br>
             							<input type="checkbox"  value="C" name="<s:property value="number"/>"> C.<s:property value="choicec"/><br>
             							<input type="checkbox"  value="D" name="<s:property value="number"/>"> D.<s:property value="choiced"/><br>
             							<s:if test='#multiple.choicee != \"\"'>
           								<input type="checkbox" value="E" name="<s:property value="number"/>"> E.<s:property value="choicee"/><br>
           								</s:if>
           								<s:if test='#multiple.choicef != \"\"'>
           								<input type="checkbox" value="F" name="<s:property value="number"/>"> F.<s:property value="choicef"/><br>
           								</s:if>
             						</td>
             					</tr>
             				</s:iterator>
             			</table>
             		</td>
             	</tr>
             	</s:else>
             	
             	
             	
             		<s:if test="judgmentList.isEmpty() == true"></s:if >
             	<s:else>
             	<tr>
             		<td>
             			<%=count[num++] %>、判断题
             		</td>
             	</tr>
             	
             	<tr>
             		<td>
             			<table>
             			<%
             				int m = 0;
             			%>
             				<s:iterator value="judgmentList" status="multiple">
             					<s:set var="sId" value="id" />
             					
             					<tr>
             						<td><%=++m %>.<s:property value="question"/>(<font color="red"><s:property value="judgmentsScore"/>分值</font>)</td>
             					</tr>
             					<tr>
             						<td>
             							<input type="radio"  value="1" name="<s:property value="number"/>"> 正确<br>
             							<input type="radio"  value="0" name="<s:property value="number"/>"> 错误<br>
             						</td>
             					</tr>
             				</s:iterator>
             			</table>
             		</td>
             	</tr>
             	</s:else>
             	
             	
             	<s:if test="blankList.isEmpty() == true"></s:if >
             	<s:else>
             	<tr>
             		<td>
             			<%=count[num++] %>、填空题
             		</td>
             	</tr>
             	
             	<tr>
             		<td>
             			<table>
             			<%
             				int n = 0;
             			%>
             				<s:iterator value="blankList" status="blank">
             					<s:set var="sId" value="id" />
             					
             					<tr>
             						<td><%=++n %>.<s:property value="question"/>(<font color="red"><s:property value="blanksScore"/>分值</font>)</td>
             					</tr>
             					<tr>
             						<td>
             							答案：<input type="text" name="<s:property value="number"/>"><br>
             						</td>
             					</tr>
             				</s:iterator>
             			</table>
             		</td>
             	</tr>
             	</s:else>
             	
             	
             	<s:if test="programList.isEmpty() == true"></s:if >
             	<s:else>
             	<tr>
             		<td>
             			编程题
             		</td>
             	</tr>
             	
             	<tr>
             		<td>
             			<table>
             			<%
             				int h = 0;
             			%>
             				<s:iterator value="programList" status="blank">
             					<s:set var="sId" value="id" />
             					
             					<tr>
             						<td><%=++h %>.<s:property value="question"/>(<font color="red"><s:property value="programsScore"/>分值</font>)</td>
             					</tr>
             					<tr>
             						<td>
             							答案：<input type="text" name="<s:property value="number"/>"><br>
             						</td>
             					</tr>
             				</s:iterator>
             			</table>
             		</td>
             	</tr>
             	</s:else>
            </table>
            <br>
    </div>
  </body>
</html>
