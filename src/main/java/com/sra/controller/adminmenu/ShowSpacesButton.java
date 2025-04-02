package com.sra.controller.adminmenu;

import com.sra.controller.Command;
import com.sra.controller.MenuButton;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class ShowSpacesButton implements MenuButton {

    private final ShowSpacesCommand showSpacesCommand;

    @Override
    public void onPush() throws IOException {
        showSpacesCommand.execute();
    }

    @Override
    public void show() {
        System.out.println(". Show available spaces");
    }

    @Override
    public Command getCommand() {
        return showSpacesCommand;
    }
}
