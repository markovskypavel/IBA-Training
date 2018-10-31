package by.iba.markovsky.festival.controller;

import by.iba.markovsky.festival.constant.Constant;
import by.iba.markovsky.festival.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({Exception.class})
    public ModelAndView handleRepositoryException() {
        return new ModelAndView(Constant.REDIRECT + Constant.ERROR_QUERY);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Human not found")
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException() {
        return new ModelAndView(Constant.REDIRECT);
    }

}
