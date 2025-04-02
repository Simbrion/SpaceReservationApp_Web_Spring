package com.sra.controller.customermenu;

import com.sra.config.Config;
import com.sra.controller.LoggedInMenuButton;
import com.sra.controller.Menu;
import com.sra.model.Customer;
import com.sra.util.UtilitiesToolSet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CustomerMenu implements Menu {

    private final UtilitiesToolSet utilitiesToolSet;
    private final CustomerMenuButtonsList customerMenuButtonsList;
    private Customer customer;

    @Autowired
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void startMenu() throws IOException {
        System.out.println("What would you like me to do?");
        List<LoggedInMenuButton> list = customerMenuButtonsList.get();
        for (LoggedInMenuButton menuButton : list) {
            System.out.print(list.indexOf(menuButton)+1);
            menuButton.show();
        }

        String userInput = utilitiesToolSet.getReader().readLine();
        LoggedInMenuButton pushedButton = null;
        if (utilitiesToolSet.getMenuInputValidator().isValidMenuInput(userInput, customerMenuButtonsList.get())) {
            try {
                pushedButton = customerMenuButtonsList.get().get(Integer.parseInt(userInput) - 1);
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(Config.RED_COLOUR + "Wrong input! Please choose one of the options" + Config.RESET_COLOUR);
                this.startMenu();
            }
            if (pushedButton != null) {
                pushedButton.onPush(customer);
                if (pushedButton.getCommand().returnToMenuAfterExecution()) startMenu();
            }
        }
    }


}
