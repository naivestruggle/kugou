
		$(function(){

			$(".create #icon").click(function(){
				$(this).toggleClass("songsheet_icon1");
				$(".create ul").toggle(500);
			});
			$(".colleted #icon").click(function(){
				$(this).toggleClass("songsheet_icon1");
				$(".colleted ul").toggle(500);
			});
			$(".songlist").mouseenter(function(){
				$(".handle").show();
			});
			$(".songlist").mouseleave(function(){
				$(".handle").hide();
			});
			$(".new_create i").click(function(){
				$(".create_list").show();
			})
			$(".create_list .close").click(function(){
				$(".create_list").hide();
			});
			$(".delete_box .close").click(function(){
				$(".delete_box").hide();
			});
			$(".addToList .close").click(function(){
				$(".addToList").hide();
			});
			$(".create_body .btn2").click(function(){
				$(".create_list").hide();
			});
			$(".delete_body .btn2").click(function(){
				$(".delete_list").hide();
			});
			$(".delete").click(function(){
                $(".delete_box").show();
			});
			$(".delete_body .btn2").click(function(){
				$(".delete_box").hide();
			});

			$(".m-table tbody tr").mouseenter(function(){
			
				$(this).find(".icons").show();
				$(this).find(".total_time").hide();
				
			});
			
			$(".m-table tbody tr").mouseleave(function(){
				$(".icons").hide();
				$(".total_time").show();
			});
			$(".share_content .close").click(function(){
				$(".share_dialog").hide();
			});
			$(".share_content .btn2").click(function(){
				$(".share_dialog").hide();
			});
			$(".btns .share").click(function(){
				$(".share_dialog").show();
			});
			$(".icons .icon-share").click(function(){
				$(".share_dialog").show();
			});
			$(".icons .icon-delete").click(function(){
				$(".delete_box").show();
			});
			$(".icons .icon-collect").click(function(){
				$(".addToList").show();
			});

			$(".handle .edit").click(function(e){
				e.stopPropagation();
				$(".right .likesong").hide();
				$(".right .edit_dialog").show();
			});
			$(".create ul li").click(function(){
				$(".right .likesong").show();
				$(".right .edit_dialog").hide();
			});
		
		});
