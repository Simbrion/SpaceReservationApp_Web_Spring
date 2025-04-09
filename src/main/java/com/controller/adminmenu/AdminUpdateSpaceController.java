package com.controller.adminmenu;

import com.model.Space;
import com.repository.SpacesDataRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminUpdateSpaceController {

    private final SpacesDataRepository spacesDataRepository;

    private static final String UPDATE_SPACE_CHOOSING = "admin-menu/update-space-choosing";
    private static final String UPDATE_SPACE_FORM = "admin-menu/update-space-form";
    private static final String UPDATE_SPACE_PROCESS = "admin-menu/update-space-process";
    private static final String UPDATE_SPACE_CONFIRMATION = "admin-menu/update-space-confirmation";

    @GetMapping(UPDATE_SPACE_CHOOSING)
    public String showUpdateSpaceChoosing(Model model) {
        if (spacesDataRepository.isEmpty()) {
            model.addAttribute("noSpacesMessage", "There are no registered spaces.");
        }
        else {
            List<Space> spaces = spacesDataRepository.getData();
            model.addAttribute("spaces", spaces);
            model.addAttribute("space", new Space());
        }
        return UPDATE_SPACE_CHOOSING;
    }

    @PostMapping(UPDATE_SPACE_FORM)
    public String showUpdateSpaceForm(Model model,
                                      @RequestParam("spaceId") int spaceId) {
        Space selectedSpace = spacesDataRepository.getSpaceById(spaceId);
        model.addAttribute("space", selectedSpace);
        return UPDATE_SPACE_FORM;
    }

    @PostMapping(UPDATE_SPACE_PROCESS)
    public String processUpdateSpace(Model model,
                                     @Valid @ModelAttribute("space") Space updatedSpace,
                                     BindingResult result) {

        if (result.hasErrors()) {
            return UPDATE_SPACE_FORM;
        }

        try {
            spacesDataRepository.syncSpaceWithRepository(updatedSpace);
        }
        catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Space with this name already exists!");
            return UPDATE_SPACE_FORM;
        }

        return "redirect:/" + UPDATE_SPACE_CONFIRMATION;
    }

    @GetMapping(UPDATE_SPACE_CONFIRMATION)
    public String showUpdateSpaceConformation() {
        return UPDATE_SPACE_CONFIRMATION;
    }
}
