package model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.RegexValidator;
import org.omg.CORBA.PolicyHolder;

public class ShopperValidator {

    private static final String postcodeRegex = "^(([gG][iI][rR] {0,}0[aA]{2})|(([aA][sS][cC][nN]|[sS][tT][hH][lL]|[tT]" +
            "[dD][cC][uU]|[bB][bB][nN][dD]|[bB][iI][qQ][qQ]|[fF][iI][qQ][qQ]|[pP][cC][rR][nN]|[sS][iI][qQ][qQ]|[iT][kK][cC][aA]) " +
            "{0,}1[zZ]{2})|((([a-pr-uwyzA-PR-UWYZ][a-hk-yxA-HK-XY]?[0-9][0-9]?)|(([a-pr-uwyzA-PR-UWYZ][0-9][a-hjkstuwA-HJKSTUW])|" +
            "([a-pr-uwyzA-PR-UWYZ][a-hk-yA-HK-Y][0-9][abehmnprv-yABEHMNPRV-Y]))) {0,}[0-9][abd-hjlnp-uw-zABD-HJLNP-UW-Z]{2}))$";

    private ShopperValidator() {
    }

    /**
     * Check if supplied firstName is valid
     * @param firstName
     * @return true if valid. Otherwise false
     */
    public static boolean isValidFirstName(String firstName) {
        if (StringUtils.isAlpha(firstName)) {
            return true;
        }

        return false;
    }

    /**
     * Check if supplied lastName is valid
     * @param lastName
     * @return true
     */
    public static boolean isValidLastName(String lastName) {
        if (StringUtils.isAlpha(lastName)) {
            return true;
        }

        return false;
    }

    public static boolean isValidPostcode(String postcode) {
        RegexValidator regexValidator = new RegexValidator(postcodeRegex);
        return regexValidator.isValid(postcode);
    }

    public static boolean isValidPhone(String phone) {
        if (!StringUtils.isAlpha(phone)) {
            return true;
        }

        return false;
    }
//
//    public static boolean isValidCountryCode(int countryCode) {
//
//    }
}
