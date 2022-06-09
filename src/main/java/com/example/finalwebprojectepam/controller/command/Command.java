package com.example.finalwebprojectepam.controller.command;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}
