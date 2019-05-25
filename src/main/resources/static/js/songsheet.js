
$(function () {
    switchSongSheet();

    //切换歌单榜  推荐，最热，热藏，飙升
    function switchSongSheet(){
        $(".tab>li").click(function () {
            $(this).addClass("active").siblings("li").removeClass("active");
            var index=$(this).index();
            if(index == 0){
                //推荐
            }else if(index == 1){
                //最热
                $.post("song.queryHotListenerSongSheet.ajax",null,function (data) {
                    // console.log(data);
                    if(data.code == 1){
                        createSongSheetList(data);
                    }else if(data.code == 0){
                        msgBoxOne(data.msg);
                    }else if(data.code == 1){
                        msgBoxOne("系统繁忙，请稍后再试");
                    }
                });
            }else if(index == 2){
                //热藏
                $.post("song.queryHotCollectSongSheet.ajax",null,function (data) {
                    // console.log(data);
                    if(data.code == 1){
                        createSongSheetList(data);
                    }else if(data.code == 0){
                        msgBoxOne(data.msg);
                    }else if(data.code == 1){
                        msgBoxOne("系统繁忙，请稍后再试");
                    }
                });
            }else if(index == 3){
                //飙升
            }







            // $(".fr>div:eq("+index+")").addClass("selected").siblings("div").removeClass("selected");
        })
    }

    /**
     * 生成歌单
     * @param data 歌单信息
     */
    function createSongSheetList(data){
        //清空已有歌单
        $(".fr #info li").remove();
        for(var i = 0; i < data.customMusicLists.length; i++){
            var point = $("<li>\n" +
                "                    <div class=\"pic\">\n" +
                "                        <a hidefocus=\"ture\" title=\"\" href=\"songsheetlist/"+data.customMusicLists[i].musicListId+"\" onclick=\"\">\n" +
                "                            <img alt=\"\" src=\""+data.customMusicLists[i].musicListHeadImage+"\"/>\n" +
                "                        </a>\n" +
                "                    </div>\n" +
                "                    <div class=\"detail\">\n" +
                "                        <div class=\"top\">\n" +
                "                            <em>制作人："+data.customMusicLists[i].musicListUserUsername+"</em>\n" +
                "                            <strong>\n" +
                "                                <a title=\""+data.customMusicLists[i].musicListName+"\" href=\"songsheetlist/"+data.customMusicLists[i].musicListId+"\">"+data.customMusicLists[i].musicListName+"</a>\n" +
                "                            </strong>\n" +
                "                        </div>\n" +
                "                        <div class=\"text\">\n" +
                "                            "+data.customMusicLists[i].musicListDescribe+"\n" +
                "                        </div>\n" +
                "                        <div class=\"btn\">\n" +
                "                            <a href=\"javascript:void(0)\" title=\"播放全部\" class=\"playall\">\n" +
                "                                <span><i class=\"t2\"></i>播放全部</span>\n" +
                "                            </a>\n" +
                "                            <a href=\"javascript:void(0)\" class=\"share\" title=\"分享\">\n" +
                "                                <span><i class=\"t2\"></i>分享</span>\n" +
                "                            </a>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </li>");
            $(".fr #info").append(point);
        }
    }


});

