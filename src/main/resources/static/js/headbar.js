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

function $(id){
    return document.getElementById(id);
}

function abc(num){
    for(var i=0;i<8;i++){
        if( i==num ){
            $("rightRadioList_"+(i+1)).style.display="block";

        }else{
            $("rightRadioList_"+(i+1)).style.display="none";

        }
    }
}