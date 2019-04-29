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

/*点击切换*/
var contents=document.querySelector(".contents");
var contents_checked=document.querySelector(".contents_checked");
var d1=document.getElementsByClassName("d1")[0];
var d2=document.getElementsByClassName("d2")[0];

function abc(){
	contents.style.display="block";
	contents_checked.style.display="none";
	d2.style.backgroundColor="#0c8ed9";
	d2.style.color="#fff";
	d1.style.backgroundColor="#f3f3f3";
	d1.style.color="black";
}
function ABC(){
	contents.style.display="";
	contents_checked.style.display="block";
	d1.style.backgroundColor="#0c8ed9";
	d1.style.color="#fff";
	d2.style.backgroundColor="#f3f3f3";
	d2.style.color="black";
}

/*内部点击切换*/
var a1=document.getElementById("a1");
var a2=document.getElementById("a2");
var a3=document.getElementById("a3");
var a4=document.getElementById("a4");
var extra1=document.getElementsByClassName("extra1")[
	0];
var macqq=document.getElementsByClassName("macqq")[0];

a1.onclick=function(){
	macqq.style.display="none";
	extra1.style.display="block";
	this.style.fontWeight="600";
	this.style.border="1px solid #80dbff";
	this.style.backgroundColor="#e6f8ff";
	a2.style.backgroundColor="#fff";
	a2.style.border="1px solid #666";
	a2.style.fontWeight="normal";
	a3.style.backgroundColor="#fff";
	a3.style.border="1px solid #666";
	a3.style.fontWeight="normal";
	a4.style.backgroundColor="#fff";
	a4.style.border="1px solid #666";
	a4.style.fontWeight="normal";
}
a2.onclick=function(){
	extra1.style.display="none";
	macqq.style.display="none";
	this.style.fontWeight="600";
	this.style.border="1px solid #80dbff";
	this.style.backgroundColor="#e6f8ff";
	a1.style.backgroundColor="#fff";
	a1.style.border="1px solid #666";
	a1.style.fontWeight="normal";
	a3.style.backgroundColor="#fff";
	a3.style.border="1px solid #666";
	a3.style.fontWeight="normal";
	a4.style.backgroundColor="#fff";
	a4.style.border="1px solid #666";
	a4.style.fontWeight="normal";
}
a3.onclick=function(){
	extra1.style.display="none";
	macqq.style.display="none";
	this.style.fontWeight="600";
	this.style.border="1px solid #80dbff";
	this.style.backgroundColor="#e6f8ff";
	a1.style.backgroundColor="#fff";
	a1.style.border="1px solid #666";
	a1.style.fontWeight="normal";
	a2.style.backgroundColor="#fff";
	a2.style.border="1px solid #666";
	a2.style.fontWeight="normal";
	a4.style.backgroundColor="#fff";
	a4.style.border="1px solid #666";
	a4.style.fontWeight="normal";
}
a4.onclick=function(){
	macqq.style.display="block";
	extra1.style.display="none";
	a4.style.fontWeight="600";
	a4.style.border="1px solid #80dbff";
	a4.style.backgroundColor="#e6f8ff";
	extra1.style.display="none";
	this.style.fontWeight="600";
	this.style.border="1px solid #80dbff";
	this.style.backgroundColor="#e6f8ff";
	a1.style.backgroundColor="#fff";
	a1.style.border="1px solid #666";
	a1.style.fontWeight="normal";
	a3.style.backgroundColor="#fff";
	a3.style.border="1px solid #666";
	a3.style.fontWeight="normal";
	a2.style.fontWeight="normal";
	a2.style.backgroundColor="#fff";
	a2.style.border="1px solid #666";
}


/*卸载方法隐藏图片*/
var hidepic=document.getElementsByClassName("hidepic")[0];
var showlog=document.getElementsByClassName("showlog")[0];
showlog.onmouseover=function(){
	hidepic.style.display="block";
}
showlog.onmouseout=function(){
	hidepic.style.display="none";
}
setTimeout(function(){
	hidepic.onmouseover=function(){
		this.style.display="block";
	    }
	},1000)

	hidepic.onmouseout=function(){
		clearTimeout();
		this.style.display="none";
	}
