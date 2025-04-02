package com.sra.controller.adminmenu;

import com.sra.controller.Command;
import com.sra.controller.MenuButton;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class ShowReservationsButton implements MenuButton {

    private final ShowSpacesCommand showSpacesCommand;

    @Override
    public void onPush() throws IOException {
        showSpacesCommand.execute();
    }

    @Override
    public void show() {
        System.out.println(". Show all reservations");
    }

    @Override
    public Command getCommand() {
        return showSpacesCommand;
    }
}
