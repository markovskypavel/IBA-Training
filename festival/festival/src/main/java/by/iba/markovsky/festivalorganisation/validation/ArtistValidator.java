package by.iba.markovsky.festivalorganisation.validation;

import by.iba.markovsky.festivalorganisation.constant.RegEx;
import by.iba.markovsky.festivalorganisation.exception.ValidationException;
import by.iba.markovsky.festivalorganisation.util.RegExValidatorUtil;

public class ArtistValidator {

    public static boolean isArtistFormValid(String name, String genre) throws ValidationException {
        if (name == null || genre == null) {
            throw new ValidationException();
        }
        if (!(RegExValidatorUtil.isValid(name, RegEx.UNIQUE_NAME)
                && RegExValidatorUtil.isValid(genre, RegEx.GENRE))) {
            throw new ValidationException();
        }
        return true;
    }

}
