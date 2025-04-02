package com.sra.controller;


import com.sra.controller.mainmenu.MainMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MainMenuNavigator {

    private MainMenu mainMenu;

    @Autowired
    @Lazy
    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public void navigate() throws IOException {
        try {
            mainMenu.startMenu();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }


}
