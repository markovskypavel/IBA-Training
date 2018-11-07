package by.iba.markovsky.festival.controller;

import by.iba.markovsky.festival.constant.HTMLConstant;
import by.iba.markovsky.festival.constant.MappingConstant;
import by.iba.markovsky.festival.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class ExceptionController {

    //TODO: Сделать исключения

    @ExceptionHandler({Exception.class})
    public RedirectView handleRepositoryException() {
        return new RedirectView(MappingConstant.HOME + MappingConstant.ERROR_QUERY);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity not found")
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException() {
        return new ModelAndView(MappingConstant.NOT_FOUND);
    }

}
