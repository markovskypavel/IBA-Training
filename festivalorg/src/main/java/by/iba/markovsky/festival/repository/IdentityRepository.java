package by.iba.markovsky.festival.repository;

import by.iba.markovsky.festival.model.Identity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("identityRepository")
public interface IdentityRepository extends CrudRepository<Identity, Integer> {
    List<Identity> findAll();
}
