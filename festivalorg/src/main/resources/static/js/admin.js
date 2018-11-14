require.config({
    baseUrl: "/js",
    paths: {
        "jquery": "libs/jquery",
        "bootstrap": "libs/bootstrap",
        "navbar": "modules/navbar",
        "datamain": "modules/datamain",
        "dataadmin": "modules/dataadmin",
        "transport": "modules/transport"
    }
});

require(['navbar', 'dataadmin', 'datamain'], function () {

});