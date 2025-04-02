package com.sra.controller.customermenu;

import com.sra.config.Config;
import com.sra.controller.Command;
import com.sra.controller.LoggedInMenuButton;
import com.sra.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class CustomerBackToLoginButton implements LoggedInMenuButton {

    private final CustomerBackToLoginCommand customerBackToLoginCommand;

    @Override
    public void onPush(Customer customer) throws IOException {
        customerBackToLoginCommand.execute(customer);
    }

    @Override
    public void onPush() throws UnsupportedOperationException {
        System.out.println(Config.RED_COLOUR + "OnPush method in " + this.getClass() + " class invoked without logged in Customer passed as a parameter!" + Config.RESET_COLOUR);
        throw new UnsupportedOperationException();
    }

    @Override
    public void show() {
        System.out.println(". Back to login menu");
    }

    @Override
    public Command getCommand() {
        return customerBackToLoginCommand;
    }

}
