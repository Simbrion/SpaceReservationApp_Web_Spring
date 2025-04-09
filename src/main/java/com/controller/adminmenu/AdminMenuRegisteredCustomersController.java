package com.controller.adminmenu;

import com.model.Customer;
import com.repository.CustomersDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminMenuRegisteredCustomersController {

    public final CustomersDataRepository customersDataRepository;

    private static final String REGISTERED_CUSTOMERS_SCREEN = "admin-menu/registered-customers-screen";

    @GetMapping(REGISTERED_CUSTOMERS_SCREEN)
    public String showRegisteredCustomersScreen(Model model) {

        if (customersDataRepository.isEmpty()) {
            model.addAttribute("noCustomersMessage", "There are no registered customers.");
        }
        else {
            List<Customer> customers = customersDataRepository.getData();
            model.addAttribute("customers", customers);
        }

        return REGISTERED_CUSTOMERS_SCREEN;
    }

}
