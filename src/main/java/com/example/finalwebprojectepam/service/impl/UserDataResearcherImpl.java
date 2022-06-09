package com.example.finalwebprojectepam.service.impl;

import com.example.finalwebprojectepam.service.UserDataResearcher;
import com.example.finalwebprojectepam.util.validator.UserValidator;
import com.example.finalwebprojectepam.util.validator.impl.UserValidatorImpl;

import java.util.Map;

import static com.example.finalwebprojectepam.controller.ParameterName.*;
import static com.example.finalwebprojectepam.controller.ParameterName.INCORRECT_PASSWORD;
import static com.example.finalwebprojectepam.controller.PropertyKey.*;

public class UserDataResearcherImpl implements UserDataResearcher {
    private static UserDataResearcherImpl instance;

    private UserDataResearcherImpl(){
    }

    public static UserDataResearcherImpl getInstance() {
        if(instance == null) {
            instance = new UserDataResearcherImpl();
        }

        return instance;
    }

    @Override
    public boolean researchLoginData(Map<String, String> userData) {
        boolean result = true;
        UserValidator validator = UserValidatorImpl.getInstance();
        String login = userData.get(USER_LOGIN);
        String password = userData.get(USER_PASSWORD);


        if (!validator.validateLogin(login)) {
            userData.put(INCORRECT_LOGIN, INCORRECT_LOGIN_MESSAGE);
            result = false;
        }

        if (!validator.validatePassword(password)) {
            userData.put(INCORRECT_PASSWORD, INCORRECT_PASSWORD_MESSAGE);
            result = false;
        }

        return result;
    }

    @Override
    public boolean researchRegisterData(Map<String, String> userData) {
        boolean result = true;
        UserValidator validator = UserValidatorImpl.getInstance();
        String name = userData.get(USER_NAME);
        String surname = userData.get(USER_SURNAME);
        String email = userData.get(USER_EMAIL);
        String login = userData.get(USER_LOGIN);
        String password = userData.get(USER_PASSWORD);
        String passwordAgain = userData.get(USER_PASSWORD_AGAIN);
        String dateOfBirth = userData.get(USER_DATE_OF_BIRTH);

        if (!validator.validateLogin(login)) {
            userData.put(INCORRECT_LOGIN, INCORRECT_LOGIN_MESSAGE);
            result = false;
        }

        if (!validator.validateName(name)) {
            userData.put(INCORRECT_NAME, INCORRECT_NAME_MESSAGE);
            result = false;
        }

        if (!validator.validateSurname(surname)) {
            userData.put(INCORRECT_SURNAME, INCORRECT_SURNAME_MESSAGE);
        }

        if (!validator.validatePassword(password)) {
            userData.put(INCORRECT_PASSWORD, INCORRECT_PASSWORD_MESSAGE);
            result = false;
        }

        if (!validator.checkCoincidencePassword(password, passwordAgain)) {
            userData.put(INCORRECT_PASSWORD_AGAIN, INCORRECT_PASSWORD_AGAIN_MESSAGE);
            result = false;
        }

        if (!validator.validateEmail(email)) {
            userData.put(INCORRECT_EMAIL, INCORRECT_EMAIL_MESSAGE);
            result = false;
        }

        if (!validator.validateDateOfBirth(dateOfBirth)) {
            userData.put(INCORRECT_DATE_OF_BIRTH, INCORRECT_DATE_OF_BIRTH_MESSAGE);
            result = false;
        }

        return result;
    }

    @Override
    public boolean researchUserProfileData(Map<String, String> userData) {
        boolean result = true;
        UserValidator validator = UserValidatorImpl.getInstance();
        String name = userData.get(USER_NAME);
        String surname = userData.get(USER_SURNAME);
        String email = userData.get(USER_EMAIL);
        String login = userData.get(USER_LOGIN);
        String dateOfBirth = userData.get(USER_DATE_OF_BIRTH);

        if (!validator.validateName(name)) {
            userData.put(INCORRECT_NAME, INCORRECT_NAME_MESSAGE);
            result = false;
        }

        if (!validator.validateSurname(surname)) {
            userData.put(INCORRECT_SURNAME, INCORRECT_SURNAME_MESSAGE);
            result = false;
        }

        if (!validator.validateEmail(email)) {
            userData.put(INCORRECT_EMAIL, INCORRECT_EMAIL_MESSAGE);
            result = false;
        }

        if(!validator.validateLogin(login)) {
            userData.put(INCORRECT_LOGIN, INCORRECT_LOGIN_MESSAGE);
            result = false;
        }

        if(!validator.validateDateOfBirth(dateOfBirth)) {
            userData.put(INCORRECT_DATE_OF_BIRTH, INCORRECT_DATE_OF_BIRTH_MESSAGE);
            result = false;
        }

        return result;
    }

    @Override
    public boolean researchChangePasswordData(Map<String, String> userChangePasswordData) {
        boolean result = true;
        UserValidator validator = UserValidatorImpl.getInstance();
        String newPassword = userChangePasswordData.get(NEW_PASSWORD);
        String oldPassword = userChangePasswordData.get(OLD_PASSWORD);
        String newPasswordAgain = userChangePasswordData.get(NEW_PASSWORD_AGAIN);

        if (!validator.validatePassword(newPassword)) {
            userChangePasswordData.put(INCORRECT_NEW_PASSWORD, INCORRECT_NEW_PASSWORD_MESSAGE);
            result = false;
        }

        if (!validator.validatePassword(newPasswordAgain)) {
            userChangePasswordData.put(INCORRECT_REPEAT_NEW_PASSWORD, INCORRECT_REPEAT_NEW_PASSWORD_MESSAGE);
            result = false;
        }

        if (!validator.validatePassword(oldPassword)) {
            userChangePasswordData.put(INCORRECT_OLD_PASSWORD, INCORRECT_OLD_PASSWORD_MESSAGE);
            result = false;
        }

        if(!validator.checkCoincidencePassword(newPassword, newPasswordAgain)) {
            userChangePasswordData.put(INCORRECT_REPEAT_NEW_PASSWORD, INCORRECT_REPEAT_NEW_PASSWORD_MESSAGE);
            result = false;
        }

        return result;
    }
}
