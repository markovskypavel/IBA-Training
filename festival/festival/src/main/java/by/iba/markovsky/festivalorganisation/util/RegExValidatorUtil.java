package by.iba.markovsky.festivalorganisation.util;

import java.util.regex.Pattern;

public class RegExValidatorUtil {

    public static boolean isValid(String regEx, String field) {
        return Pattern.matches(regEx, field);
    }

}
