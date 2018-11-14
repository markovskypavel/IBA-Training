package by.iba.markovsky.festival.constant;

public interface MappingConstant {
    /*    localhost:(Port number) /project name/(request mapping at controller) /(request mapping at method)*/
    String HOME = "/";
    String ABOUT_US = "/about";
    String ADMIN = "/admin";
    String USER = "/user";

    //Activity routing
    String ADD_ACTIVITY = "/activities/add";
    String EDIT_ACTIVITY = "/activities/{id}/edit";
    //Activity RESTful routing
    String DELETE_ACTIVITY = "/activities/{id}/delete";
    String GET_ACTIVITY = "/activities/{id}";
    String ADD_ACTIVITY_ARTIST = "/activities/{activityId}/artists/{artistId}/add";
    String REMOVE_ACTIVITY_ARTIST = "/activities/{activityId}/artists/{artistId}/remove";
    String SUBSCRIBE = "/activities/{activityId}/users/{username}/subscribe";
    String UNSUBSCRIBE = "/activities/{activityId}/users/{username}/unsubscribe";

    //Artist routing
    String ADD_ARTIST = "/artists/add";
    String EDIT_ARTIST = "/artists/{id}/edit";
    //Artist RESTful routing
    String DELETE_ARTIST = "/artists/{id}/delete";
    String GET_ARTIST = "/artists/{id}";
    String GET_UNUSED_ARTISTS = "/artists/{activityId}/unused";
    String GET_USED_ARTISTS = "/artists/{activityId}/used";

    //Authentication routing
    String LOGIN = "/login";
    String REGISTRATION = "/users/registration";
    String LOGOUT = "/logout";

    //Error routing
    String DENIED = "/403";
    String NOT_FOUND = "/404";
    String ERROR = "/error";
    String ERROR_QUERY = "?error=true";
}
