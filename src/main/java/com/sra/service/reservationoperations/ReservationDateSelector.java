package com.sra.service.reservationoperations;

import java.io.IOException;
import java.time.LocalDate;

import com.sra.model.Reservation;
import com.sra.config.Config;
import com.sra.util.UtilitiesToolSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReservationDateSelector {

    private final UtilitiesToolSet utilitiesToolSet;

    public void selectDate (Reservation reservation) throws IOException {

        while (true) {
            System.out.println(Config.YELLOW_COLOUR + "Please insert the date of the reservation in YYYY-MM-DD format." + Config.RESET_COLOUR);
            String userInput = utilitiesToolSet.getReader().readLine();
            try {
                if (utilitiesToolSet.getInputValidator().validate(utilitiesToolSet.getDateInputValidator(), userInput)) {
                    reservation.setDate(LocalDate.parse(userInput));
                    break;
                }
                else {
                    System.out.println(Config.WRONG_INPUT_MESSAGE);
                }
            } catch (Exception e) {
                System.out.println(Config.WRONG_INPUT_EXCEPTION);
            }
        }
    }
}
