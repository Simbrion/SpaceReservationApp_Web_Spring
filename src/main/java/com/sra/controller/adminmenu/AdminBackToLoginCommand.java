package com.sra.controller.adminmenu;

import com.sra.controller.Command;
import com.sra.controller.MainMenuNavigator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class AdminBackToLoginCommand implements Command {

    private final MainMenuNavigator mainMenuNavigator;

    public void execute() throws IOException {
        mainMenuNavigator.navigate();
    }

    @Override
    public boolean returnToMenuAfterExecution() {
        return false;
    }

}
