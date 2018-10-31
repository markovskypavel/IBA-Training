package by.iba.markovsky.festival.service;

import by.iba.markovsky.festival.exception.LimitException;
import by.iba.markovsky.festival.exception.ServiceException;
import by.iba.markovsky.festival.model.*;
import by.iba.markovsky.festival.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("activityService")
@Transactional(rollbackFor = ServiceException.class)
public class ActivityService {

/*    @Value("${generator.service.strings.count}")
    private int stringsCount;*/

    @Autowired
    @Qualifier("activityRepository")
    private ActivityRepository activityRepository;
    private Activity activity = new Activity();

    public void addAdctivityToDB() throws LimitException {
        if (activity.getPlace().getCapacity() < activity.getUsers().size()) {
            throw new LimitException("Capacity is less than participants quantity");
        }
        activityRepository.save(activity);
    }

    public void addParticipant(String name, String surname, int age,
                               String username, String password, String email, String telephone, boolean status) {
        Identity identity = new Identity(name, surname, age);
        WebIdentity webIdentity = new WebIdentity(username, password, email, telephone, status, identity);
        activity.getUsers().add(webIdentity);
    }
    public void addArtist(String name, String genre) {
        Artist artist = new Artist(name, genre);
        activity.getArtists().add(artist);
    }
    public void addPlace(String address, int capacity) {
        Place place = new Place(address, capacity);
        activity.setPlace(place);
    }
    public void addActivityInfo(ActivityType activityType, String name, String description, Date date) {
        activity.setActivityType(activityType);
        activity.setName(name);
        activity.setDescription(description);
        activity.setDate(date);
    }

    public void deleteActivity(int id) {
        Optional<Activity> activity = activityRepository.findById(id);
        //TODO:Проверку сделать
        activityRepository.delete(activity.get());
    }

    public Activity getActivity() {
        return activity;
    }
    public Activity getActivityById(int id) {
        Optional<Activity> activity = activityRepository.findById(id);
        return activity.orElse(null);
    }
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

}
