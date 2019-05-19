function audioStopStart(){
    if($.trim($("#myAudio").attr("src")) == ""){
        msgBoxOne("这首歌因为版权原因，不能播放！");
        return;
    }
    $("#btn_play").toggle();
    $("#btn_stop").toggle();
    var player = $("#myAudio")[0]; /*jquery对象转换成js对象*/
    if (player.paused){ /*如果已经暂停*/
        //判断播放列表是否为null
        var listLenth = $(".musicListSize").html();
        console.log("当前列表："+listLenth);
        if(listLenth == "0"){
            console.log("进来了...");
            //将当前播放的歌曲添加到播放列表
            $.post(rootPath+"/music.addNowPlayMusicToMusicPlayList.ajax",{musicId : $("#simpleMusicId").val()},function (data) {
                var code = data.code;
                if(code == -1){
                    msgBoxOne("系统繁忙，请稍后再试");
                }else if (code == 0){
                    msgBoxOne(data.msg);
                } else if(code == 1){
                    console.log(list);
                    var list = data.musicPlayList.musicPlayLists;
                    if(list == null){
                        //没有播放列表
                        $(".musicListSize").html("0");
                        $(".musiclist").html("<br/><br/><br/><br/><br/><br/><h2 style='text-align: center;'>你还没有选择要播放的音乐~~</h2>");
                    }else {
                        //有播放列表
                        flushMusicPlayList(list);
                    }
                }
            })
        }
        player.play(); /*播放*/
    }else {
        player.pause();/*暂停*/
    }
}

$(function () {
    //页面加载完毕  发送请求来获得播放列表
    onPageLoadMusicListLoad();

    //播放列表按钮的点击事件
    onClickMusicListShowButton();

    //播放列表 的事件
    musicListOnMouse();
});

////////////////////////////////////播放列表模版开始///////////////////////////////////////////////////
//页面加载完毕  发送请求来获得播放列表
function onPageLoadMusicListLoad(){
    $.post(rootPath+"/music.loadMusicPlayList.ajax",null,function (data) {
        var code = data.code;
        if(code == -1){
            msgBoxOne("系统繁忙，请稍后再试");
        }else if (code == 0){
            msgBoxOne(data.msg);
        } else if(code == 1){
            var list = data.musicPlayList.musicPlayLists;
            if(list == null){
                //没有播放列表
                $(".musicListSize").html("0");
                $(".musiclist").html("<br/><br/><br/><br/><br/><br/><h2 style='text-align: center;'>你还没有选择要播放的音乐~~</h2>");
            }else {
                //有播放列表
                flushMusicPlayList(list);
            }
        }
    });
}

//刷新播放列表
function flushMusicPlayList(list) {
    $(".musicListSize").html(list.length);
    var nowMusicId = $("#simpleMusicId").val();
    var nowMusicStr = "";
    var str = "";
    var j = 2;
    for(var i=list.length-1;i>=0;i--){
        var bean = list[i];
        var num = j;
        if(nowMusicId == bean.musicId){
            num = 1;
        }else{
            j++;
        }
        var thisStr = "<li class=\"active\" onmouseover=\"liMouserover(this)\" onmouseout=\"liMouserout(this)\">\n" +
            "\t\t\t\t\t\t\t\t\t\t\t<div class=\"musiclist-item clearfix\">\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"musiclist-number\">"+num+"</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"musiclist-name\">\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"";
        if(nowMusicId == bean.musicId){
            thisStr += "musiclist-songname-txt";
        }else {
            thisStr += "musiclist-artist";
        }

        thisStr += "\" title=\""+bean.musicName+"\">"+bean.musicName+"</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"mod-list-menu\">\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:;\" class=\"icon list-menu-item icon-playbar-download\" title=\"下载\">\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"icon list-menu-icon-down\"></i>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"icon_txt\">下载</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</a>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:;\" class=\"icon list-menu-item icon-playbar-share\" title=\"分享\">\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"icon list-menn-icon-share\"></i>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"icon_txt\">分享</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</a>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:;\" class=\"icon list-menu-item list-menu-icon-del\" title=\"删除歌曲\">\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"icon list-menu-icon-add\"></i>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"icon_txt\">删除</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</a>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"musiclist-artist\" title=\""+bean.musicAuthorName+"\">"+bean.musicAuthorName+"</div>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"musiclist-time\">"+bean.musicTimes+"</div>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t\t\t</li>";
        if(nowMusicId == bean.musicId){
            nowMusicStr = thisStr;
        }else {
            str += thisStr;
        }
    }
    str = nowMusicStr + str;
    $(".musiclist").html(str);
}

//播放列表按钮的点击事件
function onClickMusicListShowButton(){
    $("#music_list_show_button").click(function () {
        $(".mod-playlist").toggle();
    });
}

//播放列表 事件
function musicListOnMouse(){
    //关闭按钮点击事件
    $("#close_musiclist_panel").click(function () {
        $(".mod-playlist").css("display","none");
    });
    //清空所有歌曲按钮的点击事件
    $("#clearAllMusicPlayList").click(function () {
        var dblChoseAlert = simpleAlert({
            "content":"清空播放列表会使当前音乐停止播放，确定清空播放列表？",
            "buttons":{
                "确定":function () {
                    $.post(rootPath+"/music.clearAllMusicPlayList.ajax",null,function (data) {
                        var code = data.code;
                        if(code == -1){
                            msgBoxOne("系统繁忙，请稍后再试");
                        }else if(code == 0){
                            msgBoxOne(data.msg);
                        }else if(code == 1){
                            $(".musicListSize").html("0");
                            $(".musiclist").html("<br/><br/><br/><br/><br/><br/><h2 style='text-align: center;'>你还没有选择要播放的音乐~~</h2>");
                            $(".mod-playlist").css("display","none");
                            againAudio();
                        }
                    });
                    dblChoseAlert.close();
                },
                "取消":function () {
                    dblChoseAlert.close();
                }
            }
        });
    });
}

//歌曲进度重置为0  并且暂停
function againAudio(){
    $("#btn_play").css("display","block");
    $("#btn_stop").css("display","none");
    $("#myAudio")[0].currentTime = 0;
    $("#myAudio")[0].pause();/*暂停*/
}

//歌曲的移入事件
function liMouserover(obj) {
    $(obj).find(".mod-list-menu").css("visibility","inherit");
}

//歌曲的移出事件
function liMouserout(obj) {
    $(obj).find(".mod-list-menu").css("visibility","hidden");
}

////////////////////////////////////播放列表模版结束///////////////////////////////////////////////////

