package by.iba.markovsky.festival.validation;

import by.iba.markovsky.festival.constant.RegEx;
import by.iba.markovsky.festival.exception.ValidationException;
import by.iba.markovsky.festival.util.RegExValidatorUtil;

public class PlaceValidator {

    public static boolean isPlaceFormValid(String address, String capacity) throws ValidationException {
        if (address == null || capacity == null) {
            throw new ValidationException();
        }
        if (!(RegExValidatorUtil.isValid(address, RegEx.ADDRESS)
                && RegExValidatorUtil.isValid(capacity, RegEx.CAPACITY))) {
            throw new ValidationException();
        }
        return true;
    }

}
