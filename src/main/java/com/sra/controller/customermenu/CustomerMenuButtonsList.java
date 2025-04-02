package com.sra.controller.customermenu;

import com.sra.controller.LoggedInMenuButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMenuButtonsList {

        private final List<LoggedInMenuButton> menuButtonsList;

        @Autowired
        public CustomerMenuButtonsList (CreateReservationButton createReservationButton,
                                        CancelReservationButton cancelReservationButton,
                                        ShowMyReservationsButton showMyReservationsButton,
                                        CustomerBackToLoginButton customerBackToLoginButton,
                                        CustomerExitButton customerExitButton) {
            this.menuButtonsList = List.of(createReservationButton,
                                            cancelReservationButton,
                                            showMyReservationsButton,
                                            customerBackToLoginButton,
                                            customerExitButton);
        }

        public List<LoggedInMenuButton> get() {
            return menuButtonsList;
        }

}
