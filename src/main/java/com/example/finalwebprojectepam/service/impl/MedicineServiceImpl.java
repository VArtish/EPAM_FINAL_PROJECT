package com.example.finalwebprojectepam.service.impl;

import com.example.finalwebprojectepam.exception.DaoException;
import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.dao.MedicineDao;
import com.example.finalwebprojectepam.model.dao.impl.DefaultMedicineDao;
import com.example.finalwebprojectepam.model.dao.impl.DefaultPharmacyDao;
import com.example.finalwebprojectepam.model.entity.Medicine;
import com.example.finalwebprojectepam.model.entity.Pharmacy;
import com.example.finalwebprojectepam.service.MediaService;
import com.example.finalwebprojectepam.service.MedicineService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.finalwebprojectepam.controller.AttributeName.*;

public class MedicineServiceImpl implements MedicineService {
    private static MedicineServiceImpl instance;
    private static final String ERROR_MESSAGE_MEDICINE_QUANTITY = "At the moment we do not have the required quantities of these products: ";
    private static final String SPACE = " ";

    private MedicineServiceImpl() {
    }

    public static MedicineServiceImpl getInstance() {
        if (instance == null) {
            instance = new MedicineServiceImpl();
        }

        return instance;
    }

    @Override
    public boolean addMedicine(Map<String, String> medicineData) throws ServiceException {
        if (!validateMedicine(medicineData) || !checkParseMedicineData(medicineData)) {
            return false;
        }

        MediaService mediaService = MediaServiceImpl.getInstance();
        String linkImage = medicineData.get(MEDICINE_IMG);
        DefaultMedicineDao medicineDao = new DefaultMedicineDao();
        try {
            Medicine medicine = buildMedicine(medicineData);
            String cloudinaryLink;

            if (true) { // проверить
                cloudinaryLink = mediaService.storePhoto(linkImage);
            } else {
                cloudinaryLink = mediaService.storePhoto("D:\\FInalWebProjectEpam\\src\\main\\webapp\\img\\default.jpg");
            }
            medicineDao.createMedicine(medicine, cloudinaryLink);
        } catch (DaoException daoException) {
            throw new ServiceException(daoException);
        }

        return true;
    }

    private Medicine buildMedicine(Map<String, String> medicineData) throws ServiceException {
        DefaultPharmacyDao pharmacyDao = new DefaultPharmacyDao();
        String name = medicineData.get(MEDICINE_NAME);
        String str = medicineData.get(MEDICINE_PHARMACY_ID);
        long pharmacyId = Long.parseLong(medicineData.get(MEDICINE_PHARMACY_ID));
        String producingCountry = medicineData.get(MEDICINE_PRODUCING_COUNTRY);
        String ingredients = medicineData.get(MEDICINE_INGREDIENTS);
        String strQuantity = medicineData.get(MEDICINE_QUANTITY);
        String strAmountInPlate = medicineData.get(MEDICINE_AMOUNT_IN_PLATE);
        String strAmountInPackage = medicineData.get(MEDICINE_AMOUNT_IN_PACKAGE);
        String strPrice = medicineData.get(MEDICINE_PRICE);
        int amountInPlate = Integer.parseInt(strAmountInPlate);
        int amountInPackage = Integer.parseInt(strAmountInPackage);
        int quantity = Integer.parseInt(strQuantity);
        double price = Double.parseDouble(strPrice);
        Pharmacy pharmacy = null;
        try {
            pharmacy = pharmacyDao.findById(pharmacyId).get();
        } catch (DaoException daoException) {
            throw new ServiceException(daoException);
        }

        return new Medicine.MedicineBuilder().withProducingCountry(producingCountry).withPrice(price).withAmountInPlate(amountInPlate)
                .withAmountInPackage(amountInPackage).withPharmacy(pharmacy).withQuantity(quantity).withIngredients(ingredients).withName(name).withNeedPrescription(true).build();
    }

    private boolean checkParseIntegerData(String strData) {
        boolean result = true;
        try {
            Integer.parseInt(strData);
        } catch (NumberFormatException numberFormatException) {
            result = false;
        }
        return result;
    }

    private boolean checkParseDoubleData(String strData) {
        boolean result = true;
        try {
            Double.parseDouble(strData);
        } catch (NumberFormatException numberFormatException) {
            result = false;
        }
        return result;
    }

    private boolean checkParseMedicineData(Map<String, String> medicineData) {
        boolean result = true;
        String strAmountInPlate = medicineData.get(MEDICINE_AMOUNT_IN_PLATE);
        String strAmountInPackage = medicineData.get(MEDICINE_AMOUNT_IN_PACKAGE);
        String strMedicineQuantity = medicineData.get(MEDICINE_QUANTITY);
        String strMedicinePrice = medicineData.get(MEDICINE_PRICE);

        if (!checkParseIntegerData(strAmountInPackage)) {
            result = false;
        }

        if (!checkParseIntegerData(strAmountInPlate)) {
            result = false;
        }

        if (!checkParseIntegerData(strMedicineQuantity)) {
            result = false;
        }

        if (!checkParseDoubleData(strMedicinePrice)) {
            result = false;
        }

        return result;
    }

