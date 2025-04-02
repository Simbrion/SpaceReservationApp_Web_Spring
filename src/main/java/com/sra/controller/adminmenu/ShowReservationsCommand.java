package com.sra.controller.adminmenu;

import com.sra.controller.Command;
import com.sra.util.listviewers.ReservationsListViewer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class ShowReservationsCommand implements Command {

    private final ReservationsListViewer reservationsListViewer;

    public void execute() throws IOException {
       reservationsListViewer.printList();
    }

    @Override
    public boolean returnToMenuAfterExecution() {
        return true;
    }

}
