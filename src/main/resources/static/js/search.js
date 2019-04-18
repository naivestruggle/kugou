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

			$(".mv_list .mv_container").mouseenter(function(e){
				e.preventDefault();
				$(".mv_play").css("visibility","visible");
				$(".mv_shadow").css("visibility","visible");
			});
			$(".mv_list .mv_container").mouseleave(function(e){
				e.preventDefault();
				$(".mv_play").css("visibility","hidden");
				$(".mv_shadow").css("visibility","hidden");
			});
		});