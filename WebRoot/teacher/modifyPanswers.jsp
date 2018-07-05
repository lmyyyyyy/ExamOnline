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
background:#DDDDDD;
width:80%; 
height:55%;
 border-top-left-radius: 10px;
 border-top-right-radius: 10px;
 border-bottom-right-radius: 10px;
 border-bottom-left-radius: 10px;
}  
td {    border: 0px solid #777;   }  
#editor{
	width:600px;
	height:400px;
	border:1px solid #ccc;
	float:left;
}
#result{
	margin-left:50px;
	width:300px;
	height:400px;
	float:left;
	
}
 #btn{
        height:30px;
        width:100px;
     	position: absolute;
		border-radius:5px;
		background:#eee;
		color:#000;
    }
 #command{height:50px;width:100%;}
 #show{width:100%;height:300px;margin-top:0px;}
 label{padding:3px 10px;background:#26c6da;border-radius:5px 5px 0px 0px;margin-bottom: 0px;color:#fff;}
 #list{width:100%;height:350px;background:#000;border-top-width:0px;color:#fff;font-size:0.7em;font-weight:bold;
 border-top-left-radius:0.5em;
	border-top-right-radius:0.5em;
	border-bottom-right-radius:0.5em;
	border-bottom-left-radius:0.5em;}
 #btn1{
 	cursor:pointer;
 }
 #btn1:hover{
 	color:#0f0;
 }
 #clear{
 cursor:pointer;
 }
 #clear:hover{
 	color:#0f0;
 }
</style>
  </head>
  
  <body>
    <div>
    	<s:form action="ResultsAction_updateScore" namespace="/" method="post">
    		<s:hidden name="sid"/>
    		<s:hidden name="id"/>
    		<s:a action="ProgramAnswersAction_findAll">返回</s:a>
            <table>
              
              <tr>
                <td><span>科目：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="subject"  cssClass="text" readOnly="readOnly" /></td>
              </tr>
              <tr>
                <td><span>学号： &nbsp;&nbsp; </span></td>
                <td><s:textfield name="snumber" cssClass="text" readOnly="readOnly" /></td>
              </tr>
              <tr>
                <td><span>姓名：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="sname" cssClass="text" readOnly="readOnly" /></td>
              </tr>
              <tr>
                <td><span>班级：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="sclas" cssClass="text" readOnly="readOnly" /></td>
              </tr>
              <tr>
                <td><span>试卷号：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="enumber" cssClass="text" readOnly="readOnly" /></td>
              </tr>
             <tr>
                <td><span>试卷名称：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="ename" cssClass="text" readOnly="readOnly" /></td>
              </tr>
              <tr>
                <td><span>每题分值：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="escore" cssClass="text" readOnly="readOnly" /></td>
              </tr>
              <tr>
                <td><span>试卷总分：&nbsp;&nbsp; </span></td>
                <td><s:textfield name="score" cssClass="text" readOnly="readOnly" /></td>
              </tr>
             </table>
              
              	<%
             	int h = 0;
             %>
    		<s:if test="programList.isEmpty() == true"></s:if>
    		<s:else>
    		<table>
    		<s:iterator value="programList" var="program">
   					<s:set var="sId" value="id" />
   					<tr>
   						<td><%=++h %>.<s:property value="question"/>(<font color="red"><s:property value="escore"/>分</font>)</td>
   					</tr>
             	</s:iterator>
             </table>
             </s:else>
              <table>
              	<tr>
	                <td><span>第一道： &nbsp;&nbsp; </span></td>
	                <td><s:textarea name="first" cssClass="text" style="width:500px;height:200px;" readOnly="readOnly" /></td>
	                
            	</tr>
            	<tr>
	                <td><span>第二道： &nbsp;&nbsp; </span></td>
	                <td><s:textarea name="second" cssClass="text" style="width:500px;height:200px;" readOnly="readOnly" /></td>
            	</tr>
            	<tr>
	                <td><span>第三道： &nbsp;&nbsp; </span></td>
	                <td><s:textarea name="third" cssClass="text" style="width:500px;height:200px;" readOnly="readOnly" /></td>
            	</tr>
            	<tr>
	                <td><span>第四道： &nbsp;&nbsp; </span></td>
	                <td><s:textarea name="forth" cssClass="text" style="width:500px;height:200px;" readOnly="readOnly" /></td>
            	</tr>
            	<tr>
	                <td><span>第五道： &nbsp;&nbsp; </span></td>
	                <td><s:textarea name="fifth" cssClass="text" style="width:500px;height:200px;" readOnly="readOnly" /></td>
            	</tr>
              <tr>
              	<td>最终得分:</td>
              	<td><input type="text" name="allScore" /></td>
              	<td><div style="color:#f00;font-size:12px;"><s:fielderror><s:param>totalScore</s:param></s:fielderror></div></td>
              </tr>
              <tr>
                <td><s:submit value="提交"/> </td>
                <td><input type="reset" value="重置"></td>
              </tr>
            </table>
            <br>
          </s:form>
          
          <pre id="editor">public class Main{
	public static void main (String[] args) {
		System.out.println("Hello World!");
	}
}</pre>
       		      
                <div class="col-lg-5 col-md-3 col-sm-6 col-xs-12">
					<div id="result">
                    	<!-- <div id="command">
		                    <input type="button" id="btn" value="试运行">
                    	</div> -->
                        <div id="show">
                        	<div style="border-bottom:1px solid #eee;height:auto;width:100%;">
                            	<label id="btn1">调试输出</label>
                                <label id="clear">清空输出</label>
                            </div>
                            <div style="height:auto;width:100%;">
                            	<textarea id="list" readOnly></textarea>
                            </div>
                        	
                        </div>
                    </div>
                </div>
    </div>
<script type="text/javascript"  src="js/jquery-1.3.2.min.js" ></script>
<script src="ace-builds-master/src-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>
<script src="ace-builds-master/src-noconflict/ext-language_tools.js"></script>
<script>
     /* var editor = ace.edit("editor");
    editor.setTheme("ace/theme/twilight");
    editor.getSession().setMode("ace/mode/java");
     editor.setTheme("ace/theme/tomorrow");
    // enable autocompletion and snippets
    editor.setOptions({
        enableBasicAutocompletion: true,
        enableSnippets: true,
        enableLiveAutocompletion: false});  */
        ace.require("ace/ext/language_tools");
        var editor = ace.edit("editor");
        editor.setOptions({
            enableBasicAutocompletion: true,
            enableSnippets: true,
            enableLiveAutocompletion: true
        });
        editor.setTheme("ace/theme/monokai");
        editor.getSession().setMode("ace/mode/java");
        editor.getSession().setTabSize(2);
        document.getElementById('editor').style.fontSize='14px';
        editor.getSession().setUseWrapMode(true);
        editor.setHighlightActiveLine(false);
      
        
</script>

 <script type="text/javascript">
 $(document).ready(function(){
            $("#btn1").click(function(){
             var code1 = editor.getValue();
             $.ajax({
                type: 'POST',
                url: "compileTeaAction.action",
                data: "code="+encodeURIComponent(code1),
                success : function(result){ 
       				$("#list").append(result);
 				} 
            });
            });
        });  
</script>
<script type="text/javascript">
            $(document).ready(function(){
                $("#clear").click(function(){
                    $("#list").text('');                     
            	 });
            }); 
</script>


<script type="text/javascript">  

function hotkey(){  
    var a=window.event.keyCode;
    //alert(a);
    if((a==13)&&(event.ctrlKey)){
        $('#btn1').click();
    }
}// end hotkey  
document.onkeydown = hotkey;

</script>
  </body>
</html>
