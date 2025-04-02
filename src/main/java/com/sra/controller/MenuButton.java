package com.sra.controller;

import java.io.IOException;

public interface MenuButton {

    void onPush() throws IOException;

    void show();

    Command getCommand();

}
