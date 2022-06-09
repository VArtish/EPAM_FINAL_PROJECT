package com.example.finalwebprojectepam.model.mapper.impl;

import com.example.finalwebprojectepam.exception.DaoException;
import com.example.finalwebprojectepam.model.entity.Medicine;
import com.example.finalwebprojectepam.model.entity.Pharmacy;
import com.example.finalwebprojectepam.model.mapper.CustomRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.finalwebprojectepam.controller.AttributeName.*;
import static com.example.finalwebprojectepam.controller.ParameterName.PHARMACY_ADDRESS;
import static com.example.finalwebprojectepam.controller.ParameterName.PHARMACY_NAME;

public class MedicineRowMapper implements CustomRowMapper<Medicine> {
    private static MedicineRowMapper instance;
    private static final String MEDICINE_IMAGE_LINK = "medicine_image_link";

    private MedicineRowMapper() {
    }

    public static MedicineRowMapper getInstance() {
        if (instance == null) {
            instance = new MedicineRowMapper();
        }

        return instance;
    }

    @Override
    public Medicine mapRow(ResultSet resultSet) throws DaoException {
        try {
            String name = resultSet.getString(MEDICINE_NAME);
            long id = resultSet.getLong(MEDICINE_ID);
            String producingCountry = resultSet.getString(MEDICINE_PRODUCING_COUNTRY);
            int amountInPlate = resultSet.getInt(MEDICINE_AMOUNT_IN_PLATE);
            int amountInPackage = resultSet.getInt(MEDICINE_AMOUNT_IN_PACKAGE);
            int quantity = resultSet.getInt(MEDICINE_QUANTITY);
            double price = resultSet.getDouble(MEDICINE_PRICE);
            String ingredients = resultSet.getString(MEDICINE_INGREDIENTS);
            boolean needPrescription = resultSet.getBoolean(MEDICINE_NEED_PRESCRIPTION);
            String medicineName = resultSet.getString(PHARMACY_NAME);
            String medicineAddress = resultSet.getString(PHARMACY_ADDRESS);
            String imageLink = resultSet.getString(MEDICINE_IMAGE_LINK);

            Pharmacy pharmacy = new Pharmacy(medicineName, medicineAddress);

            return new Medicine.MedicineBuilder().withMedicineId(id).withName(name).withAmountInPackage(amountInPackage)
                    .withAmountInPlate(amountInPlate).withQuantity(quantity).withPrice(price).withIngredients(ingredients)
                    .withNeedPrescription(needPrescription).withImage(imageLink).withProducingCountry(producingCountry).withPharmacy(pharmacy).build();
        } catch (SQLException sqlException) {
            throw new DaoException(sqlException);
        }
    }
}
