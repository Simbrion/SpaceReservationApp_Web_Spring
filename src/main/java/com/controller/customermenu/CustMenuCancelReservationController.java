package com.controller.customermenu;

import com.model.Reservation;
import com.model.ReservationDto;
import com.repository.ReservationsRepository;
import com.repository.SpacesDataRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustMenuCancelReservationController {

    private final SpacesDataRepository spacesDataRepository;
    private final ReservationsRepository reservationsRepository;

    private static final String CANCEL_RESERVATION_CHOOSING = "customer-menu/cancel-reservation-choosing";
    private static final String CANCEL_RESERVATION_CONFIRMATION = "customer-menu/cancel-reservation-confirmation";

    @GetMapping(CANCEL_RESERVATION_CHOOSING)
    public String showCancelReservationChoosing(Model model, HttpSession session) {

        Integer customerId = (Integer) session.getAttribute("customerId");

        if (customerId == null) {
            model.addAttribute("loginRequiredError", "Please log in to cancel reservations.");
            return "redirect:/customer-login-form";
        }

        List<ReservationDto> customerReservations = reservationsRepository.getReservationsDtoListByCustomerId(customerId);

        if (customerReservations.isEmpty()) {
            model.addAttribute("noReservationsMessage", "You have no reservations.");
        }
        else {
            model.addAttribute("customerReservations", customerReservations);
        }


        return CANCEL_RESERVATION_CHOOSING;
    }

    @PostMapping(CANCEL_RESERVATION_CONFIRMATION)
    public String processCancelReservation(Model model, @RequestParam("reservation") int reservationId) {
        Reservation reservationToBeDeleted = reservationsRepository.getReservationById(reservationId);
        if (reservationToBeDeleted == null) {
            model.addAttribute("reservationNotFoundMessage", "Reservation not found in the database.");
            return CANCEL_RESERVATION_CHOOSING;
        }
        reservationsRepository.removeReservation(reservationsRepository.getReservationById(reservationId));
        return CANCEL_RESERVATION_CONFIRMATION;
    }
}
