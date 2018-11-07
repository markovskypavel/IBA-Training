require.config({
    baseUrl: "/js",
    paths: {
        "jquery": "libs/jquery",
        "bootstrap": "libs/bootstrap",
        "slider": "modules/slider",
        "navbar": "modules/navbar",
        "articles": "modules/articles"
    }
});

require(['navbar', 'slider', 'articles'], function () {

});