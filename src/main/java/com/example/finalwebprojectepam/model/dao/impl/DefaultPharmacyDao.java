package com.example.finalwebprojectepam.model.dao.impl;

import com.example.finalwebprojectepam.exception.DaoException;
import com.example.finalwebprojectepam.model.dao.PharmacyDao;
import com.example.finalwebprojectepam.model.entity.Pharmacy;
import com.example.finalwebprojectepam.model.entity.User;
import com.example.finalwebprojectepam.model.mapper.impl.PharmacyRowMapper;
import com.example.finalwebprojectepam.model.mapper.impl.UserRowMapper;
import com.example.finalwebprojectepam.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultPharmacyDao extends BaseDao<Pharmacy, Long> implements PharmacyDao {
    private static final String SQL_SELECT_ALL_PHARMACY = "SELECT pharmacy_address, pharmacy_name, pharmacy_id FROM pharmacies ";
    private static final String SQL_SELECT_PHARMACY_BY_ID = SQL_SELECT_ALL_PHARMACY + "WHERE pharmacy_id = ?";

    @Override
    public List<Pharmacy> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_PHARMACY)) {

            ResultSet resultSet = statement.executeQuery();
            List<Pharmacy> pharmacies = new ArrayList<>();

            while (resultSet.next()) {
                Pharmacy pharmacy = PharmacyRowMapper.getInstance().mapRow(resultSet);
                pharmacies.add(pharmacy);
            }

            return pharmacies;

        } catch (SQLException e) {
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        }
    }

    @Override
    public Optional<Pharmacy> findById(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_PHARMACY_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Pharmacy pharmacy = null;

            while (resultSet.next()) {
                pharmacy = PharmacyRowMapper.getInstance().mapRow(resultSet);
            }

            return Optional.of(pharmacy);

        } catch (SQLException e) {
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        }
    }
}
