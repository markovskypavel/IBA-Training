package by.iba.markovsky.festival.controller;

import by.iba.markovsky.festival.exception.NotFoundException;
import by.iba.markovsky.festival.model.Activity;
import by.iba.markovsky.festival.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestActivityController {

    @Autowired
    @Qualifier("activityService")
    private ActivityService activityService;

    //TODO: Разобраться почему не работают расширения
    //@RequestParam необходим для подстановки из UrlQuery
    @RequestMapping(value = "/festivals/festival/{id}")
    public Activity findFestival(@PathVariable("id") int id,
                           @RequestParam(value="name", required=false, defaultValue="") String name) throws NotFoundException {
        Activity activity = activityService.getActivityById(id);
        if (activity == null) {
            throw new NotFoundException();
        }
        return activity;
    }

}
