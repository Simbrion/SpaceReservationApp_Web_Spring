package com.sra.util.listviewers;

import com.sra.config.Config;
import com.sra.model.Customer;
import com.sra.model.Reservation;
import com.sra.repository.ReservationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReservationsListViewer implements ListViewer {

    private final ReservationsRepository reservationsRepository;

    public void printList() {
        if (reservationsRepository.isEmpty()) System.out.println(Config.NO_EXISTING_RESERVATIONS);
        else {
            System.out.println(Config.YELLOW_COLOUR + "The following reservations are registered in the system:" + Config.RESET_COLOUR);
            int numberOfAllReservations = 0;
            for (Reservation reservation : reservationsRepository.getData()) {
                numberOfAllReservations++;
                reservation.printDescription();
            }
            System.out.println(Config.YELLOW_COLOUR + numberOfAllReservations +" reservations in total." + Config.RESET_COLOUR);
        }
    }

    public void printCustomerReservationsList(Customer customer) {
        if (reservationsRepository.isEmpty()) System.out.println(Config.YELLOW_COLOUR + "There are no reservations for name " + customer.getName() + "." + Config.RESET_COLOUR);
        else {
            System.out.println(Config.YELLOW_COLOUR + "You have the following reservations:" + Config.RESET_COLOUR);
            int numberOfUserReservations = 0;

            for (Reservation reservation : customer.getReservations(reservationsRepository)) {
                    reservation.printDescription();
                    numberOfUserReservations++;
            }

            System.out.println(Config.YELLOW_COLOUR + numberOfUserReservations +" reservations in total." + Config.RESET_COLOUR);
        }
    }
}

