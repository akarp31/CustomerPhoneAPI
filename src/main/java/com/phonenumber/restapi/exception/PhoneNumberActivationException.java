package com.phonenumber.restapi.exception;

public class PhoneNumberActivationException extends RuntimeException{
    public PhoneNumberActivationException() {
    }
    public PhoneNumberActivationException(String message) {
        super(message);
    }
}
