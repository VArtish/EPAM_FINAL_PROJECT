package com.example.finalwebprojectepam.controller.command;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

import static com.example.finalwebprojectepam.controller.PagePath.MAIN_PAGE;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(MAIN_PAGE);
    }
}
