
    $(function () {
        $(".tab>li").click(function () {
            $(this).addClass("active").siblings("li").removeClass("active");
            var index=$(this).index();
            $(".right>div:eq("+index+")").addClass("selected").siblings("div").removeClass("selected");
        });
        $(".upload-tips-btn").mouseenter(function(){

        	$(".video-tips").css("display","block");
        });
         $(".upload-tips-btn").mouseleave(function(){

        	$(".video-tips").css("display","none");
        });
        
         	$(".win_select_ul>li").click(selectClick);
        
          $(".video-input .classify-icon").click(function(){
          		$("#creat_selectTag").toggleClass("in");
          });
           $(".classify-ipt .classify-icon").click(function(){
           		// console.log("a");
          		$(".createlist #creat_selectTag").toggleClass("in");
          });
          $("#uploadBtn").click(function(){
          	$(".upload_inp").click();
          });
           $(".sel_img").click(function(){
          	$(".sel").click();
          });
           $(".songlist_edit").click(function(){
$(".create").addClass("selected").siblings().removeClass("selected");
$(".tab>li:nth-child(2)").addClass("active").siblings().removeClass("active");
           });

         
         
    });

	var addSelectClick = false;
    function selectClick(){

   //  	//父元素是
   //   	if($(this).parent().is(".win_select_ul")){
	  //       var val= $(this).val();
	  //                   //是有value属性为val的option
	  //       if(!$(".select_box").is(`:has([value=${val}])`)){
	  //          	$(this).clone(true).appendTo(".select_box");
	  //      		$(".classify-input").hide();
	  //      		if($(".select_box>li").length>=3){
	  //      			console.log($(".select_box>li").length);
	  //      			$(".video-style .error").show();
	  //      			$(".win_select_ul>li").unbind("click",selectClick);
	  //      			addSelectClick = false;
	       			
   //     			}
	  //     }else{
   //    		$(this).remove();
	  //     }
 		// }else{
 		// 	$(this).remove();
 		// 	if(!addSelectClick){
 		// 		$(".win_select_ul>li").bind("click",selectClick);
 		// 		addSelectClick = true;
 		// 	}
 		// }

		var addSelectClick = false;
 		//如果是下面的元素
 		if($(this).parent().is(".win_select_ul")){
 			$(".classify-input").hide();
 			//如果长度已经大于3了  什么都不做

 			if($(".select_box>li").length>=3){

 				$(".video-style .error").show();
 				return;
 			}
 			
 			var val= $(this).val();
 			
 			$(".select_box li").each(function(){
 				if($(this).val()==val){

 					addSelectClick = true;
 				}
 			});
 			
 			//如果上面已经有了
 			if(addSelectClick){
 				return;
 			}else{
 				//如果上面没有  添加到上面
 				//克隆一个下面的元素到上面
 				$(this).clone(true).appendTo(".select_box");

 			}
 		}else{
 			//如果是上面的元素
 			$(this).remove();
 		}
 	}
