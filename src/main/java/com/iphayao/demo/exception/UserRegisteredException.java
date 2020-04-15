package com.iphayao.demo.exception;

import static java.lang.String.format;

public class UserRegisteredException extends Exception {
    public UserRegisteredException(String email) {
        super(format("Email: %s was registered", email));
    }
}
