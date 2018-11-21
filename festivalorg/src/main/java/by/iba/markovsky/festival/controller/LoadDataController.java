package by.iba.markovsky.festival.controller;

import by.iba.markovsky.festival.constant.HTMLConstant;
import by.iba.markovsky.festival.constant.MappingConstant;
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
public class LoadDataController {

    @Autowired
    @Qualifier("activityService")
    private ActivityService activityService;

    @Autowired
    @Qualifier("artistService")
    private ArtistService artistService;

    @RequestMapping(value = MappingConstant.LOAD_DATA_HOME, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loadAllDataMain(Model model, Principal principal) {
        if(principal != null){
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            model.addAttribute("userActivities", activityService.getAllActivitiesByUsername(loginedUser.getUsername()));
        }
        model.addAttribute("activities", activityService.getAllActivities());
        return new ModelAndView(HTMLConstant.HOME_FRAGMENT);
    }

    @RequestMapping(value = MappingConstant.LOAD_DATA_ADMIN, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loadAllDataAdmin(Model model) {
        model.addAttribute("activities", activityService.getAllActivities());
        model.addAttribute("artists", artistService.getAllArtists());
        return new ModelAndView(HTMLConstant.ADMIN_FRAGMENT);
    }

    @RequestMapping(value = MappingConstant.LOAD_DATA_USER, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loadAllDataUser(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        model.addAttribute("activities", activityService.getAllActivitiesByUsername(loginedUser.getUsername()));
        return new ModelAndView(HTMLConstant.USER_FRAGMENT);
    }

}
