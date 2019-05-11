// $(function () {
    // var secondMenu=document.getElementById("secondMenu");
// var more=document.getElementsByClassName("more")[0];
    var secondMenu = $("#secondMenu");
    var more = $(".more");
    more.mouseover(function () {
        secondMenu.attr(display, "block");
    });
    more.mouseout(function () {
        secondMenu.attr(display, "none");
    });
    setTimeout(function () {
        secondMenu.mouseover(function () {
            this.style.display = "block";
        })
    }, 500);

    secondMenu.mouseout(function () {
        clearTimeout();
        secondMenu.attr(display, "none");
    });
    var mobileShareQrcode = document.getElementsByClassName("mobileShareQrcode")[0];
    var mobileShare = document.getElementsByClassName(" mobileShare")[0];
    mobileShare.onmouseover = function () {
        mobileShareQrcode.style.display = "block";
    }
    mobileShare.onmouseout = function () {
        setTimeout(function () {
            mobileShareQrcode.style.display = "none";
        }, 300)
    }
    var dialog = document.getElementById("dialog");
    var downloadBtn = document.getElementsByClassName("downloadBtn")[0];
    var close = document.getElementsByClassName("close")[0];
    var close1 = document.getElementsByClassName("close1")[0];
    var shareBtn = document.getElementsByClassName("shareBtn")[0];
    var sharing = document.getElementById("sharing");
    downloadBtn.onclick = function () {
        dialog.style.display = "block";
    }
    close.onclick = function () {
        dialog.style.display = "none";


    }
    close1.onclick = function () {
        sharing.style.display = "none";


    }
    shareBtn.onclick = function () {
        sharing.style.display = "block";
    }
    var myPlayer = videojs('example_video_1');
    videojs("example_video_1").ready(function () {
        var myPlayer = this;
        myPlayer.play();
    });

// });