package by.iba.markovsky.festivalorganisation.application.service;

import by.iba.markovsky.festivalorganisation.domain.entity.*;
import by.iba.markovsky.festivalorganisation.infrastructure.exception.LimitException;

import java.util.List;

public class AddActivityService {

    public Activity addAdctivity(ActivityType activityType, String name, Place place, List<User> participants, List<Artist> artists, String description){
        try {
            Activity activity = new Activity(activityType, name, place, participants, artists, description);
            if (activity.getPlace().getCapacity() < activity.getParticipants().size()) {
                throw new LimitException("Capacity is less than participants quantity");
            }
            return activity;
        }catch(LimitException le){
            System.out.println(le.getMessage());
        }
        return null;
    }

}
