package by.iba.markovsky.festival.validation;

import by.iba.markovsky.festival.constant.RegEx;
import by.iba.markovsky.festival.exception.ValidationException;
import by.iba.markovsky.festival.util.RegExValidatorUtil;

public class ParticipantValidator {

    public static boolean isParticipantFormValid(String name, String surname, String age,
                                                 String username, String password, String email, String telephone) throws ValidationException {
        if (name == null || surname == null || age == null || username == null || password == null || email == null || telephone == null) {
            throw new ValidationException();
        }
        if (!(RegExValidatorUtil.isValid(name, RegEx.NAME)
                && RegExValidatorUtil.isValid(surname, RegEx.SURNAME)
                && RegExValidatorUtil.isValid(age, RegEx.AGE)
                && RegExValidatorUtil.isValid(username, RegEx.LOGIN)
                && RegExValidatorUtil.isValid(password, RegEx.PASSWORD)
                && RegExValidatorUtil.isValid(email, RegEx.EMAIL)
                && RegExValidatorUtil.isValid(telephone, RegEx.TELEPHONE_ALTERNATIVE))) {
            throw new ValidationException();
        }
        return true;
    }

}
