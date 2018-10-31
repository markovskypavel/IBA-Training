package by.iba.markovsky.festival.validation;

import by.iba.markovsky.festival.constant.RegEx;
import by.iba.markovsky.festival.exception.ValidationException;
import by.iba.markovsky.festival.util.RegExValidatorUtil;

public class ActivityValidator {

    public static boolean isActivityFormValid(String name) throws ValidationException {
        if (name == null) {
            throw new ValidationException();
        }
        if (!(RegExValidatorUtil.isValid(name, RegEx.UNIQUE_NAME))) {
            throw new ValidationException();
        }
        return true;
    }

}
