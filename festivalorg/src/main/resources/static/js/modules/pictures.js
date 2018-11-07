define("pictures", ['jquery'], function ($) {

    /* Вывод картинок в модальное окно + анимация затемнения */
    $('.gallery img').on("click", function () {
        $('.gallery').append('<div id="win"><div class="overlay"></div><div class="visible"></div></div>');
        $('.visible').css("background-image", "url(" + $(this).attr('src') + ")");
        $('.visible').bind("click", function () {
            $('#win').animate({opacity: '0'}, 600, function () {
                $('#win').remove();
            });
        });
        $('#win').animate({opacity: '1'}, 600);
    });

    $('.news-pictures img').on("click", function () {
        $('section').append('<div id="win"><div class="overlay"></div><div class="visible"></div></div>');
        $('.visible').css("background-image", "url(" + $(this).attr('src') + ")");
        $('.visible').bind("click", function () {
            $('#win').animate({opacity: '0'}, 600, function () {
                $('#win').remove();
            });
        });
        $('#win').animate({opacity: '1'}, 600);
    });

});