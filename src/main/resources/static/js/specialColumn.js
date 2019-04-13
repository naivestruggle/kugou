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