package by.iba.markovsky.festivalorganisation.application.service;

import by.iba.markovsky.festivalorganisation.domain.entity.*;
import by.iba.markovsky.festivalorganisation.infrastructure.exception.LimitException;

import java.util.List;

public class AddActivityService {

    private Activity activity = null;

    public void addAdctivity(ActivityType activityType, String name, Place place, List<User> participants,
                                 List<Artist> artists, String description) throws LimitException {
        if (place.getCapacity() < participants.size()) {
            throw new LimitException("Capacity is less than participants quantity");
        }
        activity = new Activity(activityType, name, place, participants, artists, description);
    }
    public Activity getActivity() {
        return activity;
    }

}
