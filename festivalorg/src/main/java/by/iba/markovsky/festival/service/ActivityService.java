package by.iba.markovsky.festival.service;

import by.iba.markovsky.festival.exception.LimitException;
import by.iba.markovsky.festival.exception.ServiceException;
import by.iba.markovsky.festival.model.Activity;
import by.iba.markovsky.festival.model.Artist;
import by.iba.markovsky.festival.model.WebIdentity;
import by.iba.markovsky.festival.model.enumeration.ActivityType;
import by.iba.markovsky.festival.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PrePersist;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("activityService")
@Transactional(rollbackFor = ServiceException.class)
/*rollback for unchecked exception (transaction cancelled)*/
public class ActivityService {

    @Value("${activity.service.artists.limit}")
    private int limitOfArtists;

    @Autowired
    @Qualifier("activityRepository")
    private ActivityRepository activityRepository;

    public void addOrUpdateAdctivity(Activity activity) {
        activityRepository.save(activity);
    }
    public void deleteActivity(Activity activity) {
        //For many-to-many delete we have to unlink the child's/parent's entities
        for(WebIdentity webIdentity : activity.getUsers()){
            webIdentity.getActivities().remove(activity);
        }
        for(Artist artist : activity.getArtists()){
            artist.getActivities().remove(activity);
        }
        activityRepository.delete(activity);
    }

    public void subscribeUser(Activity activity, WebIdentity webIdentity) throws LimitException {
        int capacity = Integer.valueOf(activity.getPlace().getCapacity());
        int size = activity.getUsers().size();
        if (capacity <= size) {
            throw new LimitException("Превышен лимит участников для места!");
        }
        activity.getUsers().add(webIdentity);
        activityRepository.save(activity);
    }
    public void unsubscribeUser(Activity activity, WebIdentity webIdentity) {
        activity.getUsers().remove(webIdentity);
        activityRepository.save(activity);
    }

    public void addArtist(Activity activity, Artist artist) throws LimitException {
        //Check if artist has more than 2 activities
        int amount = 0;
        for(Activity artistsActivity : artist.getActivities()){
            if(activity.getDate().equals(artistsActivity.getDate())){
                amount++;
            }
        }
        if (amount >= limitOfArtists) {
            throw new LimitException("Превышен лимит событий на день для артиста!");
        }
        activity.getArtists().add(artist);
        activityRepository.save(activity);
    }
    public void removeArtist(Activity activity, Artist artist) {
        //For many-to-many unlink we have to unlink the child's entity in parent entity
        activity.getArtists().remove(artist);
        activityRepository.save(activity);
    }

    public Activity getActivityById(int id) {
        Optional<Activity> activity = activityRepository.findById(id);
        return activity.orElse(null);
    }
    public Activity getActivityByName(String name) {
        Activity activity = activityRepository.findByName(name);
        return activity;
    }
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }
    public List<Activity> getAllActivitiesByUsername(String username) {
        return activityRepository.findAllActivitiesByUsers_username(username);
    }

}
