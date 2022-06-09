package com.example.finalwebprojectepam.controller.command;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.exception.CommandException;
import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.entity.User;
import com.example.finalwebprojectepam.service.UserService;
import com.example.finalwebprojectepam.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.example.finalwebprojectepam.controller.AttributeName.LAST_PAGE;
import static com.example.finalwebprojectepam.controller.PagePath.USER_CABINET;
import static com.example.finalwebprojectepam.controller.ParameterName.*;

public class ShowUserCabinetCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String login = request.getSession().getAttribute(USER_LOGIN).toString();
        UserService userService = UserServiceImpl.getInstance();
        try {
            Optional<User> userOptional = userService.showUserCabinet(login);
            addUserDataToRequest(userOptional.get(), request);

        } catch(ServiceException serviceException) {
            throw new CommandException(serviceException);
        }
        request.getSession().setAttribute(LAST_PAGE, USER_CABINET);
        return new Router(USER_CABINET);
    }

    private void addUserDataToRequest(User user, HttpServletRequest request) {
        request.setAttribute(USER_EMAIL, user.getEmail());
        request.setAttribute(USER_DATE_OF_BIRTH, user.getDateOfBirth());
        request.setAttribute(USER_NAME, user.getName());
        request.setAttribute(USER_SURNAME, user.getSurname());
        request.setAttribute(USER_LOGIN, user.getLogin());
    }
}
