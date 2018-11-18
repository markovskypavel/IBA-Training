define("loaddata", ['jquery', 'transport'], function ($, transport) {

    fetch('/activities', {
        method: 'GET',
        credentials: 'include',
        headers: {
            "Accept": 'application/json'
        }
    })
        .then((resp) => resp.json())
        .then(function (activities) {
            activities.forEach(function (activity) {
                $('section').append(callbackHTML(activity));
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
        })
        .catch(function (error) {
            console.log(error);
        });

    function callbackHTML(activity) {
        return '<div class="activity">' +
            '<h2><u>'+ activity.activityType + '</u>'+activity.name+'</h2>' +
            '<p><u>Дата проведения</u>: ' + activity.date.split('T')[0] + '</p>' +
            '<p><u>Место</u>: ' + activity.place.address + '</p>' +
            '<div class="activityWork">' +
            '<input type="button" name="more" value="Подробнее" data-url=/activities/' + activity.id  +'>' +
            '</div>' +
            '</div>';
    }

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

});