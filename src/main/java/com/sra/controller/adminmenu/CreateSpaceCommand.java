package com.sra.controller.adminmenu;

import com.sra.controller.Command;
import com.sra.service.spaceoperations.SpaceCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class CreateSpaceCommand implements Command {

    private final SpaceCreator spaceCreator;

    public void execute() throws IOException {
        spaceCreator.start();
    }

    @Override
    public boolean returnToMenuAfterExecution() {
        return true;
    }

}
