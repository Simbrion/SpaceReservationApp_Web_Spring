package com.sra.service.spaceoperations;

import java.io.IOException;

import com.sra.config.Config;
import com.sra.model.Space;
import com.sra.util.Reader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SpaceTypeModifier {

    private final Reader reader;
    private final SpaceTypeSelector spaceTypeSelector;

    public void modifyType(Space space) throws IOException {

        while (true) {
            System.out.println(Config.YELLOW_COLOUR +
                               """
                               Do you want to change the type of the selected space?
                                 1. Yes
                                 2. No
                               """ + Config.RESET_COLOUR);
            String userInput = reader.readLine();
            if (userInput.equals("1")) {
                spaceTypeSelector.selectType(space);
                break;
            }
            else if (userInput.equals("2")) {
                break;
            }
            else {
                System.out.println(Config.WRONG_INPUT_MESSAGE);
            }
        }
    }

}
