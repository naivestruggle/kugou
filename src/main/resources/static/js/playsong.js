function audioStopStart(){
    // console.log(document.getElementById("btn_play"));
    // console.log($("#btn_play"))
    $("#btn_play").toggle();
    $("#btn_stop").toggle();
    var player = $("#myAudio")[0]; /*jquery对象转换成js对象*/
    if (player.paused){ /*如果已经暂停*/
        player.play(); /*播放*/
    }else {
        player.pause();/*暂停*/
    }
}