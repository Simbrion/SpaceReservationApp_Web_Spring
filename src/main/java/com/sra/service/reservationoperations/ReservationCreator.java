package com.sra.service.reservationoperations;

import com.sra.config.Config;
import com.sra.model.*;
import com.sra.repository.RepositoriesSet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class ReservationCreator {

    private final RepositoriesSet repositoriesSet;
    private final ReservationSpaceSelector reservationSpaceSelector;
    private final ReservationDateSelector reservationDateSelector;
    private final ReservationStartTimeSelector reservationStartTimeSelector;
    private final ReservationEndTimeSelector reservationEndTimeSelector;
    private Reservation newReservation;


    public void createReservation(Customer customer) throws IOException {

        if (repositoriesSet.getSpacesDataRepository().isEmpty()) {
            System.out.println(Config.NO_EXISTING_SPACES);
        }

        else {
            reservationSpaceSelector.selectSpace(newReservation);
            if (newReservation.getSpace() != null) reservationDateSelector.selectDate(newReservation);
            if (newReservation.getDate() != null) reservationStartTimeSelector.select(newReservation);
            if (newReservation.getStartTime() != null) reservationEndTimeSelector.selectEndTime(newReservation);

            if (reservationIsReady(newReservation)) {
                newReservation.setCustomer(customer);
                repositoriesSet.getReservationsRepository().addReservation(newReservation);
                System.out.println(Config.GREEN_COLOUR + "Reservation has been added!" + Config.RESET_COLOUR);
                newReservation.printDescription();
            } else
                System.out.println(Config.RED_COLOUR + "Reservation creation failed, please try again." + Config.RESET_COLOUR);
        }
    }

    private boolean reservationIsReady(Reservation reservation) {
        return (reservation.getEndTime()!=null
             && reservation.getSpace()!=null
             && reservation.getDate()!=null
             && reservation.getStartTime()!=null);
    }

    @Autowired
    public void setNewReservation(Reservation newReservation) {
        this.newReservation = newReservation;
    }


}

