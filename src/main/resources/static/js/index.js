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
	pic.src="img/images/pic_"+index+".jpg";
	
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

var headA=document.querySelectorAll("#head-dian li a");
var j=5;
function autoPlay(){
	headA[j-1].style.backgroundPosition="0 -22px";
	j++;
	if(j>=6){
		j=1;
	}
	headA[j-1].style.backgroundPosition="0 -37px";
}



/*左箭头右箭头*/
var slide_icon1=document.getElementsByClassName("slide_icon1")[0];
var slide_icon2=document.getElementsByClassName("slide_icon2")[0];
var n=0;
var onoff=true;
pic.onmouseover=function(){
	slide_icon1.style.display="block";
	slide_icon2.style.display="block";
}
setTimeout(function(){
	slide_icon1.onmouseover=function(){
		this.style.display="block";
		slide_icon2.style.display="block";
	    }
	},500)
setTimeout(function(){
	slide_icon2.onmouseover=function(){
		this.style.display="block";
		slide_icon1.style.display="block";
	    }
	},500)
pic.onmouseout=function(){
	slide_icon1.style.display="none";
	slide_icon2.style.display="none";
}

/*点击事件*/
window.onload = function(){
   var arr = ['img/images/pic_1.jpg', 'img/images/pic_2.jpg', 'img/images/pic_3.jpg', 'img/images/pic_4.jpg', 'img/images/pic_5.jpg'];
   var index = 0;//定位第几张图片的变量
	
   function funTab() {
       pic.src = arr[index];//给图片标签添加图片
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
		
/*跳转到最上面*/
window.onscroll=function(){
	if(document.documentElement.scrollTop+document.body.scrollTop>600){
		document.getElementById("scollTop").style.display="block";	
	}
	else{
		document.getElementById("scollTop").style.display="none";
	}
}
onscroll();


/*更多的下拉列表*/
var secondMenu=document.getElementById("secondMenu");
var more=document.getElementsByClassName("more")[0];
more.onmouseover=function(){
	secondMenu.style.display="block";
}
more.onmouseout=function(){
	secondMenu.style.display="none";
}
setTimeout(function(){
	secondMenu.onmouseover=function(){
		this.style.display="block";
	    }
	},500)

secondMenu.onmouseout=function(){
	clearTimeout();
	this.style.display="none";
}


/*新歌首发*/
function $(id){
	return document.getElementById(id);
}
function add(id){
	return document.getElementsByClassName(id)[0];
}
var tabTspan=document.querySelectorAll(".tabT span");
var tabT1=document.getElementById("tabT1");

var uls=document.querySelectorAll(".song_tab ul");
var tabTspans=document.querySelectorAll(".itemTitle span");
var dots=document.querySelectorAll(".icon_dot");
var next=document.querySelectorAll(".pages .next");

function abc(num){
	for(let i=0;i<4;i++){
		if( i==num ){
			$("tabT"+(i+1)).style.display="block";
			tabTspan[i].style.color="#009af3";
		}else{
			$("tabT"+(i+1)).style.display="none";
			tabTspan[i].style.color="";
		}
	}
	var tabT1 = document.getElementById("tabT1");
	var tabT2 = document.getElementById("tabT2");
	var tabT3 = document.getElementById("tabT3");
	var tabT4 = document.getElementById("tabT4");
	var tabTArr = [tabT1,tabT2,tabT3,tabT4];
	for(var i=0;i<tabTArr.length;i++){
		console.log(tabTArr[i])
		if(tabTArr[i].style.display == 'block'){
			var arr = tabTArr[i].getElementsByTagName("ul");
			for(var j=1;j<=arr.length;j++){
				arr[j-1].classList.add("m"+j);
			}
		}else {
			var arr = tabTArr[i].getElementsByTagName("ul");
			for(var j=1;j<=arr.length;j++){
				arr[j-1].classList.remove("m"+j);
			}
		}
	}
}

function ABC(num){
	for(let j=0;j<3;j++){
		if( j==num ){
			$("singers"+(j+1)).style.display="block";
			tabTspans[j+1].style.color="#009af3";
			dots[j].style.backgroundPosition="0 0";
		}else{
			$("singers"+(j+1)).style.display="none";
			tabTspans[j+1].style.color="";
			dots[j].style.backgroundPosition="0 -11px";
		}
	}
}

/*新歌首发第一页第二页*/
var INDEX = 1;
function showOrHide(which)
{
    add ('m' + INDEX).style.display = 'none';
	switch (which)
	{
		case 1:
			INDEX--;
			INDEX = INDEX < 1 ? 3 : INDEX;
			add ('m' + INDEX).style.display = 'block';
			break;
		case 2:
			INDEX++;
			INDEX = INDEX > 3 ? 1 : INDEX;
			add ('m' + INDEX).style.display = 'block';
			break;
	}
	change();
}

var currentPages=document.getElementsByClassName("currentPage")[0];
function change(){
	currentPages.innerHTML=INDEX+"/"+3;
}


/*下载歌曲*/
var dialog=document.getElementById("dialog");	
var icon_download=document.getElementsByClassName("icon-download")[0];
var close=document.getElementsByClassName("close")[0];
var close1=document.getElementsByClassName("close1")[0];
	
icon_download.onclick=function(){
   dialog.style.display="block";
}
close.onclick=function(){
   dialog.style.display="none";	
}

var login=document.getElementsByClassName("login")[0];
login.onclick=function(){
	$("load").style.display="block";
	$("cover").style.display="block";
}
// $("closebtn").onclick=function(){
// 	$("load").style.display="none";
// 	$("cover").style.display="none";
// }