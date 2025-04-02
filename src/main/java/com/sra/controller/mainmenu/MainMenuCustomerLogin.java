package com.sra.controller.mainmenu;

import com.sra.controller.Command;
import com.sra.controller.MenuButton;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class MainMenuCustomerLogin implements MenuButton {

    private final CustLoginCommand custLoginCommand;

    @Override
    public void onPush() throws IOException {
        custLoginCommand.execute();
    }

    @Override
    public void show() {
        System.out.println(". Customer");
    }

    @Override
    public Command getCommand() {
        return custLoginCommand;
    }

}
