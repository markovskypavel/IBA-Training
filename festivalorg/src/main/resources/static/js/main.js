require.config({
    baseUrl: "/js",
    paths: {
        "jquery": "libs/jquery",
        "bootstrap": "libs/bootstrap",
        "slider": "modules/slider",
        "navbar": "modules/navbar",
        "datamain": "modules/datamain",
        "transport": "modules/transport"
    }
});

require(['navbar', 'slider', 'datamain'], function () {

});