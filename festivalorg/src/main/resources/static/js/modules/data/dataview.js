define("dataview", [], function () {

    //Get artists as string
    function getArtists(artists) {
        var artistInfo = '';
        artists.forEach(function (artist) {
            artistInfo += '- ' + artist.name + ' (' + artist.genre + ') -</br>';
        });
        return artistInfo;
    }

    return {

        showArtist: function (artist) {
            return '<div id="win">' +
                        '<div class="overlay"></div>' +
                        '<div class="visible">' +
                            '<pre>' +
                                '<u>Имя артиста</u></br>' + artist.name + '</br>' +
                                '<u>Жанр</u></br>' + artist.genre + '</br>' +
                            '</pre>' +
                        '</div>' +
                    '</div>';
        },

        showActivity: function (activity) {
            return '<div id="win">' +
                        '<div class="overlay"></div>' +
                        '<div class="visible">' +
                            '<pre>' +
                                '<u>Краткое описание</u></br>' + activity.description + '</br>' +
                                '<u>Дата события</u></br>' + activity.date.substring(0, 10) + '</br>' +
                                /*'<u>Время события</u></br>' + data.date.substring(11, 16) + '</br>' +*/
                                '<u>Место события</u></br>' + activity.place.address + '</br>' +
                                '<u>Количество участников</u></br>' + Object.keys(activity.users).length + '</br>' +
                                '<u>Выступаемые артисты</u></br>' + getArtists(activity.artists) +
                            '</pre>' +
                        '</div>' +
                    '</div>';
        },

        showArtistAddButtons: function (artists, url) {
            //let type can be used in each block and has individual value for each block
            var text = '';
            /*Add button with url*/
            artists.forEach(function (artist) {
                const dataUrl = url + '/' + artist.id + '/add';
                text += '<div><input type="button" name="addArtistActivity" value=' + artist.name + ' data-url=' + dataUrl + '></div>';
            });
            return '<div id="win">' +
                        '<div class="overlay"></div>' +
                        '<div class="visible">' +
                            '<pre>' +
                                text +
                            '</pre>' +
                        '</div>' +
                    '</div>';
        },

        showArtistRemoveButtons: function (artists, url) {
            //let type can be used in each block and has individual value for each block
            var text = '';
            /*Add button with url*/
            artists.forEach(function (artist) {
                const dataUrl = url + '/' + artist.id + '/remove';
                text += '<div><input type="button" name="removeArtistActivity" value=' + artist.name + ' data-url=' + dataUrl + '></div>';
            });
            return '<div id="win">' +
                        '<div class="overlay"></div>' +
                        '<div class="visible">' +
                            '<pre>' +
                                text +
                            '</pre>' +
                        '</div>' +
                    '</div>';
        }

    };

});