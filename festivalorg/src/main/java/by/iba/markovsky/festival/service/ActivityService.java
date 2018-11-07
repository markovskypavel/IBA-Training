package by.iba.markovsky.festival.service;

import by.iba.markovsky.festival.exception.LimitException;
import by.iba.markovsky.festival.exception.ServiceException;
import by.iba.markovsky.festival.model.*;
import by.iba.markovsky.festival.model.enumeration.ActivityType;
import by.iba.markovsky.festival.repository.ActivityRepository;
import by.iba.markovsky.festival.repository.WebIdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("activityService")
/*rollback for unchecked exception (transaction cancelled)*/
@Transactional(rollbackFor = ServiceException.class)
public class ActivityService {

/*    @Value("${generator.service.strings.count}")
    private int stringsCount;*/

    @Autowired
    @Qualifier("activityRepository")
    private ActivityRepository activityRepository;

    public void addAdctivity(Activity activity) throws LimitException {
        if (activity.getPlace().getCapacity() < activity.getUsers().size()) {
            throw new LimitException("Capacity is less than participants quantity");
        }
        activityRepository.save(activity);
    }
    public void deleteActivity(int id) {
        Optional<Activity> activity = activityRepository.findById(id);
        activity.ifPresent(activityToDelete -> activityRepository.delete(activityToDelete));
    }
    public Activity getActivityById(int id) {
        Optional<Activity> activity = activityRepository.findById(id);
        return activity.orElse(null);
    }
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }
    public List<Activity> getAllActivitiesByUsername(String username) {
        return activityRepository.findAllActivitiesByUsername(username);
    }

}
