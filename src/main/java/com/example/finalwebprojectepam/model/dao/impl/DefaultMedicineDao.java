package com.example.finalwebprojectepam.model.dao.impl;

import com.example.finalwebprojectepam.exception.DaoException;
import com.example.finalwebprojectepam.model.dao.EntityTransaction;
import com.example.finalwebprojectepam.model.dao.MedicineDao;
import com.example.finalwebprojectepam.model.entity.Medicine;
import com.example.finalwebprojectepam.model.mapper.impl.MedicineRowMapper;
import com.example.finalwebprojectepam.model.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.finalwebprojectepam.controller.AttributeName.MEDICINE_QUANTITY;

public class DefaultMedicineDao extends BaseDao<Medicine, Long> implements MedicineDao {
    private static final String SQL_INSERT_MEDICINE = "INSERT  INTO medicines (medicine_name, medicine_quantity, medicine_price, medicine_amount_in_plate, " +
            "medicine_amount_in_package, medicine_producing_country, medicine_ingredients, medicine_need_prescription, medicine_image_link, medicine_pharmacy_id) " +
            "VALUES(?, ?,?,?, ?, ?, ?, ?, ?, ?)";
    private static final int ONE_UPDATED = 1;
    public static final String SQL_FIND_COUNT_MEDICINE_LIKE = "SELECT COUNT(*) FROM medicines WHERE medicine_name LIKE";
    private static final String SQL_FIND_MEDICINE_QUANTITY_BY_ID = "SELECT medicine_quantity FROM medicines WHERE medicine_id = ?";
    private static final String SQL_SELECT_ALL_MEDICINE = "SELECT medicine_amount_in_package, medicine_amount_in_plate, medicine_need_prescription, " +
            "medicine_price, medicine_quantity, medicine_id, medicine_ingredients, medicine_producing_country, medicine_image_link, medicine_name, pharmacy_name, pharmacy_address, medicine_pharmacy_id FROM medicines " +
            "INNER JOIN pharmacies ON pharmacy_id = medicine_pharmacy_id ";
    private static final String SQL_FIND_COUNT_ALL_MEDICINE = "SELECT COUNT(*) FROM medicines ";
    private static final String SQL_SELECT_MEDICINE_BY_ID = "SELECT medicine_amount_in_package, medicine_amount_in_plate, medicine_need_prescription, " +
            "medicine_price, medicine_quantity, medicine_ingredients, medicine_producing_country, medicine_name, medicine_id, medicine_pharmacy_id, medicine_image_link, pharmacy_name, pharmacy_address FROM medicines INNER JOIN pharmacies ON pharmacy_id = medicine_pharmacy_id WHERE medicine_id = ?";
    private static final String ORDER_BY = "ORDER BY ";
    private static final String SQL_SELECT_MEDICINE_BY_PRODUCING_COUNTRY = "SELECT medicine_amount_in_package, medicine_amount_in_plate, medicine_need_prescription, " +
            "medicine_price, medicine_quantity, medicine_ingredients, medicine_id, medicine_pharmacy_id, medicine_name, pharmacy_name, pharmacy_address FROM medicines" +
            "WHERE medicine_producing_country = ? " +
            "INNER JOIN pharmacies ON pharmacy_id = medicine_pharmacy_id";
    private static final String SQL_SELECT_MEDICINE_BY_NEED_PRESCRIPTION = "SELECT medicine_amount_in_package, medicine_amount_in_plate, medicine_id " +
            "medicine_price, medicine_quantity, medicine_ingredients, medicine_producing_country, medicine_name, pharmacy_name, pharmacy_address, medicine_pharmacy_id FROM medicines" +
            "WHERE medicine_need_prescription = ? " +
            "INNER JOIN pharmacies ON pharmacy_id = medicine_pharmacy_id";
    private static final String SQL_UPDATE_MEDICINE_QUANTITY = "UPDATE medicines SET medicine_quantity = (?) WHERE medicine_id = ?";

