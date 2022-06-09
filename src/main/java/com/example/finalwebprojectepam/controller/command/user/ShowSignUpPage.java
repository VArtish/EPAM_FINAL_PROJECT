package com.example.finalwebprojectepam.controller.command.user;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.controller.command.Command;
import com.example.finalwebprojectepam.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

import static com.example.finalwebprojectepam.controller.AttributeName.LAST_PAGE;
import static com.example.finalwebprojectepam.controller.PagePath.SIGN_UP_PAGE;

public class ShowSignUpPage implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        request.getSession().setAttribute(LAST_PAGE, SIGN_UP_PAGE);
        return new Router(SIGN_UP_PAGE, Router.RouterType.REDIRECT);
    }
}
