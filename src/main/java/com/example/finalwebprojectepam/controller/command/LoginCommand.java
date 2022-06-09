package com.example.finalwebprojectepam.controller.command;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.exception.CommandException;
import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.entity.User;
import com.example.finalwebprojectepam.model.entity.type.UserState;
import com.example.finalwebprojectepam.model.mapper.impl.UserRequestMapper;
import com.example.finalwebprojectepam.service.UserService;
import com.example.finalwebprojectepam.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

import static com.example.finalwebprojectepam.controller.PagePath.*;
import static com.example.finalwebprojectepam.controller.ParameterName.*;
import static com.example.finalwebprojectepam.controller.PropertyKey.*;

public class LoginCommand implements Command{

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserRequestMapper requestMapper = UserRequestMapper.getInstance();
        Map<String, String> userData = requestMapper.mapRequest(request);
        UserService userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        Router router = new Router(SIGN_IN_PAGE);
        try{
            Optional<User> optionalUser = userService.logIn(userData);
            if(optionalUser.isPresent()) {
                User currentUser = optionalUser.get();
                session.setAttribute(USER_ROLE, currentUser.getUserRole());
                session.setAttribute(USER_LOGIN, currentUser.getLogin());
                session.setAttribute(USER_ID, currentUser.getId());
                router.setRouterType(Router.RouterType.REDIRECT);
                if(currentUser.getUserState() == UserState.DISABLED) {
                    router.setPagePath(BLOCKED_PAGE);
                }
                session.setAttribute(USER_STATE, currentUser.getUserState());
                router.setPagePath(MAIN_PAGE);
            } else {
                addIncorrectMessageToRequest(request, userData);
                request.setAttribute(USER_LOGIN, userData.get(USER_LOGIN));
            }
        } catch(ServiceException e) {
            throw new CommandException(e);
        }

        return router;
    }

    private void addIncorrectMessageToRequest(HttpServletRequest request, Map<String, String> userData) {
        for (String key : userData.keySet()) {
            switch (key) {
                case INCORRECT_LOGIN: {
                    request.setAttribute(INCORRECT_LOGIN, INCORRECT_LOGIN_MESSAGE);
                    break;
                }
                case INCORRECT_PASSWORD: {
                    request.setAttribute(INCORRECT_PASSWORD, INCORRECT_PASSWORD_MESSAGE);
                    break;
                }
                case INCORRECT_SIGN_IN_DATA: {
                    request.setAttribute(INCORRECT_SIGN_IN_DATA, INCORRECT_SIGN_IN_MESSAGE);
                    break;
                }
            }
        }
    }
}
