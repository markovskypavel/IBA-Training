require.config({
    baseUrl: "/js",
    paths: {
        "jquery": "libs/jquery",
        "slider": "modules/slider",
        "navbar": "modules/navbar",
        "datahome": "modules/data/datahome",
        "dataview": "modules/data/dataview",
        "request": "modules/data/request"
    }
});

require(['datahome', 'navbar', 'slider'], function (reload) {
    reload();
});