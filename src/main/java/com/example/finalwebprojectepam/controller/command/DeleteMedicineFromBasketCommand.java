package com.example.finalwebprojectepam.controller.command;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.exception.CommandException;
import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.entity.Medicine;
import com.example.finalwebprojectepam.service.MedicineService;
import com.example.finalwebprojectepam.service.impl.MedicineServiceImpl;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import static com.example.finalwebprojectepam.controller.AttributeName.MEDICINE_ID;
import static com.example.finalwebprojectepam.controller.PagePath.BASKET_PAGE;
import static com.example.finalwebprojectepam.controller.ParameterName.BASKET;

public class DeleteMedicineFromBasketCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Map<Medicine, Integer> medicines = (Map<Medicine, Integer>) request.getSession().getAttribute(BASKET);
        long medicineId = Long.parseLong(request.getParameter(MEDICINE_ID));
        MedicineService medicineService = MedicineServiceImpl.getInstance();
        try {
            medicineService.findMedicineInBasketById(medicineId, medicines);
        } catch(ServiceException serviceException) {
            throw new CommandException(serviceException);
        }
        request.getSession().setAttribute(BASKET, medicines);
        return new Router(BASKET_PAGE);
    }
}
