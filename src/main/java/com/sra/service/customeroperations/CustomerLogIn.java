package com.sra.service.customeroperations;

import java.io.IOException;

import com.sra.config.Config;
import com.sra.controller.customermenu.CustomerMenu;
import com.sra.model.Customer;
import com.sra.repository.RepositoriesSet;
import com.sra.util.UtilitiesToolSet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerLogIn {

    private final RepositoriesSet repositoriesSet;
    private final UtilitiesToolSet utilitiesToolSet;
    private CustomerMenu customerMenu;

    @Autowired
    public void setCustomerMenu(CustomerMenu customerMenu) {
        this.customerMenu = customerMenu;
    }

    public void logIn() throws IOException {
        boolean customerAlreadyExists = false;
        System.out.println("Please provide your name:");
        String userInput = utilitiesToolSet.getReader().getReader().readLine();
        boolean inputIsValid = utilitiesToolSet.getInputValidator().validate(utilitiesToolSet.getCharsOrDigitsInputValidator(), userInput);
        if (!inputIsValid) {
            System.out.println(Config.EMPTY_INPUT);
            logIn();
        }

        //Existing customer login
        if (!repositoriesSet.getCustomersDataRepository().isEmpty()) {
            for (Customer existingCustomer : repositoriesSet.getCustomersDataRepository().getData()) {
                if (existingCustomer.getName().equalsIgnoreCase(userInput)) {
                    customerAlreadyExists = true;
                    System.out.println("\nNice to see you again, " + existingCustomer.getName() + '!');
                    customerMenu.setCustomer(existingCustomer);
                    customerMenu.startMenu();
                }
            }
        }

        //New customer login
        if (!customerAlreadyExists && inputIsValid) {
            Customer newCustomer = new Customer();
            newCustomer.setName(userInput);
            repositoriesSet.getCustomersDataRepository().addCustomer(newCustomer);
            System.out.println("Welcome, new customer " + newCustomer.getName() + '!');
            customerMenu.setCustomer(newCustomer);
            customerMenu.startMenu();
        }
    }
}