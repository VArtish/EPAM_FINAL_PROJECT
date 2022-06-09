package com.example.finalwebprojectepam.controller.command.user;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.controller.command.Command;
import com.example.finalwebprojectepam.exception.CommandException;
import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.entity.User;
import com.example.finalwebprojectepam.model.entity.type.UserRole;
import com.example.finalwebprojectepam.model.entity.type.UserState;
import com.example.finalwebprojectepam.model.mapper.impl.UserRequestMapper;
import com.example.finalwebprojectepam.service.UserService;
import com.example.finalwebprojectepam.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

import static com.example.finalwebprojectepam.controller.PagePath.USER_CABINET;
import static com.example.finalwebprojectepam.controller.ParameterName.*;
import static com.example.finalwebprojectepam.controller.PropertyKey.*;

public class UpdateUserProfileCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        UserRole userRole = (UserRole) session.getAttribute(USER_ROLE);
        UserState userState = (UserState) session.getAttribute(USER_STATE);
        String strUserId = session.getAttribute(USER_ID).toString();
        UserRequestMapper userMapper = UserRequestMapper.getInstance();
        Map<String, String> updateUserData = userMapper.mapRequest(request);
        updateUserData.put(USER_ROLE, userRole.name());
        updateUserData.put(USER_STATE, userState.name());
        updateUserData.put(USER_ID, strUserId);
        UserService userService = UserServiceImpl.getInstance();

        try{
            Optional<User> updateOptionalUser = userService.updateUserProfile(updateUserData);
            if(updateOptionalUser.isEmpty()) {
                addIncorrectMessageToRequest(request, updateUserData);
            } else {
                session.setAttribute(USER_LOGIN, updateOptionalUser.get().getLogin());
            }
            addDataToRequest(request, updateUserData);

        } catch(ServiceException serviceException) {
            throw new CommandException(serviceException);
        }

        return new Router(USER_CABINET);
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
                case INCORRECT_EMAIL: {
                    request.setAttribute(INCORRECT_EMAIL, INCORRECT_EMAIL_MESSAGE);
                    break;
                }
                case INCORRECT_LOGIN : {
                    request.setAttribute(INCORRECT_LOGIN, INCORRECT_LOGIN_MESSAGE);
                    break;
                }
                case INCORRECT_DATE_OF_BIRTH: {
                    request.setAttribute(INCORRECT_DATE_OF_BIRTH, INCORRECT_DATE_OF_BIRTH_MESSAGE);
                    break;
                }
                case USER_LOGIN_EXIST: {
                    request.setAttribute(USER_LOGIN_EXIST, LOGIN_EXIST_MESSAGE);
                    break;
                }
                case USER_EMAIL_EXIST: {
                    request.setAttribute(USER_EMAIL_EXIST, EMAIL_EXIST_MESSAGE);
                    break;
                }
            }
        }
    }

    private void addDataToRequest(HttpServletRequest request, Map<String, String> userData) {
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
}
