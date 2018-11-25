require.config({
    baseUrl: "/js",
    paths: {
        "jquery": "libs/jquery",
        "navbar": "modules/navbar",
        "dropdown": "modules/dropdown",
        "dataadmin": "modules/data/dataadmin",
        "dataview": "modules/data/dataview",
        "request": "modules/data/request"
    }
});

require(['dataadmin', 'navbar', 'dropdown'], function (reload) {
    reload();
});