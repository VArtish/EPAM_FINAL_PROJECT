package com.example.finalwebprojectepam.controller.command;

import com.example.finalwebprojectepam.controller.command.basket.AddMedicineToBasket;
import com.example.finalwebprojectepam.controller.command.basket.ChangeSizeMedicineInBasketCommand;
import com.example.finalwebprojectepam.controller.command.basket.DeleteMedicineFromBasketCommand;
import com.example.finalwebprojectepam.controller.command.basket.ShowBasketCommand;
import com.example.finalwebprojectepam.controller.command.medicine.AddNewMedicineCommand;
import com.example.finalwebprojectepam.controller.command.medicine.ShowAddMedicinePageCommand;
import com.example.finalwebprojectepam.controller.command.medicine.ShowMedicineListCommand;
import com.example.finalwebprojectepam.controller.command.medicine.ShowMoreInfoMedicineCommand;
import com.example.finalwebprojectepam.controller.command.order.CreateOrderCommand;
import com.example.finalwebprojectepam.controller.command.order.ShowOrderListCommand;
import com.example.finalwebprojectepam.controller.command.pharmacy.ShowPharmacyListCommand;
import com.example.finalwebprojectepam.controller.command.redirect.GoToMainPageCommand;
import com.example.finalwebprojectepam.controller.command.user.*;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    GO_TO_MAIN_PAGE(new GoToMainPageCommand()),
    GO_TO_SIGN_UP_PAGE(new ShowSignUpPage()),
    GO_TO_SIGN_IN_PAGE(new ShowSignInPage()),
    CREATE_USER(new CreateUserCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    SHOW_USER_CABINET(new ShowUserCabinetCommand()),
    UPDATE_USER_PROFILE(new UpdateUserProfileCommand()),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    SHOW_MEDICINE_LIST(new ShowMedicineListCommand()),
    SHOW_MORE_INFO_MEDICINE(new ShowMoreInfoMedicineCommand()),
    ADD_MEDICINE_TO_BASKET(new AddMedicineToBasket()),
    SHOW_BASKET(new ShowBasketCommand()),
    DELETE_MEDICINE_FROM_BASKET(new DeleteMedicineFromBasketCommand()),
    CHANGE_SIZE_MEDICINE_IN_BASKET(new ChangeSizeMedicineInBasketCommand()),
    CREATE_ORDER(new CreateOrderCommand()),
    SHOW_ORDER_LIST(new ShowOrderListCommand()),
    ADD_NEW_MEDICINE(new AddNewMedicineCommand()),
    SHOW_ADD_MEDICINE_PAGE(new ShowAddMedicinePageCommand()),
    SHOW_PHARMACY_LIST(new ShowPharmacyListCommand()),
    DEFAULT_COMMAND(new DefaultCommand());


    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr) {
        CommandType current = null;
        try {
            if (commandStr == null || commandStr.isBlank()) {
                current = DEFAULT_COMMAND;
            } else {
                current = CommandType.valueOf(commandStr.toUpperCase());
            }
        } catch (IllegalArgumentException e) {
            //log error
            current = DEFAULT_COMMAND;
        }
        return current.command;
    }
}