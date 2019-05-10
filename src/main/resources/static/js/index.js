/*轮播*/
var index=1;
var time;
var pic=document.getElementById("pic");
time=setInterval("show()",3000);
function show(id){
	if(Number(id)){
		index=id;
		clearInterval(time);
	}else{
		index++;
		if(index==6){
			index=1;
		}
	}
	document.getElementById("pic").src="img/images/pic_"+index+".jpg";

	var lis=document.getElementsByTagName("#slideWrap li");
	for(var i=0;i<lis.length;i++){
		lis[i].setAttribute("class","scroll_number_out");
		if(i==(index-1)){
			lis[i].setAttribute("class","scroll_number_over");
		}
	}
}

function start(){
	time=setInterval("show()",3000);
}

var myInterval=setInterval(function(){
	autoPlay();
},3000);

var j=5;
function autoPlay(){
	document.querySelectorAll("#head-dian li a")[j-1].style.backgroundPosition="0 -22px";
	j++;
	if(j>=6){
		j=1;
	}
	document.querySelectorAll("#head-dian li a")[j-1].style.backgroundPosition="0 -37px";
}



/*左箭头右箭头*/
var slide_icon1=$(".slide_icon1");//document.getElementsByClassName("slide_icon1")[0];
var slide_icon2=$(".slide_icon2");//document.getElementsByClassName("slide_icon2")[0];
var n=0;
var onoff=true;
$("#pic").mouseover = function(){
	slide_icon1.css("display","block");//style.display="block";
	slide_icon2.css("display","block");//style.display="block";
};
setTimeout(function(){
	slide_icon1.mouseover(function(){
		$(this).css("display","block");
		slide_icon2.css("display","block");//style.display="block";
	})
},500)
setTimeout(function(){
	slide_icon2.mouseover(function(){
		$(this).css("display","block");
		slide_icon1.css("display","block");//style.display="block";
	})
},500)
$("#pic").mouseout=function(){
	slide_icon1.css("display","none");//style.display="none";
	slide_icon2.css("display","none");//style.display="none";
}

/*轮播点击事件*/
window.onload = function(){
	var arr = ['img/images/pic_1.jpg', 'img/images/pic_2.jpg', 'img/images/pic_3.jpg', 'img/images/pic_4.jpg', 'img/images/pic_5.jpg'];
	var index = 0;//定位第几张图片的变量

	function funTab() {
		document.getElementById("pic").src = arr[index];//给图片标签添加图片
	}
	funTab(); //第一次运行时 调用一次,进行初始化
	slide_icon1.onclick = prevImg;//当点击前一张按钮时,调用方法 显示前一张图片
	slide_icon2.onclick = nextImg;//当点击后一张按钮是,调用方法显示后一张图片

	function nextImg(){
		index++;//后一张,所以下标要增加1
		if(index > arr.length - 1) {//如果移到到数组的最大下标
			index = 0;//那么下标为0 . 这样就可以循环显示图片
		}
		funTab();//显示图片和搭配的文字信息
	}

	function prevImg(){
		index--;//前一张图片.所以下标要减1
		if(index < 0) {//如果下标小于 0
			index = arr.length - 1;//那么下标修改为最大下标  这样就可以循环显示图片
		}
		funTab();//显示图片和文字信息
	}
}

/*更多的下拉列表*/
// var secondMenu=document.getElementById("secondMenu");
// 	var secondMenu = $("#secondMenu");
// 	var more=document.getElementsByClassName("more")[0];
// 	more.onmouseover=function(){
// 		secondMenu.css("display","block");
// 	}
// 	more.onmouseout=function(){
// 		secondMenu.css("display","none");
// 	}
// 	setTimeout(function(){
// 		secondMenu.mouseover(function () {
// 			$(this).css("display","block");
// 		},500);
//
// 		secondMenu.mouseout(function () {
// 			clearTimeout();
// 			$(this).css("display","none");
// 		});


	/*新歌首发*/
	$("#tabTonLoadFistShow").css("color","#009af3");
	function abc(num,obj){
		//for循环  将当前的num显示
		for(var n=1;n<=4;n++){
			if(num == n-1){
				//显示
				$("#tabT"+n).css("display","block");
				$("#tabT"+n+" ul").each(function (j) {
					$(this).addClass("m"+(j+1));
				})
			}else {
				//隐藏
				$("#tabT"+n).css("display","none");
				$("#tabT"+n+" ul").each(function (j) {
					$(this).removeClass("m"+(j+1));
				})
			}
		}
		$(".tabT span").each(function () {
			$(this).css("color","#000");
		});
		$(obj).css("color","#009af3");

	}

	function ABC(num){
		for(let j=0;j<3;j++){
			if( j==num ){
				$("singers"+(j+1)).css("display","block");
				document.querySelectorAll(".itemTitle span")[j+1].style.color="#009af3";
				document.querySelectorAll(".icon_dot")[j].style.backgroundPosition="0 0";
			}else{
				$("singers"+(j+1)).css("display","none");
				document.querySelectorAll(".itemTitle span")[j+1].style.color="";
				document.querySelectorAll(".icon_dot")[j].style.backgroundPosition="0 -11px";
			}
		}
	}

	/*新歌首发第一页第二页*/
	var INDEX = 1;
	function showOrHide(which)
	{
		$('.m' + INDEX).css("display","none");
		switch (which)
		{
			case 1:
				INDEX--;
				INDEX = INDEX < 1 ? 3 : INDEX;
				$('.m' + INDEX).css("display","block");
				break;
			case 2:
				INDEX++;
				INDEX = INDEX > 3 ? 1 : INDEX;
				$('.m' + INDEX).css("display","block");
				break;
		}
		change();
	}

	function change(){
		$(".currentPage").html(INDEX+"/"+3);
	}


	/*下载歌曲*/
	var dialog=$("#dialog");//document.getElementById("dialog");
	var icon_download=$(".icon-download");//document.getElementsByClassName("icon-download")[0];
	var close=$(".close");//document.getElementsByClassName("close")[0];
	var close1=$(".close1");//document.getElementsByClassName("close1")[0];

	icon_download.click(function(){
		dialog.css("display","block");//style.display="block";
	});
	close.click(function(){
		dialog.css("display","none");//.style.display="none";
	});

// var login=document.getElementsByClassName("login")[0];
// login.onclick=function(){
// 	$("load").style.display="block";
// 	$("cover").style.display="block";
// }
// $("closebtn").onclick=function(){
// 	$("load").style.display="none";
// 	$("cover").style.display="none";
// }
