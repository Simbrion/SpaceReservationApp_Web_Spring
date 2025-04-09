package com.controller.adminmenu;

import com.model.Space;
import com.repository.SpacesDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminMenuRegisteredSpacesController {

    private final SpacesDataRepository spacesDataRepository;

    private static final String REGISTERED_SPACES_SCREEN = "admin-menu/registered-spaces-screen";

    @GetMapping(REGISTERED_SPACES_SCREEN)
    public String showRegisteredSpacesScreen(Model model) {
        if (spacesDataRepository.isEmpty()) {
            model.addAttribute("noSpacesMessage", "There are no registered spaces.");
        }
        else {
            List<Space> spaces = spacesDataRepository.getData();
            model.addAttribute("spaces", spaces);
        }
        return REGISTERED_SPACES_SCREEN;
    }
}
