package by.iba.markovsky.festivalorganisation.application.validation;

import by.iba.markovsky.festivalorganisation.infrastructure.constant.RegEx;
import by.iba.markovsky.festivalorganisation.infrastructure.exception.ValidationException;
import by.iba.markovsky.festivalorganisation.infrastructure.util.RegExValidator;

public class ParticipantValidator {

    public static boolean isParticipantFormValid(String name, String surname, String age,
                                                 String username, String password, String email, String telephone) throws ValidationException {
        if (name == null || surname == null || age == null || username == null || password == null || email == null || telephone == null) {
            throw new ValidationException();
        }
        if (!(RegExValidator.isValid(name, RegEx.NAME)
                && RegExValidator.isValid(surname, RegEx.SURNAME)
                && RegExValidator.isValid(age, RegEx.AGE)
                && RegExValidator.isValid(username, RegEx.LOGIN)
                && RegExValidator.isValid(password, RegEx.PASSWORD)
                && RegExValidator.isValid(email, RegEx.EMAIL)
                && RegExValidator.isValid(telephone, RegEx.TELEPHONE_ALTERNATIVE))) {
            throw new ValidationException();
        }
        return true;
    }

}
