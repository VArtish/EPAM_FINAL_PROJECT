package com.example.finalwebprojectepam.model.mapper.impl;

import com.example.finalwebprojectepam.exception.DaoException;
import com.example.finalwebprojectepam.model.entity.Pharmacy;
import com.example.finalwebprojectepam.model.mapper.CustomRowMapper;
import org.apache.logging.log4j.core.appender.db.DbAppenderLoggingException;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.finalwebprojectepam.controller.ParameterName.*;

public class PharmacyRowMapper implements CustomRowMapper<Pharmacy> {
    private static PharmacyRowMapper instance;

    private PharmacyRowMapper() {
    }

    public static PharmacyRowMapper getInstance() {
        if(instance == null) {
            instance = new PharmacyRowMapper();
        }

        return instance;
    }
    @Override
    public Pharmacy mapRow(ResultSet resultSet) throws DaoException {
        try {
            String pharmacyName = resultSet.getString(PHARMACY_NAME);
            String pharmacyAddress = resultSet.getString(PHARMACY_ADDRESS);
            long pharmacyId = resultSet.getLong(PHARMACY_ID);
            Pharmacy pharmacy = new Pharmacy(pharmacyName, pharmacyAddress, pharmacyId);
            return pharmacy;
        } catch (SQLException sqlException) {
            throw new DaoException(sqlException);
        }
    }
}
