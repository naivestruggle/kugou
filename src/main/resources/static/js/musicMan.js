var chuangzuo=document.getElementsByClassName("chuangzuo")[0];
	var cover=document.getElementById("cover");
	var notice=document.getElementById("notice");
	var close=document.getElementsByClassName("close")[0];
	chuangzuo.onclick=function(){
		cover.style.display="block";
		notice.style.display="block";
	}
	close.onclick=function(){
		notice.style.display="none";
		cover.style.display="none";
	}


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
	var tabTspan=document.getElementsByClassName(".tabT span");
	var tabT1=document.getElementById("tabT1");
	for(let i=0;i<4;i++){
			tabTspan[i].onmouseover=function(){
				$("tabT"+i).style.display="block";
			}
	}