    @Override
    public List<Medicine> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_MEDICINE)) {

            ResultSet resultSet = statement.executeQuery();
            List<Medicine> medicines = new ArrayList<>();
            MedicineRowMapper rowMapper = MedicineRowMapper.getInstance();

            while (resultSet.next()) {
                Medicine medicine = rowMapper.mapRow(resultSet);
                medicines.add(medicine);
            }

            return medicines;

        } catch (SQLException e) {
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        }
    }

    @Override
    public Optional<Medicine> findById(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_MEDICINE_BY_ID);) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Medicine medicine = null;

            while (resultSet.next()) {
                medicine = MedicineRowMapper.getInstance().mapRow(resultSet);
            }

            return Optional.ofNullable(medicine);
        } catch (SQLException sqlException) {
            throw new DaoException("Adding user exception " + sqlException);
        }
    }

    @Override
    public boolean createMedicine(Medicine medicine, String cloudinaryLink) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_MEDICINE);) {
            statement.setString(1, medicine.getName());
            statement.setInt(2, medicine.getQuantity());
            statement.setDouble(3, medicine.getPrice());
            statement.setInt(4, medicine.getAmountInPlate());
            statement.setInt(5, medicine.getAmountInPackage());
            statement.setString(6, medicine.getProducingCountry());
            statement.setString(7, medicine.getIngredients());
            statement.setBoolean(8, medicine.isNeedPrescription());
            statement.setString(9, cloudinaryLink);
            statement.setLong(10, medicine.getPharmacy().getPharmacyId());

            return statement.executeUpdate() == ONE_UPDATED;

        } catch (SQLException sqlException) {
            throw new DaoException("Adding user exception " + sqlException);
        }
    }

    @Override
    public int findMedicineSize() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_FIND_COUNT_ALL_MEDICINE);) {
            int count = 0;
            while (resultSet.next()) {
                count += resultSet.getInt(1);
            }

            return count;
        } catch (SQLException sqlException) {
            throw new DaoException(sqlException);
        }
    }

    @Override
    public List<Medicine> findPageMedicineByOrderWithLimitAndOffset(int pageToDisplay, int pageSize, String columnName) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(buildPageableQuery(pageToDisplay, pageSize, columnName));) {
            List<Medicine> medicines = new ArrayList<>();
            MedicineRowMapper medicineRowMapper = MedicineRowMapper.getInstance();

            while (resultSet.next()) {
                Medicine medicine = medicineRowMapper.mapRow(resultSet);
                medicines.add(medicine);
            }

            return medicines;
        } catch (SQLException sqlException) {
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean updateMedicineQuantityById(long medicineId, int updateQuantity) throws DaoException {
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin();
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = transaction.getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_MEDICINE_QUANTITY);
            statement.setInt(1, updateQuantity);
            statement.setLong(2, medicineId);
            int update = statement.executeUpdate();
            transaction.commit();

            return update == ONE_UPDATED;
        } catch(SQLException sqlException) {
            transaction.rollback();
            try {
                if(connection != null) {
                    connection.close();
                }
                if(statement != null) {
                    statement.close();
                }
            } catch(SQLException sqlException1) {
                throw new DaoException(sqlException1);
            }

            return false;
        }
    }

    @Override
    public Optional<Integer> findMedicineQuantityById(long medicineId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_MEDICINE_QUANTITY_BY_ID)) {
            statement.setLong(1, medicineId);
            ResultSet resultSet = statement.executeQuery();
            int quantity = -1;
            boolean exist = false;

            if (resultSet.next()) {
                exist = true;
                quantity = resultSet.getInt(MEDICINE_QUANTITY);
            }

            return exist ? Optional.of(quantity) : Optional.empty();

        } catch (SQLException e) {
            //logger
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        }
    }

    @Override
    public int findSizeMedicineLike(String like) throws DaoException {
        return 0;
    }

    private String buildPageableQuery(int pageToDisplay, int pageSize, String columnName) {
        StringBuilder stringBuilder = new StringBuilder(SQL_SELECT_ALL_MEDICINE + ORDER_BY);
        stringBuilder.append(columnName);
        int offset = (pageSize * pageToDisplay) - pageSize;
        stringBuilder.append(" LIMIT ");
        stringBuilder.append(pageSize);
        stringBuilder.append(" OFFSET ");
        stringBuilder.append(offset);

        return stringBuilder.toString();
    }
}
