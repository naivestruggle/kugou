//全局的项目根路径
// var rootPath = $("#absoPath").val();
var rootPath = $("#absoPath").val();
// var rootPath = "";

////////////////////////////////弹窗开始////////////////////////////////////
//单选框
function msgBoxOne(msg){
    var onlyChoseAlert = simpleAlert({
        "content":msg,
        "buttons":{
            "确定":function () {
                onlyChoseAlert.close();
            }
        }
    })
}
//多选框
function msgBoxTwo(msg){
    var dblChoseAlert = simpleAlert({
        "content":msg,
        "buttons":{
            "确定":function () {
                msgBoxOne("你好");
                dblChoseAlert.close();
            },
            "取消":function () {
                dblChoseAlert.close();
            }
        }
    })
}
//弹窗
var simpleAlert = function (opts) {
    //设置默认参数
    var opt = {
        "closeAll": false,
        "content": "",
        "buttons": {}
    }
    //合并参数
    var option = $.extend(opt, opts);
    //事件
    var dialog = {}
    var $simpleAlert = $('<div class="simpleAlert">');
    var $shelter = $('<div class="simpleAlertShelter">');
    var $simpleAlertBody = $('<div class="simpleAlertBody">');
    var $simpleAlertBodyClose = $('<img class="simpleAlertBodyClose" src="'+rootPath+'img/images/close_msg_button.png" height="14" width="14"/>');
    var $simpleAlertBodyContent = $('<p class="simpleAlertBodyContent">' + option.content + '</p>');
    dialog.init = function () {
        $simpleAlertBody.append($simpleAlertBodyClose).append($simpleAlertBodyContent);
        var num = 0;
        var only = false;
        var onlyArr = [];
        for (var i = 0; i < 2; i++) {
            for (var key in option.buttons) {
                switch (i) {
                    case 0:
                        onlyArr.push(key);
                        break;
                    case 1:
                        if (onlyArr.length <= 1) {
                            only = true;
                        } else {
                            only = false;
                        }
                        num++;
                        var $btn = $('<button class="simpleAlertBtn simpleAlertBtn' + num + '">' + key + '</button>')
                        $btn.bind("click", option.buttons[key]);
                        if (only) {
                            $btn.addClass("onlyOne")
                        }
                        $simpleAlertBody.append($btn);
                        break;
                }

            }
        }
        $simpleAlert.append($shelter).append($simpleAlertBody);
        $("body").append($simpleAlert);
        $simpleAlertBody.show().animate({"marginTop":"-128px","opacity":"1"},300);
    }
    //右上角关闭按键事件
    $simpleAlertBodyClose.bind("click", function () {
        option.closeAll=false;
        dialog.close();
    })
    dialog.close = function () {
        if(option.closeAll){
            $(".simpleAlert").remove()
        }else {
            $simpleAlertBody.animate({"marginTop": "-188px", "opacity": "0"}, 200, function () {
                $(".simpleAlert").last().remove()
            });
        }
    }
    dialog.init();
    return dialog;
}
/////////////////////////////弹窗结束///////////////////////////////////////

