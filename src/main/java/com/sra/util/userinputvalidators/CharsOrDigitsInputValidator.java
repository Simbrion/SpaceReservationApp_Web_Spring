package com.sra.util.userinputvalidators;

import org.springframework.stereotype.Component;

@Component
public class CharsOrDigitsInputValidator implements ValidationOperation {

    public boolean validate(String userInput) {

        if (userInput.equalsIgnoreCase("")) return false;
        char[] userInputAsChars = userInput.toCharArray();
        for (char a : userInputAsChars) {
            if (!(Character.isDigit(a)) && !(Character.isAlphabetic(a))) return false;
        }
        return true;
    }

}
