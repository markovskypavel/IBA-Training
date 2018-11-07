package by.iba.markovsky.festival.controller;

import by.iba.markovsky.festival.constant.HTMLConstant;
import by.iba.markovsky.festival.constant.MappingConstant;
import by.iba.markovsky.festival.model.WebIdentity;
import by.iba.markovsky.festival.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MappingController {

/*    localhost:(Port number) /project name/(request mapping at controller) /(request mapping at method)*/

    @Autowired
    @Qualifier("activityService")
    private ActivityService activityService;

    @RequestMapping(value = MappingConstant.HOME, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView home(HttpServletRequest req, HttpServletResponse resp, Model model) {
        model.addAttribute("activities", activityService.getAllActivities());
        return new ModelAndView(HTMLConstant.HOME_PAGE);
    }

    @RequestMapping(value = MappingConstant.ABOUT_US, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView about() {
        return new ModelAndView(HTMLConstant.ABOUT_US_PAGE);
    }

    @RequestMapping(value = MappingConstant.LOGIN, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login() {
        return new ModelAndView(HTMLConstant.LOGIN_PAGE);
    }

    @RequestMapping(value = MappingConstant.ADMIN, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView admin() {
        return new ModelAndView(HTMLConstant.ADMIN_PAGE);
    }

    @RequestMapping(value = MappingConstant.USER, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView user() {
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


/*    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        return HTMLConstant.ADMIN_PAGE;
    }*/

}
