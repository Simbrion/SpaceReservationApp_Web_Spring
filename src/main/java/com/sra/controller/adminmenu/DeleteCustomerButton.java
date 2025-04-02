package com.sra.controller.adminmenu;

import com.sra.controller.Command;
import com.sra.controller.MenuButton;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class DeleteCustomerButton implements MenuButton {

    private final DeleteCustomerCommand deleteCustomerCommand;

    @Override
    public void onPush() throws IOException {
        deleteCustomerCommand.execute();
    }

    @Override
    public void show() {
        System.out.println(". Delete existing customer");
    }

    @Override
    public Command getCommand() {
        return deleteCustomerCommand;
    }
}
