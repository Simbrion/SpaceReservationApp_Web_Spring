package com.sra.controller.adminmenu;

import com.sra.controller.Command;
import com.sra.controller.MenuButton;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class CreateSpaceButton implements MenuButton {

    private final CreateSpaceCommand createSpaceCommand;

    @Override
    public void onPush() throws IOException {
        createSpaceCommand.execute();
    }

    @Override
    public void show() {
        System.out.println(". Create new space");
    }

    @Override
    public Command getCommand() {
        return createSpaceCommand;
    }
}
