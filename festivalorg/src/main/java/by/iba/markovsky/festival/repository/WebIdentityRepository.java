package by.iba.markovsky.festival.repository;

import by.iba.markovsky.festival.model.WebIdentity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("webIdentityRepository")
public interface WebIdentityRepository extends CrudRepository<WebIdentity, Integer> {
    List<WebIdentity> findAll();
}
