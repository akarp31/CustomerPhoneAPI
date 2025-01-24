package com.phonenumber.restapi.exception;

public class PhoneNumberNotFoundException extends RuntimeException{
    public PhoneNumberNotFoundException() {
    }
    public PhoneNumberNotFoundException(String message) {
        super(message);
    }
}