package com.example.finalwebprojectepam.controller.command;

import com.example.finalwebprojectepam.controller.Page;
import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.exception.CommandException;
import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.entity.Medicine;
import com.example.finalwebprojectepam.service.MedicineService;
import com.example.finalwebprojectepam.service.impl.MedicineServiceImpl;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.finalwebprojectepam.controller.AttributeName.*;
import static com.example.finalwebprojectepam.controller.PagePath.MEDICINE_LIST;
import static com.example.finalwebprojectepam.controller.ParameterName.PAGE;
import static com.example.finalwebprojectepam.controller.ParameterName.SEARCH_LINE;

public class ShowMedicineListCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        MedicineService medicineService = MedicineServiceImpl.getInstance();
        try {
            int countMedicines = medicineService.getMedicineListCount();
            int pageToDisplay = getPage(request);
            String nameColumn = request.getParameter(SORTED);

            if(nameColumn == null) {
                nameColumn = MEDICINE_ID;
            }
            List<Medicine> medicines = new ArrayList<>(countMedicines);
            if(countMedicines > 0) {
                medicines = medicineService.getMedicineList(pageToDisplay, Page.PAGE_SIZE, nameColumn);
            }

            request.setAttribute(PAGE, new Page(countMedicines, pageToDisplay, Page.PAGE_SIZE));
            request.setAttribute(MEDICINES, medicines);
            request.setAttribute(SORTED, nameColumn);
            request.getSession().setAttribute(LAST_PAGE, MEDICINE_LIST);
            return new Router(MEDICINE_LIST);
        } catch (ServiceException serviceException) {
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
