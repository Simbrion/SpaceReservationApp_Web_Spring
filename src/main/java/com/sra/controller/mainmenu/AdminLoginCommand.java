package com.sra.controller.mainmenu;

import com.sra.controller.Command;
import com.sra.controller.adminmenu.AdminMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class AdminLoginCommand implements Command {

    private final AdminMenu adminMenu;

    @Override
    public void execute() throws IOException {
        adminMenu.startMenu();
    }

    @Override
    public boolean returnToMenuAfterExecution() {
        return false;
    }

}
