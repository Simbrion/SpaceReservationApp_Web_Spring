package com.sra.controller.mainmenu;

import com.sra.controller.Command;
import com.sra.controller.MenuButton;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class MainMenuAdminLoginButton implements MenuButton {

    private final AdminLoginCommand adminLoginCommand;

    @Override
    public void onPush() throws IOException {
        adminLoginCommand.execute();
    }

    @Override
    public void show() {
        System.out.println(". Administrator");
    }

    @Override
    public Command getCommand() {
        return adminLoginCommand;
    }


}
