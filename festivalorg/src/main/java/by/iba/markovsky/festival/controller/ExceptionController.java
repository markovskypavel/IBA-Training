package by.iba.markovsky.festival.controller;

import by.iba.markovsky.festival.constant.MappingConstant;
import by.iba.markovsky.festival.exception.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({Exception.class})
    public RedirectView handleException() {
        return new RedirectView(MappingConstant.ERROR);
    }

/*    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity not found")*/
    @ExceptionHandler(NotFoundException.class)
    public RedirectView handleNotFoundException() {
        return new RedirectView(MappingConstant.NOT_FOUND);
    }

}
