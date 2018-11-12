require.config({
    baseUrl: "/js",
    paths: {
        "jquery": "libs/jquery",
        "bootstrap": "libs/bootstrap",
        "slider": "modules/slider",
        "navbar": "modules/navbar",
        "articles": "modules/articles",
        "data": "modules/data"
    }
});

require(['navbar', 'slider', 'articles', 'data'], function () {

});