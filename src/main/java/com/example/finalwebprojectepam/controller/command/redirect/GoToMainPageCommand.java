package com.example.finalwebprojectepam.controller.command.redirect;

import com.example.finalwebprojectepam.controller.PagePath;
import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.controller.command.Command;
import com.example.finalwebprojectepam.service.impl.MediaServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class GoToMainPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        MediaServiceImpl mediaService = new MediaServiceImpl();
        String url = mediaService.getPreviewPhoto("wfjshbaojcuab91pkbb2");
        request.setAttribute("path", url);
        return new Router(PagePath.MAIN_PAGE);
    }


}
