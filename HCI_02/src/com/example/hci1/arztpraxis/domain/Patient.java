package com.example.hci1.arztpraxis.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Patient {

    private static final SimpleDateFormat BIRTHDAY_FORMAT = new SimpleDateFormat("dd.MM.YYYY");

    public enum Gender {
        MALE, FEMALE
    }

    private Gender gender;

    private String firstName;

    private String lastName;

    private String address;

    private String postCode;

    private String city;

    private String phoneNumber;

    private Calendar birthday;

    public Patient(Gender gender, String firstName, String lastName, String address, String postCode, String city,
                   String phoneNumber, Calendar birthday) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postCode = postCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Patient{"
                + "gender=" + gender
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", address='" + address + '\''
                + ", postCode='" + postCode + '\''
                + ", city='" + city + '\''
                + ", phoneNumber='" + phoneNumber + '\''
                + ", birthday='" + BIRTHDAY_FORMAT.format(birthday.getTime()) + '\''
                + '}';
    }
}
