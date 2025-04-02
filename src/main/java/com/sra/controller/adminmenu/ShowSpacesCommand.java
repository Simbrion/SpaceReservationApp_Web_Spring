package com.sra.controller.adminmenu;

import com.sra.controller.Command;
import com.sra.util.listviewers.SpaceListViewer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ShowSpacesCommand implements Command {

    private final SpaceListViewer spaceListViewer;

    public void execute() {
        spaceListViewer.printList();
    }

    @Override
    public boolean returnToMenuAfterExecution() {
        return true;
    }

}
