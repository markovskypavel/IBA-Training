package by.iba.markovsky.festival.service;

import by.iba.markovsky.festival.exception.ServiceException;
import by.iba.markovsky.festival.model.Activity;
import by.iba.markovsky.festival.model.Artist;
import by.iba.markovsky.festival.repository.ActivityRepository;
import by.iba.markovsky.festival.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("artistService")
@Transactional(rollbackFor = ServiceException.class)
public class ArtistService {

    @Autowired
    @Qualifier("artistRepository")
    private ArtistRepository artistRepository;

    public void addOrUpdateArtist(Artist artist) {
        artistRepository.save(artist);
    }
    public void deleteArtist(Artist artist) {
        //For many-to-many delete we have to unlink the child's/parent's entities
        for (Activity activity : artist.getActivities()) {
            activity.getArtists().remove(artist);
        }
        artistRepository.delete(artist);
    }
    public Artist getArtistById(int id) {
        Optional<Artist> artist = artistRepository.findById(id);
        return artist.orElse(null);
    }
    public Artist getArtistByName(String name) {
        Artist artist = artistRepository.findByName(name);
        return artist;
    }
    public List<Artist> getAllUnusedArtists(Activity activity) {
        return artistRepository.findByActivitiesIsNotContaining(activity);
    }
    public List<Artist> getAllUsedArtists(Activity activity) {
        return artistRepository.findByActivities_id(activity.getId());
    }
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

}
