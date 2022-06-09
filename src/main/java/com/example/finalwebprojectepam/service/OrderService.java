package com.example.finalwebprojectepam.service;

import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.entity.Medicine;
import com.example.finalwebprojectepam.model.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    boolean createNewOrder(long clientId, Map<Medicine, Integer> medicines) throws ServiceException;

    List<Order> findListOrderByClientId(long clientId) throws ServiceException;
}
