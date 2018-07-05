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
	<script type="text/javascript"  src="js/jquery-1.3.2.min.js" ></script>
	 <script type="text/javascript">
	 
	 function change(){
			var danxuanfen = document.getElementById("singleCount").value * document.getElementById("singleScore").value;
			var duoxuanfen = document.getElementById("multipleCount").value * document.getElementById("multipleScore").value;
			var tiankongfen = document.getElementById("blankCount").value * document.getElementById("blankScore").value;
			var panduanfen = document.getElementById("judgmentCount").value * document.getElementById("judgmentScore").value;
			
			var zongfen = danxuanfen+duoxuanfen+tiankongfen+panduanfen;
			 if(zongfen != 100)
             {
                 alert("系统总分不是100分了","系统提示");
                 document.getElementById("totalScore").value = zongfen;
             }
			 document.getElementById("totalScore").value = zongfen;
	}

	 $(function(){
        	 var danxuanfen=$("#singleCount").val()*$("#singleScore").val();
             var duoxuanfen=$("#multipleCount").val()*$("#multipleScore").val();
             var tiankongfen=$("#blankCount").val()*$("#blankScore").val();
             var panduanfen=$("#judgmentCount").val()*$("#judgmentScore").val();
             $("#totalScore").val(danxuanfen+duoxuanfen+tiankongfen+panduanfen);
             
	 }); 
 </script>
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
    	<s:form action="PaperAction_doAdd" namespace="/" method="post">
            <table style="width:400px;">
            <tbody>
            <tr><div style="color:#f00;font-size:14px;"><s:actionerror></s:actionerror></div></tr>
              <tr>
                <td><span>试卷号：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="number" value="%{sjNumber}" cssClass="text" readOnly="readOnly"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>number</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>试卷名： &nbsp;&nbsp; </span></td>
                <td><s:textfield name="name" cssClass="text" /></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>name</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>单选数量：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="singleCount"  id="singleCount" cssClass="text" value="20" onblur="change()" /></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>singleCount</s:param></s:fielderror></div></td>
              </tr>
               <tr>
                <td><span>单选分数：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="singleScore" id="singleScore" cssClass="text" value="2" onblur="change()"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>singleScore</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>多选数量&nbsp;&nbsp; </span></td>
                <td><s:textfield name="multipleCount" id="multipleCount" cssClass="text" value="5" onblur="change()"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>multipleCount</s:param></s:fielderror></div></td>
              </tr>
               <tr>
                <td><span>多选分数：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="multipleScore" id="multipleScore" cssClass="text" value="4" onblur="change()"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>multipleScore</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>填空数量：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="blankCount" id="blankCount" cssClass="text" value="10" onblur="change()"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>blankCount</s:param></s:fielderror></div></td>
              </tr>
               <tr>
                <td><span>填空分数：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="blankScore" id="blankScore" cssClass="text" value="2" onblur="change()"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>blankScore</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>判断数量：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="judgmentCount" id="judgmentCount" cssClass="text" value="5" onblur="change()"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>judgmentCount</s:param></s:fielderror></div></td>
              </tr>
               <tr>
                <td><span>判断分数：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="judgmentScore" id="judgmentScore" cssClass="text" value="4" onblur="change()"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>judgmentScore</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>总分数：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="score" cssClass="text"  id="totalScore" onblur="change()"/></td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>score</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>试卷状态：&nbsp;&nbsp; </span></td>
                <td>
                	<select name="status" >
                		<option value="0">关闭</option>
                		<option value="1">打开</option>
                	</select>
                </td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>status</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><span>试卷风格：&nbsp;&nbsp; </span></td>
                <td>
					<select name="style">
                		<option value="随机组卷">随机组卷</option>
                		<option value="顺序组卷">顺序组卷</option>
                	</select>
				</td>
                <td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>style</s:param></s:fielderror></div></td>
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
                <td><s:submit value="组卷"/> </td>
                <td><input type="reset" value="重置"></td>
              </tr>
              </tbody>
            </table>
            <br>
          </s:form>
    </div>
  </body>
</html>
