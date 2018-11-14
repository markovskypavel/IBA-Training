package by.iba.markovsky.festival.controller;

import by.iba.markovsky.festival.constant.HTMLConstant;
import by.iba.markovsky.festival.constant.MappingConstant;
import by.iba.markovsky.festival.exception.LimitException;
import by.iba.markovsky.festival.model.Activity;
import by.iba.markovsky.festival.service.ActivityService;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    //Params необходим для определения конкретной кнопки
    @RequestMapping(value = MappingConstant.ADD_ACTIVITY, method = RequestMethod.POST/*, params = "festival"*/)
    public String addActivity(@Valid @ModelAttribute(value = "activity") Activity activity,
                              BindingResult bindingResult, HttpServletRequest req) {
        if (bindingResult.hasErrors()) {
            return HTMLConstant.ACTIVITY_PAGE;
        }
        if (activityService.getActivityByName(activity.getName()) != null) {
            return "redirect:" + MappingConstant.ADD_ACTIVITY + MappingConstant.ERROR_QUERY;
        }
        activityService.setType(activity, req.getParameter("festival") != null);
        activityService.addOrUpdateAdctivity(activity);
        return "redirect:" + MappingConstant.HOME;
    }

    @RequestMapping(value = MappingConstant.EDIT_ACTIVITY, method = RequestMethod.GET)
    public ModelAndView editActivity(@PathVariable("id") int id, Model model) {
        Activity activity = activityService.getActivityById(id);
        model.addAttribute("activity", activity);
        return new ModelAndView(HTMLConstant.ACTIVITY_PAGE);
    }

}
