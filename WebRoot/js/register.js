
function isExist(number1){
	//定义一个url，带着文本框中的值
	var url_check = "RegAction_validateNum.action?number1="+number1;
	//alert("aaa");
	$.ajax({
	type: "POST",
	url: url_check,
	dataType: "text",
	success: function(result){
		$('.availability_status').ajaxComplete(function(event, request, settings) {
			//如果为0，代表学号已存在，给错误的css:class,移除正确的class
            if (result == "0") {
                $('.availability_status').html('');
                $('.availability_status').removeClass('tick');
                $('.availability_status').addClass('error');
                return true;
            }
            else if(result == "1"){
                $('.availability_status').html('');
                $('.availability_status').removeClass('error');
                $('.availability_status').addClass('tick');
                return false;
            }
        });
	 }
	});
}

function validatePass(password){
	var password = $.trim($("#password").val());
	if(password.length >=6 && password.length <= 20 ){
		 //合法长度为6-20个字符
		 $('.availability_status1').html('');
         $('.availability_status1').removeClass('error');
         $('.availability_status1').addClass('tick');
	} else {
		$('.availability_status1').html('');
        $('.availability_status1').removeClass('tick');
        $('.availability_status1').addClass('error');
	}	
}
function confirm(lgRePassword){
	var lgPassword = $.trim($("#lgPassword").val());
	var lgRePassword = $.trim($("#lgRePassword").val());
	if (lgRePassword == "") {
		$('.availability_status2').html('');
	    $('.availability_status2').removeClass('tick');
	    $('.availability_status2').addClass('error');
	}
	else if(lgRePassword == lgPassword) {
		 $('.availability_status2').html('');
         $('.availability_status2').removeClass('error');
         $('.availability_status2').addClass('tick');
	 } else {
		 $('.availability_status2').html('');
	     $('.availability_status2').removeClass('tick');
	     $('.availability_status2').addClass('error');
	 }
	
}

function validateUser(lgUsername){
	var lgUsername = $.trim($("#lgUsername").val());

	 if(lgUsername.length >=2 && lgUsername.length <= 20 ){
		 //合法长度为6-20个字符
		 $('.availability_status3').html('');
         $('.availability_status3').removeClass('error');
         $('.availability_status3').addClass('tick');
	} else {
		$('.availability_status3').html('');
        $('.availability_status3').removeClass('tick');
        $('.availability_status3').addClass('error');
	}	
}

function validateUser2(lgUsername){
	var lgUsername = $.trim($("#lgUsername").val());

	 if(lgUsername.length >=2 && lgUsername.length <= 20 ){
		 //合法长度为6-20个字符
		 $('.availability_status7').html('');
         $('.availability_status7').removeClass('error');
         $('.availability_status7').addClass('tick');
	} else {
		$('.availability_status7').html('');
        $('.availability_status7').removeClass('tick');
        $('.availability_status7').addClass('error');
	}	
}



function validateSignature(signature){
	var signature = $.trim($("#signature").val());

	 if(signature.length >=0 && signature.length <= 50 ){
		 //合法长度为6-20个字符
		 $('.availability_status4').html('');
         $('.availability_status4').removeClass('error');
         $('.availability_status4').addClass('tick');
	} else {
		$('.availability_status4').html('');
        $('.availability_status4').removeClass('tick');
        $('.availability_status4').addClass('error');
	}	
}

function isExistPassword(lgPassword2){
	var url_check = "validatePassword.me?lgPassword="+lgPassword2;
	$.ajax({
	type: "POST",
	url: url_check,
	dataType: "text",
	success: function(result){
		$('.availability_status5').ajaxComplete(function(event, request, settings) {
            if (result == "0") {
                $('.availability_status5').html('');
                $('.availability_status5').removeClass('tick');
                $('.availability_status5').addClass('error');
                return true;
            }
            else if(result == "1"){
                $('.availability_status5').html('');
                $('.availability_status5').removeClass('error');
                $('.availability_status5').addClass('tick');
                return false;
            }
        });
	 }
	});
}


function validateNewLgPassword(newLgPassword){
	var newLgPassword = $.trim($("#newLgPassword").val());
	if(newLgPassword.length >=6 && newLgPassword.length <= 20 ){
		 //合法长度为6-20个字符
		 $('.availability_status6').html('');
         $('.availability_status6').removeClass('error');
         $('.availability_status6').addClass('tick');
	} else {
		$('.availability_status6').html('');
        $('.availability_status6').removeClass('tick');
        $('.availability_status6').addClass('error');
	}
}






// Register pic
/*
 function PreviewImage(imgFile) 
   { 
    var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;      
    if(!pattern.test(imgFile.value)) 
    { 
     alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");  
     imgFile.focus(); 
    } 
    else 
    { 
     var path; 
     if(document.all)//IE 
     { 
      imgFile.select(); 
      path = document.selection.createRange().text; 
      document.getElementById("imgPreview").innerHTML=""; 
      document.getElementById("imgPreview").width=100;
      document.getElementById("imgPreview").height=100;
      document.getElementById("imgPreview").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果 
     } 
     else//FF 
     { 
      path = URL.createObjectURL(imgFile.files[0]);
      document.getElementById("imgPreview").width=100;
      document.getElementById("imgPreview").height=100;
      document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
      
     } 
    } 
   } 
*/
text = "学生注册";
i = 0;
function type(){
	str = text.substr(0,i);
	document.getElementById("login_txt_bt").innerHTML = str + "_";
	i++;
	if (i > text.length)
		i=0;
	setTimeout("type()",500);
}


