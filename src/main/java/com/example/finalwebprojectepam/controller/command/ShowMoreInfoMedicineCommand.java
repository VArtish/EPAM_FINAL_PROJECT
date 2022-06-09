package com.example.finalwebprojectepam.controller.command;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.exception.CommandException;
import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.entity.Medicine;
import com.example.finalwebprojectepam.service.MedicineService;
import com.example.finalwebprojectepam.service.impl.MedicineServiceImpl;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.example.finalwebprojectepam.controller.AttributeName.LAST_PAGE;
import static com.example.finalwebprojectepam.controller.AttributeName.MEDICINE_ID;
import static com.example.finalwebprojectepam.controller.PagePath.MEDICINE_INFO;
import static com.example.finalwebprojectepam.controller.ParameterName.MEDICINE;

public class ShowMoreInfoMedicineCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String strMedicineId = request.getParameter(MEDICINE_ID);
        if(strMedicineId == null) {
            throw new IllegalArgumentException("Medicine id is null!");
        }
        long medicineId = Long.parseLong(strMedicineId);
        MedicineService medicineService = MedicineServiceImpl.getInstance();
        try {
            Optional<Medicine> medicine = medicineService.findMedicineById(medicineId);
            if(medicine.isEmpty()) {
                // set attribute "not found"
            }
            request.setAttribute(MEDICINE, medicine.get());
            request.getSession().setAttribute(LAST_PAGE, MEDICINE_INFO);
            return new Router(MEDICINE_INFO);
        } catch(ServiceException serviceException) {
            throw new CommandException(serviceException);
        } catch(IllegalArgumentException illegalArgumentException) {
            throw new CommandException(illegalArgumentException);
        }
    }
}
