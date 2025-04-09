package com.controller.adminmenu;

import com.repository.SpacesDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AdminDeleteSpaceController {

    private final SpacesDataRepository spacesDataRepository;

    private static final String DELETE_SPACE_CHOOSING = "admin-menu/delete-space-choosing";
    private static final String DELETE_SPACE_CONFIRMATION = "admin-menu/delete-space-confirmation";

    @GetMapping(DELETE_SPACE_CHOOSING)
    public String showDeleteSpaceChoosingForm(Model model) {
        if (spacesDataRepository.isEmpty()) {
            model.addAttribute("noSpacesMessage", "There are no registered spaces.");
            return DELETE_SPACE_CHOOSING;
        }

        model.addAttribute("spaces", spacesDataRepository.getData());
        return DELETE_SPACE_CHOOSING;
    }

    @PostMapping(DELETE_SPACE_CONFIRMATION)
    public String processDeleteSpace(Model model,
                                     @RequestParam("spaceId") int spaceId) {
        spacesDataRepository.removeSpace(spacesDataRepository.getSpaceById(spaceId));
        return DELETE_SPACE_CONFIRMATION;
    }
}
