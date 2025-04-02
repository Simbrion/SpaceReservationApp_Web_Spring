package com.sra.controller.adminmenu;

import com.sra.controller.Command;
import com.sra.controller.MenuButton;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class DeleteSpaceButton implements MenuButton {

    private final DeleteSpaceCommand deleteSpaceCommand;

    @Override
    public void onPush() throws IOException {
        deleteSpaceCommand.execute();
    }

    @Override
    public void show() {
        System.out.println(". Delete an existing space");
    }

    @Override
    public Command getCommand() {
        return deleteSpaceCommand;
    }
}
