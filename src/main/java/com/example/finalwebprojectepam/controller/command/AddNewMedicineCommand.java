package com.example.finalwebprojectepam.controller.command;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.exception.CommandException;
import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.entity.Pharmacy;
import com.example.finalwebprojectepam.model.mapper.impl.MedicineRequestMapper;
import com.example.finalwebprojectepam.service.MedicineService;
import com.example.finalwebprojectepam.service.PharmacyService;
import com.example.finalwebprojectepam.service.impl.MedicineServiceImpl;
import com.example.finalwebprojectepam.service.impl.PharmacyServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.example.finalwebprojectepam.controller.AttributeName.LAST_PAGE;
import static com.example.finalwebprojectepam.controller.AttributeName.PHARMACIES;
import static com.example.finalwebprojectepam.controller.PagePath.ADD_MEDICINE_PAGE;

public class AddNewMedicineCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        MedicineRequestMapper requestMapper = MedicineRequestMapper.getInstance();
        Map<String, String> medicineData = requestMapper.mapRequest(request);
        MedicineService medicineService = MedicineServiceImpl.getInstance();
        PharmacyService pharmacyService = PharmacyServiceImpl.getInstance();
        try {
            medicineService.addMedicine(medicineData);
            List<Pharmacy> pharmacies = pharmacyService.getAllPharmacy();
            request.setAttribute(PHARMACIES, pharmacies);
            request.getSession().setAttribute(LAST_PAGE, ADD_MEDICINE_PAGE);
        } catch (ServiceException serviceException) {
            throw new CommandException(serviceException);
        }
        return new Router(ADD_MEDICINE_PAGE);
    }
}
