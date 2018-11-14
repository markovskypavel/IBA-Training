define("datamain", ['jquery'], function ($) {

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
        fetch(url, {
            method: 'GET',
            credentials: 'include',
            headers: {
                "Accept": 'application/json'
            }
        })
            .then((resp) => resp.json())
            .then(function (data) {
                $('section').append(overlayText(data));
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

    /* Подписка */
    $('input[name=subscribe]').on("click", function () {
        const url = $(this).data("url");
        fetch(url, {
            method: 'POST',
            credentials: 'include',
            headers: {
                "Accept": 'application/json, text/plain',
                "Content-type": "application/x-www-form-urlencoded; charset=UTF-8"
            }
        })
            .then(function (response) {
                if(response.status === 404){
                    location.href = $('#contextPathHolder').data("contextpath") + '404';
                } else {
                    location.reload();
                }
            })
            .catch(function (error) {
                console.log(error);
            });
    });

    /* Отписка пользователя */
    $('input[name=unsubscribe]').on("click", function () {
        const url = $(this).data("url");
        fetch(url, {
            method: 'POST',
            credentials: 'include',
            headers: {
                "Accept": 'application/json, text/plain',
                "Content-type": "application/x-www-form-urlencoded; charset=UTF-8"
            }
        })
            .then(function (response) {
                if(response.status === 404){
                    location.href = $('#contextPathHolder').data("contextpath") + '404';
                } else {
                    location.reload();
                }
        })
            .catch(function (error) {
                console.log(error);
            });
    });

});