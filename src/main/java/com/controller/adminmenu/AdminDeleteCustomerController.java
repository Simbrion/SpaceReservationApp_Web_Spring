package com.controller.adminmenu;

import com.repository.CustomersDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AdminDeleteCustomerController {

    private final CustomersDataRepository customersDataRepository;

    private static final String DELETE_CUSTOMER_CHOOSING = "admin-menu/delete-customer-choosing";
    private static final String DELETE_CUSTOMER_CONFIRMATION = "admin-menu/delete-customer-confirmation";

    @GetMapping(DELETE_CUSTOMER_CHOOSING)
    public String showDeleteCustomerChoosingForm(Model model) {
        if (customersDataRepository.isEmpty()) {
            model.addAttribute("noCustomersMessage", "There are no registered customers.");
            return DELETE_CUSTOMER_CHOOSING;
        }

        model.addAttribute("customers", customersDataRepository.getData());
        return DELETE_CUSTOMER_CHOOSING;
    }

    @PostMapping(DELETE_CUSTOMER_CONFIRMATION)
    public String processDeleteCustomer(Model model,
                                        @RequestParam("customer") int customerId) {
        customersDataRepository.removeCustomer(customersDataRepository.getCustomerById(customerId));
        return DELETE_CUSTOMER_CONFIRMATION;
    }
}
