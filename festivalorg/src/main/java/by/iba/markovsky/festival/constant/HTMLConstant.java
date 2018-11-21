package by.iba.markovsky.festival.constant;

public interface HTMLConstant {
    String HOME_PAGE = "home";
    String ABOUT_US_PAGE = "about";
    String ADMIN_PAGE = "admin";
    String USER_PAGE = "user";

    //Authentication pages
    String LOGIN_PAGE = "authenticationpages/login";
    String REGISTRATION_PAGE = "authenticationpages/registration";

    //Activity pages
    String ACTIVITY_PAGE = "editpages/activity";
    String ACTIVITY_PAGE_EDIT = "editpages/activityedit";

    //Artist pages
    String ARTIST_PAGE = "editpages/artist";
    String ARTIST_PAGE_EDIT = "editpages/artistedit";

    //Error pages
    String DENIED_PAGE = "errorpages/403page";
    String NOT_FOUND_PAGE = "errorpages/404page";
    String ERROR_PAGE = "error";

    //Load fragments
    String HOME_FRAGMENT = "fragments/load/homeload :: homeload";
    String ADMIN_FRAGMENT = "fragments/load/adminload :: adminload";
    String USER_FRAGMENT = "fragments/load/userload :: userload";
}
