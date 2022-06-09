package com.example.finalwebprojectepam.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

import static com.example.finalwebprojectepam.controller.AttributeName.LOCALE_PARAM;

@WebFilter(filterName = "LocaleFilter", urlPatterns = {"/*"})
public class LocaleFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getSession().getAttribute(LOCALE_PARAM) == null) {
            Locale userSystemDefaultLocale = httpRequest.getLocale();
            httpRequest.getSession().setAttribute(LOCALE_PARAM, userSystemDefaultLocale);
        }

        chain.doFilter(request, response);
    }
}
