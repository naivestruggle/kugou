 $(function () {
 	if($("#msg").val() != ""){
 		msgBoxOne($("#msg").val());
	}

 	//菜单的li点击事件
    $(".tab>li").click(function () {
        $(this).addClass("active").siblings("li").removeClass("active");
        var index=$(this).index();
        $(".tab_content>div:eq("+index+")").addClass("selected").siblings("div").removeClass("selected");
    });
     
    //邮箱的更改绑定或立即设置按钮的点击事件
	 $(".email_nocheck>.account_btn").click(function(){
		 $(".security").css("display","none");
		 $(".item1").css("display","block");
	 })
	 ////手机号的更改绑定或立即设置按钮的点击事件
	  $(".mobile>.account_btn").click(function(){
	  	  $(".item2").css("display","block");
		  $(".security").css("display","none");
	 })
	 ////安全问题的更改绑定或立即设置按钮的点击事件
	   $(".question>.account_btn").click(function(){
		 $(".security").css("display","none");
		 $(".item3").css("display","block");
	 })
	 //账号安全的子页面的返回按钮点击事件
	   $(".back").click(function(){
		 $(".security").css("display","block");
		 $(".item1").css("display","none");
		 $(".item2").css("display","none");
		 $(".item3").css("display","none");
		$(".tab>li:last").addClass("active").siblings().removeClass("active");
		$(".tab_content>div:last").addClass("seleted").siblings().removeClass("seleted");
	   });
    //密码的可见与影藏
    $(".eye").click(function(){
    	$(".textbox i").toggleClass("close-eye");
    	if($(".textbox i").hasClass("close-eye")){
    		$("#pwd1").attr("type","password");
    	}else{
    		$("#pwd1").attr("type","text");
    	}
    })
	 //上传头像按钮点击事件
    $("#read").click(function(){
    	$("#file").click();
    });
	 //上传头像框点击事件
     $("#pic").click(function(){
         $("#file").click();
     })



	//页面加载完毕  生成市和县
	 createCityAndDistrictsBeforePageLoad();
	 //选中省
     onSelectProvince();
     //选中市
     onSelectCity();
     //生成验证码
     createVerify(1);
     createVerify(2);
     createVerify(3);

	 //新密码的输入事件
	 onInputNewPwd();

	 //保存修改按钮的点击事件
	 onSubmitMyInfo();

	 //三个密码框的输入事件
	 onInputPwdBox();

	 //修改密码按钮点击事件
	 onClickAlterPwdButton();

	 //发送绑定邮箱验证码按钮点击事件
	 onClickSendAlterEmailAddrVerifyCode();

	 //点击确认修改邮箱按钮
	 onClickAlterEmailButton();

	 //原手机号的发送验证码按钮点击事件
	 onClickSendOldTelVerifyCodeButton();

	 //存在手机号的页面   下一步按钮点击事件
	 onClickTelNextButton();

	 //新手机号的发送验证码按钮点击事件
	 onClickSendNewTelVerifyCodeButton();

	 //绑定新手机号页面的提交按钮
	 onClickBindNewTelSubmitButton();

	 //安全问题页面 密码输入事件
	 onInputEnsurePwdBox();

	 //安全问题页面 密码保护问题选择事件
	 onChangeEnsureQuestionBox();

	 //安全问题页面 密保答案输入事件
	 onInputEnsureAnswerBox();

	 //安全问题页面  提交按钮点击事件
	 onClickEnsureSubmitButton();
});


