init();
register();
sendVerifyCode();
regxRegistInputInfo();

/**
 * 动态验证用户注册输入
 */
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
                $(".message").css({
                    "background": "#bbb"
                });
            } else if (data.code == 1) {
                $("#phonenumber").html("请输入手机号");
                $(".message").css({
                    "background": "#2085e5"
                });
            }

        });
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
            }

        });
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
            } else if (data.code == 1) {
                $("#mimax").html("请再次确认密码");
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


/**
 * 点击注册按钮
 */
function register() {
    $(".zc").click(function () {

        var data = {
            userTel: $("#mou1").val(),
            verifyCode: $("#mou2").val(),
            userPassword: $("#mou3").val(),
            userPassword2: $("#mou4").val(),
            userSex: $('.sex input[name="sex"]:checked').val()
        };
        $.post("user.regist.ajax", data, function (data) {
            data = eval(data);
            if(data.code == 1){
                alert("注册成功");
            }else if(data.code == 0){
                alert(data.msg);
            }else if(data.code == -1){
                alert("系统繁忙，请稍后再试");
            }
        });

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
            zc.style.background = "#3db9ec";
        } else {
            zc.style.background = "#bbb";
        }
    }
}