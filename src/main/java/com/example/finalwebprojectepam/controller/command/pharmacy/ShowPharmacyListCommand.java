package com.example.finalwebprojectepam.controller.command.pharmacy;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.controller.command.Command;
import com.example.finalwebprojectepam.exception.CommandException;
import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.entity.Pharmacy;
import com.example.finalwebprojectepam.service.PharmacyService;
import com.example.finalwebprojectepam.service.impl.PharmacyServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.finalwebprojectepam.controller.AttributeName.PHARMACIES;
import static com.example.finalwebprojectepam.controller.PagePath.PHARMACY_LIST;

public class ShowPharmacyListCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        PharmacyService pharmacyService = PharmacyServiceImpl.getInstance();
        try {
            List<Pharmacy> pharmacies = pharmacyService.getAllPharmacy();
            request.setAttribute(PHARMACIES, pharmacies);
            return new Router(PHARMACY_LIST);
        } catch(ServiceException serviceException) {
            throw new CommandException(serviceException);
        }
    }
}
