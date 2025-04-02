package com.sra.controller.adminmenu;

import com.sra.controller.Command;
import com.sra.util.listviewers.CustomerListViewer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class ShowCustomersCommand implements Command {

    private final CustomerListViewer customerListViewer;

    public void execute() throws IOException {
       customerListViewer.printList();
    }

    @Override
    public boolean returnToMenuAfterExecution() {
        return true;
    }

}
