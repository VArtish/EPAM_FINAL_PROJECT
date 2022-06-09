package com.example.finalwebprojectepam.controller.command;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.exception.CommandException;
import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.entity.Order;
import com.example.finalwebprojectepam.service.OrderService;
import com.example.finalwebprojectepam.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static com.example.finalwebprojectepam.controller.AttributeName.LAST_PAGE;
import static com.example.finalwebprojectepam.controller.AttributeName.ORDERS;
import static com.example.finalwebprojectepam.controller.PagePath.ORDER_PAGE;
import static com.example.finalwebprojectepam.controller.ParameterName.USER_ID;

public class ShowOrderListCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        OrderService orderService = OrderServiceImpl.getInstance();
        long userId = (Long) request.getSession().getAttribute(USER_ID);
        try {
            List<Order> orders = orderService.findListOrderByClientId(userId);
            request.setAttribute(ORDERS, orders);
            request.getSession().setAttribute(LAST_PAGE, ORDER_PAGE);
            return new Router(ORDER_PAGE);

        } catch (ServiceException serviceException) {
            throw new CommandException(serviceException);
        }
    }
}
