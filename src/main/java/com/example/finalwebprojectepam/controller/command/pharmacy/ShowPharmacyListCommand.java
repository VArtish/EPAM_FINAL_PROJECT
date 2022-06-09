package com.example.finalwebprojectepam.controller.command.pharmacy;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.controller.command.Command;
import com.example.finalwebprojectepam.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

import static com.example.finalwebprojectepam.controller.PagePath.PHARMACY_LIST;

public class ShowPharmacyListCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        return new Router(PHARMACY_LIST);
    }
}
