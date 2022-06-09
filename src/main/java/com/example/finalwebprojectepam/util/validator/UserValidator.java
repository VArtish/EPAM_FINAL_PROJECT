package com.example.finalwebprojectepam.util.validator;

public interface UserValidator {
    boolean validatePassword(String password);

    boolean validateLogin(String login);

    boolean validateEmail(String email);

    boolean validateDateOfBirth(String dateOfBirth);

    boolean validateName(String name);

    boolean validateSurname(String surname);

    boolean checkCoincidencePassword(String password, String passwordAgain);
}
