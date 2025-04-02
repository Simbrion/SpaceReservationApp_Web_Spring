package com.sra.controller;

import com.sra.model.Customer;

import java.io.IOException;

public interface LoggedInMenuButton extends MenuButton {

    void onPush(Customer customer) throws IOException;

}
