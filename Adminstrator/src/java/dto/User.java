/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.time.LocalDate;

/**
 *
 * @author Dina Ashraf
 */
public class User {

    private String email;
    private String imageUrl;
    private String gender;
    private String firstName;
    private String lastName;
    private LocalDate bDate;
    private String password;
    private String phone;
    private String address;
    private String type;
    private CreditCard creditCard;

    public User(String email, String imageUrl, String gender, String firstName, String lastName, LocalDate bDate, String password, String phone, String address, String type, CreditCard creditCard) {
        this.email = email;
        this.imageUrl = imageUrl;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bDate = bDate;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.type = type;
        this.creditCard = creditCard;
    }

    public User(String email, String imageUrl, String gender, String firstName, String lastName, LocalDate bDate, String password, String phone, String address, String type) {
        this.email = email;
        this.imageUrl = imageUrl;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bDate = bDate;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.type = type;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl the imageUrl to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the bDate
     */
    public LocalDate getbDate() {
        return bDate;
    }

    /**
     * @param bDate the bDate to set
     */
    public void setbDate(LocalDate bDate) {
        this.bDate = bDate;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the creditCard
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * @param creditCard the creditCard to set
     */
    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", imageUrl=" + imageUrl + ", gender=" + gender + ", firstName=" + firstName + ", lastName=" + lastName + ", bDate=" + bDate + ", password=" + password + ", phone=" + phone + ", address=" + address + ", type=" + type + ", creditCard=" + creditCard + '}';
    }
    // updates

    //end of updates
}
