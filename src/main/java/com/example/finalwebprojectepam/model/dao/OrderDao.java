package com.example.finalwebprojectepam.model.dao;

import com.example.finalwebprojectepam.exception.DaoException;
import com.example.finalwebprojectepam.model.entity.Medicine;
import com.example.finalwebprojectepam.model.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    boolean createOrder(Order order) throws DaoException;

    boolean insertMedicines(long medicineId, String orderId, int quantity) throws DaoException;

    List<Order> findOrderByClientId(long clientId) throws DaoException;

    Map<Medicine, Integer> findOrderMedicinesByOrderId(String orderId) throws DaoException;
}
