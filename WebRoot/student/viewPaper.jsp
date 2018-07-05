<%@ page language="java" import="java.util.*,com.exam.online.domain.PaperItem,com.exam.online.domain.SingleChoice,com.exam.online.service.impl.*;" pageEncoding="utf-8"%>
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
    
    <title>My JSP 'addStudent.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
body{
	background:#fff;
}
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
 #clear{
 cursor:pointer;
 }
 #saveProgram{
 	width:60px;
 	height:40px;
 	background:#26c6da;
 	border:1px solid #26c6da;
 	float:left;
 	cursor:pointer;
 	border-top-left-radius:0.5em;
	border-top-right-radius:0.5em;
	border-bottom-right-radius:0.5em;
	border-bottom-left-radius:0.5em;
 }
 #saveProgram:hover{
 	color:#fff;
 }
 #submit{
 	width:80px;
 	height:40px;
 	background:#19a97b;
 	border:1px solid #19a97b;
 	float:left;
 	cursor:pointer;
 	border-top-left-radius:0.5em;
	border-top-right-radius:0.5em;
	border-bottom-right-radius:0.5em;
	border-bottom-left-radius:0.5em;
	font-size:16px;
	font-weight:bold;
	color:#fff;
 }
 #submit:hover{
 	color:#f00;
 }
 #count{
 	margin-left:10px;
 	width:70px;
 	height:40px;
 	float:left;
 }
 .message{
 	color:#f00;
 	margin-top:10px;
 }
table {  
     border-collapse: collapse;  
     font-family: Futura, Arial, sans-serif;   
     border-top-left-radius:1em;
	border-top-right-radius:1em;
	border-bottom-right-radius:1em;
	border-bottom-left-radius:1em;
}   
td { padding: .65em; }
.submit{
	width:60px;
	height:40px;
	background:#19a97b;
	border:1px solid #19a97b;
	 border-top-left-radius:0.5em;
	border-top-right-radius:0.5em;
	border-bottom-right-radius:0.5em;
	border-bottom-left-radius:0.5em;
	cursor:pointer;
	color:#fff;
	font-size:15px;
	font-weight:bold;
}
#btn1{
	cursor:pointer;
}
#btn1:hover{
	color:#f00;
}
#clear:hover{
	color:#f00;
}
#countdown{
	width:200px;
	height:auto;
	border:1px solid #f00;
	font-size:15px;
	font-weight:bold;
	color:#f00;
}
</style>

