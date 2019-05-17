////二级菜单  更多
//全局的项目根路径
var rootPath = $("#absoPath").val();
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
            $.post(rootPath+"search.getSearchInfo.ajax",data,function (data) {
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

