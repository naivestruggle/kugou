init();
register();
sendVerifyCode();
regxRegistInputInfo();

/**
 * 动态验证用户注册输入
 */
var regxTel = false;
var regxCode = false;
var regxPassword = false;
var regxPassword2 = false;
function regxRegistInputInfo() {

    //验证电话
    $("#mou1").blur(function () {
        var data = {
            userTel: $("#mou1").val()
        };
        $.post("user.regxRegistInputInfo.ajax", data, function (data) {
            data = eval(data);
            if (data.code == 0) {
                $("#phonenumber").css({
                    "display": "block"
                }).html(data.msg);
                $(".message").addClass("not_allow_send_code");
                regxTel = false;
            } else if (data.code == 1) {
                $("#phonenumber").html("请输入手机号");
                $(".message").removeClass("not_allow_send_code");
                regxTel = true;
            }
            if(regxTel && regxCode && regxPassword && regxPassword2){
                $("#register").removeClass("not_register");
            }else{
                $("#register").addClass("not_register");
            }
        });

    });

    //验证码
    $("#mou2").blur(function () {
        if($(this).val() == null ||  $(this).val() == ""){
            regxCode = false;
        }else{
            console.log("jinglai");
            regxCode = true
        }
        if(regxTel && regxCode && regxPassword && regxPassword2){
            $("#register").removeClass("not_register");
        }else{
            $("#register").addClass("not_register");
        }
    });
    //密码输入事件
    $("#mou3").on("input",function () {
        var str = $("#mou3").val();
        var patrn1 = /^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)(?![a-zA-z\d]+$)(?![a-zA-z!@#$%^&*]+$)(?![\d!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/;
        var patrn2 = /^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/;
        var patrn3 = /^(?:\d+|[a-zA-Z]+|[!@#$%^&*]+)$/;
        if(patrn1.exec(str)){
            //强
            $(".bg").css("left","0px");
        }else if(patrn2.exec(str)){
            //中
            $(".bg").css("left","-55px");
        }else if(patrn3.exec(str)){
            //弱
            $(".bg").css("left","-115px");
        }else{
            $(".bg").css("left","-169px");
        }
    });
    //验证密码
    $("#mou3").blur(function () {
        var data = {
            userPassword: $("#mou3").val()
        };
        $.post("user.regxRegistInputInfo.ajax", data, function (data) {
            data = eval(data);
            if (data.code == 0) {
                $("#mima").css({
                    "display": "block"
                });
                regxPassword = false;
            }else if(data.code == 1){
                regxPassword = true;
            }
            if(regxTel && regxCode && regxPassword && regxPassword2){
                $("#register").removeClass("not_register");
            }else{
                $("#register").addClass("not_register");
            }
        });
        if($("#mou4").val() != null){
            var data = {
                userPassword: $("#mou3").val(),
                userPassword2: $("#mou4").val()
            };
            $.post("user.regxRegistInputInfo.ajax", data, function (data) {
                data = eval(data);
                if (data.code == 0) {
                    console.log(data.msg);
                    $("#mimax").css({
                        "display": "block"
                    }).html(data.msg);
                    regxPassword2 = false;
                } else if (data.code == 1) {
                    $("#mimax").html("请再次确认密码");
                    regxPassword2 = true;
                }
                if(regxTel && regxCode && regxPassword && regxPassword2){
                    $("#register").removeClass("not_register");
                }else{
                    $("#register").addClass("not_register");
                }
            });
        }
    });

    //确认密码
    $("#mou4").blur(function () {
       var data = {
           userPassword: $("#mou3").val(),
           userPassword2: $("#mou4").val()
       };
        $.post("user.regxRegistInputInfo.ajax", data, function (data) {
            data = eval(data);
            if (data.code == 0) {
                console.log(data.msg);
                $("#mimax").css({
                    "display": "block"
                }).html(data.msg);
                regxPassword2 = false;
            } else if (data.code == 1) {
                $("#mimax").html("请再次确认密码");
                regxPassword2 = true;
            }
            if(regxTel && regxCode && regxPassword && regxPassword2){
                $("#register").removeClass("not_register");
            }else{
                $("#register").addClass("not_register");
            }
        });

    });

}


/**
 * 发送验证码
 */
function sendVerifyCode() {
    $(".message").click(function () {
        if($("#phonenumber").html() == "手机号格式不正确"){
            alert("请先输入手机号");
        }else {
            var data = {
                account: $("#mou1").val(),
                type : 2
            };
            $.post("user.sendVerifyCode.ajax",data,function (data) {
                console.log(data)
               data = eval(data);
               if(data.code == 1){
                   var msg = $(".message");
                   in60ms(60,msg);
                   msg.addClass("not_allow_send_code");
                   alert("验证码发送成功");
               } else if(data.code == 0){
                   alert(data.msg);
               } else if(data.code == -1){
                   alert("系统繁忙，请稍后再试");
               }
            });
        }
    });
}

//定时
function in60ms(i,msg){
    var set = setInterval(function () {
        if(i<0){
            msg.removeClass("not_allow_send_code");
            msg.find("span").html("发送短信");
            clearTimeout(set);
            return;
        }
        msg.find("span").html("   "+i+" s");
        i--;
    },1000);
}

/**
 * 点击注册按钮
 */
function register() {
    $(".zc").click(function () {
        if(regxTel && regxCode && regxPassword && regxPassword2){
            var data = {
                userTel: $("#mou1").val(),
                verifyCode: $("#mou2").val(),
                userPassword: $("#mou3").val(),
                userPassword2: $("#mou4").val(),
                userSex: $('.sex input[name="sex"]:checked').val()
            };
            $.post("user.regist.ajax", data, function (data) {
                if(data.code == 1){
                    var loginData = {
                        account: $("#mou1").val(),
                        password: $("#mou3").val()
                    };
                    //自动登录
                    $.post("user.login.ajax",loginData,function (data) {
                        if(data.code == 1){
                            //跳主页
                            window.location.href="index.html";
                        }else if(data.code == 0){
                            $("#errorMsg").html(data.msg);
                        }else if(data.code == -1){
                            $("#errorMsg").html("系统繁忙请稍后再试");
                        }
                    })
                    //alert("注册成功");
                }else if(data.code == 0){
                    alert(data.msg);
                }else if(data.code == -1){
                    alert("系统繁忙，请稍后再试");
                }
            });
        }else{
            alert("用户信息不正确");
        }
    });
}


function init() {
    /*手机号*/
    var mou1 = document.getElementById("mou1");
    var phonenumber = document.getElementById("phonenumber");
    mou1.onclick = function () {
        phonenumber.style.display = "block";
    }
    mou1.onblur = function () {
        phonenumber.style.display = "none";
    }

    /*密码*/
    var mou3 = document.getElementById("mou3");
    var mima = document.getElementById("mima");
    mou3.onclick = function () {
        mima.style.display = "block";
    }
    mou3.onblur = function () {
        mima.style.display = "none";
    }
    var bg = document.getElementById("bg");
    var line1 = document.getElementById("line1");
    var line2 = document.getElementById("line2");
    mou3.onchange = function () {
        bg.style.background = "#a9d6f3";
    }


    /*确认密码*/
    var mou4 = document.getElementById("mou4");
    var mimax = document.getElementById("mimax");
    mou4.onclick = function () {
        mimax.style.display = "block";
    }
    mou4.onblur = function () {
        mimax.style.display = "none";
    }

    /*注册*/
    var zc = document.getElementsByClassName("zc")[0];
    var mou5 = document.getElementById("mou5");
    mou5.onclick = function () {
        if (this.checked == true) {
            $("#register").removeClass("not_allow_send_code");
        } else {
            $("#register").addClass("not_allow_send_code");
        }
    }
}