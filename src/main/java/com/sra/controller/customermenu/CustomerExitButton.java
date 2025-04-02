package com.sra.controller.customermenu;

import com.sra.config.Config;
import com.sra.controller.Command;
import com.sra.controller.ExitCommand;
import com.sra.controller.LoggedInMenuButton;
import com.sra.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class CustomerExitButton implements LoggedInMenuButton {

    private final ExitCommand exitCommand;

    @Override
    public void onPush(Customer customer) throws IOException {
        exitCommand.execute();
    }

    @Override
    public void onPush() throws UnsupportedOperationException {
        System.out.println(Config.RED_COLOUR + "OnPush method in " + this.getClass() + " class invoked without logged in Customer passed as a parameter!" + Config.RESET_COLOUR);
        throw new UnsupportedOperationException();
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
