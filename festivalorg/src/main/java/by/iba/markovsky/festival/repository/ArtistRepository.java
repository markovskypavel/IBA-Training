package by.iba.markovsky.festival.repository;

import by.iba.markovsky.festival.model.Activity;
import by.iba.markovsky.festival.model.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("artistRepository")
public interface ArtistRepository extends CrudRepository<Artist, Integer> {
    List<Artist> findAll();
    List<Artist> findByActivitiesIsNotContaining(Activity activity);
    Artist findByName(String name);
    //Searches in activity_has_webidentity
    List<Artist> findByActivities_id(int id);
}
