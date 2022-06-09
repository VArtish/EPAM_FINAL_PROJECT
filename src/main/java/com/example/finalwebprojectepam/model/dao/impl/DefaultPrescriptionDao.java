package com.example.finalwebprojectepam.model.dao.impl;

import com.example.finalwebprojectepam.model.dao.PrescriptionDao;
import com.example.finalwebprojectepam.model.dao.impl.BaseDao;
import com.example.finalwebprojectepam.model.entity.Prescription;

import java.util.List;
import java.util.Optional;

public class DefaultPrescriptionDao extends BaseDao<Prescription, Long> implements PrescriptionDao {
    @Override
    public List<Prescription> findAll() {
        return null;
    }

    @Override
    public Optional<Prescription> findById(Long id) {
        return Optional.empty();
    }
}
