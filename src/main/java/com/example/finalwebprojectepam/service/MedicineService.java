package com.example.finalwebprojectepam.service;

import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.entity.Medicine;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MedicineService {
    boolean addMedicine(Map<String, String> medicineData) throws ServiceException;

    int getMedicineListCount() throws ServiceException;

    List<Medicine> getMedicineList(int pageToDisplay, int pageSize, String columnName, String choosePharmacy) throws ServiceException;

    boolean updateMedicineQuantity(Map<Medicine, Integer> medicines) throws ServiceException;

    Optional<Integer> findQuantityById(long medicineId) throws ServiceException;

    Optional<Medicine> findMedicineById(long medicineId) throws ServiceException;

    void findMedicineInBasketById(long medicineId, Map<Medicine, Integer> medicines) throws ServiceException;

    Optional<String> checkActualMedicineQuantityInPharmacy(Map<Medicine, Integer> basket) throws ServiceException;

    int getMedicineListCountByPharmacyId(String pharmacyId) throws ServiceException;
}
