package com.example.finalwebprojectepam.controller.command.user;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.controller.command.Command;
import com.example.finalwebprojectepam.exception.CommandException;
import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.entity.User;
import com.example.finalwebprojectepam.service.UserService;
import com.example.finalwebprojectepam.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.example.finalwebprojectepam.controller.PagePath.USER_LIST;
import static com.example.finalwebprojectepam.controller.ParameterName.USERS;

public class ShowUserListCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        try {
            List<User> users = userService.getUserList();
            request.setAttribute(USERS, users);
            return new Router(USER_LIST);
        } catch (ServiceException serviceException) {
            throw new CommandException(serviceException);
        }
    }
}
