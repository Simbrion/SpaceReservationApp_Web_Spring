package com.controller.adminmenu;

import com.model.Space;
import com.repository.SpacesDataRepository;
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
public class AdminCreateSpaceController {

    private final SpacesDataRepository spacesDataRepository;

    private static final String CREATE_SPACE_FORM = "admin-menu/create-space-form";
    private static final String CREATE_SPACE_PROCESS = "admin-menu/create-space-process";
    private static final String CREATE_SPACE_CONFORMATION = "admin-menu/create-space-confirmation";

    @GetMapping(CREATE_SPACE_FORM)
    public String showCreateSpaceForm(Model model) {
        model.addAttribute("space", new Space());
        return CREATE_SPACE_FORM;
    }

    @PostMapping(CREATE_SPACE_PROCESS)
    public String processCreateSpaceForm(Model model,
                                        @Valid @ModelAttribute("space") Space space,
                                        BindingResult result) {

        if (result.hasErrors()) {
            return CREATE_SPACE_FORM;
        }

        if (spacesDataRepository.exists(space)) {
                model.addAttribute("spaceExistErrorMessage", "A space with this name already exists!");
                return CREATE_SPACE_FORM;
        }

        spacesDataRepository.addSpace(space);
        return CREATE_SPACE_CONFORMATION;
    }

    @GetMapping(CREATE_SPACE_CONFORMATION)
    public String showCreateSpaceConformation() {
        return CREATE_SPACE_CONFORMATION;
    }

}
