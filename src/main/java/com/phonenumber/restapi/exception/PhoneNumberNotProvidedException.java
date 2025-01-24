package com.phonenumber.restapi.exception;

public class PhoneNumberNotProvidedException extends RuntimeException{
    public PhoneNumberNotProvidedException() {
    }
    public PhoneNumberNotProvidedException(String message) {
        super(message);
    }
}
