// JavaScript Document
/*首页隐藏1*/
var mv1u=document.getElementsByClassName("mv1u")[0];
var spe2=document.getElementsByClassName("spe2")[0];
spe2.onmouseover=function(){
	mv1u.style.display="block";
}
spe2.onmouseout=function(){
	mv1u.style.display="none";
}
setTimeout(function(){
	mv1u.onmouseover=function(){
		this.style.display="block";
	    }
	},500)

mv1u.onmouseout=function(){
	clearTimeout();
	this.style.display="none";
}

/*首页隐藏2*/
var gh1=document.getElementsByClassName("gh1")[0];
var spe5=document.getElementsByClassName("spe5")[0];
spe5.onmouseover=function(){
	gh1.style.display="block";
}
spe5.onmouseout=function(){
	gh1.style.display="none";
}
setTimeout(function(){
	gh1.onmouseover=function(){
		this.style.display="block";
	    }
	},500)

gh1.onmouseout=function(){
	clearTimeout();
	this.style.display="none";
}
/*首页隐藏3*/
var nt1=document.getElementsByClassName("nt1")[0];
var spe7=document.getElementsByClassName("spe7")[0];
spe7.onmouseover=function(){
	nt1.style.display="block";
}
spe7.onmouseout=function(){
	nt1.style.display="none";
}
setTimeout(function(){
	nt1.onmouseover=function(){
		this.style.display="block";
	    }
	},500)

nt1.onmouseout=function(){
	clearTimeout();
	this.style.display="none";
}
/*首页隐藏4*/
var xz1=document.getElementsByClassName("xz1")[0];
var spe9=document.getElementsByClassName("spe9")[0];
spe9.onmouseover=function(){
	xz1.style.display="block";
}
spe9.onmouseout=function(){
	xz1.style.display="none";
}
setTimeout(function(){
	xz1.onmouseover=function(){
		this.style.display="block";
	    }
	},500)

xz1.onmouseout=function(){
	clearTimeout();
	this.style.display="none";
}
/*点击隐藏*/
function Show_Hidden(obj)
{
 if(obj.style.display=="block")
 {
  obj.style.display='none';
 document.getElementsByClassName("close")[0].style.display="none";
 }
}
window.onload=function()
{
 var olink=document.getElementsByClassName("close")[0];
 var odiv=document.getElementById("picbox")[0];
 olink.onclick=function()
 {
  Show_Hidden(odiv);
  return false;
 }
}

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
			pic.src="public/img/LiveMusic/pic_"+index+".jpg";
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

/*刷新*/


/*热门艺人隐藏1
var nt1=document.getElementsByClassName("nt1")[0];
var spe7=document.getElementsByClassName("spe7")[0];
spe7.onmouseover=function(){
	nt1.style.display="block";
}
spe7.onmouseout=function(){
	nt1.style.display="none";
}
setTimeout(function(){
	nt1.onmouseover=function(){
		this.style.display="block";
	    }
	},500)

nt1.onmouseout=function(){
	clearTimeout();
	this.style.display="none";
}*/



/*
var ps=document.querySelectorAll(".p1 p");
var divs=document.querySelectorAll(".p1 div");
divs.onmouseover=function(){
	ps.style.display="block";
	ps.style.marginTop="120px";
}
divs.onmouseout=function(){
	ps.style.display="none";
	ps.style.marginTop="150px";
}*/