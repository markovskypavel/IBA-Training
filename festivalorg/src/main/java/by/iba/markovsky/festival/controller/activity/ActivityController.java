package by.iba.markovsky.festival.controller.activity;

import by.iba.markovsky.festival.constant.HTMLConstant;
import by.iba.markovsky.festival.constant.MappingConstant;
import by.iba.markovsky.festival.exception.LimitException;
import by.iba.markovsky.festival.model.Activity;
import by.iba.markovsky.festival.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ActivityController {

    @Autowired
    @Qualifier("activityService")
    private ActivityService activityService;

    @RequestMapping(value = MappingConstant.ADD_ACTIVITY, method = RequestMethod.GET)
    public ModelAndView addActivityPage(Model model) {
        model.addAttribute("activity", new Activity());
        return new ModelAndView(HTMLConstant.ACTIVITY_PAGE);
    }

    @RequestMapping(value = MappingConstant.EDIT_ACTIVITY, method = RequestMethod.GET)
    public ModelAndView editActivityPage(@PathVariable("id") int id, Model model) {
        Activity activity = activityService.getActivityById(id);
        model.addAttribute("activity", activity);
        return new ModelAndView(HTMLConstant.ACTIVITY_PAGE_EDIT);
    }

    //Params необходим для определения конкретной кнопки
    @RequestMapping(value = MappingConstant.ADD_ACTIVITY, method = RequestMethod.POST, params = "add")
    public String addActivity(@Valid @ModelAttribute(value = "activity") Activity activity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HTMLConstant.ACTIVITY_PAGE;
        }
        if (activityService.getActivityByName(activity.getName()) != null) {
            return "redirect:" + MappingConstant.ADD_ACTIVITY + MappingConstant.ERROR_QUERY;
        }
        activityService.addActivity(activity);
        return "redirect:" + MappingConstant.ADMIN;
    }

    @RequestMapping(value = MappingConstant.ADD_ACTIVITY, method = RequestMethod.POST, params = "edit")
    public String editActivity(@Valid @ModelAttribute(value = "activity") Activity activity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HTMLConstant.ACTIVITY_PAGE_EDIT;
        }
        activityService.updateActivity(activity);
        return "redirect:" + MappingConstant.ADMIN;
    }

}
