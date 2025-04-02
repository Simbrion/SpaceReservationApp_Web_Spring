package com.sra.controller.adminmenu;

import com.sra.controller.Command;
import com.sra.controller.MenuButton;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class ShowCustomersButton implements MenuButton {

    private final ShowCustomersCommand showCustomersCommand;

    @Override
    public void onPush() throws IOException {
        showCustomersCommand.execute();
    }

    @Override
    public void show() {
        System.out.println(". Show existing customers");
    }

    @Override
    public Command getCommand() {
        return showCustomersCommand;
    }
}
