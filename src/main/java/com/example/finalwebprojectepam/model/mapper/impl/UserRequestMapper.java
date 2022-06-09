package com.example.finalwebprojectepam.model.mapper.impl;

import com.example.finalwebprojectepam.model.mapper.CustomRequestMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.example.finalwebprojectepam.controller.ParameterName.*;

public class UserRequestMapper implements CustomRequestMapper {
    private static UserRequestMapper instance;

    private UserRequestMapper() {
    }

    public static UserRequestMapper getInstance() {
        if (instance == null) {
            instance = new UserRequestMapper();
        }
        return instance;
    }

    @Override
    public Map<String, String> mapRequest(HttpServletRequest request) {
        Map<String, String> userData = new HashMap<>();
        String login = request.getParameter(LOGIN);
        if(checkData(login)) {
            userData.put(USER_LOGIN, login);
        }

        String name = request.getParameter(NAME);
        if(checkData(name)) {
            userData.put(USER_NAME, name);
        }

        String surname = request.getParameter(SURNAME);
        if(checkData(surname))
        userData.put(USER_SURNAME, surname);

        String password = request.getParameter(PASSWORD);
        if(checkData(password)) {
            userData.put(USER_PASSWORD, password);
        }

        String passwordAgain = request.getParameter(PASSWORD_AGAIN);
        if(checkData(passwordAgain)) {
            userData.put(USER_PASSWORD_AGAIN, passwordAgain);
        }

        String email = request.getParameter(EMAIL);
        if(checkData(email)) {
            userData.put(USER_EMAIL, email);
        }

        String dateOfBirth = request.getParameter(DATE_OF_BIRTH);
        if(checkData(dateOfBirth)) {
            userData.put(USER_DATE_OF_BIRTH, dateOfBirth);
        }
        return userData;
    }

    private boolean checkData(String data) {
        return (data != null && !data.isEmpty()) ? true : false;
    }
}