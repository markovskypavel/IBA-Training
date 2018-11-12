package by.iba.markovsky.festival.controller;

import by.iba.markovsky.festival.constant.HTMLConstant;
import by.iba.markovsky.festival.constant.MappingConstant;
import by.iba.markovsky.festival.model.WebIdentity;
import by.iba.markovsky.festival.service.ActivityService;
import by.iba.markovsky.festival.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class MappingController {

    @Autowired
    @Qualifier("activityService")
    private ActivityService activityService;

    @Autowired
    @Qualifier("artistService")
    private ArtistService artistService;

    @RequestMapping(value = MappingConstant.HOME, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView home(HttpServletRequest req, HttpServletResponse resp, Model model) {
        model.addAttribute("activities", activityService.getAllActivities());
        return new ModelAndView(HTMLConstant.HOME_PAGE);
    }

    @RequestMapping(value = MappingConstant.ABOUT_US, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView about() {
        return new ModelAndView(HTMLConstant.ABOUT_US_PAGE);
    }

    @RequestMapping(value = MappingConstant.ADMIN, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView admin(Model model) {
        model.addAttribute("activities", activityService.getAllActivities());
        model.addAttribute("artists", artistService.getAllArtists());
        return new ModelAndView(HTMLConstant.ADMIN_PAGE);
    }

    @RequestMapping(value = MappingConstant.USER, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView user(Principal principal, Model model) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        model.addAttribute("activities", activityService.getAllActivitiesByUsername(loginedUser.getUsername()));
        return new ModelAndView(HTMLConstant.USER_PAGE);
    }

    @RequestMapping(value = MappingConstant.DENIED, method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        return new ModelAndView(HTMLConstant.DENIED_PAGE);
    }

    @RequestMapping(value = MappingConstant.NOT_FOUND, method = RequestMethod.GET)
    public ModelAndView notFound() {
        return new ModelAndView(HTMLConstant.NOT_FOUND_PAGE);
    }

}
