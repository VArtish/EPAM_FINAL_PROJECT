package com.example.finalwebprojectepam.controller.command;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.exception.CommandException;
import com.example.finalwebprojectepam.exception.DaoException;
import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.entity.Medicine;
import com.example.finalwebprojectepam.service.MedicineService;
import com.example.finalwebprojectepam.service.impl.MedicineServiceImpl;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;
import java.util.Optional;

import static com.example.finalwebprojectepam.controller.AttributeName.MEDICINE_ID;
import static com.example.finalwebprojectepam.controller.PagePath.BASKET_PAGE;
import static com.example.finalwebprojectepam.controller.ParameterName.BASKET;
import static com.example.finalwebprojectepam.controller.ParameterName.OPERATION;

public class ChangeSizeMedicineInBasketCommand implements Command {
    private static final String DECREASE = "decrease";
    private static final String INCREASE = "increase";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Map<Medicine, Integer> basket = (Map<Medicine, Integer>) request.getSession().getAttribute(BASKET);
        String operation = request.getParameter(OPERATION);
        long medicineId = Long.parseLong(request.getParameter(MEDICINE_ID));
        MedicineService medicineService = MedicineServiceImpl.getInstance();
        try {
            Optional<Medicine> medicineOptional = medicineService.findMedicineById(medicineId);
            if (medicineOptional.isPresent()) {
                Medicine medicine = medicineOptional.get();
                int currentSize = basket.get(medicine);
                int updateSize = changeMedicineSize(medicine, operation, currentSize);
                basket.replace(medicine, updateSize);
                request.getSession().setAttribute(BASKET, basket);
            }
        } catch (ServiceException serviceException) {
            throw new CommandException(serviceException);
        }

        return new Router(BASKET_PAGE);
    }

    private int changeMedicineSize(Medicine medicine, String operation, int currentSize) {
        int updateSize;

        switch (operation) {
            case DECREASE -> {
                if(currentSize - 1 <= 0) {
                    return currentSize;
                }
                updateSize = currentSize - 1;
                break;
            }
            case INCREASE -> {
                if (medicine.getQuantity() < currentSize) {
                    return currentSize;
                }
                updateSize = currentSize + 1;
                break;
            }
            default -> {
                updateSize = currentSize;
                break;
            }
        }

        return updateSize;
    }
}
