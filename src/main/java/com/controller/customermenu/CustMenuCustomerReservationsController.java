package com.controller.customermenu;

import com.model.ReservationDto;
import com.repository.ReservationsRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustMenuCustomerReservationsController {

    private final ReservationsRepository reservationsRepository;

    private static final String CUSTOMER_RESERVATIONS_SCREEN = "customer-menu/customer-reservations-screen";

    @GetMapping(CUSTOMER_RESERVATIONS_SCREEN)
    public String showCustomerReservations(Model model, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");

        if (customerId == null) {
            model.addAttribute("loginRequiredError", "Please log in to see your reservations.");
            return "redirect:/customer-login-form";
        }

        List<ReservationDto> customersReserervationsDtoList = reservationsRepository.getReservationsDtoListByCustomerId(customerId);

        if (reservationsRepository.isEmpty()) {
            model.addAttribute("noReservationsMessage", "There are no reservations.");
        }
        else if (customersReserervationsDtoList.isEmpty()) {
            model.addAttribute("noCustomerReservationsMessage", "You have no reservations.");
        }
        else {
            model.addAttribute("reservations", customersReserervationsDtoList);
        }
        return CUSTOMER_RESERVATIONS_SCREEN;
    }
}
