package com.sra.service.spaceoperations;

import java.io.IOException;

import com.sra.config.Config;
import com.sra.model.Space;
import com.sra.util.UtilitiesToolSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SpacePriceSelector {

    private final UtilitiesToolSet utilitiesToolSet;

    public void start(Space space) throws IOException {
        System.out.println(Config.YELLOW_COLOUR + "Please indicate the price of the space " + space.getName() + " (USD per hour)." + Config.RESET_COLOUR);
        String userInput = utilitiesToolSet.getReader().readLine();
        try {
            if (utilitiesToolSet.getInputValidator().validate(utilitiesToolSet.getPositiveDigitValidator(), userInput)) {
                space.setPrice(Integer.parseInt(userInput));
            }
            else {
                System.out.println(Config.WRONG_INPUT_MESSAGE);
                this.start(space);
            }
        } catch (NumberFormatException e) {
            System.out.println(Config.WRONG_INPUT_EXCEPTION);
        }
    }


}
