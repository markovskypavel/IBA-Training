require.config({
    baseUrl: "/js",
    paths: {
        "jquery": "libs/jquery",
        "navbar": "modules/navbar",
        "dataadmin": "modules/data/dataadmin",
        "dataview": "modules/data/dataview",
        "request": "modules/data/request"
    }
});

require(['dataadmin', 'navbar'], function (reload) {
    reload();
});