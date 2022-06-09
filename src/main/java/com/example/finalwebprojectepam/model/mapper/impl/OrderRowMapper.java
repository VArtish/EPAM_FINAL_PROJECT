package com.example.finalwebprojectepam.model.mapper.impl;

import com.example.finalwebprojectepam.exception.DaoException;
import com.example.finalwebprojectepam.model.entity.Order;
import com.example.finalwebprojectepam.model.entity.type.OrderState;
import com.example.finalwebprojectepam.model.mapper.CustomRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.finalwebprojectepam.controller.ParameterName.ORDER_ID;
import static com.example.finalwebprojectepam.controller.ParameterName.ORDER_STATE;

public class OrderRowMapper implements CustomRowMapper<Order> {
    private static OrderRowMapper instance;

    public static OrderRowMapper getInstance() {
        if (instance == null) {
            instance = new OrderRowMapper();
        }

        return instance;
    }

    @Override
    public Order mapRow(ResultSet resultSet) throws DaoException {
        try {
            String strOrderState = resultSet.getString(ORDER_STATE);
            String orderId = resultSet.getString(ORDER_ID);
            OrderState orderState = OrderState.valueOf(strOrderState);
            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderState(orderState);
            return order;
        } catch (SQLException sqlException) {
            throw new DaoException(sqlException);
        }
    }
}
