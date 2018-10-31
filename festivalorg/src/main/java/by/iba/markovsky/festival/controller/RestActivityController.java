package by.iba.markovsky.festival.controller;

import by.iba.markovsky.festival.exception.NotFoundException;
import by.iba.markovsky.festival.model.Activity;
import by.iba.markovsky.festival.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestActivityController {

    @Autowired
    private ActivityService activityService;

    //@RequestParam необходим для подстановки из UrlQuery
    @RequestMapping(value = "/activity/activities/{id}")
    public Activity findId(@PathVariable("id") int id,
                           @RequestParam(value="name", required=false, defaultValue="") String name) throws NotFoundException {
        Activity activity = activityService.getActivityById(id);
        if (activity == null) {
            throw new NotFoundException();
        }
        return activity;
    }

}
