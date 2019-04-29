// JavaScript Document
//头部
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

//更多盒子
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

//大图
var dot=document.getElementsByClassName("dot")[0];
var dotli=dot.getElementsByTagName("li");

var BigImgs=document.getElementsByClassName("BigImgs")[0];
var z=1;

setInterval(function(){
	for(var i=0;i<dotli.length;i++){
		dotli[i].style.background="../MVIMg/doc_hover.png";
	}

	//单位换算
	if(BigImgs.style.left==""){
		x=0;
	}else{
		x=parseInt(BigImgs.style.left);
	}
	BigImgs.style.left=(x-668)+"px";
	z++;

	if(z==6){
		z=1;
		BigImgs.style.left=0+"px";
	}
},1000);

//内容 菜单部分
var dds=document.getElementsByTagName("dd");
for(let i=1;i<dds.length;i++){
	dds[i].onmouseover=function(){
		var as=this.getElementsByTagName("a")[0];
		this.style.background="#85d4f3";
		as.style.color="#fff";
	}
	dds[i].onmouseout=function(){
		var as=this.getElementsByTagName("a")[0];
		this.style.background="";
		as.style.color="#555";
	}
}


/*dott.style.background="../MVIMg/doc_hover.png";*/