    private boolean validateMedicine(Map<String, String> medicineData) {
        boolean result = true;
        String medicineName = medicineData.get(MEDICINE_NAME);
        String strAmountInPlate = medicineData.get(MEDICINE_AMOUNT_IN_PLATE);
        String strAmountInPackage = medicineData.get(MEDICINE_AMOUNT_IN_PACKAGE);
        String strMedicineQuantity = medicineData.get(MEDICINE_QUANTITY);
        String strMedicinePrice = medicineData.get(MEDICINE_PRICE);
        String producingCountry = medicineData.get(MEDICINE_PRODUCING_COUNTRY);
        String ingredients = medicineData.get(MEDICINE_INGREDIENTS);
        String imgLink = medicineData.get(MEDICINE_IMG);
        String pharmacyId = medicineData.get(MEDICINE_PHARMACY_ID);

        if (!checkParseIntegerData(strAmountInPackage)) {
            result = false;
        }

        if (!checkParseIntegerData(strAmountInPlate)) {
            result = false;
        }

        if (!checkParseIntegerData(strMedicineQuantity)) {
            result = false;
        }

        if (!checkParseDoubleData(strMedicinePrice)) {
            result = false;
        }

        if (medicineName == null || medicineName.isEmpty()) {
            result = false;
        }

        if (ingredients == null || ingredients.isEmpty()) {
            result = false;
        }

        if (producingCountry == null || producingCountry.isEmpty()) {
            result = false;
        }

        if (strAmountInPackage == null || strAmountInPackage.isEmpty()) {
            result = false;
        }

        if (strAmountInPlate == null || strAmountInPlate.isEmpty()) {
            result = false;
        }

        if (pharmacyId == null || pharmacyId.isEmpty()) {
            result = false;
        }

        if (strMedicineQuantity == null || strMedicineQuantity.isEmpty()) {
            result = false;
        }

        if (strMedicinePrice == null || strMedicinePrice.isEmpty()) {
            result = false;
        }

        return result;
    }

    @Override
    public int getMedicineListCount() throws ServiceException {
        DefaultMedicineDao medicineDao = new DefaultMedicineDao();
        try {
            int countMedicineList = medicineDao.findMedicineSize();
            return countMedicineList;
        } catch (DaoException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public List<Medicine> getMedicineList(int pageToDisplay, int pageSize, String columnName) throws ServiceException {
        MedicineDao medicineDao = new DefaultMedicineDao();
        MediaService mediaService = MediaServiceImpl.getInstance();
        try {
            List<Medicine> medicines = medicineDao.findPageMedicineByOrderWithLimitAndOffset(pageToDisplay, pageSize, columnName);
            for(Medicine medicine: medicines) {
                String imageKey = medicine.getImageLink();
                medicine.setImageLink(mediaService.getPreviewPhoto(imageKey));
            }
            return medicines;
        } catch (DaoException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public boolean updateMedicineQuantity(Map<Medicine, Integer> medicines) throws ServiceException {
        DefaultMedicineDao medicineDao = new DefaultMedicineDao();
        boolean result = true;
        try {
            for (Medicine medicine : medicines.keySet()) {
                int updateQuantity = medicine.getQuantity() - medicines.get(medicine);
                boolean update = medicineDao.updateMedicineQuantityById(medicine.getMedicineId(), updateQuantity);
                if (!update) {
                    result = false;
                }
            }
            return result;
        } catch (DaoException daoException) {
            throw new ServiceException();
        }
    }

    @Override
    public Optional<Integer> findQuantityById(long medicineId) throws ServiceException {
        DefaultMedicineDao medicineDao = new DefaultMedicineDao();
        try {
            return medicineDao.findMedicineQuantityById(medicineId);
        } catch (DaoException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public Optional<Medicine> findMedicineById(long medicineId) throws ServiceException {
        DefaultMedicineDao medicineDao = new DefaultMedicineDao();
        MediaService mediaService = MediaServiceImpl.getInstance();
        try {
            Optional<Medicine> medicine = medicineDao.findById(medicineId);
            if (medicine.isPresent()) {
                String imageLinkCloudinary = medicine.get().getImageLink();
                medicine.get().setImageLink(mediaService.getPreviewPhoto(imageLinkCloudinary));
            }
            return medicine;
        } catch (DaoException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public void findMedicineInBasketById(long medicineId, Map<Medicine, Integer> medicines) {
        if (medicines != null) {
            Optional<Medicine> medicineOptional = medicines.keySet().stream().filter(med -> med.getMedicineId() == medicineId).findFirst();
            if (medicineOptional.isPresent()) {
                Medicine medicine = medicineOptional.get();
                medicines.remove(medicine);
            }
        }
    }

    @Override
    public Optional<String> checkActualMedicineQuantityInPharmacy(Map<Medicine, Integer> basket) throws ServiceException {
        DefaultMedicineDao medicineDao = new DefaultMedicineDao();
        StringBuilder message = new StringBuilder(ERROR_MESSAGE_MEDICINE_QUANTITY);
        boolean check = false;
        try {
            for (Medicine medicine : basket.keySet()) {
                Optional<Integer> optionalActualQuantity = medicineDao.findMedicineQuantityById(medicine.getMedicineId());
                if (optionalActualQuantity.isPresent()) {
                    int actualQuantity = optionalActualQuantity.get();
                    if (basket.get(medicine) > actualQuantity) {
                        message.append(medicine.getName() + SPACE);
                        check = true;
                    }
                }
            }
        } catch (DaoException daoException) {
            throw new ServiceException(daoException);
        }

        return check ? Optional.of(message.toString()) : Optional.empty();
    }
}
