package by.iba.markovsky.festival.repository;

import by.iba.markovsky.festival.model.Activity;
import by.iba.markovsky.festival.model.WebIdentity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("activityRepository")
public interface ActivityRepository extends CrudRepository<Activity, Integer> {
    List<Activity> findAll();
    @Query("select a from Activity a join a.users u where u.username=?1 ORDER BY id ASC")
    List<Activity> findAllActivitiesByUsername(String username);
}