<script language="javascript">
    window.onload = function ()
    {
    var oCountDown = document.getElementById("countdown");
    var aInput = oCountDown.getElementsByTagName("input")[0];
    aInput.style.display="none";
    var timer = null;
    aInput.onclick = function ()
        {
            this.className == "" ? (timer = setInterval(updateTime, 1000), updateTime()) : (clearInterval(timer));
            this.className = this.className == '' ? "cancel" : '';
        };
    function format(a)
        {
            return a.toString().replace(/^(\d)$/,'0$1');
        }
    function updateTime ()
        {
            var aSpan = oCountDown.getElementsByTagName("span");
            var oRemain = aSpan[0].innerHTML.replace(/^0/,'') * 60 + parseInt(aSpan[1].innerHTML.replace(/^0/,''));
            if(oRemain <= 0)
        {
            clearInterval(timer);
            document.forms["doIt"].submit();
            return;
        }
            oRemain--;
            aSpan[0].innerHTML = format(parseInt(oRemain / 60));
            oRemain %= 60;
            aSpan[1].innerHTML = format(parseInt(oRemain));
        }
        // 加上此句即可。
        aInput.click();
    }
    </script>
    <script language="javascript">
	
    function savePrograms(){
    	var sid = document.getElementById("pid").value;
    	var code1 = editor.getValue();
    	var count = document.getElementById("count").value;
    	var url_check = "PaperAction_saveProgram.action?count="+count+"&sid="+sid;
    	//alert("aaa");
    	$.ajax({
    	type: "POST",
    	url: url_check,
    	dataType: "text",
    	data: "code="+encodeURIComponent(code1),
    	success: function(result){
                if (result == "0") {
                	alert("系统原因-保存失败。请重试!");
                    return true;
                }
                else if(result == "1"){
                	alert("保存成功!");
                    return false;
                }
    	 }
    	});
    }
    </script>
    <script language="javascript">  
		function sumbit_sure(){  
			var gnl=confirm("确定要提交?");  
			if (gnl==true){  
				return true;  
			}else{  
				return false;  
			}  
		}  
	</script> 
  </head>
  
  <body>
    <div>
    <form name="doIt" action="PaperAction_saveAnswer" method="post" onsubmit="return sumbit_sure()">
    <s:hidden name="sid"/>
    <%
    	String[] count = {"一","二","三","四"};
    	int num = 0;
    %>
            <table>
             	<tr>
             		<td align="center" ><div style="font-size:20px;font-weight:bold;color:#19a97b;">试卷名称：${requestScope.paperList.name}; 总分：<s:property value="score"/>分</div>
             		<div align="center" id="countdown">
						考试时间还剩<span>120</span>分钟<span>00</span>秒
						<input type="button" value="开始"/>
					</div> 
				</td>
             	
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
             						<td><%=++i %>.<s:property value="question"/>(<font color="red"><s:property value="singleChoiceScore"/>分</font>)</td>
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
             				<s:iterator value="multipleList"  var="multiple">
             					<s:set var="sId" value="id" />
             					
             					<tr>
             						<td><%=++j %>.<s:property value="question"/>(<font color="red"><s:property value="multipleChoiceScore"/>分</font>)</td>
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
             						<td><%=++m %>.<s:property value="question"/>(<font color="red"><s:property value="judgmentsScore"/>分</font>)</td>
             					</tr>
             					<tr>
             						<td>
             							<input type="radio"  value="T" name="<s:property value="number"/>"> 正确<br>
             							<input type="radio"  value="F" name="<s:property value="number"/>"> 错误<br>
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
             	<%
             		int n = 0;
             	%>
             	<tr>
             		<td>
             			<table>
             				<s:iterator value="blankList" status="blank">
             					<s:set var="sId" value="id" />
             					
             					<tr>
             						<td><%=++n %>.<s:property value="question"/>(<font color="red"><s:property value="blanksScore"/>分</font>)</td>
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
             	
             	
             	<s:if test="programList.isEmpty() == true">
             	<tr>
             		<td><input type="submit" value="交卷" class="submit"/></td>
             	</tr>
             	</s:if >
             	</form>
             	<s:else>
             	<form action="PaperAction_finishSave" method="post" onsubmit="return sumbit_sure()">
             	<s:hidden name="sid" id="pid" />
             	<tr>
             		<td>
             			编程题
             		</td>
             	</tr>
             	<%
             		int h = 0;
             	%>
             	<tr>
             		<td>
             			<table>
             				<s:iterator value="programList" var="program">
             					<s:set var="sId" value="id" />
             					<tr>
             						<td><%=++h %>.<s:property value="question"/>(<font color="red"><s:property value="programsScore"/>分</font>)</td>
             					</tr>
             					
             				</s:iterator>
             			</table>
             		</td>
             	</tr>
             	<tr>
             						<td>
            	
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
                	</td>
                	</tr>
                	<tr>
             		<td>		
             		<%
             			String[] number = {"一","二","三","四","五"};
             			int b = 0;
             			int c = 0;
             		%>
                <input type="button" id="saveProgram" onclick="savePrograms()" value="保存" />
       			<select name="count" id="count">
       			<s:iterator value="programList">
       				<option value="<%=++c %>">第<%=number[b++] %>道</option>
				</s:iterator>
       			</select>
       			<div class="message">(必须保存每一道题!)</div>
       			</td>
             		</tr>	
             		<tr>
             		<td>
       			<input type="submit" id="submit" onclick="javascript:return confirm("您确认要提交表单吗？");" value="提交" />
       				</td>
       				</tr>
       				</form>
             	</s:else>
             	
            </table>
            <br>
           
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
                url: "compileAction.action",
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
