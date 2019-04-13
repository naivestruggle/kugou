// var secondMenu=document.getElementById("secondMenu");
////二级菜单  更多
var secondMenu = $("#secondMenu");
var more = $(".more");
more.mouseover(function(){
    secondMenu.css("display","block");
});
more.mouseout(function(){
    secondMenu.css("display","none");
});
setTimeout(function(){
    secondMenu.mouseover(function(){
        $(this).css("display","block");
    })
},500);

secondMenu.mouseout(function(){
    clearTimeout();
    $(this).css("display","none");
});

// function $(id){
//     return document.getElementById(id);
// }

function abc(num){
    for(var i=0;i<8;i++){
        if( i==num ){
            $("#rightRadioList_"+(i+1)).css("display","block");

        }else{
            $("#rightRadioList_"+(i+1)).css("display","none");

        }
    }
}

// //
$(".more").mouseenter(function(){
    $("#secondMenu").css("display","block");
})
$(".more").mouseleave(function(){
    $("#secondMenu").css("display","none");
})

var timer=setTimeout(function(){
    $("#secondMenu").mouseenter(function(){
        $(".more").css("background","#0c8ed9");
        $(this).css("display","block");
    });
},500);

$("#secondMenu").mouseleave(function(){
    clearTimeout(timer);
    $(".more").css("background","");
    $(this).css("display","none");
});

$(".homeNav>li").click(function(){
    $(this).addClass("active");
    $(this).siblings(".homeNav>li").removeClass("active");
});


$(".login_btn").click(function(){

});

$("#closebtn").click(function(){
    $("#load").css("display","none");
    $("#cover").css("display","none");
});
$("#fmLoginBtn").click(function(){
    $("#load").css("display","block");
    $("#cover").css("display","block");
})



///////头部菜单栏  登录按钮点击事件
$(".login").click(function () {
    $("#load").css("display","block");
    $("#cover").css("display","block");
});

//用户名框输入事件
$("#KgPopupUserName").on("input",function (){
    loginInputInfoAjax($("#KgPopupUserNameErrorMsg"))
});
//用户名框失焦事件
$("#KgPopupUserName").on("blur",function (){
    loginInputInfoAjax($("#KgPopupUserName"))
});
//密码框输入事件
$("#KgPopupUserPwd").on("input",function (){
    loginInputInfoAjax($("#KgPopupUserPwd"))
});
//密码框失焦事件
$("#KgPopupUserPwd").on("blur",function (){
    loginInputInfoAjax($("#KgPopupUserPwd"))
});


//登录信息输入校验
function loginInputInfoAjax(obj){
    var userAccount = $("#KgPopupUserName").val();
    var userPassword = $("#KgPopupUserPwd").val();
    var autoLogin = $("#autoLogin").attr("checked");

    console.log("obj:"+obj)
    console.log("userAccount:"+userAccount)
    console.log("userPassword:"+userPassword)
    console.log("autoLogin:"+autoLogin)
    console.log("----------------------------------")
}

//登录
function login(){

}