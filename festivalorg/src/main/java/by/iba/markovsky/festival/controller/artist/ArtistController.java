package by.iba.markovsky.festival.controller.artist;

import by.iba.markovsky.festival.constant.HTMLConstant;
import by.iba.markovsky.festival.constant.MappingConstant;
import by.iba.markovsky.festival.model.Artist;
import by.iba.markovsky.festival.service.ArtistService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
public class ArtistController {

    //Logger logger = LoggerFactory.getLogger(ArtistController.class);

    @Autowired
    @Qualifier("artistService")
    private ArtistService artistService;

    @RequestMapping(value = MappingConstant.ADD_ARTIST, method = RequestMethod.GET)
    public ModelAndView addArtistPage(Model model) {
        model.addAttribute("artist", new Artist());
        return new ModelAndView(HTMLConstant.ARTIST_PAGE);
    }

    @RequestMapping(value = MappingConstant.EDIT_ARTIST, method = RequestMethod.GET)
    public ModelAndView editArtistPage(@PathVariable("id") int id, Model model) {
        Artist artist = artistService.getArtistById(id);
        model.addAttribute("artist", artist);
        return new ModelAndView(HTMLConstant.ARTIST_PAGE_EDIT);
    }

    @RequestMapping(value = MappingConstant.ADD_ARTIST, method = RequestMethod.POST, params = "add")
    public String addArtist(@Valid @ModelAttribute(value = "artist") Artist artist, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HTMLConstant.ARTIST_PAGE;
        }
        if (artistService.getArtistByName(artist.getName()) != null) {
            return "redirect:" + MappingConstant.ADD_ARTIST + MappingConstant.ERROR_QUERY;
        }
        artistService.addOrUpdateArtist(artist);
        return "redirect:" + MappingConstant.ADMIN;
    }
    @RequestMapping(value = MappingConstant.ADD_ARTIST, method = RequestMethod.POST, params = "edit")
    public String editArtist(@Valid @ModelAttribute(value = "artist") Artist artist, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HTMLConstant.ARTIST_PAGE_EDIT;
        }
        artistService.addOrUpdateArtist(artist);
        return "redirect:" + MappingConstant.ADMIN;
    }

}
