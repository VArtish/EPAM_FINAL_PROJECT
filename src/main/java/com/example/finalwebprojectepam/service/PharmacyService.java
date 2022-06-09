package com.example.finalwebprojectepam.service;

import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.entity.Pharmacy;

import java.util.List;

public interface PharmacyService {
    List<Pharmacy> getAllPharmacy() throws ServiceException;
}
