define("articles", ['jquery'], function ($) {

    /* Удаление выделения */
    function deleteClasses() {
        $('.aside-menu').find('li').removeClass("active");
        $('.aside-menu').find('a').removeClass("active");
    }

    /* Загрузка данных из html/txt и помещение данных в блок + выделение */
    $('#tuning').on('click', function () {
        $.ajax({
            type: "GET",
            url: document.getElementById("contextPathHolder").getAttribute('data-contextPath') + "/data/tuning.html",
            dataType: "text",
            success: function (data) {
                deleteClasses();
                $('#tuning').addClass("active");
                $('#tuning').parent().addClass("active");
                $('.article').html(data);
            },
            error: function () {
                alert("Возникла неожиданная ошибка получения данных!");
            }
        });
    });

    $('#technique').on('click', function () {
        $.ajax({
            type: "GET",
            url: document.getElementById("contextPathHolder").getAttribute('data-contextPath') + "/data/technique.html",
            dataType: "html",
            success: function (data) {
                deleteClasses();
                $('#technique').addClass("active");
                $('#technique').parent().addClass("active");
                $('.article').html(data);
            },
            error: function () {
                alert("Возникла неожиданная ошибка получения данных!");
            }
        });
    });

    $('#exterior').on('click', function () {
        $.ajax({
            type: "GET",
            url: document.getElementById("contextPathHolder").getAttribute('data-contextPath') + "/data/exterior.html",
            dataType: "html",
            success: function (data) {
                deleteClasses();
                $('#exterior').addClass("active");
                $('#exterior').parent().addClass("active");
                $('.article').html(data);
            },
            error: function () {
                alert("Возникла неожиданная ошибка получения данных!");
            }
        });
    });

    $('#interior').on('click', function () {
        $.ajax({
            type: "GET",
            url: document.getElementById("contextPathHolder").getAttribute('data-contextPath') + "/data/interior.html",
            dataType: "html",
            success: function (data) {
                deleteClasses();
                $('#interior').addClass("active");
                $('#interior').parent().addClass("active");
                $('.article').html(data);
            },
            error: function () {
                alert("Возникла неожиданная ошибка получения данных!");
            }
        });
    });

    $('#address').on('click', function () {
        $.ajax({
            type: "GET",
            url: document.getElementById("contextPathHolder").getAttribute('data-contextPath') + "/data/address.html",
            dataType: "html",
            success: function (data) {
                deleteClasses();
                $('#address').addClass("active");
                $('#address').parent().addClass("active");
                $('.article').html(data);
            },
            error: function () {
                alert("Возникла неожиданная ошибка получения данных!");
            }
        });
    });

});