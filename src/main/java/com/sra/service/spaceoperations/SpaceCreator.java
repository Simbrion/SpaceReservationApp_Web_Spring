package com.sra.service.spaceoperations;

import java.io.IOException;

import com.sra.config.Config;
import com.sra.model.Space;
import com.sra.repository.SpacesDataRepository;
import com.sra.util.UtilitiesToolSet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SpaceCreator {

    private final UtilitiesToolSet utilitiesToolSet;
    private final SpacesDataRepository spacesDataRepository;
    private final SpaceTypeSelector spaceTypeSelector;
    private final SpacePriceSelector spacePriceSelector;
    private Space newSpace;

    @Autowired
    public void setNewSpace(Space newSpace) {
        this.newSpace = newSpace;
    }

    public void start() throws IOException {

        boolean newSpaceNameIsOriginal = true;

        System.out.println(Config.YELLOW_COLOUR + "Please provide a name for the new space." + Config.RESET_COLOUR);
        String userInput = utilitiesToolSet.getReader().readLine();

        boolean inputIsValid = utilitiesToolSet.getInputValidator().validate(utilitiesToolSet.getCharsOrDigitsInputValidator(), userInput);

        if (!inputIsValid) {
            System.out.println(Config.EMPTY_INPUT);
            start();
        }

        for (Space space : spacesDataRepository.getData()) {
            if (userInput.equalsIgnoreCase(space.getName())) {
                System.out.println(Config.RED_COLOUR + "Space with this name already exists. Please choose another name." + Config.RESET_COLOUR);
                newSpaceNameIsOriginal = false;
            }
        }

        if (newSpaceNameIsOriginal && inputIsValid) {
            newSpace.initialize(userInput, spaceTypeSelector, spacePriceSelector);
            spacesDataRepository.addSpace(newSpace);
            System.out.println(Config.GREEN_COLOUR + "A new space has been created!" + Config.RESET_COLOUR);
            newSpace.printDescription();
        }
    }

}
