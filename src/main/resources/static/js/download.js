// JavaScript Document

var a1=document.getElementsByClassName("a1")[0];
var cover=document.getElementById("cover");
var notice=document.getElementById("notice");
var closer=document.getElementsByClassName("close")[0];

a1.onclick=function(){
	cover.style.display="block";
	notice.style.display="block";
}
closer.onclick=function(){
	notice.style.display="none";
	cover.style.display="none";
}


/*头部*/
var dir_aa=document.getElementsByClassName("dir_aa");
for(let i=0;i<dir_aa.length;i++){
	dir_aa[i].onmouseover=function(){
		var aaA=this.getElementsByTagName("a")[0];
		aaA.style.color="#fff";
		this.style.background="#0c8ed9";
	}
	dir_aa[i].onmouseout=function(){
		var aaA=this.getElementsByTagName("a")[0];
		aaA.style.color="#bbb";
		this.style.background="";
	}
}

/*更多盒子*/
var dir_more=document.getElementsByClassName("dir_more")[0];
var dir_hind=document.getElementsByClassName("dir_hind")[0];

dir_more.onmouseover=function(){
	dir_hind.style.display="block";
}
dir_more.onmouseout=function(){
	dir_hind.style.display="none";
}
setTimeout(function(){
	dir_hind.onmouseover=function(){
		this.style.display="block";
	    }
	},500)

dir_hind.onmouseout=function(){
	clearTimeout();
	this.style.display="none";
}
