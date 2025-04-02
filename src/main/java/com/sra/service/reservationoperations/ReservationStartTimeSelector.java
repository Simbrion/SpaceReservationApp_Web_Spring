package com.sra.service.reservationoperations;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.sra.config.Config;

import com.sra.model.Reservation;
import com.sra.util.UtilitiesToolSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReservationStartTimeSelector {

    private final UtilitiesToolSet utilitiesToolSet;

    public void select(Reservation newReservation) throws IOException {
        while (true) {
            System.out.println(Config.YELLOW_COLOUR + "Please insert the reservation start time of the reservation in HH:mm format." + Config.RESET_COLOUR);
            String userInput = utilitiesToolSet.getReader().readLine();
            if (utilitiesToolSet.getInputValidator().validate(utilitiesToolSet.getTimeInputValidator(), userInput)) {
                try {
                LocalDateTime startOfNewReservation = LocalDateTime.of(newReservation.getDate(), LocalTime.parse(userInput));
                if (utilitiesToolSet.getTimeOverlapChecker().startTimeOverlaps(startOfNewReservation, newReservation)) {
                    System.out.println(Config.RED_COLOUR + "The reservation start time overlaps with the existing reservation. Please choose different start time." + Config.RESET_COLOUR);
                    continue;
                }
                newReservation.setStartTime(LocalTime.parse(userInput));
                break;
                }
                catch (Exception e) {
                System.out.println(Config.WRONG_INPUT_EXCEPTION);
                }
            }
            else System.out.println(Config.WRONG_INPUT_MESSAGE);
        }
    }
}
