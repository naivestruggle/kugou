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
	searchKey = getRealSearchKey(searchKey);
	pushHistory(searchKey);
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


////////////////////////搜索页面的搜索框开始///////////////////////////////////
$(function () {
	//头部搜索框的回车事件
	addSearchBoxKeypress();
	//头部搜索框的提交事件
	addSeachButtonSubmit();
	//头部搜索框的默认值
	addSearchBoxDefaultValue();
	//头部搜索框聚焦事件
	onFocusSearchBox();
	//头部搜索框失焦事件
	onBlurSearchBox();
	//头部搜索框输入事件
	onInputSearchBox();
})



//每次刷新页面时判断头部的搜索框是否为空  如果为空，设为默认
function addSearchBoxDefaultValue(){
	var inp = $("#searchAllBox2");
	if($.trim(inp.val()) == "" && $.trim(inp.attr("placeholder")) == ""){
		$.post(rootPath+"index.searchBox.ajax",null,function (data) {
			if(data.code == 1){
				inp.attr("placeholder",data.searchString);
			}
		});
	}
}

//给头部的输入框绑定回车事件
function addSearchBoxKeypress(){
	$("#searchAllBox2").bind("keypress",function (event) {
		if(event.keyCode == '13'){
			onSearchBoxSubmit();
		}
	});
}
//给头部的搜索按钮添加点击事件
function addSeachButtonSubmit(){
	$("#searchAllBox1SubmitButton2").bind("click",function () {
		onSearchBoxSubmit();
	});
}

//头部搜索框的提交事件
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
	//保存历史搜索
	searchKey = getRealSearchKey(searchKey);
	pushHistory(searchKey);
	form.attr("action",rootPath+"search/"+searchKey);
	form.submit();
}

//点击历史记录的提交事件
function onHistorySearchBoxSubmit(searchKey){
	//保存历史搜索
	var form = $("#searchForm2");
	searchKey = getRealSearchKey(searchKey);
	pushHistory(searchKey);
	form.attr("action",rootPath+"search/"+searchKey);
	form.submit();
}


//头部搜索框聚焦事件
function onFocusSearchBox(){
	$("#searchAllBox2").on("focus",function () {
		if($.trim($(this).val()) == ""){
			//取出历史记录
			pullHistory();
			$("#history").css("display","block");
			$("#search_recommend").css("display","none");
		}
	});
}

//头部搜索框失焦事件
function onBlurSearchBox(){
	$("#searchAllBox2").bind("blur",function () {
		setTimeout(function () {
			$("#history").css("display","none");
			$("#search_recommend").css("display","none");
		},500)
	});
}

//头部搜索框输入事件
function onInputSearchBox(){
	$("#searchAllBox2").on("input",function () {
		var val = $.trim($(this).val());
		//如果搜索框中没有东西  就显示历史记录
		if(val == ""){
			//取出历史记录
			pullHistory();
			$("#history").css("display","block");
			$("#search_recommend").css("display","none");
		}else{
			//动态返回匹配结果
			var data = {
				searchKey : $("#searchAllBox2").val()
			};
			$.post(rootPath+"search.getSearchInfo.ajax",data,function (data) {
				var str = "";
				var musicList = data.musicList.solrBeanList;
				for(var i=0;i<musicList.length;i++){
					var bean = musicList[i];
					var searchStr = bean.highlight.replace("<em>","").replace("</em>","").replace(" ","");
					str = str + "<dl class=\"recommend_list_head\" onclick='onHistorySearchBoxSubmit(\""+searchStr+"\")'>\n" +
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
					str = str + "<dd onclick='onHistorySearchBoxSubmit(\""+searchStr+"\")'>\n" +
						"                            "+bean.highlight+"\n" +
						"                        </dd>";
				}
				str = str + "</dl>";
				$("#search_recommend").html(str);
			});
			$("#history").css("display","none");
			$("#search_recommend").css("display","block");
		}
	});
}

//存储history的方法
function pushHistory(searchKey){
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
function pullHistory() {
	var str = localStorage.getItem("searchHistory");
	if(str == null){
		$("#history").html(
			"<dl class=\"clear_history_head\" style=\"font-size: 10px;\">\n" +
			"                        <dt>没有历史记录~~~</dt>\n" +
			"                    </dl>"
		);
	}else{
		var str1 = "";
		var arr = str.split("|");
		for(var i=0;i<arr.length;i++){
			str1 = str1 + "<dl class=\"history_list_head\" onclick='onHistorySearchBoxSubmit(\""+arr[i]+"\")'>\n" +
				"                        <dd>"+arr[i]+"</dd>\n" +
				"                    </dl>";
		}
		str1 = str1 + "<dl class=\"clear_history_head\" style=\"font-size: 10px;\" onclick='clearHistory()'>\n" +
			"                        <dt>清空历史记录</dt>\n" +
			"                    </dl>";
		$("#history").html(str1);
	}
}
//清空history的方法
function clearHistory(){
	localStorage.removeItem("searchHistory");
	pullHistory();
}

///////////////////////////搜索页面搜索框结束/////////////////////////////////////