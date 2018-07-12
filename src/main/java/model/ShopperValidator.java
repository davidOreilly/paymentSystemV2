package model;

import org.apache.commons.lang3.StringUtils;

public class ShopperValidator {

    private ShopperValidator() {
    }

    public static boolean isValidFirstName(String firstName) {
        if (StringUtils.isAlpha(firstName)) {
            return true;
        }

        return false;
    }

    public static boolean isValidLastName(String lastName) {
        if (StringUtils.containsOnly())
    }
//
//    public static boolean isValidTown(String town) {
//
//    }
//
//    public static boolean isValidCounty(String county) {
//
//    }
//
//    public static boolean isValidPostcode(String postcode) {
//
//    }
//
//    public static boolean isValidPhone(String phone) {
//
//    }
//
//    public static boolean isValidCountryCode(int countryCode) {
//
//    }
}
