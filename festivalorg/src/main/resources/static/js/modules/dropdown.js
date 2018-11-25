define("dropdown", ['jquery'], function ($) {

    $(window).click(function (event) {
        var target = $(event.target);
        if(!target.is('.dropbtn')){
            $('.dropdown-content').removeClass("show");
        }
    });

});