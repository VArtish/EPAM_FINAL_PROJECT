package com.example.finalwebprojectepam.util.validator.impl;

import com.example.finalwebprojectepam.util.validator.UserValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidatorImpl implements UserValidator {
    private static final String REGEXP_NAME = "^([a-zA-Z][a-zA-Z0-9_]{3,16})$";
    private static final String REGEXP_PASSWORD = "^.{4,18}$";
    private static final String REGEXP_LOGIN = "^([a-zA-Z][a-zA-Z0-9_]{3,16})$";
    private static final String REGEXP_SURNAME = "^([a-zA-Z][a-zA-Z0-9_]{3,16})$";
    private static final String REGEXP_EMAIL = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]{2,}+$";
    private static final String REGEXP_DATE = "[1-2]\\d{3}-[0-1]\\d-[0-3]\\d";
    private static UserValidatorImpl instance;

    private UserValidatorImpl() {
    }

    public static UserValidatorImpl getInstance() {
        if (instance == null) {
            instance = new UserValidatorImpl();
        }

        return instance;
    }

    public boolean validateName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        return match(name, REGEXP_NAME);
    }

    public boolean validateSurname(String surname) {
        if (surname == null || surname.isEmpty()) {
            return false;
        }
        return match(surname, REGEXP_SURNAME);
    }

    public boolean validateLogin(String login) {
        if (login == null || login.isEmpty()) {
            return false;
        }
        return match(login, REGEXP_LOGIN);
    }

    public boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return match(email, REGEXP_EMAIL);
    }

    public boolean validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }

        return match(password, REGEXP_PASSWORD);
    }

    public boolean validateDateOfBirth(String dateOfBirth) {
        if (dateOfBirth == null || dateOfBirth.isEmpty()) {
            return false;
        }
        return match(dateOfBirth, REGEXP_DATE);
    }

    public boolean checkCoincidencePassword(String password, String passwordAgain) {
        if(password == null || passwordAgain == null) {
            return false;
        }

        return password.equals(passwordAgain);
    }

    private static boolean match(String data, String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(data);

        return matcher.find();
    }
}

