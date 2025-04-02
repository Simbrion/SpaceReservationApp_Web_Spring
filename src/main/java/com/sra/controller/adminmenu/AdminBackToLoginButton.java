package com.sra.controller.adminmenu;

import com.sra.controller.Command;
import com.sra.controller.MenuButton;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class AdminBackToLoginButton implements MenuButton {

    private final AdminBackToLoginCommand adminBackToLoginCommand;

    @Override
    public void onPush() throws IOException {
        adminBackToLoginCommand.execute();
    }

    @Override
    public void show() {
        System.out.println(". Back to login menu");
    }

    @Override
    public Command getCommand() {
        return adminBackToLoginCommand;
    }
}
