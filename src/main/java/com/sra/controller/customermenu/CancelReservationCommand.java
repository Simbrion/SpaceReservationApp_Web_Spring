package com.sra.controller.customermenu;

import com.sra.config.Config;
import com.sra.controller.LoggenInCommand;
import com.sra.model.Customer;
import com.sra.service.reservationoperations.ReservationCanceler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class CancelReservationCommand implements LoggenInCommand {

    private final ReservationCanceler reservationCanceler;

    @Override
    public void execute() throws UnsupportedOperationException {
        System.out.println(Config.RED_COLOUR + "Execute method in " + this.getClass() + " class invoked without logged in Customer passed as a parameter!" + Config.RESET_COLOUR);
        throw new UnsupportedOperationException();
    }

    @Override
    public void execute(Customer customer) throws IOException {
        reservationCanceler.cancelReservation(customer);
    }

    @Override
    public boolean returnToMenuAfterExecution() {
        return true;
    }


}
