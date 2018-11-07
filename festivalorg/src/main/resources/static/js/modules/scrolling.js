define("scrolling", ['jquery'], function ($) {

    /* Плавный переход к ссылке */
    $('.aside-menu a').click(function () {
        var elementClick = $(this).attr("href");
        var destination = $(elementClick).offset().top - 100;
        $('html, body').animate({scrollTop: destination}, 1100);
        return false;
    });

});
