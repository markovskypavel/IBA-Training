define("dataadmin", ['jquery', 'transport'], function ($, transport) {

    //Append to section
    function overlayTextArtist(artist) {
        return '<div id="win">' +
                    '<div class="overlay"></div>' +
                    '<div class="visible">' +
                        '<pre>' +
                            '<u>Имя артиста</u></br>' + artist.name + '</br>' +
                            '<u>Жанр</u></br>' + artist.genre + '</br>' +
                        '</pre>' +
                    '</div>' +
                '</div>';
    }

    /* Вывод информации об артистах в модальное окно + анимация затемнения */
    $('input[name=moreArtist]').on("click", function () {
        const url = $(this).data("url");
        transport.getFetch(url, overlayTextArtist);
    });

    /* Переход на страницу редактирования */
    $('input[name=editArtist], input[name=editActivity]').on("click", function () {
        const url = $(this).data("url");
        transport.nextPage(url);
    });

    /* Удаление */
    $('input[name=deleteArtist], input[name=deleteActivity]').on("click", function () {
        const url = $(this).data("url");
        transport.postFetch(url);
    });


    /* Добавление артиста для события */
    $('input[name=addActivityArtist]').on("click", function () {
        const url = $(this).data("url");
        transport.getFetch(url, function (artists) {
            //let type can be used in each block and has individual value for each block
            var text = '';
            /*Add button with url*/
            artists.forEach(function (artist) {
                const dataUrl = url + '/' + artist.id + '/add';
                text += '<div><input type="button" name="addArtistActivity" value='+ artist.name + ' data-url=' + dataUrl + '></div>';
            });
            return '<div id="win">' +
                        '<div class="overlay"></div>' +
                        '<div class="visible">' +
                            '<pre>' +
                                text +
                            '</pre>' +
                        '</div>' +
                    '</div>';
        }).then(function () {
            /*After request we got Promise and we can link action to buttons(post to link url) */
            $('input[name=addArtistActivity]').on("click", function () {
                const url = $(this).data("url");
                transport.postFetch(url);
            });
        });

    });

    /* Удаление артиста для события */
    $('input[name=deleteActivityArtist]').on("click", function () {
        const url = $(this).data("url");
        transport.getFetch(url, function (artists) {
            //let type can be used in each block
            var text = '';
            /*Add button with url*/
            artists.forEach(function (artist) {
                const dataUrl = url + '/' + artist.id + '/remove';
                text += '<div><input type="button" name="removeArtistActivity" value='+ artist.name + ' data-url=' + dataUrl + '></div>';
            });
            return '<div id="win">' +
                        '<div class="overlay"></div>' +
                        '<div class="visible">' +
                            '<pre>' +
                                text +
                            '</pre>' +
                        '</div>' +
                    '</div>';
        }).then(function () {
            /*After request we got Promise and we can link action to buttons(post to link url) */
            $('input[name=removeArtistActivity]').on("click", function () {
                const url = $(this).data("url");
                transport.postFetch(url);
            });
        });
    });

});