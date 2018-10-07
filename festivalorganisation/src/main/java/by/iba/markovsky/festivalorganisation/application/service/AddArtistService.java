package by.iba.markovsky.festivalorganisation.application.service;

import by.iba.markovsky.festivalorganisation.domain.entity.Artist;
import by.iba.markovsky.festivalorganisation.infrastructure.exception.ExistException;

import java.util.ArrayList;
import java.util.List;

public class AddArtistService {

    private List<Artist> artists = new ArrayList<>();

    public void addArtist(String name, String genre) throws ExistException {
        Artist artist = new Artist(name, genre);
        if (checkExist(artist)) {
            throw new ExistException("Artist exists");
        }
        artists.add(artist);
    }
    private boolean checkExist(Artist newArtist) {
        for (Artist a : artists) {
            if (a.getName().equals(newArtist.getName())) {
                return true;
            }
        }
        return false;
    }
    public List<Artist> getArtists() {
        return artists;
    }

}
