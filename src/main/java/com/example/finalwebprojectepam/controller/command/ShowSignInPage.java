package com.example.finalwebprojectepam.controller.command;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

import static com.example.finalwebprojectepam.controller.AttributeName.LAST_PAGE;
import static com.example.finalwebprojectepam.controller.PagePath.SIGN_IN_PAGE;

public class ShowSignInPage implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        request.getSession().setAttribute(LAST_PAGE, SIGN_IN_PAGE);
        return new Router(SIGN_IN_PAGE, Router.RouterType.REDIRECT);
    }
}
