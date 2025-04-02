package com.sra.util.userinputvalidators;

import org.springframework.stereotype.Component;

@Component
public class InputValidator {

    public boolean validate(ValidationOperation validationType, String userInput) {

        return validationType.validate(userInput);

    }

}
