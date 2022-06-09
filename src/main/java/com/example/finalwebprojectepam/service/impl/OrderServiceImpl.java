package com.example.finalwebprojectepam.service.impl;

import com.example.finalwebprojectepam.exception.DaoException;
import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.dao.impl.DefaultOrderDao;
import com.example.finalwebprojectepam.model.entity.Medicine;
import com.example.finalwebprojectepam.model.entity.Order;
import com.example.finalwebprojectepam.model.entity.type.OrderState;
import com.example.finalwebprojectepam.service.MediaService;
import com.example.finalwebprojectepam.service.OrderService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {
    private static OrderServiceImpl instance;

    public static OrderServiceImpl getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }

        return instance;
    }

    @Override
    public boolean createNewOrder(long clientId, Map<Medicine, Integer> medicines) throws ServiceException {
        DefaultOrderDao orderDao = new DefaultOrderDao();
        Order order = buildOrder(clientId);
        try {
            boolean created = orderDao.createOrder(order);
            for (Medicine medicine : medicines.keySet()) {
                boolean check = orderDao.insertMedicines(medicine.getMedicineId(), order.getOrderId(), medicines.get(medicine));
                if (!check) {
                    created = false;
                }
            }
            return created;
        } catch (DaoException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public List<Order> findListOrderByClientId(long clientId) throws ServiceException {
        DefaultOrderDao orderDao = new DefaultOrderDao();
        MediaService mediaService = MediaServiceImpl.getInstance();
        try {
            List<Order> orders = orderDao.findOrderByClientId(clientId);
            for (Order order : orders) {
                Map<Medicine, Integer> medicines = orderDao.findOrderMedicinesByOrderId(order.getOrderId());
                for (Medicine medicine : medicines.keySet()) {
                    String imageKey = medicine.getImageLink();
                    medicine.setImageLink(mediaService.getPreviewPhoto(imageKey));
                }
                order.setMedicines(medicines);
            }

            return orders;
        } catch (DaoException daoException) {
            throw new ServiceException(daoException);
        }
    }

    private Order buildOrder(long clientId) {
        Order order = new Order();
        order.setOrderState(OrderState.EXPECT);
        order.setClientId(clientId);
        order.setOrderId(UUID.randomUUID().toString());
        return order;
    }
}
