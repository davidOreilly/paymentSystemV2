package model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.RegexValidator;

/**
 * Utility class containing static methods to validate various elements of a Shopper object
 */
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
     * @return true if valid. Otherwise false
     */
    public static boolean isValidLastName(String lastName) {
        if (StringUtils.isAlpha(lastName)) {
            return true;
        }

        return false;
    }

    /**
     * Check if supplied city is valid
     * @param city
     * @return true if valid. Otherwise false
     */
    public static boolean isValidCity(String city) {
        if(StringUtils.isAlpha(city)) {
            return true;
        }

        return false;
    }

    /**
     * Check if supplied state is valid
     * @param state
     * @return true if valid. Otherwise false
     */
    public static boolean isValidState(String state) {
        if(StringUtils.isAlpha(state)) {
            return true;
        }

        return false;
    }

    /**
     * Check if supplied postcode is valid
     * @param postcode
     * @return true if valid. Otherwise false
     */
    public static boolean isValidPostcode(String postcode) {
        RegexValidator regexValidator = new RegexValidator(postcodeRegex);

        return regexValidator.isValid(postcode);
    }

    /**
     * Check if supplied phone number is valid
     * @param phone
     * @return true if valid. Otherwise false
     */
    public static boolean isValidPhone(String phone) {
        char[] phoneChars = phone.toCharArray();
        for (char phoneChar : phoneChars) {
            if (!Character.isDigit(phoneChar)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Check if supplied countryCode is a valid 2 digit ISO 3166-1 country code
     * @param countryCode
     * @return true if valid. Otherwise false
     */
    public static boolean isValidCountryCode(String countryCode) {
        if (countryCode.length() == 2 && StringUtils.isAlpha(countryCode)) {
            return true;
        }

        return false;
    }

}
