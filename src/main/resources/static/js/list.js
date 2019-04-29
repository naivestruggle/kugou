// JavaScript Document
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
/*热门榜单点击隐藏事件*/
function Show_Hidden(obj)
{
 if(obj.style.display=="block")
 {
  obj.style.display='none';
  document.getElementsByClassName("arrow")[0].style.backgroundPosition="-480px -120px";
	 
 }
 else
 {
  obj.style.display='block';
	 document.getElementsByClassName("arrow")[0].style.backgroundPosition="-450px -120px";
 }
	
}
window.onload=function()
{
 var olink=document.getElementById("linkk");
 var odiv=document.getElementById("btnshow");
 olink.onclick=function()
 {
  Show_Hidden(odiv);
  return false;
 }
}


/*全选*/
var flag=true;
function allcheck(){
	var chks=document.getElementsByClassName("musics")[0].getElementsByClassName("checkbox");
	for(var i=0;i<chks.length;i++){
		if(flag){
			chks[i].style.backgroundPosition="-180px -150px";
			document.getElementsByClassName("checkbox")[0].style.backgroundPosition="-180px -150px";
		}else{
			chks[i].style.backgroundPosition="-180px -180px";
			document.getElementsByClassName("checkbox")[0].style.backgroundPosition="-180px -180px";
		}
		
		chks[i].onclick=function(){
			if(flag){
				this.style.backgroundPosition="-180px -150px";
				document.getElementsByClassName("checkbox")[0].style.backgroundPosition="-180px -150px";
			}else{
				this.style.backgroundPosition="-180px -180px";
			}
		}
	}
	flag=!flag;
}
/*播放
function $(id){
	return document.getElementById(id);
}
var tabTspan=document.getElementsByClassName(".tabT span");
var tabT1=document.getElementById("tabT1");
for(let i=0;i<4;i++){     
		tabTspan[i].onmouseover=function(){  
			$("tabT"+i).style.display="block";	
		}
}*/