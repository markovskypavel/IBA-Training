package by.iba.markovsky.festivalorganisation.application.validation;

import by.iba.markovsky.festivalorganisation.infrastructure.constant.RegEx;
import by.iba.markovsky.festivalorganisation.infrastructure.exception.ValidationException;
import by.iba.markovsky.festivalorganisation.infrastructure.util.RegExValidator;

public class ArtistValidator {

    public static boolean isArtistFormValid(String name, String genre) throws ValidationException {
        if (name == null || genre == null) {
            throw new ValidationException();
        }
        if (!(RegExValidator.isValid(name, RegEx.UNIQUE_NAME)
                && RegExValidator.isValid(genre, RegEx.GENRE))) {
            throw new ValidationException();
        }
        return true;
    }

}
