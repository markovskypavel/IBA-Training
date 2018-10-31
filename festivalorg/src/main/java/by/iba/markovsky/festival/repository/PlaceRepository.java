package by.iba.markovsky.festival.repository;

import by.iba.markovsky.festival.model.Place;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("placeRepository")
public interface PlaceRepository extends CrudRepository<Place, Integer> {
    List<Place> findAll();
}
