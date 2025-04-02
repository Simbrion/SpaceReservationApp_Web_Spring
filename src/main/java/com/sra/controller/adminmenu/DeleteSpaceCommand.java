package com.sra.controller.adminmenu;

import com.sra.controller.Command;
import com.sra.service.spaceoperations.SpaceDeleter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class DeleteSpaceCommand implements Command {

    private final SpaceDeleter spaceDeleter;

    public void execute() throws IOException {
        spaceDeleter.start();
    }

    @Override
    public boolean returnToMenuAfterExecution() {
        return true;
    }

}
