package com.example.finalwebprojectepam.model.mapper.impl;

import com.example.finalwebprojectepam.model.mapper.CustomRequestMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.example.finalwebprojectepam.controller.AttributeName.*;

public class MedicineRequestMapper implements CustomRequestMapper {
    private static MedicineRequestMapper instance;

    private MedicineRequestMapper(){}

    public static MedicineRequestMapper getInstance() {
        if(instance == null) {
            instance = new MedicineRequestMapper();
        }

        return instance;
    }
    @Override
    public Map<String, String> mapRequest(HttpServletRequest request) {
        String medicineName = request.getParameter(MEDICINE_NAME);
        String strAmountInPlate = request.getParameter(MEDICINE_AMOUNT_IN_PLATE);
        String strAmountInPackage = request.getParameter(MEDICINE_AMOUNT_IN_PACKAGE);
        String strMedicineQuantity = request.getParameter(MEDICINE_QUANTITY);
        String strMedicinePrice = request.getParameter(MEDICINE_PRICE);
        String producingCountry = request.getParameter(MEDICINE_PRODUCING_COUNTRY);
        String ingredients = request.getParameter(MEDICINE_INGREDIENTS);
        String imgLink = request.getParameter(MEDICINE_IMG);
        String pharmacyId = request.getParameter(MEDICINE_PHARMACY_ID);

        Map<String, String> medicineMap = new HashMap<>();
        medicineMap.put(MEDICINE_NAME, medicineName);
        medicineMap.put(MEDICINE_AMOUNT_IN_PACKAGE, strAmountInPackage);
        medicineMap.put(MEDICINE_AMOUNT_IN_PLATE, strAmountInPlate);
        medicineMap.put(MEDICINE_QUANTITY, strMedicineQuantity);
        medicineMap.put(MEDICINE_PRICE, strMedicinePrice);
        medicineMap.put(MEDICINE_PRODUCING_COUNTRY, producingCountry);
        medicineMap.put(MEDICINE_INGREDIENTS, ingredients);
        medicineMap.put(MEDICINE_IMG, imgLink);
        medicineMap.put(MEDICINE_PHARMACY_ID, pharmacyId);

        return medicineMap;
    }
}
