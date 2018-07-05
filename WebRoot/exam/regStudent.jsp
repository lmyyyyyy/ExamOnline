<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
  <head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<meta name="keywords" content="scclui框架">
	<meta name="description" content="scclui为轻量级的网站后台管理系统模版。">
    <title>学生注册</title>
	
	<link rel="stylesheet" href="../common/layui/css/layui.css">
	<link rel="stylesheet" href="../common/css/sccl.css">
	<link rel="stylesheet" href="../css/style.css">
	<script type="text/javascript"  src="../js/jquery-1.3.2.min.js" ></script>
	<script src="../js/register.js" type="text/javascript" charset="utf-8"></script>
    
  </head>
  
  <body class="login-bg" onload="type()">
    <div class="login-box" style="height:auto;">
        <header>
            <h1 id="login_txt_bt">学生注册</h1>
        </header>
        <div class="login-main" style="height:auto;">
			<form action="RegAction_doReg" class="layui-form" method="post">
				<input name="__RequestVerificationToken" type="hidden" value="">                
				<div class="layui-form-item">
					<label class="login-icon">
						<i class="layui-icon"></i>
					</label>
					<input type="text" name="number" lay-verify="number" autocomplete="off" placeholder="这里输入学号" class="layui-input" onblur="isExist(this.value)"></input><div class='availability_status'></div>
					<div class="validate" style="color:#f00;font-size:12px;"><s:fielderror><s:param>number</s:param></s:fielderror></div>
				</div>
				<div class="layui-form-item">
					<label class="login-icon">
						<i class="layui-icon"></i>
					</label>
					<input type="password" name="password" lay-verify="password" autocomplete="off" placeholder="这里输入密码" class="layui-input">
					<div style="color:#f00;font-size:12px;"><s:fielderror><s:param>password</s:param></s:fielderror></div>
				</div>
				<div class="layui-form-item">
					<label class="login-icon">
						<i class="layui-icon"></i>
					</label>
					<input type="password" name="confirmPassword" lay-verify="password" autocomplete="off" placeholder="这里确认密码" class="layui-input">
					<div style="color:#f00;font-size:12px;"><s:fielderror><s:param>confirmPassword</s:param></s:fielderror></div>
				</div>
				<div class="layui-form-item">
					<label class="login-icon">
						<i class="layui-icon"></i>
					</label>
					<input type="text" name="name" lay-verify="name" autocomplete="off" placeholder="这里输入姓名" class="layui-input">
					<div style="color:#f00;font-size:12px;"><s:fielderror><s:param>name</s:param></s:fielderror></div>
				</div>
				<div class="layui-form-item">
					<label class="login-icon">
						<i class="layui-icon"></i>
					</label>
					<input type="text" name="syear" lay-verify="syear" autocomplete="off" placeholder="这里输入入学年限(如:2017)" class="layui-input">
					<div style="color:#f00;font-size:12px;"><s:fielderror><s:param>syear</s:param></s:fielderror></div>
				</div>
				<div class="layui-form-item">
					<label class="login-icon">
						<i class="layui-icon"></i>
					</label>
					<input type="text" name="major" lay-verify="major" autocomplete="off" placeholder="这里输入专业(如:软件工程)" class="layui-input">
					<div style="color:#f00;font-size:12px;"><s:fielderror><s:param>major</s:param></s:fielderror></div>
				</div>
				<div class="layui-form-item">
					<label class="login-icon">
						<i class="layui-icon"></i>
					</label>
					<input type="text" name="clas" lay-verify="clas" autocomplete="off" placeholder="这里输入班级(如:软件二班)" class="layui-input">
					<div style="color:#f00;font-size:12px;"><s:fielderror><s:param>clas</s:param></s:fielderror></div>
				</div>
				<div class="layui-form-item">
					<div class="pull-right">
						<button class="layui-btn layui-btn-primary" lay-submit="" lay-filter="login">
							<i class="layui-icon"></i> 注册
						</button>
					</div>
					<div class="clear"></div>
				</div>
			</form>        
		</div>
        <footer>
            <p>版权所有 © LIUMINGYU</p>
        </footer>
    </div>
    <script type="text/html" id="code-temp">
        <div class="login-code-box">
            <input type="text" class="layui-input" id="code" />
            <img id="valiCode" src="/manage/validatecode?v=636150612041789540" alt="验证码" />
        </div>
    </script>
    <script src="../common/layui/layui.js"></script>
    <script>
    
        layui.use(['layer', 'form'], function () {
            var layer = layui.layer,
				$ = layui.jquery,
				form = layui.form();

            form.verify({
            	number: function (value) {
                    if (value === '')
                        return '请输入学号';
                },
                password: function (value) {
                    if (value === '')
                        return '请输入密码';
                },
                name: function (value) {
                    if (value === '')
                        return '请输入姓名';
                },
                syear: function (value) {
                    if (value === '')
                        return '请输入入学年限';
                },
                major: function (value) {
                    if (value === '')
                        return '请输入专业';
                },
                clas: function (value) {
                    if (value === '')
                        return '请输入班级';
                }
            });

            var errorCount = 0;

            form.on('submit(login)', function (data) {
				window.location.href = "exam/index.html";
                /*if (errorCount > 5) {
                    layer.open({
                        title: '<img src="' + location.origin + '/Plugins/layui/images/face/7.gif" alt="[害羞]">输入验证码',
                        type: 1,
                        content: document.getElementById('code-temp').innerHTML,
                        btn: ['确定'],
                        yes: function (index, layero) {
                            var $code = $('#code');
                            if ($code.val() === '') {
                                layer.msg('输入验证码啦，让我知道你是人类。');
                                isCheck = false;
                            } else {
                                $('input[name=verifyCode]').val();
                                var params = data.field;
                                params.verifyCode = $code.val();
                                submit($,params);
                                layer.close(index);
                            }
                        },
                        area: ['250px', '150px']
                    });
                    $('#valiCode').off('click').on('click', function () {
                        this.src = '/manage/validatecode?v=' + new Date().getTime();
                    });
                }else{
                    submit($,data.field);
                }

                return false;*/
            });

        });
		
        /*function submit($,params){
            $.post('/manage/login',params , function (res) {
                if (!res.success) {
                    if (res.data !== undefined)
                        errorCount = res.data.errorCount
                    layer.msg(res.message,{icon:2});
                }else
                {
                    layer.msg(res.message,{icon:1},function(index){
                        layer.close(index);
                        location.href='/manage';
                    });
                }
            }, 'json');
        }*/
    </script>
  </body>
</html>

