require.config({
    baseUrl: "/js",
    paths: {
        "jquery": "libs/jquery",
        "bootstrap": "libs/bootstrap",
        "slider": "modules/slider",
        "navbar": "modules/navbar",
        "datamain": "modules/datamain"
    }
});

require(['navbar', 'slider', 'datamain'], function () {

});