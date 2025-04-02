package com.sra.service.spaceoperations;

import java.io.IOException;

import com.sra.model.Space;
import com.sra.model.TypeOfSpace;
import com.sra.config.Config;
import com.sra.repository.SpacesDataRepository;
import com.sra.util.Reader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SpaceTypeSelector {

    private final Reader reader;
    private final SpacesDataRepository spacesDataRepository;

    public void selectType(Space space) throws IOException {
        System.out.println(Config.YELLOW_COLOUR + "Please indicate the type of the space " + space.getName() +
                "\n 1. Open space" +
                "\n 2. Private room" +
                "\n 3. Conference room" + Config.RESET_COLOUR);
        String userInput = reader.readLine();
        switch (userInput) {
            case "1": {
                space.setType(TypeOfSpace.OPEN_SPACE);
                if (space.getId() != 0) spacesDataRepository.updateSpaceType(space.getId(), TypeOfSpace.OPEN_SPACE);
                break;
            }
            case "2": {
                space.setType(TypeOfSpace.PRIVATE_ROOM);
                if (space.getId() != 0) spacesDataRepository.updateSpaceType(space.getId(), TypeOfSpace.PRIVATE_ROOM);
                break;
            }
            case "3": {
                space.setType(TypeOfSpace.CONFERENCE_ROOM);
                if (space.getId() != 0) spacesDataRepository.updateSpaceType(space.getId(), TypeOfSpace.CONFERENCE_ROOM);
                break;
            }
            default: {
                System.out.println(Config.WRONG_INPUT_MESSAGE);
                this.selectType(space);
            }
        }
    }

}
