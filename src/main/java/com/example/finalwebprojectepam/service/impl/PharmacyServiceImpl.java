package com.example.finalwebprojectepam.service.impl;

import com.example.finalwebprojectepam.exception.DaoException;
import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.dao.impl.DefaultPharmacyDao;
import com.example.finalwebprojectepam.model.entity.Pharmacy;
import com.example.finalwebprojectepam.service.PharmacyService;

import java.util.List;

public class PharmacyServiceImpl implements PharmacyService {
    private static PharmacyServiceImpl instance;

    private PharmacyServiceImpl() {
    }

    public static PharmacyServiceImpl getInstance() {
        if (instance == null) {
            instance = new PharmacyServiceImpl();
        }

        return instance;
    }

    @Override
    public List<Pharmacy> getAllPharmacy() throws ServiceException {
        DefaultPharmacyDao pharmacyDao = new DefaultPharmacyDao();
        try {
            List<Pharmacy> pharmacies = pharmacyDao.findAll();
            return pharmacies;
        } catch (DaoException daoException) {
            throw new ServiceException(daoException);
        }
    }
}
