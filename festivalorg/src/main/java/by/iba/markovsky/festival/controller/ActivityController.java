package by.iba.markovsky.festival.controller;

import by.iba.markovsky.festival.constant.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ActivityController {

/*    localhost:(Port number) /project name/(request mapping at controller) /(request mapping at method)*/

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView home(HttpServletRequest req, HttpServletResponse resp) {
        return new ModelAndView(Constant.MAIN_PAGE);
    }

}
