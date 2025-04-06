package com.controller.customermenu;

import com.model.Reservation;
import com.repository.CustomersDataRepository;
import com.repository.ReservationsRepository;
import com.repository.SpacesDataRepository;
import com.util.TimeOverlapChecker;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class CustMenuCreateReservationController {

    private final SpacesDataRepository spacesDataRepository;
    private final CustomersDataRepository customersDataRepository;
    private final ReservationsRepository reservationsRepository;
    private final TimeOverlapChecker timeOverlapChecker;

    private static final String CREATE_RESERVATION_FORM = "customer-menu/create-reservation-form";
    private static final String CREATE_RESERVATION_PROCESS = "customer-menu/create-reservation-process";
    private static final String CREATE_RESERVATION_CONFIRMATION = "customer-menu/create-reservation-confirmation";

    @GetMapping(CREATE_RESERVATION_FORM)
    public String showCreateReservationForm(Model model) {
        if (spacesDataRepository.isEmpty()) {
            model.addAttribute("noSpacesMessage", "There are no registered spaces, no reservation possible.");
        } else {
            model.addAttribute("reservation", new Reservation());
            model.addAttribute("spaces", spacesDataRepository.getData());
        }
        return CREATE_RESERVATION_FORM;
    }

    @PostMapping(CREATE_RESERVATION_PROCESS)
    public String processReservationForm(Model model,
                                         @Valid @ModelAttribute("reservation") Reservation reservation,
                                         BindingResult result,
                                         @RequestParam("spaceId") Integer spaceId,
                                         HttpSession session) {

        model.addAttribute("reservation", reservation);
        model.addAttribute("spaces", spacesDataRepository.getData());

        if (result.hasErrors()) {
            return CREATE_RESERVATION_FORM;
        }

        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            model.addAttribute("loginRequiredError", "Please log in to create reservations.");
            return "redirect:/customer-login-form";
        }

        reservation.setSpace(spacesDataRepository.getSpaceById(spaceId));
        reservation.setCustomer(customersDataRepository.getCustomerById(customerId));
        LocalDateTime start = LocalDateTime.of(reservation.getDate(), reservation.getStartTime());
        LocalDateTime end = LocalDateTime.of(reservation.getDate(), reservation.getEndTime());

        if (start.isAfter(end)) {
            model.addAttribute("startAfterEndError", "The start time should not be after the end time.");
            return CREATE_RESERVATION_FORM;
        }

        if (timeOverlapChecker.startTimeOverlaps(start, reservation)) {
            model.addAttribute("startTimeOverlapsError", "The start time overlaps with an existing reservation.");
            return CREATE_RESERVATION_FORM;
        }

        if (timeOverlapChecker.endTimeOverlaps(end, reservation)) {
            model.addAttribute("endTimeOverlapsError", "The end time overlaps with an existing reservation.");
            return CREATE_RESERVATION_FORM;
        }

        reservation.setSpace(spacesDataRepository.getSpaceById(spaceId));
        reservationsRepository.addReservation(reservation);
        return CREATE_RESERVATION_CONFIRMATION;
    }

    @GetMapping(CREATE_RESERVATION_CONFIRMATION)
    public String showCreateReservationConformation(Model model) {
        return CREATE_RESERVATION_CONFIRMATION;
    }
}
