package com.example.finalwebprojectepam.controller.command.user;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Objects;

import static com.example.finalwebprojectepam.controller.AttributeName.LAST_PAGE;
import static com.example.finalwebprojectepam.controller.AttributeName.LOCALE_PARAM;
import static com.example.finalwebprojectepam.controller.PagePath.INDEX_PAGE;
import static com.example.finalwebprojectepam.controller.command.RequestParameter.NEW_LOCALE_PARAM;

public class ChangeLocaleCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Locale locale = request.getLocale();
        String newLocale = request.getParameter(NEW_LOCALE_PARAM);
        request.getSession().setAttribute(LOCALE_PARAM, newLocale);

        String lastPage = (String) request.getSession().getAttribute(LAST_PAGE);

        return new Router(Objects.requireNonNullElse(lastPage, INDEX_PAGE));
    }
}
//wrapper, стратегия, посетитель