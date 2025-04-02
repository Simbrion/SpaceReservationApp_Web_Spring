package com.sra.service.reservationoperations;


import com.sra.model.Customer;
import com.sra.model.Reservation;
import com.sra.config.Config;
import com.sra.repository.ReservationsRepository;
import com.sra.util.UtilitiesToolSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationCanceler {

    private final UtilitiesToolSet utilitiesToolSet;
    private final ReservationsRepository reservationsRepository;

    public void cancelReservation(Customer customer) throws IOException {
        boolean skipTheRest = false;

        if (reservationsRepository.isEmpty() || customer.getReservations(reservationsRepository).isEmpty()) {
            System.out.println(Config.YELLOW_COLOUR + "You have no reservations." + Config.RESET_COLOUR);
            skipTheRest = true;
        }

        if (!skipTheRest) {
            try {
                System.out.println("Please indicate ID of the reservation to be cancelled.");
                utilitiesToolSet.getReservationsListViewer().printCustomerReservationsList(customer);
                String userInput = utilitiesToolSet.getReader().readLine();
                if (utilitiesToolSet.getInputValidator().validate(utilitiesToolSet.getPositiveDigitValidator(), userInput)) {
                    performDeletion(userInput, customer);
                } else {
                    System.out.println(Config.WRONG_INPUT_MESSAGE);
                }
            } catch (IOException exception) {
                System.out.println(Config.WRONG_INPUT_EXCEPTION);
            }
        }
    }

    private void performDeletion(String userInput, Customer customer) {
        boolean foundReservationToDelete = false;
        List<Reservation> currentReservationsList = reservationsRepository.getData();

        for (int i = 0; i < currentReservationsList.size(); i++) {
            if ((Integer.parseInt(userInput) == currentReservationsList.get(i).getId()) && (currentReservationsList.get(i).getId() == customer.getId())) {
                reservationsRepository.removeReservation(currentReservationsList.get(i));
                currentReservationsList.remove(currentReservationsList.get(i));
                System.out.println(Config.GREEN_COLOUR + "Reservation has been successfully cancelled!" + Config.RESET_COLOUR);
                foundReservationToDelete = true;
                break;
            }
        }

        if (!foundReservationToDelete) {
            System.out.println(Config.RED_COLOUR + "Can't find your reservation ith such ID." + Config.RESET_COLOUR);
        }
    }
}


