<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%  
  Object student = session.getAttribute("student");  
  if(null == student){  
	  out.print("<script>alert('用户已经退出，确定后返回登录页面。');window.location.href='../exam/login.jsp'</script>");
  }  
%> 
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Editor</title>
<style type="text/css">
body{
	background:#000;
}
#editor{
	width:60%;
	height:770px;
	border:1px solid #ccc;
	float:left;
}
#result{width:300px;height:600px;float:left;margin-top:15px;margin-left:20px;}
 #btn{
        height:30px;
        width:100px;
     	position: absolute;
		border-radius:5px;
		background:#eee;
		color:#000;
    }
 #command{height:50px;width:100%;}
 #show{width:100%;height:400px;margin-top:0px;}
 label{padding:3px 10px;background:#26c6da;border-radius:5px 5px 0px 0px;margin-bottom: 0px;color:#fff;}
 #list{width:100%;height:400px;background:#000;border-top-width:0px;color:#fff;font-size:0.7em;font-weight:bold;
 border-top-left-radius:0.5em;
	border-top-right-radius:0.5em;
	border-bottom-right-radius:0.5em;
	border-bottom-left-radius:0.5em;
 }
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
<pre id="editor">public class Main{
	public static void main (String[] args) {
		System.out.println("Hello World!");
	}
}</pre></div>

					<div class="col-lg-5 col-md-3 col-sm-6 col-xs-12">
					<div id="result">
                    	<!-- <div id="command">
		                    <input type="button" id="btn" value="试运行">
                    	</div> -->
                        <div id="show">
                        	<div style="border-bottom:1px solid #eee;height:auto;width:100%;">
                            	<label id="btn1">运行输出</label>
                                <label id="clear">清空输出</label>
                            </div>
                            <div style="height:auto;width:100%;">
                            	<textarea id="list" readOnly></textarea>
                            </div>
                        	
                        </div>
                    </div>
                </div>
<script type="text/javascript"  src="../js/jquery-1.3.2.min.js" ></script>
<script src="../ace-builds-master/src-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>
<script src="../ace-builds-master/src-noconflict/ext-language_tools.js"></script>
<script>
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
