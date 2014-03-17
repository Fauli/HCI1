package com.example.hci1.arztpraxis.domain;

public class Doctor extends TreatingPerson {

    private String firstName;
    private String lastName;

    public Doctor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "Doctor{"
                + "firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + '}';
    }
}
