require.config({
    baseUrl: "/js",
    paths: {
        "jquery": "libs/jquery",
        "navbar": "modules/navbar",
        "datauser": "modules/data/datauser",
        "dataview": "modules/data/dataview",
        "request": "modules/data/request"
    }
});

require(['datauser', 'navbar'], function (reload) {
    reload();
});