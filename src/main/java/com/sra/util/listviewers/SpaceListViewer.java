package com.sra.util.listviewers;

import com.sra.config.Config;
import com.sra.model.Space;
import com.sra.repository.SpacesDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpaceListViewer implements ListViewer {

    private final SpacesDataRepository spacesDataRepository;

    @Autowired
    public SpaceListViewer (SpacesDataRepository spacesDataRepository) {
        this.spacesDataRepository = spacesDataRepository;
    }

    public void printList () {
        if (spacesDataRepository.isEmpty()) System.out.println(Config.NO_EXISTING_SPACES);
        else {
            System.out.println(Config.YELLOW_COLOUR + "The following spaces are registered in the system:" + Config.RESET_COLOUR);
            for (Space space : spacesDataRepository.getData()) space.printDescription();
        }
    }
}
