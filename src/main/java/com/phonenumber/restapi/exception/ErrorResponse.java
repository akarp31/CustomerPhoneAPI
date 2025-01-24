package com.phonenumber.restapi.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {
    private int statusCode ;
    private String message ;

    public ErrorResponse(int value, String message) {
    }
}