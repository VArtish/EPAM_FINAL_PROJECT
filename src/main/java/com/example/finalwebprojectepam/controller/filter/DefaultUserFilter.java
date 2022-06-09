package com.example.finalwebprojectepam.controller.filter;

import com.example.finalwebprojectepam.model.entity.type.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.example.finalwebprojectepam.controller.ParameterName.USER_ROLE;

@WebFilter(filterName = "DefaultUserFilter", urlPatterns = {"/*"})
public class DefaultUserFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        UserRole userRole = (UserRole) httpServletRequest.getSession().getAttribute(USER_ROLE);
        if(userRole == null) {
            httpServletRequest.getSession().setAttribute(USER_ROLE, UserRole.GUEST);
        }

        chain.doFilter(request, response);
    }
}
