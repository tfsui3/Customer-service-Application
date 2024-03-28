package com.zycode.demo.student;

import java.util.UUID;
public class Customer {

    private final UUID customerId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Gender gender;
    public Customer(UUID customerId,
                    String firstName,
                    String lastName,
                    String email,
                    Gender gender){
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }

    public enum Gender{
        MALE,
        FEMALE
    }
}
