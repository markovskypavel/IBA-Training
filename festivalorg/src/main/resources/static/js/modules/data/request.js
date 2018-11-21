define("request", ['jquery'], function ($) {

    return {

        postFetch: function (url, callbackReload) {
            return fetch(url, {
                method: 'POST',
                credentials: 'include',
                headers: {
                    "Accept": 'application/json, text/plain',
                    "Content-type": "application/x-www-form-urlencoded; charset=UTF-8"
                }
            })
                .then(function (response) {
                    if (response.status === 404) {
                        location.href = $('#contextPathHolder').data("contextpath") + '404';
                    } else if (response.status === 500) {
                        location.href = $('#contextPathHolder').data("contextpath") + 'error';
                    } else if (response.status === 418) {
                        response.json().then(function (exp) {
                            alert(exp.message);
                        });
                    } else {
                        callbackReload();
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });
        },

        getFetch: function (url, callbackHTML) {
            return fetch(url, {
                method: 'GET',
                credentials: 'include',
                headers: {
                    "Accept": 'application/json'
                }
            })
                .then((resp) => resp.json())
                .then(function (data) {
                    $('section').append(callbackHTML(data));
                })
                .then(function () {
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
        },

        nextPage: function (url) {
            location.href = url;
        }

    };

});