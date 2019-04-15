$(function () {
$(".search_tab>li").click(function () {
	$(this).addClass("active").siblings("li").removeClass("active");
	var index=$(this).index();
	$(".tab_content>div:eq("+index+")").addClass("selected").siblings("div").removeClass("selected");
});


	$(".checkall").click(function(){
		 $(this).toggleClass("checked");
		if($(this).hasClass("checked")){
			$(".checkbox").each(function(){
				$(".checkbox").addClass("checked");
			});
		}else{
			$(".checkbox").each(function(){
				$(this).removeClass("checked");
			});
		}
	});

	//循环子按钮
	$(".checkbox").each(function(){

		//添加点击事件
		$(this).click(function(){
			$(this).toggleClass("checked");
			var flag = true;
			$(".checkbox").each(function(){
				if(!$(this).hasClass("checked")){
					flag = false;
				}
			});
			if(flag){
				$(".checkall").addClass("checked");
			}else{
				$(".checkall").removeClass("checked");
			}
		});

	});

});

$(function () {
    //页面加载完毕  调用获取mv信息
	getMvList();
    //页面加载完毕  调用获取歌单信息
    getMusicList();
    //给搜索页面的输入框绑定回车事件
	addSearchBoxKeypress();
	//给搜索页面的搜索按钮添加点击事件
	addSeachButtonSubmit();
});

//给搜索页面的输入框绑定回车事件
function addSearchBoxKeypress(){
	$("#searchAllBox2").bind("keypress",function (event) {
		if(event.keyCode == '13'){
			onSearchBoxSubmit();
		}
	});
}
//给搜索页面的搜索按钮添加点击事件
function addSeachButtonSubmit(){
	$("#searchAllBox1SubmitButton2").bind("click",function () {
		onSearchBoxSubmit();
	});
}
//搜索页面的搜索框的提交事件
function onSearchBoxSubmit(){
	var searchKey = "";
	var form = $("#searchForm2");
	var inp = $("#searchAllBox2");
	if($.trim(inp.val()) != ""){
		searchKey = $.trim(inp.val());
	}else if($.trim(inp.attr("placeholder")) != ''){
		searchKey = $.trim(inp.attr("placeholder"));
	}else{
		alert("请输入搜索关键字");
		return;
	}
	// console.log(form.attr("rel")+"/search/"+searchKey);
	form.attr("action",rootPath+"search/"+searchKey);
	form.submit();
}

//页面加载完毕  调用获取歌单信息
function getMusicList(){
	var inp = $("#searchAllBox2");
	var data = {
		searchKey : inp.val()
	}
	$.post(rootPath+"musiclist.search.ajax",data,function (data) {
		var list = data.lists.solrBeanList;
		console.log(data)
		if(list.length == 0){
			//没搜到
			return;
		}
		for(var i=0;i<list.length;i++){
			var bean = list[i];
			var str = "<li>\n" +
				"\t\t            \t\t<div class=\"left clearfix\">\n" +
				"\t\t            \t\t\t<a class=\"songsheet_link\" target=\"_blank\" title=\"\" href=\"\">\n" +
				"\t\t            \t\t\t\t<img class=\"songsheet_img\" src='"+bean.musicListHeadImage+"'/>\n" +
				"\t\t            \t\t\t</a>\n" +
				"\t\t            \t\t\t<a target=\"_blank\" class=\"songsheet_name songsheet_link\" title=\"\" href=\"\">"+bean.musicListName+"\n" +
				"\t\t            \t\t\t</a>\n" +
				"\t\t            \t\t</div>\n" +
				"\t\t            \t\t<div class=\"middle\">\n" +
				"\t\t            \t\t\t"+bean.musicListUserUsername+"\n" +
				"\t\t            \t\t</div>\n" +
				"\t\t            \t\t<div class=\"right\">"+bean.musicListCollectCount+"</div>\n" +
				"\t\t            \t\t<div class=\"play-item\">\n" +
				"\t\t            \t\t\t<span class=\"icon_play\"></span>\n" +
				"\t\t            \t\t</div>\n" +
				"\t\t            \t</li>\n";
			$("#music_list_show_list").append(str);
		}
	});
}

//页面加载完毕  调用获取mv信息
function getMvList() {
	var inp = $("#searchAllBox2");
	var data = {
		searchKey : inp.val()
	}
	$.post(rootPath+"mv.search.ajax",data,function (data) {
		var list = data.mvSolrBean.solrBeanList;
		if(list.length == 0){
		    //没有搜到mv
            $(".search_mv").show();
            $(".mv_list").hide();
		    return;
        }
		for(var i=0;i<list.length;i++){
		    var bean = list[i];
            var str = "<li>\n" +
                "\t        \t\t\t\t<div class=\"mv_container\">\n" +
                "\t        \t\t\t\t\t<a class=\"mv_cover\" target=\"_blank\" href='"+rootPath+"mvPlay.html/"+bean.mvId+"' hidefocus=\"true\" title=\""+bean.mvName+"\"> \n" +
                "\t        \t\t\t\t\t\t<span class=\"mv_img\">\n" +
                "\t        \t\t\t\t\t\t\t<img width=\"100%\" height=\"100%\" src='"+bean.mvHeadImage+"' alt=\"网络加载问题，请重新加载\" class=\"\">\n" +
                "\t        \t\t\t\t\t\t</span>\n" +
                "\t        \t\t\t\t\t\t<span class=\"mv_play\">\n" +
                "\t        \t\t\t\t\t\t\t<i class=\"search_icon\"></i>\n" +
                "\t        \t\t\t\t\t\t</span>\n" +
                "\t        \t\t\t\t\t\t<span class=\"mv_shadow\"></span>\n" +
                "\t        \t\t\t\t\t</a>\n" +
                "\t        \t\t\t\t\t<a class=\"mv_name\" target=\"_blank\" href=\"\" hidefocus=\"true\" title=\"\">"+bean.mvHighlightNamne+"</a> \n" +
                "\t        \t\t\t\t\t<span class=\"mv_singer\" title=\"\">"+bean.mvHighlightAuthorName+"</span>\n" +
                "\t        \t\t\t\t</div>\n" +
                "\t        \t\t\t</li>";

		    $("#mvUlList").append(str);
        }

        //给mv添加移入移出事件
        $(".mv_list .mv_container").mouseenter(function(e){
            e.preventDefault();
            $(this).find(".mv_play").css("visibility","visible");
            $(this).find(".mv_shadow").css("visibility","visible");
        });
        $(".mv_list .mv_container").mouseleave(function(e){
            e.preventDefault();
            $(this).find(".mv_play").css("visibility","hidden");
            $(this).find(".mv_shadow").css("visibility","hidden");
        });

	});


}