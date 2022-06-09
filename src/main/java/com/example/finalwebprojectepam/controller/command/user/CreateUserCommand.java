package com.example.finalwebprojectepam.controller.command.user;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.controller.command.Command;
import com.example.finalwebprojectepam.exception.CommandException;
import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.entity.type.UserRole;
import com.example.finalwebprojectepam.model.entity.type.UserState;
import com.example.finalwebprojectepam.model.mapper.CustomRequestMapper;
import com.example.finalwebprojectepam.model.mapper.impl.UserRequestMapper;
import com.example.finalwebprojectepam.service.UserService;
import com.example.finalwebprojectepam.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.example.finalwebprojectepam.controller.AttributeName.SUCCESSFUL_REGISTRATION;
import static com.example.finalwebprojectepam.controller.PagePath.*;
import static com.example.finalwebprojectepam.controller.ParameterName.*;
import static com.example.finalwebprojectepam.controller.PropertyKey.*;

public class CreateUserCommand implements Command {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        CustomRequestMapper requestMapper = UserRequestMapper.getInstance();
        Map<String, String> userData = requestMapper.mapRequest(request);
        userData.put(USER_ROLE, UserRole.CUSTOMER.name());
        userData.put(USER_STATE, UserState.ENABLED.name());
        UserService userService = UserServiceImpl.getInstance();
        Router router = new Router();
        try {
            if (userService.registerUser(userData)) {
                request.getSession().setAttribute(SUCCESSFUL_REGISTRATION, true);
                router.setRouterType(Router.RouterType.REDIRECT);
                router.setPagePath(SIGN_IN_PAGE);
                // fixme sign_in
            } else {
                addIncorrectMessageToRequest(request, userData);
                addCorrectDataToRequest(request, userData);
                router.setPagePath(SIGN_UP_PAGE);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return router;
    }

    private void addCorrectDataToRequest(HttpServletRequest request, Map<String, String> userData) {
        for (String key : userData.keySet()) {
            switch (key) {
                case USER_NAME: {
                    request.setAttribute(USER_NAME, userData.get(key));
                    break;
                }
                case USER_SURNAME: {
                    request.setAttribute(USER_SURNAME, userData.get(key));
                    break;
                }
                case USER_EMAIL: {
                    request.setAttribute(USER_EMAIL, userData.get(key));
                    break;
                }
                case USER_LOGIN: {
                    request.setAttribute(USER_LOGIN, userData.get(key));
                    break;
                }
                case USER_DATE_OF_BIRTH: {
                    request.setAttribute(USER_DATE_OF_BIRTH, userData.get(key));
                    break;
                }
            }
        }
    }

    private void addIncorrectMessageToRequest(HttpServletRequest request, Map<String, String> userData) {
        for (String key : userData.keySet()) {
            switch (key) {
                case INCORRECT_NAME: {
                    request.setAttribute(INCORRECT_NAME, INCORRECT_NAME_MESSAGE);
                    break;
                }
                case INCORRECT_SURNAME: {
                    request.setAttribute(INCORRECT_SURNAME, INCORRECT_SURNAME_MESSAGE);
                    break;
                }
                case INCORRECT_LOGIN: {
                    request.setAttribute(INCORRECT_LOGIN, INCORRECT_LOGIN_MESSAGE);
                    break;
                }
                case INCORRECT_EMAIL: {
                    request.setAttribute(INCORRECT_EMAIL, INCORRECT_EMAIL_MESSAGE);
                    break;
                }
                case INCORRECT_PASSWORD: {
                    request.setAttribute(INCORRECT_PASSWORD, INCORRECT_PASSWORD_MESSAGE);
                    break;
                }
                case INCORRECT_PASSWORD_AGAIN: {
                    request.setAttribute(INCORRECT_PASSWORD_AGAIN, INCORRECT_PASSWORD_AGAIN_MESSAGE);
                    break;
                }
                case INCORRECT_DATE_OF_BIRTH: {
                    request.setAttribute(INCORRECT_DATE_OF_BIRTH, INCORRECT_DATE_OF_BIRTH_MESSAGE);
                    break;
                }
            }
        }
    }
}
