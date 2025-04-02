package com.sra.controller.mainmenu;

import com.sra.controller.Command;
import com.sra.service.customeroperations.CustomerLogIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class CustLoginCommand implements Command {

    private final CustomerLogIn customerLogIn;

    @Override
    public void execute() throws IOException {
        customerLogIn.logIn();
    }

    @Override
    public boolean returnToMenuAfterExecution() {
        return false;
    }
}
