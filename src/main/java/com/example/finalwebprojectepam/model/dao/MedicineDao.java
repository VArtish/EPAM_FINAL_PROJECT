package com.example.finalwebprojectepam.model.dao;

import com.example.finalwebprojectepam.exception.DaoException;
import com.example.finalwebprojectepam.model.entity.Medicine;

import java.util.List;
import java.util.Optional;

public interface MedicineDao {
    boolean createMedicine(Medicine medicine, String cloudinaryLink) throws DaoException;

    int findMedicineSize() throws DaoException;

    List<Medicine> findPageMedicineByOrderWithLimitAndOffset(int pageToDisplay, int pageSize, String columnName) throws DaoException;

    boolean updateMedicineQuantityById(long medicineId, int updateQuantity) throws DaoException;

    Optional<Integer> findMedicineQuantityById(long medicineId) throws DaoException;

    int findSizeMedicineLike(String like) throws DaoException;
}
