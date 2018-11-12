package by.iba.markovsky.festival.controller;

import by.iba.markovsky.festival.constant.MappingConstant;
import by.iba.markovsky.festival.exception.LimitException;
import by.iba.markovsky.festival.exception.NotFoundException;
import by.iba.markovsky.festival.model.Activity;
import by.iba.markovsky.festival.model.Artist;
import by.iba.markovsky.festival.model.WebIdentity;
import by.iba.markovsky.festival.service.ActivityService;
import by.iba.markovsky.festival.service.ArtistService;
import by.iba.markovsky.festival.service.WebIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RestActivityController {

    @Autowired
    @Qualifier("activityService")
    private ActivityService activityService;

    @Autowired
    @Qualifier("artistService")
    private ArtistService artistService;

    @Autowired
    @Qualifier("webIdentityService")
    private WebIdentityService webIdentityService;

    //@RequestParam необходим для подстановки из UrlQuery
    //spring.mvc.pathmatch.use-suffix-pattern=true в properties для работы расширений
    @RequestMapping(value = MappingConstant.GET_ACTIVITY, method = RequestMethod.GET)
    public Activity findActivity(@PathVariable("id") int id,
                                 @RequestParam(value = "name", required = false, defaultValue = "") String name) throws NotFoundException {
        Activity activity = activityService.getActivityById(id);
        if (activity == null) {
            throw new NotFoundException();
        }
        return activity;
    }

    @RequestMapping(value = MappingConstant.DELETE_ACTIVITY, method = RequestMethod.POST)
    public void deleteActivity(@PathVariable("id") int id) throws NotFoundException {
        Activity activity = activityService.getActivityById(id);
        if (activity == null) {
            throw new NotFoundException();
        }
        activityService.deleteActivity(activity);
    }

    @RequestMapping(value = MappingConstant.ADD_ACTIVITY_ARTIST, method = RequestMethod.POST)
    public void addActivityArtist(@PathVariable("activityId") int activityId,
                                  @PathVariable("artistId") int artistId) throws NotFoundException {
        Activity activity = activityService.getActivityById(activityId);
        Artist artist = artistService.getArtistById(artistId);
        if (activity == null || artist == null) {
            throw new NotFoundException();
        }
        activityService.addArtist(activity, artist);
    }

    @RequestMapping(value = MappingConstant.REMOVE_ACTIVITY_ARTIST, method = RequestMethod.POST)
    public void removeActivityArtist(@PathVariable("activityId") int activityId,
                                     @PathVariable("artistId") int artistId) throws NotFoundException {
        Activity activity = activityService.getActivityById(activityId);
        Artist artist = artistService.getArtistById(artistId);
        if (activity == null || artist == null) {
            throw new NotFoundException();
        }
        activityService.removeArtist(activity, artist);
    }

    @RequestMapping(value = MappingConstant.SUBSCRIBE, method = RequestMethod.POST)
    public void subscribeUser(@PathVariable("activityId") int activityId,
                              @PathVariable("userId") int userId) throws LimitException, NotFoundException {
        Activity activity = activityService.getActivityById(activityId);
        WebIdentity user = webIdentityService.getUserById(userId);
        if (activity == null || user == null) {
            throw new NotFoundException();
        }
        activityService.subscribeUser(activity, user);
    }

    @RequestMapping(value = MappingConstant.UNSUBSCRIBE, method = RequestMethod.POST)
    public void unsubscribeUser(@PathVariable("activityId") int activityId,
                                @PathVariable("userId") int userId) throws NotFoundException {
        Activity activity = activityService.getActivityById(activityId);
        WebIdentity user = webIdentityService.getUserById(userId);
        if (activity == null || user == null) {
            throw new NotFoundException();
        }
        activityService.unsubscribeUser(activity, user);
    }

}
