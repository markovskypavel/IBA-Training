define("navbar", ['jquery'], function ($) {

    /* Hover для верхнего меню */
    $('ul.top-menu').find('li').on({
        mouseover: function () {
            $(this).css("background", "#d4a06e");
            $(this).children(':first').css("color", "#e8ceb5");
        },
        mouseout: function () {
            $(this).css("background", "");
            $(this).children(':first').css("color", "");
        }
    });

});