define("data", ['jquery'], function ($) {

    /* Вывод информации о фестивалях в модальное окно + анимация затемнения */
    $('input[name=festival]').on("click", function () {
        const url = $(this).data("url");
        fetch(url)
            .then((resp) => resp.json())
            .then(function (data) {
                $('section').append('<div id="win">' +
                    '<div class="overlay"></div>' +
                    '<div class="visible"><pre>' + JSON.stringify(data, undefined, 2) + '</pre></div>' +
                    '</div>');
                $('.visible').bind("click", function () {
                    $('#win').animate({opacity: '0'}, 600, function () {
                        $('#win').remove();
                    });
                });
                $('#win').animate({opacity: '1'}, 600);
            })
            .catch(function (error) {
                console.log(error);
            });
    });

    /* Вывод информации о фестивалях в модальное окно + анимация затемнения */
    /*    $('input[name=festival]').on("click", function () {
            const url = '';
            fetch(url)
                .then((resp) => resp.json())
                .then(function (data) {
                    let authors = data.results;
                    return authors.map(function (author) {
                        let li = createNode('li'),
                            img = createNode('img'),
                            span = createNode('span');
                        img.src = author.picture.medium;
                        span.innerHTML = '${author.name.first} ${author.name.last}';
                        append(li, img);
                        append(li, span);
                        append(ul, li);
                    })
                })
                .catch(function (error) {
                    console.log(error);
                });
        });*/

});