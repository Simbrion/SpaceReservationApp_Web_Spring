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
public class SpacePriceModifier {

    private final UtilitiesToolSet utilitiesToolSet;
    private final SpacesDataRepository spacesDataRepository;

    public void start(Space space) throws IOException {
        System.out.println(Config.YELLOW_COLOUR + "Please provide a new price for the space " + space.getName() +
                    ". Insert \"N\" if you do not want to change the price." + Config.RESET_COLOUR);
        String userInput = utilitiesToolSet.getReader().readLine();
        try {
            if (utilitiesToolSet.getInputValidator().validate(utilitiesToolSet.getPositiveDigitValidator(), userInput))  {
                space.setPrice(Integer.parseInt(userInput));
                spacesDataRepository.updateSpacePrice(space.getId(), space.getPrice());
            }
            else if (userInput.equalsIgnoreCase("n"))
                System.out.println((Config.YELLOW_COLOUR + "Price remains unchanged." + Config.RESET_COLOUR));
            else {
                System.out.println(Config.WRONG_INPUT_MESSAGE);
                this.start(space);
            }
        } catch (NumberFormatException e) {
            System.out.println(Config.WRONG_INPUT_EXCEPTION);
        }
    }

}
