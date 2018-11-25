define("dataadmin", ['jquery', 'request', 'dataview'], function ($, request, dataview) {

    return function loadAllAdmin() {
        $('#admin')
            .load($('#contextPathHolder').data("contextpath") + 'load/admin', function () {
                /* Dropdown */
                $('input[name=dropbtnartist], input[name=dropbtnactivity]').on("click", function () {
                    $(this).next().toggleClass("show");
                });

                /* Вывод информации о событиях в модальное окно + анимация затемнения */
                $('input[name=more]').on("click", function () {
                    const url = $(this).data("url");
                    request.getFetch(url, dataview.showActivity);
                });

                /* Подписка/отписка пользователя */
                $('input[name=subscribe], input[name=unsubscribe]').on("click", function () {
                    const url = $(this).data("url");
                    request.postFetch(url, loadAllAdmin);
                });

                /* Вывод информации об артистах в модальное окно + анимация затемнения */
                $('input[name=moreArtist]').on("click", function () {
                    const url = $(this).data("url");
                    request.getFetch(url, dataview.showArtist);
                });

                /* Переход на страницу редактирования */
                $('input[name=editArtist], input[name=editActivity]').on("click", function () {
                    const url = $(this).data("url");
                    request.nextPage(url);
                });

                /* Удаление */
                $('input[name=deleteArtist], input[name=deleteActivity]').on("click", function () {
                    const url = $(this).data("url");
                    request.postFetch(url, loadAllAdmin);
                });

                /* Добавление артиста для события */
                $('input[name=addActivityArtist]').on("click", function () {
                    const url = $(this).data("url");
                    request.getFetch(url, function (artists) {
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
                    })
                        .then(function () {
                            /*After request we got Promise and we can link action to buttons(post to link url) */
                            $('input[name=addArtistActivity]').on("click", function () {
                                const url = $(this).data("url");
                                request.postFetch(url, loadAllAdmin);
                            });
                        });
                });

                /* Удаление артиста для события */
                $('input[name=deleteActivityArtist]').on("click", function () {
                    const url = $(this).data("url");
                    request.getFetch(url, function (artists) {
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
                    })
                        .then(function () {
                            /*After request we got Promise and we can link action to buttons(post to link url) */
                            $('input[name=removeArtistActivity]').on("click", function () {
                                const url = $(this).data("url");
                                request.postFetch(url, loadAllAdmin);
                            });
                        });
                });

            });
    };

});