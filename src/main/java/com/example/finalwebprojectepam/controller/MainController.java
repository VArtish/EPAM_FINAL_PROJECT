package com.example.finalwebprojectepam.controller;

import com.example.finalwebprojectepam.controller.command.Command;
import com.example.finalwebprojectepam.controller.command.CommandType;
import com.example.finalwebprojectepam.model.pool.ConnectionPool;
import com.example.finalwebprojectepam.exception.CommandException;

import java.io.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static com.example.finalwebprojectepam.controller.PagePath.ERROR_505_PAGE;

@WebServlet(name = "MainController", value = "/controller")
public class MainController extends HttpServlet {

    @Override
    public void init(ServletConfig config) {
        ConnectionPool.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String commandStr = request.getParameter("command");
        Command command = CommandType.define(commandStr);
        String page = null;

        try {
            Router router = command.execute(request);
            if (router.getRouterType() == Router.RouterType.FORWARD) {
                request.getRequestDispatcher(router.getPagePath()).forward(request, response);
            } else if (router.getRouterType() == Router.RouterType.REDIRECT) {
                response.sendRedirect(router.getPagePath());
            }
        } catch (CommandException e) {
            request.setAttribute("error_msg", e.getMessage());
            request.getRequestDispatcher(ERROR_505_PAGE).forward(request, response);
        }
    }

    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }
}