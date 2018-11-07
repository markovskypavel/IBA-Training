package by.iba.markovsky.festival.controller;

import by.iba.markovsky.festival.constant.HTMLConstant;
import by.iba.markovsky.festival.constant.MappingConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ActivityController {

    @RequestMapping(value = MappingConstant.ADD_FESTIVAL, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView add() {
        return new ModelAndView(HTMLConstant.ADD_FESTIVAL_PAGE);
    }

}
