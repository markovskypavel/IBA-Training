require.config({
    baseUrl: "/js",
    paths: {
        "jquery": "libs/jquery",
        "navbar": "modules/navbar"
    }
});

require(['navbar'], function () {

});