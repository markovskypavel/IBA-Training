require.config({
    baseUrl: "/js",
    paths: {
        "jquery": "libs/jquery",
        "bootstrap": "libs/bootstrap",
        "navbar": "modules/navbar",
        "datamain": "modules/data/datahome",
        "transport": "modules/data/request"
    }
});

require(['navbar', 'datamain'], function () {

});