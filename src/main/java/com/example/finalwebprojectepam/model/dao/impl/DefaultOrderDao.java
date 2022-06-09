package com.example.finalwebprojectepam.model.dao.impl;

import com.example.finalwebprojectepam.exception.DaoException;
import com.example.finalwebprojectepam.model.dao.OrderDao;
import com.example.finalwebprojectepam.model.entity.Medicine;
import com.example.finalwebprojectepam.model.entity.Order;
import com.example.finalwebprojectepam.model.mapper.impl.OrderRowMapper;
import com.example.finalwebprojectepam.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.example.finalwebprojectepam.controller.ParameterName.*;

public class DefaultOrderDao extends BaseDao<Order, String> implements OrderDao {
    private static final String OM_MEDICINE_ID = "om_medicine_id";
    private static final String SQL_INSERT_ORDER =
            "INSERT INTO orders (order_id, order_client_id, order_state_id) values(?, ?,?)";
    private static final String SQL_INSERT_MEDICINE_IN_ORDER = "INSERT INTO orders_medicines (om_medicine_id, om_order_id, om_medicine_quantity) VALUES(?, ?, ?)";
    private static final Integer ONE_UPDATED = 1;
    public static final String SQL_SELECT_ORDER_BY_CLIENT_ID = "SELECT order_state_id, order_id, order_state FROM orders " +
            "INNER JOIN order_state ON ostate_id = order_state_id WHERE order_client_id = ?";
    private static final String SQL_SELECT_ORDER_MEDICINE_BY_ORDER_ID = "SELECT om_medicine_quantity, om_medicine_id FROM orders_medicines WHERE om_order_id = ?";

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Optional<Order> findById(String id) {
        return null;
    }

    @Override
    public boolean createOrder(Order order) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ORDER);) {
            statement.setString(1, order.getOrderId());
            statement.setLong(2, order.getClientId());
            statement.setLong(3, order.getOrderState().getOrderStateId());

            return statement.executeUpdate() == ONE_UPDATED;
        } catch (SQLException sqlException) {
            throw new DaoException("Adding user exception " + sqlException);
        }
    }

    @Override
    public boolean insertMedicines(long medicineId, String orderId, int quantity) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_MEDICINE_IN_ORDER);) {
            statement.setLong(1, medicineId);
            statement.setString(2, orderId);
            statement.setInt(3, quantity);

            return statement.executeUpdate() == ONE_UPDATED;

        } catch (SQLException sqlException) {
            throw new DaoException("Adding user exception " + sqlException);
        }
    }

    @Override
    public List<Order> findOrderByClientId(long clientId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ORDER_BY_CLIENT_ID);) {
            statement.setLong(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();

            while (resultSet.next()) {
                Order order = OrderRowMapper.getInstance().mapRow(resultSet);
                orders.add(order);
            }

            return orders;
        } catch (SQLException sqlException) {
            throw new DaoException("Adding user exception " + sqlException);
        }
    }

    @Override
    public Map<Medicine, Integer> findOrderMedicinesByOrderId(String orderId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ORDER_MEDICINE_BY_ORDER_ID);) {
            statement.setString(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            Map<Medicine, Integer> medicines = new HashMap<>();
            DefaultMedicineDao medicineDao = new DefaultMedicineDao();

            while (resultSet.next()) {
                int quantity = resultSet.getInt(OM_MEDICINE_QUANTITY);
                long medicineId = resultSet.getLong(OM_MEDICINE_ID);
                Medicine medicine = medicineDao.findById(medicineId).get();
                medicines.put(medicine, quantity);
            }

            return medicines;
        } catch (SQLException sqlException) {
            throw new DaoException("Adding user exception " + sqlException);
        }
    }


}
