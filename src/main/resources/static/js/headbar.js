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

// //
// $(".more").mouseenter(function(){
//     $("#secondMenu").css("display","block");
// })
// $(".more").mouseleave(function(){
//     $("#secondMenu").css("display","none");
// })
//
// var timer=setTimeout(function(){
//     $("#secondMenu").mouseenter(function(){
//         $(".more").css("background","#0c8ed9");
//         $(this).css("display","block");
//     });
// },500);
//
// $("#secondMenu").mouseleave(function(){
//     clearTimeout(timer);
//     $(".more").css("background","");
//     $(this).css("display","none");
// });
//
// $(".homeNav>li").click(function(){
//     $(this).addClass("active");
//     $(this).siblings(".homeNav>li").removeClass("active");
// });
console.log($("#secondMenu"))

// $(".login_btn").click(function(){
//
// });
//
// $("#closebtn").click(function(){
//     $("#load").css("display","none");
//     $("#cover").css("display","none");
// });
// $("#fmLoginBtn").click(function(){
//     $("#load").css("display","block");
//     $("#cover").css("display","block");
// })