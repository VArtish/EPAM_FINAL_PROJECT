package com.example.finalwebprojectepam.controller.command.basket;

import com.example.finalwebprojectepam.controller.Page;
import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.controller.command.Command;
import com.example.finalwebprojectepam.exception.CommandException;
import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.entity.Medicine;
import com.example.finalwebprojectepam.service.MedicineService;
import com.example.finalwebprojectepam.service.impl.MedicineServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.*;

import static com.example.finalwebprojectepam.controller.AttributeName.*;
import static com.example.finalwebprojectepam.controller.PagePath.MEDICINE_LIST;
import static com.example.finalwebprojectepam.controller.ParameterName.*;

public class AddMedicineToBasket implements Command {
    private static final Integer FIRST_ADD = 1;

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        MedicineService medicineService = MedicineServiceImpl.getInstance();
        long medicineId = Long.parseLong(request.getParameter(MEDICINE_ID));
        Map<Medicine, Integer> basket = getBasketFromSession(request.getSession());
        try {
            Optional<Medicine> medicineOptional = medicineService.findMedicineById(medicineId);
            if(medicineOptional.isEmpty()) {
                throw new CommandException();
            }
            Medicine medicine = medicineOptional.get();
            if(!basket.containsKey(medicine)) {
                basket.put(medicine, FIRST_ADD);
                request.getSession().setAttribute(BASKET, basket);
            } else {
                // вывести сообщение, что уже есть в корзине
            }
            updateCurrentMedicineList(request);
        } catch(ServiceException serviceException) {
            throw new CommandException(serviceException);
        }

        return new Router(MEDICINE_LIST);
    }

    private Map<Medicine, Integer> getBasketFromSession(HttpSession session) {
        Map<Medicine, Integer> basket = (Map<Medicine, Integer>) session.getAttribute(BASKET);
        if(basket == null) {
            basket = new HashMap<Medicine, Integer>();
        }

        return basket;
    }

    private void updateCurrentMedicineList(HttpServletRequest request) throws CommandException {
        MedicineService medicineService = MedicineServiceImpl.getInstance();
        try {
            int pageToDisplay = getPage(request);
            String nameColumn = request.getParameter(SORTED);
            String choosePharmacy = request.getParameter(CHOOSE_PHARMACY);
            int countMedicines = findMedicineCount(choosePharmacy);
            List<Medicine> medicines = new ArrayList<>();
            if (countMedicines > 0) {
                medicines = medicineService.getMedicineList(pageToDisplay, Page.PAGE_SIZE, nameColumn, choosePharmacy);
            }

            request.setAttribute(PAGE, new Page(countMedicines, pageToDisplay, Page.PAGE_SIZE));
            request.setAttribute(MEDICINES, medicines);
            request.setAttribute(SORTED, nameColumn);
            request.setAttribute(CHOOSE_PHARMACY, choosePharmacy);
        } catch (ServiceException serviceException) {
            throw new CommandException(serviceException);
        }
    }

    private int findMedicineCount(String choosePharmacy) throws CommandException{
        MedicineService medicineService = MedicineServiceImpl.getInstance();
        int medicineCount;
        try {
            if (choosePharmacy == null || choosePharmacy.isEmpty()) {
                medicineCount = medicineService.getMedicineListCount();
            } else {
                medicineCount = medicineService.getMedicineListCountByPharmacyId(choosePharmacy);
            }
            return medicineCount;
        } catch(ServiceException serviceException) {
            throw new CommandException(serviceException);
        }
    }

    private int getPage(HttpServletRequest request) {
        int page = 1;
        String stringPage = request.getParameter(PAGE);

        if (stringPage != null) {
            try {
                page = Integer.parseInt(stringPage);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error parsing page number, argument illegal.");
            }
        }
        return page;
    }
}
