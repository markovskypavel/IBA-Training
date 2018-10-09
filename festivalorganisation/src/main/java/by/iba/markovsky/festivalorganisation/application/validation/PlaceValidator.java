package by.iba.markovsky.festivalorganisation.application.validation;

import by.iba.markovsky.festivalorganisation.infrastructure.constant.RegEx;
import by.iba.markovsky.festivalorganisation.infrastructure.exception.ValidationException;
import by.iba.markovsky.festivalorganisation.infrastructure.util.RegExValidator;

public class PlaceValidator {

    public static boolean isPlaceFormValid(String address, String capacity) throws ValidationException {
        if (address == null || capacity == null) {
            throw new ValidationException();
        }
        if (!(RegExValidator.isValid(address, RegEx.ADDRESS)
                && RegExValidator.isValid(capacity, RegEx.CAPACITY))) {
            throw new ValidationException();
        }
        return true;
    }

}
