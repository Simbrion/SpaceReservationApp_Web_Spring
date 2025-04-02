package com.sra.util.userinputvalidators;

import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

@Component
public class TimeInputValidator implements ValidationOperation {

    public boolean validate(String userInput) {
        try {
                LocalTime.parse(userInput);
                return true;
        }
        catch (DateTimeParseException e) {
                return false;
        }
    }

}
