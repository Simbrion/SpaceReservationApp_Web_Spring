package com.sra.controller;

import java.io.IOException;

public interface Command {

    void execute() throws IOException;

    boolean returnToMenuAfterExecution();

}
