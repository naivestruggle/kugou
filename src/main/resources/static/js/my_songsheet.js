$(function () {

    inti();
    showHtml();
    SwitchSongs();
    delSongSheet();
    addSongSheet();
    editSongSheet();
    playSong();
    addMusicPlayList();
    addMusicToSongSheet();
    delMusicFromSongSheet();
    shareMusic();
    downloadMusic();

    //初始化
    function inti() {
        $(".create ul").show();
        $(".create #icon").toggleClass("songsheet_icon1");

        $(".create #icon").click(function () {
            $(this).toggleClass("songsheet_icon1");
            $(".create ul").stop().toggle(500);
        });
        $(".colleted #icon").click(function () {
            $(this).toggleClass("songsheet_icon1");
            $(".colleted ul").stop().toggle(500);
        });


        $("body").delegate(".songlist", "mouseenter", function () {
            $(this).children(".handle").show();
        });
        $("body").delegate(".songlist", "mouseleave", function () {
            $(this).children(".handle").hide();
        });


        $(".new_create i").click(function () {
            $(".create_list").show();
        });
        $(".create_list .close").click(function () {
            $(".create_list").hide();
        });


        $(".delete_box .close").click(function () {
            $(".delete_box").hide();
        });
        $(".addToList .close").click(function () {
            $(".addToList").hide();
        });
        // $(".create_body .btn2").click(function () {
        //     $(".create_list").hide();
        // });
        $(".delete_body .btn2").click(function () {
            $(".delete_list").hide();
        });
        // $(".delete").click(function () {
        //     $(".delete_box").show();
        // });
        // $(".delete_body .btn2").click(function () {
        //     $(".delete_box").hide();
        // });


        $("body").delegate(".m-table tbody tr", "mouseenter", function () {
            $(this).find(".icons").show();
            $(this).find(".total_time").hide();
        });
        $("body").delegate(".m-table tbody tr", "mouseleave", function () {
            $(".icons").hide();
            $(".total_time").show();
        });

        // $(".m-table tbody tr").mouseenter(function () {
        //
        //     $(this).find(".icons").show();
        //     $(this).find(".total_time").hide();
        //
        // });

        // $(".m-table tbody tr").mouseleave(function () {
        //     $(".icons").hide();
        //     $(".total_time").show();
        // });

        $(".share_content .close").click(function () {
            $(".share_dialog").hide();
        });
        $(".share_content .btn2").click(function () {
            $(".share_dialog").hide();
        });
        $(".btns .share").click(function () {
            $(".share_dialog").show();
        });


        // $(".icons .icon-share").click(function () {
        //     $(".share_dialog").show();
        // });
        // $(".icons .icon-delete").click(function () {
        //     $(".delete_box").show();
        // });
        // $(".icons .icon-collect").click(function () {
        //     $(".addToList").show();
        // });

        // $(".handle .edit").click(function (e) {
        //     e.stopPropagation();
        //     $(".right .likesong").hide();
        //     $(".right .edit_dialog").show();
        // });
        $("body").delegate(".create ul li", "click", function () {
            $(".right .likesong").show();
            $(".right .edit_dialog").hide();
        });
        // $(".create ul li").click(function () {
        //     $(".right .likesong").show();
        //     $(".right .edit_dialog").hide();
        // });
    }

    //展示初始页面信息
    function showHtml() {
        //获取用户id
        var data = {
            "userId": $("#userId").val()
        };
        //查询当前用户的所有歌单
        $.post("song.querySongSheet.ajax", data, function (data) {
            // console.log(data);
            if (data.code == 1) {
                $(".create .num").html(data.customMusicLists.length);
                for (var i = 0; i < data.customMusicLists.length; i++) {
                    var point;
                    if ("我喜欢的音乐" == data.customMusicLists[i].musicListName) {
                        point = $("<li class=\"songlist selected\">\n" +
                            "<input type=\"hidden\" id=\"musicListId\" value=\"" + data.customMusicLists[i].musicListId + "\">\n" +
                            "\t\t\t\t\t\t\t\t<div class=\"item\">\n" +
                            "\t\t\t\t\t\t\t\t\t<div class=\"pic\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t<a href=\"#\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t<img src=\"" + data.customMusicLists[i].musicListHeadImage + "\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t</a>\n" +
                            "\t\t\t\t\t\t\t\t\t</div>\n" +
                            "\t\t\t\t\t\t\t\t\t<p class=\"name text\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t<a hidefocus=\"true\" href=\"javascript:void(0);\" class=\"s-fc0\" title=\"我喜欢的音乐\">我喜欢的音乐</a>\n" +
                            "\t\t\t\t\t\t\t\t\t</p>\n" +
                            "\t\t\t\t\t\t\t\t\t<p class=\"song_num text\">" + data.customMusicLists[i].musicListMusicCount + "首</p>\n" +
                            "\t\t\t\t\t\t\t\t</div>\n" +
                            "\t\t\t\t\t\t\t</li>");
                    } else {
                        point = $("<li class=\"songlist\">\n" +
                            "<input type=\"hidden\" id=\"musicListId\" value=\"" + data.customMusicLists[i].musicListId + "\">\n" +
                            "<input type=\"hidden\" id=\"musicListDescribe\" value=\"" + data.customMusicLists[i].musicListDescribe + "\">\n" +
                            "<input type=\"hidden\" id=\"musicListMood\" value=\"" + data.customMusicLists[i].musicListMood + "\">\n" +
                            "<div class=\"item\">\n" +
                            "<div class=\"pic\">\n" +
                            "<a href=\"#\">\n" +
                            "<img src=\"" + data.customMusicLists[i].musicListHeadImage + "\">\n" +
                            "</a>\n" +
                            "</div>\n" +
                            "<p class=\"name text\">\n" +
                            "<a hidefocus=\"true\" href=\"javascript:void(0);\" class=\"s-fc0\" title=\"" + data.customMusicLists[i].musicListName + "\">" + data.customMusicLists[i].musicListName + "</a>\n" +
                            "</p>\n" +
                            "<p class=\"song_num text\">" + data.customMusicLists[i].musicListMusicCount + "首</p>\n" +
                            "</div>\n" +
                            "<span class=\"handle\">\n" +
                            "<a title=\"编辑\" href=\"javascript:void(0);\" class=\"edit\"></a>\n" +
                            "<a title=\"删除\" href=\"javascript:void(0);\" class=\"delete\"></a>\n" +
                            "</span>\n" +
                            "</li>");
                    }
                    $(".create>ul").append(point);
                }
                //查询喜欢的歌单信息
                data = {
                    "musicListId": $(".songlist > input").eq(0).val()
                };
                // console.log(data);
                $.post("song.queryMySongSheetList.ajax", data, function (data) {
                    // console.log(data);
                    $(".cover > img").attr("src", data.customMusicList.musicListHeadImage);
                    $(".user_pic > img").attr("src", $("#headImage").attr("src"));
                    $(".uname > a").html(data.customMusicList.musicListUserUsername);
                    $(".user > .create_time").html(data.customMusicList.musicListUpdateTime + "  修改");
                    $(".sub > .count").html(data.customMusicList.musicsList.length);
                    $(".song_title > .more > .paly_count").html(data.customMusicList.musicListListenerCount)
                    for (var i = 0; i < data.customMusicList.musicsList.length; i++) {
                        var musicData = data.customMusicList.musicsList[i];
                        var point = $("<tr>\n" +
                            "\t\t\t\t\t\t\t\t\t\t<td>\n" +
                            "                                            <input type=\"hidden\" id=\"musicId\" value=\"" + musicData.musicId + "\">\n" +
                            "                                            <input type=\"hidden\" id=\"musicImage\" value=\"" + musicData.musicImg + "\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t<span class=\"ply\">&nbsp;</span>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t<span class=\"num\">" + (i + 1) + "</span>\n" +
                            "\t\t\t\t\t\t\t\t\t\t</td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t<td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t<span class=\"txt\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"#\" class=\"song_txt\">" + musicData.musicName + "</a>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t</span>\n" +
                            "\t\t\t\t\t\t\t\t\t\t</td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t<td style=\"color: #666;\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t<span class=\"total_time\">" + musicData.musicTimes + "</span>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t<div class=\"icons\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"u-icon\" title=\"添加到播放列表\"></a>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"icon icon-collect\" title=\"收藏\"></span>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"icon icon-share\" title=\"分享\"></span>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"icon icon-download\" title=\"下载\"></span>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"icon icon-delete\" title=\"删除\"></span>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                            "\t\t\t\t\t\t\t\t\t\t</td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t<td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t<span class=\"txt\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"#\" class=\"song_txt\">" + musicData.musicAuthorName + "</a>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t</span>\n" +
                            "\t\t\t\t\t\t\t\t\t\t</td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t<td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t<span class=\"txt\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"#\" class=\"song_txt\">" + musicData.musicAlbumName + "</a>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t</span>\n" +
                            "\t\t\t\t\t\t\t\t\t\t</td>\n" +
                            "\t\t\t\t\t\t\t\t\t</tr>");

                        $(".m-table > tbody").append(point);
                    }
                });
            } else if (data.code == 0) {
                msgBoxOne(data.msg);
            } else if (data.code == -1) {
                msgBoxOne("系统繁忙，请稍后再试");
            }
        });
    }

    //播放歌曲
    function playSong() {

        $("body").delegate(".ply", "click", function () {
            //得到歌曲id
            var musicId = $(this).siblings("#musicId").val();
            window.open("simpleSong.html/" + musicId, "_blank");

            //增加歌单的播放数

        });
    }

    //添加到播放列表
    function addMusicPlayList() {

        $("body").delegate(".u-icon", "click", function () {
            //得到歌曲id
            var data = {
                "musicId": $(this).siblings("#musicId").val()
            };
            $.post("music.addNowPlayMusicToMusicPlayList.ajax", data, function (data) {
                console.log(data);
                if (data.code == 1) {
                    msgBoxOne("已添加到播放列表");
                } else if (data.code == 0) {
                    msgBoxOne(data.msg);
                } else if (data.code == -1) {
                    msgBoxOne("系统繁忙，请稍后再试");
                }
            });
        });
    }

    //切换歌单
    function SwitchSongs() {
        $("body").delegate(".create .songlist", "click", function () {
            if (!$(this).hasClass("selected")) {
                //将选中的歌单加上class
                $(this).addClass("selected");
                //未选中的去除
                $(".create > ul > li").not($(this)).removeClass("selected");

                var data = {
                    //查询喜欢的歌单信息
                    "musicListId": $(this).children("#musicListId").val()
                };
                //查询被点击的歌单的信息
                $.post("song.queryMySongSheetList.ajax", data, function (data) {
                    // console.log(data);
                    if (data.code == 1) {
                        //删除已存在的所有
                        $(".m-table > tbody > tr").remove();

                        $(".cover > img").attr("src", data.customMusicList.musicListHeadImage);
                        $(".user_pic > img").attr("src", $("#headImage").attr("src"));
                        $(".uname > a").html(data.customMusicList.musicListUserUsername);
                        $(".user > .create_time").html(data.customMusicList.musicListUpdateTime + "  修改");
                        $(".top .count").html(data.customMusicList.musicsList.length);
                        $(".song_title > .more > .paly_count").html(data.customMusicList.musicListListenerCount);

                        for (var i = 0; i < data.customMusicList.musicsList.length; i++) {
                            var musicData = data.customMusicList.musicsList[i];
                            var point = $("<tr>\n" +
                                "\t\t\t\t\t\t\t\t\t\t<td>\n" +
                                "                                            <input type=\"hidden\" id=\"musicId\" value=\"" + musicData.musicId + "\">\n" +
                                "                                            <input type=\"hidden\" id=\"musicImage\" value=\"" + musicData.musicImg + "\">\n" +
                                "\t\t\t\t\t\t\t\t\t\t\t<span class=\"ply\">&nbsp;</span>\n" +
                                "\t\t\t\t\t\t\t\t\t\t\t<span class=\"num\">" + (i + 1) + "</span>\n" +
                                "\t\t\t\t\t\t\t\t\t\t</td>\n" +
                                "\t\t\t\t\t\t\t\t\t\t<td>\n" +
                                "\t\t\t\t\t\t\t\t\t\t\t<span class=\"txt\">\n" +
                                "\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"#\" class=\"song_txt\">" + musicData.musicName + "</a>\n" +
                                "\t\t\t\t\t\t\t\t\t\t\t</span>\n" +
                                "\t\t\t\t\t\t\t\t\t\t</td>\n" +
                                "\t\t\t\t\t\t\t\t\t\t<td style=\"color: #666;\">\n" +
                                "\t\t\t\t\t\t\t\t\t\t\t<span class=\"total_time\">" + musicData.musicTimes + "</span>\n" +
                                "\t\t\t\t\t\t\t\t\t\t\t<div class=\"icons\">\n" +
                                "\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"u-icon\" title=\"添加到播放列表\"></a>\n" +
                                "\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"icon icon-collect\" title=\"收藏\"></span>\n" +
                                "\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"icon icon-share\" title=\"分享\"></span>\n" +
                                "\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"icon icon-download\" title=\"下载\"></span>\n" +
                                "\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"icon icon-delete\" title=\"删除\"></span>\n" +
                                "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                                "\t\t\t\t\t\t\t\t\t\t</td>\n" +
                                "\t\t\t\t\t\t\t\t\t\t<td>\n" +
                                "\t\t\t\t\t\t\t\t\t\t\t<span class=\"txt\">\n" +
                                "\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"#\" class=\"song_txt\">" + musicData.musicAuthorName + "</a>\n" +
                                "\t\t\t\t\t\t\t\t\t\t\t</span>\n" +
                                "\t\t\t\t\t\t\t\t\t\t</td>\n" +
                                "\t\t\t\t\t\t\t\t\t\t<td>\n" +
                                "\t\t\t\t\t\t\t\t\t\t\t<span class=\"txt\">\n" +
                                "\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"#\" class=\"song_txt\">" + musicData.musicAlbumName + "</a>\n" +
                                "\t\t\t\t\t\t\t\t\t\t\t</span>\n" +
                                "\t\t\t\t\t\t\t\t\t\t</td>\n" +
                                "\t\t\t\t\t\t\t\t\t</tr>");

                            $(".m-table > tbody").append(point);
                        }
                    } else if (data.code == 0) {
                        msgBoxOne(data.msg);
                    } else if (data.code == -1) {
                        msgBoxOne("系统繁忙，请稍后再试");
                    }
                });
            }
        });
    }

    //删除歌单
    function delSongSheet() {
        //点击删除按钮，显示选择框
        $("body").delegate(".delete", "click", function () {
            $(".delete_box").show();
        });
        //取消删除
        $("body").delegate(".delete_box .btn2", "click", function () {
            $(".delete_box").hide();
        });
        //确认删除
        $("body").delegate(".delete_box .btn1", "click", function () {
            $(".delete_box").hide();
            var data = {
                "musicListId": $(".create ul .selected #musicListId").val()
            };
            $.post("song.delSongSheet.ajax", data, function (data) {
                if (data.code == 1) {
                    $(".create ul .selected").remove();

                    //将歌单数-1
                    $(".create .num").html(parseInt($(".create .num").html()) - 1);

                    //将歌单列表切换到我喜欢
                    $(".create .songlist").eq(0).click();
                } else if (data.code == 0) {
                    msgBoxOne(data.msg);
                } else if (data.code == -1) {
                    msgBoxOne("系统繁忙，请稍后再试");
                }
            });

        });

    }

    //新建歌单
    function addSongSheet() {
        //取消新建
        $(".create_body .btn2").click(function () {
            $(".create_list").hide();
            //清空歌单名
            $(".body_content .list_txt").val("");
        });
        //确定新建
        $(".create_body .btn1").click(function () {
            $(".create_list").hide();

            var data = {
                "musicListName": $(".body_content .list_txt").val()
            };

            $.post("song.addSongSheet.ajax", data, function (data) {
                // console.log(data);
                if (data.code == 1) {
                    var musicData = data.customMusicList;
                    var point = $("<li class=\"songlist\">\n" +
                        "<input type=\"hidden\" id=\"musicListId\" value=\"" + musicData.musicListId + "\">\n" +
                        "                                <div class=\"item\">\n" +
                        "                                    <div class=\"pic\">\n" +
                        "                                        <a href=\"#\">\n" +
                        "                                            <img src=\" " + musicData.musicListHeadImage + " \" >\n" +
                        "                                        </a>\n" +
                        "                                    </div>\n" +
                        "                                    <p class=\"name text\">\n" +
                        "                                        <a hidefocus=\"true\" href=\"javascript:void(0);\" class=\"s-fc0\" title=\"" + musicData.musicListName + "\">" + musicData.musicListName + "</a>\n" +
                        "                                    </p>\n" +
                        "                                    <p class=\"song_num text\">" + musicData.musicListMusicCount + "首</p>\n" +
                        "                                </div>\n" +
                        "                                <span class=\"handle\">\n" +
                        "\t\t\t\t\t\t\t\t\t<a title=\"编辑\" href=\"javascript:void(0);\" class=\"edit\"></a>\n" +
                        "\t\t\t\t\t\t\t\t\t<a title=\"删除\" href=\"javascript:void(0);\" class=\"delete\"></a>\n" +
                        "\t\t\t\t\t\t\t\t</span>\n" +
                        "                            </li>");

                    $(".create>ul").append(point);

                    //将歌单数+1
                    $(".create .num").html(parseInt($(".create .num").html()) + 1);
                } else if (data.code == 0) {
                    msgBoxOne(data.msg);
                } else if (data.code == -1) {
                    msgBoxOne("系统繁忙，请稍后再试");
                }
            });

            //清空歌单名
            $(".body_content .list_txt").val("");
        });

    }

    //编辑歌单
    function editSongSheet() {
        $("body").delegate(".handle .edit", "click", function (e) {
            e.stopPropagation();
            $(".right .likesong").hide();
            $(".right .edit_dialog").show();
            $(this).parents(".songlist").addClass("selected");
            $(".create > ul > li").not($(this).parents(".songlist")).removeClass("selected");

            //将歌单信息填写在修改页面上
            $(".edit_title .name").html($(".selected .name .s-fc0").html());
            $(".itm .songsheetName").val($(".selected .name .s-fc0").html());
            $(".edit_right img").attr("src", $(".selected .pic img").attr("src"));

            var mood = $(".selected").children("#musicListMood").val();
            if (mood != null && "null" != mood) {
                $(".itm .mood").val(mood);
            } else {
                $(".itm .mood").val("");
            }
            var desc = $(".selected").children("#musicListDescribe").val();
            if (desc != null && "null" != desc) {
                $(".itm .u-txtwrap .area").val(desc);
            } else {
                $(".itm .u-txtwrap .area").val("");
            }

            //查询歌单信息
            var data = {
                "musicListId": $(".create ul .selected #musicListId").val()
            };
            $.post("song.queryMySongSheetList.ajax", data, function (data) {
                // console.log(data);
                if (data.code == 1) {
                    //删除已存在的所有
                    $(".m-table > tbody > tr").remove();

                    $(".cover > img").attr("src", data.customMusicList.musicListHeadImage);
                    $(".user_pic > img").attr("src", $("#headImage").attr("src"));
                    $(".uname > a").html(data.customMusicList.musicListUserUsername);
                    $(".user > .create_time").html(data.customMusicList.musicListUpdateTime + "  修改");
                    $(".top .count").html(data.customMusicList.musicsList.length);
                    $(".song_title > .more > .paly_count").html(data.customMusicList.musicListListenerCount)
                    for (var i = 0; i < data.customMusicList.musicsList.length; i++) {
                        var musicData = data.customMusicList.musicsList[i];
                        var point = $("<tr>\n" +
                            "\t\t\t\t\t\t\t\t\t\t<td>\n" +
                            "                                            <input type=\"hidden\" id=\"musicId\" value=\"" + musicData.musicId + "\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t<span class=\"ply\">&nbsp;</span>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t<span class=\"num\">" + (i + 1) + "</span>\n" +
                            "\t\t\t\t\t\t\t\t\t\t</td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t<td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t<span class=\"txt\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"#\" class=\"song_txt\">" + musicData.musicName + "</a>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t</span>\n" +
                            "\t\t\t\t\t\t\t\t\t\t</td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t<td style=\"color: #666;\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t<span class=\"total_time\">" + musicData.musicTimes + "</span>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t<div class=\"icons\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"u-icon\"></a>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"icon icon-collect\"></span>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"icon icon-share\"></span>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"icon icon-download\"></span>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"icon icon-delete\"></span>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                            "\t\t\t\t\t\t\t\t\t\t</td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t<td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t<span class=\"txt\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"#\" class=\"song_txt\">" + musicData.musicAuthorName + "</a>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t</span>\n" +
                            "\t\t\t\t\t\t\t\t\t\t</td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t<td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t<span class=\"txt\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"#\" class=\"song_txt\">" + musicData.musicAlbumName + "</a>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t</span>\n" +
                            "\t\t\t\t\t\t\t\t\t\t</td>\n" +
                            "\t\t\t\t\t\t\t\t\t</tr>");

                        $(".m-table > tbody").append(point);
                    }
                }
            });

            //保存歌单信息
            $(".edit_left .btn1").click(function () {

                var data = {
                    "musicListId": $(".create ul .selected #musicListId").val(),
                    "musicListName": $(".itm .songsheetName").val(),
                    "musicListDescribe": $(".itm .u-txtwrap .area").val(),
                    "musicListMood": $(".itm .mood").val()
                };

                $.post("song.updateSongSheet.ajax", data, function (data) {
                    // console.log(data);
                    if (data.code == 1) {
                        $(".selected .name .s-fc0").html(data.customMusicList.musicListName);
                        $(".selected #musicListMood").val(data.customMusicList.musicListMood);
                        $(".selected #musicListDescribe").val(data.customMusicList.musicListDescribe);
                        msgBoxOne("保存成功");
                    } else if (data.code == 0) {
                        msgBoxOne(data.msg);
                    } else if (data.code == -1) {
                        msgBoxOne("系统繁忙，请稍后再试");
                    }
                    $(".right .likesong").show();
                    $(".right .edit_dialog").hide();
                });
            });


            //取消
            $(".edit_left .btn2").click(function () {
                $(".right .likesong").show();
                $(".right .edit_dialog").hide();
            });
        });
    }

    //将歌曲添加到歌单
    function addMusicToSongSheet() {
        var musicId;
        $("body").delegate(".icons .icon-collect", "click", function () {
            $(".addToList").show();
            //获取歌曲id
            musicId = $(this).parents("tr").find("#musicId").val();

            //移除所有歌单
            $(".newlist .songlist").remove();
            //将所有歌单展示
            var songList = $(".create .songlist");
            for (var i = 0; i < songList.length; i++) {
                var songListInfo = songList.eq(i);
                var point = $("<li class=\"songlist\">\n" +
                    "<input type=\"hidden\" id=\"musicListId\" value=\"" + songListInfo.find("#musicListId").val() + "\">\n" +
                    "\t\t\t\t\t\t\t\t<div class=\"item\">\n" +
                    "\t\t\t\t\t\t\t\t\t<div class=\"pic\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t<a href=\"#\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<img src=\" " + songListInfo.find("img").attr("src") + " \">\n" +
                    "\t\t\t\t\t\t\t\t\t\t</a>\n" +
                    "\t\t\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t\t\t\t<p class=\"name text\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t<a hidefocus=\"true\" href=\"javascript:void(0);\" class=\"s-fc0\" title=\"" + songListInfo.find(".s-fc0").html() + "\">" + songListInfo.find(".s-fc0").html() + "</a>\n" +
                    "\t\t\t\t\t\t\t\t\t</p>\n" +
                    "\t\t\t\t\t\t\t\t\t<p class=\"song_num text\">" + songListInfo.find(".song_num").html() + "</p>\n" +
                    "\t\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t\t</li>");

                $(".newlist ul").append(point);
            }
        });

        //确定
        $("body").delegate(".newlist .songlist", "click", function () {
            //获取当前点击的歌单下标
            var index = $(this).index(".newlist .songlist");
            var data = {
                "musicListId": $(this).find("#musicListId").val(),
                "musicId": musicId
            };
            $.post("song.addMusicToSongSheet.ajax", data, function (data) {
                // console.log(data);
                if (data.code == 1) {
                    msgBoxOne("收藏成功");

                    //将对应的歌单数+1
                    var song_num = parseInt($(".create .songlist").eq(index).find(".song_num").html());
                    $(".create .songlist").eq(index).find(".song_num").html(song_num + 1 + "首");
                } else if (data.code == 0) {
                    msgBoxOne(data.msg);
                } else if (data.code == -1) {
                    msgBoxOne("系统繁忙，请稍后再试");
                }
            });
            //将收藏框隐藏
            $(".addToList").hide();
        });
    }

    //将歌曲从歌单中删除
    function delMusicFromSongSheet() {
        var musicId;
        var index;
        $("body").delegate(".icons .icon-delete", "click", function () {
            //获取歌曲id
            musicId = $(this).parents("tr").find("#musicId").val();
            //获取选中歌曲的下标
            index = $(this).index(".middle tbody tr .icon-delete");
            $(".delete_box2").show();
        });
        //取消删除
        $("body").delegate(".delete_box2 .btn2", "click", function () {
            $(".delete_box2").hide();
        });
        //确认删除
        $("body").delegate(".delete_box2 .btn1", "click", function () {
            $(".delete_box2").hide();
            var data = {
                "musicListId": $(".create ul .selected #musicListId").val(),
                "musicId": musicId
            };
            $.post("song.delMusicFromSongSheet.ajax", data, function (data) {
                if (data.code == 1) {
                    //将歌曲从页面删除
                    var musicList = $(".middle tbody tr");
                    musicList.eq(index).remove();
                    for (var i = index+1; i < musicList.length; i++) {
                        musicList.eq(i).find(".num").html(parseInt(musicList.eq(i).find(".num").html()) - 1);
                    }
                    //将歌单的歌曲数-1
                    var songCount = parseInt($(".create .selected .song_num").html());
                    $(".create .selected .song_num").html(songCount - 1 + "首");
                } else if (data.code == 0) {
                    msgBoxOne(data.msg);
                } else if (data.code == -1) {
                    msgBoxOne("系统繁忙，请稍后再试");
                }
            });

        });

    }

    //分享歌曲
    function shareMusic(){
        $("body").delegate(".icons .icon-share", "click", function () {
            var musicId = $(this).parents("tr").find("#musicId").val();
            var url = "http://www.sansheng2019.cn/simpleSong.html/" + musicId;
            var title = $(this).parents("tr").find(".song_txt").eq(1).html() + "-" + $(this).parents("tr").find(".song_txt").eq(0).html();
            var summary = "我在音梦音乐常听的《"+title+"》，你也来听听吧！";
            var pics = $(this).parents("tr").find("#musicImage").val();
            qqShare(url,title,summary,pics);
        });
    }

    //下载歌曲
    function downloadMusic() {
        // $("body").delegate(".icons .icon-download", "click", function () {
        //
        // });
    }
});
