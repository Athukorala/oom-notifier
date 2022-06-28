
package com.swlc.bolton.notifier.util;

import com.swlc.bolton.notifier.enums.ValidateType;
import java.util.regex.Pattern;

/**
 *
 * @author athukorala
 */
public class Validator {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_PASSWORD = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", Pattern.CASE_INSENSITIVE);
    
    public static boolean regexHandler(String value, ValidateType validateType) {
        switch(validateType) {
            case EMAIL:
                 return VALID_EMAIL_ADDRESS_REGEX.matcher(value).find();
            case PASSWORD:
                return VALID_PASSWORD.matcher(value).find();
        }
       return false;
    }
}
