package com.example.finalwebprojectepam.controller.command.user;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.controller.command.Command;
import com.example.finalwebprojectepam.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

import static com.example.finalwebprojectepam.controller.PagePath.INDEX_PAGE;

public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        request.getSession().invalidate();
        return new Router(INDEX_PAGE, Router.RouterType.REDIRECT);
    }
}
