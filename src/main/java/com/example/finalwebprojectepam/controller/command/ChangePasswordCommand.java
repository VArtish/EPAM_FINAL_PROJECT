package com.example.finalwebprojectepam.controller.command;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.exception.CommandException;
import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.entity.User;
import com.example.finalwebprojectepam.service.UserService;
import com.example.finalwebprojectepam.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.example.finalwebprojectepam.controller.AttributeName.LAST_PAGE;
import static com.example.finalwebprojectepam.controller.PagePath.USER_CABINET;
import static com.example.finalwebprojectepam.controller.ParameterName.*;
import static com.example.finalwebprojectepam.controller.PropertyKey.*;

public class ChangePasswordCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        Map<String, String> userData = mapRequest(request);
        String strUserId = request.getSession().getAttribute(USER_ID).toString();
        String login = request.getSession().getAttribute(USER_LOGIN).toString();
        userData.put(USER_ID, strUserId);
        Router router = new Router(USER_CABINET);
        try {
            boolean result = userService.changeUserPassword(userData);
            if(result) {
                request.setAttribute(SUCCESSFUL_CHANGE_PASSWORD, SUCCESSFUL_CHANGE_PASSWORD_MESSAGE);
            } else {
                addIncorrectDataToRequest(userData, request);
            }
            //fixme?
            Optional<User> userOptional = userService.showUserCabinet(login);
            addUserDataToRequest(userOptional.get(), request);
            //fixme?
            request.getSession().setAttribute(LAST_PAGE, USER_CABINET);
            return router;
        } catch (ServiceException serviceException) {
            throw new CommandException(serviceException);
        }

    }

    private void addUserDataToRequest(User user, HttpServletRequest request) {
        request.setAttribute(USER_EMAIL, user.getEmail());
        request.setAttribute(USER_DATE_OF_BIRTH, user.getDateOfBirth());
        request.setAttribute(USER_NAME, user.getName());
        request.setAttribute(USER_SURNAME, user.getSurname());
        request.setAttribute(USER_LOGIN, user.getLogin());
    }

    private void addIncorrectDataToRequest(Map<String, String> userData, HttpServletRequest request) {
        for(String key: userData.keySet()) {
            switch(key) {
                case INCORRECT_OLD_PASSWORD -> {
                    request.setAttribute(INCORRECT_OLD_PASSWORD, INCORRECT_OLD_PASSWORD_MESSAGE);
                    break;
                }
                case INCORRECT_NEW_PASSWORD -> {
                    request.setAttribute(INCORRECT_NEW_PASSWORD, INCORRECT_REPEAT_NEW_PASSWORD_MESSAGE);
                    break;
                }
                case INCORRECT_REPEAT_NEW_PASSWORD -> {
                    request.setAttribute(INCORRECT_REPEAT_NEW_PASSWORD, INCORRECT_REPEAT_NEW_PASSWORD_MESSAGE);
                    break;
                }
            }
        }
    }

    private Map<String, String> mapRequest(HttpServletRequest request) {
        Map<String, String> userData = new HashMap<>();
        String newPassword = request.getParameter(NEW_PASSWORD);
        String oldPassword = request.getParameter(OLD_PASSWORD);
        String repeatNewPassword = request.getParameter(NEW_PASSWORD_AGAIN);

        userData.put(NEW_PASSWORD, newPassword);
        userData.put(OLD_PASSWORD, oldPassword);
        userData.put(NEW_PASSWORD_AGAIN, repeatNewPassword);

        return userData;
    }
}
