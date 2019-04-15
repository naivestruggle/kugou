function audioStopStart(){
    if($.trim($("#myAudio").attr("src")) == ""){
        alert("这首歌因为版权原因，不能播放！");
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