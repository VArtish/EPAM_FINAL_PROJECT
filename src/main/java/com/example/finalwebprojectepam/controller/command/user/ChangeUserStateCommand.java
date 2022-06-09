package com.example.finalwebprojectepam.controller.command.user;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.controller.command.Command;
import com.example.finalwebprojectepam.exception.CommandException;
import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.entity.type.UserRole;
import com.example.finalwebprojectepam.model.entity.type.UserState;
import com.example.finalwebprojectepam.service.UserService;
import com.example.finalwebprojectepam.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static com.example.finalwebprojectepam.controller.PagePath.USER_LIST;
import static com.example.finalwebprojectepam.controller.ParameterName.*;

public class ChangeUserStateCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        String userLogin = request.getParameter(USERNAME);
        String userNewState = request.getParameter(NEW_STATE);
        try {
            UserState userState = UserState.valueOf(userNewState);
            userService.updateUserState(userLogin, userState);
        } catch (ServiceException serviceException) {
            throw new CommandException(serviceException);
        }

        return new Router(USER_LIST);
    }
}
