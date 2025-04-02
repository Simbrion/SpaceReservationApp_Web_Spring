package com.sra.controller;

import com.sra.config.Config;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ExitCommand implements Command {

    @Override
    public void execute() throws IOException {
        System.out.println(Config.EXIT_MESSAGE);
        System.exit(0);
    }

    @Override
    public boolean returnToMenuAfterExecution() {
        return false;
    }
}
