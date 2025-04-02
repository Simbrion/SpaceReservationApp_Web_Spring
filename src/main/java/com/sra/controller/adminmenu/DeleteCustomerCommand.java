package com.sra.controller.adminmenu;

import com.sra.controller.Command;
import com.sra.service.customeroperations.CustomerDeleter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class DeleteCustomerCommand implements Command {

    private final CustomerDeleter customerDeleter;

    public void execute() throws IOException {
        customerDeleter.start();
    }

    @Override
    public boolean returnToMenuAfterExecution() {
        return true;
    }

}
