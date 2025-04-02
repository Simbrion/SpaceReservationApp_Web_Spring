package com.sra.controller.mainmenu;

import com.sra.controller.Command;
import com.sra.controller.ExitCommand;
import com.sra.controller.MenuButton;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class MainMenuExitButton implements MenuButton {

   private final ExitCommand exitCommand;

    @Override
    public void onPush() throws IOException {
        exitCommand.execute();
    }

    @Override
    public void show() {
        System.out.println(". Exit");
    }

    @Override
    public Command getCommand() {
        return exitCommand;
    }
}
