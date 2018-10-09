package by.iba.markovsky.festivalorganisation.infrastructure.util;

import java.util.regex.Pattern;

public class RegExValidator {

    public static boolean isValid(String regEx, String field) {
        return Pattern.matches(regEx, field);
    }

}
