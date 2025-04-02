package com.sra.controller;

import com.sra.model.Customer;

import java.io.IOException;

public interface LoggenInCommand extends Command {

    void execute (Customer customer) throws IOException;
}
