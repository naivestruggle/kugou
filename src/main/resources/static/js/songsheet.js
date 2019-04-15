
$(function () {
    $(".tab>li").click(function () {
        $(this).addClass("active").siblings("li").removeClass("active");
        var index=$(this).index();
        $(".fr>div:eq("+index+")").addClass("selected").siblings("div").removeClass("selected");
    })
});

