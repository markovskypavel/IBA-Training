package by.iba.markovsky.festivalorganisation.validation;

import by.iba.markovsky.festivalorganisation.constant.RegEx;
import by.iba.markovsky.festivalorganisation.exception.ValidationException;
import by.iba.markovsky.festivalorganisation.util.RegExValidatorUtil;

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
