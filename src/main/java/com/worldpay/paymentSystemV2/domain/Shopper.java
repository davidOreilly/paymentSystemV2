package com.worldpay.paymentSystemV2.domain;

import model.ShopperValidator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shopper")
public class Shopper {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String address1;
    private String address2; //optional
    private String city; //optional
    private String state; //optional
    private String postCode;
    private String phone; //optional
    private String email; //optional
    private String countryCode;

    private Shopper(ShopperBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.address1 = builder.address1;
        this.postCode = builder.postCode;
        this.countryCode = builder.countryCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public static class ShopperBuilder {
        private final String firstName;
        private final String lastName;
        private final String address1;
        private final String postCode;
        private final String countryCode;
        private String address2;
        private String city;
        private String state;
        private String phone;
        private String email;

        public ShopperBuilder(String firstName, String lastName, String address1, String postCode, String countryCode) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address1 = address1;
            this.postCode = postCode;
            this.countryCode = countryCode;
        }

        public ShopperBuilder withAddress2(String address2) {
            this.address2 = address2;
            return this;
        }

        public ShopperBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public ShopperBuilder withState(String state) {
            this.state = state;
            return this;
        }

        public ShopperBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public ShopperBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Shopper build() throws IllegalStateException {
            if (ShopperValidator.isValidFirstName(firstName) &&
                    ShopperValidator.isValidLastName(lastName) &&
                    ShopperValidator.isValidPostcode(postCode) &&
                    ShopperValidator.isValidCountryCode(countryCode)) {
                return new Shopper(this);
            } else {
                throw new IllegalStateException("Data passed from merchant is invalid");
            }
        }

    }
}
