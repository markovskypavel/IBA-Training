require.config({
    baseUrl: "/js",
    paths: {
        "jquery": "libs/jquery",
        "bootstrap": "libs/bootstrap",
        "navbar": "modules/navbar",
        "datamain": "modules/datamain"
    }
});

require(['navbar', 'datamain'], function () {

});