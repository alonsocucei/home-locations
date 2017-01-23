package com.luxoft.homelocations.representations;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
 *
 * @author jjsanche
 */
public class Home {
    private final int id;
    @NotBlank
    private final String address1;
    private final String address2;
    @NotBlank
    private final String city;
    private final String state;
    @Pattern(regexp="[\\d\\w\\s]*")
    private final String zipCode;
    @NotBlank
    @Length(min=2, max=30)
    private final String country;
    
    public Home() {
        this.id = 0;
        this.address1 = null;
        this.address2 = null;
        this.city = null;
        this.state = null;
        this.zipCode = null;
        this.country = null;
    }
    
    public Home(int id, String address1, String address2, String city, String state, String zipCode, String country) {
        this.id = id;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }
}