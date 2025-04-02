package com.sra.service.spaceoperations;

import java.io.IOException;

import com.sra.config.Config;
import com.sra.model.Space;
import com.sra.repository.SpacesDataRepository;
import com.sra.util.UtilitiesToolSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SpaceModifier {

    private final UtilitiesToolSet utilitiesToolSet;
    private final SpacesDataRepository spacesDataRepository;
    private final SpaceNameModifier spaceNameModifier;
    private final SpaceTypeModifier spaceTypeModifier;
    private final SpacePriceModifier spacePriceModifier;

    public void start() throws IOException {

            System.out.println(Config.YELLOW_COLOUR + "What is the name of the space you want to modify?" + Config.RESET_COLOUR);
            utilitiesToolSet.getSpaceListViewer().printList();
            String userInput = utilitiesToolSet.getReader().readLine();
            boolean spaceIsOnTheList = false;

            for (Space space : spacesDataRepository.getData()) {
                if (userInput.equalsIgnoreCase(space.getName())) {
                    spaceIsOnTheList = true;
                    spaceNameModifier.start(space);
                    spaceTypeModifier.modifyType(space);
                    spacePriceModifier.start(space);
                    System.out.println(Config.GREEN_COLOUR + "Changes to the space have been saved!" + Config.RESET_COLOUR);
                    space.printDescription();
                    break;
                }
            }
            if (!spaceIsOnTheList) System.out.println(Config.NO_SPACE_WITH_NAME);

    }
}
