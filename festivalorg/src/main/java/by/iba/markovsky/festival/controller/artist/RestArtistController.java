package by.iba.markovsky.festival.controller.artist;

import by.iba.markovsky.festival.constant.MappingConstant;
import by.iba.markovsky.festival.exception.NotFoundException;
import by.iba.markovsky.festival.model.Activity;
import by.iba.markovsky.festival.model.Artist;
import by.iba.markovsky.festival.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestArtistController {

    @Autowired
    @Qualifier("artistService")
    private ArtistService artistService;

    //@RequestParam необходим для подстановки из UrlQuery
    //spring.mvc.pathmatch.use-suffix-pattern=true в properties для работы расширений
    @RequestMapping(value = MappingConstant.GET_ARTIST, method = RequestMethod.GET)
    public Artist findArtist(@PathVariable("id") int id) throws NotFoundException {
        Artist artist = artistService.getArtistById(id);
        if (artist == null) {
            throw new NotFoundException();
        }
        return artist;
    }

    @RequestMapping(value = MappingConstant.GET_ALL_ARTISTS, method = RequestMethod.GET)
    public List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }

    @RequestMapping(value = MappingConstant.DELETE_ARTIST, method = RequestMethod.POST)
    public void deleteArtist(@PathVariable("id") int id) throws NotFoundException {
        Artist artist = artistService.getArtistById(id);
        if (artist == null) {
            throw new NotFoundException();
        }
        artistService.deleteArtist(artist);
    }

}
