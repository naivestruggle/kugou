////二级菜单  更多
//全局的项目根路径
var rootPath = $("#absoPath").val();

var secondMenu = $("#secondMenu");
var more = $(".more");
more.mouseover(function () {
    secondMenu.css("display", "block");
});
more.mouseout(function () {
    secondMenu.css("display", "none");
});
setTimeout(function () {
    secondMenu.mouseover(function () {
        $(this).css("display", "block");
    })
}, 500);

secondMenu.mouseout(function () {
    clearTimeout();
    $(this).css("display", "none");
});


//用户menu

$(".logined_area").hover(
    function () {
        $(".user_menus").css({
            "display" : "block"
        });
    },
    function () {
        $(".user_menus").css({
            "display" : "none"
        });
    }
);


// function $(id){
//     return document.getElementById(id);
// }

function abc(num) {
    for (var i = 0; i < 8; i++) {
        if (i == num) {
            $("#rightRadioList_" + (i + 1)).css("display", "block");

        } else {
            $("#rightRadioList_" + (i + 1)).css("display", "none");

        }
    }
}

// //
$(".more").mouseenter(function () {
    $("#secondMenu").css("display", "block");
})
$(".more").mouseleave(function () {
    $("#secondMenu").css("display", "none");
})

var timer = setTimeout(function () {
    $("#secondMenu").mouseenter(function () {
        $(".more").css("background", "#0c8ed9");
        $(this).css("display", "block");
    });
}, 500);

$("#secondMenu").mouseleave(function () {
    clearTimeout(timer);
    $(".more").css("background", "");
    $(this).css("display", "none");
});

$(".homeNav>li").click(function () {
    $(this).addClass("active");
    $(this).siblings(".homeNav>li").removeClass("active");
});


$(".login_btn").click(function () {

});

$("#closebtn").click(function () {
    $("#load").css("display", "none");
    $("#cover").css("display", "none");
});
$("#fmLoginBtn").click(function () {
    $("#load").css("display", "block");
    $("#cover").css("display", "block");
})


///////头部菜单栏  登录按钮点击事件
$(".login").click(function () {
    $("#load").css("display", "block");
    $("#cover").css("display", "block");
});

//用户名框输入事件
$("#KgPopupUserName").on("input", function () {
    loginInputInfoAjax($("#KgPopupUserNameErrorMsg"))
});
//用户名框失焦事件
$("#KgPopupUserName").on("blur", function () {
    loginInputInfoAjax($("#KgPopupUserName"))
});
//密码框输入事件
$("#KgPopupUserPwd").on("input", function () {
    loginInputInfoAjax($("#KgPopupUserPwdErrorMsg"))
});
//密码框失焦事件
$("#KgPopupUserPwd").on("blur", function () {
    loginInputInfoAjax($("#KgPopupUserPwd"))
});


//登录信息输入校验
function loginInputInfoAjax(obj) {
    var userAccount = $("#KgPopupUserName").val();
    var userPassword = $("#KgPopupUserPwd").val();
    var autoLogin = $("#autoLogin").attr("checked");
    var data = {
        account: userAccount,
        password: userPassword
    };

    $.post(rootPath+"user.regxLoginInputInfo.ajax", data, function (data) {
        if(data.code == 1){
            $("#KgPopupLoginBtn").removeClass("not_allow_login");
            $("#errorMsg").html("");
        }else if(data.code == 0){
            $("#errorMsg").html(data.msg);
            $("#KgPopupLoginBtn").addClass("not_allow_login");
        }else if(data.code == -1){
            $("#errorMsg").html("系统异常，请稍后再试");
            $("#KgPopupLoginBtn").addClass("not_allow_login");
        }
    });


    // console.log("obj:"+obj)
    // console.log("userAccount:"+userAccount)
    // console.log("userPassword:"+userPassword)
    // console.log("autoLogin:"+autoLogin)
    // console.log("----------------------------------")
}

$("#KgPopupUserPwd").keypress(function (event) {
    if($(this).val() != null && $(this).val() != ""){
        var e = event || window.event
        if (e.keyCode == 13){
            login();
        }
    }
});


//登录
function login() {
    var data = {
        account: $("#KgPopupUserName").val(),
        password: $("#KgPopupUserPwd").val()
    };
    $.post(rootPath+"user.login.ajax",data,function (data) {
        if(data.code == 1){
            //刷新页面
            location.reload();

        }else if(data.code == 0){
            $("#errorMsg").html(data.msg);
        }else if(data.code == -1){
            $("#errorMsg").html("系统繁忙请稍后再试");
        }
    })
}

////////////////////////搜索框开始////////////////////////////
$(function () {
    addHeadSearchBoxKeypress();
    addHeadSeachButtonSubmit();
    addHeadSearchBoxDefaultValue();
})


//每次刷新页面时判断头部的搜索框是否为空  如果为空，设为默认
function addHeadSearchBoxDefaultValue(){
    var inp = $("#searchAllBox1");
    if($.trim(inp.val()) == "" && $.trim(inp.attr("placeholder")) == ""){
        $.post(rootPath+"index.searchBox.ajax",null,function (data) {
            if(data.code == 1){
                inp.attr("placeholder",data.searchString);
            }
        });
    }
}

//给头部的输入框绑定回车事件
function addHeadSearchBoxKeypress(){
    $("#searchAllBox1").bind("keypress",function (event) {
        if(event.keyCode == '13'){
            onHeadSearchBoxSubmit();
        }
    });
}
//给头部的搜索按钮添加点击事件
function addHeadSeachButtonSubmit(){
    $("#searchAllBox1SubmitButton").bind("click",function () {
        onHeadSearchBoxSubmit();
    });
}

//头部搜索框的提交事件
function onHeadSearchBoxSubmit(){
    var searchKey = "";
    var form = $("#searchForm1");
    var inp = $("#searchAllBox1");
    if($.trim(inp.val()) != ""){
        searchKey = $.trim(inp.val());
    }else if($.trim(inp.attr("placeholder")) != ''){
        searchKey = $.trim(inp.attr("placeholder"));
    }else{
        alert("请输入搜索关键字");
        return;
    }
    console.log(form.attr("rel")+"/search/"+searchKey);
    form.attr("action",rootPath+"search/"+searchKey);
    form.submit();
}


/////////////////////////////搜索框结束///////////////////////////////