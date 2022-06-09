package com.example.finalwebprojectepam.controller.command;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

import static com.example.finalwebprojectepam.controller.AttributeName.LAST_PAGE;
import static com.example.finalwebprojectepam.controller.PagePath.BASKET_PAGE;

public class ShowBasketCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        request.getSession().setAttribute(LAST_PAGE, BASKET_PAGE);
        return new Router(BASKET_PAGE);
    }
}
