package by.iba.markovsky.festival.repository;

import by.iba.markovsky.festival.model.Activity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("activityRepository")
public interface ActivityRepository extends CrudRepository<Activity, Integer> {
    List<Activity> findAll();
}
