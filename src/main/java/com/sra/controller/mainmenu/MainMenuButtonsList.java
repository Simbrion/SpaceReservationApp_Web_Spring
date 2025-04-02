package com.sra.controller.mainmenu;

import com.sra.controller.MenuButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MainMenuButtonsList {

    private final List<MenuButton> menuButtonsList;

    @Autowired
    public MainMenuButtonsList (MainMenuCustomerLogin mainMenuCustomerLogin,
                                MainMenuAdminLoginButton mainMenuAdminLoginButton,
                                MainMenuExitButton mainMenuExitButton) {
        this.menuButtonsList = List.of(mainMenuCustomerLogin, mainMenuAdminLoginButton, mainMenuExitButton);
    }

    public List<MenuButton> get() {
        return menuButtonsList;
    }
}
