package by.iba.markovsky.festival.controller;

import by.iba.markovsky.festival.constant.HTMLConstant;
import by.iba.markovsky.festival.constant.MappingConstant;
import by.iba.markovsky.festival.exception.LimitException;
import by.iba.markovsky.festival.exception.NotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice(annotations = {Controller.class, RestController.class})
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ModelAndView(HTMLConstant.ERROR_PAGE);
    }

    @ExceptionHandler(LimitException.class)
    public ModelAndView handleLimitException(HttpServletResponse response) {
        response.setStatus(HttpStatus.I_AM_A_TEAPOT.value());
        return new ModelAndView(HTMLConstant.ERROR_PAGE);
    }

/*    TODO: Warning: when using this annotation on an exception class, or when setting the reason attribute
    of this annotation, the HttpServletResponse.sendError method will be used. With
    HttpServletResponse.sendError, the response is considered complete and should not be written
    to any further. Furthermore, the Servlet container will typically write an HTML error page
    therefore making the use of a reason unsuitable for REST APIs. For such cases it is preferable
    to use a org.springframework.http.ResponseEntity as a return type and avoid the use of
    @ResponseStatus altogether.*/
    /*@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity not found")*/
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundRestException(HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return new ModelAndView(HTMLConstant.NOT_FOUND_PAGE);
    }

}