//将字符串中所有的 "/" 符号替换成空串
function getRealSearchKey(searchKey) {
    var re = new RegExp("/","g");
    return searchKey.replace(re,"");
}
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

    $.post(rootPath+"/user.regxLoginInputInfo.ajax", data, function (data) {
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
    var isAutoLogin = $(".kg_popup_checkbox").prop("checked");
    console.log(isAutoLogin);
    var data = {
        account: $("#KgPopupUserName").val(),
        password: $("#KgPopupUserPwd").val()
    };
    $.post(rootPath+"/user.login.ajax",data,function (data) {
        if(data.code == 1){
            //将账号和密码保存起来
            if(isAutoLogin){
                // localStorage.setItem('indream_autoLogin',$("#KgPopupUserName").val()+"-"+$("#KgPopupUserPwd").val());
                setCookie('indream_autoLogin', $("#KgPopupUserName").val()+"-"+$("#KgPopupUserPwd").val(), 7);
            }
            //刷新页面
            location.reload();

        }else if(data.code == 0){
            $("#errorMsg").html(data.msg);
        }else if(data.code == -1){
            $("#errorMsg").html("系统繁忙请稍后再试");
        }
    })
}

//退出登录
function loginOut(){
    var dblChoseAlert = simpleAlert({
        "content": "您确定要退出登录？",
        "buttons":{
            "确定":function () {
                // localStorage.removeItem("indream_autoLogin");
                // localStorage.setItem('indream_autoLogin',$("#KgPopupUserName").val()+"-"+$("#KgPopupUserPwd").val());
                // $.cookie("indream_autoLogin",null);
                clearCookie("indream_autoLogin");
                dblChoseAlert.close();
                location.href = rootPath+"/user.loginOut.ajax";
            },
            "取消":function () {
                dblChoseAlert.close();
            }
        }
    });
}
function clearCookie(cname) {
   setCookie(cname,"",0);
}
function setCookie(cname,cvalue,exdays)
{
    var d = new Date();
    d.setTime(d.getTime()+(exdays*24*60*60*1000));
    var expires = "expires="+d.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

function getCookie(cname)
{
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++)
    {
        var c = ca[i].trim();
        if (c.indexOf(name)==0) return c.substring(name.length,c.length);
    }
    return "";
}

////////////////////////搜索框开始////////////////////////////
$(function () {
    //头部搜索框的回车事件
    addHeadSearchBoxKeypress();
    //头部搜索框的提交事件
    addHeadSeachButtonSubmit();
    //头部搜索框的默认值
    addHeadSearchBoxDefaultValue();
    //头部搜索框聚焦事件
    onHeadFocusSearchBox();
    //头部搜索框失焦事件
    onHeadBlurSearchBox();
    //头部搜索框输入事件
    onHeadInputSearchBox();
})



//每次刷新页面时判断头部的搜索框是否为空  如果为空，设为默认
function addHeadSearchBoxDefaultValue(){
    var inp = $("#searchAllBox1");
    if($.trim(inp.val()) == "" && $.trim(inp.attr("placeholder")) == ""){
        $.post(rootPath+"/index.searchBox.ajax",null,function (data) {
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
        msgBoxOne("请输入搜索关键字");
        return;
    }
    //保存历史搜索
    searchKey = getRealSearchKey(searchKey);
    pushHeadHistory(searchKey);
    form.attr("action",rootPath+"search/"+searchKey);
    form.submit();
}

//点击历史记录的提交事件
function onHeadHistorySearchBoxSubmit(searchKey){
    //保存历史搜索
    var form = $("#searchForm1");
    searchKey = getRealSearchKey(searchKey);
    pushHeadHistory(searchKey);
    form.attr("action",rootPath+"search/"+searchKey);
    form.submit();
}


//头部搜索框聚焦事件
function onHeadFocusSearchBox(){
    $("#searchAllBox1").on("focus",function () {
       if($.trim($(this).val()) == ""){
           //取出历史记录
           pullHeadHistory();
           $("#history_head").css("display","block");
           $("#search_recommend_head").css("display","none");
       }
    });
}

//头部搜索框失焦事件
function onHeadBlurSearchBox(){
    $("#searchAllBox1").bind("blur",function () {
        setTimeout(function () {
            $("#history_head").css("display","none");
            $("#search_recommend_head").css("display","none");
        },500)
    });
}

//头部搜索框输入事件
function onHeadInputSearchBox(){
    $("#searchAllBox1").on("input",function () {
       var val = $.trim($(this).val());
       //如果搜索框中没有东西  就显示历史记录
        if(val == ""){
            //取出历史记录
            pullHeadHistory();
            $("#history_head").css("display","block");
            $("#search_recommend_head").css("display","none");
        }else{
            //动态返回匹配结果
            var data = {
              searchKey : $("#searchAllBox1").val()
            };
            $.post(rootPath+"/search.getSearchInfo.ajax",data,function (data) {
                var str = "";
                var musicList = data.musicList.solrBeanList;
                for(var i=0;i<musicList.length;i++){
                    var bean = musicList[i];
                    var searchStr = bean.highlight.replace("<em>","").replace("</em>","").replace(" ","");
                    str = str + "<dl class=\"recommend_list_head\" onclick='onHeadHistorySearchBoxSubmit(\""+searchStr+"\")'>\n" +
                        "                        <dd data-type=\"song\" title=\"\">\n" +
                        "                            "+bean.highlight+"\n" +
                        "                        </dd>\n" +
                        "                    </dl>";
                }
                str = str + "<dl style=\"border-top:1px solid #ddd;\">\n" +
                    "                        <span style=\"margin-left: 10px;\">MV</span>";
                var mvList = data.mvList.solrBeanList;
                for(var i=0;i<mvList.length;i++){
                    var bean = mvList[i];
                    var searchStr = bean.highlight.replace("<em style='color:#14a9ff;'>","").replace("</em>","").replace(" ","");
                    str = str + "<dd onclick='onHeadHistorySearchBoxSubmit(\""+searchStr+"\")'>\n" +
                        "                            "+bean.highlight+"\n" +
                        "                        </dd>";
                }
                str = str + "</dl>";
                $("#search_recommend_head").html(str);
            });
            $("#history_head").css("display","none");
            $("#search_recommend_head").css("display","block");
        }
    });
}

//存储history的方法
function pushHeadHistory(searchKey){
    var str = localStorage.getItem("searchHistory")
    if(str == null){
        localStorage.setItem("searchHistory",searchKey)
    }else{
        var arr = str.split("|");
        var result = "";
        result = searchKey;
        for(var i=0;i<arr.length;i++){
            if(arr[i] != $.trim(searchKey)){
                result = result + "|" + arr[i];
            }
        }
        localStorage.setItem("searchHistory",result)
    }
}

//取出history的方法
function pullHeadHistory() {
    var str = localStorage.getItem("searchHistory");
    if(str == null){
        $("#history_head").html(
           "<dl class=\"clear_history_head\" style=\"font-size: 10px;\">\n" +
            "                        <dt>没有历史记录~~~</dt>\n" +
            "                    </dl>"
        );
    }else{
        var str1 = "";
        var arr = str.split("|");
        for(var i=0;i<arr.length;i++){
            str1 = str1 + "<dl class=\"history_list_head\" onclick='onHeadHistorySearchBoxSubmit(\""+arr[i]+"\")'>\n" +
                "                        <dd>"+arr[i]+"</dd>\n" +
                "                    </dl>";
        }
        str1 = str1 + "<dl class=\"clear_history_head\" style=\"font-size: 10px;\" onclick='clearHeadHistory()'>\n" +
            "                        <dt>清空历史记录</dt>\n" +
            "                    </dl>";
        $("#history_head").html(str1);
    }
}
//清空history的方法
function clearHeadHistory(){
    localStorage.removeItem("searchHistory");
    pullHeadHistory();
}
/////////////////////////////搜索框结束///////////////////////////////




/**
 * qq分享
 * @param url       链接地址
 * @param desc      描述
 * @param title     标题
 * @param summary   内容
 * @param pics      图片
 */
function qqShare(url,title,summary,pics){
    var urlPath = "https://connect.qq.com/widget/shareqq/index.html?url="+ encodeURI(url) +
                  "&desc=&title=" + title +
                  "&summary=" + summary +
                  "&pics=" + pics;
    window.open (urlPath, 'qq分享', 'height=637, width=1053, top=195,left=459, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no')
}