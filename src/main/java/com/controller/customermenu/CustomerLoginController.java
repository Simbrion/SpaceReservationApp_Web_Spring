package com.controller.customermenu;

import com.model.Customer;
import com.repository.CustomersDataRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CustomerLoginController {

    private final CustomersDataRepository customersDataRepository;

    private static final String CUSTOMER_LOGIN_FORM = "customer-menu/customer-login-form";
    private static final String CUSTOMER_MENU = "customer-menu";

    @GetMapping(CUSTOMER_LOGIN_FORM)
    public String createCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return CUSTOMER_LOGIN_FORM;
    }

    @PostMapping("customer-menu/customer-login-process")
    public String processName(Model model,
                              @Valid @ModelAttribute("customer") Customer customer,
                              BindingResult result,
                              HttpSession session) {

        if (result.hasErrors()) {
            return CUSTOMER_LOGIN_FORM;
        }

        Customer existingCustomer = customersDataRepository.findByNameIgnoreCase(customer.getName());
        if (existingCustomer != null) {
            session.setAttribute("customerId", existingCustomer.getId());
            model.addAttribute("existingCustomerMessage", "Nice to see you again, " + customer.getName());
            return CUSTOMER_MENU;
        }

        customersDataRepository.addCustomer(customer);
        session.setAttribute("customerId", customer.getId());
        model.addAttribute("newCustomerMessage", "Welcome, new customer " + customer.getName() + "!");
        return CUSTOMER_MENU;
    }
}
