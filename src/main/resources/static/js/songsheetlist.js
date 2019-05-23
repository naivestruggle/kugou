// 全选功能
$(function () {
    init();
    collectSongSheet();

    //初始化
    function init(){
        var boolean1 = false;
        var boolean2 = true;
        $("#selAll").click(function () {

            console.log($(this).prop("checked"));
            if ($(this).prop("checked")) {
                $(".checkItem").each(function () {
                    $(this).prop("checked", true);
                });
            } else {
                $(".checkItem").each(function () {
                    $(this).prop("checked", false);
                });
            }
        });

        //循环子按钮
        $(".checkItem").each(function () {
            //添加点击事件
            $(this).click(function () {
                var flag = true;
                $(".checkItem").each(function () {
                    if (!$(this).prop("checked")) {
                        flag = false;
                    }
                });
                if (flag) {
                    $("#selAll").prop("checked", true);
                } else {
                    $("#selAll").prop("checked", false);
                }
            });

        });
        // 点击收听按钮跳转到对应的歌曲播放界面

        $(".listen").click(function () {

            var tempwindow = window.open('_blank');
            tempwindow.location = 'playsong.html';
            // tempwindow.location='playsong.html?id=;
        });
        $(".share_weixin").mouseenter(function () {
            $(".qrcode").css("display", "block");
        });
        $(".share_weixin").mouseleave(function () {
            $(".qrcode").css("display", "none");
        })
        $(".close").click(function () {
            $(".dialog").hide();
        })
        $(".share").click(function () {
            $(".dialog").show();
        })


        //如果歌单创建者是自己就不显示收藏按钮
        if($("#musicListUserId").val() == $("#userId").val() ){
            $(".dq .collect").remove();
        }
    }

    //收藏歌单
    function collectSongSheet(){
        $(".dq .collect").click(function () {
            var data = {
                "musicListId" : $("#musicListId").val()
            };

            $.post("/song.collectSongSheet.ajax",data,function (data) {
               if(data.code == 1){
                   msgBoxOne("收藏成功");
               } else if(data.code == 0){
                   msgBoxOne(data.msg);
               } else if (data.code == -1){
                   msgBoxOne("系统繁忙，请稍后再试");
               }
            });
        });
    }

});
