package com.controller.adminmenu;

import com.model.ReservationDto;
import com.repository.ReservationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminMenuRegisteredReservationsController {

    private final ReservationsRepository reservationsRepository;

    private static final String REGISTERED_RESERVATIONS_SCREEN = "admin-menu/registered-reservations-screen";

    @GetMapping(REGISTERED_RESERVATIONS_SCREEN)
    public String showRegisteredReservationsScreen(Model model) {

        if (reservationsRepository.isEmpty()) {
            model.addAttribute("noReservationsMessage", "There are no registered reservations.");
        }
        else {
            List<ReservationDto> reservationsDtoList = reservationsRepository.getReservationsDtoList();
            model.addAttribute("reservations", reservationsDtoList);
        }

        return REGISTERED_RESERVATIONS_SCREEN;
    }
}