//页面加载完毕  生成市和县
 function createCityAndDistrictsBeforePageLoad(){
     $("#sel_City").html("");
 	 //获取到当前所选省的id
	 var provinceId = $("#sel_Province").val();
	 if(provinceId == "-1"){
		 $("#sel_City").html("<option value=\"-1\">请选择</option>");
		 $("#sel_Districts").html("<option value=\"-1\">请选择</option>");
		 return;
	 }
	 var data = {
		 provinceId : provinceId
	 };
	 $.post(rootPath+"/user.createCity.ajax",data,function (data) {
	 	 var map = data.cityMap;
	 	 for(var key in map){
	 	 	var value = map[key];
	 	 	if(value == $("#sel_City").attr("rel")){
				var nowCity = "<option value=\""+key+"\" selected=\"selected\">"+value+"</option>";
                $("#sel_City").append(nowCity);
			}else{
				var str = "<option value=\""+key+"\">"+value+"</option>";
                $("#sel_City").append(str);
			}
		 }
		 onChooesCityCreateDistricts();
	 });
 }

 //选中市 生成县
 function onChooesCityCreateDistricts(){
     $("#sel_Districts").html("");
	 var cityId = $("#sel_City").val();
	 if(cityId == "-1"){
		 $("#sel_Districts").html("<option value=\"-1\">请选择</option>");
		 return;
	 }
	 var data = {
		 cityId : cityId
	 };
	 //市生成完毕  生成县
	 $.post(rootPath+"/user.createDistricts.ajax",data,function (data) {
		 var map1 = data.districtsMap;
		 for(var key1 in map1){
			 var value1 = map1[key1];
			 if(value1 == $("#sel_Districts").attr("rel")){
                 var mowDistricts = "<option value=\""+key1+"\" selected=\"selected\">"+value1+"</option>";
                 $("#sel_Districts").append(mowDistricts);
			 }else {
				 var str1 = "<option value=\""+key1+"\">"+value1+"</option>";
                 $("#sel_Districts").append(str1);
			 }
		 }
	 });
 }

 //选中省
 function onSelectProvince(){
     $("#sel_Province").bind("change",function () {
         createCityAndDistrictsBeforePageLoad();
     })
 }

 //选中市
 function onSelectCity(){
     $("#sel_City").bind("change",function () {
         onChooesCityCreateDistricts();
     })
 }

 //生成验证码
 function createVerify(verifyType){
 	 //清空输入的验证码
 	 $(".verify_code").each(function () {
		 $(this).val("");
	 })
     var data = {
         verifyType : verifyType
     };
     $.post(rootPath+"/verify.createVerifyImage.ajax",data,function (data) {
     	var url = data.verifyImage;
     	url = url.replace("/","")
         var path = rootPath+url;
        if(verifyType == 1){
            $("#uccheckimg_alterinfo").attr("src",path);
        }else if (verifyType == 2){
            $("#uccheckimg_alterpwd").attr("src",path);
        } else if (verifyType == 3){
        	// $("#confirm_pic_updatemail").html("<img id=\"uccheckimg_updatemail\" onclick=\"createVerify(3)\" class=\"uccheckimg\" title=\"看不清，换一张\" src=\""+path+"\">");
            $("#uccheckimg_updatemail").attr("src",path);
        }
     })
 }

 //密码是否显示
 function eye(str){
 	var pwd = $("#"+str);
 	if(pwd.attr("type") == "text"){
		pwd.attr("type","password");
	}else if(pwd.attr("type") == "password"){
		pwd.attr("type","text");
	}
 }


 //新密码的输入事件
 function onInputNewPwd(){
	var patrn1 = /^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)(?![a-zA-z\d]+$)(?![a-zA-z!@#$%^&*]+$)(?![\d!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/;
	var patrn2 = /^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/;
	var patrn3 = /^(?:\d+|[a-zA-Z]+|[!@#$%^&*]+)$/;
 	$("#pwd2").on("input",function () {
 		var str = $(this).val();
		if(patrn1.exec(str)){
			//强
			$(".bg").css("left","0px");
		}else if(patrn2.exec(str)){
			//中
			$(".bg").css("left","-57px");
		}else if(patrn3.exec(str)){
			//弱
			$(".bg").css("left","-113px");
		}else{
			$(".bg").css("left","-169px");
		}
	});
 }

 ////////////////////////////////////上传头像开始////////////////////////////////

 //上传头像按钮点击事件
 $("#uploadButton").bind("click",function () {
    $("#uploadForm").submit();
 });

 //获取上传按钮
 var input1 = document.getElementById("file");

 if(typeof FileReader==='undefined'){
	 input1.setAttribute('disabled','disabled');
 }else{
	 input1.addEventListener('change',readFile,false);
 }
 function readFile(){
	 var file = this.files[0];//获取上传文件列表中第一个文件
	 if(!/image\/\w+/.test(file.type)){
		 msgBoxOne("文件必须为图片！");
		 return false;
	 }
	 var reader = new FileReader();//实例一个文件对象
	 reader.readAsDataURL(file);//把上传的文件转换成url
	 //当文件读取成功便可以调取上传的接口
	 reader.onload = function(e){
		 var image = new Image();
		 // 设置src属性
		 image.src = e.target.result;
		 var max=200;
		 // 绑定load事件处理器，加载完成后执行，避免同步问题
		 image.onload = function(){
			 // 获取 canvas DOM 对象
			 var canvas = document.getElementById("pic");
			 // 如果高度超标 宽度等比例缩放 *=
			 var ctx = canvas.getContext("2d");
			 ctx.clearRect(0, 0, canvas.width, canvas.height);
			 ctx.drawImage(image, 0, 0, 200, 200);
		 };
	 }
 };
 ////////////////////////////////////上传头像结束////////////////////////////////

 ///////////////////////////////////编辑资料开始/////////////////////////////////////
 //保存修改按钮的点击事件
 function onSubmitMyInfo(){
 	$("#button1").bind("click",function () {
 		//昵称
		var nickname1 = $("#nickname").val();
		var nickname2 = $("#nickname").attr("placeholder");
		if(nickname1 == "" && nickname2 == ""){
			msgBoxOne("请输入昵称，让大家记住你");
			createVerify(1);
			return;
		}
		var nickname = "";
		$.trim(nickname1) == "" ? nickname=nickname2:nickname=nickname1;

		//性别
		var sex = "";
		$(".info_item input").each(function () {
			if($(this).prop("checked") == true){
				sex = $(this).val();
			}
		});
		//生日
		var birthday = $("#birthday").val();
		//地址
		var addr = "";
		var sel_Province = $("#sel_Province").val();
		var sel_City = $("#sel_City").val();
		var sel_Districts = $("#sel_Districts").val();
		if(sel_Province != "-1"){
			$("#sel_Province option").each(function () {
				if($(this).val() == sel_Province){
					addr += $(this).html()+"-";
				}
			});
			$("#sel_City option").each(function () {
				if($(this).val() == sel_City){
					addr += $(this).html()+"-";
				}
			});
			$("#sel_Districts option").each(function () {
				if($(this).val() == sel_Districts){
					addr += $(this).html();
				}
			});
		}
		if (addr == ""){
			msgBoxOne("请填写地区");
			createVerify(1);
			return;
		}
		//个性签名
		var intro = $("#intro").val();
		if(intro.length > 500){
			msgBoxOne("个性签名不能超过500个字符");
			createVerify(1);
			return;
		}
		//验证码
		var updateInfoVerifyCode = $("#updateInfoVerifyCode").val();
		if(updateInfoVerifyCode == ""){
			msgBoxOne("请填写验证码");
			createVerify(1);
			return;
		}else if (updateInfoVerifyCode.length != 4){
			msgBoxOne("验证码错误");
			createVerify(1);
			return;
		}
		var data = {
			userUsername : nickname,
			userSex : sex,
			userBirthday : birthday,
			userAddr : addr,
			userSignature : intro,
			verifyCode : updateInfoVerifyCode
		};
		$.post(rootPath+"/user.updateInfo.ajax",data,function (data) {
			var code = data.code;
			if(code == 0){
				msgBoxOne(data.errorMsg);
				createVerify(1);
			}else if (code == -1){
				msgBoxOne("系统繁忙，请稍后再试")
				createVerify(1);
			} else if(code == 1){
				var bean = data.loginedUser;
				//修改成功
				msgBoxOne("修改成功");
				//更新头部用户名显示
				var head_UserName = $("#head_UserName");
				head_UserName.html(bean.userUsername);
				head_UserName.attr("title",bean.userUsername);
				//更新昵称显示、
				$("#nickname").val("");
				$("#nickname").attr("placeholder",bean.userUsername);
				//更新性别显示
				$(".info_item input").each(function () {
					if($(this).val() == bean.userSex){
						$(this).prop("checked",true);
					}
				});
				//更新生日显示
				$("#birthday").val(bean.userBirthday);
				//更新地区显示
				$("#sel_Province option").each(function () {
					if ($(this).html() == bean.province){
						$(this).prop("selected",true);
					}
				});
				$("#sel_City").attr("rel",bean.city);
				$("#sel_Districts").attr("rel",bean.districts);
				createCityAndDistrictsBeforePageLoad();

				//更新个性签名
				$("#intro").val(bean.userSignature);

				//更新左侧昵称
				$("#myucname").html(bean.userUsername);
				createVerify(1);
			}
		});
	})
 }
 //////////////////////////////////////编辑资料结束//////////////////////////////////////

 //////////////////////////////////////修改密码开始///////////////////////////////////////
 //三个密码框的输入事件
 function onInputPwdBox(){
 	//原密码
 	$("#pwd1").bind("input",function () {
		var val = $(this).val();
		if(val.length > 18 || val.length < 6) {
			$("#old_pwd_errorMsg").css("color","red");
		}else {
			$("#old_pwd_errorMsg").css("color","#fff");
		}
	});

 	//新密码
	 $("#pwd2").bind("input",function () {
		 var val = $(this).val();
		 if(val.length > 18 || val.length < 6) {
			 $("#new_pwd_errorMsg").css("color","red");
		 }else {
			 $("#new_pwd_errorMsg").css("color","#fff");
		 }
		 var val2 = $("#pwd3").val();
		 if(val != val2){
			 $("#aga_pwd_errorMsg").css("color","red");
		 }else {
			 $("#aga_pwd_errorMsg").css("color","#fff");
		 }
	 });

	 //确认密码
	 $("#pwd3").bind("input",function () {
		 var val = $(this).val();
		 var val2 = $("#pwd2").val();
		 if(val != val2){
			 $("#aga_pwd_errorMsg").css("color","red");
		 }else {
			 $("#aga_pwd_errorMsg").css("color","#fff");
		 }
	 });
 }

 //保存密码修改的点击事件
 function onClickAlterPwdButton(){
 	$("#button2").bind("click",function () {
		var oldPwd = $("#pwd1").val();
		var newPwd = $("#pwd2").val();
		var agaPwd = $("#pwd3").val();
		var alterPwdVerifyCode = $("#alterPwdVerifyCode").val();
		if(oldPwd.length > 18 || oldPwd.length < 6){
			msgBoxOne("请输入正确格式的原密码");
			createVerify(2);
			return;
		}
		if(newPwd.length > 18 || newPwd.length < 6){
			msgBoxOne("请输入正确格式的新密码");
			createVerify(2);
			return;
		}
		if(newPwd != agaPwd){
			msgBoxOne("两次输入的密码不一致");
			createVerify(2);
			return;
		}
		if(alterPwdVerifyCode.length != 4){
			msgBoxOne("请输入正确的验证码");
			createVerify(2);
			return;
		}
		var data = {
			userPassword : oldPwd,
			userPassword1 : newPwd,
			userPassword2 : agaPwd,
			verifyCode : alterPwdVerifyCode
		};
		$.post(rootPath+"user.alterPwd.ajax",data,function (data) {
			var code = data.code;
			if(code == -1){
				msgBoxOne("系统繁忙，请稍后再试")
				createVerify(2);
			}else if (code == 0){
				msgBoxOne(data.errorMsg);
				createVerify(2);
			} else if (code == 1){
				createVerify(2);
				//清除cookie中的自动登录用户
				clearCookie("indream_autoLogin");

				location.href = rootPath;
			}
		});
	});
 }
 ////////////////////////////////////////修改密码结束////////////////////////////////////////


 ////////////////////////////////////////邮箱绑定开始/////////////////////////////////////////////////
 //发送绑定邮箱验证码按钮点击事件
 function onClickSendAlterEmailAddrVerifyCode(){
 	$("#sendAlterEmailAddrVerifyCode").click(function () {
		var new_email_box = $("#new_email_box").val();
		var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
		if(!reg.test(new_email_box)){
			msgBoxOne("邮箱格式不正确");
			return;
		}
		var data = {
			account : new_email_box,
			type : "4"
		};
		$.post(rootPath+"/user.sendVerifyCode.ajax",data,function (data) {
			var code = data.code;
			if(code == -1){
				msgBoxOne("系统繁忙，请稍后再试");
			}else if (code == 0){
				msgBoxOne(data.msg);
			} else if (code == 1){
				msgBoxOne("验证码发送成功，请注意查收");
				$("#sendAlterEmailAddrVerifyCode").addClass("not_send_email");
				var i = 60;
				alterTimeSendEmailCode(i);
			}

		});
	});
 }

 //倒计时不可以重复发送
 function alterTimeSendEmailCode(i){
	 $("#sendAlterEmailAddrVerifyCode").html(i+" s");
	 if(i <= 0){
		 $("#sendAlterEmailAddrVerifyCode").removeClass("not_send_email");
		 $("#sendAlterEmailAddrVerifyCode").html("发送验证码");
		 return;
	 }
	 i--;
	 setTimeout(function () {
		 alterTimeSendEmailCode(i);
	 },1000);
 }

 //点击确认修改邮箱按钮
 function onClickAlterEmailButton(){
 	$("#button3").bind("click",function () {
		var account = $("#new_email_box").val();
		var verifyCode = $("#new_email_verifycode").val();
		var oldPwd = $("#pwd6").val();
		var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
		if(!reg.test(account)){
			msgBoxOne("邮箱格式不正确");
			return;
		}
		if(verifyCode.length != 6){
			msgBoxOne("请输入正确的验证码");
			return;
		}
		if(oldPwd.length > 18 || oldPwd.length < 6){
			msgBoxOne("请输入正确的密码");
			return;
		}
		var data = {
			account : account,
			verifyCode : verifyCode,
			oldPwd : oldPwd
		};
		$.post(rootPath+"/user.alterEmail.ajax",data,function (data) {
			var code = data.code;
			if(code == -1){
				msgBoxOne("系统繁忙，请稍后再试");
			}else if(code == 0){
				msgBoxOne(data.msg);
			}else if(code == 1){
				//修改成功
				msgBoxOne("修改成功");
				//修改邮箱显示
				s1 = "<p>安全邮箱\n" +
					"\t\t\t\t<span class=\"gray9 f14\">(可用于找回密码和登录帐户)</span>\n" +
					"\t\t\t\t\t</p>\n" +
					"\t\t\t\t\t<p ><span id=\"userEmailShowSpan\" class=\"f14 gray9\">"+data.loginedUser.userEmail+"</span></p>";
				$("#user_email_has_").html(s1);
				//立即设置 按钮 变为更换绑定
				$("#email_account_update_").html("<a class=\"setting-btn\" title=\"更换绑定\">更换绑定</a>");
				//去掉 您还没有邮箱哦
				$(".tips_error").html("");
				//去掉 simple的样式  使居中
				$("#user_email_has_").addClass("single-item");
				//清空当前模版数据
				$("#new_email_box").val("");
				$("#new_email_verifycode").val("");
				$("#pwd6").val("");
				//模版跳转
				$(".security").css("display","block");
				$(".item1").css("display","none");
			}
		});
	});
 }
 ///////////////////////////////////////////邮箱绑定结束/////////////////////////////////////////////////


 //////////////////////////////////////手机绑定开始//////////////////////////////////////////////////////////
 //原手机号的发送验证码按钮点击事件
 function onClickSendOldTelVerifyCodeButton(){
 	$("#sendBtn").bind("click",function () {
		$.post(rootPath+"/user.sendVerifyCode.ajax",{type:"5"},function (data) {
			var code = data.code;
			if(code == -1){
				msgBoxOne("系统繁忙，请稍后再试");
			}else if(code == 0){
				msgBoxOne(data.msg);
			}else if(code == 1){
				msgBoxOne("验证码发送成功，请注意查收");
				$("#sendBtn").addClass("not_send_tel");
				sendVerifyAfter1(60);
			}
		})
	});
 }

 //发送完验证码轮询倒计时
 function  sendVerifyAfter1(i) {
	 $("#sendBtn").html(i+" s");
	 if(i <= 0){
		 $("#sendBtn").html("发送验证码");
		 $("#sendBtn").removeClass("not_send_tel");
	 	return;
	 }
	 i--;
	 setTimeout(function () {
		 sendVerifyAfter1(i);
	 },1000);
 }

 //下一步按钮点击事件
 function onClickTelNextButton(){
 	$("#button5").bind("click",function () {
		var verifyCode = $("#pwd7").val();
		if(verifyCode.length != 6){
			msgBoxOne("请输入正确的验证码");
			return;
		}
		$.post(rootPath+"/user.bindTelBeforeRegxVerifyCode.ajax",{verifyCode : verifyCode},function (data) {
			var code = data.code;
			if(code == 0){
				msgBoxOne(data.msg);
			}else if(code == 1){
				$("#pwd7").val("");
				$(".item2").css("display","none");
				$(".item2_").css("display","block");
			}
		})
	});
 }

 //新手机号的发送验证码按钮点击事件
 function onClickSendNewTelVerifyCodeButton(){
 	$("#sendBtn_").click(function () {
		var tel = $("#new_tel_box").val();
		if(tel.length != 11){
			msgBoxOne("请输入正确格式的手机号");
			return;
		}
		$.post(rootPath+"/user.sendVerifyCode.ajax",{account : tel,type : "6"},function (data) {
			var code = data.code;
			if(code == -1){
				msgBoxOne("系统繁忙，请稍后再试");
			}else if(code == 0){
				msgBoxOne(data.msg);
			}else if(code == 1){
				msgBoxOne("验证码发送成功，请注意查收");
				$("#sendBtn_").addClass("not_send_tel");
				sendVerifyAfter2(60);
			}
		});
	});
 }

 //发送完验证码轮询倒计时
 function  sendVerifyAfter2(i) {
	 $("#sendBtn_").html(i+" s");
	 if(i <= 0){
		 $("#sendBtn_").html("发送验证码");
		 $("#sendBtn_").removeClass("not_send_tel");
		 return;
	 }
	 i--;
	 setTimeout(function () {
		 sendVerifyAfter2(i);
	 },1000);
 }

 //绑定新手机号页面的提交按钮
 function onClickBindNewTelSubmitButton() {
	 $("#button5_").click(function () {
		var account =  $("#new_tel_box").val();
		var verifyCode = $("#pwd7_").val();
		if(account.length != 11){
			msgBoxOne("请输入正确的手机号");
			return;
		}
		if(verifyCode.length != 6){
			msgBoxOne("请输入正确的验证码");
			return;
		}
		$.post(rootPath+"/user.bindTel.ajax",{account:account,verifyCode:verifyCode},function (data) {
			var code = data.code;
			if(code == -1){
				msgBoxOne("系统繁忙，请稍后再试");
			}else if(code == 0){
				msgBoxOne(data.msg);
			}else if(code == 1){
				//修改成功
				msgBoxOne("更换成功");
				//将第二个子页面的数据清空
				$("#new_tel_box").val("");
				$("#pwd7_").val("");
				//将第一个子页面清空  并更新手机号
				$("#pwd7").val("");
				$("#has_tel_show").html(data.loginedUser.tel);
				//将主界面的手机号更新
				$("#has_tel_show_main").html(data.loginedUser.tel);
				//跳回主页面
				$(".item2_").css("display","none");
				$(".security").css("display","block");
			}
		});
	 });
 }
 //////////////////////////////////////手机绑定结束//////////////////////////////////////////////////////////


 ///////////////////////////////////////安全问题设置开始////////////////////////////////////////////////////////////
 //安全问题页面 密码输入事件
 function onInputEnsurePwdBox(){
 	$("#pwd8").bind("input",function () {
		var val = $(this).val();
		if(val.length < 6 || val.length > 18){
			$("#ensure_pwd_warning").css("display","block");
			$("#ensure_pwd_warning span").html("请填写正确的密码(6-18位)")
		}else {
			$("#ensure_pwd_warning").css("display","none");
		}
	});
 }

 //安全问题页面 密码保护问题选择事件
 function onChangeEnsureQuestionBox() {
	 $("#ensure_pwd").bind("change",function () {
		var val = $(this).val();
		if(val == -1){
			$("#ensure_question_warning").css("display","block");
			$("#ensure_question_warning span").html("请选择密保问题");
		}else {
			$("#ensure_question_warning").css("display","none");
		}
	 });
 }
 
 //安全问题页面 密保答案输入事件
 function onInputEnsureAnswerBox() {
	 $("#pwd9").bind("input",function () {
		var val = $(this).val();
		if(val.length == 0){
			$("#ensure_answer_warning").css("display","block");
			$("#ensure_answer_warning span").html("请输入密保答案");
		}else {
			$("#ensure_answer_warning").css("display","none");
		}
	 });
 }
 
 //安全问题页面  提交按钮点击事件
 function onClickEnsureSubmitButton() {
	 $("#button6").click(function () {
		 var pwd = $("#pwd8").val();
		 var que = $("#ensure_pwd").val();
		 var ans = $("#pwd9").val();
		 var ver = $("#ensure_verify_code").val();
		 if(ver.length != 4){
		 	msgBoxOne("请输入正确的验证码");
		 	createVerify(3);
		 	return;
		 }
		 if(ans.length == 0){
			 $("#ensure_answer_warning").css("display","block");
			 $("#ensure_answer_warning span").html("请输入密保答案");
			 createVerify(3);
			 return;
		 }
		 if(que == -1){
			 $("#ensure_question_warning").css("display","block");
			 $("#ensure_question_warning span").html("请选择密保问题");
			 createVerify(3);
			 return;
		 }
		 if(pwd.length < 6 || pwd.length > 18){
			 $("#ensure_pwd_warning").css("display","block");
			 $("#ensure_pwd_warning span").html("请填写正确的密码(6-18位)");
			 createVerify(3);
			 return;
		 }
		 $("#ensure_pwd option").each(function () {
			 if($(this).val() == que){
				 que = $(this).html();
			 }
		 })
		 var data = {
		 	 userPwd : pwd,
			 question : que,
			 answer : ans,
			 verifyCode : ver
		 };
		 $.post(rootPath+"/user.addEnsurePwd.ajax",data,function (data) {
			var code = data.code;
			if(code == -1){
				msgBoxOne("系统繁忙，请稍后再试");
				createVerify(3);
			}else if(code == 0){
				msgBoxOne(data.msg);
				createVerify(3);
			}else if(code == 1){
				msgBoxOne("设置成功");
				//刷新验证码
				createVerify(3);
				//刷新密保问题那里
				$("#ensure_status").html("状态：\n" +
					"                                        \t<span class=\"gray6\">已设置</span>");
				$("#ensure_add_button").html("<a class=\"setting-btn\" href=\"#\" title=\"修改密保\">修改密保</a>");
				//跳转回主界面
				$(".security").css("display","block");
				$(".item3").css("display","none");
			}
		 });
	 });
 }
 ////////////////////////////////////////安全问题设置结束///////////////////////////////////////////////////////