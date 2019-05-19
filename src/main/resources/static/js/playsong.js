function audioStopStart(){
    if($.trim($("#myAudio").attr("src")) == ""){
        msgBoxOne("这首歌因为版权原因，不能播放！");
        return;
    }
    $("#btn_play").toggle();
    $("#btn_stop").toggle();
    var player = $("#myAudio")[0]; /*jquery对象转换成js对象*/
    if (player.paused){ /*如果已经暂停*/
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
            var list = data.musicPlayList.musicPlayList;
            // if(list.length == 0){
            //
            // }else {
            //
            // }
        }
    });
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

