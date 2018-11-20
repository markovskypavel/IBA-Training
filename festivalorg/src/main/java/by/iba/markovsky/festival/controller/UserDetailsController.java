package by.iba.markovsky.festival.controller;

import by.iba.markovsky.festival.constant.HTMLConstant;
import by.iba.markovsky.festival.constant.MappingConstant;
import by.iba.markovsky.festival.model.WebIdentity;
import by.iba.markovsky.festival.service.WebIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserDetailsController {

    @Autowired
    @Qualifier("webIdentityService")
    private WebIdentityService webIdentityService;

    @RequestMapping(value = MappingConstant.LOGIN, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login() {
        return new ModelAndView(HTMLConstant.LOGIN_PAGE);
    }

    @RequestMapping(value = MappingConstant.REGISTRATION, method = RequestMethod.GET)
    public ModelAndView registrationPage(Model model) {
        model.addAttribute("webIdentity", new WebIdentity());
        return new ModelAndView(HTMLConstant.REGISTRATION_PAGE);
    }

    @RequestMapping(value = MappingConstant.REGISTRATION, method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute(value = "webIdentity") WebIdentity webIdentity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HTMLConstant.REGISTRATION_PAGE;
        }
        if (webIdentityService.getUserByUsername(webIdentity.getUsername()) != null) {
            return "redirect:" + MappingConstant.REGISTRATION + MappingConstant.ERROR_QUERY;
        }
        webIdentityService.addOrUpdateWebIdentity(webIdentity);
        return HTMLConstant.LOGIN_PAGE;
    }

}
