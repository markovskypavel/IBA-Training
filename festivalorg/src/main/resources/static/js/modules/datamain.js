define("datamain", ['jquery', 'transport'], function ($, transport) {

    //Get artists as string
    function getArtists(artists) {
        var artistInfo = '';
        artists.forEach(function (artist) {
            artistInfo += '- ' + artist.name + ' (' + artist.genre + ') -</br>';
        });
        return artistInfo;
    }

    //Append to section
    function overlayText(data) {
        return '<div id="win">' +
                '<div class="overlay"></div>' +
                '<div class="visible">' +
                    '<pre>' +
                        '<u>Краткое описание</u></br>' + data.description + '</br>' +
                        '<u>Дата события</u></br>' + data.date.substring(0, 10) + '</br>' +
                        /*'<u>Время события</u></br>' + data.date.substring(11, 16) + '</br>' +*/
                        '<u>Место события</u></br>' + data.place.address + '</br>' +
                        '<u>Количество участников</u></br>' + Object.keys(data.users).length + '</br>' +
                        '<u>Выступаемые артисты</u></br>' + getArtists(data.artists) +
                    '</pre>' +
                '</div>' +
            '</div>';
    }

    /* Вывод информации о событиях в модальное окно + анимация затемнения */
    $('input[name=more]').on("click", function () {
        const url = $(this).data("url");
        transport.getFetch(url, overlayText);
    });

    /* Подписка/отписка пользователя */
    $('input[name=subscribe], input[name=unsubscribe]').on("click", function () {
        const url = $(this).data("url");
        transport.postFetch(url);
    });

});