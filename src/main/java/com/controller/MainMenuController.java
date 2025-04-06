package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainMenuController {

    private static final String MAIN_MENU = "main-menu";
    private static final String ADMIN_MENU = "admin-menu";
    private static final String CUSTOMER_MENU = "customer-menu";

    @GetMapping("/")
    public String startMenu(Model model) {
        return MAIN_MENU;
    }

    @GetMapping(ADMIN_MENU)
    public String showAdminMenu(Model model) {
        return ADMIN_MENU;
    }

    @GetMapping(CUSTOMER_MENU)
    public String showCustomerMenu(Model model) {
        return CUSTOMER_MENU;
    }
}

