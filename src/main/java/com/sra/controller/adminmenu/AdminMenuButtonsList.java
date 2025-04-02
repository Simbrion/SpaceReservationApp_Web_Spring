package com.sra.controller.adminmenu;

import com.sra.controller.MenuButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminMenuButtonsList {

    private final List<MenuButton> menuButtonsList;

    @Autowired
    public AdminMenuButtonsList (ShowSpacesButton showSpacesButton,
                                 CreateSpaceButton createSpaceButton,
                                 DeleteSpaceButton deleteSpaceButton,
                                 ShowReservationsButton showReservationsButton,
                                 ShowCustomersButton showCustomersButton,
                                 DeleteCustomerButton deleteCustomerButton,
                                 AdminBackToLoginButton adminBackToLoginButton,
                                 ExitAdminButton exitAdminButton
                                 ) {
        this.menuButtonsList = List.of(showSpacesButton,
                                        createSpaceButton,
                                        deleteSpaceButton,
                                        showReservationsButton,
                                        showCustomersButton,
                                        deleteCustomerButton,
                                        adminBackToLoginButton,
                                        exitAdminButton);
    }

    public List<MenuButton> get() {
        return menuButtonsList;
    }

}
