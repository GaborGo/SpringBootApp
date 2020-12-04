package com.springboot.contapp.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String errorMessage){
       super(errorMessage);
    }
}
