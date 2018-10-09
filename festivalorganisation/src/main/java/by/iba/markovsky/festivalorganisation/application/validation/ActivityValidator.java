package by.iba.markovsky.festivalorganisation.application.validation;

import by.iba.markovsky.festivalorganisation.infrastructure.constant.RegEx;
import by.iba.markovsky.festivalorganisation.infrastructure.exception.ValidationException;
import by.iba.markovsky.festivalorganisation.infrastructure.util.RegExValidator;

public class ActivityValidator {

    public static boolean isActivityFormValid(String name) throws ValidationException {
        if (name == null) {
            throw new ValidationException();
        }
        if (!(RegExValidator.isValid(name, RegEx.UNIQUE_NAME))) {
            throw new ValidationException();
        }
        return true;
    }

}
