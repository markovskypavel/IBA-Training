package by.iba.markovsky.festival.validation;

import by.iba.markovsky.festival.constant.RegExConstant;
import by.iba.markovsky.festival.exception.ValidationException;
import by.iba.markovsky.festival.util.RegExValidatorUtil;

public class WebIdentityValidator {
    public static boolean isWebIdentityFormValid(String name, String surname, String age,
                                                 String username, String password, String email, String telephone) throws ValidationException {
        if (name == null || surname == null || age == null || username == null || password == null || email == null || telephone == null) {
            throw new ValidationException();
        }
        if (!(RegExValidatorUtil.isValid(name, RegExConstant.NAME)
                && RegExValidatorUtil.isValid(surname, RegExConstant.SURNAME)
                && RegExValidatorUtil.isValid(age, RegExConstant.AGE)
                && RegExValidatorUtil.isValid(username, RegExConstant.LOGIN)
                && RegExValidatorUtil.isValid(password, RegExConstant.PASSWORD)
                && RegExValidatorUtil.isValid(email, RegExConstant.EMAIL)
                && RegExValidatorUtil.isValid(telephone, RegExConstant.TELEPHONE_ALTERNATIVE))) {
            throw new ValidationException();
        }
        return true;
    }
}
