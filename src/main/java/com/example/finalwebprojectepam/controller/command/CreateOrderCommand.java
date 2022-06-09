package com.example.finalwebprojectepam.controller.command;

import com.example.finalwebprojectepam.controller.Router;
import com.example.finalwebprojectepam.exception.CommandException;
import com.example.finalwebprojectepam.exception.DaoException;
import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.entity.Medicine;
import com.example.finalwebprojectepam.service.MedicineService;
import com.example.finalwebprojectepam.service.OrderService;
import com.example.finalwebprojectepam.service.impl.MedicineServiceImpl;
import com.example.finalwebprojectepam.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.example.finalwebprojectepam.controller.PagePath.BASKET_PAGE;
import static com.example.finalwebprojectepam.controller.ParameterName.*;

public class CreateOrderCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Map<Medicine, Integer> medicines = (Map<Medicine, Integer>) request.getSession().getAttribute(BASKET);
        MedicineService medicineService = MedicineServiceImpl.getInstance();
        OrderService orderService = OrderServiceImpl.getInstance();
        Router router = new Router(BASKET_PAGE);
        try {
            Optional<String> message = medicineService.checkActualMedicineQuantityInPharmacy(medicines);
            if(message.isPresent()) {
                request.setAttribute(BASKET_ERROR_MESSAGE, message.get());
                router.setRouterType(Router.RouterType.FORWARD);
                return router;
            }

            medicineService.updateMedicineQuantity(medicines);
            // изменить, если false - отправить ошибку на фронт fixme
            long clientId = (Long) request.getSession().getAttribute(USER_ID);
            orderService.createNewOrder(clientId, medicines);
            request.getSession().setAttribute(BASKET, new HashMap<Medicine, Integer>());
        } catch (ServiceException serviceException) {
            throw new CommandException(serviceException);
        }

        return router;
    }
}
